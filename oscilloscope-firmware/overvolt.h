/*
 * overvolt.h
 *
 *  Created on: 12 May 2016
 *      Author: Ryan
 */

#ifndef OVERVOLT_H_
#define OVERVOLT_H_

#include <stdbool.h>
#include <string.h>

#include <ti/sysbios/BIOS.h>

#include "driverlib/comp.h"
#include "driverlib/sysctl.h"

#include "inc/hw_memmap.h"

extern void OverVoltageInit(void);

#endif /* OVERVOLT_H_ */
