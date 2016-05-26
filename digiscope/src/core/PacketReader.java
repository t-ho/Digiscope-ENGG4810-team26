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

	/**
	 * Constructor
	 * @param inputStream
	 */
	public PacketReader(InputStream inputStream) {
		dis_ = new DataInputStream(new BufferedInputStream(inputStream)) ;
	}
	
	/**
	 * Read next packet from the input stream
	 * @return next packet from the input stream. Otherwise, null 
	 * @throws IOException
	 * @throws PacketFormatException
	 */
	public synchronized Packet nextPacket() throws IOException, PacketFormatException {
		//TODO:
		Packet packet = null;
		byte type = dis_.readByte();
		byte indicator;
		short sequenceNumber;
		short nSamples;
		short period;
		short[] samples;
		int totalPacketSize;
		switch (type) {
			case PacketType.HORIZONTAL_RANGE :
				// Fallthrough

			case PacketType.VERTICAL_RANGE_A :
				// Fallthrough
				
			case PacketType.VERTICAL_RANGE_B :
				// Fallthrough

			case PacketType.TRIGGER_STATE_A:
				// Fallthrough
				
			case PacketType.TRIGGER_STATE_B:
				// Fallthrough

			case PacketType.TRIGGER_MODE_A :
				// Fallthrough

			case PacketType.TRIGGER_MODE_B :
				// Fallthrough

			case PacketType.TRIGGER_TYPE_A :
				// Fallthrough

			case PacketType.TRIGGER_TYPE_B :
				// Fallthrough

			case PacketType.TRIGGER_THRESHOLD_A :
				// Fallthrough

			case PacketType.TRIGGER_THRESHOLD_B :
				// Fallthrough

			case PacketType.DC_OFFSET_A :
				// Fallthrough
			
			case PacketType.DC_OFFSET_B :
				// Fallthrough
			
			case PacketType.NUMBER_OF_SAMPLES :
				// Fallthrough

			case PacketType.TRIGGER_ARM_A :
				// Fallthrough
			
			case PacketType.TRIGGER_ARM_B :
				// Fallthrough

			case PacketType.TRIGGER_FORCE_A :
				// Fallthrough
			
			case PacketType.TRIGGER_FORCE_B :
				// Fallthrough

			case PacketType.CHANNEL_COUPLING_A :
				// Fallthrough

			case PacketType.CHANNEL_COUPLING_B :
				// Fallthrough
			
			case PacketType.CHANNEL_MODE_A :
				// Fallthrough
				
			case PacketType.CHANNEL_MODE_B :
				// Fallthrough

			case PacketType.GENERATOR_OUTPUT :
				// Fallthrough
				
			case PacketType.WAVE_TYPE :
				// Fallthrough

			case PacketType.GENERATOR_VOLTAGE :
				// Fallthrough
				
			case PacketType.GENERATOR_OFFSET :
				// Fallthrough

			case PacketType.GENERATOR_FREQUENCY :
				// Fallthrough
				
			case PacketType.KEEP_ALIVE :
				// Fallthrough

			case PacketType.DISPLAY_POWER :
				// Fallthrough
				
			case PacketType.BACKLIGHT_BRIGHTNESS :
				indicator = dis_.readByte();
				int argument = dis_.readInt();
				packet = new CommandPacket(type, indicator, argument);
				break;
		
			case PacketType.CHANNEL_A_8_BITS :
				// Fallthrough

			case PacketType.CHANNEL_B_8_BITS :
				sequenceNumber = (short) dis_.readByte();
				nSamples = dis_.readShort();
				// Skip period for now
				period = dis_.readShort();
				totalPacketSize = 6 + nSamples;
				if(totalPacketSize > Packet.MAX_PACKET_SIZE) {
					throw new PacketFormatException("Exceeds the maximum packet size.");
				}
				samples = new short[nSamples];
				for(short i = 0; i < nSamples; i++) {
					samples[i] = (short) dis_.readByte();
				}
				packet = new DataPacket(type, sequenceNumber, nSamples, period, samples);
				break;
				
			case PacketType.CHANNEL_A_12_BITS :
				// Fallthrough

			case PacketType.CHANNEL_B_12_BITS :
				sequenceNumber = (short) dis_.readByte();
				nSamples = dis_.readShort();
				// Skip period for now
				period = dis_.readShort();
				totalPacketSize = 6 + nSamples * 2;
				if(totalPacketSize > Packet.MAX_PACKET_SIZE) {
					throw new PacketFormatException("Exceeds the maximum packet size.");
				}
				samples = new short[nSamples];
				for(short i = 0; i < nSamples; i++) {
					samples[i] = Short.reverseBytes(dis_.readShort());
				}
				packet = new DataPacket(type, sequenceNumber, nSamples, period, samples);
				break;
		
			default :
				// Ignore 
		}
		return packet;
	}
	
	/**
	 * Close packet reader
	 */
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
