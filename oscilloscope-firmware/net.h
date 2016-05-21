/*
 * net.h
 *
 *  Created on: 11 May 2016
 *      Author: ryanf
 */

#ifndef NET_H_
#define NET_H_

#include "command.h"

extern void Init_Net(void);
extern int NetSend(Command *cmd, uint32_t timeout);
extern int NetGetClients(void);

#endif /* NET_H_ */
