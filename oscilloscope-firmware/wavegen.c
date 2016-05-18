/*
 * wavegen.c
 *
 *  Created on: 19 May 2016
 *      Author: Ryan
 */

#include <stdint.h>
#include <stdbool.h>

#include "driverlib/rom.h"
#include "driverlib/rom_map.h"
#include "driverlib/gpio.h"
#include "driverlib/timer.h"

#include "inc/hw_memmap.h"
#include "inc/hw_gpio.h"
#include "inc/hw_types.h"

#include "wavegen.h"

#define WAVEGEN_TIMER TIMER2_BASE

void
WaveGenSetFreq(uint32_t freq)
{

}

void
WaveGenEnableSet(uint8_t on)
{
	if (on)
	{
		MAP_TimerEnable(WAVEGEN_TIMER, TIMER_BOTH);
	}
	else
	{
		MAP_TimerDisable(WAVEGEN_TIMER, TIMER_BOTH);
	}
}

void
WaveGen_Init(void)
{
	MAP_GPIOPinTypeGPIOOutput(GPIO_PORTA_BASE, 0xFF);
	MAP_GPIOPadConfigSet(GPIO_PORTA_BASE, 0xFF, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
}

void
wavegenCallback(void)
{
	static uint8_t val;
	HWREG(GPIO_PORTA_AHB_BASE + GPIO_O_DATA + (0xFF << 2)) = val;
	//MAP_GPIOPinWrite(GPIO_PORTA_AHB_BASE, 0xFF, val);
	val += 1;
}
