/*
 * common.c
 *
 *  Created on: 30 Apr 2016
 *      Author: Ryan
 */

#include "common.h"
#include <xdc/runtime/Error.h>

Semaphore_Handle clients_connected_h;

static Semaphore_Struct clients_connected;

Mailbox_Handle GraphicsMailbox;

void
SI_Micro_Print(char* line1, char* line2, int32_t val, char* suffix)
{
	char prefs[] = "um ";
	int mag = 0;

	while (val >= 1000)
	{
		val /= 1000;
		mag++;
	}

	sprintf(line1, "%lu", val);
	sprintf(line2, "%c%s", prefs[mag], suffix);
}

uint32_t
Standard_Step(uint32_t val, int8_t dir)
{
	// 0 is invalid input
	if (val == 0) return 0;

	uint8_t mag = 0;

	while (val > 10)
	{
		val /= 10;
		mag++;
	}

	if (dir < 0)
	{
		if (val > 5)
		{
			val = 5;
		}
		else if (val > 2)
		{
			val = 2;
		}
		else if (val > 1)
		{
			val = 1;
		}
		else if (val == 1 && mag > 0)
		{
			val = 5;
			mag--;
		}
	}
	else
	{
		if (val < 2)
		{
			val = 2;
		}
		else if (val < 5)
		{
			val = 5;
		}
		else if (val < 10)
		{
			val = 10;
		}
		else
		{
			val = 2;
			mag++;
		}
	}

	while (mag > 0)
	{
		mag--;
		val *= 10;
	}

	return val;
}


void
Init_Semaphores(void)
{
	Mailbox_Params mbparams;
	Mailbox_Params_init(&mbparams);
	static Error_Block eb;

	GraphicsMailbox = Mailbox_create(12,10,&mbparams,&eb);

    // Initialise semaphores
    Semaphore_Params params;
    Semaphore_Params_init(&params);

    params.mode = Semaphore_Mode_COUNTING;
    Semaphore_construct(&clients_connected, 0, &params);
    clients_connected_h = Semaphore_handle(&clients_connected);
}
