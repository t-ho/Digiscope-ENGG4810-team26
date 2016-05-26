/*
 * range_menu.c
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#include "graphics_thread.h"
#include "range_menu.h"
#include "frontend.h"

static uint32_t display_channel = 0;

static void OnHorRangeUp(tWidget *psWidget);
static void OnHorRangeDown(tWidget *psWidget);
static void OnVertRangeUp(tWidget *psWidget);
static void OnVertRangeDown(tWidget *psWidget);
static void OnRangeCoupling(tWidget *psWidget);

static Canvas(hor_div_label1, &menus[RANGE_MENU], 0, 0, &SSD1289_Display, 170, 140, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static Canvas(hor_div_label2, &menus[RANGE_MENU], &hor_div_label1, 0, &SSD1289_Display, 170, 160, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static Canvas(vert_div_label1, &menus[RANGE_MENU], &hor_div_label2, 0, &SSD1289_Display, 170, 50, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static Canvas(vert_div_label2, &menus[RANGE_MENU], &vert_div_label1, 0, &SSD1289_Display, 170, 70, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static RectangularButton(VertRangeDown, &menus[RANGE_MENU], &vert_div_label2, 0, &SSD1289_Display, 110, 30,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, OnVertRangeDown);
static RectangularButton(VertRangeUp, &menus[RANGE_MENU], &VertRangeDown, 0, &SSD1289_Display, 260, 30,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, OnVertRangeUp);
static RectangularButton(HorRangeDown, &menus[RANGE_MENU], &VertRangeUp, 0, &SSD1289_Display, 110, 120,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, OnHorRangeDown);
static RectangularButton(HorRangeUp, &menus[RANGE_MENU], &HorRangeDown, 0, &SSD1289_Display, 260, 120,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, OnHorRangeUp);
static RectangularButton(RangeCoupling, &menus[RANGE_MENU], &HorRangeUp, 0, &SSD1289_Display, 0, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, NULL, 0, 0, 0, 0, OnRangeCoupling);
RectangularButton(RangeBack, &menus[RANGE_MENU], &RangeCoupling, 0, &SSD1289_Display, 0, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN);

static void
OnHorRangeUp(tWidget *psWidget)
{
	FrontEndSetHorDiv(Standard_Step(FrontEndGetHorDiv(), 1));
}

static void
OnHorRangeDown(tWidget *psWidget)
{
	FrontEndSetHorDiv(Standard_Step(FrontEndGetHorDiv(), -1));
}

static void
OnVertRangeUp(tWidget *psWidget)
{
	FrontEndSetVerDiv(display_channel, Standard_Step(FrontEndGetVerDiv(display_channel), 1));
}

static void
OnVertRangeDown(tWidget *psWidget)
{
	FrontEndSetVerDiv(display_channel, Standard_Step(FrontEndGetVerDiv(display_channel), -1));
}

static void
OnRangeCoupling(tWidget *psWidget)
{
	FrontEndSetCoupling(display_channel, !FrontEndGetCoupling(display_channel));
}

void
RangeVerSetText(uint32_t channel, const char* line1, const char* line2)
{
	if (channel == display_channel && current_menu == RANGE_MENU)
	{
		CanvasTextSet(&vert_div_label1, line1);
		CanvasTextSet(&vert_div_label2, line2);

		Repaint((tWidget *)&vert_div_label1);
		Repaint((tWidget *)&vert_div_label2);
	}
}

void
RangeHorSetText(const char* line1, const char* line2)
{
	if (current_menu == RANGE_MENU)
	{
		CanvasTextSet(&hor_div_label1, line1);
		CanvasTextSet(&hor_div_label2, line2);

		Repaint((tWidget *)&hor_div_label1);
		Repaint((tWidget *)&hor_div_label2);
	}
}

void
RangeCouplingSetText(uint32_t channel, const char* text)
{
	if (channel == display_channel && current_menu == RANGE_MENU)
	{
		PushButtonTextSet(&RangeCoupling, text);
		Repaint((tWidget *)&RangeCoupling);
	}
}

void
SetDisplayChannel(uint32_t channel)
{
	display_channel = channel;
	FrontEndSetCoupling(channel, FrontEndGetCoupling(channel));
	FrontEndSetHorDiv(FrontEndGetHorDiv());
	FrontEndSetVerDiv(channel, FrontEndGetVerDiv(channel));
}
