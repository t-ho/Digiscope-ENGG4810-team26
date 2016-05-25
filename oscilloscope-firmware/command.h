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
	COMMAND_VERTICAL_RANGE_A = 0xA1,
	COMMAND_VERTICAL_RANGE_B = 0xB1,
	COMMAND_HORIZONTAL_RANGE = 0x02,
	COMMAND_TRIGGER_MODE_A = 0xA3,
	COMMAND_TRIGGER_MODE_B = 0xB3,
	COMMAND_TRIGGER_TYPE_A = 0xA4,
	COMMAND_TRIGGER_TYPE_B = 0xB4,
	COMMAND_TRIGGER_THRESHOLD_A = 0xA5,
	COMMAND_TRIGGER_THRESHOLD_B = 0xB5,
	COMMAND_DC_OFFSET_A = 0xA6,
	COMMAND_DC_OFFSET_B = 0xB6,
	COMMAND_TRIGGER_ARM_A = 0xAA,
	COMMAND_TRIGGER_ARM_B = 0xBA,
	COMMAND_TRIGGER_FORCE_A = 0xAF,
	COMMAND_TRIGGER_FORCE_B = 0xBF,
	COMMAND_COUPLING_A = 0xAC,
	COMMAND_COUPLING_B = 0xBC,
	COMMAND_FUNCTION_GEN_OUT = 0xF0,
	COMMAND_FUNCTION_GEN_WAVE = 0xF1,
	COMMAND_FUNCTION_GEN_VOLTAGE = 0xF2,
	COMMAND_FUNCTION_GEN_OFFSET = 0xF3,
	COMMAND_FUNCTION_GEN_FREQUENCY = 0xF4,
	COMMAND_DISPLAY_POWER = 0xD0,
	COMMAND_DIPLAY_BRIGHTNESS = 0xD1,

	SAMPLE_PACKET_A_8 = 0xAD,
	SAMPLE_PACKET_B_8 = 0xBD,
	SAMPLE_PACKET_A_12 = 0xAE,
	SAMPLE_PACKET_B_12 = 0xBE,

	COMMAND_KEEPALIVE = 0xEE,

	_COMMAND_UNKNOWN = 0x00,
	_COMMAND_REPAINT = 0x88,
	_COMMAND_IP_UPDATE = 0x80,
	_COMMAND_CONN_UPDATE = 0x81,
	_COMMAND_PTR_UP = 0x82,
	_COMMAND_PTR_DOWN = 0x83,
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
