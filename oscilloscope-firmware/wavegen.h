/*
 * wavegen.h
 *
 *  Created on: 19 May 2016
 *      Author: Ryan
 */

#ifndef WAVEGEN_H_
#define WAVEGEN_H_

extern void WaveGenEnableSet(bool on);
extern bool WaveGenEnableGet(void);

extern void WaveGen_Init(void);

extern void WaveGenSetFreq(uint32_t freq);
extern uint32_t WaveGenGetFreq(void);

#endif /* WAVEGEN_H_ */
