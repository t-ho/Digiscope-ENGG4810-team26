/*
 * trigger_menu.c
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#include "graphics_thread.h"
#include "adc.h"

static void OnTriggerThreshold(tWidget *psWidget);
static void OnTriggerType(tWidget *psWidget);
static void OnTriggerMode(tWidget *psWidget);
static void OnTriggerForce(tWidget *psWidget);
static void OnTriggerArm(tWidget *psWidget);


static RectangularButton(TriggerThreshold, &menus[TRIGGER_MENU], 0, 0, &SSD1289_Display, 220, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Threshold", 0, 0, 0, 0, OnTriggerThreshold);
static RectangularButton(TriggerType, &menus[TRIGGER_MENU], &TriggerThreshold, 0, &SSD1289_Display, 110, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Type", 0, 0, 0, 0, OnTriggerType);
static RectangularButton(TriggerMode, &menus[TRIGGER_MENU], &TriggerType, 0, &SSD1289_Display, 0, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Mode", 0, 0, 0, 0, OnTriggerMode);
static RectangularButton(TriggerForce, &menus[TRIGGER_MENU], &TriggerMode, 0, &SSD1289_Display, 220, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrRed, ClrGreen, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Force", 0, 0, 0, 0, OnTriggerForce);
static RectangularButton(TriggerArm, &menus[TRIGGER_MENU], &TriggerForce, 0, &SSD1289_Display, 110, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrRed, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Arm", 0, 0, 0, 0, OnTriggerArm);
RectangularButton(TriggerBack, &menus[TRIGGER_MENU], &TriggerArm, 0, &SSD1289_Display, 0, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN);

static void
OnTriggerThreshold(tWidget *psWidget)
{
}

static void
OnTriggerType(tWidget *psWidget)
{
}

static void
OnTriggerMode(tWidget *psWidget)
{
}

static void
OnTriggerForce(tWidget *psWidget)
{
	ForceTrigger();
}

static void
OnTriggerArm(tWidget *psWidget)
{
}
