package core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import data.Packet;
import data.PacketType;

/**
 *
 * @author ToanHo
 */
public class PacketReader {
	private DataInputStream dis_;

	public PacketReader(InputStream is) {
		dis_ = new DataInputStream(new BufferedInputStream(is));
	}
	
	public synchronized Packet nextPacket() throws IOException{
		//TODO:
		Packet packet = null;
		byte b = dis_.readByte();
		if(b == PacketType.VERTICAL_RANGE) {
			
		} else if(b == PacketType.HORIZONTAL_RANGE) {
			
		} else if(b == PacketType.TRIGGER_MODE) {
			
		} else if(b == PacketType.TRIGGER_TYPE) {
			
		} else if(b == PacketType.TRIGGER_TYPE) {
			
		} else if(b == PacketType.TRIGGER_THRESHOLD) {
			
		} else if(b == PacketType.CHANNEL_COUPLING) {
			
		} else if(b == PacketType.CHANNEL_A_8_BITS) {
			
		} else if(b == PacketType.CHANNEL_B_8_BITS) {
			
		} else if(b == PacketType.CHANNEL_A_12_BITS) {
			
		} else if(b == PacketType.CHANNEL_B_12_BITS) {
			
		} else if(b == PacketType.KEEP_ALIVE) {
			
		} else if(b == PacketType.FUNCTION_GENERATOR_OUTPUT) {
			
		}
		return packet;
	}
	
	public void close() {
		if(dis_ != null) {
			try {
				dis_.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
