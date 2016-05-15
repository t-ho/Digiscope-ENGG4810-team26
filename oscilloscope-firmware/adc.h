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

extern uint16_t adc_pos;
extern uint16_t adc_buffer[ADC_BUF_SIZE] __attribute__(( aligned(8) ));

#endif /* ADC_H_ */
