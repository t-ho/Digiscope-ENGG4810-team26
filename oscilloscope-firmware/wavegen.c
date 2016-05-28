/*
 * wavegen.c
 *
 *  Created on: 19 May 2016
 *      Author: Ryan
 */

#include <stdint.h>
#include <stdbool.h>
#include <string.h>
#include <stdio.h>

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

#include "command.h"
#include "wavegen.h"
#include "net.h"
#include "ui/graphics_thread.h"
#include "ui/wavegen_menu.h"

#define NOISE_PERIOD 300
#define APPARENT_CLOCK_FREQ 118400000

#define MAX_AMPLITUDE 2000000
#define MIN_AMPLITUDE 100000
#define MAX_OFFSET 2500000

static void WaveGenUpdateShape();

static char* WaveNames[] =
{
	"Sine",
	"Square",
	"Triangle",
	"Ramp",
	"Noise",
};

static Timer_Handle th;

static void WaveGenISR(unsigned int arg);

#pragma location=0x20030000
volatile static int8_t inc = 1;

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

static uint8_t signal_lookup[256];

static uint32_t frequency = 50;
static uint32_t amplitude = MAX_AMPLITUDE;
static int32_t offset = 0;
static bool enabled;
static WaveType shape = SINE;

void
WaveGenSetAmplitude(uint32_t uV)
{
	static char line1[16];
	static char line2[16];

	if (uV > MAX_AMPLITUDE)
	{
		uV = MAX_AMPLITUDE;
	}
	else if (uV < MIN_AMPLITUDE)
	{
		uV = MIN_AMPLITUDE;
	}

	amplitude = uV;

	WaveGenUpdateShape();

	SI_Micro_Print(line1, line2, WaveGenGetAmplitude(), "Vpp");
	WaveGenAmplitudeSetText(line1, line2);

	Command cmd;
	cmd.type = COMMAND_FUNCTION_GEN_VOLTAGE;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = WaveGenGetAmplitude();

	NetSend(&cmd, 0);
}

uint32_t
WaveGenGetAmplitude(void)
{
	return amplitude;
}

void
WaveGenSetFreq(uint32_t freq)
{
	static char line1[16];
	static char line2[16];

	int period;

	if (shape == NOISE)
	{
		inc = 0;
		period = 300;
		snprintf(line1, sizeof(line1), "N/A");
		snprintf(line2, sizeof(line1), "");
	}
	else
	{
		if (freq > 25000)
		{
			frequency = 25000;
		}
		else
		{
			frequency = freq;
		}

		period = (APPARENT_CLOCK_FREQ / 255) / frequency;
		inc = 1;

		while (period < 150)
		{
			inc++;
			period = (inc * APPARENT_CLOCK_FREQ / 255) / (frequency);
		}

		SI_Print(line1, line2, WaveGenGetFreq(), "Hz", " k");
	}

	Hwi_disable();
	Timer_setPeriod(th, period);
	if (enabled)
	{
		Timer_start(th);
	}
	Hwi_enable();

	WaveGenFreqSetText(line1, line2);

	Command cmd;
	cmd.type = COMMAND_FUNCTION_GEN_FREQUENCY;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = WaveGenGetFreq();

	NetSend(&cmd, 0);
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

	Command cmd;
	cmd.type = COMMAND_FUNCTION_GEN_OUT;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = enabled;

	NetSend(&cmd, 0);
}

bool
WaveGenEnableGet(void)
{
	return enabled;
}

/**
 * Update the output buffer. 
 * 
 * Should be called whenever the shape or amplitude are changed.
 */
static void
WaveGenUpdateShape()
{
	int i;

	WaveGenShapeSetText(WaveNames[shape]);

	switch (shape)
	{
	case SINE:
		for (i = 0; i < 256; i++)
		{
			signal_lookup[i] = sine[i];
		}
		WaveGenSetFreq(WaveGenGetFreq());
		break;
	case SQUARE:
		for (i = 0; i < 256; i++)
		{
			signal_lookup[i] = i > 127 ? 255 : 0;
		}
		WaveGenSetFreq(WaveGenGetFreq());
		break;
	case TRIANGLE:
		for (i = 0; i < 128; i++)
		{
			signal_lookup[i] = i * 2;
			signal_lookup[255 - i] = i * 2;
		}
		WaveGenSetFreq(WaveGenGetFreq());
		break;
	case RAMP:
		for (i = 0; i < 256; i++)
		{
			signal_lookup[i] = i;
		}
		WaveGenSetFreq(WaveGenGetFreq());
		break;
	case NOISE:
		WaveGenSetFreq(0);
		break;
	}

	for (i = 0; i < 256; i++)
	{
		int32_t centred = signal_lookup[i];
		centred -= 127;
		centred *= amplitude;
		centred /= MAX_AMPLITUDE;
		centred += 127;
		signal_lookup[i] = centred;
	}
}

void
WaveGenSetShape(WaveType newshape)
{
	shape = newshape;

	WaveGenShapeSetText(WaveNames[shape]);

	WaveGenUpdateShape();

	Command cmd;
	cmd.type = COMMAND_FUNCTION_GEN_WAVE;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = WaveGenGetShape();

	NetSend(&cmd, 0);
}

WaveType
WaveGenGetShape(void)
{
	return shape;
}

void
WaveGenSetOffset(int32_t uV)
{
	static char line1[16];
	static char line2[16];

	if (uV > MAX_OFFSET)
	{
		uV = MAX_OFFSET;
	}
	else if (uV < -MAX_OFFSET)
	{
		uV = -MAX_OFFSET;
	}

	offset = uV;

	SI_Micro_Print(line1, line2, WaveGenGetOffset(), "V");
	WaveGenOffsetSetText(line1, line2);

	Command cmd;
	cmd.type = COMMAND_FUNCTION_GEN_OFFSET;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	cmd.args[0] = WaveGenGetOffset();

	NetSend(&cmd, 0);
}

int32_t
WaveGenGetOffset(void)
{
	return offset;
}

static void
WaveGenISR(unsigned int arg)
{
	volatile static uint8_t pos;
	volatile static uint16_t lfsr = 0xACE1;

    HWREG(TIMER2_BASE + TIMER_O_ICR) = TIMER_TIMA_TIMEOUT;

    if (inc == 0)
    {
        HWREG(TIMER2_BASE + TIMER_O_ICR) = TIMER_TIMA_TIMEOUT;

        unsigned lsb = lfsr & 1;   /* Get LSB (i.e., the output bit). */
        lfsr >>= 1;                /* Shift register */
        lfsr ^= (-lsb) & 0xB400;  /* If the output bit is 1, apply toggle mask.
                                    * The value has 1 at bits corresponding
                                    * to taps, 0 elsewhere. */
    	HWREG(GPIO_PORTA_AHB_BASE + GPIO_O_DATA + (0xFF << 2)) = (0xFF & lfsr);
    }
    else
    {
        HWREG(GPIO_PORTA_AHB_BASE + GPIO_O_DATA + (0xFF << 2)) = signal_lookup[pos];
    	pos += inc;
    }
}

void
WaveGen_Init(void)
{
	MAP_GPIOPinTypeGPIOOutput(GPIO_PORTA_BASE, 0xFF);
	MAP_GPIOPadConfigSet(GPIO_PORTA_BASE, 0xFF, GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);

	MAP_SysCtlPeripheralEnable(TIMER2_BASE);
	while(!MAP_SysCtlPeripheralReady(TIMER2_BASE));

	Timer_Params TimerParams;
	Hwi_Params hwiParams;

    Error_Block eb;
    Error_init(&eb);

	Timer_Params_init(&TimerParams);
	TimerParams.period = 1000;
	TimerParams.periodType = Timer_PeriodType_COUNTS;
    th = Timer_create(2, NULL, &TimerParams, &eb);

    Hwi_Params_init(&hwiParams);
    hwiParams.useDispatcher = false;
	hwiParams.priority = 0;       //zero latency interrupt, priority 0
	Hwi_create(39, WaveGenISR, &hwiParams, &eb);

	TimerIntEnable(TIMER2_BASE, TIMER_TIMA_TIMEOUT);

	WaveGenNotify();
}

void
WaveGenNotify(void)
{
	WaveGenSetAmplitude(WaveGenGetAmplitude());
	WaveGenSetFreq(WaveGenGetFreq());
	WaveGenEnableSet(WaveGenEnableGet());
	WaveGenSetShape(WaveGenGetShape());
	WaveGenSetOffset(WaveGenGetOffset());
}
