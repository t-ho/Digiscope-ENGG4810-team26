/*
 * acquisition.h
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

#ifndef ACQUISITION_H_
#define ACQUISITION_H_

#define ADC_TRANSFER_SIZE 1024

extern void ADC_Init(void);
extern uint32_t ADCGetPeriod(void);
extern void ADCSetFreq(uint32_t freq);

extern uint16_t adc_buffer_A_PRI[ADC_TRANSFER_SIZE] __attribute__(( aligned(8) ));
extern uint16_t adc_buffer_A_ALT[ADC_TRANSFER_SIZE] __attribute__(( aligned(8) ));
extern uint16_t adc_buffer_B_PRI[ADC_TRANSFER_SIZE] __attribute__(( aligned(8) ));
extern uint16_t adc_buffer_B_ALT[ADC_TRANSFER_SIZE] __attribute__(( aligned(8) ));

#endif /* ACQUISITION_H_ */
