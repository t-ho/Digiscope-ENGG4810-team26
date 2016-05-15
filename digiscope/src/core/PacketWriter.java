package core;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import data.Packet;
import data.PacketType;

/**
 *
 * @author ToanHo
 */
public class PacketWriter {
	private DataOutputStream dos_;
	
	public PacketWriter(OutputStream os) {
		dos_ = new DataOutputStream(new BufferedOutputStream(os));
	}

	public synchronized void writePacket(Packet packet) throws IOException {
		
		if(packet.isPacket(PacketType.VERTICAL_RANGE)) {
			dos_.writeByte(PacketType.VERTICAL_RANGE);
			//write the content here
		} else if(packet.isPacket(PacketType.HORIZONTAL_RANGE)) {
			
		} else if(packet.isPacket(PacketType.TRIGGER_MODE)) {
			
		} else if(packet.isPacket(PacketType.TRIGGER_TYPE)) {
			
		} else if(packet.isPacket(PacketType.TRIGGER_TYPE)) {
			
		} else if(packet.isPacket(PacketType.TRIGGER_THRESHOLD)) {
			
		} else if(packet.isPacket(PacketType.CHANNEL_COUPLING)) {
			
		} else if(packet.isPacket(PacketType.CHANNEL_A_8_BITS)) {
			
		} else if(packet.isPacket(PacketType.CHANNEL_B_8_BITS)) {
			
		} else if(packet.isPacket(PacketType.CHANNEL_A_12_BITS)) {
			
		} else if(packet.isPacket(PacketType.CHANNEL_B_12_BITS)) {
			
		} else if(packet.isPacket(PacketType.KEEP_ALIVE)) {
			
		} else if(packet.isPacket(PacketType.FUNCTION_GENERATOR_OUTPUT)) {
			
		}
	}
	
	public void close() {
		if(dos_ != null) {
			try {
				dos_.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
