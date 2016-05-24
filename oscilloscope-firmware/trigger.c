/*
 * trigger.c
 *
 *  Created on: 24 May 2016
 *      Author: Ryan
 */

#include <xdc/runtime/System.h>


#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Task.h>
#include <ti/sysbios/knl/Event.h>

#include <xdc/runtime/Error.h>

#include "trigger.h"
#include "adc.h"
#include "net.h"

Event_Handle AcqEvent;
static Error_Block task_eb;
static Error_Block ev_eb;

#define TRIGGER_BUF_SIZE 1024 * 24

static uint16_t channel_A_samples[TRIGGER_BUF_SIZE] __attribute__(( aligned(8) ));
static uint16_t channel_B_samples[TRIGGER_BUF_SIZE] __attribute__(( aligned(8) ));

static void
triggerSearchISR(UArg arg0, UArg arg1)
{
	uint32_t active;
	uint32_t offset_A = 0, offset_B = 0;

	int i;

	while (1)
	{
		 active = Event_pend(AcqEvent, Event_Id_NONE, EVENT_ID_A_HALF | EVENT_ID_A_FULL | EVENT_ID_B_HALF | EVENT_ID_B_FULL, BIOS_WAIT_FOREVER);

		 if (active & EVENT_ID_A_HALF)
		 {
			 for (i = 0; i < ADC_TRANSFER_SIZE * (ADC_TRANSFERS / 2); i++)
			 {
				 channel_A_samples[offset_A + i] = adc_buffer_A[i];
			 }
		 }
		 else if (active & EVENT_ID_A_FULL)
		 {
			 for (i = ADC_TRANSFER_SIZE * (ADC_TRANSFERS / 2); i < ADC_TRANSFER_SIZE * ADC_TRANSFERS; i++)
			 {
				 channel_A_samples[offset_A + i] = adc_buffer_A[i];
			 }
			 offset_A += ADC_TRANSFER_SIZE * ADC_TRANSFERS;

			 if (offset_A >= TRIGGER_BUF_SIZE - ADC_TRANSFER_SIZE * ADC_TRANSFERS)
			 {
				 offset_A = 0;
			 }
		 }

		 if (active & EVENT_ID_B_HALF)
		 {
			 for (i = 0; i < ADC_TRANSFER_SIZE * (ADC_TRANSFERS / 2); i++)
			 {
				 channel_B_samples[offset_B + i] = adc_buffer_B[i];
			 }
		 }
		 else if (active & EVENT_ID_B_FULL)
		 {
			 for (i = ADC_TRANSFER_SIZE * (ADC_TRANSFERS / 2); i < ADC_TRANSFER_SIZE * ADC_TRANSFERS; i++)
			 {
				 channel_B_samples[offset_B + i] = adc_buffer_B[i];
			 }
			 offset_B += ADC_TRANSFER_SIZE * ADC_TRANSFERS;

			 if (offset_B >= TRIGGER_BUF_SIZE - ADC_TRANSFER_SIZE * ADC_TRANSFERS)
			 {
				 offset_B = 0;
			 }
		 }
	}
}

void
ForceTrigger(void)
{
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

}
