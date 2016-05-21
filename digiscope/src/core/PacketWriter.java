package core;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import data.CommandPacket;

/**
 *
 * @author ToanHo
 */
public class PacketWriter {
	private DataOutputStream dos_;

	/**
	 * Constructor
	 * @param outputStream
	 */
	public PacketWriter(OutputStream outputStream) {
		dos_ = new DataOutputStream(new BufferedOutputStream(outputStream));
	}

	/**
	 * Write the specified packet to the output stream
	 * @param commandPacket
	 * @throws IOException
	 */
	public synchronized void writePacket(CommandPacket commandPacket) throws IOException {
		dos_.writeByte(commandPacket.getType());
		dos_.writeByte(commandPacket.getIndicator());
		dos_.writeInt(commandPacket.getArgument());
		dos_.flush();
	}

	/**
	 * Close the packet writer
	 */
	public void close() {
		if (dos_ != null) {
			try {
				dos_.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
