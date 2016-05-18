/*
 * SSD1289_driver.c
 *
 *  Created on: 26 Apr 2016
 *      Author: Ryan
 */

#include <stdbool.h>
#include <stdint.h>
#include "inc/hw_gpio.h"
#include "inc/hw_ints.h"
#include "inc/hw_memmap.h"
#include "inc/hw_types.h"
#include "driverlib/pin_map.h"
#include "driverlib/gpio.h"
#include "driverlib/pwm.h"
#include "driverlib/epi.h"
#include "driverlib/interrupt.h"
#include "driverlib/sysctl.h"
#include "driverlib/timer.h"
#include "driverlib/rom.h"
#include "driverlib/rom_map.h"
#include "grlib/grlib.h"

#define SSD1289_ENTRY_MODE_REG        0x11

#define SSD1289_RAM_DATA_REG          0x22

#define SSD1289_V_RAM_POS_REG         0x44
#define SSD1289_H_RAM_START_REG       0x45
#define SSD1289_H_RAM_END_REG         0x46
#define SSD1289_X_RAM_ADDR_REG        0x4E
#define SSD1289_Y_RAM_ADDR_REG        0x4F

#define ENTRY_MODE_HORIZ 0x6818
#define ENTRY_MODE_VERT 0x6810

#define MAPPED_X(x, y) (y)
#define MAPPED_Y(x, y) (319 - x)

#define GPIO_SET_HIGH(pin) GPIOPinWrite(pin ## _B, pin ## _P, pin ## _P)
#define GPIO_SET_LOW(pin) GPIOPinWrite(pin ## _B, pin ## _P, 0)

//*****************************************************************************
//
// Translates a 24-bit RGB color to a display driver-specific color.
//
// \param c is the 24-bit RGB color.  The least-significant byte is the blue
// channel, the next byte is the green channel, and the third byte is the red
// channel.
//
// This macro translates a 24-bit RGB color into a value that can be written
// into the display's frame buffer in order to reproduce that color, or the
// closest possible approximation of that color.
//
// \return Returns the display-driver specific color.
//
//*****************************************************************************
#define DPYCOLORTRANSLATE(c)    ((((c) & 0x00f80000) >> 8) | \
								 (((c) & 0x0000fc00) >> 5) | \
								 (((c) & 0x000000f8) >> 3))

#define LCD_DATA_0_B	GPIO_PORTK_BASE
#define LCD_DATA_1_B	GPIO_PORTK_BASE
#define LCD_DATA_2_B	GPIO_PORTK_BASE
#define LCD_DATA_3_B	GPIO_PORTK_BASE
#define LCD_DATA_4_B	GPIO_PORTK_BASE
#define LCD_DATA_5_B	GPIO_PORTK_BASE
#define LCD_DATA_6_B	GPIO_PORTK_BASE
#define LCD_DATA_7_B	GPIO_PORTK_BASE
#define LCD_DATA_8_B	GPIO_PORTM_BASE
#define LCD_DATA_9_B	GPIO_PORTM_BASE
#define LCD_DATA_10_B	GPIO_PORTM_BASE
#define LCD_DATA_11_B	GPIO_PORTM_BASE
#define LCD_DATA_12_B	GPIO_PORTM_BASE
#define LCD_DATA_13_B	GPIO_PORTM_BASE
#define LCD_DATA_14_B	GPIO_PORTM_BASE
#define LCD_DATA_15_B	GPIO_PORTM_BASE

#define LCD_RS_B		GPIO_PORTP_BASE
#define LCD_WR_B		GPIO_PORTP_BASE
#define LCD_RD_B		GPIO_PORTP_BASE

#define LCD_CS_B		GPIO_PORTP_BASE
#define LCD_RST_B		GPIO_PORTP_BASE
#define LCD_LED_B		GPIO_PORTG_BASE

#define LCD_DATA_0_P	GPIO_PIN_0
#define LCD_DATA_1_P	GPIO_PIN_1
#define LCD_DATA_2_P	GPIO_PIN_2
#define LCD_DATA_3_P	GPIO_PIN_3
#define LCD_DATA_4_P	GPIO_PIN_4
#define LCD_DATA_5_P	GPIO_PIN_5
#define LCD_DATA_6_P	GPIO_PIN_6
#define LCD_DATA_7_P	GPIO_PIN_7
#define LCD_DATA_8_P	GPIO_PIN_0
#define LCD_DATA_9_P	GPIO_PIN_1
#define LCD_DATA_10_P	GPIO_PIN_2
#define LCD_DATA_11_P	GPIO_PIN_3
#define LCD_DATA_12_P	GPIO_PIN_4
#define LCD_DATA_13_P	GPIO_PIN_5
#define LCD_DATA_14_P	GPIO_PIN_6
#define LCD_DATA_15_P	GPIO_PIN_7

#define LCD_RS_P		GPIO_PIN_5
#define LCD_WR_P		GPIO_PIN_4
#define LCD_RD_P		GPIO_PIN_3

#define LCD_CS_P		GPIO_PIN_2
#define LCD_RST_P		GPIO_PIN_0
#define LCD_LED_P		GPIO_PIN_1

static const uint32_t LCD_BASES[] =
{
	LCD_DATA_0_B,
	LCD_DATA_1_B,
	LCD_DATA_2_B,
	LCD_DATA_3_B,
	LCD_DATA_4_B,
	LCD_DATA_5_B,
	LCD_DATA_6_B,
	LCD_DATA_7_B,
	LCD_DATA_8_B,
	LCD_DATA_9_B,
	LCD_DATA_10_B,
	LCD_DATA_11_B,
	LCD_DATA_12_B,
	LCD_DATA_13_B,
	LCD_DATA_14_B,
	LCD_DATA_15_B,
	LCD_RS_B,
	LCD_WR_B,
	LCD_RD_B,
	LCD_CS_B,
	LCD_RST_B,
};

static const uint8_t LCD_PINS[] =
{
	LCD_DATA_0_P,
	LCD_DATA_1_P,
	LCD_DATA_2_P,
	LCD_DATA_3_P,
	LCD_DATA_4_P,
	LCD_DATA_5_P,
	LCD_DATA_6_P,
	LCD_DATA_7_P,
	LCD_DATA_8_P,
	LCD_DATA_9_P,
	LCD_DATA_10_P,
	LCD_DATA_11_P,
	LCD_DATA_12_P,
	LCD_DATA_13_P,
	LCD_DATA_14_P,
	LCD_DATA_15_P,
	LCD_RS_P,
	LCD_WR_P,
	LCD_RD_P,
	LCD_CS_P,
	LCD_RST_P,
};

static bool backlight_state = true;

static void
gpio_out_data(uint16_t c)
{
	MAP_GPIOPinWrite(GPIO_PORTK_BASE, 0xFF, c & 0xFF);
	MAP_GPIOPinWrite(GPIO_PORTM_BASE, 0xFF, (c >> 8) & 0xFF);

//	int32_t a1 = MAP_GPIOPinRead(GPIO_PORTA_BASE, 0xFF);
//	int32_t b1 = MAP_GPIOPinRead(GPIO_PORTB_BASE, 0xFF);
//	int32_t h1 = MAP_GPIOPinRead(GPIO_PORTH_BASE, 0xFF);
//	int32_t k1 = MAP_GPIOPinRead(GPIO_PORTK_BASE, 0xFF);
//	int32_t m1 = MAP_GPIOPinRead(GPIO_PORTM_BASE, 0xFF);
//
//	int i;
//	for (i = 0; i < 16; i++)
//	{
//		if (c & (1 << i))
//		{
//			MAP_GPIOPinWrite(LCD_BASES[i], LCD_PINS[i], LCD_PINS[i]);
//		}
//		else
//		{
//			MAP_GPIOPinWrite(LCD_BASES[i], LCD_PINS[i], 0);
//		}
//	}
//
//	int32_t a2 = MAP_GPIOPinRead(GPIO_PORTA_BASE, 0xFF);
//	int32_t b2 = MAP_GPIOPinRead(GPIO_PORTB_BASE, 0xFF);
//	int32_t h2 = MAP_GPIOPinRead(GPIO_PORTH_BASE, 0xFF);
//	int32_t k2 = MAP_GPIOPinRead(GPIO_PORTK_BASE, 0xFF);
//	int32_t m2 = MAP_GPIOPinRead(GPIO_PORTM_BASE, 0xFF);
}

static void
Write_Command(uint16_t c)
{
	MAP_GPIOPinWrite(LCD_CS_B, LCD_CS_P, 0);
	MAP_GPIOPinWrite(LCD_RS_B, LCD_RS_P, 0);
	gpio_out_data(c);
	MAP_GPIOPinWrite(LCD_WR_B, LCD_WR_P, 0);
	MAP_GPIOPinWrite(LCD_WR_B, LCD_WR_P, LCD_WR_P);
	MAP_GPIOPinWrite(LCD_CS_B, LCD_CS_P, LCD_CS_P);
}

static void
Write_Data(uint16_t c)
{
	MAP_GPIOPinWrite(LCD_CS_B, LCD_CS_P, 0);
	MAP_GPIOPinWrite(LCD_RS_B, LCD_RS_P, LCD_RS_P);
	gpio_out_data(c);
	MAP_GPIOPinWrite(LCD_WR_B, LCD_WR_P, 0);
	MAP_GPIOPinWrite(LCD_WR_B, LCD_WR_P, LCD_WR_P);
	MAP_GPIOPinWrite(LCD_CS_B, LCD_CS_P, LCD_CS_P);
}

static void
Write_Command_Data(uint16_t cmd, uint16_t dat)
{
	Write_Command(cmd);
	Write_Data(dat);
}

static void
SetXY(unsigned int x0,unsigned int y0,unsigned int x1,unsigned int y1)
{
	Write_Command_Data(SSD1289_V_RAM_POS_REG, (x1<<8)+x0);
	Write_Command_Data(SSD1289_H_RAM_START_REG, y0);
	Write_Command_Data(SSD1289_H_RAM_END_REG, y1);
	Write_Command_Data(SSD1289_X_RAM_ADDR_REG, x0);
	Write_Command_Data(SSD1289_Y_RAM_ADDR_REG, y0);
	Write_Command(SSD1289_RAM_DATA_REG);//LCD_WriteCMD(GRAMWR);
}

void Pant(uint16_t color)
{
	int i,j;
	SetXY(0,0,239,319);

	for(i=0;i<320;i++)
	{
		for (j=0;j<240;j++)
		{
			Write_Data(color);
		}
	}
}

static void
SSD1289_Init_Magic()
{
	//MAP_GPIOPinWrite(LCD_LED_B, LCD_LED_P, LCD_LED_P);

	MAP_GPIOPinWrite(LCD_RST_B, LCD_RST_P, 0);
	MAP_GPIOPinWrite(LCD_CS_B, LCD_CS_P, LCD_CS_P);
	MAP_GPIOPinWrite(LCD_RS_B, LCD_RS_P, LCD_RS_P);
	MAP_GPIOPinWrite(LCD_WR_B, LCD_WR_P, LCD_WR_P);
	MAP_GPIOPinWrite(LCD_RD_B, LCD_RD_P, LCD_RD_P);

	MAP_GPIOPinWrite(LCD_RST_B, LCD_RST_P, LCD_RST_P);

	MAP_SysCtlDelay(100);

	Write_Command_Data(0x0000,0x0001);
	Write_Command_Data(0x0003,0xA8A4);
	Write_Command_Data(0x000C,0x0000);
	Write_Command_Data(0x000D,0x080C);
	Write_Command_Data(0x000E,0x2B00);
	Write_Command_Data(0x001E,0x00B7);
	Write_Command_Data(0x0001,0x2B3F);
	Write_Command_Data(0x0002,0x0600);
	Write_Command_Data(0x0010,0x0000);
	Write_Command_Data(0x0011,0x6070);
	Write_Command_Data(0x0005,0x0000);
	Write_Command_Data(0x0006,0x0000);
	Write_Command_Data(0x0016,0xEF1C);
	Write_Command_Data(0x0017,0x0003);
	Write_Command_Data(0x0007,0x0233);
	Write_Command_Data(0x000B,0x0000);
	Write_Command_Data(0x000F,0x0000);
	Write_Command_Data(0x0041,0x0000);
	Write_Command_Data(0x0042,0x0000);
	Write_Command_Data(0x0048,0x0000);
	Write_Command_Data(0x0049,0x013F);
	Write_Command_Data(0x004A,0x0000);
	Write_Command_Data(0x004B,0x0000);
	Write_Command_Data(SSD1289_V_RAM_POS_REG, 0xEF00);
	Write_Command_Data(SSD1289_H_RAM_START_REG, 0x0000);
	Write_Command_Data(SSD1289_H_RAM_END_REG, 0x013F);
	Write_Command_Data(0x0030,0x0707);
	Write_Command_Data(0x0031,0x0204);
	Write_Command_Data(0x0032,0x0204);
	Write_Command_Data(0x0033,0x0502);
	Write_Command_Data(0x0034,0x0507);
	Write_Command_Data(0x0035,0x0204);
	Write_Command_Data(0x0036,0x0204);
	Write_Command_Data(0x0037,0x0502);
	Write_Command_Data(0x003A,0x0302);
	Write_Command_Data(0x003B,0x0302);
	Write_Command_Data(0x0023,0x0000);
	Write_Command_Data(0x0024,0x0000);
	Write_Command_Data(0x0025,0x8000);
	Write_Command_Data(0x004f,0x0000);
	Write_Command_Data(0x004e,0x0000);
	Write_Command(0x0022);
}

void
SSD1289_Backlight_Set(uint8_t level)
{
	if (level > 5)
	{
		level = 5;
	}

	MAP_PWMPulseWidthSet(PWM0_BASE, PWM_OUT_5, 2000 * level);
}

static void
Backlight_Init(void)
{
	MAP_GPIOPinTypePWM(LCD_LED_B, LCD_LED_P);
	MAP_GPIOPinConfigure(GPIO_PG1_M0PWM5);

	MAP_SysCtlPeripheralEnable(SYSCTL_PERIPH_PWM0);
	while (!MAP_SysCtlPeripheralReady(SYSCTL_PERIPH_PWM0));

	MAP_PWMGenConfigure(PWM0_BASE, PWM_GEN_2, PWM_GEN_MODE_DOWN | PWM_GEN_MODE_NO_SYNC);
	MAP_PWMGenPeriodSet(PWM0_BASE, PWM_GEN_2, 10000);
	MAP_PWMPulseWidthSet(PWM0_BASE, PWM_OUT_5, 10000);
	MAP_PWMGenEnable(PWM0_BASE, PWM_GEN_2);
	MAP_PWMOutputState(PWM0_BASE, PWM_OUT_5_BIT, true);
}

void
SSD1289_Set_Backlight_On(bool state)
{
	backlight_state = state;
	MAP_PWMOutputState(PWM0_BASE, PWM_OUT_5_BIT, backlight_state);
}

bool
SSD1289_Get_Backlight_On(void)
{
	return backlight_state;
}

void
SSD1289_Init(void)
{
	int i;
	for (i = 0; i < 21; i++)
	{
		MAP_GPIOPinTypeGPIOOutput(LCD_BASES[i], LCD_PINS[i]);
		MAP_GPIOPadConfigSet(LCD_BASES[i], LCD_PINS[i], GPIO_STRENGTH_12MA, GPIO_PIN_TYPE_STD);
	}

	Backlight_Init();

	SSD1289_Init_Magic();

	Pant(0x0000);
}

static uint32_t
SSD1289_ColorTranslate(void *pvDisplayData, uint32_t ui32Value)
{
    return(DPYCOLORTRANSLATE(ui32Value));
}

static void
SSD1289_Flush(void *pvDisplayData)
{
}

static void
SSD1289_PixelDraw(void *pvDisplayData, int32_t x, int32_t y, uint32_t color)
{
	Write_Command_Data(SSD1289_X_RAM_ADDR_REG, MAPPED_X(x, y));
	Write_Command_Data(SSD1289_Y_RAM_ADDR_REG, MAPPED_Y(x, y));
	Write_Command_Data(SSD1289_RAM_DATA_REG, color);
}

static void
SSD1289_PixelDrawMultiple(void *pvDisplayData, int32_t x, int32_t y, int32_t x0, int32_t count, int32_t bpp,
		const uint8_t *pui8Data, const uint8_t *pui8Palette)
{
    uint32_t ui32Byte;

    // Set the cursor increment to left to right, followed by top to bottom.
    Write_Command_Data(SSD1289_ENTRY_MODE_REG, ENTRY_MODE_HORIZ);

    // Set the starting X and Y address of the display cursor.
    Write_Command_Data(SSD1289_X_RAM_ADDR_REG, MAPPED_X(x, y));
    Write_Command_Data(SSD1289_Y_RAM_ADDR_REG, MAPPED_Y(x, y));

    // Write the data RAM write command.
    Write_Command(SSD1289_RAM_DATA_REG);

    // Determine how to interpret the pixel data based on the number of bits
    // per pixel.
    switch(bpp & 0xFF)
    {
        // The pixel data is in 1 bit per pixel format.
        case 1:
        {
			// Loop while there are more pixels to draw.
			while(count)
			{
				// Get the next byte of image data.
				ui32Byte = *pui8Data++;

				// Loop through the pixels in this byte of image data.
				for(; (x0 < 8) && count; x0++, count--)
				{
					// Draw this pixel in the appropriate color.
					Write_Data(((uint32_t *)pui8Palette)[(ui32Byte >> (7 - x0)) & 1]);
				}

				// Start at the beginning of the next byte of image data.
				x0 = 0;
			}

			// The image data has been drawn.
			break;
        }
		// The pixel data is in 4 bit per pixel format.
        case 4:
        {
			// Loop while there are more pixels to draw.  "Duff's device"
			// is used to jump into the middle of the loop if the first
			// nibble of the pixel data should not be used.  Duff's device
			// makes use of the fact that a case statement is legal
			// anywhere within a sub-block of a switch statement.  See
			// http://en.wikipedia.org/wiki/Duff's_device for detailed
			// information about Duff's device.
			switch(x0 & 1)
			{
				case 0:
					while(count)
					{
						// Get the upper nibble of the next byte of pixel
						// data and extract the corresponding entry from
						// the palette.
						ui32Byte = (*pui8Data >> 4) * 3;
						ui32Byte = (*(uint32_t *)(pui8Palette + ui32Byte) & 0x00ffffff);

						// Translate this palette entry and write it to the
						// screen.
						Write_Data(DPYCOLORTRANSLATE(ui32Byte));

						// Decrement the count of pixels to draw.
						count--;

						// See if there is another pixel to draw.
						if(count)
						{
							case 1:
								// Get the lower nibble of the next byte of
								// pixel data and extract the corresponding
								// entry from the palette.
								ui32Byte = (*pui8Data++ & 15) * 3;
								ui32Byte = (*(uint32_t *)(pui8Palette + ui32Byte) & 0x00ffffff);

								// Translate this palette entry and write
								// it to the screen.
								Write_Data(DPYCOLORTRANSLATE(ui32Byte));

								// Decrement the count of pixels to draw.
								count--;
						}
					}
			}

			// The image data has been drawn.
			break;
    	}

		// The pixel data is in 8 bit per pixel format.
        case 8:
        {
			// Loop while there are more pixels to draw.
			while(count--)
			{
				// Get the next byte of pixel data and extract the
				// corresponding entry from the palette.
				ui32Byte = *pui8Data++ * 3;
				ui32Byte = *(uint32_t *)(pui8Palette + ui32Byte) & 0x00ffffff;

				// Translate this palette entry and write it to the screen.
				Write_Data(DPYCOLORTRANSLATE(ui32Byte));
			}

			// The image data has been drawn.
			break;


		// We are being passed data in the display's native format.  Merely
		// write it directly to the display.  This is a special case which
		// is not used by the graphics library but which is helpful to
		// applications which may want to handle, for example, JPEG images.
    	}
        case 16:
        {
			uint16_t ui16Byte;

			// Loop while there are more pixels to draw.
			while(count--)
			{
				// Get the next byte of pixel data and extract the
				// corresponding entry from the palette.
				ui16Byte = *((uint16_t *)pui8Data);
				pui8Data += 2;

				// Translate this palette entry and write it to the screen.
				Write_Data(ui16Byte);
			}
        }
    }
}

static void
SSD1289_LineDrawH(void *pvDisplayData, int32_t x1, int32_t x2, int32_t y, uint32_t color)
{
	Write_Command_Data(SSD1289_ENTRY_MODE_REG, ENTRY_MODE_HORIZ);
	Write_Command_Data(SSD1289_X_RAM_ADDR_REG, MAPPED_X(x1, y));
	Write_Command_Data(SSD1289_Y_RAM_ADDR_REG, MAPPED_Y(x1, y));

	Write_Command(SSD1289_RAM_DATA_REG);

	while (x1++ <= x2)
	{
		Write_Data(color);
	}
}

static void
SSD1289_LineDrawV(void *pvDisplayData, int32_t x, int32_t y1, int32_t y2, uint32_t color)
{
	Write_Command_Data(SSD1289_ENTRY_MODE_REG, ENTRY_MODE_VERT);
	Write_Command_Data(SSD1289_X_RAM_ADDR_REG, MAPPED_X(x, y1));
	Write_Command_Data(SSD1289_Y_RAM_ADDR_REG, MAPPED_Y(x, y1));

	Write_Command(SSD1289_RAM_DATA_REG);

	while (y1++ <= y2)
	{
		Write_Data(color);
	}
}

static void
SSD1289_RectFill(void *pvDisplayData, const tRectangle *rect, uint32_t color)
{
	int32_t count;

	Write_Command_Data(SSD1289_ENTRY_MODE_REG, ENTRY_MODE_HORIZ);

	Write_Command_Data(SSD1289_H_RAM_START_REG, MAPPED_Y(rect->i16XMax, rect->i16YMax));
	Write_Command_Data(SSD1289_H_RAM_END_REG, MAPPED_Y(rect->i16XMin, rect->i16YMin));

	Write_Command_Data(SSD1289_V_RAM_POS_REG, MAPPED_X(rect->i16XMax, rect->i16YMax) << 8 | MAPPED_X(rect->i16XMin, rect->i16YMin));

	Write_Command_Data(SSD1289_X_RAM_ADDR_REG, MAPPED_X(rect->i16XMin, rect->i16YMin));
	Write_Command_Data(SSD1289_Y_RAM_ADDR_REG, MAPPED_Y(rect->i16XMax, rect->i16YMax));

	Write_Command(SSD1289_RAM_DATA_REG);

	count = (rect->i16XMax - rect->i16XMin + 1) * (rect->i16YMax - rect->i16YMin + 1);
	while (count-- >= 0)
	{
		Write_Data(color);
	}

	Write_Command_Data(SSD1289_H_RAM_START_REG, 0x0000);
	Write_Command_Data(SSD1289_H_RAM_END_REG, 0x013F);
	Write_Command_Data(SSD1289_V_RAM_POS_REG, 0xEF00);
}

const tDisplay SSD1289_Display =
{
    sizeof(tDisplay),
    0,
    320,
    240,
    SSD1289_PixelDraw,
	SSD1289_PixelDrawMultiple,
	SSD1289_LineDrawH,
	SSD1289_LineDrawV,
	SSD1289_RectFill,
	SSD1289_ColorTranslate,
	SSD1289_Flush
};
