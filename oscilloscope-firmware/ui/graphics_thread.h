/*
 * graphics_thread.h
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#ifndef UI_GRAPHICS_THREAD_H_
#define UI_GRAPHICS_THREAD_H_

#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>

#include "grlib/grlib.h"
#include "grlib/widget.h"
#include "grlib/canvas.h"
#include "grlib/pushbutton.h"

#include "drivers/SSD1289_driver.h"
#include "command.h"

enum menus
{
	MAIN_MENU,
	RANGE_MENU,
	TRIGGER_ARM_MENU,
	TRIGGER_THRESHOLD_MENU,
	WAVEGEN_MENU,
	BRIGHTNESS_MENU,
	OVERVOLTAGE_MENU
};

extern uint8_t current_menu;
extern tCanvasWidget menus[];
extern void OnMAIN(tWidget *psWidget);
extern void OnOVERVOLTAGE(tWidget *psWidget);
extern void OnTRIGGER_ARM(tWidget *psWidget);
extern void OnTRIGGER_THRESHOLD(tWidget *psWidget);

extern void UISend(Command *cmd, uint32_t timeout);

extern uint32_t Standard_Step(uint32_t, int8_t);
extern void SI_Micro_Print(char* line1, char* line2, int32_t val, char* suffix);
extern void Repaint(tWidget *psWidget);

extern void Init_UI(void);

#endif /* UI_GRAPHICS_THREAD_H_ */
