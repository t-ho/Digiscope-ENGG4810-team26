package data;

/**
 *
 * @author ToanHo
 */
public class PacketType {

	public static final byte VERTICAL_RANGE = (byte) 0x01;
	public static final byte HORIZONTAL_RANGE = (byte) 0x02;
	public static final byte TRIGGER_MODE = (byte) 0x03;
	public static final byte TRIGGER_TYPE = (byte) 0x04;
	public static final byte TRIGGER_THRESHOLD = (byte) 0x05;
	public static final byte CHANNEL_COUPLING = (byte) 0x0C;
	public static final byte CHANNEL_A_8_BITS = (byte) 0xD1;
	public static final byte CHANNEL_B_8_BITS = (byte) 0xD2;
	public static final byte CHANNEL_A_12_BITS = (byte) 0xE1;
	public static final byte CHANNEL_B_12_BITS = (byte) 0xE2;
	public static final byte KEEP_ALIVE = (byte) 0xEE;
	public static final byte FUNCTION_GENERATOR_OUTPUT = (byte) 0xF0;
}
