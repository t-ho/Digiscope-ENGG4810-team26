/*
 * potentiometer.h
 *
 *  Created on: 28 May 2016
 *      Author: Ryan
 */

#ifndef DRIVERS_POTENTIOMETER_H_
#define DRIVERS_POTENTIOMETER_H_

enum PotPins {
	POT_PIN_MOSI,
	POT_PIN_MISO,
	POT_PIN_CLK,
	POT_PIN_CE
};

typedef struct Potentiometer {
	uint32_t ports[4];
	uint8_t pins[4];
} Potentiometer;

extern void Potentiometer_Init(Potentiometer *pot);
extern void PotentiometerSet(Potentiometer *pot, uint16_t val);

#endif /* DRIVERS_POTENTIOMETER_H_ */
