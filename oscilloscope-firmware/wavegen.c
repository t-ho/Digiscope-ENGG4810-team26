/*
 * wavegen.c
 *
 *  Created on: 19 May 2016
 *      Author: Ryan
 */

#include <stdint.h>
#include <stdbool.h>
#include <string.h>

#include "driverlib/rom.h"
#include "driverlib/rom_map.h"
#include "driverlib/gpio.h"
#include "driverlib/timer.h"
#include "driverlib/sysctl.h"

#include "inc/hw_memmap.h"
#include "inc/hw_timer.h"
#include "inc/hw_gpio.h"
#include "inc/hw_types.h"

#include <xdc/std.h>
#include <xdc/runtime/System.h>
#include <xdc/runtime/Error.h>

#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/family/arm/m3/Hwi.h>
#include <ti/sysbios/family/arm/lm4/Timer.h>

#include "wavegen.h"

static Timer_Handle th;

/* MATLAB Code:
 * 		transpose(reshape(round((cos(0:2*pi/256:2*pi*255/256) * 127.5) + 127.5), [8 32]))
 */
static uint8_t sine[] =
{
   255,  255,  255,  255,  254,  254,  254,  253,
   253,  252,  251,  250,  250,  249,  248,  246,
   245,  244,  243,  241,  240,  238,  237,  235,
   234,  232,  230,  228,  226,  224,  222,  220,
   218,  215,  213,  211,  208,  206,  203,  201,
   198,  196,  193,  190,  188,  185,  182,  179,
   176,  173,  170,  167,  165,  162,  158,  155,
   152,  149,  146,  143,  140,  137,  134,  131,
   128,  124,  121,  118,  115,  112,  109,  106,
   103,  100,   97,   93,   90,   88,   85,   82,
	79,   76,   73,   70,   67,   65,   62,   59,
	57,   54,   52,   49,   47,   44,   42,   40,
	37,   35,   33,   31,   29,   27,   25,   23,
	21,   20,   18,   17,   15,   14,   12,   11,
	10,    9,    7,    6,    5,    5,    4,    3,
	 2,    2,    1,    1,    1,    0,    0,    0,
	 0,    0,    0,    0,    1,    1,    1,    2,
	 2,    3,    4,    5,    5,    6,    7,    9,
	10,   11,   12,   14,   15,   17,   18,   20,
	21,   23,   25,   27,   29,   31,   33,   35,
	37,   40,   42,   44,   47,   49,   52,   54,
	57,   59,   62,   65,   67,   70,   73,   76,
	79,   82,   85,   88,   90,   93,   97,  100,
   103,  106,  109,  112,  115,  118,  121,  124,
   127,  131,  134,  137,  140,  143,  146,  149,
   152,  155,  158,  162,  165,  167,  170,  173,
   176,  179,  182,  185,  188,  190,  193,  196,
   198,  201,  203,  206,  208,  211,  213,  215,
   218,  220,  222,  224,  226,  228,  230,  232,
   234,  235,  237,  238,  240,  241,  243,  244,
   245,  246,  248,  249,  250,  250,  251,  252,
   253,  253,  254,  254,  254,  255,  255,  255,
};

static uint8_t *signal_lookup = sine;
static uint32_t frequency = 1000000;
static bool enabled;

void
WaveGenSetFreq(uint32_t freq)
{
	static char freq_display[8] = "500 Hz";

	frequency = freq;

	Hwi_disable();
	Timer_setPeriod(th, (120000000 / 255) / freq);
	if (enabled)
	{
		Timer_start(th);
	}
	Hwi_enable();

	if (frequency < 1000)
	{
		snprintf(freq_display, sizeof(freq_display), "%d Hz", frequency);
	}
	else
	{
		snprintf(freq_display, sizeof(freq_display), "%d kHz", frequency/1000);
	}
	WaveGenFreqSetText(freq_display);
}

uint32_t
WaveGenGetFreq(void)
{
	return frequency;
}

void
WaveGenEnableSet(bool on)
{
	Hwi_disable();
	if (on)
	{
		enabled = true;
		Timer_start(th);
	}
	else
	{
		enabled = false;
		Timer_stop(th);
		HWREG(GPIO_PORTA_AHB_BASE + GPIO_O_DATA + (0xFF << 2)) = 0;
	}
	Hwi_enable();

	WaveGenOnOffSetText(enabled?"On":"Off");
}

bool
WaveGenEnableGet(void)
{
	return enabled;
}

static void
WaveGenISR(unsigned int arg)
{
	volatile static uint8_t pos;
	volatile static uint8_t inc = 1;
    HWREG(TIMER2_BASE + TIMER_O_ICR) = TIMER_TIMA_TIMEOUT;
	HWREG(GPIO_PORTA_AHB_BASE + GPIO_O_DATA + (0xFF << 2)) = signal_lookup[pos];
	pos += inc;
}

void
WaveGen_Init(void)
{
	MAP_GPIOPinTypeGPIOOutput(GPIO_PORTA_BASE, 0xFF);
	MAP_GPIOPadConfigSet(GPIO_PORTA_BASE, 0xFF, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);

	MAP_SysCtlPeripheralEnable(TIMER2_BASE);
	while(!MAP_SysCtlPeripheralReady(TIMER2_BASE));

    Hwi_Params hwiParams;
	Timer_Params TimerParams;

    Error_Block eb;
    Error_init(&eb);

	Timer_Params_init(&TimerParams);
	TimerParams.period = 12000;
	TimerParams.periodType = Timer_PeriodType_COUNTS;
    th = Timer_create(2, NULL, &TimerParams, &eb);

    Hwi_Params_init(&hwiParams);
    hwiParams.useDispatcher = false;
	hwiParams.priority = 0;       //zero latency interrupt, priority 0
	Hwi_create(39, WaveGenISR, &hwiParams, &eb);

	TimerIntEnable(TIMER2_BASE, TIMER_TIMA_TIMEOUT);

	WaveGenEnableSet(false);
//
}
