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

	public PacketWriter(OutputStream os) {
		dos_ = new DataOutputStream(new BufferedOutputStream(os));
	}

	public synchronized void writePacket(CommandPacket commandPacket) throws IOException {
		dos_.writeByte(commandPacket.getType());
		dos_.writeByte(commandPacket.getIndicator());
		dos_.writeInt(commandPacket.getArgument());
		dos_.flush();
	}

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
