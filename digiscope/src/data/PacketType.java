package data;

/**
 *
 * @author ToanHo
 */
public class PacketType {

	public static final byte HORIZONTAL_RANGE = (byte) 0x02;
	public static final byte VERTICAL_RANGE_A = (byte) 0xA1;
	public static final byte VERTICAL_RANGE_B = (byte) 0xB1;
	public static final byte TRIGGER_MODE_A = (byte) 0xA3;
	public static final byte TRIGGER_MODE_B = (byte) 0xB3;
	public static final byte TRIGGER_TYPE_A = (byte) 0xA4;
	public static final byte TRIGGER_TYPE_B = (byte) 0xB4;
	public static final byte TRIGGER_THRESHOLD_A = (byte) 0xA5;
	public static final byte TRIGGER_THRESHOLD_B = (byte) 0xB5;
	public static final byte DC_OFFSET_A = (byte) 0xA6;
	public static final byte DC_OFFSET_B = (byte) 0xB6;
	public static final byte NUMBER_OF_SAMPLES = (byte) 0x71;
	public static final byte TRIGGER_ARM_A = (byte) 0xAA;
	public static final byte TRIGGER_ARM_B = (byte) 0xBA;
	public static final byte TRIGGER_FORCE_A = (byte) 0xAF;
	public static final byte TRIGGER_FORCE_B = (byte) 0xBF;
	public static final byte CHANNEL_COUPLING_A = (byte) 0xAC;
	public static final byte CHANNEL_COUPLING_B = (byte) 0xBC;
	public static final byte CHANNEL_MODE_A = (byte) 0xA7;
	public static final byte CHANNEL_MODE_B = (byte) 0xB7;
	public static final byte GENERATOR_OUTPUT = (byte) 0xF0;
	public static final byte WAVE_TYPE = (byte) 0xF1;
	public static final byte GENERATOR_VOLTAGE = (byte) 0xF2;
	public static final byte GENERATOR_OFFSET = (byte) 0xF3;
	public static final byte GENERATOR_FREQUENCY = (byte) 0xF4;
	public static final byte KEEP_ALIVE = (byte) 0xEE;
	public static final byte DISPLAY_POWER = (byte) 0xD0;
	public static final byte BACKLIGHT_BRIGHTNESS = (byte) 0xD1;
	public static final byte CHANNEL_A_8_BITS = (byte) 0xAD;
	public static final byte CHANNEL_B_8_BITS = (byte) 0xBD;
	public static final byte CHANNEL_A_12_BITS = (byte) 0xAE;
	public static final byte CHANNEL_B_12_BITS = (byte) 0xBE;
}
