/*
 * acquisition.c
 *
 *  Created on: 12 May 2016
 *      Author: Ryan
 */
#include <xdc/std.h>
#include <xdc/runtime/Error.h>
#include <xdc/runtime/System.h>

#include <ti/sysbios/family/arm/m3/Hwi.h>

#include "inc/hw_types.h"

#include "command.h"
#include "acquisition.h"
#include "trigger.h"

uint16_t adc_buffer_A_PRI[ADC_TRANSFER_SIZE] __attribute__(( aligned(8) ));
uint16_t adc_buffer_A_ALT[ADC_TRANSFER_SIZE] __attribute__(( aligned(8) ));
uint16_t adc_buffer_B_PRI[ADC_TRANSFER_SIZE] __attribute__(( aligned(8) ));
uint16_t adc_buffer_B_ALT[ADC_TRANSFER_SIZE] __attribute__(( aligned(8) ));

//TODO shrink to 1024?
static uint32_t _udmaCtrlTable[1024/sizeof(uint32_t)] __attribute__(( aligned(1024) ));
static tDMAControlTable *udmaCtrlTable = (tDMAControlTable *)_udmaCtrlTable;

static void adcDmaCallback_A_ISR(unsigned int arg);
static void adcDmaCallback_B_ISR(unsigned int arg);

static uint32_t period = 1;

uint32_t
ADCGetPeriod(void)
{
	return period;
}

void
ADCSetFreq(uint32_t freq_khz)
{
	switch (freq_khz)
	{
	case 1000:
	    ADCClockConfigSet(ADC0_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FULL, 32);
	    ADCClockConfigSet(ADC1_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FULL, 32);
	    period = 1;
		break;
	case 500:
    	ADCClockConfigSet(ADC0_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_HALF, 32);
		ADCClockConfigSet(ADC1_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_HALF, 32);
		period = 2;
		break;
	case 200:
    	ADCClockConfigSet(ADC0_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FOURTH, 40);
		ADCClockConfigSet(ADC1_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FOURTH, 40);
		period = 5;
		break;
	case 100:
    	ADCClockConfigSet(ADC0_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_EIGHTH, 40);
		ADCClockConfigSet(ADC1_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_EIGHTH, 40);
		period = 10;
		break;
	default:
		System_printf("Unsupported ADC freq: %d kHz\n", freq_khz);
	}
}

void
ADC_Init(void)
{
    memset(&adc_buffer_A_PRI, 0, sizeof(adc_buffer_A_PRI));
    memset(&adc_buffer_A_ALT, 0, sizeof(adc_buffer_A_ALT));
	memset(&adc_buffer_B_PRI, 0, sizeof(adc_buffer_B_PRI));
    memset(&adc_buffer_B_ALT, 0, sizeof(adc_buffer_B_ALT));

    SysCtlPeripheralEnable(SYSCTL_PERIPH_ADC0);
    SysCtlPeripheralEnable(SYSCTL_PERIPH_ADC1);
    SysCtlPeripheralEnable(SYSCTL_PERIPH_UDMA);
    while (!SysCtlPeripheralReady(SYSCTL_PERIPH_ADC0));
    while (!SysCtlPeripheralReady(SYSCTL_PERIPH_ADC1));
    while (!SysCtlPeripheralReady(SYSCTL_PERIPH_UDMA));

    ADCClockConfigSet(ADC0_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FULL, 32);
    ADCClockConfigSet(ADC1_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FULL, 32);

    ADCSequenceConfigure(ADC0_BASE, 0, ADC_TRIGGER_ALWAYS, 0);
    ADCSequenceConfigure(ADC1_BASE, 0, ADC_TRIGGER_ALWAYS, 0);

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
    uDMAControlBaseSet(_udmaCtrlTable);

    ADCSequenceDMAEnable(ADC0_BASE, 0);
    ADCSequenceDMAEnable(ADC1_BASE, 0);

    uDMAChannelAssign(UDMA_CH24_ADC1_0);

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
    		(void *)(ADC0_BASE + ADC_O_SSFIFO0), adc_buffer_A_PRI, ADC_TRANSFER_SIZE);
    uDMAChannelTransferSet(UDMA_CHANNEL_ADC0 | UDMA_ALT_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC0_BASE + ADC_O_SSFIFO0), adc_buffer_A_ALT, ADC_TRANSFER_SIZE);

    uDMAChannelTransferSet(24 | UDMA_PRI_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC1_BASE + ADC_O_SSFIFO0), adc_buffer_B_PRI, ADC_TRANSFER_SIZE);
    uDMAChannelTransferSet(24 | UDMA_ALT_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC1_BASE + ADC_O_SSFIFO0), adc_buffer_B_ALT, ADC_TRANSFER_SIZE);

	uDMAChannelEnable(14);
	uDMAChannelEnable(24);

	Hwi_Params hwiParams;
    Error_Block eb1, eb2;
    Error_init(&eb1);
    Error_init(&eb2);

    Hwi_Params_init(&hwiParams);
	hwiParams.priority = 33;
	Hwi_create(30, adcDmaCallback_A_ISR, &hwiParams, &eb1);
	hwiParams.priority = 34;
	Hwi_create(62, adcDmaCallback_B_ISR, &hwiParams, &eb2);

	ADCIntEnableEx(ADC0_BASE, ADC_INT_DMA_SS0);
	ADCIntEnableEx(ADC1_BASE, ADC_INT_DMA_SS0);
}

static void
adcDmaCallback_A_ISR(unsigned int arg)
{
	// Clear ADC0 SS0 DMA Interrupt
    HWREG(ADC0_BASE + ADC_O_ISC) = ADC_INT_DMA_SS0;

    // Check if primary mode stopped
    if ((udmaCtrlTable[14 | UDMA_PRI_SELECT].ui32Control & UDMA_CHCTL_XFERMODE_M) == UDMA_MODE_STOP)
    {
    	// Set udma mode (ping pong) and transfer size
    	uint32_t ui32Control = (udmaCtrlTable[14 | UDMA_PRI_SELECT].ui32Control & ~(UDMA_CHCTL_XFERSIZE_M | UDMA_CHCTL_XFERMODE_M));
        ui32Control |= UDMA_MODE_PINGPONG | ((ADC_TRANSFER_SIZE - 1) << 4);
    	udmaCtrlTable[14 | UDMA_PRI_SELECT].ui32Control = ui32Control;

    	Event_post(AcqEvent, EVENT_ID_A_PRI);
    }
    // Otherwise check alt mode stopped
    //TODO Might be able to assume this and change to just "else"?
    else if ((udmaCtrlTable[14 | UDMA_ALT_SELECT].ui32Control & UDMA_CHCTL_XFERMODE_M) == UDMA_MODE_STOP)
    {
    	// Set udma mode (ping pong) and transfer size
        uint32_t ui32Control = (udmaCtrlTable[14 | UDMA_ALT_SELECT].ui32Control & ~(UDMA_CHCTL_XFERSIZE_M | UDMA_CHCTL_XFERMODE_M));
        ui32Control |= UDMA_MODE_PINGPONG | ((ADC_TRANSFER_SIZE - 1) << 4);
    	udmaCtrlTable[14 | UDMA_ALT_SELECT].ui32Control = ui32Control;

    	Event_post(AcqEvent, EVENT_ID_A_ALT);
    }

    // Enable uDMA channel 14
    HWREG(UDMA_ENASET) = 1 << (14 & 0x1f);
}

static void
adcDmaCallback_B_ISR(unsigned int arg)
{
	// Clear ADC1 SS0 DMA Interrupt
    HWREG(ADC1_BASE + ADC_O_ISC) = ADC_INT_DMA_SS0;

    // Check if primary mode stopped
    if ((udmaCtrlTable[24 | UDMA_PRI_SELECT].ui32Control & UDMA_CHCTL_XFERMODE_M) == UDMA_MODE_STOP)
    {
    	// Set udma mode (ping pong) and transfer size
    	uint32_t ui32Control = (udmaCtrlTable[24 | UDMA_PRI_SELECT].ui32Control & ~(UDMA_CHCTL_XFERSIZE_M | UDMA_CHCTL_XFERMODE_M));
        ui32Control |= UDMA_MODE_PINGPONG | ((ADC_TRANSFER_SIZE - 1) << 4);
    	udmaCtrlTable[24 | UDMA_PRI_SELECT].ui32Control = ui32Control;

    	Event_post(AcqEvent, EVENT_ID_B_PRI);
    }
    // Otherwise check alt mode stopped
    //TODO Might be able to assume this and change to just "else"?
    else if ((udmaCtrlTable[24 | UDMA_ALT_SELECT].ui32Control & UDMA_CHCTL_XFERMODE_M) == UDMA_MODE_STOP)
    {
    	// Set udma mode (ping pong) and transfer size
        uint32_t ui32Control = (udmaCtrlTable[24 | UDMA_ALT_SELECT].ui32Control & ~(UDMA_CHCTL_XFERSIZE_M | UDMA_CHCTL_XFERMODE_M));
        ui32Control |= UDMA_MODE_PINGPONG | ((ADC_TRANSFER_SIZE - 1) << 4);
    	udmaCtrlTable[24 | UDMA_ALT_SELECT].ui32Control = ui32Control;

    	Event_post(AcqEvent, EVENT_ID_B_ALT);
    }

    // Enable uDMA channel 24
    HWREG(UDMA_ENASET) = 1 << (24 & 0x1f);
}
