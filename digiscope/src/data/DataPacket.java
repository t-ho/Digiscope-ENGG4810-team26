package data;

/**
 *
 * @author ToanHo
 */
public class DataPacket extends Packet {
	private short sequenceNumber_;
	private short nSamples_;
	private short[] samples_;
	
	public DataPacket(byte type, short sequenceNumber, short nSamples, short[] samples) {
		super(type);
		sequenceNumber_ = sequenceNumber;
		nSamples_ = nSamples;
		samples_ = samples;
	}
	
	public short getSequenceNumber() {
		return sequenceNumber_;
	}

	public void setSequenceNumber(short sequenceNumber_) {
		this.sequenceNumber_ = sequenceNumber_;
	}

	public short getNumberOfSamples() {
		return nSamples_;
	}

	public void setNumberOfSamples(short nSamples) {
		this.nSamples_ = nSamples;
	}

	public short[] getSamples() {
		return samples_;
	}

	public void setSamples(short[] samples_) {
		this.samples_ = samples_;
	}
}