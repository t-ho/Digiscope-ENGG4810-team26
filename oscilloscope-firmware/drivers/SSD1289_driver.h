/*
 * SSD1289_driver.h
 *
 *  Created on: 26 Apr 2016
 *      Author: Ryan
 */

#ifndef SSD1289_DRIVER_H_
#define SSD1289_DRIVER_H_

#include "grlib/grlib.h"

extern void SSD1289_Init(void);
extern void Pant(uint16_t color);
extern void SSD1289_Backlight_Set(uint8_t level);
extern void SSD1289_Set_Backlight_On(bool state);
extern bool SSD1289_Get_Backlight_On(void);

extern const tDisplay SSD1289_Display;

#endif /* SSD1289_DRIVER_H_ */
