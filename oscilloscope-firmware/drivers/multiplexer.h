/*
 * multiplexer.h
 *
 *  Created on: 29 May 2016
 *      Author: Ryan
 */



#ifndef DRIVERS_MULTIPLEXER_H_
#define DRIVERS_MULTIPLEXER_H_

typedef struct Multiplexer {
	uint32_t ports[3];
	uint8_t pins[3];
} Multiplexer;

extern void MultiplexerSet(Multiplexer *mult, uint8_t val);
extern void Multiplexer_Init(Multiplexer *mult);


#endif /* DRIVERS_MULTIPLEXER_H_ */
