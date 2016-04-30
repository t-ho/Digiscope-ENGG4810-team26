/*
 * common.h
 *
 *  Created on: 30 Apr 2016
 *      Author: Ryan
 */

/* BIOS Header files */
#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Semaphore.h>

#ifndef COMMON_H_
#define COMMON_H_

extern void Init_Semaphores(void);

Semaphore_Handle widget_message_h;
Semaphore_Handle ip_update_h;
Semaphore_Handle force_trigger_h;
extern uint32_t IpAddrVal;
extern uint8_t ClientConnected;

#endif /* COMMON_H_ */
