/*
 * Copyright (c) 2015, Texas Instruments Incorporated
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * *  Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * *  Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * *  Neither the name of Texas Instruments Incorporated nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

#include <string.h>
#include <stdio.h>

/* XDCtools Header files */
#include <xdc/std.h>
#include <xdc/runtime/Error.h>
#include <xdc/runtime/System.h>

/* BIOS Header files */
#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Task.h>

#include <sys/socket.h>

#include "common.h"
#include "net.h"

#define TCPPORT 1000

#define TCPHANDLERSTACK 1024

#define SOCKET_TIMEOUT 500000

/* Prototypes */
void tcpHandler(UArg arg0, UArg arg1);

#define TCPPACKETSIZE 256
#define NUMTCPWORKERS 3

static Semaphore_Struct NetSendLock;
static Semaphore_Handle NetSendLock_h;
static Queue_Handle NetSendQueue;

enum CommandTypes
{
	UNKNOWN = 0x00,
	//VERTICAL_RANGE = 0x01,
	VERTICAL_RANGE = 0x61,
	HORIZONTAL_RANGE = 0x02,
	TRIGGER_MODE = 0x03,
	TRIGGER_TYPE = 0x04,
	TRIGGER_THRESHOLD = 0x05,
	CHANNEL_COUPLING = 0x0C,
	FUNCTION_GEN_OUT = 0xF0,
	FORCE_TRIGGER = 'f',
};

void
Init_SendQueue(void)
{
    Semaphore_Params params;
    Semaphore_Params_init(&params);
    params.mode = Semaphore_Mode_BINARY;

    Semaphore_construct(&NetSendLock, 0, &params);
    NetSendLock_h = Semaphore_handle(&NetSendLock);

	NetSendQueue = Queue_create(NULL, NULL);
}

int
NetSend(NetPacket *np)
{
	if (Semaphore_pend(NetSendLock_h, 100))
	{
		Queue_enqueue(NetSendQueue, (Queue_Elem*) np);
		Semaphore_post(NetSendLock_h);
		return 0;
	}
	else
	{
		return -1;
	}
}

void
packet_process(char *buffer, size_t len)
{
	static NetPacket np;

	static char reply[64];

	Command *command = (Command*) buffer;

	if (len != 6) {
		System_printf("Not a command\n");
		return;
	}

	GraphicsMessage msg;


	switch (command->id)
	{
		case VERTICAL_RANGE:
			msg.type = GM_SET_VER_RANGE;
			msg.data[0] = command->arg;
			Mailbox_post(GraphicsMailbox, &msg, 0);
			sprintf(reply, "Vert range %ul\n", command->arg);
			break;
		case HORIZONTAL_RANGE:
			msg.type = GM_SET_HOR_RANGE;
			msg.data[0] = command->arg;
			Mailbox_post(GraphicsMailbox, &msg, 0);
			sprintf(reply, "Horiz range %ul\n", command->arg);
			break;
		case TRIGGER_MODE:
			sprintf(reply, "Trigger mode %ul\n", command->arg);
			break;
		case FORCE_TRIGGER:
			sprintf(reply, "Forcing trigger now\n");
			ForceTrigger();
			break;
		default:
			sprintf(reply, "Unrecognised Command\n");
	}

	System_printf(reply);
	np.data = reply;
	np.len = strlen(np.data);
	NetSend(&np);
}

/*
 *  ======== tcpWorker ========
 *  Task to handle TCP connection. Can be multiple Tasks running
 *  this function.
 */
Void tcpWorker(UArg arg0, UArg arg1)
{
    int  clientfd = (int)arg0;
    int  bytesRcvd;
    int  bytesSent;
    char buffer[TCPPACKETSIZE];

    System_printf("tcpWorker: start clientfd = 0x%x\n", clientfd);

    Semaphore_post(clients_connected_h);
	Semaphore_post(NetSendLock_h);

	NetPacket keepalive;
	keepalive.data = "keepalive\n";
	keepalive.len = strlen(keepalive.data);

	NetPacket echopacket;

	GraphicsMessage msg;
	msg.type = GM_CONN_UPDATE;
	Mailbox_post(GraphicsMailbox, &msg, 0);

    while (1) {
    	bytesRcvd = recv(clientfd, buffer, TCPPACKETSIZE, 0);

		if (bytesRcvd > 0)
		{
			packet_process(buffer, bytesRcvd);

			echopacket.data = buffer;
			echopacket.len = bytesRcvd;

			NetSend(&echopacket);
		}

    	if (Semaphore_pend(NetSendLock_h, BIOS_WAIT_FOREVER))
    	{

			if (Queue_empty(NetSendQueue))
			{
				Queue_enqueue(NetSendQueue, (Queue_Elem*) &keepalive);
			}

			NetPacket *np;
			while(!Queue_empty(NetSendQueue)) {

				np = Queue_dequeue(NetSendQueue);

				bytesSent = send(clientfd, np->data, np->len, 0);

				if (bytesSent < 0 || bytesSent != np->len) {
					System_printf("Error: send failed.\n");
					System_printf("tcpWorker stop clientfd = 0x%x\n", clientfd);

					close(clientfd);

					if (!Semaphore_pend(clients_connected_h, 0))
					{
						System_printf("Unable to decrement client count\n");
					}

					GraphicsMessage msg;
					msg.type = GM_CONN_UPDATE;
					Mailbox_post(GraphicsMailbox, &msg, 0);

					return;
				}
			}
    	}
    	Semaphore_post(NetSendLock_h);
    }
}

/*
 *  ======== tcpHandler ========
 *  Creates new Task to handle new TCP connections.
 */
void tcpHandler(UArg arg0, UArg arg1)
{
    int                status;
    int                clientfd;
    int                server;
    struct sockaddr_in localAddr;
    struct sockaddr_in clientAddr;
    int                optval;
    int                optlen = sizeof(optval);
    socklen_t          addrlen = sizeof(clientAddr);
    Task_Handle        taskHandle;
    Task_Params        taskParams;
    Error_Block        eb;

    server = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (server == -1) {
        System_printf("Error: socket not created.\n");
        goto shutdown;
    }


    memset(&localAddr, 0, sizeof(localAddr));
    localAddr.sin_family = AF_INET;
    localAddr.sin_addr.s_addr = htonl(INADDR_ANY);
    localAddr.sin_port = htons(arg0);

    status = bind(server, (struct sockaddr *)&localAddr, sizeof(localAddr));
    if (status == -1) {
        System_printf("Error: bind failed.\n");
        goto shutdown;
    }

    status = listen(server, NUMTCPWORKERS);
    if (status == -1) {
        System_printf("Error: listen failed.\n");
        goto shutdown;
    }

    optval = 1;
    if (setsockopt(server, SOL_SOCKET, SO_KEEPALIVE, &optval, optlen) < 0) {
        System_printf("Error: setsockopt failed\n");
        goto shutdown;
    }

    struct timeval timeout;
    timeout.tv_sec = 0;
    timeout.tv_usec = SOCKET_TIMEOUT;

    if (setsockopt(server, SOL_SOCKET, SO_RCVTIMEO, &timeout, sizeof(timeout)) < 0) {
        System_printf("Error: setsockopt timeout failed\n");
        goto shutdown;
    }

    while ((clientfd =
            accept(server, (struct sockaddr *)&clientAddr, &addrlen)) != -1) {

        System_printf("tcpHandler: Creating thread clientfd = %d\n", clientfd);

        /* Init the Error_Block */
        Error_init(&eb);

        /* Initialize the defaults and set the parameters. */
        Task_Params_init(&taskParams);
        taskParams.arg0 = (UArg)clientfd;
        taskParams.stackSize = 1280;
        taskHandle = Task_create((Task_FuncPtr)tcpWorker, &taskParams, &eb);
        if (taskHandle == NULL) {
            System_printf("Error: Failed to create new Task\n");
            close(clientfd);
        }

        /* addrlen is a value-result param, must reset for next accept call */
        addrlen = sizeof(clientAddr);
    }

    System_printf("Error: accept failed.\n");

shutdown:
    if (server > 0) {
        close(server);
    }
}

/*
 *  ======== netOpenHook ========
 *  NDK network open hook used to initialize IPv6
 */
void netOpenHook(){
    Task_Handle taskHandle;
    Task_Params taskParams;
    Error_Block eb;

    /* Make sure Error_Block is initialized */
    Error_init(&eb);

    /*
     *  Create the Task that farms out incoming TCP connections.
     *  arg0 will be the port that this task listens to.
     */
    Task_Params_init(&taskParams);
    taskParams.stackSize = TCPHANDLERSTACK;
    taskParams.priority = 1;
    taskParams.arg0 = TCPPORT;
    taskHandle = Task_create((Task_FuncPtr)tcpHandler, &taskParams, &eb);
    if (taskHandle == NULL) {
        System_printf("netOpenHook: Failed to create tcpHandler Task\n");
    }

    System_flush();
}

void
ipAddrHook(uint32_t IPAddr, uint32_t IfIdx, uint32_t fAdd)
{
//    System_printf("mynetworkIPAddrHook: enter\n");

	GraphicsMessage msg;
	msg.type = GM_IP_UPDATE;
	msg.data[0] = ntohl(IPAddr);

	Mailbox_post(GraphicsMailbox, &msg, 0);

//    System_printf("mynetworkIPAddrHook:\tIf-%d:%d.%d.%d.%d\n", IfIdx,
//            (uint8_t)(IpAddrVal>>24)&0xFF, (uint8_t)(IpAddrVal>>16)&0xFF,
//            (uint8_t)(IpAddrVal>>8)&0xFF, (uint8_t)IpAddrVal&0xFF);
//
//    System_flush();
}
