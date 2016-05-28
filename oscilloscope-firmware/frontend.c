/*
 * frontend.c
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#include <xdc/runtime/System.h>

#include "net.h"
#include "command.h"
#include "ui/graphics_thread.h"
#include "ui/range_menu.h"
#include "adc.h"
#include "trigger.h"

#define CHANNEL_A 0
#define CHANNEL_B 1
#define COUPLING_DC 0
#define COUPLING_AC 1

#define HDIV_MIN 1
#define HDIV_MAX 1000000
#define VDIV_MIN 20000
#define VDIV_MAX 2000000

#define PREFERRED_SAMPLES_PER_DIV 100

static uint32_t hor_div = 500;
static uint32_t vert_divs[] = {500, 500};

static uint8_t current_coupling[] = { COUPLING_DC, COUPLING_DC };

uint32_t
FrontEndGetHorDiv(void)
{
	return hor_div;
}

void
FrontEndSetHorDiv(uint32_t us)
{
	static char line1[16], line2[16];

	if (us > HDIV_MAX)
	{
		hor_div = HDIV_MAX;
	}
	else if (us < HDIV_MIN)
	{
		hor_div = HDIV_MIN;
	}
	else
	{
		hor_div = us;
	}

	if (hor_div / PREFERRED_SAMPLES_PER_DIV < 1000)
	{
		ADCSetFreq(1000);
	}
	else if (hor_div / PREFERRED_SAMPLES_PER_DIV < 2000)
	{
		ADCSetFreq(500);
	}
	else if (hor_div / PREFERRED_SAMPLES_PER_DIV < 5000)
	{
		ADCSetFreq(200);
	}
	else
	{
		ADCSetFreq(100);
	}

	int samplediv = 2;
	while (ADCGetPeriod() * samplediv < hor_div / PREFERRED_SAMPLES_PER_DIV && samplediv <= SAMPLE_DIVISOR_MAX)
	{
		samplediv <<= 1;
	}

	TriggerSetSampleDivisor(samplediv >> 1);

	System_printf("Sample period: %d us\n", ADCGetPeriod() * TriggerGetSampleDivisor());

	Command cmd;
	cmd.type = COMMAND_HORIZONTAL_RANGE;
	cmd.args[0] = hor_div;
	cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
	NetSend(&cmd, 0);

	SI_Micro_Print(line1, line2, hor_div, "s/div");

	RangeHorSetText(line1, line2);
}

uint32_t
FrontEndGetVerDiv(uint32_t channel)
{
	if (channel < 2)
	{
		return vert_divs[channel];
	}
	else
	{
		return 0;
	}
}

void
FrontEndSetVerDiv(uint32_t channel, uint32_t uV)
{
	static char line1[16], line2[16];

	if (channel < 2)
	{
		if (uV > VDIV_MAX)
		{
			vert_divs[channel] = VDIV_MAX;
		}
		else if (uV < VDIV_MIN)
		{
			vert_divs[channel] = VDIV_MIN;
		}
		else
		{
			vert_divs[channel] = uV;
		}

		Command cmd;
		cmd.type = channel ? COMMAND_VERTICAL_RANGE_B : COMMAND_VERTICAL_RANGE_A;
		cmd.args[0] = vert_divs[channel];
		cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
		NetSend(&cmd, 0);

		SI_Micro_Print(line1, line2, vert_divs[channel], "V/div");

		RangeVerSetText(channel, line1, line2);
	}
}

uint32_t
FrontEndGetCoupling(uint32_t channel)
{
	if (channel < 2)
	{
		return current_coupling[channel];
	}
	else
	{
		return 9;
	}
}

void
FrontEndSetCoupling(uint32_t channel, uint32_t coupling)
{
	if (channel < 2)
	{
		current_coupling[channel] = coupling;

		Command cmd;
		cmd.type = channel ? COMMAND_COUPLING_B : COMMAND_COUPLING_A;
		cmd.args[0] = coupling;
		cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
		NetSend(&cmd, 0);

		RangeCouplingSetText(channel, current_coupling[channel] ? "AC" : "DC");
	}
}
