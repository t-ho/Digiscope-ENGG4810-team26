/*
 * overvolt.c
 *
 *  Created on: 12 May 2016
 *      Author: Ryan
 */

#include <command.h>
#include "overvolt.h"
#include "driverlib/rom.h"
#include "driverlib/rom_map.h"

#include "ui/graphics_thread.h"

void
OverVoltageInit(void)
{
	MAP_SysCtlPeripheralEnable(SYSCTL_PERIPH_COMP0);
	while (!MAP_SysCtlPeripheralReady(SYSCTL_PERIPH_COMP0));

	MAP_ComparatorRefSet(COMP_BASE, COMP_REF_2_371875V);

	// Uses pin PC7
	MAP_ComparatorConfigure(COMP_BASE, 0, COMP_TRIG_NONE | COMP_INT_HIGH | COMP_ASRCP_REF | COMP_OUTPUT_NORMAL);
	// Uses pin PC4
	MAP_ComparatorConfigure(COMP_BASE, 1, COMP_TRIG_NONE | COMP_INT_HIGH | COMP_ASRCP_REF | COMP_OUTPUT_NORMAL);

	MAP_ComparatorIntClear(COMP_BASE, 0);
	MAP_ComparatorIntClear(COMP_BASE, 1);

	MAP_ComparatorIntEnable(COMP_BASE, 0);
	MAP_ComparatorIntEnable(COMP_BASE, 1);
}

void
ComparatorHandler(unsigned int index)
{
	MAP_ComparatorIntDisable(COMP_BASE, 0);
	MAP_ComparatorIntDisable(COMP_BASE, 1);

	Command msg;
	msg.type = _COMMAND_OVERVOLTAGE;
	msg.args[0] = index;

	UISend(&msg, 0);
}

void
OverVoltageReenable(void)
{
	MAP_ComparatorIntClear(COMP_BASE, 0);
	MAP_ComparatorIntClear(COMP_BASE, 1);

	MAP_ComparatorIntEnable(COMP_BASE, 0);
	MAP_ComparatorIntEnable(COMP_BASE, 1);
}
