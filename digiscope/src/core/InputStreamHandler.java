package core;

import java.io.IOException;

import data.Packet;
import data.PacketFormatException;
import data.PacketType;

/**
 *
 * @author ToanHo
 */
public class InputStreamHandler implements Runnable {

	private MainWindow mainWindow_;
	private PacketWriter packetWriter_;
	private PacketReader packetReader_;
	
	public InputStreamHandler(MainWindow mainWindow, PacketReader packetReader, PacketWriter packetWriter) {
		mainWindow_ = mainWindow;
		packetReader_ = packetReader;
		packetWriter_ = packetWriter;
	}
	
	@Override
	public void run() {
		// TODO
		try {
			Packet packet = packetReader_.nextPacket();
			while (packet != null) {
				byte type = packet.getType();
				if (type == PacketType.VERTICAL_RANGE) {

				} else if (type == PacketType.HORIZONTAL_RANGE) {

				} else if (type == PacketType.TRIGGER_MODE) {

				} else if (type == PacketType.TRIGGER_TYPE) {

				} else if (type == PacketType.TRIGGER_TYPE) {

				} else if (type == PacketType.TRIGGER_THRESHOLD) {

				} else if (type == PacketType.CHANNEL_COUPLING) {

				} else if (type == PacketType.CHANNEL_A_8_BITS) {

				} else if (type == PacketType.CHANNEL_B_8_BITS) {

				} else if (type == PacketType.CHANNEL_A_12_BITS) {

				} else if (type == PacketType.CHANNEL_B_12_BITS) {

				} else if (type == PacketType.KEEP_ALIVE) {

				} else if (type == PacketType.FUNCTION_GENERATOR_OUTPUT) {

				}
			}
		} catch (PacketFormatException pfe) {
			pfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
