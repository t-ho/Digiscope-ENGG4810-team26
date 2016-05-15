/*
 * XPT2046_driver.c
 *
 *  Created on: 27 Apr 2016
 *      Author: Ryan
 */

#include <ti/sysbios/knl/Clock.h>

#include <ti/drivers/GPIO.h>

#include "Board.h"

#include <stdbool.h>
#include <stdint.h>
#include "inc/hw_gpio.h"
#include "inc/hw_ints.h"
#include "inc/hw_types.h"
#include "inc/hw_memmap.h"
#include "driverlib/gpio.h"
#include "driverlib/interrupt.h"
#include "driverlib/sysctl.h"
#include "driverlib/rom.h"
#include "driverlib/rom_map.h"
#include "utils/uartstdio.h"

#include "common.h"

#define T_CLK_B		GPIO_PORTM_BASE
#define T_CS_B		GPIO_PORTQ_BASE
#define T_DIN_B		GPIO_PORTP_BASE

#define T_BUSY_B	GPIO_PORTQ_BASE
#define T_IRQ_B		GPIO_PORTN_BASE
#define T_DOUT_B	GPIO_PORTQ_BASE

#define T_CLK_P		GPIO_PIN_6
#define T_CS_P		GPIO_PIN_1
#define T_DIN_P		GPIO_PIN_3

#define T_BUSY_P	GPIO_PIN_3
#define T_IRQ_P		GPIO_PIN_4
#define T_DOUT_P	GPIO_PIN_2

/* Touch screen calibration */
#define X_MIN 200
#define X_MAX 1700
#define X_PX 320.0f
#define Y_MIN 200
#define Y_MAX 1700
#define Y_PX 240.0f

#define PREC_TOUCH_CONST 10

#define DEBOUNCE_PERIOD 200

static void TouchReenable(void);
void TouchCallback(unsigned int);

Clock_Struct TouchClkStruct;
Clock_Handle TouchClkHandle;

void
XPT2046_Init(void)
{
	MAP_GPIOPinTypeGPIOOutput(T_CLK_B, T_CLK_P);
	MAP_GPIOPinTypeGPIOOutput(T_CS_B, T_CS_P);
	MAP_GPIOPinTypeGPIOOutput(T_DIN_B, T_DIN_P);

	MAP_GPIOPinTypeGPIOInput(T_BUSY_B, T_BUSY_P);
	MAP_GPIOPinTypeGPIOInput(T_IRQ_B, T_IRQ_P);
	MAP_GPIOPinTypeGPIOInput(T_DOUT_B, T_DOUT_P);

	MAP_GPIOPadConfigSet(T_CLK_B, T_CLK_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	MAP_GPIOPadConfigSet(T_CS_B, T_CS_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	MAP_GPIOPadConfigSet(T_DIN_B, T_DIN_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);

	MAP_GPIOPadConfigSet(T_BUSY_B, T_BUSY_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD_WPU);
	MAP_GPIOPadConfigSet(T_IRQ_B, T_IRQ_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD_WPU);
	MAP_GPIOPadConfigSet(T_DOUT_B, T_DOUT_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD_WPU);

    Clock_Params clkParams;
    Clock_Params_init(&clkParams);
    clkParams.period = 0;
    clkParams.startFlag = FALSE;
    Clock_construct(&TouchClkStruct, (Clock_FuncPtr)TouchReenable, DEBOUNCE_PERIOD, &clkParams);
    TouchClkHandle = Clock_handle(&TouchClkStruct);

	MAP_GPIOIntTypeSet(T_IRQ_B, T_IRQ_P, GPIO_FALLING_EDGE);

	GPIOIntEnable(T_IRQ_B, T_IRQ_P);

	MAP_GPIOPinWrite(T_CS_B, T_CS_P, T_CS_P);
	MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
	MAP_GPIOPinWrite(T_DIN_B, T_DIN_P, T_DIN_P);

	MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
}

static void
Touch_WriteData(uint8_t data)
{
	uint8_t temp;
	uint8_t count;

	temp=data;
	MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, 0);

	for(count=0; count<8; count++)
	{
		if(temp & 0x80)
			MAP_GPIOPinWrite(T_DIN_B, T_DIN_P, T_DIN_P);
		else
			MAP_GPIOPinWrite(T_DIN_B, T_DIN_P, 0);
		temp = temp << 1;
		MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
		MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
	}
}

static uint16_t
Touch_ReadData()
{
	uint16_t data = 0;
	uint8_t count;

	for(count=0; count<12; count++)
	{
		data <<= 1;
		MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
		if (GPIOPinRead(T_DOUT_B, T_DOUT_P))
		{
			data++;
		}
		MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
	}
	return(data);
}

static void
Touch_Read(uint32_t *x_out, uint32_t *y_out)
{
	uint32_t tx=0;
	uint32_t ty=0;

	MAP_GPIOPinWrite(T_CS_B, T_CS_P, 0);

	int i;
	for (i = 0; i < PREC_TOUCH_CONST; i++)
	{
		Touch_WriteData(0x90);
		MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
		MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
		tx += Touch_ReadData();

		Touch_WriteData(0xD0);
		MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
		MAP_GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
		ty += Touch_ReadData();

	}

	MAP_GPIOPinWrite(T_CS_B, T_CS_P, T_CS_P);

	int16_t x_temp = ((tx / PREC_TOUCH_CONST) - X_MIN) * X_PX / (X_MAX - X_MIN);
	int16_t y_temp = ((ty / PREC_TOUCH_CONST) - Y_MIN) * Y_PX / (Y_MAX - Y_MIN);

	if (x_temp < 0)
	{
		*x_out = 0;
	}
	else if (x_temp > X_PX)
	{
		*x_out = (uint32_t) X_PX;
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
		*y_out = (uint32_t) Y_PX;
	}
	else
	{
		*y_out = y_temp;
	}
}

static GraphicsMessage msg;

static void
TouchReenable(void)
{
	msg.type = GM_PTR_UP;
	Mailbox_post(GraphicsMailbox, &msg, 0);
	MAP_GPIOIntClear(T_IRQ_B, T_IRQ_P);
	MAP_GPIOIntEnable(T_IRQ_B, T_IRQ_P);
}

void
TouchCallback(unsigned int index)
{
	MAP_GPIOIntClear(T_IRQ_B, T_IRQ_P);
	MAP_GPIOIntDisable(T_IRQ_B, T_IRQ_P);

	msg.type = GM_PTR_DOWN;
	Touch_Read(&(msg.data[0]), &(msg.data[1]));

	Mailbox_post(GraphicsMailbox, &msg, 0);

	Clock_start(TouchClkHandle);
}
