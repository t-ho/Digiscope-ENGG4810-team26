/*
 * graphics_thread.c
 *
 *  Created on: 28 Apr 2016
 *      Author: Ryan
 */

#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>

#include <ti/sysbios/knl/Task.h>
#include <ti/sysbios/knl/Semaphore.h>
#include <ti/sysbios/knl/Mailbox.h>

#include "grlib/grlib.h"
#include "grlib/widget.h"
#include "grlib/canvas.h"
#include "grlib/pushbutton.h"

#include "common.h"
#include "net.h"
#include "adc.h"
#include "overvolt.h"
#include "drivers/SSD1289_driver.h"

#define HDIV_MIN 1
#define HDIV_MAX 1000000
#define VDIV_MIN 20000
#define VDIV_MAX 2000000

#define CHANNEL_A 0
#define CHANNEL_B 1
#define COUPLING_DC 0
#define COUPLING_AC 1

static const char * const menu_titles[] = { "Team 26 Oscilloscope", "Channel X", "Trigger", "Wave Generator", "Brightness", "Overvoltage Warning!" };

static uint8_t brightness = 5;

static uint8_t current_channel = 0;
static uint8_t current_coupling[] = { COUPLING_DC, COUPLING_DC };

tCanvasWidget g_sTitle;

tPushButtonWidget main_menu_buttons[];
tPushButtonWidget range_menu_buttons[];
tPushButtonWidget trigger_menu_buttons[];
tPushButtonWidget wavegen_menu_buttons[];
tPushButtonWidget brightness_menu_buttons[];
tPushButtonWidget overvoltage_menu_buttons[];

#define MENU_NAV(X) void On##X(tWidget *psWidget) { \
	WidgetRemove((tWidget *)&menus[current_menu]); \
	current_menu = X##_MENU; \
	WidgetAdd(WIDGET_ROOT, (tWidget *)&menus[current_menu]); \
    CanvasTextSet(&g_sTitle, menu_titles[current_menu]); \
    WidgetPaint(WIDGET_ROOT); \
}

enum menus
{
	MAIN_MENU,
	RANGE_MENU,
	TRIGGER_MENU,
	WAVEGEN_MENU,
	BRIGHTNESS_MENU,
	OVERVOLTAGE_MENU
};

static uint8_t current_menu = MAIN_MENU;

tCanvasWidget menus[] =
{
	// Main menu
    CanvasStruct(0, 0, &main_menu_buttons, &SSD1289_Display, 0, 24,
                 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Range menu
    CanvasStruct(0, 0, &range_menu_buttons, &SSD1289_Display, 0, 24,
                 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Trigger menu
	CanvasStruct(0, 0, &trigger_menu_buttons, &SSD1289_Display, 0, 24,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Wavegen menu
	CanvasStruct(0, 0, &wavegen_menu_buttons, &SSD1289_Display, 0, 24,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Brightness menu
	CanvasStruct(0, 0, &brightness_menu_buttons, &SSD1289_Display, 0, 24,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Overvoltage menu
	CanvasStruct(0, 0, &overvoltage_menu_buttons, &SSD1289_Display, 0, 24,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
};

MENU_NAV(MAIN)
MENU_NAV(TRIGGER)
MENU_NAV(WAVEGEN)
MENU_NAV(BRIGHTNESS)
MENU_NAV(OVERVOLTAGE)

char coupling_text[] = "DC";

void OnRange_A(tWidget *psWidget)
{
	current_channel = CHANNEL_A;
	WidgetRemove((tWidget *)&menus[current_menu]);
	current_menu = RANGE_MENU;
	WidgetAdd(WIDGET_ROOT, (tWidget *)&menus[current_menu]);
    CanvasTextSet(&g_sTitle, "Channel A");
	coupling_text[0] = ((current_coupling[current_channel] == COUPLING_AC) ? 'A':'D');
    WidgetPaint(WIDGET_ROOT);
}

void OnRange_B(tWidget *psWidget)
{
	current_channel = CHANNEL_B;
	WidgetRemove((tWidget *)&menus[current_menu]);
	current_menu = RANGE_MENU;
	WidgetAdd(WIDGET_ROOT, (tWidget *)&menus[current_menu]);
    CanvasTextSet(&g_sTitle, "Channel B");
	coupling_text[0] = ((current_coupling[current_channel] == COUPLING_AC) ? 'A':'D');
    WidgetPaint(WIDGET_ROOT);
}

void OnWaveGenEnable(tWidget *psWidget);
void OnWaveGenFreq(tWidget *psWidget);
void OnCoupling(tWidget *psWidget);
void EnterSleep(tWidget *psWidget);
void OnPrevious(tWidget *psWidget);
void OnNext(tWidget *psWidget);
void BrightnessUp(tWidget *psWidget);
void BrightnessDown(tWidget *psWidget);
void HorRangeUp(tWidget *psWidget);
void VertRangeUp(tWidget *psWidget);
void HorRangeDown(tWidget *psWidget);
void VertRangeDown(tWidget *psWidget);
void ForceTriggerPress(tWidget *psWidget);
void OverVoltageAcknowledge(tWidget *psWidget);

Canvas(g_sTitle, 0, 0, 0, &SSD1289_Display, 50, 2, 220, 20,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm20, 0, 0, 0);

static char ipaddrstring[32];
Canvas(g_sConnStatus, 0, 0, 0, &SSD1289_Display, 50, 220, 220, 20,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm20, ipaddrstring, 0, 0);

tPushButtonWidget main_menu_buttons[] =
{
		RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 1, 0, &SSD1289_Display, 0, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Channel A", 0, 0, 0, 0, OnRange_A),
		RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 2, 0, &SSD1289_Display, 110, 30,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Channel B", 0, 0, 0, 0, OnRange_B),
		RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 3, 0, &SSD1289_Display, 220, 30,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Trigger", 0, 0, 0, 0, OnTRIGGER),
		RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 4, 0, &SSD1289_Display, 0, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Brightness", 0, 0, 0, 0, OnBRIGHTNESS),
		RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 5, 0, &SSD1289_Display, 110, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Sleep", 0, 0, 0, 0, EnterSleep),
		RectangularButtonStruct(&menus[MAIN_MENU], 0, 0, &SSD1289_Display, 220, 120,
					  	  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Wave Gen", 0, 0, 0, 0, OnWAVEGEN),
};

static char vert_div_text1[] = "500";
static char vert_div_text2[] = "mV/div";
static char hor_div_text1[] = "500";
static char hor_div_text2[] = "us/div";

Canvas(hor_div_label1, &menus[RANGE_MENU], 0, 0, &SSD1289_Display, 170, 140, 90, 20,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm20, hor_div_text1, 0, 0);
Canvas(hor_div_label2, &menus[RANGE_MENU], &hor_div_label1, 0, &SSD1289_Display, 170, 160, 90, 20,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm20, hor_div_text2, 0, 0);
Canvas(vert_div_label1, &menus[RANGE_MENU], &hor_div_label2, 0, &SSD1289_Display, 170, 50, 90, 20,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm20, vert_div_text1, 0, 0);
Canvas(vert_div_label2, &menus[RANGE_MENU], &vert_div_label1, 0, &SSD1289_Display, 170, 70, 90, 20,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm20, vert_div_text2, 0, 0);

tPushButtonWidget range_menu_buttons[] =
{
		RectangularButtonStruct(&menus[RANGE_MENU], range_menu_buttons + 1, 0, &SSD1289_Display, 0, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN),
		RectangularButtonStruct(&menus[RANGE_MENU], range_menu_buttons + 2, 0, &SSD1289_Display, 0, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, coupling_text, 0, 0, 0, 0, OnCoupling),
		RectangularButtonStruct(&menus[RANGE_MENU], range_menu_buttons + 3, 0, &SSD1289_Display, 110, 30,
		                  60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, VertRangeDown),
		RectangularButtonStruct(&menus[RANGE_MENU], range_menu_buttons + 4, 0, &SSD1289_Display, 260, 30,
						  60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, VertRangeUp),
		RectangularButtonStruct(&menus[RANGE_MENU], range_menu_buttons + 5, 0, &SSD1289_Display, 110, 120,
						  60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, HorRangeDown),
		RectangularButtonStruct(&menus[RANGE_MENU], &vert_div_label2, 0, &SSD1289_Display, 260, 120,
						  60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, HorRangeUp),
};

tPushButtonWidget trigger_menu_buttons[] =
{
		RectangularButtonStruct(&menus[TRIGGER_MENU], trigger_menu_buttons + 1, 0, &SSD1289_Display, 0, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN),
		RectangularButtonStruct(&menus[TRIGGER_MENU], trigger_menu_buttons + 2, 0, &SSD1289_Display, 110, 30,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrRed, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Arm", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[TRIGGER_MENU], trigger_menu_buttons + 3, 0, &SSD1289_Display, 220, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrRed, ClrGreen, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Force", 0, 0, 0, 0, ForceTriggerPress),
		RectangularButtonStruct(&menus[TRIGGER_MENU], trigger_menu_buttons + 4, 0, &SSD1289_Display, 0, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Mode", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[TRIGGER_MENU], trigger_menu_buttons + 5, 0, &SSD1289_Display, 110, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Type", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[TRIGGER_MENU], 0, 0, &SSD1289_Display, 220, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Threshold", 0, 0, 0, 0, OnPrevious),
};

RectangularButton(WaveGenOnOff, &menus[WAVEGEN_MENU], 0, 0, &SSD1289_Display, 110, 30,
				  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
				  ClrWhite, &g_sFontCm18b, "On/Off", 0, 0, 0, 0, OnWaveGenEnable);
RectangularButton(WaveGenFreq, &menus[WAVEGEN_MENU], &WaveGenOnOff, 0, &SSD1289_Display, 220, 120,
				  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
				  ClrWhite, &g_sFontCm18b, "Freq", 0, 0, 0, 0, OnWaveGenFreq);

tPushButtonWidget wavegen_menu_buttons[] =
{
		RectangularButtonStruct(&menus[WAVEGEN_MENU], wavegen_menu_buttons + 1, 0, &SSD1289_Display, 0, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN),
		RectangularButtonStruct(&menus[WAVEGEN_MENU], wavegen_menu_buttons + 2, 0, &SSD1289_Display, 220, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Type", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[WAVEGEN_MENU], wavegen_menu_buttons + 3, 0, &SSD1289_Display, 0, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Vpp", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[WAVEGEN_MENU], &WaveGenFreq, 0, &SSD1289_Display, 110, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Offset", 0, 0, 0, 0, OnPrevious),
};

char brightness_text[] = "5";
Canvas(brightness_label, &menus[BRIGHTNESS_MENU], 0, 0, &SSD1289_Display, 150, 150, 20, 20,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm20, brightness_text, 0, 0);

tPushButtonWidget brightness_menu_buttons[] =
{
		RectangularButtonStruct(&menus[BRIGHTNESS_MENU], brightness_menu_buttons + 1, 0, &SSD1289_Display, 0, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN),
		RectangularButtonStruct(&menus[BRIGHTNESS_MENU], brightness_menu_buttons + 2, 0, &SSD1289_Display, 0, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, BrightnessDown),
		RectangularButtonStruct(&menus[BRIGHTNESS_MENU], &brightness_label, 0, &SSD1289_Display, 220, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, BrightnessUp),
};

char overvoltage_text[] = "Overvoltage on channel X!";
Canvas(overvoltage_label, &menus[OVERVOLTAGE_MENU], 0, 0, &SSD1289_Display, 10, 50, 300, 24,
       CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
       &g_sFontCm22b, overvoltage_text, 0, 0);

tPushButtonWidget overvoltage_menu_buttons[] =
{
		RectangularButtonStruct(&menus[OVERVOLTAGE_MENU], &overvoltage_label, 0, &SSD1289_Display, 10, 90,
		                  300, 100, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrRed, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm24b, "Acknowledge", 0, 0, 0, 0, OverVoltageAcknowledge),
};

void
Repaint(tWidget *psWidget)
{
	static Command cmd;
	cmd.type = _COMMAND_REPAINT;
	cmd.args[0] = (uint32_t)psWidget;

	Mailbox_post(GraphicsMailbox, &cmd, 0);
}

void
SetCoupling(uint8_t channel, uint8_t coupling)
{
	current_coupling[channel] = coupling;

	if (current_channel == channel && current_menu == RANGE_MENU)
	{
		coupling_text[0] = ((coupling == COUPLING_AC) ? 'A':'D');
	    WidgetPaint(WIDGET_ROOT);
	}

	Command cmd;
	cmd.type = (channel == CHANNEL_A) ? COMMAND_COUPLING_A : COMMAND_COUPLING_B;
	cmd.args[0] = coupling;
	NetSend(&cmd, 0);
}

void
OnCoupling(tWidget *psWidget)
{
	SetCoupling(current_channel, (current_coupling[current_channel] == COUPLING_AC) ? COUPLING_DC : COUPLING_AC);
}

void
OverVoltageAcknowledge(tWidget *psWidget)
{
	OverVoltageReenable();
	OnMAIN(NULL);
}

void
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
    WidgetPaint((tWidget *)&brightness_label);
}

void
BrightnessUp(tWidget *psWidget)
{
	BrightnessChange(1);
}

void
BrightnessDown(tWidget *psWidget)
{
	BrightnessChange(-1);
}

static uint32_t vdiv_vals[2] = { 500, 500 };
static uint32_t hdiv_val = 500;

void VertRangeChange(uint32_t newVal, uint8_t channel)
{
	if (newVal > VDIV_MAX)
	{
		vdiv_vals[channel] = VDIV_MAX;
	}
	else if (newVal < VDIV_MIN)
	{
		vdiv_vals[channel] = VDIV_MIN;
	}
	else
	{
		vdiv_vals[channel] = newVal;

		Command cmd;
		cmd.type = channel ? COMMAND_VERTICAL_RANGE_B : COMMAND_VERTICAL_RANGE_A;
		cmd.args[0] = newVal;
		cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
		NetSend(&cmd, 0);
	}

	SI_Micro_Print(vert_div_text1, vert_div_text2, vdiv_vals[channel], "V/div");

	if (current_menu == RANGE_MENU)
	{
		WidgetPaint((tWidget *)&vert_div_label1);
		WidgetPaint((tWidget *)&vert_div_label2);
	}
}

void
WaveGenOnOffSetText(const char* text)
{
	PushButtonTextSet(&WaveGenOnOff, text);

	if (current_menu == WAVEGEN_MENU)
	{
		Repaint((tWidget *)&WaveGenOnOff);
	}
}

void
WaveGenFreqSetText(const char* text)
{
	PushButtonTextSet(&WaveGenFreq, text);

	if (current_menu == WAVEGEN_MENU)
	{
		Repaint((tWidget *)&WaveGenFreq);
	}
}

void
OnWaveGenEnable(tWidget *psWidget)
{
	WaveGenEnableSet(!WaveGenEnableGet());
}

void
OnWaveGenFreq(tWidget *psWidget)
{
	uint32_t newfreq = Standard_Step(WaveGenGetFreq(), 1);

	if (newfreq > 2000)
	{
		newfreq = 100;
	}

	WaveGenSetFreq(newfreq);
}

void
VertRangeUp(tWidget *psWidget)
{
	VertRangeChange(Standard_Step(vdiv_vals[0], 1), current_channel);
}

void
VertRangeDown(tWidget *psWidget)
{
	VertRangeChange(Standard_Step(vdiv_vals[0], -1), current_channel);
}

void HorRangeChange(uint32_t newVal)
{
	if (newVal > HDIV_MAX)
	{
		hdiv_val = HDIV_MAX;
	}
	else if (newVal < HDIV_MIN)
	{
		hdiv_val = HDIV_MIN;
	}
	else
	{
		hdiv_val = newVal;
		Command cmd;
		cmd.type = COMMAND_HORIZONTAL_RANGE;
		cmd.args[0] = newVal;
		cmd.is_confirmation = COMMAND_IS_CONFIRMATION;
		NetSend(&cmd, 0);
	}

	SI_Micro_Print(hor_div_text1, hor_div_text2, hdiv_val, "s/div");

	if (current_menu == RANGE_MENU)
	{
		WidgetPaint((tWidget *)&hor_div_label1);
    	WidgetPaint((tWidget *)&hor_div_label2);
	}
}

void
HorRangeUp(tWidget *psWidget)
{
	HorRangeChange(Standard_Step(hdiv_val, 1));
}

void
HorRangeDown(tWidget *psWidget)
{
	HorRangeChange(Standard_Step(hdiv_val, -1));
}

void
EnterSleep(tWidget *psWidget)
{
	SSD1289_Set_Backlight_On(false);
}

void
OnPrevious(tWidget *psWidget)
{
}

void
ForceTriggerPress(tWidget *psWidget)
{
	ForceTrigger();
}

void
screenDemo(UArg arg0, UArg arg1)
{
	uint32_t ipaddr = 0;

    tContext sContext;
    tRectangle sRect;

    GrContextInit(&sContext, &SSD1289_Display);

    // Fill the top 24 rows of the screen with blue to create the banner.
    sRect.i16XMin = 0;
    sRect.i16YMin = 0;
    sRect.i16XMax = GrContextDpyWidthGet(&sContext) - 1;
    sRect.i16YMax = 23;

    // GrContextForegroundSet(&sContext, ClrDarkBlue);
    // GrRectFill(&sContext, &sRect);
    // Put a white box around the banner.
    GrContextForegroundSet(&sContext, ClrWhite);
    GrRectDraw(&sContext, &sRect);
    // Put the application name in the middle of the banner.
//    GrContextFontSet(&sContext, &g_sFontCm20);
//    GrStringDrawCentered(&sContext, "Team 26 Oscilloscope", -1, GrContextDpyWidthGet(&sContext) / 2, 8, 0);

//    WidgetAdd(WIDGET_ROOT, (tWidget *)&g_sPrevious);
    WidgetAdd(WIDGET_ROOT, (tWidget *)&g_sTitle);
    WidgetAdd(WIDGET_ROOT, (tWidget *)&g_sConnStatus);
//    WidgetAdd(WIDGET_ROOT, (tWidget *)&g_sNext);
    WidgetAdd(WIDGET_ROOT, (tWidget *)&menus[MAIN_MENU]);

    CanvasTextSet(&g_sTitle, menu_titles[MAIN_MENU]);
    sprintf(ipaddrstring, "No connection...");

    WidgetPaint(WIDGET_ROOT);

	Command cmd;
	cmd.type = _COMMAND_UNKNOWN;

	Mailbox_post(GraphicsMailbox, &cmd, 0);

    while (1)
    {
    	if (Mailbox_pend(GraphicsMailbox, &cmd, BIOS_WAIT_FOREVER))
    	{
    		switch(cmd.type)
    		{

    		case _COMMAND_IP_UPDATE:
    			ipaddr = cmd.args[0];
    			/* Fallthrough */
    		case _COMMAND_CONN_UPDATE:
                sprintf(ipaddrstring, "%d.%d.%d.%d %s",
                        (uint8_t)(ipaddr>>24)&0xFF, (uint8_t)(ipaddr>>16)&0xFF,
                        (uint8_t)(ipaddr>>8)&0xFF, (uint8_t)ipaddr&0xFF,
    					Semaphore_getCount(clients_connected_h)?"Connected":"No Client");
                WidgetPaint((tWidget *)&g_sConnStatus);
    			break;
    		case _COMMAND_PTR_DOWN:
    			if (!SSD1289_Get_Backlight_On())
				{
    				break;
				}

    			WidgetPointerMessage(WIDGET_MSG_PTR_DOWN, cmd.args[0], cmd.args[1]);
    			break;
    		case _COMMAND_PTR_UP:
    			if (!SSD1289_Get_Backlight_On())
    			{
        			SSD1289_Set_Backlight_On(true);
    				break;
    			}

    			WidgetPointerMessage(WIDGET_MSG_PTR_UP, cmd.args[0], cmd.args[1]);
    			break;
    		case _COMMAND_OVERVOLTAGE:
    			SSD1289_Set_Backlight_On(true);
    			overvoltage_text[strlen(overvoltage_text) - 2] = cmd.args[0] + 1 + '0';
    			OnOVERVOLTAGE(NULL);
    			break;
    		case COMMAND_HORIZONTAL_RANGE:
    			HorRangeChange(cmd.args[0]);
    			break;
    		case COMMAND_VERTICAL_RANGE_A:
				VertRangeChange(cmd.args[0], 0);
				break;
    		case COMMAND_VERTICAL_RANGE_B:
				VertRangeChange(cmd.args[0], 1);
				break;
    		case COMMAND_TRIGGER_FORCE_A:
    		case COMMAND_TRIGGER_FORCE_B:
    			ForceTrigger();
    			break;
    		case COMMAND_COUPLING_A:
    			SetCoupling(CHANNEL_A, 1 ^ current_coupling[CHANNEL_A]);
    			break;
    		case COMMAND_COUPLING_B:
    			SetCoupling(CHANNEL_B, 1 ^ current_coupling[CHANNEL_A]);
    			break;
    		case _COMMAND_REPAINT:
    			WidgetPaint((tWidget *)cmd.args[0]);
    			break;
    		case _COMMAND_UNKNOWN:
    			/* Fallthrough */
    		default:
    			break;
    		}

        	WidgetMessageQueueProcess();
    	}
    }
}
