/*
 * eeprom.h
 *
 *  Created on: 29 May 2016
 *      Author: Ryan
 */

#ifndef EEPROM_H_
#define EEPROM_H_

typedef enum EEPROMSetting {
	EEPROM_TRIGGER_MODE,
	EEPROM_TRIGGER_TYPE,
	EEPROM_TRIGGER_THRESHOLD,
	EEPROM_TRIGGER_SAMPLESIZE,
	EEPROM_TRIGGER_NUMSAMPLES,
	EEPROM_TRIGGER_CHANNEL
} EEPROMSetting;

extern void EEPROM_Init(void);
extern void EEPROMSave(EEPROMSetting setting, int32_t val);
extern int32_t EEPROMLoad(EEPROMSetting setting);

#endif /* EEPROM_H_ */
