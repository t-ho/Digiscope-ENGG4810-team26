/*
 * command.h
 *
 *  Created on: 30 Apr 2016
 *      Author: Ryan
 */

#include <stdio.h>

/* BIOS Header files */
#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Semaphore.h>
#include <ti/sysbios/knl/Mailbox.h>

#ifndef COMMAND_H_
#define COMMAND_H_

#define COMMAND_IS_CONFIRMATION 0xFF
#define COMMAND_IS_COMMAND 0x00

enum CommandTypes
{
	COMMAND_UNKNOWN = 0x00,
	COMMAND_HORIZONTAL_RANGE = 0x02,
	COMMAND_TRIGGER_MODE = 0x03,
	COMMAND_TRIGGER_TYPE = 0x04,
	COMMAND_TRIGGER_THRESHOLD = 0x05,
	COMMAND_SAMPLE_LENGTH = 0x07,
	COMMAND_TRIGGER_STATE = 0x08,
	COMMAND_TRIGGER_ARM = 0x0A,
	COMMAND_TRIGGER_FORCE = 0x0F,

	COMMAND_NUM_SAMPLES = 0x71,

	COMMAND_VERTICAL_RANGE_A = 0xA1,
	COMMAND_DC_OFFSET_A = 0xA6,
	COMMAND_COUPLING_A = 0xAC,
	SAMPLE_PACKET_A_8 = 0xAD,
	SAMPLE_PACKET_A_12 = 0xAE,

	COMMAND_VERTICAL_RANGE_B = 0xB1,
	COMMAND_DC_OFFSET_B = 0xB6,
	COMMAND_COUPLING_B = 0xBC,
	SAMPLE_PACKET_B_8 = 0xBD,
	SAMPLE_PACKET_B_12 = 0xBE,

	COMMAND_DISPLAY_POWER = 0xD0,
	COMMAND_DIPLAY_BRIGHTNESS = 0xD1,

	COMMAND_KEEPALIVE = 0xEE,

	COMMAND_FUNCTION_GEN_OUT = 0xF0,
	COMMAND_FUNCTION_GEN_WAVE = 0xF1,
	COMMAND_FUNCTION_GEN_VOLTAGE = 0xF2,
	COMMAND_FUNCTION_GEN_OFFSET = 0xF3,
	COMMAND_FUNCTION_GEN_FREQUENCY = 0xF4,

	_COMMAND_REPAINT = 0x88,
	_COMMAND_IP_UPDATE = 0x80,
	_COMMAND_CONN_UPDATE = 0x81,
	_COMMAND_PTR_UP = 0x82,
	_COMMAND_PTR_DOWN = 0x83,
	_COMMAND_TRIGGER_INDICATOR = 0x90,
	_COMMAND_OVERVOLTAGE = 0x99,
};

#define COMMANDLENGTH 6

typedef struct __attribute__((__packed__)) Command
{
	uint8_t type;
	uint8_t is_confirmation;
	int32_t args[2];
} Command;

typedef struct __attribute__((__packed__)) SampleCommand
{
	uint8_t type;
	uint8_t seq_num;
	int16_t num_samples;
	int16_t period;
	void* buffer;
} SampleCommand;

#endif /* COMMAND_H_ */
