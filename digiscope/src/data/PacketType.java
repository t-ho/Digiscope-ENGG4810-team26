package data;

/**
 *
 * @author ToanHo
 */
public class PacketType {
	public static final byte UNKNOWN = (byte) 0x00;
	public static final byte HORIZONTAL_RANGE = (byte) 0x02;
	public static final byte TRIGGER_MODE = (byte) 0x03;
	public static final byte TRIGGER_TYPE = (byte) 0x04;
	public static final byte TRIGGER_THRESHOLD = (byte) 0x05;
	public static final byte SAMPLING_MODE = (byte) 0x07;
	public static final byte TRIGGER_STATE = (byte) 0x08;
	public static final byte TRIGGER_ARM = (byte) 0x0A;
	public static final byte TRIGGER_FORCE = (byte) 0x0F;
	
	public static final byte NUMBER_OF_SAMPLES = (byte) 0x71;
	
	public static final byte VERTICAL_RANGE_A = (byte) 0xA1;
	public static final byte DC_OFFSET_A = (byte) 0xA6;
	public static final byte CHANNEL_COUPLING_A = (byte) 0xAC;
	public static final byte CHANNEL_A_8_BITS = (byte) 0xAD;
	public static final byte CHANNEL_A_12_BITS = (byte) 0xAE;

	public static final byte VERTICAL_RANGE_B = (byte) 0xB1;
	public static final byte DC_OFFSET_B = (byte) 0xB6;
	public static final byte CHANNEL_COUPLING_B = (byte) 0xBC;
	public static final byte CHANNEL_B_8_BITS = (byte) 0xBD;
	public static final byte CHANNEL_B_12_BITS = (byte) 0xBE;

	public static final byte DISPLAY_POWER = (byte) 0xD0;
	public static final byte BACKLIGHT_BRIGHTNESS = (byte) 0xD1;
	public static final byte KEEP_ALIVE = (byte) 0xEE;

	public static final byte GENERATOR_OUTPUT = (byte) 0xF0;
	public static final byte WAVE_TYPE = (byte) 0xF1;
	public static final byte GENERATOR_VOLTAGE = (byte) 0xF2;
	public static final byte GENERATOR_OFFSET = (byte) 0xF3;
	public static final byte GENERATOR_FREQUENCY = (byte) 0xF4;
}