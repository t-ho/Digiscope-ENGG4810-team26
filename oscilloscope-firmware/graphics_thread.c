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

#include "grlib/grlib.h"
#include "grlib/widget.h"
#include "grlib/canvas.h"
#include "grlib/pushbutton.h"

#include "common.h"
#include "net.h"
#include "adc.h"
#include "drivers/SSD1289_driver.h"

#define HDIV_MIN 1
#define HDIV_MAX 1000000
#define VDIV_MIN 20000
#define VDIV_MAX 2000000

static const char * const menu_titles[] = { "Team 26 Oscilloscope", "Range", "Trigger", "Wave Generator", "Brightness" };

static uint8_t brightness = 5;

tCanvasWidget g_sTitle;

tPushButtonWidget main_menu_buttons[];
tPushButtonWidget range_menu_buttons[];
tPushButtonWidget trigger_menu_buttons[];
tPushButtonWidget wavegen_menu_buttons[];
tPushButtonWidget brightness_menu_buttons[];

#define MENU_NAV(X) void On##X(tWidget *psWidget) { \
	WidgetRemove((tWidget *)&menus[current_menu]); \
	current_menu = X##_MENU; \
	WidgetAdd(WIDGET_ROOT, (tWidget *)&menus[current_menu]); \
    CanvasTextSet(&g_sTitle, menu_titles[current_menu]); \
    WidgetPaint(WIDGET_ROOT); \
}

#define MAIN_MENU 	0
#define RANGE_MENU 	1
#define TRIGGER_MENU 2
#define WAVEGEN_MENU 3
#define BRIGHTNESS_MENU 4

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
};

MENU_NAV(MAIN)
MENU_NAV(RANGE)
MENU_NAV(TRIGGER)
MENU_NAV(WAVEGEN)
MENU_NAV(BRIGHTNESS)

void OnPrevious(tWidget *psWidget);
void OnNext(tWidget *psWidget);
void BrightnessUp(tWidget *psWidget);
void BrightnessDown(tWidget *psWidget);
void HorRangeUp(tWidget *psWidget);
void VertRangeUp(tWidget *psWidget);
void HorRangeDown(tWidget *psWidget);
void VertRangeDown(tWidget *psWidget);
void ForceTrigger(tWidget *psWidget);

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
						  ClrWhite, &g_sFontCm18b, "Range", 0, 0, 0, 0, OnRANGE),
		RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 2, 0, &SSD1289_Display, 110, 30,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Trigger", 0, 0, 0, 0, OnTRIGGER),
		RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 3, 0, &SSD1289_Display, 220, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Wave Gen", 0, 0, 0, 0, OnWAVEGEN),
		RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 4, 0, &SSD1289_Display, 0, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Brightness", 0, 0, 0, 0, OnBRIGHTNESS),
		RectangularButtonStruct(&menus[MAIN_MENU], 0, 0, &SSD1289_Display, 110, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Sleep", 0, 0, 0, 0, OnPrevious),
};

char vert_div_text1[] = "500";
char vert_div_text2[] = "mV/div";
char hor_div_text1[] = "500";
char hor_div_text2[] = "us/div";

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
						  ClrWhite, &g_sFontCm18b, "AC", 0, 0, 0, 0, OnPrevious),
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
						  ClrWhite, &g_sFontCm18b, "Force", 0, 0, 0, 0, ForceTrigger),
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

tPushButtonWidget wavegen_menu_buttons[] =
{
		RectangularButtonStruct(&menus[WAVEGEN_MENU], wavegen_menu_buttons + 1, 0, &SSD1289_Display, 0, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN),
		RectangularButtonStruct(&menus[WAVEGEN_MENU], wavegen_menu_buttons + 2, 0, &SSD1289_Display, 110, 30,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "On/Off", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[WAVEGEN_MENU], wavegen_menu_buttons + 3, 0, &SSD1289_Display, 220, 30,
		                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Type", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[WAVEGEN_MENU], wavegen_menu_buttons + 4, 0, &SSD1289_Display, 0, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Vpp", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[WAVEGEN_MENU], wavegen_menu_buttons + 5, 0, &SSD1289_Display, 110, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Offset", 0, 0, 0, 0, OnPrevious),
		RectangularButtonStruct(&menus[WAVEGEN_MENU], 0, 0, &SSD1289_Display, 220, 120,
						  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
						  ClrWhite, &g_sFontCm18b, "Freq", 0, 0, 0, 0, OnPrevious),
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

static uint32_t vdiv_val = 500;
static uint32_t hdiv_val = 500;

void VertRangeChange(uint32_t newVal)
{
	if (newVal > VDIV_MAX)
	{
		vdiv_val = VDIV_MAX;
	}
	else if (newVal < VDIV_MIN)
	{
		vdiv_val = VDIV_MIN;
	}
	else
	{
		vdiv_val = newVal;
	}

	SI_Micro_Print(vert_div_text1, vert_div_text2, vdiv_val, "V/div");

    WidgetPaint((tWidget *)&vert_div_label1);
    WidgetPaint((tWidget *)&vert_div_label2);
}

void
VertRangeUp(tWidget *psWidget)
{
	VertRangeChange(Standard_Step(vdiv_val, 1));
}

void
VertRangeDown(tWidget *psWidget)
{
	VertRangeChange(Standard_Step(vdiv_val, -1));
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
	}

	SI_Micro_Print(hor_div_text1, hor_div_text2, hdiv_val, "s/div");

    WidgetPaint((tWidget *)&hor_div_label1);
    WidgetPaint((tWidget *)&hor_div_label2);
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
OnPrevious(tWidget *psWidget)
{
}

void
ForceTrigger(tWidget *psWidget)
{
    static NetPacket np;
    np.data = (char *) adc_buffer;
    np.len = 2 * ADC_BUF_SIZE;

    NetSend(&np);
}

void
screenDemo(UArg arg0, UArg arg1)
{
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

    Semaphore_post(widget_message_h);

    while (1)
    {
        if (Semaphore_pend(widget_message_h, 1000))
        {
        	WidgetMessageQueueProcess();
        }

        if (Semaphore_pend(ip_update_h, 0))
        {
            sprintf(ipaddrstring, "%d.%d.%d.%d %s",
                    (uint8_t)(IpAddrVal>>24)&0xFF, (uint8_t)(IpAddrVal>>16)&0xFF,
                    (uint8_t)(IpAddrVal>>8)&0xFF, (uint8_t)IpAddrVal&0xFF,
					Semaphore_getCount(clients_connected_h)?"Connected":"No Client");
            WidgetPaint((tWidget *)&g_sConnStatus);
            Semaphore_post(widget_message_h);
        }
    }
}
