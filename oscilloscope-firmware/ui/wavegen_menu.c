/*
 * wavegen_menu.c
 *
 *  Created on: 21 May 2016
 *      Author: ryanf
 */

#include "graphics_thread.h"
#include "wavegen.h"

static void OnWaveGenEnable(tWidget *psWidget);
static void OnWaveGenFreq(tWidget *psWidget);
static void OnWaveGenOffset(tWidget *psWidget);
static void OnWaveGenAmplitude(tWidget *psWidget);
static void OnWaveGenShape(tWidget *psWidget);

static RectangularButton(WaveGenOnOff, &menus[WAVEGEN_MENU], 0, 0, &SSD1289_Display, 110, 30,
				  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
				  ClrWhite, &g_sFontCm18b, "On/Off", 0, 0, 0, 0, OnWaveGenEnable);
static RectangularButton(WaveGenFreq, &menus[WAVEGEN_MENU], &WaveGenOnOff, 0, &SSD1289_Display, 220, 120,
				  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
				  ClrWhite, &g_sFontCm18b, "Freq", 0, 0, 0, 0, OnWaveGenFreq);
static RectangularButton(WaveGenOffset, &menus[WAVEGEN_MENU], &WaveGenFreq, 0, &SSD1289_Display, 110, 120,
				  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
				  ClrWhite, &g_sFontCm18b, "Offset", 0, 0, 0, 0, OnWaveGenOffset);
static RectangularButton(WaveGenAmplitude, &menus[WAVEGEN_MENU], &WaveGenOffset, 0, &SSD1289_Display, 0, 120,
				  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
				  ClrWhite, &g_sFontCm18b, "Vpp", 0, 0, 0, 0, OnWaveGenAmplitude);
static RectangularButton(WaveGenShape, &menus[WAVEGEN_MENU], &WaveGenAmplitude, 0, &SSD1289_Display, 220, 30,
                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE, ClrBlue, ClrYellow, ClrWhite,
				  ClrWhite, &g_sFontCm18b, "Type", 0, 0, 0, 0, OnWaveGenShape);
RectangularButton(WaveGenBack, &menus[WAVEGEN_MENU], &WaveGenShape, 0, &SSD1289_Display, 0, 30,
                  100, 80, PB_STYLE_FILL | PB_STYLE_TEXT | PB_STYLE_OUTLINE | PB_STYLE_RELEASE_NOTIFY, ClrGray, ClrYellow, ClrWhite,
				  ClrWhite, &g_sFontCm18b, "Back", 0, 0, 0, 0, OnMAIN);

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
WaveGenShapeSetText(const char* text)
{
	PushButtonTextSet(&WaveGenShape, text);

	if (current_menu == WAVEGEN_MENU)
	{
		Repaint((tWidget *)&WaveGenShape);
	}
}

static void
OnWaveGenEnable(tWidget *psWidget)
{
	WaveGenEnableSet(!WaveGenEnableGet());
}

static void
OnWaveGenFreq(tWidget *psWidget)
{
	uint32_t newfreq = Standard_Step(WaveGenGetFreq(), 1);

	if (WaveGenGetFreq() == 25000)
	{
		newfreq = 10;
	}
	else if (newfreq > 25000)
	{
		newfreq = 25000;
	}

	WaveGenSetFreq(newfreq);
}

static void
OnWaveGenOffset(tWidget *psWidget)
{
}

static void
OnWaveGenAmplitude(tWidget *psWidget)
{
}

static void
OnWaveGenShape(tWidget *psWidget)
{
	WaveGenSetShape((WaveType)((WaveGenGetShape() + 1) % 5));
}
