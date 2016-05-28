/*
 * potentiometer.c
 *
 *  Created on: 28 May 2016
 *      Author: Ryan
 */

#include <stdbool.h>

#include <ti/drivers/GPIO.h>

#include "Board.h"

#include "driverlib/gpio.h"
#include "driverlib/rom.h"
#include "driverlib/rom_map.h"

#include "potentiometer.h"

#define POTPINWRITE_0(pin) MAP_GPIOPinWrite(pot->ports[POT_PIN_##pin], pot->pins[POT_PIN_##pin], 0);
#define POTPINWRITE_1(pin) MAP_GPIOPinWrite(pot->ports[POT_PIN_##pin], pot->pins[POT_PIN_##pin], pot->pins[POT_PIN_##pin]);

static void
PotentiometerWrite(Potentiometer *pot, uint32_t val)
{
	POTPINWRITE_0(CE);

	int i;
	for (i = 0; i < 24; i++)
	{
		POTPINWRITE_0(CLK);

		if (val & (1 << (23 - i)))
		{
			POTPINWRITE_1(MOSI);
		}
		else
		{
			POTPINWRITE_0(MOSI);
		}

		POTPINWRITE_1(CLK);
	}

	POTPINWRITE_0(CLK);
	POTPINWRITE_1(CE);
}

void
PotentiometerSet(Potentiometer *pot, uint16_t val)
{
	PotentiometerWrite(pot, 0xB00000 | (val & 0x3FF));
}

void
Potentiometer_Init(Potentiometer *pot)
{
	MAP_GPIOPinTypeGPIOOutput(pot->ports[POT_PIN_MOSI], pot->pins[POT_PIN_MOSI]);
	MAP_GPIOPinTypeGPIOOutput(pot->ports[POT_PIN_CE], pot->pins[POT_PIN_CE]);
	MAP_GPIOPinTypeGPIOOutput(pot->ports[POT_PIN_CLK], pot->pins[POT_PIN_CLK]);
	MAP_GPIOPinTypeGPIOInput(pot->ports[POT_PIN_MISO], pot->pins[POT_PIN_MISO]);

	MAP_GPIOPadConfigSet(pot->ports[POT_PIN_CE], pot->pins[POT_PIN_CE], GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	MAP_GPIOPadConfigSet(pot->ports[POT_PIN_MOSI], pot->pins[POT_PIN_MOSI], GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	MAP_GPIOPadConfigSet(pot->ports[POT_PIN_CLK], pot->pins[POT_PIN_CLK], GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	MAP_GPIOPadConfigSet(pot->ports[POT_PIN_MISO], pot->pins[POT_PIN_MISO], GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD_WPU);

	POTPINWRITE_1(CE);
	POTPINWRITE_0(CLK);
	POTPINWRITE_0(MOSI);
}
