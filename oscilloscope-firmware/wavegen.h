/*
 * wavegen.h
 *
 *  Created on: 19 May 2016
 *      Author: Ryan
 */

#ifndef WAVEGEN_H_
#define WAVEGEN_H_

typedef enum WaveType
{
	SINE,
	SQUARE,
	TRIANGLE,
	RAMP,
	NOISE,
} WaveType;

extern void WaveGenNotify(void);

extern void WaveGenEnableSet(bool on);
extern bool WaveGenEnableGet(void);

extern void WaveGenSetShape(WaveType shape);
extern WaveType WaveGenGetShape(void);

extern void WaveGenSetAmplitude(uint32_t uV);
extern uint32_t WaveGenGetAmplitude(void);

extern void WaveGenSetOffset(int32_t uV);
extern int32_t WaveGenGetOffset(void);

extern void WaveGen_Init(void);

extern void WaveGenSetFreq(uint32_t freq);
extern uint32_t WaveGenGetFreq(void);

#endif /* WAVEGEN_H_ */
