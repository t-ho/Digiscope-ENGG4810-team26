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

#include "ui/graphics_thread.h"
#include "ui/trigger_menu.h"

#define MIN_TRIGGER_PERIOD 500

#define TRIGGER_BUF_12_SIZE ADC_TRANSFER_SIZE * 28
#define TRIGGER_BUF_8_SIZE (TRIGGER_BUF_12_SIZE * 2)

#define MAX_8_BIT_SAMPLES (1024 - COMMANDLENGTH)
#define MAX_12_BIT_SAMPLES (MAX_8_BIT_SAMPLES / 2)

#define MAX_THRESHOLD 5000000

static void SendSamples(uint32_t start_pos, uint32_t num);
static void ResetBuffers(void);

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
static TriggerState currentState = TRIGGER_STATE_ARMED;
static uint32_t currentChannel = 0;

// Threshold in uV
static int32_t currentThreshold = 1000000;
// Actual threshold to use for comparisons
static uint16_t realThreshold;

static SampleSize currentSampleSize = SAMPLE_SIZE_12_BIT;
static uint32_t currentNumSamples = 25000;

static inline void
sampleCopy8(uint16_t* src_a, uint16_t* src_b, uint32_t offset, int32_t *trigger_index, int32_t *countdown)
{

	uint8_t *trig_src = currentChannel ? channel_B_samples_8 : channel_A_samples_8;

	int i;
	for (i = 0; i < ADC_TRANSFER_SIZE; i++)
	{
		channel_A_samples_8[offset + i] = src_a[i] >> 4;
		channel_B_samples_8[offset + i] = src_b[i] >> 4;

		if (currentState == TRIGGER_STATE_ARMED)
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

			*trigger_index = offset + i - (currentNumSamples / 2);
			if (*trigger_index < 0) *trigger_index += TRIGGER_BUF_8_SIZE;
			TriggerSetState(TRIGGER_STATE_TRIGGERED);
			*countdown = currentNumSamples / (ADC_TRANSFER_SIZE * 4);
		}
	}
}


static inline void
sampleCopy12(uint16_t* src_a, uint16_t* src_b, uint32_t offset, int32_t *trigger_index, int32_t *countdown)
{

	uint16_t *trig_src = currentChannel ? channel_B_samples_12 : channel_A_samples_12;

	int i;
	for (i = 0; i < ADC_TRANSFER_SIZE; i++)
	{
		channel_A_samples_12[offset + i] = src_a[i];
		channel_B_samples_12[offset + i] = src_b[i];

		if (currentState == TRIGGER_STATE_ARMED)
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

			*trigger_index = offset + i - (currentNumSamples / 2);
			if (*trigger_index < 0) *trigger_index += TRIGGER_BUF_12_SIZE;
			TriggerSetState(TRIGGER_STATE_TRIGGERED);
			*countdown = currentNumSamples / (ADC_TRANSFER_SIZE * 4);
		}
	}
}

static void
triggerSearchISR(UArg arg0, UArg arg1)
{
	int32_t trigger_index, countdown;

	while (1)
	{
		Event_pend(AcqEvent, EVENT_ID_A_PRI | EVENT_ID_B_PRI, Event_Id_NONE, BIOS_WAIT_FOREVER);

		if (currentSampleSize == SAMPLE_SIZE_8_BIT)
		{
			sampleCopy8(adc_buffer_A_PRI, adc_buffer_B_PRI, offset, &trigger_index, &countdown);
		}
		else
		{
			sampleCopy12(adc_buffer_A_PRI, adc_buffer_B_PRI, offset, &trigger_index, &countdown);
		}

		Event_pend(AcqEvent, EVENT_ID_A_ALT | EVENT_ID_B_ALT, Event_Id_NONE, BIOS_WAIT_FOREVER);

		offset += ADC_TRANSFER_SIZE;

		if (currentSampleSize == SAMPLE_SIZE_8_BIT)
		{
			sampleCopy8(adc_buffer_A_ALT, adc_buffer_B_ALT, offset, &trigger_index, &countdown);

			offset += ADC_TRANSFER_SIZE;

			if (offset >= TRIGGER_BUF_8_SIZE)
			{
				offset = 0;
			}
		}
		else
		{
			sampleCopy12(adc_buffer_A_ALT, adc_buffer_B_ALT, offset, &trigger_index, &countdown);

			offset += ADC_TRANSFER_SIZE;

			if (offset >= TRIGGER_BUF_12_SIZE)
			{
				offset = 0;
			}
		}

		if (currentState == TRIGGER_STATE_TRIGGERED)
		{
			if (countdown < 0)
			{
				SendSamples(trigger_index, currentNumSamples);
				if (TriggerGetMode() == TRIGGER_MODE_SINGLE)
				{
					TriggerSetState(TRIGGER_STATE_STOP);
				}
				else
				{
					TriggerSetState(TRIGGER_STATE_ARMED);
				}
			}
			else
			{
				countdown--;
			}
		}
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
	currentMode = mode;

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
	currentType = type;

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
	currentState = state;

	Command cmd;
	cmd.type = COMMAND_TRIGGER_STATE;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = currentState;

	NetSend(&cmd, 0);
}

uint32_t
TriggerGetChannel(void)
{
	return currentChannel;
}

void
TriggerSetChannel(uint32_t channel)
{
	static char channelName[] = "Channel A";

	currentChannel = channel;
	channelName[8] = 'A' + channel;

	TriggerSetChannelText(channelName);
}

static void
TransmitBuffer8(uint8_t* buffer, uint8_t type, uint16_t start_index, uint16_t numSamples)
{
	int seqnum;

	int numPackets = numSamples / MAX_8_BIT_SAMPLES;

	SampleCommand scmd;
	scmd.num_samples = MAX_8_BIT_SAMPLES;
	scmd.period = 1;
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
TransmitBuffer12(uint16_t* buffer, uint8_t type, uint16_t start_index, uint16_t numSamples)
{
	int seqnum;

	int numPackets = numSamples / MAX_12_BIT_SAMPLES;

	SampleCommand scmd;
	scmd.num_samples = MAX_12_BIT_SAMPLES;
	scmd.period = 1;
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

	TriggerSetThreshold(TriggerGetThreshold());

	Command cmd;
	cmd.type = COMMAND_SAMPLE_LENGTH;
	cmd.args[0] = TriggerGetSampleSize();
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;

	NetSend(&cmd, 0);
}

void
TriggerSetNumSamples(uint32_t numSamples)
{
	uint32_t max = (TriggerGetSampleSize() == SAMPLE_SIZE_8_BIT) ? TRIGGER_BUF_8_SIZE - 4096 : TRIGGER_BUF_12_SIZE - 2048;

	if (numSamples >= max)
	{
		numSamples = max;
	}

	if (numSamples < 1024)
	{
		numSamples = 1024;
	}

	currentNumSamples = numSamples;

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
ForceTrigger(void)
{
	TriggerSetState(TRIGGER_STATE_TRIGGERED);
}

static void
SendSamples(uint32_t start_pos, uint32_t num)
{
	static uint32_t last = 0;

	// Ignore trigger if too soon
	uint32_t current = Clock_getTicks();
	if (current - last < MIN_TRIGGER_PERIOD)
	{
		return;
	}
	else
	{
		last = current;
	}

	ADCPause();

	if (currentSampleSize == SAMPLE_SIZE_8_BIT)
	{
		TransmitBuffer8(channel_A_samples_8, SAMPLE_PACKET_A_8, start_pos, num);
		TransmitBuffer8(channel_B_samples_8, SAMPLE_PACKET_B_8, start_pos, num);
	}
	else
	{
		TransmitBuffer12(channel_A_samples_12, SAMPLE_PACKET_A_12, start_pos, num);
		TransmitBuffer12(channel_B_samples_12, SAMPLE_PACKET_B_12, start_pos, num);
	}

	offset = 0;
}

static void
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
    taskHandle = Task_create((Task_FuncPtr)triggerSearchISR, &taskParams, &task_eb);

    if (taskHandle == NULL)
    {
        System_printf("Error: Failed to create trigger Task\n");
    }

    TriggerSetChannel(TriggerGetChannel());
    TriggerSetMode(TriggerGetMode());
    TriggerSetType(TriggerGetType());
    TriggerSetThreshold(TriggerGetThreshold());
    TriggerSetSampleSize(TriggerGetSampleSize());
}
