package core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import data.CommandPacket;
import data.DataPacket;
import data.Packet;
import data.PacketFormatException;
import data.PacketType;

/**
 *
 * @author ToanHo
 */
public class PacketReader {
	private DataInputStream dis_;

	public PacketReader(InputStream is) {
		dis_ = new DataInputStream(new BufferedInputStream(is)) ;
	}
	
	public synchronized Packet nextPacket() throws IOException, PacketFormatException{
		//TODO:
		Packet packet = null;
		byte type = dis_.readByte();
		byte indicator;
		short sequenceNumber;
		short nSamples;
		short[] samples;
		int totalPacketSize;
		switch (type) {
			case PacketType.VERTICAL_RANGE :
				// Fallthrough

			case PacketType.HORIZONTAL_RANGE :
				// Fallthrough

			case PacketType.TRIGGER_MODE :
				// Fallthrough

			case PacketType.TRIGGER_TYPE :
				// Fallthrough

			case PacketType.TRIGGER_THRESHOLD :
				// Fallthrough

			case PacketType.CHANNEL_COUPLING :
				// Fallthrough

			case PacketType.FUNCTION_GENERATOR_OUTPUT :
				indicator = dis_.readByte();
				int argument = dis_.readInt();
				packet = new CommandPacket(type, indicator, argument);
				break;
		
			case PacketType.CHANNEL_A_8_BITS :
				// Fallthrough

			case PacketType.CHANNEL_B_8_BITS :
				sequenceNumber = (short) dis_.readByte();
				nSamples = dis_.readShort();
				totalPacketSize = 4 + nSamples;
				if(totalPacketSize > Packet.MAX_PACKET_SIZE) {
					throw new PacketFormatException("Exceeds the maximum packet size.");
				}
				samples = new short[nSamples];
				for(short i = 0; i < nSamples; i++) {
					samples[i] = (short) dis_.readByte();
				}
				packet = new DataPacket(type, sequenceNumber, nSamples, samples);
				break;
				
			case PacketType.CHANNEL_A_12_BITS :
				// Fallthrough

			case PacketType.CHANNEL_B_12_BITS :
				sequenceNumber = (short) dis_.readByte();
				nSamples = dis_.readShort();
				totalPacketSize = 4 + nSamples * 2;
				if(totalPacketSize > Packet.MAX_PACKET_SIZE) {
					throw new PacketFormatException("Exceeds the maximum packet size.");
				}
				samples = new short[nSamples];
				for(short i = 0; i < nSamples; i++) {
					samples[i] = dis_.readShort();
				}
				packet = new DataPacket(type, sequenceNumber, nSamples, samples);
				break;
		
			case PacketType.KEEP_ALIVE :
				break;
				
			default :
				// Ignore 
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
