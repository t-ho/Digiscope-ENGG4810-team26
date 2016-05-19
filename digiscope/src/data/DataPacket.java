package data;

/**
 *
 * @author ToanHo
 */
public class DataPacket extends Packet {
	private short sequenceNumber_;
	private short nSamples_;
	private short period_;
	private short[] samples_;
	
	/**
	 * Constructor for DataPacket
	 * @param type
	 * @param sequenceNumber
	 * @param nSamples
	 * @param period
	 * @param samples
	 */
	public DataPacket(byte type, short sequenceNumber, short nSamples, short period, short[] samples) {
		super(type);
		sequenceNumber_ = sequenceNumber;
		nSamples_ = nSamples;
		period_ = period;
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

	public short getPeriod() {
		return period_;
	}

	public void setPeriod(short period_) {
		this.period_ = period_;
	}
}