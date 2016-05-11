/*
 * common.c
 *
 *  Created on: 30 Apr 2016
 *      Author: Ryan
 */

#include "common.h"

Semaphore_Handle widget_message_h;
Semaphore_Handle ip_update_h;
Semaphore_Handle force_trigger_h;

static Semaphore_Struct ip_update;
static Semaphore_Struct widget_message;
static Semaphore_Struct force_trigger;
uint32_t IpAddrVal = 0;
uint8_t ClientConnected = 0;

void
Init_Semaphores(void)
{
    // Initialise semaphores
    Semaphore_Params params;
    Semaphore_Params_init(&params);
    params.mode = Semaphore_Mode_BINARY;

    Semaphore_construct(&ip_update, 0, &params);
    ip_update_h = Semaphore_handle(&ip_update);

    Semaphore_construct(&widget_message, 0, &params);
    widget_message_h = Semaphore_handle(&widget_message);

    Semaphore_construct(&force_trigger, 0, &params);
    force_trigger_h = Semaphore_handle(&force_trigger);
}
