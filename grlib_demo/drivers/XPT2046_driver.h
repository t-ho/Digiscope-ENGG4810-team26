/*
 * XPT2046_driver.h
 *
 *  Created on: 27 Apr 2016
 *      Author: Ryan
 */

#ifndef DRIVERS_XPT2046_DRIVER_H_
#define DRIVERS_XPT2046_DRIVER_H_

extern void XPT2046_Init(void);
extern void XPT2046_SetCallback(int32_t (*pfnCallback)(uint32_t message, int32_t x, int32_t y));

#endif /* DRIVERS_XPT2046_DRIVER_H_ */
