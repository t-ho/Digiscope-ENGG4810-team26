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

#include "ui/trigger_menu.h"

Event_Handle AcqEvent;
static Error_Block task_eb;
static Error_Block ev_eb;

#define TRIGGER_BUF_SIZE ADC_TRANSFER_SIZE * 2 * 14
#define TRIGGER_LEVEL 0x700

static uint16_t channel_A_samples[TRIGGER_BUF_SIZE] __attribute__(( aligned(8) ));
static uint16_t channel_B_samples[TRIGGER_BUF_SIZE] __attribute__(( aligned(8) ));

static uint32_t offset_A = 0, offset_B = 0;

static const char* TriggerModeNames[] = {"Auto", "Normal", "Single"};
static const char* TriggerTypeNames[] = {"Rising", "Falling", "Level"};

static TriggerType currentType = TRIGGER_TYPE_FALLING;
static TriggerMode currentMode = TRIGGER_MODE_NORMAL;
static uint32_t currentChannel = 0;

static void
triggerSearchISR(UArg arg0, UArg arg1)
{
	int i;

	uint32_t events;

	int trigger_index;

	while (1)
	{
		events = Event_pend(AcqEvent, Event_Id_NONE, EVENT_ID_A_PRI | EVENT_ID_A_ALT | EVENT_ID_B_PRI | EVENT_ID_B_ALT, BIOS_WAIT_FOREVER);

		trigger_index = -1;

		if (events & EVENT_ID_A_PRI)
		{
			for (i = 0; i < ADC_TRANSFER_SIZE; i++)
			{
				channel_A_samples[offset_A + i] = adc_buffer_A_PRI[i];

				if (trigger_index < 0 && channel_A_samples[offset_A + i] > TRIGGER_LEVEL)
				{
					trigger_index = i;
				}
			}
		}
		else if (events & EVENT_ID_A_ALT)
		{
			for (i = 0; i < ADC_TRANSFER_SIZE; i++)
			{
				channel_A_samples[offset_A + ADC_TRANSFER_SIZE + i] = adc_buffer_A_ALT[i];

				if (trigger_index < 0 && channel_A_samples[offset_A + ADC_TRANSFER_SIZE + i] > TRIGGER_LEVEL)
				{
					trigger_index = i + ADC_TRANSFER_SIZE;
				}
			}

			offset_A += 2 * ADC_TRANSFER_SIZE;

			if (offset_A >= TRIGGER_BUF_SIZE)
			{
				offset_A = 0;
			}
		}

		if (events & EVENT_ID_B_PRI)
		{
			for (i = 0; i < ADC_TRANSFER_SIZE; i++)
			{
				channel_B_samples[offset_B + i] = adc_buffer_B_PRI[i];
			}
		}
		else if (events & EVENT_ID_B_ALT)
		{
			for (i = 0; i < ADC_TRANSFER_SIZE; i++)
			{
				channel_B_samples[offset_B + ADC_TRANSFER_SIZE + i] = adc_buffer_B_ALT[i];
			}

			offset_B += 2 * ADC_TRANSFER_SIZE;

			if (offset_B >= TRIGGER_BUF_SIZE)
			{
				offset_B = 0;
			}
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

}

void
TriggerSetThreshold(int32_t threshold)
{

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

	uint32_t current = Clock_getTicks();
	if (current - last < 1000)
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
		scmd.buffer = &channel_A_samples[seqnum * maxSamples];
		NetSend((Command *) &scmd, 100);
		seqnum++;
	}
	scmd.seq_num = seqnum;
	scmd.buffer = &channel_A_samples[seqnum * maxSamples];
	scmd.num_samples = TRIGGER_BUF_SIZE - seqnum * maxSamples;
	NetSend((Command *) &scmd, 100);

	scmd.type = SAMPLE_PACKET_B_12;
	scmd.num_samples = maxSamples;
	seqnum = 0;

	while ((seqnum + 1) * maxSamples < TRIGGER_BUF_SIZE)
	{
		scmd.seq_num = seqnum;
		scmd.buffer = &channel_B_samples[seqnum * maxSamples];
		NetSend((Command *) &scmd, 100);
		seqnum++;
	}
	scmd.seq_num = seqnum;
	scmd.buffer = &channel_B_samples[seqnum * maxSamples];
	scmd.num_samples = TRIGGER_BUF_SIZE - seqnum * maxSamples;
	NetSend((Command *) &scmd, 100);

	offset_A = 0;
	offset_B = 0;
}

void
Trigger_Init(void)
{
    memset(&channel_A_samples, 0, sizeof(channel_A_samples));
    memset(&channel_B_samples, 0, sizeof(channel_B_samples));

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

}
