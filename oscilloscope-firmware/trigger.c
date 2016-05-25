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

Event_Handle AcqEvent;
static Error_Block task_eb;
static Error_Block ev_eb;

#define TRIGGER_BUF_SIZE ADC_TRANSFER_SIZE * 2 * 14

#define SAMPLECOPY16(dest, src, offset) for (i = 0; i < ADC_TRANSFER_SIZE; i++) \
{\
	dest[offset + i] = src[i]; \
	if (trigger_index < 0 && dest[offset + i] > realThreshold) \
	{ \
		trigger_index = offset + i; \
	} \
} \

static uint16_t _channel_A_samples[TRIGGER_BUF_SIZE] __attribute__(( aligned(8) ));
static uint16_t _channel_B_samples[TRIGGER_BUF_SIZE] __attribute__(( aligned(8) ));

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

static void
triggerSearchISR(UArg arg0, UArg arg1)
{
	int i;

	int trigger_index;

	while (1)
	{
		trigger_index = -1;

		Event_pend(AcqEvent, EVENT_ID_A_PRI | EVENT_ID_B_PRI, Event_Id_NONE, BIOS_WAIT_FOREVER);

		SAMPLECOPY16(channel_A_samples_12, adc_buffer_A_PRI, offset)
		SAMPLECOPY16(channel_B_samples_12, adc_buffer_B_PRI, offset)

		Event_pend(AcqEvent, EVENT_ID_A_ALT | EVENT_ID_B_ALT, Event_Id_NONE, BIOS_WAIT_FOREVER);

		offset += ADC_TRANSFER_SIZE;

		SAMPLECOPY16(channel_A_samples_12, adc_buffer_A_ALT, offset)
		SAMPLECOPY16(channel_B_samples_12, adc_buffer_B_ALT, offset)

		offset += ADC_TRANSFER_SIZE;

		if (offset >= TRIGGER_BUF_SIZE)
		{
			offset = 0;
		}

		if (trigger_index >= 0)
		{
			ForceTrigger();
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

	short maxSamples = (1024 - COMMANDLENGTH) / 2;

	SampleCommand scmd;
	scmd.type = SAMPLE_PACKET_A_12;
	scmd.num_samples = maxSamples;
	scmd.period = 1;

	int seqnum = 0;

	ADCPause();

	while ((seqnum + 1) * maxSamples < TRIGGER_BUF_SIZE)
	{
		scmd.seq_num = seqnum;
		scmd.buffer = &channel_A_samples_12[seqnum * maxSamples];
		NetSend((Command *) &scmd, 100);
		seqnum++;
	}
	scmd.seq_num = seqnum;
	scmd.buffer = &channel_A_samples_12[seqnum * maxSamples];
	scmd.num_samples = TRIGGER_BUF_SIZE - seqnum * maxSamples;
	NetSend((Command *) &scmd, 100);

	scmd.type = SAMPLE_PACKET_B_12;
	scmd.num_samples = maxSamples;
	seqnum = 0;

	while ((seqnum + 1) * maxSamples < TRIGGER_BUF_SIZE)
	{
		scmd.seq_num = seqnum;
		scmd.buffer = &channel_B_samples_12[seqnum * maxSamples];
		NetSend((Command *) &scmd, 100);
		seqnum++;
	}
	scmd.seq_num = seqnum;
	scmd.buffer = &channel_B_samples_12[seqnum * maxSamples];
	scmd.num_samples = TRIGGER_BUF_SIZE - seqnum * maxSamples;
	NetSend((Command *) &scmd, 100);

	offset = 0;
}

static void
ClearBuffers(void)
{
    memset(&_channel_A_samples, 0, sizeof(_channel_A_samples));
    memset(&_channel_B_samples, 0, sizeof(_channel_B_samples));
}

void
Trigger_Init(void)
{
	ClearBuffers();

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

    TriggerSetChannel(0);
    TriggerSetMode(TriggerGetMode());
    TriggerSetType(TriggerGetType());
    TriggerSetThreshold(10000);

}
