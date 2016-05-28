package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RectangleEdge;

import data.CommandPacket;
import data.Constant;
import data.FilterFile;
import data.PacketType;
import gui.FileChooserUi;
import gui.MainWindowUi;

/**
 *
 * @author ToanHo
 */
public class MainWindow extends MainWindowUi
		implements ChartMouseListener, ItemListener, ActionListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	private LaunchWindow launchWindow_;
	private Visualizer visualizer_;
	private ChartPanel chartPanel_;
	private Crosshair timeCrosshair_;
	private Crosshair voltageCrosshair_;
	private int measuredChannelIndex_; // used for cursor measurement
	private FilterFile filterFile_;
	private Socket socket_;
	private PacketWriter packetWriter_;
	private PacketReader packetReader_;
	private InputStreamHandler inputStreamHandler_;

	private int previousVerticalRangeAIndex_;
	private int previousVerticalRangeBIndex_;
	private int previousHorizontalRangeIndex_;
	private int previousTriggerModeIndex_;
	private int previousTriggerTypeIndex_;
	private int previousVerticalOffsetAValue_;
	private int previousVerticalOffsetBValue_;
	private boolean sentVerticalOffsetACommand_;
	private boolean sentVerticalOffsetBCommand_;
	private int previousWaveTypeIndex_;
	private int previousNoOfSamples_;
	private boolean sentTriggerThresholdCommand_;
	private int previousTriggerThresholdValue_;
	private boolean sentVerticalOffsetGeneratorCommand_;
	private int previousVerticalOffsetGeneratorValue_;
	private boolean sentP2PVoltageCommand_;
	private int previousP2PVoltageValue_;
	private int previousGeneratorFrequency_;

	public MainWindow(LaunchWindow launchWindow) {
		super();
		initialize();
		addListenersToComponents();
		setLaunchWindow(launchWindow);
		addComponentToCanvasPanel(chartPanel_);
	}

	public MainWindow(LaunchWindow launchWindow, Socket socket) throws IOException {
		super();
		initialize();
		setLaunchWindow(launchWindow);
		setSocket(socket);
		packetWriter_ = new PacketWriter(socket.getOutputStream());
		packetReader_ = new PacketReader(socket.getInputStream());
		// Create a new thread to handle input stream
		inputStreamHandler_ = new InputStreamHandler(this, packetReader_, packetWriter_);
		inputStreamHandler_.start();
		addComponentToCanvasPanel(chartPanel_);
		addListenersToComponents();
	}

	private void initialize() {
		visualizer_ = new Visualizer();
		chartPanel_ = createDefaultChartPanel(visualizer_.getChart());
		filterFile_ = new FilterFile();
		previousVerticalRangeAIndex_ = verticalRangeAComboBox.getSelectedIndex();
		previousVerticalRangeBIndex_ = verticalRangeBComboBox.getSelectedIndex();
		previousHorizontalRangeIndex_ = horizontalRangeComboBox.getSelectedIndex();
		previousTriggerModeIndex_ = triggerModeComboBox.getSelectedIndex();
		triggerTypeComboBox.setSelectedIndex(Constant.RISING);
		previousTriggerTypeIndex_ = triggerTypeComboBox.getSelectedIndex();
		previousVerticalOffsetAValue_ = (int) verticalOffsetASpinner.getValue();
		previousVerticalOffsetBValue_ = (int) verticalOffsetBSpinner.getValue();
		sentVerticalOffsetACommand_ = false;
		sentVerticalOffsetBCommand_ = false;
		previousWaveTypeIndex_ = waveTypeComboBox.getSelectedIndex();
		previousNoOfSamples_ = (int) noOfSamplesSpinner.getValue();
		sentTriggerThresholdCommand_ = false;
		previousTriggerThresholdValue_ = (int) triggerThresholdSpinner.getValue();
		sentVerticalOffsetGeneratorCommand_ = false;
		previousVerticalOffsetGeneratorValue_ = (int) verticalOffsetGeneratorSpinner.getValue();
		sentP2PVoltageCommand_ = false;
		previousP2PVoltageValue_ = (int) p2pVoltageSpinner.getValue();
		previousGeneratorFrequency_ = (int) generatorFrequencySpinner.getValue();
		// test
		// Channel A
		XYSeries aSeries = new XYSeries(Constant.CHANNEL_A);
		for (double i = 0, j = 0; i < 25000; i = i + 1, j = j + 2 * Math.PI/200) {
			aSeries.add(i, 1 * Math.sin(j));
		}
//		 Test Filter
//		 aSeries = new XYSeries(Constant.CHANNEL_A);
//		 aSeries.add(0, 3);
//		 aSeries.add(1, 4);
//		 aSeries.add(2, 5);
//		 aSeries.add(3, 6);

		rawXYSeries.put(Constant.CHANNEL_A, aSeries);

		// Channel B
		XYSeries bSeries = new XYSeries(Constant.CHANNEL_B);
		for (double i = 0, j = 0; i < 25000; i = i + 1, j = j + (2 * Math.PI / 250)) {
			bSeries.add(i, 1.5 * Math.sin(j));
		}
		rawXYSeries.put(Constant.CHANNEL_B, bSeries);
	}

	private void addListenersToComponents() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event) {
				mainWindowClosed();
			}
		});

		channelTabbedPane.addChangeListener(this);

		channelACheckBox.addItemListener(this);

		channelBCheckBox.addItemListener(this);

		mathChannelCheckBox.addItemListener(this);

		filterChannelCheckBox.addItemListener(this);

		cursorComboBox.addItemListener(this);

		channelCouplingAToggleButton.addActionListener(this);

		channelCouplingBToggleButton.addActionListener(this);

		samplingModeToggleButton.addActionListener(this);

		horizontalRangeComboBox.addActionListener(this);

		verticalRangeAComboBox.addActionListener(this);

		verticalRangeBComboBox.addActionListener(this);

		verticalRangeMathComboBox.addActionListener(this);

		verticalRangeFilterComboBox.addActionListener(this);

		forceTriggerButton.addActionListener(this);

		triggerModeComboBox.addActionListener(this);

		triggerTypeComboBox.addActionListener(this);

		verticalOffsetUnitAComboBox.addActionListener(this);

		verticalOffsetUnitBComboBox.addActionListener(this);

		verticalOffsetASpinner.addChangeListener(this);

		verticalOffsetBSpinner.addChangeListener(this);

		verticalOffsetMathSpinner.addChangeListener(this);

		verticalOffsetFilterSpinner.addChangeListener(this);

		outputToggleButton.addActionListener(this);

		waveTypeComboBox.addActionListener(this);

		noOfSamplesSpinner.addChangeListener(this);

		measureAToggleButton.addActionListener(this);

		measureBToggleButton.addActionListener(this);

		measureMathToggleButton.addActionListener(this);

		measureFilterToggleButton.addActionListener(this);

		triggerThresholdUnitComboBox.addActionListener(this);

		triggerThresholdSpinner.addChangeListener(this);

		rearmTriggerButton.addActionListener(this);

		verticalOffsetGeneratorSpinner.addChangeListener(this);

		verticalOffsetUnitGeneratorComboBox.addActionListener(this);

		p2pVoltageSpinner.addChangeListener(this);

		p2pVoltageUnitComboBox.addActionListener(this);

		generatorFrequencySpinner.addChangeListener(this);

		horizontalOffsetASpinner.addChangeListener(this);

		horizontalOffsetBSpinner.addChangeListener(this);

		horizontalOffsetMathSpinner.addChangeListener(this);

		horizontalOffsetFilterSpinner.addChangeListener(this);

		verticalOffsetUnitMathComboBox.addItemListener(this);

		verticalOffsetUnitFilterComboBox.addItemListener(this);

		horizontalOffsetUnitAComboBox.addItemListener(this);

		horizontalOffsetUnitBComboBox.addItemListener(this);

		horizontalOffsetUnitMathComboBox.addItemListener(this);

		horizontalOffsetUnitFilterComboBox.addItemListener(this);

		newExpressionButton.addActionListener(this);

		editExpressionButton.addActionListener(this);

		removeExpressionButton.addActionListener(this);

		browseButton.addActionListener(this);

		inputChannelComboBox.addActionListener(this);

		removeFilterButton.addActionListener(this);
	}


	/**
	 * Send command packet to the device
	 * 
	 * @param packetType
	 *            packet type
	 * @param argument
	 */
	private void sendCommand(byte packetType, int argument) {
		CommandPacket commandPacket = new CommandPacket(packetType, Constant.REQUEST, argument);
		try {
			packetWriter_.writePacket(commandPacket);
			System.out.printf("Sent:     Type %2x Indicator %2x Argument %d\n", commandPacket.getType(),
					Constant.REQUEST, argument);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Connection has been lost! Please reconnect!", "Error",
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	/**
	 * Update inputChannelCombox's items for filter channel by adding available
	 * channels and remove unavailable ones to/from the list.
	 */
	public void updateInputChannelComboBox() {
		String selectedItem = (String) inputChannelComboBox.getSelectedItem();
		inputChannelComboBox.removeAllItems();
		inputChannelComboBox.addItem("Select channel");
		if (rawXYSeries.containsKey(Constant.CHANNEL_A)) {
			inputChannelComboBox.addItem(Constant.CHANNEL_A);
		}
		if (rawXYSeries.containsKey(Constant.CHANNEL_B)) {
			inputChannelComboBox.addItem(Constant.CHANNEL_B);
		}
		if (rawXYSeries.containsKey(Constant.MATH_CHANNEL)) {
			String expression = expressionTextArea.getText().trim();
			if (!expression.contains("F")) {
				inputChannelComboBox.addItem(Constant.MATH_CHANNEL);
			}
		}
		inputChannelComboBox.setSelectedItem(selectedItem);
	}

	/**
	 * Get the available derived channel for Math channel
	 * 
	 * @return List of channels available for Math channel
	 */
	public ArrayList<String> getAvailableDerivedChannelForMath() {
		ArrayList<String> result = new ArrayList<String>();
		if (rawXYSeries.containsKey(Constant.CHANNEL_A)) {
			result.add(Constant.CHANNEL_A);
		}
		if (rawXYSeries.containsKey(Constant.CHANNEL_B)) {
			result.add(Constant.CHANNEL_B);
		}
		if (rawXYSeries.containsKey(Constant.FILTER_CHANNEL)) {
			String inputChannelForMath = (String) inputChannelComboBox.getSelectedItem();
			if (!inputChannelForMath.equals(Constant.MATH_CHANNEL)) {
				result.add(Constant.FILTER_CHANNEL);
			}
		}
		return result;
	}

	/**
	 * Convert the voltage string to volts
	 * 
	 * @param voltString
	 *            The voltage string. e.g. 200 mV, 1 V,...
	 * @return the voltage in volts
	 */
	private double convertVoltageStringToVolts(String voltString) {
		double value = 0;
		switch (voltString) {
		case Constant.TWENTY_MILIVOLTS:
			value = 0.02;
			break;

		case Constant.FIFTY_MILIVOLTS:
			value = 0.05;
			break;

		case Constant.ONE_HUNDRED_MILIVOLTS:
			value = 0.1;
			break;

		case Constant.TWO_HUNDRED_MILIVOLTS:
			value = 0.2;
			break;

		case Constant.FIVE_HUNDRED_MILIVOLTS:
			value = 0.5;
			break;

		case Constant.ONE_VOLT:
			value = 1;
			break;

		case Constant.TWO_VOLTS:
			value = 2;
			break;

		default:
			System.err.println("Cannot convert the specified voltage string to volts");
		}
		return value;
	}

	/**
	 * Convert the voltage string to microvolts
	 * 
	 * @param voltString
	 *            The voltage string. e.g. 200 mV, 1 V,...
	 * @return the voltage in microvolts
	 */
	private int convertVoltageStringToMicrovolts(String voltString) {
		int value = 0;
		switch (voltString) {
		case Constant.TWENTY_MILIVOLTS:
			value = 20000;
			break;

		case Constant.FIFTY_MILIVOLTS:
			value = 50000;
			break;

		case Constant.ONE_HUNDRED_MILIVOLTS:
			value = 100000;
			break;

		case Constant.TWO_HUNDRED_MILIVOLTS:
			value = 200000;
			break;

		case Constant.FIVE_HUNDRED_MILIVOLTS:
			value = 500000;
			break;

		case Constant.ONE_VOLT:
			value = 1000000;
			break;

		case Constant.TWO_VOLTS:
			value = 2000000;
			break;

		default:
			System.err.println("Cannot convert the specified voltage string to milivolts");
		}
		return value;
	}

	/**
	 * Convert the microvolts to voltage string
	 * 
	 * @param milivolts
	 *            The specified milivolts
	 * @return the voltage string
	 */
	private String convertMicrovoltsToVoltageString(int microvolts) {
		String string = "";
		switch (microvolts) {
		case 20000:
			string = Constant.TWENTY_MILIVOLTS;
			break;

		case 50000:
			string = Constant.FIFTY_MILIVOLTS;
			break;

		case 100000:
			string = Constant.ONE_HUNDRED_MILIVOLTS;
			break;

		case 200000:
			string = Constant.TWO_HUNDRED_MILIVOLTS;
			break;

		case 500000:
			string = Constant.FIVE_HUNDRED_MILIVOLTS;
			break;

		case 1000000:
			string = Constant.ONE_VOLT;
			break;

		case 2000000:
			string = Constant.TWO_VOLTS;
			break;

		default:
			System.err.println("Cannot convert " + microvolts + " milivolts to voltage string.");
		}
		return string;
	}

	/**
	 * Convert time string to micro seconds
	 * 
	 * @param timeString
	 *            The time string. e.g. 1 us, 500 ms
	 * @return the microseconds
	 */
	private int convertTimeStringToMicroSeconds(String timeString) {
		int value = 0;
		switch (timeString) {
		case Constant.ONE_MICROSECOND:
			value = 1;
			break;

		case Constant.TWO_MICROSECONDS:
			value = 2;
			break;

		case Constant.FIVE_MICROSECONDS:
			value = 5;
			break;

		case Constant.TEN_MICROSECONDS:
			value = 10;
			break;

		case Constant.TWENTY_MICROSECONDS:
			value = 20;
			break;

		case Constant.FIFTY_MICROSECONDS:
			value = 50;
			break;

		case Constant.ONE_HUNDRED_MICROSECONDS:
			value = 100;
			break;

		case Constant.TWO_HUNDRED_MICROSECONDS:
			value = 200;
			break;

		case Constant.FIVE_HUNDRED_MICROSECONDS:
			value = 500;
			break;

		case Constant.ONE_MILISECOND:
			value = 1000;
			break;

		case Constant.TWO_MILISECONDS:
			value = 2000;
			break;

		case Constant.FIVE_MILISECONDS:
			value = 5000;
			break;

		case Constant.TEN_MILISECONDS:
			value = 10000;
			break;

		case Constant.TWENTY_MILISECONDS:
			value = 20000;
			break;

		case Constant.FIFTY_MILISECONDS:
			value = 50000;
			break;

		case Constant.ONE_HUNDRED_MILISECONDS:
			value = 100000;
			break;

		case Constant.TWO_HUNDRED_MILISECONDS:
			value = 200000;
			break;

		case Constant.FIVE_HUNDRED_MILISECONDS:
			value = 500000;
			break;

		case Constant.ONE_SECOND:
			value = 1000000;
			break;

		default:
			System.err.println("Cannot convert the specified time string to microseconds");
		}
		return value;
	}

	/**
	 * Convert micro seconds to time string
	 * 
	 * @param microSeconds
	 * @return the time string
	 */
	private String convertMicroSecondsToTimeString(int microSeconds) {
		String timeString = "";
		switch (microSeconds) {
		case 1:
			timeString = Constant.ONE_MICROSECOND;
			break;

		case 2:
			timeString = Constant.TWO_MICROSECONDS;
			break;

		case 5:
			timeString = Constant.FIVE_MICROSECONDS;
			break;

		case 10:
			timeString = Constant.TEN_MICROSECONDS;
			break;

		case 20:
			timeString = Constant.TWENTY_MICROSECONDS;
			break;

		case 50:
			timeString = Constant.FIFTY_MICROSECONDS;
			break;

		case 100:
			timeString = Constant.ONE_HUNDRED_MICROSECONDS;
			break;

		case 200:
			timeString = Constant.TWO_HUNDRED_MICROSECONDS;
			break;

		case 500:
			timeString = Constant.FIVE_HUNDRED_MICROSECONDS;
			break;

		case 1000:
			timeString = Constant.ONE_MILISECOND;
			break;

		case 2000:
			timeString = Constant.TWO_MILISECONDS;
			break;

		case 5000:
			timeString = Constant.FIVE_MILISECONDS;
			break;

		case 10000:
			timeString = Constant.TEN_MILISECONDS;
			break;

		case 20000:
			timeString = Constant.TWENTY_MILISECONDS;
			break;

		case 50000:
			timeString = Constant.FIFTY_MILISECONDS;
			break;

		case 100000:
			timeString = Constant.ONE_HUNDRED_MILISECONDS;
			break;

		case 200000:
			timeString = Constant.TWO_HUNDRED_MILISECONDS;
			break;

		case 500000:
			timeString = Constant.FIVE_HUNDRED_MILISECONDS;
			break;

		case 1000000:
			timeString = Constant.ONE_SECOND;
			break;

		default:
			System.err.println("Cannot convert " + microSeconds + " us to time String.");
		}
		return timeString;
	}

	/**
	 * Create default chart panel
	 * 
	 * @param chart
	 * @return ChartPanel instance
	 */
	private ChartPanel createDefaultChartPanel(JFreeChart chart) {
		ChartPanel chartPanel = new ChartPanel(chart);
		CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
		this.measuredChannelIndex_ = 0;
		this.timeCrosshair_ = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
		this.timeCrosshair_.setLabelVisible(true);
		this.timeCrosshair_.setLabelBackgroundPaint(Constant.A_COLOR);
		this.voltageCrosshair_ = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
		this.voltageCrosshair_.setLabelVisible(true);
		crosshairOverlay.addDomainCrosshair(timeCrosshair_);
		crosshairOverlay.addRangeCrosshair(voltageCrosshair_);
		chartPanel.addOverlay(crosshairOverlay);
		chartPanel.setMouseZoomable(false);
		return chartPanel;
	}

	private void mainWindowClosed() {
		getLaunchWindow().setStatus("To connect, please enter the IP address!", Constant.NORMAL);
		getLaunchWindow().setVisible(true);
		try {
			this.socket_.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LaunchWindow getLaunchWindow() {
		return launchWindow_;
	}

	public void setLaunchWindow(LaunchWindow launchWindow) {
		this.launchWindow_ = launchWindow;
	}

	public Map<String, XYSeries> getRawXYSeries() {
		return rawXYSeries;
	}

	public void setExpressionForMathChannel(String expression) {
		expressionTextArea.setText(expression);
	}

	/**
	 * Show cursor measurement and set channel index which will be measured
	 * 
	 * @param channelIndex
	 *            the index of the measured channel
	 */
	private void showCursorMeasurement(int channelIndex) {
		chartPanel_.removeChartMouseListener(this);
		measuredChannelIndex_ = channelIndex;
		if (channelIndex == Constant.A_INDEX) {
			String selectedItem = (String) verticalRangeAComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.A_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.A_LIGHT_COLOR);
		} else if (channelIndex == Constant.B_INDEX) {
			String selectedItem = (String) verticalRangeBComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.B_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.B_LIGHT_COLOR);
		} else if (channelIndex == Constant.MATH_INDEX) {
			String selectedItem = (String) verticalRangeMathComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.MATH_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.MATH_LIGHT_COLOR);
		} else if (channelIndex == Constant.FILTER_INDEX) {
			String selectedItem = (String) verticalRangeFilterComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			timeCrosshair_.setLabelBackgroundPaint(Constant.FILTER_LIGHT_COLOR);
			voltageCrosshair_.setLabelBackgroundPaint(Constant.FILTER_LIGHT_COLOR);
		}
		chartPanel_.addChartMouseListener(this);
	}

	/**
	 * Hide the cursor measurement
	 */
	private void hideCursorMeasurement() {
		this.chartPanel_.removeChartMouseListener(this);
		timeCrosshair_.setValue(Double.NaN);
		voltageCrosshair_.setValue(Double.NaN);
		cursorVerticalValueLabel.setText("N/A");
	}

	/**
	 * Convert voltage value to string
	 * 
	 * @param voltage
	 *            the value to be converted
	 * @return voltage string
	 */
	private String convertVoltsToVoltageString(double voltage) {
		String result = Constant.roundString(voltage) + " V";
		return result;
	}
	
	/**
	 * Convert frequency to string
	 * @param frequency
	 * @return frequency string
	 */
	private String convertFrequencyToString(double frequency) {
		if(frequency >= 1000) {
			String result = Constant.roundString(frequency / 1000) + "kHz";
			return result;
		} else {
			String result = Constant.roundString(frequency) + "Hz";
			return result;
		}
	}

	/**
	 * Create a XYSeries with given offset
	 * 
	 * @param channelName
	 *            The name of the channel
	 * @param xYSeries
	 *            The raw XYSeries
	 * @param horizontalOffset
	 *            The horizontal offset
	 * @param verticalOffset
	 *            The vertical offset
	 * @return a XYSeries with given offset or null if the given xYSeries is
	 *         null.
	 */
	private XYSeries createXYSeriesWithOffsets(String channelName, XYSeries xYSeries, int horizontalOffset,
			double verticalOffset) {
		if (horizontalOffset == 0 && verticalOffset == 0) {
			return xYSeries;
		} else {
			XYSeries result = new XYSeries(channelName);
			if (xYSeries != null) {
				for (int i = 0; i < xYSeries.getItemCount(); i++) {
					double xValue = xYSeries.getDataItem(i).getXValue() + horizontalOffset;
					double yValue = xYSeries.getDataItem(i).getYValue() + verticalOffset;
					result.add(xValue, yValue);
				}
			} else {
				result = null;
			}
			return result;
		}
	}

	/**
	 * Show the plot of specified channel on the chart panel
	 * 
	 * @param channelName
	 *            The channel's name
	 */
	private void showChannelPlotOnChartPanel(String channelName) {
		refreshChannelPlotOnChartPanel(channelName);
		if (rawXYSeries.containsKey(channelName)) {
			// remove item if it exists in the combo box before adding
			cursorComboBox.removeItem(channelName);
			cursorComboBox.addItem(channelName);
		}
	}

	/**
	 * Refresh the plot of specified channel on the chart panel
	 * 
	 * @param channelName
	 *            The channel's name
	 */
	private void refreshChannelPlotOnChartPanel(String channelName) {
		XYSeries rawSeries = rawXYSeries.get(channelName);
		if (rawSeries != null) {
			int horizontalOffset = 0;
			double verticalOffset = 0;
			int channelIndex = 0;
			if (channelName == Constant.CHANNEL_A) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetASpinner.getValue(),
						(String) horizontalOffsetUnitAComboBox.getSelectedItem());
				channelIndex = Constant.A_INDEX;
				aDivisionInfoLabel.setEnabled(true);
			} else if (channelName == Constant.CHANNEL_B) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetBSpinner.getValue(),
						(String) horizontalOffsetUnitBComboBox.getSelectedItem());
				channelIndex = Constant.B_INDEX;
				bDivisionInfoLabel.setEnabled(true);
			} else if (channelName == Constant.MATH_CHANNEL) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetMathSpinner.getValue(),
						(String) horizontalOffsetUnitMathComboBox.getSelectedItem());
				verticalOffset = getVerticalOffsetValueInVolt((int) verticalOffsetMathSpinner.getValue(),
						(String) verticalOffsetUnitMathComboBox.getSelectedItem());
				channelIndex = Constant.MATH_INDEX;
				mathDivisionInfoLabel.setEnabled(true);
			} else if (channelName == Constant.FILTER_CHANNEL) {
				horizontalOffset = getHorizontalOffsetValue((int) horizontalOffsetFilterSpinner.getValue(),
						(String) horizontalOffsetUnitFilterComboBox.getSelectedItem());
				verticalOffset = getVerticalOffsetValueInVolt((int) verticalOffsetFilterSpinner.getValue(),
						(String) verticalOffsetUnitFilterComboBox.getSelectedItem());
				channelIndex = Constant.FILTER_INDEX;
				filterDivisionInfoLabel.setEnabled(true);
			}
			XYSeries xYSeries = createXYSeriesWithOffsets(channelName, rawSeries, horizontalOffset, verticalOffset);
			visualizer_.addSeriesToDataset(channelIndex, xYSeries);
			showMeasurementResults(channelIndex);
		}
	}

	/**
	 * Remove the plot of specified channel from the chart panel
	 * 
	 * @param channelName
	 *            The channel's name
	 */
	private void removeChannelPlotFromChartPanel(String channelName) {
		int channelIndex = 0;
		if (channelName == Constant.CHANNEL_A) {
			channelIndex = Constant.A_INDEX;
			aDivisionInfoLabel.setEnabled(false);
		} else if (channelName == Constant.CHANNEL_B) {
			channelIndex = Constant.B_INDEX;
			bDivisionInfoLabel.setEnabled(false);
		} else if (channelName == Constant.MATH_CHANNEL) {
			channelIndex = Constant.MATH_INDEX;
			mathDivisionInfoLabel.setEnabled(false);
		} else if (channelName == Constant.FILTER_CHANNEL) {
			channelIndex = Constant.FILTER_INDEX;
			filterDivisionInfoLabel.setEnabled(false);
		}
		visualizer_.removeAllSeriesFromDataset(channelIndex);
		hideMeasurementResults(channelIndex);
		cursorComboBox.removeItem(channelName);
	}

	/**
	 * Get vertical offset value in volt
	 * 
	 * @param offset
	 *            the value from verticalOffset spinner
	 * @param unit
	 *            the selected unit from verticalOffsetUnit combobox
	 * @return vertical offset value in milivolts
	 */
	private double getVerticalOffsetValueInVolt(int offset, String unit) {
		if (unit.equals(Constant.TEN_MILIVOLTS)) {
			return offset * 0.01;
		} else if (unit.equals(Constant.ONE_HUNDRED_MILIVOLTS)) {
			return offset * 0.1;
		} else if (unit.equals(Constant.ONE_VOLT)) {
			return offset * 1;
		} else { // unit == Constant.ONE_MILIVOLT
			return offset * 0.001;
		}
	}

	/**
	 * Convert to microvolt
	 * 
	 * @param value
	 *            from spinner
	 * @param unit
	 *            the selected unit from the combobox
	 * @return value in milivolts
	 */
	private int convertToMicrovolt(int value, String unit) {
		if (unit.equals(Constant.TEN_MILIVOLTS)) {
			return value * 10000;
		} else if (unit.equals(Constant.ONE_HUNDRED_MILIVOLTS)) {
			return value * 100000;
		} else if (unit.equals(Constant.ONE_VOLT)) {
			return value * 1000000;
		} else { // unit == Constant.ONE_MILIVOLT
			return value * 1000;
		}
	}

	/**
	 * Get horizontal offset value in microsecond
	 * 
	 * @param offset
	 *            the value from horizontalOffset spinner
	 * @param unit
	 *            the selected unit from horizontalOffsetUnit combobox
	 * @return horizontal offset value in micro-seconds
	 */
	private int getHorizontalOffsetValue(int offset, String unit) {
		if (unit.equals(Constant.TEN_MICROSECONDS)) {
			return offset * 10;
		} else if (unit.equals(Constant.ONE_HUNDRED_MICROSECONDS)) {
			return offset * 100;
		} else if (unit.equals(Constant.ONE_MILISECOND)) {
			return offset * 1000;
		} else if (unit.equals(Constant.TEN_MILISECONDS)) {
			return offset * 10000;
		} else if (unit.equals(Constant.ONE_HUNDRED_MILISECONDS)) {
			return offset * 100000;
		} else if (unit.equals(Constant.ONE_SECOND)) {
			return offset * 1000000;
		} else { // unit == Constant.ONE_MICROSECOND
			return offset;
		}
	}

	/**
	 * Calculate the MATH channel and store MATH channel data into rawXYSeries
	 * 
	 * @param expression
	 *            the expression string
	 * @return false if the expression produce infinity number. Otherwise, true
	 * @throws IllegalArgumentException
	 */
	public boolean calculateMathChannel(String expression) throws IllegalArgumentException {
		Map<String, String> channelNames = new HashMap<String, String>();
		if (expression.contains("A")) {
			channelNames.put("A", Constant.CHANNEL_A);
		}
		if (expression.contains("B")) {
			channelNames.put("B", Constant.CHANNEL_B);
		}
		if (expression.contains("F")) {
			channelNames.put("F", Constant.FILTER_CHANNEL);
		}
		int noOfItems = getMinNoOfItems(channelNames);
		double maxHorizontalRange = visualizer_.getHorizontalRange().getUpperBound();
		if (noOfItems != Integer.MAX_VALUE) {
			Evaluator evaluator = new Evaluator();
			XYSeries mathSeries = new XYSeries(Constant.MATH_CHANNEL);
			Double x = Double.MAX_VALUE;
			Double lastX = Double.MAX_VALUE;
			for (int i = 0; i < noOfItems; i++) {
				for (Map.Entry<String, String> entry : channelNames.entrySet()) {
					XYDataItem dataItem = rawXYSeries.get(entry.getValue()).getDataItem(i);
					evaluator.setVariableValue(entry.getKey(), dataItem.getYValue());
					x = dataItem.getXValue();
				}
				Double y = evaluator.evaluate(expression, evaluator.getVariables());
				if (y != Double.POSITIVE_INFINITY && y != Double.NEGATIVE_INFINITY) {
					if (x <= maxHorizontalRange) {
						mathSeries.add(x, y);
						lastX = x;
					} else {
						if (lastX < maxHorizontalRange) {
							mathSeries.add(x, y);
						}
						break;
					}
				} else {
					return false;
				}
			}
			rawXYSeries.put(Constant.MATH_CHANNEL, mathSeries);
			if (mathChannelCheckBox.isSelected()) {
				showChannelPlotOnChartPanel(Constant.MATH_CHANNEL);
			}
			return true;
		} else {
			System.out.print("There is no input channels (A, B or Filter) in the expression");
			return false;
		}
	}

	/**
	 * Calculate the FILTER channel
	 */
	public void calculateFilterChannel() {
		String inputChannel = (String) inputChannelComboBox.getSelectedItem();
		if (inputChannel != null) {
			if (filterFile_.isValid() && (!inputChannel.equals("Select channel"))) {
				ArrayList<Double> firstColumn = filterFile_.getFirstColumn();
				XYSeries derivedSeries = rawXYSeries.get(inputChannel);
				XYSeries filterSeries = new XYSeries(Constant.FILTER_CHANNEL);
				double maxHorizontalRange = visualizer_.getHorizontalRange().getUpperBound();
				if (filterFile_.getType() == Constant.FIR) {
					double xValue = Double.MAX_VALUE;
					double lastXValue = Double.MAX_VALUE;
					for (int n = 0; n < derivedSeries.getItemCount(); n++) {
						Double result = 0.0;
						for (int i = 0; i < firstColumn.size(); i++) {
							Double x = 0.0;
							if (n - i >= 0) {
								x = derivedSeries.getDataItem(n - i).getYValue();
							}
							result = result + firstColumn.get(i) * x;
						}
						xValue = derivedSeries.getDataItem(n).getXValue();
						if (xValue <= maxHorizontalRange) {
							filterSeries.add(xValue, result);
							lastXValue = xValue;
						} else {
							if (lastXValue < maxHorizontalRange) {
								filterSeries.add(xValue, result);
							}
							break;
						}
					}
				} else if (filterFile_.getType() == Constant.IIR) {
					ArrayList<Double> secondColumn = filterFile_.getSecondColumn();
					double xValue = Double.MAX_VALUE;
					double lastXValue = Double.MAX_VALUE;
					for (int n = 0; n < derivedSeries.getItemCount(); n++) {
						Double firstSum = 0.0;
						Double secondSum = 0.0;
						for (int i = 0; i < firstColumn.size(); i++) {
							Double x = 0.0;
							if (n - i >= 0) {
								x = derivedSeries.getDataItem(n - i).getYValue();
							}
							firstSum += firstColumn.get(i) * x;
						}
						for (int j = 1; j < secondColumn.size(); j++) {
							Double y = 0.0;
							if (n - j >= 0) {
								y = filterSeries.getDataItem(n - j).getYValue();
							}
							secondSum += secondColumn.get(j) * y;
						}
						Double result = (1 / secondColumn.get(0)) * (firstSum - secondSum);
						xValue = derivedSeries.getDataItem(n).getXValue();
						if (xValue <= maxHorizontalRange) {
							filterSeries.add(xValue, result);
							lastXValue = xValue;
						} else {
							if (lastXValue < maxHorizontalRange) {
								filterSeries.add(xValue, result);
							}
							break;
						}
					}
				}
				rawXYSeries.put(Constant.FILTER_CHANNEL, filterSeries);
				if (filterChannelCheckBox.isSelected()) {
					showChannelPlotOnChartPanel(Constant.FILTER_CHANNEL);
				}
			}
		}
	}

	/**
	 * get the minimum number of items
	 * 
	 * @param channelNames
	 * @return
	 */
	private int getMinNoOfItems(Map<String, String> channelNames) {
		int min = Integer.MAX_VALUE;
		for (String name : channelNames.values()) {
			if (rawXYSeries.get(name).getItemCount() < min) {
				min = rawXYSeries.get(name).getItemCount();
			}
		}
		return min;
	}

	/**
	 * Determine the max voltage, min voltage, max peak to peak, average
	 * voltage, standard deviation of the specified channel.
	 * 
	 * @param channelIndex
	 *            the index of specified channel
	 * @return Map<String, Double> containing results or null
	 */
	private Map<String, Double> measureChannel(int channelIndex) {
		switch (channelIndex) {
		case Constant.A_INDEX:
			if (measureAToggleButton.isSelected() == false) {
				return null;
			}
			break;

		case Constant.B_INDEX:
			if (measureBToggleButton.isSelected() == false) {
				return null;
			}
			break;

		case Constant.MATH_INDEX:
			if (measureMathToggleButton.isSelected() == false) {
				return null;
			}
			break;

		case Constant.FILTER_INDEX:
			if (measureFilterToggleButton.isSelected() == false) {
				return null;
			}
			break;
		}
		Map<String, Double> result = new HashMap<String, Double>();
		XYSeries xYSeries = visualizer_.getSeries(channelIndex);
		if (xYSeries != null) {
			Range horizontalRange = visualizer_.getHorizontalRange();
			Range verticalRange = visualizer_.getVerticalRange(channelIndex);
			double maxVoltage = Double.MIN_VALUE;
			double minVoltage = Double.MAX_VALUE;
			double totalVoltage = 0;
			int nSamples = 0;
			int startIndex = 0;
			if(horizontalRange.getLowerBound() < 0) {
				startIndex = 0;
			} else {
				startIndex = (int) horizontalRange.getLowerBound();
			}
			for (int i = startIndex; i < xYSeries.getItemCount(); i++) {
				double time = xYSeries.getDataItem(i).getXValue();
				double voltage = xYSeries.getDataItem(i).getYValue();
				if (time <= horizontalRange.getUpperBound()) {
					if ((voltage <= verticalRange.getUpperBound()) && (voltage >= verticalRange.getLowerBound())) {
						if (maxVoltage < voltage) {
							maxVoltage = voltage;
						}
						if (minVoltage > voltage) {
							minVoltage = voltage;
						}
						totalVoltage += voltage;
						nSamples++;
					}
				} else {
					break;
				}
			}
			if (nSamples > 0) {
				double averageVoltage = totalVoltage / nSamples;
				double sumOfSquare = 0;
				for (int i = 0; i < nSamples; i++) {
					double voltage = xYSeries.getDataItem(i).getYValue();
					sumOfSquare += Math.pow((voltage - averageVoltage), 2);
				}
				double deviation = Math.sqrt(sumOfSquare / (nSamples - 1));
				result.put(Constant.MAX_VOLTAGE, maxVoltage);
				result.put(Constant.MIN_VOLTAGE, minVoltage);
				result.put(Constant.AVERAGE_VOLTAGE, averageVoltage);
				result.put(Constant.MAX_P2P_VOLTAGE, maxVoltage - minVoltage);
				result.put(Constant.STANDARD_DEVIATION_VOLTAGE, deviation);
				double firstZeroCrossing = -1;
				double secondZeroCrossing = -1;
				boolean isReady = false;
				for (int i = startIndex; i < xYSeries.getItemCount(); i++) {
					double time = xYSeries.getDataItem(i).getXValue();
					double voltage = xYSeries.getDataItem(i).getYValue();
					if (time <= horizontalRange.getUpperBound()) {
						if ((voltage <= verticalRange.getUpperBound()) && (voltage >= verticalRange.getLowerBound())) {
							//if ((voltage <= (9 * minVoltage) / 10) || voltage >= (9 * maxVoltage) / 10)  {
							if(voltage <= (0.95 * minVoltage)) {
								isReady = true;
							}
							if (voltage >= -0.05 && voltage <= 0.05 && isReady == true) {
								if (firstZeroCrossing == -1) {
									firstZeroCrossing = time;
									isReady = false;
								} else if (secondZeroCrossing == -1) {
									secondZeroCrossing = time;
									break;
								}
							}
						}
					} else {
						break;
					}
				}
				if (firstZeroCrossing != -1 && secondZeroCrossing != -1) {
					double frequency = 1 / (((secondZeroCrossing - firstZeroCrossing)) / 1000000);
					result.put(Constant.FREQUENCY, frequency);
				}
			} else {
				result = null;
			}
		} else {
			result = null;
		}
		return result;
	}

	/**
	 * Show measurement results of the specified channel
	 * 
	 * @param channelIndex
	 *            the index of specified channel
	 */
	private void showMeasurementResults(int channelIndex) {
		Map<String, Double> results = measureChannel(channelIndex);
		if (results != null) {
			if (channelIndex == Constant.A_INDEX) {
				maxVoltageALabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_VOLTAGE)));
				minVoltageALabel.setText(convertVoltsToVoltageString(results.get(Constant.MIN_VOLTAGE)));
				maxP2pVoltageALabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_P2P_VOLTAGE)));
				averageVoltageALabel.setText(convertVoltsToVoltageString(results.get(Constant.AVERAGE_VOLTAGE)));
				String deviation = Constant.roundString(results.get(Constant.STANDARD_DEVIATION_VOLTAGE));
				standardDeviationVoltageALabel.setText(deviation);
				if(results.containsKey(Constant.FREQUENCY)) {
					String frequency = convertFrequencyToString(results.get(Constant.FREQUENCY));
					frequencyALabel.setText(frequency);
				} else {
					frequencyALabel.setText("N/A");
				}
				
			} else if (channelIndex == Constant.B_INDEX) {
				maxVoltageBLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_VOLTAGE)));
				minVoltageBLabel.setText(convertVoltsToVoltageString(results.get(Constant.MIN_VOLTAGE)));
				maxP2pVoltageBLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_P2P_VOLTAGE)));
				averageVoltageBLabel.setText(convertVoltsToVoltageString(results.get(Constant.AVERAGE_VOLTAGE)));
				String deviation = Constant.roundString(results.get(Constant.STANDARD_DEVIATION_VOLTAGE));
				standardDeviationVoltageBLabel.setText(deviation);
				if(results.containsKey(Constant.FREQUENCY)) {
					String frequency = convertFrequencyToString(results.get(Constant.FREQUENCY));
					frequencyBLabel.setText(frequency);
				} else {
					frequencyBLabel.setText("N/A");
				}

			} else if (channelIndex == Constant.MATH_INDEX) {
				maxVoltageMathLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_VOLTAGE)));
				minVoltageMathLabel.setText(convertVoltsToVoltageString(results.get(Constant.MIN_VOLTAGE)));
				maxP2pVoltageMathLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_P2P_VOLTAGE)));
				averageVoltageMathLabel.setText(convertVoltsToVoltageString(results.get(Constant.AVERAGE_VOLTAGE)));
				String deviation = Constant.roundString(results.get(Constant.STANDARD_DEVIATION_VOLTAGE));
				standardDeviationVoltageMathLabel.setText(deviation);
				if(results.containsKey(Constant.FREQUENCY)) {
					String frequency = convertFrequencyToString(results.get(Constant.FREQUENCY));
					frequencyMathLabel.setText(frequency);
				} else {
					frequencyMathLabel.setText("N/A");
				}

			} else if (channelIndex == Constant.FILTER_INDEX) {
				maxVoltageFilterLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_VOLTAGE)));
				minVoltageFilterLabel.setText(convertVoltsToVoltageString(results.get(Constant.MIN_VOLTAGE)));
				maxP2pVoltageFilterLabel.setText(convertVoltsToVoltageString(results.get(Constant.MAX_P2P_VOLTAGE)));
				averageVoltageFilterLabel.setText(convertVoltsToVoltageString(results.get(Constant.AVERAGE_VOLTAGE)));
				String deviation = Constant.roundString(results.get(Constant.STANDARD_DEVIATION_VOLTAGE));
				standardDeviationVoltageFilterLabel.setText(deviation);
				if(results.containsKey(Constant.FREQUENCY)) {
					String frequency = convertFrequencyToString(results.get(Constant.FREQUENCY));
					frequencyFilterLabel.setText(frequency);
				} else {
					frequencyFilterLabel.setText("N/A");
				}

			}
		} else {
			hideMeasurementResults(channelIndex);
		}
	}

	/**
	 * Hide measurement results of the specified channel
	 * 
	 * @param channelIndex
	 *            The index of specified channel
	 */
	private void hideMeasurementResults(int channelIndex) {
		if (channelIndex == Constant.A_INDEX) {
			maxVoltageALabel.setText("N/A");
			minVoltageALabel.setText("N/A");
			maxP2pVoltageALabel.setText("N/A");
			averageVoltageALabel.setText("N/A");
			standardDeviationVoltageALabel.setText("N/A");
			frequencyALabel.setText("N/A");
		} else if (channelIndex == Constant.B_INDEX) {
			maxVoltageBLabel.setText("N/A");
			minVoltageBLabel.setText("N/A");
			maxP2pVoltageBLabel.setText("N/A");
			averageVoltageBLabel.setText("N/A");
			standardDeviationVoltageBLabel.setText("N/A");
			frequencyBLabel.setText("N/A");
		} else if (channelIndex == Constant.MATH_INDEX) {
			maxVoltageMathLabel.setText("N/A");
			minVoltageMathLabel.setText("N/A");
			maxP2pVoltageMathLabel.setText("N/A");
			averageVoltageMathLabel.setText("N/A");
			standardDeviationVoltageMathLabel.setText("N/A");
			frequencyMathLabel.setText("N/A");
		} else if (channelIndex == Constant.FILTER_INDEX) {
			maxVoltageFilterLabel.setText("N/A");
			minVoltageFilterLabel.setText("N/A");
			maxP2pVoltageFilterLabel.setText("N/A");
			averageVoltageFilterLabel.setText("N/A");
			standardDeviationVoltageFilterLabel.setText("N/A");
			frequencyFilterLabel.setText("N/A");
		}
	}

	@Override
	public void chartMouseClicked(ChartMouseEvent event) {
	}

	@Override
	public void chartMouseMoved(ChartMouseEvent event) {
		Rectangle2D dataArea = this.chartPanel_.getScreenDataArea();
		JFreeChart chart = event.getChart();
		XYPlot plot = (XYPlot) chart.getPlot();
		ValueAxis xAxis = plot.getDomainAxis();
		double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea, RectangleEdge.BOTTOM);
		double y = DatasetUtilities.findYValue(plot.getDataset(measuredChannelIndex_), 0, x);
		if (!Double.isNaN(y)) {
			this.timeCrosshair_.setValue(x);
			this.voltageCrosshair_.setValue(y);
			this.cursorVerticalValueLabel.setText(convertVoltsToVoltageString(y));
		}
	}

	public Socket getSocket() {
		return socket_;
	}

	public void setSocket(Socket socket_) {
		this.socket_ = socket_;
	}

	public PacketWriter getPacketWriter() {
		return packetWriter_;
	}

	public void setPacketWriter(PacketWriter packetWriter_) {
		this.packetWriter_ = packetWriter_;
	}

	public PacketReader getPacketReader() {
		return packetReader_;
	}

	public void setPacketReader(PacketReader packetReader_) {
		this.packetReader_ = packetReader_;
	}

	/**
	 * Set horizontal range
	 * 
	 * @param microSeconds
	 */
	public void setHorizontalRange(int microSeconds) {
		String timeString = convertMicroSecondsToTimeString(microSeconds);
		horizontalRangeComboBox.removeActionListener(this);
		horizontalRangeComboBox.setSelectedItem(timeString);
		horizontalRangeComboBox.addActionListener(this);

		int horizontalRange = microSeconds;
		visualizer_.setValueForHorizontalGridSpacing(horizontalRange, (int) noOfSamplesSpinner.getValue());
		if (horizontalRangeComboBox.getSelectedIndex() > previousHorizontalRangeIndex_) {
			if (rawXYSeries.containsKey(Constant.MATH_CHANNEL)) {
				calculateMathChannel(expressionTextArea.getText().trim());
			}
			if (rawXYSeries.containsKey(Constant.FILTER_CHANNEL)) {
				calculateFilterChannel();
			}
		}
		showMeasurementResults(Constant.A_INDEX);
		showMeasurementResults(Constant.B_INDEX);
		showMeasurementResults(Constant.MATH_INDEX);
		showMeasurementResults(Constant.FILTER_INDEX);
		horizontalDivisionInfoLabel.setText("Horizontal: " + timeString + "/div");
		previousHorizontalRangeIndex_ = horizontalRangeComboBox.getSelectedIndex();
	}

	/**
	 * Set vertical range for the specified channel
	 * 
	 * @param channelName
	 *            The specified channel's name
	 * @param microvolts
	 */
	public void setVerticalRange(String channelName, int microvolts) {
		String voltageString = convertMicrovoltsToVoltageString(microvolts);
		if (channelName.equals(Constant.CHANNEL_A)) {
			verticalRangeAComboBox.removeActionListener(this);
			verticalRangeAComboBox.setSelectedItem(voltageString);
			verticalRangeAComboBox.addActionListener(this);
			previousVerticalRangeAIndex_ = verticalRangeAComboBox.getSelectedIndex();
			double verticalRange = convertVoltageStringToVolts(voltageString);
			visualizer_.setValueForVerticalGridSpacing(Constant.A_INDEX, verticalRange);
			showMeasurementResults(Constant.A_INDEX);
			if (measuredChannelIndex_ == Constant.A_INDEX) {
				visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			}
			aDivisionInfoLabel.setText("A: " + voltageString + "/div");

		} else if (channelName.equals(Constant.CHANNEL_B)) {
			verticalRangeBComboBox.removeActionListener(this);
			verticalRangeBComboBox.setSelectedItem(voltageString);
			verticalRangeBComboBox.addActionListener(this);
			previousVerticalRangeBIndex_ = verticalRangeBComboBox.getSelectedIndex();
			double verticalRange = convertVoltageStringToVolts(voltageString);
			visualizer_.setValueForVerticalGridSpacing(Constant.B_INDEX, verticalRange);
			showMeasurementResults(Constant.B_INDEX);
			if (measuredChannelIndex_ == Constant.B_INDEX) {
				visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			}
			bDivisionInfoLabel.setText("B: " + voltageString + "/div");

		} else {
			System.err.println("The channel named \"" + channelName + "\" does not exists.");
		}
	}

	/**
	 * Set vertical offset
	 * 
	 * @param channelName
	 * @param microvolts
	 */
	public void setVerticalOffset(String channelName, int microvolts) {
		if (channelName.equals(Constant.CHANNEL_A)) {
			if (microvolts == 0) {
				verticalOffsetASpinner.removeChangeListener(this);
				verticalOffsetASpinner.setValue(0);
				verticalOffsetASpinner.addChangeListener(this);
			} else {
				if (sentVerticalOffsetACommand_ == false) {
					verticalOffsetUnitAComboBox.removeActionListener(this);
					verticalOffsetUnitAComboBox.setSelectedItem(Constant.ONE_MILIVOLT);
					verticalOffsetUnitAComboBox.addActionListener(this);
				}
				sentVerticalOffsetACommand_ = false;
				verticalOffsetASpinner.removeChangeListener(this);
				int spinnerValue = calculateValueForSpinner(microvolts,
						(String) verticalOffsetUnitAComboBox.getSelectedItem());
				verticalOffsetASpinner.setValue(spinnerValue);
				verticalOffsetASpinner.addChangeListener(this);
				previousVerticalOffsetAValue_ = (int) verticalOffsetASpinner.getValue();
			}

		} else if (channelName.equals(Constant.CHANNEL_B)) {
			if (microvolts == 0) {
				verticalOffsetBSpinner.removeChangeListener(this);
				verticalOffsetBSpinner.setValue(0);
				verticalOffsetBSpinner.addChangeListener(this);
			} else {
				if (sentVerticalOffsetBCommand_ == false) {
					verticalOffsetUnitBComboBox.removeActionListener(this);
					verticalOffsetUnitBComboBox.setSelectedItem(Constant.ONE_MILIVOLT);
					verticalOffsetUnitBComboBox.addActionListener(this);
				}
				sentVerticalOffsetBCommand_ = false;
				verticalOffsetBSpinner.removeChangeListener(this);
				int spinnerValue = calculateValueForSpinner(microvolts,
						(String) verticalOffsetUnitBComboBox.getSelectedItem());
				verticalOffsetBSpinner.setValue(spinnerValue);
				verticalOffsetBSpinner.addChangeListener(this);
				previousVerticalOffsetBValue_ = (int) verticalOffsetBSpinner.getValue();
			}

		} else if (channelName.equals(Constant.GENERATOR_CHANNEL)) {
			if (microvolts == 0) {
				verticalOffsetGeneratorSpinner.removeChangeListener(this);
				verticalOffsetGeneratorSpinner.setValue(0);
				verticalOffsetGeneratorSpinner.addChangeListener(this);
			} else {
				if (sentVerticalOffsetGeneratorCommand_ == false) {
					verticalOffsetUnitGeneratorComboBox.removeActionListener(this);
					verticalOffsetUnitGeneratorComboBox.setSelectedItem(Constant.ONE_MILIVOLT);
					verticalOffsetUnitGeneratorComboBox.addActionListener(this);
				}
				sentVerticalOffsetGeneratorCommand_ = false;
				verticalOffsetGeneratorSpinner.removeChangeListener(this);
				int spinnerValue = calculateValueForSpinner(microvolts,
						(String) verticalOffsetUnitGeneratorComboBox.getSelectedItem());
				verticalOffsetGeneratorSpinner.setValue(spinnerValue);
				verticalOffsetGeneratorSpinner.addChangeListener(this);
				previousVerticalOffsetGeneratorValue_ = (int) verticalOffsetBSpinner.getValue();
			}

		}
	}

	/**
	 * Set peak to peak voltage for function generator
	 * 
	 * @param microvolts
	 */
	public void setP2PVoltage(int microvolts) {
		if (microvolts == 0) {
			p2pVoltageSpinner.removeChangeListener(this);
			p2pVoltageSpinner.setValue(0);
			p2pVoltageSpinner.addChangeListener(this);
		} else {
			if (sentP2PVoltageCommand_ == false) {
				p2pVoltageUnitComboBox.removeActionListener(this);
				p2pVoltageUnitComboBox.setSelectedItem(Constant.ONE_MILIVOLT);
				p2pVoltageUnitComboBox.addActionListener(this);
			}
			sentP2PVoltageCommand_ = false;
			p2pVoltageSpinner.removeChangeListener(this);
			int spinnerValue = calculateValueForSpinner(microvolts, (String) p2pVoltageUnitComboBox.getSelectedItem());
			p2pVoltageSpinner.setValue(spinnerValue);
			p2pVoltageSpinner.addChangeListener(this);
			previousP2PVoltageValue_ = (int) p2pVoltageSpinner.getValue();
		}
	}

	/**
	 * Set the frequency for function generator
	 * 
	 * @param frequency
	 */
	public void setGeneratorFrequency(int frequency) {
		generatorFrequencySpinner.removeChangeListener(this);
		generatorFrequencySpinner.setValue(frequency);
		generatorFrequencySpinner.addChangeListener(this);
		previousGeneratorFrequency_ = (int) generatorFrequencySpinner.getValue();
	}

	/**
	 * Set trigger state
	 * 
	 * @param channelName
	 * @param state
	 */
	public void setTriggerState(int state) {
		if (state >= 0 && state <= 2) {
			switch (state) {
			case Constant.ARMED_STATE:
				triggerStateLabel.setText("ARMED");
				break;

			case Constant.TRIGGERED_STATE:
				triggerStateLabel.setText("TRIGGERED");
				break;

			case Constant.STOPPED_STATE:
				triggerStateLabel.setText("STOP");
				break;
			}
		} else {
			System.out.println("Invalid trigger state: " + state);
		}
	}

	/**
	 * Set trigger threshold
	 * 
	 * @param channelName
	 * @param microvolts
	 */
	public void setTriggerThreshold(int microvolts) {
		if (microvolts == 0) {
			triggerThresholdSpinner.removeChangeListener(this);
			triggerThresholdSpinner.setValue(0);
			triggerThresholdSpinner.addChangeListener(this);
		} else {
			if (sentTriggerThresholdCommand_ == false) {
				triggerThresholdUnitComboBox.removeActionListener(this);
				triggerThresholdUnitComboBox.setSelectedItem(Constant.ONE_MILIVOLT);
				triggerThresholdUnitComboBox.addActionListener(this);
			}
			sentTriggerThresholdCommand_ = false;
			triggerThresholdSpinner.removeChangeListener(this);
			int spinnerValue = calculateValueForSpinner(microvolts,
					(String) triggerThresholdUnitComboBox.getSelectedItem());
			triggerThresholdSpinner.setValue(spinnerValue);
			triggerThresholdSpinner.addChangeListener(this);
			previousTriggerThresholdValue_ = (int) triggerThresholdSpinner.getValue();
		}
	}

	/**
	 * Set number of samples
	 * 
	 * @param noOfSamples
	 */
	public void setNoOfSamples(int noOfSamples) {
		noOfSamplesSpinner.removeChangeListener(this);
		noOfSamplesSpinner.setValue(noOfSamples);
		noOfSamplesSpinner.addChangeListener(this);
		String timeString = (String) horizontalRangeComboBox.getSelectedItem();
		int horizontalRange = convertTimeStringToMicroSeconds(timeString);
		visualizer_.setValueForHorizontalGridSpacing(horizontalRange, noOfSamples);
		previousNoOfSamples_ = (int) noOfSamplesSpinner.getValue();
	}

	/**
	 * Calculate the value for spinner
	 * 
	 * @param microvolts
	 * @param unit
	 * @return
	 */
	private int calculateValueForSpinner(int microvolts, String unit) {
		int result = 0;
		if (unit.equals(Constant.ONE_MILIVOLT)) {
			result = microvolts / 1000;
		} else if (unit.equals(Constant.TEN_MILIVOLTS)) {
			result = microvolts / 10000;
		} else if (unit.equals(Constant.ONE_HUNDRED_MILIVOLTS)) {
			result = microvolts / 100000;
		} else if (unit.equals(Constant.ONE_VOLT)) {
			result = microvolts / 1000000;
		}
		return result;
	}

	/**
	 * Set trigger mode
	 * 
	 * @param triggerMode
	 */
	public void setTriggerMode(int triggerMode) {
		if (triggerMode >= 0 && triggerMode <= 2) {
			triggerModeComboBox.removeActionListener(this);
			triggerModeComboBox.setSelectedIndex(triggerMode);
			triggerModeComboBox.addActionListener(this);
			previousTriggerModeIndex_ = triggerMode;
			if (triggerMode == Constant.SINGLE_MODE) {
				rearmTriggerButton.setEnabled(true);
			} else {
				rearmTriggerButton.setEnabled(false);
			}
		} else {
			System.err.println("Trigger mode is out of range.");
		}
	}

	/**
	 * Set trigger type
	 * 
	 * @param channelName
	 * @param triggerType
	 */
	public void setTriggerType(int triggerType) {
		if (triggerType >= 0 && triggerType <= 2) {
			triggerTypeComboBox.removeActionListener(this);
			triggerTypeComboBox.setSelectedIndex(triggerType);
			triggerTypeComboBox.addActionListener(this);
			previousTriggerTypeIndex_ = triggerType;
		} else {
			System.err.println("Trigger type is out of range.");
		}
	}

	/**
	 * Set channel coupling
	 * 
	 * @param channelName
	 * @param channelCoupling
	 */
	public void setChannelCoupling(String channelName, int channelCoupling) {
		if (channelName.equals(Constant.CHANNEL_A)) {
			if (channelCoupling == Constant.DC) {
				channelCouplingAToggleButton.setSelected(true);
				channelCouplingAToggleButton.setText("DC");
			} else {
				channelCouplingAToggleButton.setSelected(false);
				channelCouplingAToggleButton.setText("AC");
			}

		} else if (channelName.equals(Constant.CHANNEL_B)) {
			if (channelCoupling == Constant.DC) {
				channelCouplingBToggleButton.setSelected(true);
				channelCouplingBToggleButton.setText("DC");
			} else {
				channelCouplingBToggleButton.setSelected(false);
				channelCouplingBToggleButton.setText("AC");
			}
		}
	}

	/**
	 * Turn on or turn off function generator output
	 * 
	 * @param state
	 */
	public void setGeneratorOutput(int state) {
		if (state == Constant.GENERATOR_OFF) {
			outputToggleButton.setSelected(false);
			outputToggleButton.setText("OFF");
			setEnabledGeneratorChannelControls(false);
		} else {
			outputToggleButton.setSelected(true);
			outputToggleButton.setText("ON");
			setEnabledGeneratorChannelControls(true);
		}
	}

	/**
	 * Set the wave type of function generator
	 * 
	 * @param waveType
	 */
	public void setWaveType(int waveType) {
		waveTypeComboBox.removeActionListener(this);
		waveTypeComboBox.setSelectedIndex(waveType);
		waveTypeComboBox.addActionListener(this);
		previousWaveTypeIndex_ = waveTypeComboBox.getSelectedIndex();
	}

	/**
	 * Set sampling mode
	 * 
	 * @param channelName
	 * @param mode
	 */
	public void setSamplingMode(int mode) {
		if (mode == Constant.MODE_8BIT) {
			samplingModeToggleButton.setSelected(true);
			samplingModeToggleButton.setText("8-bit");
		} else {
			samplingModeToggleButton.setSelected(false);
			samplingModeToggleButton.setText("12-bit");
		}
	}

	/**
	 * Set XYSeries for the specified channel
	 * 
	 * @param channelName
	 *            channel's name
	 * @param xYSeries
	 * @param isUpdatePlot
	 *            flag indicates update Math and Filter channel
	 */
	public void setXYSeries(String channelName, XYSeries xYSeries, boolean isUpdatePlot) {
		rawXYSeries.put(channelName, xYSeries);
		updateInputChannelComboBox();
		if (isUpdatePlot = true) {
			if (channelName.equals(Constant.CHANNEL_A)) {
				String expression = expressionTextArea.getText().trim();
				if (expression.contains("A")) {
					calculateMathChannel(expressionTextArea.getText().trim());
				}
				String inputChannelForFilter = (String) inputChannelComboBox.getSelectedItem();
				if (inputChannelForFilter.equals(Constant.CHANNEL_A)) {
					calculateFilterChannel();
				}
				if (channelACheckBox.isSelected()) {
					refreshChannelPlotOnChartPanel(channelName);
				}
			} else if (channelName.equals(Constant.CHANNEL_B)) {
				String expression = expressionTextArea.getText().trim();
				if (expression.contains("B")) {
					calculateMathChannel(expressionTextArea.getText().trim());
				}
				String inputChannelForFilter = (String) inputChannelComboBox.getSelectedItem();
				if (inputChannelForFilter.equals(Constant.CHANNEL_B)) {
					calculateFilterChannel();
				}
				if (channelBCheckBox.isSelected()) {
					refreshChannelPlotOnChartPanel(channelName);
				}
			}
		}
	}

	/**
	 * Get maximum voltage displayed on screen
	 * 
	 * @param channelIndex
	 * @return
	 */
	public double getMaxDisplayVoltage(int channelIndex) {
		return visualizer_.getVerticalRange(channelIndex).getUpperBound();
	}

	/**
	 * Get minimum voltage displayed on screen
	 * 
	 * @param channelIndex
	 * @return
	 */
	public double getMinDisplayVoltage(int channelIndex) {
		return visualizer_.getVerticalRange(channelIndex).getLowerBound();
	}

	/**
	 * Get maximum time displayed on screen
	 * 
	 * @return
	 */
	public double getMaxDisplayTime() {
		return visualizer_.getHorizontalRange().getUpperBound();
	}

	/**
	 * Get number of samples of specified channel
	 * 
	 * @param channelName
	 * @return number of samples or -1 if channel name is neither channel A or B
	 */
	public int getNoOfSamples() {
		return (int) noOfSamplesSpinner.getValue();
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO
		Object source = event.getSource();
		if (source == channelACheckBox) {
			if (channelACheckBox.isSelected()) {
				showTab(Constant.TAB.CHANNEL_A);
				showChannelPlotOnChartPanel(Constant.CHANNEL_A);
			} else {
				removeChannelPlotFromChartPanel(Constant.CHANNEL_A);
			}

		} else if (source == channelBCheckBox) {
			if (channelBCheckBox.isSelected()) {
				showTab(Constant.TAB.CHANNEL_B);
				showChannelPlotOnChartPanel(Constant.CHANNEL_B);
			} else {
				removeChannelPlotFromChartPanel(Constant.CHANNEL_B);
			}

		} else if (source == mathChannelCheckBox) {
			if (mathChannelCheckBox.isSelected()) {
				showTab(Constant.TAB.MATH_CHANNEL);
				showChannelPlotOnChartPanel(Constant.MATH_CHANNEL);
			} else {
				removeChannelPlotFromChartPanel(Constant.MATH_CHANNEL);
			}

		} else if (source == filterChannelCheckBox) {
			if (filterChannelCheckBox.isSelected()) {
				showTab(Constant.TAB.FILTER_CHANNEL);
				updateInputChannelComboBox();
				showChannelPlotOnChartPanel(Constant.FILTER_CHANNEL);
			} else {
				removeChannelPlotFromChartPanel(Constant.FILTER_CHANNEL);
			}

		} else if (source == cursorComboBox) {
			String selectChannel = (String) cursorComboBox.getSelectedItem();
			if (selectChannel == Constant.CHANNEL_A) {
				showCursorMeasurement(Constant.A_INDEX);
				cursorVerticalValueLabel.setForeground(Constant.A_COLOR);
			} else if (selectChannel == Constant.CHANNEL_B) {
				showCursorMeasurement(Constant.B_INDEX);
				cursorVerticalValueLabel.setForeground(Constant.B_COLOR);
			} else if (selectChannel == Constant.MATH_CHANNEL) {
				showCursorMeasurement(Constant.MATH_INDEX);
				cursorVerticalValueLabel.setForeground(Constant.MATH_COLOR);
			} else if (selectChannel == Constant.FILTER_CHANNEL) {
				showCursorMeasurement(Constant.FILTER_INDEX);
				cursorVerticalValueLabel.setForeground(Constant.FILTER_COLOR);
			} else {
				hideCursorMeasurement();
				cursorVerticalValueLabel.setForeground(Color.BLACK);
			}

		} else if (source == horizontalOffsetUnitAComboBox) {
			horizontalOffsetASpinner.setValue(0);

		} else if (source == horizontalOffsetUnitBComboBox) {
			horizontalOffsetBSpinner.setValue(0);

		} else if (source == horizontalOffsetUnitMathComboBox) {
			horizontalOffsetMathSpinner.setValue(0);

		} else if (source == horizontalOffsetUnitFilterComboBox) {
			horizontalOffsetFilterSpinner.setValue(0);

		} else if (source == verticalOffsetUnitMathComboBox) {
			verticalOffsetMathSpinner.setValue(0);

		} else if (source == verticalOffsetUnitFilterComboBox) {
			verticalOffsetFilterSpinner.setValue(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO
		Object source = event.getSource();

		if (source == channelCouplingAToggleButton) {
			int coupling;
			if (channelCouplingAToggleButton.isSelected()) {
				coupling = Constant.DC;
				channelCouplingAToggleButton.setSelected(false);
			} else {
				coupling = Constant.AC;
				channelCouplingAToggleButton.setSelected(true);
			}
			sendCommand(PacketType.CHANNEL_COUPLING_A, coupling);

		} else if (source == channelCouplingBToggleButton) {
			int coupling;
			if (channelCouplingBToggleButton.isSelected()) {
				coupling = Constant.DC;
				channelCouplingBToggleButton.setSelected(false);
			} else {
				coupling = Constant.AC;
				channelCouplingBToggleButton.setSelected(true);
			}
			sendCommand(PacketType.CHANNEL_COUPLING_B, coupling);

		} else if (source == samplingModeToggleButton) {
			int mode;
			if (samplingModeToggleButton.isSelected()) {
				mode = Constant.MODE_8BIT;
				samplingModeToggleButton.setSelected(false);
			} else {
				mode = Constant.MODE_12_BIT;
				samplingModeToggleButton.setSelected(true);
			}
			sendCommand(PacketType.SAMPLING_MODE, mode);

		} else if (source == horizontalRangeComboBox) {
			String timeString = (String) horizontalRangeComboBox.getSelectedItem();
			horizontalRangeComboBox.removeActionListener(this);
			horizontalRangeComboBox.setSelectedIndex(previousHorizontalRangeIndex_);
			horizontalRangeComboBox.addActionListener(this);
			int horizontalRange = convertTimeStringToMicroSeconds(timeString);
			sendCommand(PacketType.HORIZONTAL_RANGE, horizontalRange);

		} else if (source == verticalRangeAComboBox) {
			String voltageString = (String) verticalRangeAComboBox.getSelectedItem();
			verticalRangeAComboBox.removeActionListener(this);
			verticalRangeAComboBox.setSelectedIndex(previousVerticalRangeAIndex_);
			verticalRangeAComboBox.addActionListener(this);
			int verticalRange = convertVoltageStringToMicrovolts(voltageString);
			sendCommand(PacketType.VERTICAL_RANGE_A, verticalRange);

		} else if (source == verticalRangeBComboBox) {
			String voltageString = (String) verticalRangeBComboBox.getSelectedItem();
			verticalRangeBComboBox.removeItemListener(this);
			verticalRangeBComboBox.setSelectedIndex(previousVerticalRangeBIndex_);
			verticalRangeBComboBox.addItemListener(this);
			int verticalRange = convertVoltageStringToMicrovolts(voltageString);
			sendCommand(PacketType.VERTICAL_RANGE_B, verticalRange);

		} else if (source == verticalRangeMathComboBox) {
			String selectedItem = (String) verticalRangeMathComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForVerticalGridSpacing(Constant.MATH_INDEX, verticalRange);
			showMeasurementResults(Constant.MATH_INDEX);
			if (measuredChannelIndex_ == Constant.MATH_INDEX) {
				visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			}
			mathDivisionInfoLabel.setText("Math: " + selectedItem + "/div");

		} else if (source == verticalRangeFilterComboBox) {
			String selectedItem = (String) verticalRangeFilterComboBox.getSelectedItem();
			double verticalRange = convertVoltageStringToVolts(selectedItem);
			visualizer_.setValueForVerticalGridSpacing(Constant.FILTER_INDEX, verticalRange);
			showMeasurementResults(Constant.FILTER_INDEX);
			if (measuredChannelIndex_ == Constant.FILTER_INDEX) {
				visualizer_.setValueForCommonVerticalGridSpacing(verticalRange);
			}
			filterDivisionInfoLabel.setText("Filter: " + selectedItem + "/div");

		} else if (source == forceTriggerButton) {
			sendCommand(PacketType.TRIGGER_FORCE, Constant.IGNORE);

		} else if (source == triggerModeComboBox) {
			int mode = triggerModeComboBox.getSelectedIndex();
			triggerModeComboBox.removeActionListener(this);
			triggerModeComboBox.setSelectedIndex(previousTriggerModeIndex_);
			triggerModeComboBox.addActionListener(this);
			sendCommand(PacketType.TRIGGER_MODE, mode);

		} else if (source == triggerTypeComboBox) {
			int mode = triggerTypeComboBox.getSelectedIndex();
			triggerTypeComboBox.removeActionListener(this);
			triggerTypeComboBox.setSelectedIndex(previousTriggerTypeIndex_);
			triggerTypeComboBox.addActionListener(this);
			sendCommand(PacketType.TRIGGER_TYPE, mode);

		} else if (source == verticalOffsetUnitAComboBox) {
			sendCommand(PacketType.DC_OFFSET_A, 0);
			sentVerticalOffsetACommand_ = true;

		} else if (source == verticalOffsetUnitBComboBox) {
			sendCommand(PacketType.DC_OFFSET_B, 0);
			sentVerticalOffsetBCommand_ = true;

		} else if (source == outputToggleButton) {
			int state;
			if (outputToggleButton.isSelected()) {
				state = Constant.GENERATOR_ON;
				outputToggleButton.setSelected(false);
			} else {
				state = Constant.GENERATOR_OFF;
				outputToggleButton.setSelected(true);
			}
			sendCommand(PacketType.GENERATOR_OUTPUT, state);

		} else if (source == waveTypeComboBox) {
			int waveType = waveTypeComboBox.getSelectedIndex();
			waveTypeComboBox.removeActionListener(this);
			waveTypeComboBox.setSelectedIndex(previousWaveTypeIndex_);
			waveTypeComboBox.addActionListener(this);
			sendCommand(PacketType.WAVE_TYPE, waveType);

		} else if (source == measureAToggleButton) {
			if (measureAToggleButton.isSelected()) {
				measureAToggleButton.setText("ON");
			} else {
				measureAToggleButton.setText("OFF");
			}
			showMeasurementResults(Constant.A_INDEX);

		} else if (source == measureBToggleButton) {
			if (measureBToggleButton.isSelected()) {
				measureBToggleButton.setText("ON");
			} else {
				measureBToggleButton.setText("OFF");
			}
			showMeasurementResults(Constant.B_INDEX);

		} else if (source == measureMathToggleButton) {
			if (measureMathToggleButton.isSelected()) {
				measureMathToggleButton.setText("ON");
			} else {
				measureMathToggleButton.setText("OFF");
			}
			showMeasurementResults(Constant.MATH_INDEX);

		} else if (source == measureFilterToggleButton) {
			if (measureFilterToggleButton.isSelected()) {
				measureFilterToggleButton.setText("ON");
			} else {
				measureFilterToggleButton.setText("OFF");
			}
			showMeasurementResults(Constant.FILTER_INDEX);

		} else if (source == triggerThresholdUnitComboBox) {
			sendCommand(PacketType.TRIGGER_THRESHOLD, 0);
			sentTriggerThresholdCommand_ = true;

		} else if (source == rearmTriggerButton) {
			sendCommand(PacketType.TRIGGER_ARM, Constant.IGNORE);

		} else if (source == verticalOffsetUnitGeneratorComboBox) {
			sendCommand(PacketType.GENERATOR_OFFSET, 0);
			sentVerticalOffsetGeneratorCommand_ = true;

		} else if (source == p2pVoltageUnitComboBox) {
			sendCommand(PacketType.GENERATOR_VOLTAGE, 0);
			sentP2PVoltageCommand_ = true;

		} else if (source == newExpressionButton) {
			ExpressionDialog expressionDialog = new ExpressionDialog(this);
			expressionDialog.setVisible(true);

		} else if (source == editExpressionButton) {
			String expression = expressionTextArea.getText();
			ExpressionDialog expressionDialog = new ExpressionDialog(this, expression);
			expressionDialog.setVisible(true);
			calculateMathChannel(expressionTextArea.getText().trim());

		} else if (source == removeFilterButton) {
			String expression = expressionTextArea.getText().trim();
			if (expression.contains("F")) {
				int response = JOptionPane.showConfirmDialog(this,
						"The MATH channel is derived from this channel.\n"
								+ "Removing this channel will also remove the MATH channel.\n"
								+ "Do you want to continue?",
						"Remove Filter Channel", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					expressionTextArea.setText("");
					mathChannelCheckBox.setSelected(false);
					rawXYSeries.remove(Constant.MATH_CHANNEL);
				} else {
					return;
				}
			}
			browseButton.setEnabled(true);
			inputChannelComboBox.setEnabled(true);
			removeChannelPlotFromChartPanel(Constant.FILTER_CHANNEL);
			rawXYSeries.remove(Constant.FILTER_CHANNEL);
			inputChannelComboBox.setSelectedIndex(0);
			filterFile_.setValid(false);
			csvFilePathTextField.setText("Choose CSV file");

		} else if (source == inputChannelComboBox) {
			calculateFilterChannel();

		} else if (source == browseButton) {
			String iconPath = "/icons/csv_icon_16x16.png";
			String decription = "Comma-separated-values file (*." + Constant.CSV_FILE_EXTENSION + ")";
			FileChooserUi fileChooser = new FileChooserUi(JFileChooser.FILES_ONLY, Constant.CSV_FILE_EXTENSION,
					iconPath, decription);
			int status = fileChooser.showOpenDialog(this);
			if (status == JFileChooser.APPROVE_OPTION) {
				File csvFile = fileChooser.getSelectedFile();
				if (filterFile_.loadCsvFile(csvFile.getAbsolutePath())) {
					csvFilePathTextField.setForeground(Color.BLACK);
					csvFilePathTextField.setText(csvFile.getName());
					csvFilePathTextField.setToolTipText(csvFile.getAbsolutePath());
					calculateFilterChannel();
				} else {
					csvFilePathTextField.setForeground(Color.RED);
					csvFilePathTextField.setText("The choosen file is not valid!");
					csvFilePathTextField.setToolTipText("");
				}
			}
			
		} else if (source == removeExpressionButton) {
			if (inputChannelComboBox.getSelectedItem().equals(Constant.MATH_CHANNEL)) {
				int response = JOptionPane.showConfirmDialog(this,
						"The FILTER channel is derived from this channel.\n"
								+ "Removing this channel will also remove the FILTER channel.\n"
								+ "Do you want to continue?",
						"Remove Math Channel", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					filterChannelCheckBox.setSelected(false);
					rawXYSeries.remove(Constant.FILTER_CHANNEL);
					inputChannelComboBox.setSelectedIndex(0);
					filterFile_.setValid(false);
					csvFilePathTextField.setText("Choose CSV file");
				} else {
					return;
				}
			}
			expressionTextArea.setText("");
			setEnabledExpressionControls(false);
			newExpressionButton.setEnabled(true);
			removeChannelPlotFromChartPanel(Constant.MATH_CHANNEL);
			rawXYSeries.remove(Constant.MATH_CHANNEL);
			updateInputChannelComboBox();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO
		Object source = e.getSource();
		if (source == channelTabbedPane) {
			switch (channelTabbedPane.getSelectedIndex()) {
			case Constant.A_INDEX:
				showTriggerControls(Constant.TAB.CHANNEL_A);
				break;

			case Constant.B_INDEX:
				showTriggerControls(Constant.TAB.CHANNEL_B);
				break;

			case Constant.MATH_INDEX:
				showTriggerControls(Constant.TAB.MATH_CHANNEL);
				break;

			case Constant.FILTER_INDEX:
				showTriggerControls(Constant.TAB.FILTER_CHANNEL);
				break;
			}

		} else if (source == verticalOffsetASpinner) {
			int offset = convertToMicrovolt((int) verticalOffsetASpinner.getValue(),
					(String) verticalOffsetUnitAComboBox.getSelectedItem());
			verticalOffsetASpinner.removeChangeListener(this);
			verticalOffsetASpinner.setValue(previousVerticalOffsetAValue_);
			verticalOffsetASpinner.addChangeListener(this);
			sendCommand(PacketType.DC_OFFSET_A, offset);
			sentVerticalOffsetACommand_ = true;

		} else if (source == verticalOffsetBSpinner) {
			int offset = convertToMicrovolt((int) verticalOffsetBSpinner.getValue(),
					(String) verticalOffsetUnitBComboBox.getSelectedItem());
			verticalOffsetBSpinner.removeChangeListener(this);
			verticalOffsetBSpinner.setValue(previousVerticalOffsetBValue_);
			verticalOffsetBSpinner.addChangeListener(this);
			sendCommand(PacketType.DC_OFFSET_B, offset);
			sentVerticalOffsetBCommand_ = true;

		} else if (source == verticalOffsetMathSpinner) {
			if (mathChannelCheckBox.isSelected()) {
				refreshChannelPlotOnChartPanel(Constant.MATH_CHANNEL);
			}

		} else if (source == verticalOffsetFilterSpinner) {
			if (filterChannelCheckBox.isSelected()) {
				refreshChannelPlotOnChartPanel(Constant.FILTER_CHANNEL);
			}

		} else if (source == noOfSamplesSpinner) {
			int nSamples = (int) noOfSamplesSpinner.getValue();
			noOfSamplesSpinner.removeChangeListener(this);
			noOfSamplesSpinner.setValue(previousNoOfSamples_);
			noOfSamplesSpinner.addChangeListener(this);
			if (nSamples > 0) {
				sendCommand(PacketType.NUMBER_OF_SAMPLES, nSamples);
			} else {
				// Ignore
			}

		} else if (source == triggerThresholdSpinner) {
			int threshold = convertToMicrovolt((int) triggerThresholdSpinner.getValue(),
					(String) triggerThresholdUnitComboBox.getSelectedItem());
			triggerThresholdSpinner.removeChangeListener(this);
			;
			triggerThresholdSpinner.setValue(previousTriggerThresholdValue_);
			triggerThresholdSpinner.addChangeListener(this);
			sendCommand(PacketType.TRIGGER_THRESHOLD, threshold);
			sentTriggerThresholdCommand_ = true;

		} else if (source == verticalOffsetGeneratorSpinner) {
			int offset = convertToMicrovolt((int) verticalOffsetGeneratorSpinner.getValue(),
					(String) verticalOffsetUnitGeneratorComboBox.getSelectedItem());
			verticalOffsetGeneratorSpinner.removeChangeListener(this);
			verticalOffsetGeneratorSpinner.setValue(previousVerticalOffsetGeneratorValue_);
			verticalOffsetGeneratorSpinner.addChangeListener(this);
			sendCommand(PacketType.GENERATOR_OFFSET, offset);
			sentVerticalOffsetGeneratorCommand_ = true;

		} else if (source == p2pVoltageSpinner) {
			int p2pVoltage = convertToMicrovolt((int) p2pVoltageSpinner.getValue(),
					(String) p2pVoltageUnitComboBox.getSelectedItem());
			p2pVoltageSpinner.removeChangeListener(this);
			p2pVoltageSpinner.setValue(previousP2PVoltageValue_);
			p2pVoltageSpinner.addChangeListener(this);
			sendCommand(PacketType.GENERATOR_VOLTAGE, p2pVoltage);
			sentP2PVoltageCommand_ = true;

		} else if (source == generatorFrequencySpinner) {
			int frequency = (int) generatorFrequencySpinner.getValue();
			generatorFrequencySpinner.removeChangeListener(this);
			generatorFrequencySpinner.setValue(previousGeneratorFrequency_);
			generatorFrequencySpinner.addChangeListener(this);
			sendCommand(PacketType.GENERATOR_FREQUENCY, frequency);

		} else if (source == horizontalOffsetASpinner) {
			if (channelACheckBox.isSelected()) {
				refreshChannelPlotOnChartPanel(Constant.CHANNEL_A);
			}

		} else if (source == horizontalOffsetBSpinner) {
			if (channelBCheckBox.isSelected()) {
				refreshChannelPlotOnChartPanel(Constant.CHANNEL_B);
			}

		} else if (source == horizontalOffsetMathSpinner) {
			if (mathChannelCheckBox.isSelected()) {
				refreshChannelPlotOnChartPanel(Constant.MATH_CHANNEL);
			}

		} else if (source == horizontalOffsetFilterSpinner) {
			if (filterChannelCheckBox.isSelected()) {
				refreshChannelPlotOnChartPanel(Constant.FILTER_CHANNEL);
			}
		}
	}
}
