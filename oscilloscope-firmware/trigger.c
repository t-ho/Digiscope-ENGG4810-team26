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

#define SAMPLECOPY8(src, offset) { \
	chan_A_dest_8 = &channel_A_samples_8[offset]; \
	chan_A_src = adc_buffer_A_##src; \
	chan_B_dest_8 = &channel_B_samples_8[offset]; \
	chan_B_src = adc_buffer_B_##src; \
	while(chan_A_src <= &adc_buffer_A_##src[ADC_TRANSFER_SIZE]) \
	{ \
		*chan_A_dest_8 = *chan_A_src >> 4; \
		*chan_B_dest_8 = *chan_B_src >> 4; \
/*		if (trigger_index < 0 && *chan_A_dest_8 > realThreshold) \
		{ \
			trigger_index = offset + i; \
		} */\
		chan_A_dest_8++; \
		chan_A_src++; \
		chan_B_dest_8++; \
		chan_B_src++; \
	} \
}

#define SAMPLECOPY12(src, offset) { \
	chan_A_dest_12 = &channel_A_samples_12[offset]; \
	chan_A_src = adc_buffer_A_##src; \
	chan_B_dest_12 = &channel_B_samples_12[offset]; \
	chan_B_src = adc_buffer_B_##src; \
	while(chan_A_src <= &adc_buffer_A_##src[ADC_TRANSFER_SIZE]) \
	{ \
		*chan_A_dest_12 = *chan_A_src; \
		*chan_B_dest_12 = *chan_B_src; \
/*		if (trigger_index < 0 && *chan_A_dest_12 > realThreshold) \
		{ \
			trigger_index = offset + i; \
		} */\
		chan_A_dest_12++; \
		chan_A_src++; \
		chan_B_dest_12++;*\
		chan_B_src++; \
	} \
}

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

static const char* TriggerModeNames[] = {"Auto", "Normal", "Single"};
static const char* TriggerTypeNames[] = {"Rising", "Falling", "Level"};

static TriggerType currentType = TRIGGER_TYPE_FALLING;
static TriggerMode currentMode = TRIGGER_MODE_NORMAL;
static uint32_t currentChannel = 0;

static int32_t currentThreshold = 0;
static uint16_t realThreshold = 0x700;

static SampleSize samplesize = SAMPLE_SIZE_12_BIT;
static uint32_t numSamples = 25000;

static void
triggerSearchISR(UArg arg0, UArg arg1)
{
//	int trigger_index;

	uint8_t 	*chan_A_dest_8, 	*chan_B_dest_8;
	uint16_t 	*chan_A_dest_12, 	*chan_B_dest_12;
	uint16_t 	*chan_A_src, 		*chan_B_src;

	while (1)
	{
//		trigger_index = -1;

		Event_pend(AcqEvent, EVENT_ID_A_PRI | EVENT_ID_B_PRI, Event_Id_NONE, BIOS_WAIT_FOREVER);

		if (samplesize == SAMPLE_SIZE_8_BIT)
			SAMPLECOPY8(PRI, offset)
		else
			SAMPLECOPY12(PRI, offset)

		Event_pend(AcqEvent, EVENT_ID_A_ALT | EVENT_ID_B_ALT, Event_Id_NONE, BIOS_WAIT_FOREVER);

		offset += ADC_TRANSFER_SIZE;

		if (samplesize == SAMPLE_SIZE_8_BIT)
			SAMPLECOPY8(ALT, offset)
		else
			SAMPLECOPY12(ALT, offset)

		offset += ADC_TRANSFER_SIZE;

		if (offset >= numSamples)
		{
			offset = 0;
		}

//		if (trigger_index >= 0)
//		{
//			ForceTrigger();
//		}
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

	currentThreshold = threshold;

	SI_Micro_Print(line1, line2, currentThreshold, "V");

	TriggerSetThresholdLevelText(line1, line2);
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
TransmitBuffer8(uint8_t* buffer, uint8_t type, uint16_t start_index)
{
	int seqnum = 0;

	SampleCommand scmd;
	scmd.num_samples = MAX_8_BIT_SAMPLES;
	scmd.period = 1;
	scmd.type = type;

	while ((seqnum + 1) * MAX_8_BIT_SAMPLES < numSamples)
	{
		scmd.seq_num = seqnum;
		scmd.buffer = &buffer[seqnum * MAX_8_BIT_SAMPLES];
		NetSend((Command *) &scmd, 0);
		seqnum++;
	}
	scmd.seq_num = seqnum;
	scmd.buffer = &buffer[seqnum * MAX_8_BIT_SAMPLES];
	scmd.num_samples = numSamples - seqnum * MAX_8_BIT_SAMPLES;
	NetSend((Command *) &scmd, 0);
}

static void
TransmitBuffer12(uint16_t* buffer, uint8_t type, uint16_t start_index)
{
	int seqnum = 0;

	SampleCommand scmd;
	scmd.num_samples = MAX_12_BIT_SAMPLES;
	scmd.period = 1;
	scmd.type = type;

	while ((seqnum + 1) * MAX_12_BIT_SAMPLES < numSamples)
	{
		scmd.seq_num = seqnum;
		scmd.buffer = &buffer[seqnum * MAX_12_BIT_SAMPLES];
		NetSend((Command *) &scmd, 0);
		seqnum++;
	}
	scmd.seq_num = seqnum;
	scmd.buffer = &buffer[seqnum * MAX_12_BIT_SAMPLES];
	scmd.num_samples = numSamples - seqnum * MAX_12_BIT_SAMPLES;
	NetSend((Command *) &scmd, 0);
}

SampleSize
TriggerGetSampleSize(void)
{
	return samplesize;
}

void
TriggerSetSampleSize(SampleSize mode)
{
	ResetBuffers();

	offset = 0;

	if (samplesize == SAMPLE_SIZE_8_BIT && mode == SAMPLE_SIZE_12_BIT)
	{
		TriggerSetNumSamples(TriggerGetNumSamples() / 2);
	}
	else if (samplesize == SAMPLE_SIZE_12_BIT && mode == SAMPLE_SIZE_8_BIT)
	{
		TriggerSetNumSamples(TriggerGetNumSamples() * 2);
	}

	samplesize = mode;

	Command cmd;
	cmd.type = COMMAND_SAMPLE_LENGTH;
	cmd.args[0] = TriggerGetSampleSize();
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;

	NetSend(&cmd, 0);
}

void
TriggerSetNumSamples(uint32_t newNum)
{
	uint32_t max = (TriggerGetSampleSize() == SAMPLE_SIZE_8_BIT) ? TRIGGER_BUF_8_SIZE : TRIGGER_BUF_12_SIZE;

	if (newNum >= max)
	{
		newNum = max;
	}

	if (newNum < 1024)
	{
		newNum = 1024;
	}

	numSamples = newNum;

	Command cmd;
	cmd.type = COMMAND_NUM_SAMPLES;
	cmd.args[0] = numSamples;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;

	NetSend(&cmd, 0);
}

uint32_t
TriggerGetNumSamples(void)
{
	return numSamples;
}

void
ForceTrigger(void)
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

	if (samplesize == SAMPLE_SIZE_8_BIT)
	{
		TransmitBuffer8(channel_A_samples_8, SAMPLE_PACKET_A_8, 0);
		TransmitBuffer8(channel_B_samples_8, SAMPLE_PACKET_B_8, 0);
	}
	else
	{
		TransmitBuffer12(channel_A_samples_12, SAMPLE_PACKET_A_12, 0);
		TransmitBuffer12(channel_B_samples_12, SAMPLE_PACKET_B_12, 0);
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
	taskParams.stackSize = 512;
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
