/*
 * brightness_menu.c
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#include "graphics_thread.h"

static void OnBrightnessUp(tWidget *psWidget);
static void OnBrightnessDown(tWidget *psWidget);

static uint8_t brightness = 5;
static char brightness_text[] = "5";

static Canvas(brightness_label, &menus[BRIGHTNESS_MENU], 0, 0, &SSD1289_Display, 150, 150, 20, 20,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm20, brightness_text, 0, 0);
static RectangularButton(BrightnessUp, &menus[BRIGHTNESS_MENU], &brightness_label, 0, &SSD1289_Display, 220, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, OnBrightnessUp);
static RectangularButton(BrightnessDown, &menus[BRIGHTNESS_MENU], &BrightnessUp, 0, &SSD1289_Display, 0, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, OnBrightnessDown);
RectangularButton(BrightnessBack, &menus[BRIGHTNESS_MENU], &BrightnessDown, 0, &SSD1289_Display, 0, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN);

static void
BrightnessChange(int change)
{
	brightness += change;

	if (brightness > 5)
	{
		brightness = 5;
	}
	else if (brightness < 1)
	{
		brightness = 1;
	}

	SSD1289_Backlight_Set(brightness);

	sprintf(brightness_text, "%d", brightness);
	Repaint((tWidget *)&brightness_label);
}

static void
OnBrightnessUp(tWidget *psWidget)
{
	BrightnessChange(1);
}

static void
OnBrightnessDown(tWidget *psWidget)
{
	BrightnessChange(-1);
}
