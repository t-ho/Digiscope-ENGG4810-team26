/*
 * net.h
 *
 *  Created on: 11 May 2016
 *      Author: ryanf
 */

#ifndef NET_H_
#define NET_H_

extern void Init_SendQueue(void);
extern int NetSend(Command *cmd, uint32_t timeout);

#endif /* NET_H_ */
