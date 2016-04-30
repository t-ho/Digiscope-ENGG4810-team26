/*
 * common.c
 *
 *  Created on: 30 Apr 2016
 *      Author: Ryan
 */

#include "common.h"

static Semaphore_Struct ip_update;
static Semaphore_Struct widget_message;

void
Init_Semaphores(void)
{
    // Initialise semaphores
    Semaphore_Params params;
    Semaphore_Params_init(&params);

    Semaphore_construct(&ip_update, 0, &params);
    ip_update_h = Semaphore_handle(&ip_update);

    Semaphore_construct(&widget_message, 0, &params);
    widget_message_h = Semaphore_handle(&widget_message);
}
