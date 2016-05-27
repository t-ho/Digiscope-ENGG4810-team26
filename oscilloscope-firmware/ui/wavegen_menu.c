/*
 * wavegen_menu.c
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#include "graphics_thread.h"
#include "wavegen.h"

static void OnEnable(tWidget *psWidget);
static void OnFreqDown(tWidget *psWidget);
static void OnFreqUp(tWidget *psWidget);
static void OnOffsetDown(tWidget *psWidget);
static void OnOffsetUp(tWidget *psWidget);
static void OnAmplitudeDown(tWidget *psWidget);
static void OnAmplitudeUp(tWidget *psWidget);
static void OnChangeShape(tWidget *psWidget);

static Canvas(freq_label1, &menus[WAVEGEN1_MENU], 0, 0, &SSD1289_Display, 170, 140, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static Canvas(freq_label2, &menus[WAVEGEN1_MENU], &freq_label1, 0, &SSD1289_Display, 170, 160, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static RectangularButton(WaveGenOnOff, &menus[WAVEGEN1_MENU], &freq_label2, 0, &SSD1289_Display, 110, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, NULL, 0, 0, 0, 0, OnEnable);
static RectangularButton(WaveGenShape, &menus[WAVEGEN1_MENU], &WaveGenOnOff, 0, &SSD1289_Display, 220, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, NULL, 0, 0, 0, 0, OnChangeShape);
static RectangularButton(WaveGenFreqDown, &menus[WAVEGEN1_MENU], &WaveGenShape, 0, &SSD1289_Display, 110, 120,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, OnFreqDown);
static RectangularButton(WaveGenFreqUp, &menus[WAVEGEN1_MENU], &WaveGenFreqDown, 0, &SSD1289_Display, 260, 120,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, OnFreqUp);
static RectangularButton(WaveGenTo2, &menus[WAVEGEN1_MENU], &WaveGenFreqUp, 0, &SSD1289_Display, 0, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "More", 0, 0, 0, 0, OnWAVEGEN2);
RectangularButton(WaveGen1Back, &menus[WAVEGEN1_MENU], &WaveGenTo2, 0, &SSD1289_Display, 0, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN);

static Canvas(amplitude_label1, &menus[WAVEGEN2_MENU], 0, 0, &SSD1289_Display, 170, 50, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static Canvas(amplitude_label2, &menus[WAVEGEN2_MENU], &amplitude_label1, 0, &SSD1289_Display, 170, 70, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static Canvas(offset_label1, &menus[WAVEGEN2_MENU], &amplitude_label2, 0, &SSD1289_Display, 170, 140, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static Canvas(offset_label2, &menus[WAVEGEN2_MENU], &offset_label1, 0, &SSD1289_Display, 170, 160, 90, 20,
	CANVAS_STYLE_TEXT | CANVAS_STYLE_TEXT_OPAQUE | CANVAS_STYLE_FILL, 0, 0, ClrWhite,
	&g_sFontCm20, NULL, 0, 0);
static RectangularButton(WaveGenAmplitudeDown, &menus[WAVEGEN2_MENU], &offset_label2, 0, &SSD1289_Display, 110, 30,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, OnAmplitudeDown);
static RectangularButton(WaveGenAmplitudeUp, &menus[WAVEGEN2_MENU], &WaveGenAmplitudeDown, 0, &SSD1289_Display, 260, 30,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, OnAmplitudeUp);
static RectangularButton(WaveGenOffsetDown, &menus[WAVEGEN2_MENU], &WaveGenAmplitudeUp, 0, &SSD1289_Display, 110, 120,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "-", 0, 0, 0, 0, OnOffsetDown);
static RectangularButton(WaveGenOffsetUp, &menus[WAVEGEN2_MENU], &WaveGenOffsetDown, 0, &SSD1289_Display, 260, 120,
	60, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "+", 0, 0, 0, 0, OnOffsetUp);
static RectangularButton(WaveGenTo1, &menus[WAVEGEN2_MENU], &WaveGenOffsetUp, 0, &SSD1289_Display, 0, 120,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "More", 0, 0, 0, 0, OnWAVEGEN1);
RectangularButton(WaveGen2Back, &menus[WAVEGEN2_MENU], &WaveGenTo1, 0, &SSD1289_Display, 0, 30,
	100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
	ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN);


void
WaveGenOnOffSetText(const char* text)
{
	PushButtonTextSet(&WaveGenOnOff, text);

	if (current_menu == WAVEGEN1_MENU)
	{
		Repaint((tWidget *)&WaveGenOnOff);
	}
}

void
WaveGenFreqSetText(const char* line1, const char* line2)
{
	CanvasTextSet(&freq_label1, line1);
	CanvasTextSet(&freq_label2, line2);

	if (current_menu == WAVEGEN1_MENU)
	{
		Repaint((tWidget *)&freq_label1);
		Repaint((tWidget *)&freq_label2);
	}
}

void
WaveGenAmplitudeSetText(const char* line1, const char* line2)
{
	CanvasTextSet(&amplitude_label1, line1);
	CanvasTextSet(&amplitude_label2, line2);

	if (current_menu == WAVEGEN2_MENU)
	{
		Repaint((tWidget *)&amplitude_label1);
		Repaint((tWidget *)&amplitude_label2);
	}
}

void
WaveGenOffsetSetText(const char* line1, const char* line2)
{
	CanvasTextSet(&offset_label1, line1);
	CanvasTextSet(&offset_label2, line2);

	if (current_menu == WAVEGEN2_MENU)
	{
		Repaint((tWidget *)&offset_label1);
		Repaint((tWidget *)&offset_label2);
	}
}

void
WaveGenShapeSetText(const char* text)
{
	PushButtonTextSet(&WaveGenShape, text);

	if (current_menu == WAVEGEN1_MENU)
	{
		Repaint((tWidget *)&WaveGenShape);
	}
}

static void
OnEnable(tWidget *psWidget)
{
	WaveGenEnableSet(!WaveGenEnableGet());
}

static void
OnFreqDown(tWidget *psWidget)
{
	uint32_t diff;
	uint32_t current = WaveGenGetFreq();

	if (current > 1000)
	{
		diff = 100;
	}
	else if (current > 100)
	{
		diff = 10;
	}
	else if (current > 1)
	{
		diff = 1;
	}
	else
	{
		diff = 0;
	}

	WaveGenSetFreq(current - diff);
}

static void
OnFreqUp(tWidget *psWidget)
{
	uint32_t diff;
	uint32_t current = WaveGenGetFreq();

	if (current < 100)
	{
		diff = 1;
	}
	else if (current < 1000)
	{
		diff = 10;
	}
	else
	{
		diff = 100;
	}

	WaveGenSetFreq(current + diff);
}

static void
OnOffsetDown(tWidget *psWidget)
{
	WaveGenSetOffset(WaveGenGetOffset() - 1000);
}

static void
OnOffsetUp(tWidget *psWidget)
{
	WaveGenSetOffset(WaveGenGetOffset() + 1000);
}

static void
OnAmplitudeDown(tWidget *psWidget)
{
	WaveGenSetAmplitude(Standard_Step(WaveGenGetAmplitude(), -1));
}

static void
OnAmplitudeUp(tWidget *psWidget)
{
	WaveGenSetAmplitude(Standard_Step(WaveGenGetAmplitude(), 1));
}

static void
OnChangeShape(tWidget *psWidget)
{
	WaveGenSetShape((WaveType)((WaveGenGetShape() + 1) % 5));
}
