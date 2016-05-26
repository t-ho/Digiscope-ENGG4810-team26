/*
 * trigger.h
 *
 *  Created on: 24 May 2016
 *      Author: Ryan
 */

#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Event.h>

#ifndef TRIGGER_H_
#define TRIGGER_H_

#define EVENT_ID_A_PRI Event_Id_00
#define EVENT_ID_A_ALT Event_Id_01
#define EVENT_ID_B_PRI Event_Id_02
#define EVENT_ID_B_ALT Event_Id_03

typedef enum TriggerMode
{
	TRIGGER_MODE_AUTO,
	TRIGGER_MODE_NORMAL,
	TRIGGER_MODE_SINGLE
} TriggerMode;

typedef enum TriggerType
{
	TRIGGER_TYPE_RISING,
	TRIGGER_TYPE_FALLING,
	TRIGGER_TYPE_LEVEL
} TriggerType;

typedef enum SampleSize
{
	SAMPLE_SIZE_8_BIT = 0,
	SAMPLE_SIZE_12_BIT = 1,
} SampleSize;

extern void Trigger_Init(void);
extern Event_Handle AcqEvent;
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
extern uint32_t TriggerGetNumSamples(void);
extern void TriggerSetNumSamples(uint32_t newNum);


extern volatile uint8_t channel_a_pri_full;
extern volatile uint8_t channel_a_alt_full;
extern volatile uint8_t channel_b_pri_full;
extern volatile uint8_t channel_b_alt_full;

#endif /* TRIGGER_H_ */
