/*
 * trigger_menu.c
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#include "graphics_thread.h"
#include "adc.h"
#include "trigger.h"

static void OnTriggerType(tWidget *psWidget);
static void OnTriggerMode(tWidget *psWidget);
static void OnTriggerForce(tWidget *psWidget);
static void OnTriggerArm(tWidget *psWidget);
static void OnTriggerThresholdIncrease(tWidget *psWidget);
static void OnTriggerThresholdDecrease(tWidget *psWidget);
static void OnTriggerChannel(tWidget *psWidget);

static RectangularButton(TriggerChannel, &menus[TRIGGER_ARM_MENU], 0, 0, &SSD1289_Display, 220, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE , ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, NULL, 0, 0, 0, 0, OnTriggerChannel);
static RectangularButton(TriggerModeButton, &menus[TRIGGER_ARM_MENU], &TriggerChannel, 0, &SSD1289_Display, 110, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, NULL, 0, 0, 0, 0, OnTriggerMode);
static RectangularButton(TriggerToThreshold, &menus[TRIGGER_ARM_MENU], &TriggerModeButton, 0, &SSD1289_Display, 0, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "More", 0, 0, 0, 0, OnTRIGGER_THRESHOLD);
static RectangularButton(TriggerForce, &menus[TRIGGER_ARM_MENU], &TriggerToThreshold, 0, &SSD1289_Display, 220, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrRed, ClrLightGreen, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Force", 0, 0, 0, 0, OnTriggerForce);
static RectangularButton(TriggerArm, &menus[TRIGGER_ARM_MENU], &TriggerForce, 0, &SSD1289_Display, 110, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrRed, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Arm", 0, 0, 0, 0, OnTriggerArm);
RectangularButton(TriggerArmBack, &menus[TRIGGER_ARM_MENU], &TriggerArm, 0, &SSD1289_Display, 0, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN);

static Canvas(TriggerThresholdLabel1, &menus[TRIGGER_THRESHOLD_MENU], 0, 0, &SSD1289_Display, 170, 50, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static Canvas(TriggerThresholdLabel2, &menus[TRIGGER_THRESHOLD_MENU], &TriggerThresholdLabel1, 0, &SSD1289_Display, 170, 70, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static RectangularButton(TriggerTypeButton, &menus[TRIGGER_THRESHOLD_MENU], &TriggerThresholdLabel2, 0, &SSD1289_Display, 110, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE , ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, NULL, 0, 0, 0, 0, OnTriggerType);
static RectangularButton(TriggerToArm, &menus[TRIGGER_THRESHOLD_MENU], &TriggerTypeButton, 0, &SSD1289_Display, 0, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "More", 0, 0, 0, 0, OnTRIGGER_ARM);
static RectangularButton(TriggerThresholdDecrease, &menus[TRIGGER_THRESHOLD_MENU], &TriggerToArm, 0, &SSD1289_Display, 110, 30,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, OnTriggerThresholdDecrease);
static RectangularButton(TriggerThresholdIncrease, &menus[TRIGGER_THRESHOLD_MENU], &TriggerThresholdDecrease, 0, &SSD1289_Display, 260, 30,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, OnTriggerThresholdIncrease);
RectangularButton(TriggerThresholdBack, &menus[TRIGGER_THRESHOLD_MENU], &TriggerThresholdIncrease, 0, &SSD1289_Display, 0, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN);

static void
OnTriggerType(tWidget *psWidget)
{
	TriggerSetType((TriggerType)((TriggerGetType() + 1) % 3));
}

static void
OnTriggerMode(tWidget *psWidget)
{
	TriggerSetMode((TriggerMode)((TriggerGetMode() + 1) % 3));
}

static void
OnTriggerForce(tWidget *psWidget)
{
	ForceTrigger();
}

static void
OnTriggerArm(tWidget *psWidget)
{
	if (TriggerGetState() == TRIGGER_STATE_STOP)
	{
		TriggerSetState(TRIGGER_STATE_ARMED);
	}
}

static void
OnTriggerThresholdIncrease(tWidget *psWidget)
{
	TriggerSetThreshold(TriggerGetThreshold() + 100000);
}

static void
OnTriggerThresholdDecrease(tWidget *psWidget)
{
	TriggerSetThreshold(TriggerGetThreshold() - 100000);
}

static void
OnTriggerChannel(tWidget *psWidget)
{
	TriggerSetChannel((Channel)!TriggerGetChannel());
}

void
TriggerSetThresholdLevelText(const char* line1, const char* line2)
{
	CanvasTextSet(&TriggerThresholdLabel1, line1);
	CanvasTextSet(&TriggerThresholdLabel2, line2);

	if (current_menu == TRIGGER_THRESHOLD_MENU)
	{
		Repaint((tWidget *)&TriggerThresholdLabel1);
		Repaint((tWidget *)&TriggerThresholdLabel2);
	}
}

void
TriggerSetModeText(const char* text)
{
	PushButtonTextSet(&TriggerModeButton, text);

	if (current_menu == TRIGGER_ARM_MENU)
	{
		Repaint((tWidget *)&TriggerModeButton);
	}
}

void
TriggerSetTypeText(const char* text)
{
	PushButtonTextSet(&TriggerTypeButton, text);

	if (current_menu == TRIGGER_THRESHOLD_MENU)
	{
		Repaint((tWidget *)&TriggerTypeButton);
	}
}

void
TriggerSetChannelText(const char* text)
{
	PushButtonTextSet(&TriggerChannel, text);

	if (current_menu == TRIGGER_ARM_MENU)
	{
		Repaint((tWidget *)&TriggerChannel);
	}
}
