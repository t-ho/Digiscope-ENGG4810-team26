/*
 * trigger.h
 *
 *  Created on: 24 May 2016
 *      Author: Ryan
 */

#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Event.h>
#include <ti/sysbios/knl/Semaphore.h>

#ifndef TRIGGER_H_
#define TRIGGER_H_

#define SAMPLE_DIVISOR_MAX 1024

#define EVENT_ID_A_PRI Event_Id_00
#define EVENT_ID_A_ALT Event_Id_01
#define EVENT_ID_B_PRI Event_Id_02
#define EVENT_ID_B_ALT Event_Id_03

typedef enum TriggerMode
{
	TRIGGER_MODE_AUTO = 0,
	TRIGGER_MODE_SINGLE = 1,
	TRIGGER_MODE_NORMAL = 2
} TriggerMode;

typedef enum TriggerType
{
	TRIGGER_TYPE_LEVEL = 0,
	TRIGGER_TYPE_RISING = 1,
	TRIGGER_TYPE_FALLING = 2
} TriggerType;

typedef enum TriggerState
{
	TRIGGER_STATE_ARMED = 0,
	TRIGGER_STATE_TRIGGERED = 1,
	TRIGGER_STATE_STOP = 2
} TriggerState;

typedef enum SampleSize
{
	SAMPLE_SIZE_8_BIT = 0,
	SAMPLE_SIZE_12_BIT = 1,
} SampleSize;

extern Event_Handle AcqEvent;
extern Semaphore_Handle bufferlock;

extern void Trigger_Init(void);
extern void ForceTrigger(void);
extern int32_t TriggerGetThreshold(void);
extern void TriggerSetThreshold(int32_t threshold);
extern TriggerMode TriggerGetMode(void);
extern void TriggerSetMode(TriggerMode mode);
extern TriggerType TriggerGetType(void);
extern void TriggerSetType(TriggerType type);
extern uint32_t TriggerGetChannel(void);
extern void TriggerSetChannel(uint32_t channel);
extern SampleSize TriggerGetSampleSize(void);
extern void TriggerSetSampleSize(SampleSize mode);
extern TriggerState TriggerGetState(void);
extern void TriggerSetState(TriggerState state);
extern uint32_t TriggerGetNumSamples(void);
extern void TriggerSetNumSamples(uint32_t newNum);
extern uint16_t TriggerGetSampleDivisor(void);
extern void TriggerSetSampleDivisor(uint16_t divisor);
extern void TriggerNotify(void);

#endif /* TRIGGER_H_ */
