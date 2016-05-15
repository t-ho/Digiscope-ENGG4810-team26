/*
 * common.h
 *
 *  Created on: 30 Apr 2016
 *      Author: Ryan
 */

#include <stdio.h>

/* BIOS Header files */
#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Semaphore.h>
#include <ti/sysbios/knl/Queue.h>
#include <ti/sysbios/knl/Mailbox.h>

#ifndef COMMON_H_
#define COMMON_H_

extern void Init_Semaphores(void);

extern uint32_t Standard_Step(uint32_t, int8_t);
extern void SI_Micro_Print(char* line1, char* line2, int32_t val, char* suffix);

extern Semaphore_Handle clients_connected_h;

extern Mailbox_Handle GraphicsMailbox;

enum GraphicsMessageType
{
	GM_PTR_UP,
	GM_PTR_DOWN,
	GM_REFRESH,
	GM_IP_UPDATE,
	GM_CONN_UPDATE,
	GM_OVERVOLTAGE,
};

typedef struct GraphicsMessage
{
	enum GraphicsMessageType type;
	uint32_t data[2];
} GraphicsMessage;

#endif /* COMMON_H_ */
