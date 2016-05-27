package core;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.jfree.data.xy.XYSeries;

import data.CommandPacket;
import data.Constant;
import data.DataPacket;
import data.Packet;
import data.PacketFormatException;
import data.PacketType;

/**
 *
 * @author ToanHo
 */
public class InputStreamHandler extends Thread {

	private MainWindow mainWindow_;
	private PacketWriter packetWriter_;
	private PacketReader packetReader_;
	private boolean isComplete_;

	public InputStreamHandler(MainWindow mainWindow, PacketReader packetReader, PacketWriter packetWriter) {
		mainWindow_ = mainWindow;
		packetReader_ = packetReader;
		packetWriter_ = packetWriter;
		isComplete_ = false;
	}

	@Override
	public void run() {
		try {
			int aCurrentTime = 0;
			int aPeriod = 0;
			int bCurrentTime = 0;
			int bPeriod = 0;
			double aMaxDiplayVoltage = 0;
			double aMinDiplayVoltage = 0;
			double bMaxDiplayVoltage = 0;
			double bMinDiplayVoltage = 0;
			double maxDisplayTime = 0;
			boolean isUpdatedA = false;
			boolean isUpdatedB = false;
			int aTotalOfSamples = 0;
			int bTotalOfSamples = 0;
			XYSeries aSeries = new XYSeries(Constant.CHANNEL_A);
			XYSeries bSeries = new XYSeries(Constant.CHANNEL_B);
			while (isComplete_ == false) {
				Packet packet = packetReader_.nextPacket();
				if (packet != null) {
					byte type = packet.getType();
					if (packet instanceof CommandPacket) {
						CommandPacket commandPacket = (CommandPacket) packet;
						/// Test
						if(type != PacketType.KEEP_ALIVE) {
							System.out.printf("Received: Type %2x Indicator %2x argument %d\n\n",commandPacket.getType(), commandPacket.getIndicator(), commandPacket.getArgument());
						}
						/// Test
						byte indicator = commandPacket.getIndicator();
						if (indicator == Constant.CONFIRMATION) {
							switch (type) {
							case PacketType.VERTICAL_RANGE_A:
								mainWindow_.setVerticalRange(Constant.CHANNEL_A, commandPacket.getArgument());
								break;

							case PacketType.VERTICAL_RANGE_B:
								mainWindow_.setVerticalRange(Constant.CHANNEL_B, commandPacket.getArgument());
								break;

							case PacketType.HORIZONTAL_RANGE:
								mainWindow_.setHorizontalRange(commandPacket.getArgument());
								break;
								
							case PacketType.TRIGGER_STATE:
								mainWindow_.setTriggerState(commandPacket.getArgument());
								break;
								
							case PacketType.TRIGGER_MODE:
								mainWindow_.setTriggerMode(commandPacket.getArgument());
								break;

							case PacketType.TRIGGER_TYPE:
								mainWindow_.setTriggerType(commandPacket.getArgument());
								break;

							case PacketType.TRIGGER_THRESHOLD:
								mainWindow_.setTriggerThreshold(commandPacket.getArgument());
								break;
								
							case PacketType.TRIGGER_ARM:
								mainWindow_.setReArmTrigger(commandPacket.getArgument());
								break;

							case PacketType.CHANNEL_COUPLING_A:
								mainWindow_.setChannelCoupling(Constant.CHANNEL_A, commandPacket.getArgument());
								break;

							case PacketType.CHANNEL_COUPLING_B:
								mainWindow_.setChannelCoupling(Constant.CHANNEL_B, commandPacket.getArgument());
								break;

							case PacketType.SAMPLING_MODE:
								mainWindow_.setSamplingMode(commandPacket.getArgument());
								break;

							case PacketType.DC_OFFSET_A:
								mainWindow_.setVerticalOffset(Constant.CHANNEL_A, commandPacket.getArgument());
								break;
							
							case PacketType.DC_OFFSET_B:
								mainWindow_.setVerticalOffset(Constant.CHANNEL_B, commandPacket.getArgument());
								break;
							
							case PacketType.GENERATOR_OUTPUT:
								mainWindow_.setGeneratorOutput(commandPacket.getArgument());
								break;
								
							case PacketType.WAVE_TYPE:
								mainWindow_.setWaveType(commandPacket.getArgument());
								break;

							case PacketType.GENERATOR_VOLTAGE:
								mainWindow_.setP2PVoltage(commandPacket.getArgument());
								break;

							case PacketType.GENERATOR_OFFSET:
								mainWindow_.setVerticalOffset(Constant.GENERATOR_CHANNEL, commandPacket.getArgument());
								break;

							case PacketType.GENERATOR_FREQUENCY:
								mainWindow_.setGeneratorFrequency(commandPacket.getArgument());
								break;

							case PacketType.NUMBER_OF_SAMPLES:
								mainWindow_.setNoOfSamples(commandPacket.getArgument());
								break;
							}
						}
					} else if (packet instanceof DataPacket) {
						DataPacket dataPacket = (DataPacket) packet;
						short sequenceNumber = dataPacket.getSequenceNumber();
						short nSamples = dataPacket.getNumberOfSamples();
						short samples[] = dataPacket.getSamples();
						switch (type) {
						case PacketType.CHANNEL_A_8_BITS:
							if (sequenceNumber == 0) {
								if (isUpdatedA == true) {
									mainWindow_.setXYSeries(Constant.CHANNEL_A, aSeries, false);
								}
								isUpdatedA = false;
								aSeries = new XYSeries(Constant.CHANNEL_A);
								aCurrentTime = 0;
								aPeriod = dataPacket.getPeriod();
								aMaxDiplayVoltage = mainWindow_.getMaxDisplayVoltage(Constant.A_INDEX);
								aMinDiplayVoltage = mainWindow_.getMinDisplayVoltage(Constant.A_INDEX);
								maxDisplayTime = mainWindow_.getMaxDisplayTime();
								aTotalOfSamples = 0;
							}
							for (short i = 0; i < nSamples; i++) {
								double voltage = (aMaxDiplayVoltage - aMinDiplayVoltage) * ((double) samples[i] / 255.0)
										+ aMinDiplayVoltage;
								aSeries.add(aCurrentTime, voltage);
								aCurrentTime += aPeriod;
								aTotalOfSamples += 1;
							}
							if (isUpdatedA == false) {
								if (aCurrentTime >= maxDisplayTime) {
									mainWindow_.setXYSeries(Constant.CHANNEL_A, aSeries, true);
									isUpdatedA = true;
								} else if (aTotalOfSamples == mainWindow_.getNoOfSamples()) {
									mainWindow_.setXYSeries(Constant.CHANNEL_A, aSeries, true);
									isUpdatedA = false;
								}
							}
							break;

						case PacketType.CHANNEL_B_8_BITS:
							if (sequenceNumber == 0) {
								if (isUpdatedB == true) {
									mainWindow_.setXYSeries(Constant.CHANNEL_B, bSeries, false);
								}
								isUpdatedB = false;
								bSeries = new XYSeries(Constant.CHANNEL_B);
								bCurrentTime = 0;
								bPeriod = dataPacket.getPeriod();
								bMaxDiplayVoltage = mainWindow_.getMaxDisplayVoltage(Constant.B_INDEX);
								bMinDiplayVoltage = mainWindow_.getMinDisplayVoltage(Constant.B_INDEX);
								maxDisplayTime = mainWindow_.getMaxDisplayTime();
								bTotalOfSamples = 0;
							}
							for (short i = 0; i < nSamples; i++) {
								double voltage = (bMaxDiplayVoltage - bMinDiplayVoltage) * ((double) samples[i] / 255.0)
										+ bMinDiplayVoltage;
								bSeries.add(bCurrentTime, voltage);
								bCurrentTime += bPeriod;
								bTotalOfSamples += 1;
							}
							if (isUpdatedB == false) {
								if (bCurrentTime >= maxDisplayTime) {
									mainWindow_.setXYSeries(Constant.CHANNEL_B, bSeries, true);
									isUpdatedB = true;
								} else if (bTotalOfSamples == mainWindow_.getNoOfSamples()) {
									mainWindow_.setXYSeries(Constant.CHANNEL_B, bSeries, true);
									isUpdatedB = false;
								}
							}
							break;

						case PacketType.CHANNEL_A_12_BITS:
							if (sequenceNumber == 0) {
								if (isUpdatedA == true) {
									mainWindow_.setXYSeries(Constant.CHANNEL_A, aSeries, false);
								}
								isUpdatedA = false;
								aSeries = new XYSeries(Constant.CHANNEL_A);
								aCurrentTime = 0;
								aPeriod = dataPacket.getPeriod();
								aMaxDiplayVoltage = mainWindow_.getMaxDisplayVoltage(Constant.A_INDEX);
								aMinDiplayVoltage = mainWindow_.getMinDisplayVoltage(Constant.A_INDEX);
								maxDisplayTime = mainWindow_.getMaxDisplayTime();
								aTotalOfSamples = 0;
							}
							for (short i = 0; i < nSamples; i++) {
								double voltage = (aMaxDiplayVoltage - aMinDiplayVoltage)
										* ((double) samples[i] / 4095.0) + aMinDiplayVoltage;
								aSeries.add(aCurrentTime, voltage);
								aCurrentTime += aPeriod;
								aTotalOfSamples += 1;
							}
							if (isUpdatedA == false) {
								if (aCurrentTime >= maxDisplayTime) {
									mainWindow_.setXYSeries(Constant.CHANNEL_A, aSeries, true);
									isUpdatedA = true;
								} else if (aTotalOfSamples == mainWindow_.getNoOfSamples()) {
									mainWindow_.setXYSeries(Constant.CHANNEL_A, aSeries, true);
									isUpdatedA = false;
								}
							}
							break;

						case PacketType.CHANNEL_B_12_BITS:
							if (sequenceNumber == 0) {
								if (isUpdatedB == true) {
									mainWindow_.setXYSeries(Constant.CHANNEL_B, bSeries, false);
								}
								isUpdatedB = false;
								bSeries = new XYSeries(Constant.CHANNEL_B);
								bCurrentTime = 0;
								bPeriod = dataPacket.getPeriod();
								bMaxDiplayVoltage = mainWindow_.getMaxDisplayVoltage(Constant.B_INDEX);
								bMinDiplayVoltage = mainWindow_.getMinDisplayVoltage(Constant.B_INDEX);
								maxDisplayTime = mainWindow_.getMaxDisplayTime();
								bTotalOfSamples = 0;
							}
							for (short i = 0; i < nSamples; i++) {
								double voltage = (bMaxDiplayVoltage - bMinDiplayVoltage)
										* ((double) samples[i] / 4095.0) + bMinDiplayVoltage;
								bSeries.add(bCurrentTime, voltage);
								bCurrentTime += bPeriod;
								bTotalOfSamples += 1;
							}
							if (isUpdatedB == false) {
								if (bCurrentTime >= maxDisplayTime) {
									mainWindow_.setXYSeries(Constant.CHANNEL_B, bSeries, true);
									isUpdatedB = true;
								} else if (bTotalOfSamples == mainWindow_.getNoOfSamples()) {
									mainWindow_.setXYSeries(Constant.CHANNEL_B, bSeries, true);
									isUpdatedB = false;
								}
							}
							break;

						}
					}
				}
			}
		} catch (PacketFormatException pfe) {
			pfe.printStackTrace();
		} catch (IOException e) {
			if(e.getMessage() == null) { // lost connection
				JOptionPane.showMessageDialog(mainWindow_, "Connection has been lost! Please reconnect!", "Error",
						JOptionPane.ERROR_MESSAGE);
				mainWindow_.dispose();
			}
		}
	}
	
	/**
	 * Stop the thread
	 */
	public void stopExecute() {
		isComplete_ = true;
		packetReader_.close();
		packetWriter_.close();
	}

}
