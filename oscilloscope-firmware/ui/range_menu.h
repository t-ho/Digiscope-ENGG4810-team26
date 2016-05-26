/*
 * range_menu.h
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#ifndef UI_RANGE_MENU_H_
#define UI_RANGE_MENU_H_

extern void RangeHorSetText(const char* line1, const char* line2);
extern void RangeVerSetText(uint32_t channel, const char* line1, const char* line2);
extern void SetDisplayChannel(uint32_t channel);
extern void RangeCouplingSetText(uint32_t channel, const char* text);

#endif /* UI_RANGE_MENU_H_ */
