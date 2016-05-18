package data;

/**
 *
 * @author ToanHo
 */
public class CommandPacket extends Packet {
	private byte indicator_;
	private int argument_;
	
	public CommandPacket(byte type, byte indicator, int argument) {
		super(type);
		indicator_ = indicator;
		argument_ = argument;
	}
	
	public byte getIndicator() {
		return indicator_;
	}

	public void setIndicator(byte indicator) {
		this.indicator_ = indicator;
	}

	public int getArgument() {
		return argument_;
	}

	public void setArgument(int argument_) {
		this.argument_ = argument_;
	}
	
}
