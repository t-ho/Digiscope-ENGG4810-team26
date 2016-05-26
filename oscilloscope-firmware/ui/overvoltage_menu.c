/*
 * overvoltage_menu.c
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#include "graphics_thread.h"
#include "overvolt.h"

static char overvoltage_text[] = "Overvoltage on channel X!";

static void OnOverVoltageAcknowledge(tWidget *psWidget);

static Canvas(overvoltage_label, &menus[OVERVOLTAGE_MENU], 0, 0, &SSD1289_Display, 10, 50, 300, 24,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm22b, overvoltage_text, 0, 0);

RectangularButton(OverVoltageAcknowledge, &menus[OVERVOLTAGE_MENU], &overvoltage_label, 0, &SSD1289_Display, 10, 90,
	300, 100, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrRed, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm24b, "Acknowledge", 0, 0, 0, 0, OnOverVoltageAcknowledge);

static void
OnOverVoltageAcknowledge(tWidget *psWidget)
{
	OverVoltageReenable();
	OnMAIN(NULL);
}

void
OverVoltageWarn(uint8_t channel)
{
	overvoltage_text[strlen(overvoltage_text) - 2] = channel + 'A';
	OnOVERVOLTAGE(NULL);
}
