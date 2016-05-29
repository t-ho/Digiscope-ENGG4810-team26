/*
 * trigger.c
 *
 *  Created on: 24 May 2016
 *      Author: Ryan
 */

#include <xdc/runtime/System.h>

#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Clock.h>
#include <ti/sysbios/knl/Task.h>
#include <ti/sysbios/knl/Event.h>

#include <xdc/runtime/Error.h>

#include "trigger.h"
#include "adc.h"
#include "net.h"
#include "eeprom.h"

#include "ui/graphics_thread.h"
#include "ui/trigger_menu.h"

#define TRIGGER_BUF_12_SIZE ADC_TRANSFER_SIZE * 28
#define TRIGGER_BUF_8_SIZE (TRIGGER_BUF_12_SIZE * 2)
#define TRIGGER_BUF_CURRENT_SIZE ((currentSampleSize == SAMPLE_SIZE_12_BIT)?(TRIGGER_BUF_12_SIZE):(TRIGGER_BUF_8_SIZE))

#define MIN_SAMPLES 32

#define MAX_8_BIT_SAMPLES (1024 - COMMANDLENGTH)
#define MAX_12_BIT_SAMPLES (MAX_8_BIT_SAMPLES / 2)

#define MAX_THRESHOLD 5000000

static inline void SendSamples(uint32_t start_pos, uint32_t num);
static inline void ResetBuffers(void);

Event_Handle AcqEvent;
static Error_Block task_eb;
static Error_Block ev_eb;

static uint16_t _channel_A_samples[TRIGGER_BUF_12_SIZE] __attribute__(( aligned(8) ));
static uint16_t _channel_B_samples[TRIGGER_BUF_12_SIZE] __attribute__(( aligned(8) ));

static uint16_t* channel_A_samples_12 = (uint16_t*) &_channel_A_samples;
static uint16_t* channel_B_samples_12 = (uint16_t*) &_channel_B_samples;
static uint8_t* channel_A_samples_8 = (uint8_t*) &_channel_A_samples;
static uint8_t* channel_B_samples_8 = (uint8_t*) &_channel_B_samples;

static uint32_t offset = 0;

static const char* TriggerModeNames[] = {"Auto", "Single", "Normal"};
static const char* TriggerTypeNames[] = {"Level", "Rising", "Falling"};

static TriggerType currentType = TRIGGER_TYPE_LEVEL;
static TriggerMode currentMode = TRIGGER_MODE_AUTO;
static TriggerState currentState = TRIGGER_STATE_STOP;
static Channel currentChannel = CHANNEL_A;

// Threshold in uV
static int32_t currentThreshold = 1000000;
// Actual threshold to use for comparisons
static uint16_t realThreshold;

static SampleSize currentSampleSize = SAMPLE_SIZE_12_BIT;
static uint32_t currentNumSamples = 25000;

static bool forceTriggerFlag = false;

static Semaphore_Struct _bufferlock;
Semaphore_Handle bufferlock;

static uint16_t sample_divisor = 1;

static Semaphore_Struct _settingslock;
static Semaphore_Handle settingslock;

static inline void
sampleCopy8(uint16_t* src_a, uint16_t* src_b, uint32_t offset, int32_t *trigger_index, int32_t countdown, uint32_t skip)
{

	uint8_t *trig_src = currentChannel ? channel_B_samples_8 : channel_A_samples_8;

	int i;
	for (i = 0; i < ADC_TRANSFER_SIZE / skip; i++)
	{
		channel_A_samples_8[offset + i] = src_a[i * skip] >> 4;
		channel_B_samples_8[offset + i] = src_b[i * skip] >> 4;

		if (currentState == TRIGGER_STATE_ARMED && countdown <= 0)
		{

			switch(currentType)
			{
			case TRIGGER_TYPE_LEVEL:
				if (!(trig_src[offset + i] > realThreshold))
					continue;
				break;
			case TRIGGER_TYPE_RISING:
				if (!(trig_src[offset + i] > realThreshold && trig_src[offset + i - 1] <= realThreshold))
					continue;
				break;
			case TRIGGER_TYPE_FALLING:
				if (!(trig_src[offset + i] < realThreshold && trig_src[offset + i - 1] >= realThreshold))
					continue;
				break;
			}

			*trigger_index = offset + i;
			TriggerSetState(TRIGGER_STATE_TRIGGERED);
		}
	}
}


static inline void
sampleCopy12(uint16_t* src_a, uint16_t* src_b, uint32_t offset, int32_t *trigger_index, int32_t countdown, uint32_t skip)
{

	uint16_t *trig_src = currentChannel ? channel_B_samples_12 : channel_A_samples_12;

	int i;
	for (i = 0; i < ADC_TRANSFER_SIZE / skip; i++)
	{
		channel_A_samples_12[offset + i] = src_a[i * skip];
		channel_B_samples_12[offset + i] = src_b[i * skip];

		if (currentState == TRIGGER_STATE_ARMED && countdown <= 0)
		{
			switch(currentType)
			{
			case TRIGGER_TYPE_LEVEL:
				if (!(trig_src[offset + i] > realThreshold))
					continue;
				break;
			case TRIGGER_TYPE_RISING:
				if (!(trig_src[offset + i] > realThreshold && trig_src[offset + i - 1] <= realThreshold))
					continue;
				break;
			case TRIGGER_TYPE_FALLING:
				if (!(trig_src[offset + i] < realThreshold && trig_src[offset + i - 1] >= realThreshold))
					continue;
				break;
			}

			*trigger_index = offset + i;
			TriggerSetState(TRIGGER_STATE_TRIGGERED);
		}
	}
}

static void
triggerSearchTask(UArg arg0, UArg arg1)
{
	int32_t trigger_index;
	int32_t countdown = 0;

	while (1)
	{
		Semaphore_pend(settingslock, BIOS_WAIT_FOREVER);

		// Wait for primary buffers
		Event_pend(AcqEvent, EVENT_ID_A_PRI | EVENT_ID_B_PRI, Event_Id_NONE, BIOS_WAIT_FOREVER);

		if (currentSampleSize == SAMPLE_SIZE_8_BIT)
		{
			sampleCopy8(adc_buffer_A_PRI, adc_buffer_B_PRI, offset, &trigger_index, countdown, sample_divisor);
		}
		else
		{
			sampleCopy12(adc_buffer_A_PRI, adc_buffer_B_PRI, offset, &trigger_index, countdown, sample_divisor);
		}
		Event_pend(AcqEvent, Event_Id_NONE, EVENT_ID_A_PRI | EVENT_ID_B_PRI | EVENT_ID_A_ALT | EVENT_ID_B_ALT, BIOS_NO_WAIT);

		offset += ADC_TRANSFER_SIZE / sample_divisor;

		// Wait for alternate buffers
		Event_pend(AcqEvent, EVENT_ID_A_ALT | EVENT_ID_B_ALT, Event_Id_NONE, BIOS_WAIT_FOREVER);

		if (currentSampleSize == SAMPLE_SIZE_8_BIT)
		{
			sampleCopy8(adc_buffer_A_ALT, adc_buffer_B_ALT, offset, &trigger_index, countdown, sample_divisor);
		}
		else
		{
			sampleCopy12(adc_buffer_A_ALT, adc_buffer_B_ALT, offset, &trigger_index, countdown, sample_divisor);
		}
		Event_pend(AcqEvent, Event_Id_NONE, EVENT_ID_A_PRI | EVENT_ID_B_PRI | EVENT_ID_A_ALT | EVENT_ID_B_ALT, BIOS_NO_WAIT);

		offset += ADC_TRANSFER_SIZE / sample_divisor;
		if (offset >= TRIGGER_BUF_CURRENT_SIZE) offset = 0;

		if (countdown > 0)
		{
			countdown -= (ADC_TRANSFER_SIZE / sample_divisor);
		}
		else if (currentState == TRIGGER_STATE_TRIGGERED)
		{
			int32_t dist = offset - trigger_index;
			if (dist < 0) dist += TRIGGER_BUF_CURRENT_SIZE;

			// Check if enough new samples have been taken
			if (dist > (currentNumSamples / 2))
			{
				// Transmit Samples
				SendSamples(trigger_index, currentNumSamples);

				TriggerSetMode(TriggerGetMode());

				// Wait for the net task to finish transmitting
				Semaphore_pend(bufferlock, BIOS_WAIT_FOREVER);

				countdown = currentNumSamples / 2;
			}
		}
		// Check if trigger has been forced
		else if (forceTriggerFlag)
		{
			trigger_index = offset;
			forceTriggerFlag = false;
			TriggerSetState(TRIGGER_STATE_TRIGGERED);
		}

		Semaphore_post(settingslock);
	}
}

int32_t
TriggerGetThreshold(void)
{
	return currentThreshold;
}

void
TriggerSetThreshold(int32_t threshold)
{
	static char line1[16], line2[16];

	if (threshold > MAX_THRESHOLD)
	{
		threshold = MAX_THRESHOLD;
	}
	else if (threshold < -MAX_THRESHOLD)
	{
		threshold = -MAX_THRESHOLD;
	}

	currentThreshold = threshold;

	EEPROMSave(EEPROM_TRIGGER_THRESHOLD, threshold);

	if (currentSampleSize == SAMPLE_SIZE_8_BIT)
	{
		realThreshold = (255 * (currentThreshold + 1650000.0)) / 3300000;
	}
	else
	{
		realThreshold = (4095 * (currentThreshold + 1650000.0)) / 3300000;
	}

	SI_Micro_Print(line1, line2, currentThreshold, "V");

	TriggerSetThresholdLevelText(line1, line2);

	Command cmd;
	cmd.type = COMMAND_TRIGGER_THRESHOLD;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = currentThreshold;

	NetSend(&cmd, 0);

	System_printf("Trigger threshold set to %d mV, (0x%2x)\n", currentThreshold / 1000, realThreshold);
}

TriggerMode
TriggerGetMode(void)
{
	return currentMode;
}

void
TriggerSetMode(TriggerMode mode)
{
	switch (mode)
	{
	case TRIGGER_MODE_AUTO:
	case TRIGGER_MODE_NORMAL:
	case TRIGGER_MODE_SINGLE:
		break;
	default:
		mode = TRIGGER_MODE_AUTO;
	}

	currentMode = mode;
	EEPROMSave(EEPROM_TRIGGER_MODE, mode);

	// Set state as appropriate
	if (TriggerGetMode() == TRIGGER_MODE_SINGLE)
	{
		TriggerSetState(TRIGGER_STATE_STOP);
	}
	else
	{
		TriggerSetState(TRIGGER_STATE_ARMED);
	}

	TriggerSetModeText(TriggerModeNames[mode]);

	Command cmd;
	cmd.type = COMMAND_TRIGGER_MODE;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = currentMode;

	NetSend(&cmd, 0);
}

TriggerType
TriggerGetType(void)
{
	return currentType;
}

void
TriggerSetType(TriggerType type)
{
	switch (type)
	{
	case TRIGGER_TYPE_FALLING:
	case TRIGGER_TYPE_RISING:
	case TRIGGER_TYPE_LEVEL:
		break;
	default:
		type = TRIGGER_TYPE_RISING;
	}

	currentType = type;

	EEPROMSave(EEPROM_TRIGGER_TYPE, type);

	TriggerSetTypeText(TriggerTypeNames[type]);

	Command cmd;
	cmd.type = COMMAND_TRIGGER_TYPE;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = currentType;

	NetSend(&cmd, 0);
}

TriggerState
TriggerGetState(void)
{
	return currentState;
}

void
TriggerSetState(TriggerState state)
{
	// Can't trigger without connection
	if (state == TRIGGER_STATE_TRIGGERED && NetGetClients() == 0)
	{
		return;
	}

	currentState = state;

	Command cmd;
	cmd.type = COMMAND_TRIGGER_STATE;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = currentState;

	NetSend(&cmd, 0);

	cmd.type = _COMMAND_TRIGGER_INDICATOR;
	UISend(&cmd, 0);
}

Channel
TriggerGetChannel(void)
{
	return currentChannel;
}

void
TriggerSetChannel(Channel channel)
{
	static char channelName[] = "Channel A";

	currentChannel = channel;
	channelName[8] = 'A' + channel;

	TriggerSetChannelText(channelName);
}

static void
TransmitBuffer8(uint8_t* buffer, uint8_t type, int32_t triggerindex, uint16_t numSamples)
{
	int seqnum;
	int numPackets = numSamples / MAX_8_BIT_SAMPLES;
	int start_index = triggerindex - (numSamples / 2);
	if (start_index < 0) start_index += TRIGGER_BUF_8_SIZE;

	SampleCommand scmd;
	scmd.num_samples = MAX_8_BIT_SAMPLES;
	scmd.period = ADCGetPeriod() * sample_divisor;
	scmd.type = type;

	for (seqnum = 0; seqnum <= numPackets; seqnum++)
	{
		scmd.seq_num = seqnum;

		uint8_t* trans = &buffer[start_index + seqnum * MAX_8_BIT_SAMPLES];
		if (trans > &buffer[TRIGGER_BUF_8_SIZE])
		{
			trans -= TRIGGER_BUF_8_SIZE;
		}
		scmd.buffer = trans;

		if (seqnum == numPackets)
		{
			scmd.num_samples = numSamples - MAX_8_BIT_SAMPLES * numPackets;
		}

		NetSend((Command *) &scmd, 0);
	}
}

static void
TransmitBuffer12(uint16_t* buffer, uint8_t type, int32_t triggerindex, uint16_t numSamples)
{
	int seqnum;
	int numPackets = numSamples / MAX_12_BIT_SAMPLES;
	int start_index = triggerindex - (numSamples / 2);
	if (start_index < 0) start_index += TRIGGER_BUF_12_SIZE;

	SampleCommand scmd;
	scmd.num_samples = MAX_12_BIT_SAMPLES;
	scmd.period = ADCGetPeriod() * sample_divisor;
	scmd.type = type;

	for (seqnum = 0; seqnum <= numPackets; seqnum++)
	{
		scmd.seq_num = seqnum;

		uint16_t* trans = &buffer[start_index + seqnum * MAX_12_BIT_SAMPLES];
		if (trans > &buffer[TRIGGER_BUF_12_SIZE])
		{
			trans -= TRIGGER_BUF_12_SIZE;
		}
		scmd.buffer = trans;

		if (seqnum == numPackets)
		{
			scmd.num_samples = numSamples - MAX_12_BIT_SAMPLES * numPackets;
		}

		NetSend((Command *) &scmd, 0);
	}
}

SampleSize
TriggerGetSampleSize(void)
{
	return currentSampleSize;
}

void
TriggerSetSampleSize(SampleSize sampleSize)
{
	switch (sampleSize)
	{
	case SAMPLE_SIZE_8_BIT:
	case SAMPLE_SIZE_12_BIT:
		break;
	default:
		sampleSize = SAMPLE_SIZE_12_BIT;
	}

	if (Semaphore_pend(settingslock, 1000))
	{
		ResetBuffers();

		offset = 0;

		if (currentSampleSize == SAMPLE_SIZE_8_BIT && sampleSize == SAMPLE_SIZE_12_BIT)
		{
			currentSampleSize = sampleSize;
			TriggerSetNumSamples(TriggerGetNumSamples() / 2);
		}
		else if (currentSampleSize == SAMPLE_SIZE_12_BIT && sampleSize == SAMPLE_SIZE_8_BIT)
		{
			currentSampleSize = sampleSize;
			TriggerSetNumSamples(TriggerGetNumSamples() * 2);
		}
		else
		{
			currentSampleSize = sampleSize;
		}

		EEPROMSave(EEPROM_TRIGGER_SAMPLESIZE, currentSampleSize);

		TriggerSetThreshold(TriggerGetThreshold());

		TriggerSetMode(TriggerGetMode());

		Command cmd;
		cmd.type = COMMAND_SAMPLE_LENGTH;

		cmd.args[0] = TriggerGetSampleSize();
		cmd.is_confirmation = COMMAND_IS_CONFIRMATION;

		NetSend(&cmd, 0);

		Semaphore_post(settingslock);
	}
	else
	{
		System_printf("Unable to change sample size");
	}
}

void
TriggerSetNumSamples(uint32_t numSamples)
{
	uint32_t max = (TriggerGetSampleSize() == SAMPLE_SIZE_8_BIT) ? TRIGGER_BUF_8_SIZE - 4096 : TRIGGER_BUF_12_SIZE - 2048;

	if (numSamples >= max)
	{
		numSamples = max;
	}

	if (numSamples < MIN_SAMPLES)
	{
		numSamples = MIN_SAMPLES;
	}

	currentNumSamples = numSamples;

	EEPROMSave(EEPROM_TRIGGER_NUMSAMPLES, currentNumSamples);

	Command cmd;
	cmd.type = COMMAND_NUM_SAMPLES;
	cmd.args[0] = currentNumSamples;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;

	NetSend(&cmd, 0);
}


uint32_t
TriggerGetNumSamples(void)
{
	return currentNumSamples;
}

void
TriggerSetSampleDivisor(uint16_t divisor)
{
	if (Semaphore_pend(settingslock, 1000))
	{
		offset = 0;
		sample_divisor = divisor;

		Semaphore_post(settingslock);
	}
	else
	{
		System_printf("Unable to change sample divisor");
	}
}

uint16_t
TriggerGetSampleDivisor(void)
{
	return sample_divisor;
}

void
ForceTrigger(void)
{
	forceTriggerFlag = true;
}

static inline void
SendSamples(uint32_t triggerindex, uint32_t num)
{
	if (currentSampleSize == SAMPLE_SIZE_8_BIT)
	{
		TransmitBuffer8(channel_A_samples_8, SAMPLE_PACKET_A_8, triggerindex, num);
		TransmitBuffer8(channel_B_samples_8, SAMPLE_PACKET_B_8, triggerindex, num);
	}
	else
	{
		TransmitBuffer12(channel_A_samples_12, SAMPLE_PACKET_A_12, triggerindex, num);
		TransmitBuffer12(channel_B_samples_12, SAMPLE_PACKET_B_12, triggerindex, num);
	}

	Command cmd;
	cmd.type = _COMMAND_ACQUISITION_SEND_COMPLETE;

	NetSend(&cmd, 0);
}

static inline void
ResetBuffers(void)
{
    memset(&_channel_A_samples, 0, sizeof(_channel_A_samples));
    memset(&_channel_B_samples, 0, sizeof(_channel_B_samples));
    offset = 0;
}

void
Trigger_Init(void)
{
	ResetBuffers();

	AcqEvent = Event_create(NULL, &ev_eb);

	Task_Params taskParams;
	Task_Handle taskHandle;

    Task_Params_init(&taskParams);
	taskParams.stackSize = 768;
    taskParams.priority = 10;
    taskHandle = Task_create((Task_FuncPtr)triggerSearchTask, &taskParams, &task_eb);

    if (taskHandle == NULL)
    {
        System_printf("Error: Failed to create trigger Task\n");
    }

    Semaphore_Params params;
    Semaphore_Params_init(&params);
    params.mode = Semaphore_Mode_BINARY;

    Semaphore_construct(&_bufferlock, 0, &params);
    bufferlock = Semaphore_handle(&_bufferlock);

    Semaphore_construct(&_settingslock, 0, &params);
    settingslock = Semaphore_handle(&_settingslock);

    Semaphore_post(settingslock);

    TriggerNotify();
}

void
TriggerNotify(void)
{
    TriggerSetChannel(TriggerGetChannel());
    TriggerSetMode((TriggerMode)EEPROMLoad(EEPROM_TRIGGER_MODE));
    TriggerSetType((TriggerType)EEPROMLoad(EEPROM_TRIGGER_TYPE));
    TriggerSetThreshold(EEPROMLoad(EEPROM_TRIGGER_THRESHOLD));
    TriggerSetSampleSize((SampleSize)EEPROMLoad(EEPROM_TRIGGER_SAMPLESIZE));
    TriggerSetNumSamples(EEPROMLoad(EEPROM_TRIGGER_NUMSAMPLES));
}
