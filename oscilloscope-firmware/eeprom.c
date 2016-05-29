/*
 * eeprom.c
 *
 *  Created on: 29 May 2016
 *      Author: Ryan
 */

#include <stdint.h>
#include <stdbool.h>

#include "inc/hw_memmap.h"

#include "driverlib/eeprom.h"
#include "driverlib/rom.h"
#include "driverlib/rom_map.h"
#include "driverlib/sysctl.h"

#include "eeprom.h"

void
EEPROM_Init(void)
{
    MAP_SysCtlPeripheralEnable(SYSCTL_PERIPH_EEPROM0);
    while(!MAP_SysCtlPeripheralReady(SYSCTL_PERIPH_EEPROM0))
    EEPROMInit();
}

void
EEPROMSave(EEPROMSetting setting, int32_t val)
{
	EEPROMProgram((uint32_t *)&val, (uint32_t)setting * 4, 4);
}

int32_t
EEPROMLoad(EEPROMSetting setting)
{
	int32_t val = 255;
	EEPROMRead((uint32_t *)&val, (uint32_t)setting * 4, 4);
	return val;
}
