/*
 * adc.c
 *
 *  Created on: 12 May 2016
 *      Author: Ryan
 */
#include <xdc/runtime/Error.h>

#include <ti/sysbios/family/arm/m3/Hwi.h>

#include "inc/hw_types.h"

#include "command.h"
#include "adc.h"
#include "net.h"

uint16_t adc_pos_A = 0;
uint16_t adc_pos_B = 0;
uint16_t adc_buffer_A[ADC_BUF_SIZE] __attribute__(( aligned(8) ));
uint16_t adc_buffer_B[ADC_BUF_SIZE] __attribute__(( aligned(8) ));


static uint32_t udmaCtrlTable[4096/sizeof(uint32_t)] __attribute__(( aligned(1024) ));

static void adcDmaCallback_A_ISR(unsigned int arg);
static void adcDmaCallback_B_ISR(unsigned int arg);

void
ADC_Init(void)
{
    memset(&adc_buffer_A, 0, sizeof(adc_buffer_A));
    memset(&adc_buffer_B, 0, sizeof(adc_buffer_B));

    SysCtlPeripheralEnable(SYSCTL_PERIPH_ADC0);
    SysCtlPeripheralEnable(SYSCTL_PERIPH_ADC1);
    SysCtlPeripheralEnable(SYSCTL_PERIPH_UDMA);
    while (!SysCtlPeripheralReady(SYSCTL_PERIPH_ADC0));
    while (!SysCtlPeripheralReady(SYSCTL_PERIPH_ADC1));
    while (!SysCtlPeripheralReady(SYSCTL_PERIPH_UDMA));

    ADCClockConfigSet(ADC0_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FULL, 8);
    ADCClockConfigSet(ADC1_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FULL, 8);

//    ADCSequenceConfigure(ADC0_BASE, 0 /*SS0*/, ADC_TRIGGER_PROCESSOR, 3 /*priority*/);  // SS0-SS3 priorities must always be different
//    ADCSequenceConfigure(ADC0_BASE, 3 /*SS3*/, ADC_TRIGGER_PROCESSOR, 0 /*priority*/);  // so change SS3 to prio0 when SS0 gets set to prio3

    ADCSequenceConfigure(ADC0_BASE, 0, ADC_TRIGGER_ALWAYS, 0);
    ADCSequenceConfigure(ADC1_BASE, 0, ADC_TRIGGER_ALWAYS, 0);

//    ADCSequenceConfigure(ADC0_BASE, 0, ADC_TRIGGER_PROCESSOR, 0);

    // Channel A Pin set to AIN3 (PE0)
    // Channel B Pin set to AIN2 (PE1)
    int i;

    for (i = 0; i < 7; i++)
    {
        ADCSequenceStepConfigure(ADC0_BASE, 0, i, ADC_CTL_CH3);
        ADCSequenceStepConfigure(ADC1_BASE, 0, i, ADC_CTL_CH2);
    }
    ADCSequenceStepConfigure(ADC0_BASE, 0, 7, ADC_CTL_CH3 | ADC_CTL_IE | ADC_CTL_END);
    ADCSequenceStepConfigure(ADC1_BASE, 0, 7, ADC_CTL_CH2 | ADC_CTL_IE | ADC_CTL_END);

    ADCSequenceEnable(ADC0_BASE, 0);
    ADCSequenceEnable(ADC1_BASE, 0);

    ADCIntClear(ADC0_BASE, 0);
    ADCIntClear(ADC1_BASE, 0);

    uDMAEnable();
    uDMAControlBaseSet(udmaCtrlTable);

    ADCSequenceDMAEnable(ADC0_BASE, 0);
    ADCSequenceDMAEnable(ADC1_BASE, 0);

    uDMAChannelAssign(UDMA_CH24_ADC1_0);
//    uDMAChannelSelectSecondary(UDMA_DEF_SSI1RX_SEC_ADC10);

    // disable some bits
    uDMAChannelAttributeDisable(UDMA_CHANNEL_ADC0, UDMA_ATTR_ALTSELECT /*start with ping-pong PRI side*/ |
         UDMA_ATTR_REQMASK /*unmask*/);
    uDMAChannelAttributeDisable(24, UDMA_ATTR_ALTSELECT /*start with ping-pong PRI side*/ |
         UDMA_ATTR_REQMASK /*unmask*/);
    // enable some bits
    uDMAChannelAttributeEnable(UDMA_CHANNEL_ADC0, UDMA_ATTR_USEBURST /*only allow burst transfers*/ | UDMA_ATTR_HIGH_PRIORITY /*low priority*/);
    uDMAChannelAttributeEnable(24, UDMA_ATTR_USEBURST /*only allow burst transfers*/ | UDMA_ATTR_HIGH_PRIORITY /*low priority*/);
    // set dma params on PRI_ and ALT_SELECT
    uDMAChannelControlSet(UDMA_CHANNEL_ADC0 | UDMA_PRI_SELECT, UDMA_SIZE_16 | UDMA_SRC_INC_NONE | UDMA_DST_INC_16 | UDMA_ARB_8);
    uDMAChannelControlSet(UDMA_CHANNEL_ADC0 | UDMA_ALT_SELECT, UDMA_SIZE_16 | UDMA_SRC_INC_NONE | UDMA_DST_INC_16 | UDMA_ARB_8);
    uDMAChannelControlSet(24 | UDMA_PRI_SELECT, UDMA_SIZE_16 | UDMA_SRC_INC_NONE | UDMA_DST_INC_16 | UDMA_ARB_8);
    uDMAChannelControlSet(24 | UDMA_ALT_SELECT, UDMA_SIZE_16 | UDMA_SRC_INC_NONE | UDMA_DST_INC_16 | UDMA_ARB_8);

    uDMAChannelTransferSet(UDMA_CHANNEL_ADC0 | UDMA_PRI_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC0_BASE + ADC_O_SSFIFO0), adc_buffer_A, ADC_SAMPLE_BUF_SIZE);
    uDMAChannelTransferSet(UDMA_CHANNEL_ADC0 | UDMA_ALT_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC0_BASE + ADC_O_SSFIFO0), adc_buffer_A, ADC_SAMPLE_BUF_SIZE);

    uDMAChannelTransferSet(24 | UDMA_PRI_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC1_BASE + ADC_O_SSFIFO0), adc_buffer_B, ADC_SAMPLE_BUF_SIZE);
    uDMAChannelTransferSet(24 | UDMA_ALT_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC1_BASE + ADC_O_SSFIFO0), adc_buffer_B, ADC_SAMPLE_BUF_SIZE);

	uDMAChannelEnable(14);
	uDMAChannelEnable(24);

	Hwi_Params hwiParams;
    Error_Block eb1, eb2;
    Error_init(&eb1);
    Error_init(&eb2);

    Hwi_Params_init(&hwiParams);
	hwiParams.priority = 3;
	Hwi_create(30, adcDmaCallback_A_ISR, &hwiParams, &eb1);
	hwiParams.priority = 4;
	Hwi_create(62, adcDmaCallback_B_ISR, &hwiParams, &eb2);

	ADCIntEnable(ADC0_BASE, 0);
	ADCIntEnable(ADC1_BASE, 0);
}

void
ADCPause(void)
{
	ADCSequenceDisable(ADC0_BASE, 0);
	ADCSequenceDisable(ADC1_BASE, 0);
}

void
ADCResume(void)
{
	ADCSequenceEnable(ADC0_BASE, 0);
	ADCSequenceEnable(ADC1_BASE, 0);
}

void
ForceTrigger(void)
{
	SampleCommand scmd;
	scmd.type = SAMPLE_PACKET_A_12;
	scmd.num_samples = (1024 - COMMANDLENGTH) / 2;
	scmd.period = 1;

	int seqnum = 0;

	ADCPause();

	while ((seqnum + 1) * scmd.num_samples < ADC_BUF_SIZE)
	{
		scmd.seq_num = seqnum;
		scmd.buffer = &adc_buffer_A[seqnum * scmd.num_samples];
		NetSend((Command *) &scmd, 100);
		seqnum++;
	}
	scmd.seq_num = seqnum;
	scmd.buffer = &adc_buffer_A[seqnum * scmd.num_samples];
	scmd.num_samples = ADC_BUF_SIZE - seqnum * scmd.num_samples;
	NetSend((Command *) &scmd, 100);

	scmd.type = SAMPLE_PACKET_B_12;
	seqnum = 0;

	while ((seqnum + 1) * scmd.num_samples < ADC_BUF_SIZE)
	{
		scmd.seq_num = seqnum;
		scmd.buffer = &adc_buffer_B[seqnum * scmd.num_samples];
		NetSend((Command *) &scmd, 100);
		seqnum++;
	}
	scmd.seq_num = seqnum;
	scmd.buffer = &adc_buffer_B[seqnum * scmd.num_samples];
	scmd.num_samples = ADC_BUF_SIZE - seqnum * scmd.num_samples;
	NetSend((Command *) &scmd, 100);
}

static void
adcDmaCallback_A_ISR(unsigned int arg)
{
	// Clear ADC0 Interrupt
    HWREG(ADC0_BASE + ADC_O_ISC) = (1 << 0);

    if ((((tDMAControlTable *) udmaCtrlTable)[14 | UDMA_PRI_SELECT].ui32Control & UDMA_CHCTL_XFERMODE_M) == UDMA_MODE_STOP)
    {
    	uint32_t ui32Control = (((tDMAControlTable *) udmaCtrlTable)[14 | UDMA_PRI_SELECT].ui32Control &
                       ~(UDMA_CHCTL_XFERSIZE_M | UDMA_CHCTL_XFERMODE_M));

        ui32Control |= UDMA_MODE_PINGPONG | ((ADC_SAMPLE_BUF_SIZE - 1) << 4);

    	((tDMAControlTable *) udmaCtrlTable)[14 | UDMA_PRI_SELECT].ui32Control = ui32Control;
    }
    else if ((((tDMAControlTable *) udmaCtrlTable)[14 | UDMA_ALT_SELECT].ui32Control & UDMA_CHCTL_XFERMODE_M) == UDMA_MODE_STOP)
    {
        uint32_t ui32Control = (((tDMAControlTable *) udmaCtrlTable)[14 | UDMA_ALT_SELECT].ui32Control &
                       ~(UDMA_CHCTL_XFERSIZE_M | UDMA_CHCTL_XFERMODE_M));

        ui32Control |= UDMA_MODE_PINGPONG | ((ADC_SAMPLE_BUF_SIZE - 1) << 4);

    	((tDMAControlTable *) udmaCtrlTable)[14 | UDMA_ALT_SELECT].ui32Control = ui32Control;
    }

    // Enable uDMA channel 14
    HWREG(UDMA_ENASET) = 1 << (14 & 0x1f);
}

static void
adcDmaCallback_B_ISR(unsigned int arg)
{
	// Clear ADC1 Interrupt
    HWREG(ADC1_BASE + ADC_O_ISC) = (1 << 0);

    if ((((tDMAControlTable *) udmaCtrlTable)[24 | UDMA_PRI_SELECT].ui32Control & UDMA_CHCTL_XFERMODE_M) == UDMA_MODE_STOP)
    {
    	uint32_t ui32Control = (((tDMAControlTable *) udmaCtrlTable)[24 | UDMA_PRI_SELECT].ui32Control &
                       ~(UDMA_CHCTL_XFERSIZE_M | UDMA_CHCTL_XFERMODE_M));

        ui32Control |= UDMA_MODE_PINGPONG | ((ADC_SAMPLE_BUF_SIZE - 1) << 4);

    	((tDMAControlTable *) udmaCtrlTable)[24 | UDMA_PRI_SELECT].ui32Control = ui32Control;
    }
    else if ((((tDMAControlTable *) udmaCtrlTable)[24 | UDMA_ALT_SELECT].ui32Control & UDMA_CHCTL_XFERMODE_M) == UDMA_MODE_STOP)
    {
        uint32_t ui32Control = (((tDMAControlTable *) udmaCtrlTable)[24 | UDMA_ALT_SELECT].ui32Control &
                       ~(UDMA_CHCTL_XFERSIZE_M | UDMA_CHCTL_XFERMODE_M));

        ui32Control |= UDMA_MODE_PINGPONG | ((ADC_SAMPLE_BUF_SIZE - 1) << 4);

    	((tDMAControlTable *) udmaCtrlTable)[24 | UDMA_ALT_SELECT].ui32Control = ui32Control;
    }

    // Enable uDMA channel 24
    HWREG(UDMA_ENASET) = 1 << (24 & 0x1f);
}
