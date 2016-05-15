/*
 * XPT2046_driver.c
 *
 *  Created on: 27 Apr 2016
 *      Author: Ryan
 */

#include <stdbool.h>
#include <stdint.h>
#include "inc/hw_gpio.h"
#include "inc/hw_ints.h"
#include "inc/hw_types.h"
#include "inc/hw_memmap.h"
#include "driverlib/gpio.h"
#include "driverlib/interrupt.h"
#include "driverlib/sysctl.h"
#include "grlib/grlib.h"
#include "grlib/widget.h"

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
#define X_MAX 1850
#define X_PX 320.0f
#define Y_MIN 200
#define Y_MAX 1900
#define Y_PX 240.0f

#define PREC_TOUCH_CONST 10

static int32_t (*g_pfnTSHandler)(uint32_t message, int32_t x, int32_t y);

void
XPT2046_SetCallback(int32_t (*pfnCallback)(uint32_t message, int32_t x, int32_t y))
{
    // Save the pointer to the callback function.
    g_pfnTSHandler = pfnCallback;
}

void
XPT2046_Init(void)
{
	uint32_t touch_bs[] =
	{
		SYSCTL_PERIPH_GPIOM,
		SYSCTL_PERIPH_GPION,
		SYSCTL_PERIPH_GPIOP,
		SYSCTL_PERIPH_GPIOQ
	};

	int i;
	for (i = 0; i < sizeof(touch_bs) / sizeof(uint32_t); i++)
	{
		SysCtlPeripheralEnable(touch_bs[i]);
		SysCtlGPIOAHBEnable(touch_bs[i]);
	}

	GPIOPinTypeGPIOOutput(T_CLK_B, T_CLK_P);
	GPIOPinTypeGPIOOutput(T_CS_B, T_CS_P);
	GPIOPinTypeGPIOOutput(T_DIN_B, T_DIN_P);

	GPIOPinTypeGPIOInput(T_BUSY_B, T_BUSY_P);
	GPIOPinTypeGPIOInput(T_IRQ_B, T_IRQ_P);
	GPIOPinTypeGPIOInput(T_DOUT_B, T_DOUT_P);

	GPIOPadConfigSet(T_CLK_B, T_CLK_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	GPIOPadConfigSet(T_CS_B, T_CS_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	GPIOPadConfigSet(T_DIN_B, T_DIN_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);

	GPIOPadConfigSet(T_BUSY_B, T_BUSY_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD_WPU);
	GPIOPadConfigSet(T_IRQ_B, T_IRQ_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD_WPU);
	GPIOPadConfigSet(T_DOUT_B, T_DOUT_P, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD_WPU);

	GPIOIntTypeSet(T_IRQ_B, T_IRQ_P, GPIO_FALLING_EDGE);
	GPIOIntEnable(T_IRQ_B, T_IRQ_P);
	IntEnable(INT_GPION);

	GPIOPinWrite(T_CS_B, T_CS_P, T_CS_P);
	SysCtlDelay(3);
	GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
	SysCtlDelay(3);
	GPIOPinWrite(T_DIN_B, T_DIN_P, T_DIN_P);
	SysCtlDelay(3);

	GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
	SysCtlDelay(3);
}


static void
Touch_WriteData(uint8_t data)
{
	uint8_t temp;
	uint8_t count;

	temp=data;
	GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
	SysCtlDelay(3);

	for(count=0; count<8; count++)
	{
		if(temp & 0x80)
			GPIOPinWrite(T_DIN_B, T_DIN_P, T_DIN_P);
		else
			GPIOPinWrite(T_DIN_B, T_DIN_P, 0);
		temp = temp << 1;
		SysCtlDelay(3);
		GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
		SysCtlDelay(3);
		GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
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
		GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
		SysCtlDelay(3);
		if (GPIOPinRead(T_DOUT_B, T_DOUT_P))
		{
			data++;
		}
		SysCtlDelay(3);
		GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
		SysCtlDelay(3);
	}
	return(data);
}
static void
Touch_Read(uint16_t *x_out, uint16_t *y_out)
{
	uint32_t tx=0;
	uint32_t ty=0;

	GPIOPinWrite(T_CS_B, T_CS_P, 0);
	SysCtlDelay(3);

	int i;
	for (i = 0; i < PREC_TOUCH_CONST; i++)
	{
		Touch_WriteData(0x90);
		GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
		SysCtlDelay(3);
		GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
		SysCtlDelay(3);
		ty += Touch_ReadData();

		Touch_WriteData(0xD0);
		GPIOPinWrite(T_CLK_B, T_CLK_P, T_CLK_P);
		SysCtlDelay(3);
		GPIOPinWrite(T_CLK_B, T_CLK_P, 0);
		SysCtlDelay(3);
		tx += Touch_ReadData();

	}

	GPIOPinWrite(T_CS_B, T_CS_P, T_CS_P);

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

void
TouchScreenIntHandler(void)
{
	static bool ignore = false;
	GPIOIntClear(T_IRQ_B, T_IRQ_P);

	if (ignore)
	{
		ignore = false;
	}
	else
	{
		uint16_t x=0;
		uint16_t y=0;

		Touch_Read(&x, &y);

		if (g_pfnTSHandler)
		{
			g_pfnTSHandler(WIDGET_MSG_PTR_DOWN, x, y);
			g_pfnTSHandler(WIDGET_MSG_PTR_UP, x, y);
		}

		ignore = true;
	}

}
