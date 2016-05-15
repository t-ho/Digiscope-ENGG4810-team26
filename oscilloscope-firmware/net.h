/*
 * net.h
 *
 *  Created on: 11 May 2016
 *      Author: ryanf
 */

#ifndef NET_H_
#define NET_H_

typedef struct NetPacket {
	Queue_Elem _elem;
	char *data;
	size_t len;
} NetPacket;

typedef struct Command
{
	uint8_t id;
	uint8_t is_notification;
	uint32_t arg;
} Command;

extern void Init_SendQueue(void);
extern int NetSend(NetPacket *np);

#endif /* NET_H_ */
