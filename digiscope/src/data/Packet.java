package data;

/**
 *
 * @author ToanHo
 */
public class Packet {
	public static short MAX_PACKET_SIZE = 1024;
	
	private byte type_;
	
	public Packet() {
		setType((byte) 0x00);
	}
	
	public Packet(byte type) {
		setType(type);
	}

	/**
	 * Return the type of the packet
	 * @return byte
	 */
	public byte getType() {
		return type_;
	}

	public void setType(byte type_) {
		this.type_ = type_;
	}

	public boolean isPacket(byte type) {
		if(type_ == type) {
			return true;
		} else {
			return false;
		}
	}
}
