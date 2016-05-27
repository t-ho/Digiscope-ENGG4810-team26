package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import data.CommandPacket;
import data.Constant;
import data.Packet;
import data.PacketFormatException;
import data.PacketType;

/**
 * This class is used for testing purpose only
 * 
 * @author ToanHo
 */

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket serverSocket = null;
		// listen on port 4810
		try {
			serverSocket = new ServerSocket(Constant.PORT_NUMBER);
			System.out.println("<TCPServer> Server is activated, listening on port: " + Constant.PORT_NUMBER);
			Socket connSocket = null;
			while (true) {
				try {
					// block, waiting for a conn. request
					connSocket = serverSocket.accept();
					// At this point, we have a connection
					System.out.println("Connection accepted from: " + connSocket.getInetAddress().getHostName());
				} catch (IOException e) {
					e.printStackTrace();
				}
				PacketReader packetReader;
				PacketWriter packetWriter;
				try {
					packetReader = new PacketReader(connSocket.getInputStream());
					packetWriter = new PacketWriter(connSocket.getOutputStream());
					while (true) {
						Packet packet = packetReader.nextPacket();
						if (packet != null) {
							if (packet instanceof CommandPacket) {
								CommandPacket commandPacket = (CommandPacket) packet;
								System.out.println("Received command packet");
								if (commandPacket.getIndicator() == Constant.REQUEST) {
									commandPacket.setIndicator(Constant.CONFIRMATION);
									packetWriter.writePacket(commandPacket);
									String message = "";
									switch (commandPacket.getType()) {
									case PacketType.VERTICAL_RANGE_A:
										message = "Vertical Range A: " + commandPacket.getArgument();
										break;

									case PacketType.VERTICAL_RANGE_B:
										message = "Vertical Range B: " + commandPacket.getArgument();
										break;

									case PacketType.HORIZONTAL_RANGE:
										message = "Horizontal Range B: " + commandPacket.getArgument();
										break;

									case PacketType.TRIGGER_MODE:
										message = "Trigger mode A: " + commandPacket.getArgument();
										break;

									case PacketType.TRIGGER_TYPE:
										message = "Trigger type A: " + commandPacket.getArgument();
										break;
										
									case PacketType.SAMPLING_MODE:
										message = "Channel mode A: " + commandPacket.getArgument();
										break;
										
									case PacketType.CHANNEL_COUPLING_A:
										message = "Channel coupling A; " + commandPacket.getArgument();
										break;
										
									case PacketType.CHANNEL_COUPLING_B:
										message = "Channel coupling B: " + commandPacket.getArgument();
										break;
									}
									System.out.println("Sent confirmation: " + message + "\n");
								}
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Disconnected from client");
				} catch (PacketFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
	}

}
