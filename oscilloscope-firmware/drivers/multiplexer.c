/*
 * multiplexer.c
 *
 *  Created on: 29 May 2016
 *      Author: Ryan
 */

#include <stdbool.h>

#include <ti/drivers/GPIO.h>

#include "Board.h"

#include "driverlib/gpio.h"
#include "driverlib/rom.h"
#include "driverlib/rom_map.h"

#include "multiplexer.h"

#define MULTPINWRITE_0(pin) MAP_GPIOPinWrite(mult->ports[pin], mult->pins[pin], 0);
#define MULTINWRITE_1(pin) MAP_GPIOPinWrite(mult->ports[pin], mult->pins[pin], mult->pins[pin]);

void
MultiplexerSet(Multiplexer *mult, uint8_t val)
{
	int i;
	for (i = 0; i < 3; i++)
	{
		if (val & (1 << i))
		{
			MULTPINWRITE_1(i);
		}
		else
		{
			MULTPINWRITE_0(i);
		}
	}
}

void
Multiplexer_Init(Multiplexer *mult)
{
	MAP_GPIOPinTypeGPIOOutput(mult->ports[0], mult->pins[0]);
	MAP_GPIOPinTypeGPIOOutput(mult->ports[1], mult->pins[1]);
	MAP_GPIOPinTypeGPIOOutput(mult->ports[2], mult->pins[2]);

	MAP_GPIOPadConfigSet(mult->ports[0], mult->pins[0], GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	MAP_GPIOPadConfigSet(mult->ports[1], mult->pins[1], GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	MAP_GPIOPadConfigSet(mult->ports[2], mult->pins[2], GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);

	MULTPINWRITE_0(0);
	MULTPINWRITE_0(0);
	MULTPINWRITE_0(0);
}
