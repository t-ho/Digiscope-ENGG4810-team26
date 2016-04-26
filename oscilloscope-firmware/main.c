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

#include <sys/socket.h>

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


#define TASKSTACKSIZE   512

/* Touch screen calibration */
#define X_MIN 200
#define X_MAX 1850
#define X_PX 320.0f
#define Y_MIN 200
#define Y_MAX 1900
#define Y_PX 240.0f

#define PREC_TOUCH_CONST 10

#define ADC_SAMPLE_BUF_SIZE 8
#define ADC_BUF_SIZE 1024

Task_Struct task0Struct;
Char task0Stack[TASKSTACKSIZE];
Task_Struct task1Struct;
Char task1Stack[TASKSTACKSIZE];

Clock_Struct TouchClkStruct;
Clock_Handle TouchClkHandle;

uint16_t x, y;

uint16_t adc_pos = 0;
uint16_t adc_buffer[ADC_BUF_SIZE] __attribute__(( aligned(8) ));

uint32_t udmaCtrlTable[1024/sizeof(uint32_t)] __attribute__(( aligned(1024) ));

void gpio_out_data(uint16_t c)
{
	GPIO_write(LCD_DATA_0, (c >> 0) & 1);
	GPIO_write(LCD_DATA_1, (c >> 1) & 1);
	GPIO_write(LCD_DATA_2, (c >> 2) & 1);
	GPIO_write(LCD_DATA_3, (c >> 3) & 1);
	GPIO_write(LCD_DATA_4, (c >> 4) & 1);
	GPIO_write(LCD_DATA_5, (c >> 5) & 1);
	GPIO_write(LCD_DATA_6, (c >> 6) & 1);
	GPIO_write(LCD_DATA_7, (c >> 7) & 1);
	GPIO_write(LCD_DATA_8, (c >> 8) & 1);
	GPIO_write(LCD_DATA_9, (c >> 9) & 1);
	GPIO_write(LCD_DATA_10, (c >> 10) & 1);
	GPIO_write(LCD_DATA_11, (c >> 11) & 1);
	GPIO_write(LCD_DATA_12, (c >> 12) & 1);
	GPIO_write(LCD_DATA_13, (c >> 13) & 1);
	GPIO_write(LCD_DATA_14, (c >> 14) & 1);
	GPIO_write(LCD_DATA_15, (c >> 15) & 1);
}

void Write_Command(uint16_t c)
{
	GPIO_write(LCD_CS, 0);
	GPIO_write(LCD_RS,0);
	gpio_out_data(c);
	GPIO_write(LCD_WR,0);
	GPIO_write(LCD_WR,1);
	GPIO_write(LCD_CS,1);
}

void Write_Data(uint16_t c)
{
	GPIO_write(LCD_CS, 0);
	GPIO_write(LCD_RS,1);
	gpio_out_data(c);
	GPIO_write(LCD_WR,0);
	GPIO_write(LCD_WR,1);
	GPIO_write(LCD_CS,1);
}

void Write_Command_Data(uint16_t cmd, uint16_t dat)
{
	Write_Command(cmd);
	Write_Data(dat);
}


void Lcd_Init()
{
	GPIO_write(LCD_LED,1);

	GPIO_write(LCD_RST,0);
	GPIO_write(LCD_CS,1);
	GPIO_write(LCD_RS,1);
	GPIO_write(LCD_WR,1);
	GPIO_write(LCD_RD,1);

	GPIO_write(LCD_RST,1);

	SysCtlDelay(100);

	Write_Command_Data(0x0000,0x0001);
	Write_Command_Data(0x0003,0xA8A4);
	Write_Command_Data(0x000C,0x0000);
	Write_Command_Data(0x000D,0x080C);
	Write_Command_Data(0x000E,0x2B00);
	Write_Command_Data(0x001E,0x00B7);
	Write_Command_Data(0x0001,0x2B3F);
	Write_Command_Data(0x0002,0x0600);
	Write_Command_Data(0x0010,0x0000);
	Write_Command_Data(0x0011,0x6070);
	Write_Command_Data(0x0005,0x0000);
	Write_Command_Data(0x0006,0x0000);
	Write_Command_Data(0x0016,0xEF1C);
	Write_Command_Data(0x0017,0x0003);
	Write_Command_Data(0x0007,0x0233);
	Write_Command_Data(0x000B,0x0000);
	Write_Command_Data(0x000F,0x0000);
	Write_Command_Data(0x0041,0x0000);
	Write_Command_Data(0x0042,0x0000);
	Write_Command_Data(0x0048,0x0000);
	Write_Command_Data(0x0049,0x013F);
	Write_Command_Data(0x004A,0x0000);
	Write_Command_Data(0x004B,0x0000);
	Write_Command_Data(0x0044,0xEF00);
	Write_Command_Data(0x0045,0x0000);
	Write_Command_Data(0x0046,0x013F);
	Write_Command_Data(0x0030,0x0707);
	Write_Command_Data(0x0031,0x0204);
	Write_Command_Data(0x0032,0x0204);
	Write_Command_Data(0x0033,0x0502);
	Write_Command_Data(0x0034,0x0507);
	Write_Command_Data(0x0035,0x0204);
	Write_Command_Data(0x0036,0x0204);
	Write_Command_Data(0x0037,0x0502);
	Write_Command_Data(0x003A,0x0302);
	Write_Command_Data(0x003B,0x0302);
	Write_Command_Data(0x0023,0x0000);
	Write_Command_Data(0x0024,0x0000);
	Write_Command_Data(0x0025,0x8000);
	Write_Command_Data(0x004f,0x0000);
	Write_Command_Data(0x004e,0x0000);
	Write_Command(0x0022);
}

void SetXY(unsigned int x0,unsigned int y0,unsigned int x1,unsigned int y1)
{
	Write_Command_Data(0x0044,(x1<<8)+x0);
	Write_Command_Data(0x0045,y0);
	Write_Command_Data(0x0046,y1);
	Write_Command_Data(0x004e,x0);
	Write_Command_Data(0x004f,y0);
	Write_Command (0x0022);//LCD_WriteCMD(GRAMWR);
}

void Pant(uint16_t color)
{
	int i,j;
	SetXY(0,0,239,319);

	for(i=0;i<320;i++)
	{
		for (j=0;j<240;j++)
		{
			Write_Data(color);
		}
        Task_sleep(1);
	}
}

void Touch_Init(void)
{
	GPIO_write(T_CS,  1);
	SysCtlDelay(3);
	GPIO_write(T_CLK, 1);
	SysCtlDelay(3);
	GPIO_write(T_DIN, 1);
	SysCtlDelay(3);
	GPIO_write(T_CLK, 1);
	SysCtlDelay(3);

	GPIO_write(T_CLK, 0);
	SysCtlDelay(3);
}


void Touch_WriteData(uint8_t data)
{
	uint8_t temp;
	uint8_t count;

	temp=data;
	GPIO_write(T_CLK,0);
	SysCtlDelay(3);

	for(count=0; count<8; count++)
	{
		if(temp & 0x80)
			GPIO_write(T_DIN, 1);
		else
			GPIO_write(T_DIN, 0);
		temp = temp << 1;
		SysCtlDelay(3);
		GPIO_write(T_CLK, 0);
		SysCtlDelay(3);
		GPIO_write(T_CLK, 1);
//		SysCtlDelay(3);
	}
}

uint16_t Touch_ReadData()
{
	uint16_t data = 0;
	uint8_t count;

	for(count=0; count<12; count++)
	{
		data <<= 1;
		GPIO_write(T_CLK, 1);
		SysCtlDelay(3);
		if (GPIO_read(T_DOUT))
		{
			data++;
		}
		SysCtlDelay(3);
		GPIO_write(T_CLK, 0);
		SysCtlDelay(3);
	}
	return(data);
}

void Touch_Read(uint16_t *x_out, uint16_t *y_out)
{
	uint32_t tx=0;
	uint32_t ty=0;

	GPIO_write(T_CS,0);
	SysCtlDelay(3);

	int i;
	for (i = 0; i < PREC_TOUCH_CONST; i++)
	{
		Touch_WriteData(0x90);
		GPIO_write(T_CLK,1);
		SysCtlDelay(3);
		GPIO_write(T_CLK,0);
		SysCtlDelay(3);
		ty += Touch_ReadData();

		Touch_WriteData(0xD0);
		GPIO_write(T_CLK,1);
		SysCtlDelay(3);
		GPIO_write(T_CLK,0);
		SysCtlDelay(3);
		tx += Touch_ReadData();

	}

	GPIO_write(T_CS,1);

	int16_t x_temp = ((tx / PREC_TOUCH_CONST) - X_MIN) * X_PX / (X_MAX - X_MIN);
	int16_t y_temp = ((ty / PREC_TOUCH_CONST) - Y_MIN) * Y_PX / (Y_MAX - Y_MIN);

	if (x_temp < 0)
	{
		*x_out = 0;
	}
	else if (x_temp > X_PX)
	{
		*x_out = (uint16_t) X_PX;
	}
	else
	{
		*x_out = x_temp;
	}

	if (y_temp < 0)
	{
		*y_out = 0;
	}
	else if (y_temp > Y_PX)
	{
		*y_out = (uint16_t) Y_PX;
	}
	else
	{
		*y_out = y_temp;
	}
}

void touchCallback(unsigned int index)
{
	GPIO_disableInt(T_IRQ);

	Touch_Read(&x, &y);
    System_printf("x: %d, y: %d\r", x, y);

    Clock_start(TouchClkHandle);

}

void ADCprocess(uint32_t ch)
{
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
}

void clk0Fxn(UArg arg0)
{
    GPIO_clearInt(T_IRQ);
    GPIO_enableInt(T_IRQ);
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
        //GPIO_toggle(Board_LED0);

//        ADCProcessorTrigger(ADC0_BASE, 0);
//        while(!ADCIntStatus(ADC0_BASE, 0, false));
//        uint32_t result;
//        ADCSequenceDataGet(ADC0_BASE, 0, &result);
//        System_printf("%04lu\r", result);
        System_flush();
    }
}

void screenDemo(UArg arg0, UArg arg1)
{
    while (1) {
        Task_sleep(2000);
    	Pant(0xF800);
        Task_sleep(2000);
    	Pant(0x001F);
        Task_sleep(2000);
    	Pant(0xFFE0);
    }
}


#define TCPPACKETSIZE 256
#define NUMTCPWORKERS 3

/*
 *  ======== tcpWorker ========
 *  Task to handle TCP connection. Can be multiple Tasks running
 *  this function.
 */
Void tcpWorker(UArg arg0, UArg arg1)
{
    int  clientfd = (int)arg0;
    int  bytesRcvd;
    int  bytesSent;
    char buffer[TCPPACKETSIZE];

    System_printf("tcpWorker: start clientfd = 0x%x\n", clientfd);

    while ((bytesRcvd = recv(clientfd, buffer, TCPPACKETSIZE, 0)) > 0) {
        bytesSent = send(clientfd, buffer, bytesRcvd, 0);
        if (bytesSent < 0 || bytesSent != bytesRcvd) {
            System_printf("Error: send failed.\n");
            break;
        }
    }
    System_printf("tcpWorker stop clientfd = 0x%x\n", clientfd);

    close(clientfd);
}

/*
 *  ======== tcpHandler ========
 *  Creates new Task to handle new TCP connections.
 */
Void tcpHandler(UArg arg0, UArg arg1)
{
    int                status;
    int                clientfd;
    int                server;
    struct sockaddr_in localAddr;
    struct sockaddr_in clientAddr;
    int                optval;
    int                optlen = sizeof(optval);
    socklen_t          addrlen = sizeof(clientAddr);
    Task_Handle        taskHandle;
    Task_Params        taskParams;
    Error_Block        eb;

    server = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (server == -1) {
        System_printf("Error: socket not created.\n");
        goto shutdown;
    }


    memset(&localAddr, 0, sizeof(localAddr));
    localAddr.sin_family = AF_INET;
    localAddr.sin_addr.s_addr = htonl(INADDR_ANY);
    localAddr.sin_port = htons(arg0);

    status = bind(server, (struct sockaddr *)&localAddr, sizeof(localAddr));
    if (status == -1) {
        System_printf("Error: bind failed.\n");
        goto shutdown;
    }

    status = listen(server, NUMTCPWORKERS);
    if (status == -1) {
        System_printf("Error: listen failed.\n");
        goto shutdown;
    }

    optval = 1;
    if (setsockopt(server, SOL_SOCKET, SO_KEEPALIVE, &optval, optlen) < 0) {
        System_printf("Error: setsockopt failed\n");
        goto shutdown;
    }

    while ((clientfd =
            accept(server, (struct sockaddr *)&clientAddr, &addrlen)) != -1) {

        System_printf("tcpHandler: Creating thread clientfd = %d\n", clientfd);

        /* Init the Error_Block */
        Error_init(&eb);

        /* Initialize the defaults and set the parameters. */
        Task_Params_init(&taskParams);
        taskParams.arg0 = (UArg)clientfd;
        taskParams.stackSize = 1280;
        taskHandle = Task_create((Task_FuncPtr)tcpWorker, &taskParams, &eb);
        if (taskHandle == NULL) {
            System_printf("Error: Failed to create new Task\n");
            close(clientfd);
        }

        /* addrlen is a value-result param, must reset for next accept call */
        addrlen = sizeof(clientAddr);
    }

    System_printf("Error: accept failed.\n");

shutdown:
    if (server > 0) {
        close(server);
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

//    ADCClockConfigSet(ADC0_BASE, ADC_CLOCK_SRC_PLL | ADC_CLOCK_RATE_EIGHTH, 1);

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
    uDMAChannelControlSet(UDMA_CHANNEL_ADC0 | UDMA_PRI_SELECT, UDMA_SIZE_16 | UDMA_SRC_INC_NONE | UDMA_DST_INC_16 | UDMA_ARB_8);
    uDMAChannelControlSet(UDMA_CHANNEL_ADC0 | UDMA_ALT_SELECT, UDMA_SIZE_16 | UDMA_SRC_INC_NONE | UDMA_DST_INC_16 | UDMA_ARB_8);


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

    SysCtlClockFreqSet((SYSCTL_XTAL_25MHZ |
                    SYSCTL_OSC_MAIN | SYSCTL_USE_PLL |
                    SYSCTL_CFG_VCO_480), 120000000);

    Task_Params taskParams;
    /* Call board init functions */
    Board_initGeneral();
    Board_initGPIO();
    Board_initEMAC();
    // Board_initI2C();
    // Board_initSDSPI();
    // Board_initSPI();
    // Board_initUART();
    // Board_initUSB(Board_USBDEVICE);
    // Board_initUSBMSCHFatFs();
    // Board_initWatchdog();
    // Board_initWiFi();

    Clock_Params clkParams;
    Clock_Params_init(&clkParams);
    clkParams.period = 0;
    clkParams.startFlag = FALSE;
    Clock_construct(&TouchClkStruct, (Clock_FuncPtr)clk0Fxn, 200, &clkParams);
    TouchClkHandle = Clock_handle(&TouchClkStruct);

    Lcd_Init();
    Touch_Init();

    ADC_Init();

    GPIO_setCallback(T_IRQ, touchCallback);
    GPIO_enableInt(T_IRQ);

    /* Construct heartBeat Task  thread */
    Task_Params_init(&taskParams);
    taskParams.arg0 = 1000;
    taskParams.stackSize = TASKSTACKSIZE;
    taskParams.stack = &task0Stack;
    Task_construct(&task0Struct, (Task_FuncPtr)heartBeatFxn, &taskParams, NULL);

    taskParams.stack = &task1Stack;
//    Task_construct(&task1Struct, (Task_FuncPtr)screenDemo, &taskParams, NULL);

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
