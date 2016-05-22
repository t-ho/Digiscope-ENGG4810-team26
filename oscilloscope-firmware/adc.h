/*
 * adc.h
 *
 *  Created on: 12 May 2016
 *      Author: Ryan
 */

#include <stdbool.h>
#include <string.h>

#include <ti/sysbios/BIOS.h>

#include "driverlib/adc.h"
#include "driverlib/sysctl.h"
#include "driverlib/udma.h"

#include "inc/hw_adc.h"
#include "inc/hw_udma.h"
#include "inc/hw_memmap.h"

#ifndef ADC_H_
#define ADC_H_

#define ADC_SAMPLE_BUF_SIZE 1024
#define ADC_BUF_SIZE 1024 * 25

extern void ADC_Init(void);
extern void ADCPause(void);
extern void ACDResume(void);
extern void ForceTrigger(void);
extern void ADCResume(void);

extern uint16_t adc_pos_A;
extern uint16_t adc_pos_B;
extern uint16_t adc_buffer_A[ADC_BUF_SIZE] __attribute__(( aligned(8) ));
extern uint16_t adc_buffer_B[ADC_BUF_SIZE] __attribute__(( aligned(8) ));


#endif /* ADC_H_ */
