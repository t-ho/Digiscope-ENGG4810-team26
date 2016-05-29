/*
 * multiplexer.h
 *
 *  Created on: 29 May 2016
 *      Author: Ryan
 */

#include "driverlib/gpio.h"

#ifndef DRIVERS_MULTIPLEXER_H_
#define DRIVERS_MULTIPLEXER_H_

typedef struct Multiplexer {
	uint32_t ports[2];
	uint8_t pins[2];
} Multiplexer;

extern void MultiplexerSet(Multiplexer *mult, uint8_t val);
extern void Multiplexer_Init(Multiplexer *mult);


#endif /* DRIVERS_MULTIPLEXER_H_ */
