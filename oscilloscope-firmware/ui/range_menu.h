/*
 * range_menu.h
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#ifndef UI_RANGE_MENU_H_
#define UI_RANGE_MENU_H_

#include "frontend.h"

extern void RangeHorSetText(const char* line1, const char* line2);
extern void RangeVerSetText(Channel channel, const char* line1, const char* line2);
extern void SetDisplayChannel(Channel channel);
extern void RangeCouplingSetText(Channel channel, const char* text);

#endif /* UI_RANGE_MENU_H_ */
