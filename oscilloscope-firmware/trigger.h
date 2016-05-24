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

extern void Trigger_Init(void);
extern Event_Handle AcqEvent;
extern void ForceTrigger(void);

extern volatile uint8_t channel_a_pri_full;
extern volatile uint8_t channel_a_alt_full;
extern volatile uint8_t channel_b_pri_full;
extern volatile uint8_t channel_b_alt_full;

#endif /* TRIGGER_H_ */
