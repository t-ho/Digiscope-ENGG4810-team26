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

#include "net.h"
#include "adc.h"
#include "command.h"
#include "trigger.h"
#include "ui/graphics_thread.h"

#define TCPPORT 4810

#define TCPHANDLERSTACK 1024

#define SOCKET_TIMEOUT 100000

/* Prototypes */
void tcpHandler(UArg arg0, UArg arg1);

#define TCPPACKETSIZE 256
#define NUMTCPWORKERS 3

static Mailbox_Handle NetCommandMailbox;

static Semaphore_Handle clients_connected_h;
static Semaphore_Struct clients_connected;

void
Init_Net(void)
{
	Mailbox_Params mbparams;
	Mailbox_Params_init(&mbparams);
	static Error_Block eb;
	NetCommandMailbox = Mailbox_create(sizeof(Command),128,&mbparams,&eb);

    Semaphore_Params params;
    Semaphore_Params_init(&params);

    params.mode = Semaphore_Mode_COUNTING;
    Semaphore_construct(&clients_connected, 0, &params);
    clients_connected_h = Semaphore_handle(&clients_connected);
}

int
NetGetClients(void)
{
	return Semaphore_getCount(clients_connected_h);
}

int
NetSend(Command *cmd, uint32_t timeout)
{
	return Mailbox_post(NetCommandMailbox, cmd, timeout);
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

	Command msg;
	msg.type = _COMMAND_CONN_UPDATE;
	UISend(&msg, 0);

    while (1)
    {
    	bytesRcvd = recv(clientfd, buffer, TCPPACKETSIZE, 0);

		if (bytesRcvd == 6)
		{
			Command *cmd = (Command*) buffer;
			cmd->args[0] = ntohl(cmd->args[0]);
			System_printf("C: %x - %d\n", cmd->type, cmd->args[0]);
			UISend(cmd, 0);
		}
		else if (bytesRcvd > 0)
		{
			System_printf("Not a command\n");
		}

		if (Mailbox_getNumPendingMsgs(NetCommandMailbox) == 0)
		{
			Command keepalive;
			keepalive.type = COMMAND_KEEPALIVE;
			NetSend(&keepalive, 0);
		}

		Command cmd;
		while(Mailbox_pend(NetCommandMailbox, &cmd, 0))
		{
			if (cmd.type == _COMMAND_ACQUISITION_SEND_COMPLETE)
			{
				Semaphore_post(bufferlock);
			}

			if (cmd.type == SAMPLE_PACKET_A_8 || cmd.type == SAMPLE_PACKET_B_8
					|| cmd.type == SAMPLE_PACKET_A_12 || cmd.type == SAMPLE_PACKET_B_12)
			{
				SampleCommand *scmd = (SampleCommand*) &cmd;
				scmd->num_samples = htons(scmd->num_samples);
				scmd->period = htons(scmd->period);
			}
			else
			{
				cmd.args[0] = htonl(cmd.args[0]);
			}

			bytesSent = send(clientfd, &cmd, COMMANDLENGTH, 0);

			if (bytesSent < 0 || bytesSent != COMMANDLENGTH) {
				System_printf("Error: send failed.\n");
				goto clientlost;
			}

			if (cmd.type == SAMPLE_PACKET_A_8 || cmd.type == SAMPLE_PACKET_B_8
				|| cmd.type == SAMPLE_PACKET_A_12 || cmd.type == SAMPLE_PACKET_B_12)
			{
				SampleCommand *scmd = (SampleCommand*) &cmd;

				uint16_t packetSize = ntohs(scmd->num_samples);
				if (cmd.type == SAMPLE_PACKET_A_12 || cmd.type == SAMPLE_PACKET_B_12)
				{
					packetSize *= 2;
				}

				bytesSent = send(clientfd, scmd->buffer, packetSize, 0);

				if (bytesSent < 0 || bytesSent != packetSize) {
					System_printf("Error: buffer send failed.\n");
					goto clientlost;
				}
			}
    	}
    }

clientlost:
	System_printf("tcpWorker stop clientfd = 0x%x\n", clientfd);

	close(clientfd);

	if (!Semaphore_pend(clients_connected_h, 0))
	{
		System_printf("Unable to decrement client count\n");
	}

	// Clear mailbox
	Command cmd;
	while(Mailbox_pend(NetCommandMailbox, &cmd, 0));

	// Don't keep the buffer locked
	Semaphore_post(bufferlock);
	Task_sleep(10);
	// Don't leave the buffer unlocked for next time
	Semaphore_pend(bufferlock, 0);

	Command connupdate;
	connupdate.type = _COMMAND_CONN_UPDATE;
	UISend(&connupdate, 0);
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

	Command msg;
	msg.type = _COMMAND_IP_UPDATE;
	msg.args[0] = ntohl(IPAddr);

	UISend(&msg, 0);

//    System_printf("mynetworkIPAddrHook:\tIf-%d:%d.%d.%d.%d\n", IfIdx,
//            (uint8_t)(IpAddrVal>>24)&0xFF, (uint8_t)(IpAddrVal>>16)&0xFF,
//            (uint8_t)(IpAddrVal>>8)&0xFF, (uint8_t)IpAddrVal&0xFF);
//
//    System_flush();
}
