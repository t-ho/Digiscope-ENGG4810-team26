/*
 * graphics_thread.c
 *
 *  Created on: 28 Apr 2016
 *      Author: Ryan
 */

#include <command.h>
#include <ti/sysbios/knl/Task.h>
#include <ti/sysbios/knl/Semaphore.h>
#include <ti/sysbios/knl/Mailbox.h>

#include <xdc/runtime/Error.h>

#include "net_task.h"
#include "acquisition.h"
#include "overvolt.h"
#include "frontend.h"
#include "wavegen.h"
#include "trigger.h"

#include "graphics_thread.h"
#include "overvoltage_menu.h"
#include "range_menu.h"
#include "trigger_menu.h"

static const char * const menu_titles[] = { "Team 26 Oscilloscope", "Channel X", "Trigger - Force/Arm", "Trigger - Threshold", "Wave Generator", "Wave Generator", "Brightness", "Overvoltage Warning!" };

static tContext sContext;

tCanvasWidget g_sTitle;

tPushButtonWidget main_menu_buttons[];
extern tPushButtonWidget RangeBack;
extern tPushButtonWidget TriggerArmBack;
extern tPushButtonWidget TriggerThresholdBack;
extern tPushButtonWidget WaveGen1Back;
extern tPushButtonWidget WaveGen2Back;
extern tPushButtonWidget BrightnessBack;

static Mailbox_Handle GraphicsMailbox;

#define MENU_NAV(X) void On##X(tWidget *psWidget) { \
	WidgetRemove((tWidget *)&menus[current_menu]); \
	current_menu = X##_MENU; \
	WidgetAdd(WIDGET_ROOT, (tWidget *)&menus[current_menu]); \
    CanvasTextSet(&g_sTitle, menu_titles[current_menu]); \
    WidgetPaint(WIDGET_ROOT); \
}

uint8_t current_menu = MAIN_MENU;

tCanvasWidget menus[] =
{
	// Main menu
    CanvasStruct(0, 0, &main_menu_buttons, &SSD1289_Display, 0, 25,
                 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Range menu
    CanvasStruct(0, 0, &RangeBack, &SSD1289_Display, 0, 25,
                 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Trigger Arm menu
	CanvasStruct(0, 0, &TriggerArmBack, &SSD1289_Display, 0, 25,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Trigger Threshold menu
	CanvasStruct(0, 0, &TriggerThresholdBack, &SSD1289_Display, 0, 25,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Wavegen1 menu
	CanvasStruct(0, 0, &WaveGen1Back, &SSD1289_Display, 0, 25,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Wavegen2 menu
	CanvasStruct(0, 0, &WaveGen2Back, &SSD1289_Display, 0, 25,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Brightness menu
	CanvasStruct(0, 0, &BrightnessBack, &SSD1289_Display, 0, 25,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
	// Overvoltage menu
	CanvasStruct(0, 0, &OverVoltageAcknowledge, &SSD1289_Display, 0, 25,
				 320, 180, CANVAS_STYLE_FILL, ClrBlack, 0, 0, 0, 0, 0, 0),
};

MENU_NAV(MAIN)
MENU_NAV(TRIGGER_ARM)
MENU_NAV(TRIGGER_THRESHOLD)
MENU_NAV(WAVEGEN1)
MENU_NAV(WAVEGEN2)
static MENU_NAV(BRIGHTNESS)
MENU_NAV(OVERVOLTAGE)

void OnRange_A(tWidget *psWidget)
{
	WidgetRemove((tWidget *)&menus[current_menu]);
	current_menu = RANGE_MENU;
	SetDisplayChannel(CHANNEL_A);
    CanvasTextSet(&g_sTitle, "Channel A");
	WidgetAdd(WIDGET_ROOT, (tWidget *)&menus[current_menu]);
    WidgetPaint(WIDGET_ROOT);
}

void OnRange_B(tWidget *psWidget)
{
	WidgetRemove((tWidget *)&menus[current_menu]);
	current_menu = RANGE_MENU;
	SetDisplayChannel(CHANNEL_B);
	CanvasTextSet(&g_sTitle, "Channel B");
	WidgetAdd(WIDGET_ROOT, (tWidget *)&menus[current_menu]);
    WidgetPaint(WIDGET_ROOT);
}

void EnterSleep(tWidget *psWidget);

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
		ClrWhite, &g_sFontCm18b, "Trigger", 0, 0, 0, 0, OnTRIGGER_ARM),
	RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 4, 0, &SSD1289_Display, 0, 120,
		100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
		ClrWhite, &g_sFontCm18b, "Brightness", 0, 0, 0, 0, OnBRIGHTNESS),
	RectangularButtonStruct(&menus[MAIN_MENU], main_menu_buttons + 5, 0, &SSD1289_Display, 110, 120,
		100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
		ClrWhite, &g_sFontCm18b, "Sleep", 0, 0, 0, 0, EnterSleep),
	RectangularButtonStruct(&menus[MAIN_MENU], 0, 0, &SSD1289_Display, 220, 120,
		100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrBlue, ClrYellow, ClrWhite,
		ClrWhite, &g_sFontCm18b, "Wave Gen", 0, 0, 0, 0, OnWAVEGEN1),
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
EnterSleep(tWidget *psWidget)
{
	SSD1289_Set_Backlight_On(false);
}

uint32_t
Standard_Step(uint32_t val, int8_t dir)
{
	// 0 is invalid input
	if (val == 0) return 0;

	uint8_t mag = 0;

	while (val > 10)
	{
		val /= 10;
		mag++;
	}

	if (dir < 0)
	{
		if (val > 5)
		{
			val = 5;
		}
		else if (val > 2)
		{
			val = 2;
		}
		else if (val > 1)
		{
			val = 1;
		}
		else if (val == 1 && mag > 0)
		{
			val = 5;
			mag--;
		}
	}
	else
	{
		if (val < 2)
		{
			val = 2;
		}
		else if (val < 5)
		{
			val = 5;
		}
		else if (val < 10)
		{
			val = 10;
		}
		else
		{
			val = 2;
			mag++;
		}
	}

	while (mag > 0)
	{
		mag--;
		val *= 10;
	}

	return val;
}

static void
DrawTriggerIndicator(TriggerState state)
{
	static uint8_t arrow[] =
	{
		IMAGE_FMT_1BPP_UNCOMP, 16, 0, 16, 0,

		0b00000000, 0b01000000,
		0b00000000, 0b11100000,
		0b00000001, 0b11110000,
		0b00000011, 0b11111000,
		0b00000111, 0b11111100,
		0b00001111, 0b11111110,
		0b00011111, 0b11111111,
		0b00000001, 0b11110000,
		0b00000001, 0b11110000,
		0b00000001, 0b11110000,
		0b00000001, 0b11110000,
		0b00000001, 0b11110000,
		0b00000001, 0b11110000,
		0b00000001, 0b11110000,
		0b00000001, 0b11110000,
		0b00000001, 0b11110000
	};

    static uint16_t clrSquare[] = { 298, 3, 316, 21 };
    static uint16_t stopSquare[] = { 300, 5, 314, 19 };

    GrContextForegroundSet(&sContext, ClrBlack);
    GrRectFill(&sContext, (tRectangle*)&clrSquare);

    switch (state)
    {
    case TRIGGER_STATE_ARMED:
        GrContextForegroundSet(&sContext, ClrYellow);
        GrCircleFill(&sContext, 307, 12, 9);
        break;
    case TRIGGER_STATE_TRIGGERED:
        GrContextForegroundSet(&sContext, ClrLightGreen);
        GrImageDraw(&sContext, arrow, 299, 5);
    	break;
    case TRIGGER_STATE_STOP:
    default:
        GrContextForegroundSet(&sContext, ClrRed);
        GrRectFill(&sContext, (tRectangle*)&stopSquare);
    	break;
    }
}

void
UISend(Command *cmd, uint32_t timeout)
{
	Mailbox_post(GraphicsMailbox, cmd, timeout);
}

void
UI_Task(UArg arg0, UArg arg1)
{
	uint32_t ipaddr = 0;

    tRectangle sRect;

    GrContextInit(&sContext, &SSD1289_Display);

    // Fill the top 24 rows of the screen with blue to create the banner.
    sRect.i16XMin = 0;
    sRect.i16YMin = 0;
    sRect.i16XMax = GrContextDpyWidthGet(&sContext) - 1;
    sRect.i16YMax = 24;

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
	cmd.type = COMMAND_UNKNOWN;

	Mailbox_post(GraphicsMailbox, &cmd, 0);
	DrawTriggerIndicator((TriggerState) cmd.args[0]);

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
    					NetGetClients()?"Connected":"No Client");
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
    			OverVoltageWarn(cmd.args[0]);
    			break;
    		case COMMAND_HORIZONTAL_RANGE:
    			FrontEndSetHorDiv(cmd.args[0]);
    			break;
    		case COMMAND_VERTICAL_RANGE_A:
    			FrontEndSetVerDiv(CHANNEL_A, cmd.args[0]);
				break;
    		case COMMAND_VERTICAL_RANGE_B:
    			FrontEndSetVerDiv(CHANNEL_B, cmd.args[0]);
				break;
    		case COMMAND_TRIGGER_FORCE:
    			ForceTrigger();
    			break;
    		case COMMAND_TRIGGER_ARM:
    			if (TriggerGetState() != TRIGGER_STATE_ARMED && TriggerGetMode() == TRIGGER_MODE_SINGLE)
    			{
    				TriggerSetState(TRIGGER_STATE_ARMED);
    			}
    			break;
    		case COMMAND_TRIGGER_MODE:
    			TriggerSetMode((TriggerMode)cmd.args[0]);
    			break;
    		case COMMAND_TRIGGER_TYPE:
    			TriggerSetType((TriggerType)cmd.args[0]);
    			break;
    		case COMMAND_TRIGGER_THRESHOLD:
    			TriggerSetThreshold(cmd.args[0]);
    			break;
    		case COMMAND_NUM_SAMPLES:
    			TriggerSetNumSamples(cmd.args[0]);
    			break;
    		case COMMAND_COUPLING_A:
    			FrontEndSetCoupling(CHANNEL_A, (FrontendCoupling)cmd.args[0]);
    			break;
    		case COMMAND_COUPLING_B:
    			FrontEndSetCoupling(CHANNEL_B, (FrontendCoupling)cmd.args[0]);
    			break;
    		case COMMAND_SAMPLE_LENGTH:
    			TriggerSetSampleSize((SampleSize)cmd.args[0]);
    			break;
    		case COMMAND_FUNCTION_GEN_OUT:
    			WaveGenEnableSet(cmd.args[0]);
    			break;
    		case COMMAND_FUNCTION_GEN_FREQUENCY:
    			WaveGenSetFreq(cmd.args[0]);
    			break;
    		case COMMAND_FUNCTION_GEN_WAVE:
    			WaveGenSetShape((WaveType)cmd.args[0]);
    			break;
    		case COMMAND_FUNCTION_GEN_VOLTAGE:
    			WaveGenSetAmplitude(cmd.args[0]);
    			break;
    		case COMMAND_FUNCTION_GEN_OFFSET:
    			WaveGenSetOffset(cmd.args[0]);
    			break;
    		case _COMMAND_REPAINT:
    			WidgetPaint((tWidget *)cmd.args[0]);
    			break;
    		case _COMMAND_TRIGGER_INDICATOR:
    			DrawTriggerIndicator((TriggerState) cmd.args[0]);
    			break;
    		case COMMAND_UNKNOWN:
    			/* Fallthrough */
    		default:
    			break;
    		}

        	WidgetMessageQueueProcess();
    	}
    }
}

void
SI_Print(char* line1, char* line2, int32_t val, char* suffix, char* prefixes)
{
	int mag = 0;
	uint16_t dec = 0;

	while (abs(val) >= 1000)
	{
		dec = abs(val) % 1000;
		val /= 1000;
		mag++;
	}

	sprintf(line1, "%ld.%d", val, dec / 100);
	sprintf(line2, "%c%s", prefixes[mag], suffix);
}

void
SI_Micro_Print(char* line1, char* line2, int32_t val, char* suffix)
{
	SI_Print(line1, line2, val, suffix, "um k");
}

void
Init_UI(void)
{
	Mailbox_Params mbparams;
	Mailbox_Params_init(&mbparams);
	static Error_Block eb;

	GraphicsMailbox = Mailbox_create(sizeof(Command),20,&mbparams,&eb);
}
