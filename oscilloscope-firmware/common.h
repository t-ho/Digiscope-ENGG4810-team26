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

#ifndef COMMON_H_
#define COMMON_H_

#define ADC_SAMPLE_BUF_SIZE 8
#define ADC_BUF_SIZE 1024 * 25

extern void Init_SendQueue(void);
extern void Init_Semaphores(void);

extern uint32_t Standard_Step(uint32_t, int8_t);
extern void SI_Micro_Print(char* line1, char* line2, int32_t val, char* suffix);

extern Semaphore_Handle widget_message_h;
extern Semaphore_Handle ip_update_h;
extern Semaphore_Handle force_trigger_h;

extern uint32_t IpAddrVal;
extern uint8_t ClientConnected;

extern uint16_t adc_pos;
extern uint16_t adc_buffer[ADC_BUF_SIZE] __attribute__(( aligned(8) ));

#endif /* COMMON_H_ */
