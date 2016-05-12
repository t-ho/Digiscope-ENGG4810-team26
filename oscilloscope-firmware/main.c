/*
 * Copyright (c) 2015, Texas Instruments Incorporated
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * *  Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * *  Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * *  Neither the name of Texas Instruments Incorporated nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 *  ======== main.c ========
 */

#include <string.h>

/* XDCtools Header files */
#include <xdc/std.h>
#include <xdc/runtime/Error.h>
#include <xdc/runtime/System.h>

/* BIOS Header files */
#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Task.h>
#include <ti/sysbios/knl/Clock.h>
#include <ti/sysbios/knl/Semaphore.h>

/* TI-RTOS Header files */
#include <ti/drivers/EMAC.h>
#include <ti/drivers/GPIO.h>
// #include <ti/drivers/I2C.h>
// #include <ti/drivers/SDSPI.h>
// #include <ti/drivers/SPI.h>
// #include <ti/drivers/UART.h>
// #include <ti/drivers/USBMSCHFatFs.h>
// #include <ti/drivers/Watchdog.h>
// #include <ti/drivers/WiFi.h>


#include <stdbool.h>

/* Board Header file */
#include "Board.h"

#include "driverlib/adc.h"
#include "driverlib/sysctl.h"
#include "driverlib/udma.h"
#include "driverlib/interrupt.h"
#include "inc/hw_memmap.h"
#include "inc/hw_adc.h"
#include "inc/hw_udma.h"

#include "grlib/grlib.h"
#include "grlib/widget.h"

#include "common.h"
#include "net.h"
#include "drivers/SSD1289_driver.h"
#include "drivers/XPT2046_driver.h"



uint32_t udmaCtrlTable[1024/sizeof(uint32_t)] __attribute__(( aligned(1024) ));

void ADCprocess(uint32_t ch)
{
	//if (uDMAChannelModeGet(ch) != UDMA_MODE_STOP) return;
	// Faster way?
    if ((((tDMAControlTable *) udmaCtrlTable)[ch].ui32Control & UDMA_CHCTL_XFERMODE_M) != UDMA_MODE_STOP) return;

    // store the next buffer in the uDMA transfer descriptor
    // the ADC is read directly into the correct emacBufTx to be transmitted
    uDMAChannelTransferSet(ch, UDMA_MODE_PINGPONG, (void *)(ADC0_BASE + ADC_O_SSFIFO0), &adc_buffer[adc_pos], ADC_SAMPLE_BUF_SIZE);
}

void
adcDmaCallback(unsigned int arg)
{
	ADCIntClear(ADC0_BASE, 0);

	adc_pos += ADC_SAMPLE_BUF_SIZE;

    if (adc_pos >= ADC_BUF_SIZE)
    {
    	adc_pos = 0;
    }

    ADCprocess(UDMA_CHANNEL_ADC0 | UDMA_PRI_SELECT);
    ADCprocess(UDMA_CHANNEL_ADC0 | UDMA_ALT_SELECT);

    uDMAChannelEnable(UDMA_CHANNEL_ADC0);
}

/*
 *  ======== heartBeatFxn ========
 *  Toggle the Board_LED0. The Task_sleep is determined by arg0 which
 *  is configured for the heartBeat Task instance.
 */
void heartBeatFxn(UArg arg0, UArg arg1)
{
    while (1) {
        Task_sleep((unsigned int)arg0);
        GPIO_toggle(Board_LED0);
        System_flush();
    }
}

void
ADC_Init(void)
{
    memset(&adc_buffer, 0, sizeof(adc_buffer));

    SysCtlPeripheralEnable(SYSCTL_PERIPH_ADC0);
    SysCtlPeripheralEnable(SYSCTL_PERIPH_UDMA);
    while (!SysCtlPeripheralReady(SYSCTL_PERIPH_ADC0));
    while (!SysCtlPeripheralReady(SYSCTL_PERIPH_UDMA));

    ADCClockConfigSet(ADC0_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_FULL, 32);

//    ADCSequenceConfigure(ADC0_BASE, 0 /*SS0*/, ADC_TRIGGER_PROCESSOR, 3 /*priority*/);  // SS0-SS3 priorities must always be different
//    ADCSequenceConfigure(ADC0_BASE, 3 /*SS3*/, ADC_TRIGGER_PROCESSOR, 0 /*priority*/);  // so change SS3 to prio0 when SS0 gets set to prio3

    ADCSequenceConfigure(ADC0_BASE, 0, ADC_TRIGGER_ALWAYS, 0);
//    ADCSequenceConfigure(ADC0_BASE, 0, ADC_TRIGGER_PROCESSOR, 0);
    // Pin set to AIN0 (PE3)
    int i;

    for (i = 0; i < 7; i++)
    {
        ADCSequenceStepConfigure(ADC0_BASE, 0, i, ADC_CTL_CH0);
    }
    ADCSequenceStepConfigure(ADC0_BASE, 0, 7,  ADC_CTL_CH0 | ADC_CTL_IE | ADC_CTL_END);

    ADCSequenceEnable(ADC0_BASE, 0);

    uDMAEnable();
    uDMAControlBaseSet(udmaCtrlTable);
    ADCSequenceDMAEnable(ADC0_BASE, 0);

    // disable some bits
    uDMAChannelAttributeDisable(UDMA_CHANNEL_ADC0, UDMA_ATTR_ALTSELECT /*start with ping-pong PRI side*/ |
         UDMA_ATTR_REQMASK /*unmask*/);
    // enable some bits
    uDMAChannelAttributeEnable(UDMA_CHANNEL_ADC0, UDMA_ATTR_USEBURST /*only allow burst transfers*/ | UDMA_ATTR_HIGH_PRIORITY /*low priority*/);
    // set dma params on PRI_ and ALT_SELECT
    uDMAChannelControlSet(UDMA_CHANNEL_ADC0 | UDMA_PRI_SELECT, UDMA_SIZE_16 | UDMA_SRC_INC_NONE | UDMA_DST_INC_16 | UDMA_ARB_128);
    uDMAChannelControlSet(UDMA_CHANNEL_ADC0 | UDMA_ALT_SELECT, UDMA_SIZE_16 | UDMA_SRC_INC_NONE | UDMA_DST_INC_16 | UDMA_ARB_128);


    uDMAChannelTransferSet(UDMA_CHANNEL_ADC0 | UDMA_PRI_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC0_BASE + ADC_O_SSFIFO0), adc_buffer, ADC_SAMPLE_BUF_SIZE);
    uDMAChannelTransferSet(UDMA_CHANNEL_ADC0 | UDMA_ALT_SELECT, UDMA_MODE_PINGPONG,
    		(void *)(ADC0_BASE + ADC_O_SSFIFO0), adc_buffer, ADC_SAMPLE_BUF_SIZE);

	//ADCIntEnableEx(ADC0_BASE, ADC_INT_DMA_SS0);
	uDMAChannelEnable(UDMA_CHANNEL_ADC0);

	//ADCIntRegister(ADC0_BASE, 0, &adcDmaCallback);
	ADCIntEnable(ADC0_BASE, 0);
}

/*
 *  ======== main ========
 */
int main(void)
{
    /* Call board init functions */
    Board_initGeneral();
    //Board_initGPIO();
    Board_initEMAC();
    // Board_initI2C();
    // Board_initSDSPI();
    // Board_initSPI();
    // Board_initUART();
    // Board_initUSB(Board_USBDEVICE);
    // Board_initUSBMSCHFatFs();
    // Board_initWatchdog();
    // Board_initWiFi();

    Init_Semaphores();
    Init_SendQueue();

    SSD1289_Init();
    XPT2046_Init();
    XPT2046_SetCallback(WidgetPointerMessage);

    ADC_Init();

     /* Turn on user LED */
    GPIO_write(Board_LED0, Board_LED_ON);

    System_printf("Starting the example\nSystem provider is set to SysMin. "
                  "Halt the target to view any SysMin contents in ROV.\n");
    /* SysMin will only print to the console when you call flush or exit */
    System_flush();

    /* Start BIOS */
    BIOS_start();

    return (0);
}
