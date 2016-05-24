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

#define EVENT_ID_A_HALF Event_Id_00
#define EVENT_ID_A_FULL Event_Id_01
#define EVENT_ID_B_HALF Event_Id_02
#define EVENT_ID_B_FULL Event_Id_03

extern void Trigger_Init(void);
extern Event_Handle AcqEvent;
extern void ForceTrigger(void);

#endif /* TRIGGER_H_ */
