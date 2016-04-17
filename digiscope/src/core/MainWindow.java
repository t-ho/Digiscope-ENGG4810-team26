package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

import data.Constant;
import gui.MainWindowUi;

/**
 *
 * @author ToanHo
 */
public class MainWindow extends MainWindowUi {

	private static final long serialVersionUID = 1L;
	private LaunchWindow launchWindow_;
	private Visualizer visualizer;
	private ChartPanel chartPanel;

	public MainWindow(LaunchWindow launchWindow) {
		super();
		initialize();
		setLaunchWindow(launchWindow);
		
		// Test
		visualizer = new Visualizer();
		String selectedItem = (String) verticalRangeAComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		selectedItem = (String) horizontalRangeAComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValueForHorizontalGridSpacing(horizontalRange);
		visualizer.setValueForVerticalGridSpacing(Constant.A_INDEX, verticalRange);
		chartPanel = createDefaultChartPanel(visualizer.getChart());
		addComponentToCanvasPanel(chartPanel);
		// endTest
	}

	private void initialize() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event) {
				mainWindowClosed(event);
			}
		});
		
		channelACheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				channelACheckboxItemStateChanged(event);
			}
		});

		channelBCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				channelBCheckboxItemStateChanged(event);
			}
		});
		
		mathChannelCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				mathChannelCheckboxItemStateChanged(event);
			}
		});
		
		filterChannelCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				filterChannelCheckboxItemStateChanged(event);
			}
		});

		horizontalRangeAComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeAComboBoxItemStateChanged(event);
			}
		});

		horizontalRangeBComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeBComboBoxItemStateChanged(event);
			}
		});

		horizontalRangeMathComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeMathComboBoxItemStateChanged(event);
			}
		});

		horizontalRangeFilterComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				horizontalRangeFilterComboBoxItemStateChanged(event);
			}
		});
		
		horizontalRangeAComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeAComboBoxActionPerformed(event);
			}
		});
		
		horizontalRangeBComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeBComboBoxActionPerformed(event);
			}
		});

		horizontalRangeMathComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeMathComboBoxActionPerformed(event);
			}
		});
		
		horizontalRangeFilterComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				horizontalRangeFilterComboBoxActionPerformed(event);
			}
		});

		verticalRangeAComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeAComboBoxItemStateChanged(event);
			}
		});

		verticalRangeBComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeBComboBoxItemStateChanged(event);
			}
		});

		verticalRangeMathComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeMathComboBoxItemStateChanged(event);
			}
		});

		verticalRangeFilterComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				verticalRangeFilterComboBoxItemStateChanged(event);
			}
		});
	}

	private void horizontalRangeAComboBoxActionPerformed(ActionEvent event) {
		// TODO 
		int selectedIndex = horizontalRangeAComboBox.getSelectedIndex();
		horizontalRangeBComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeMathComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeFilterComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeBComboBoxActionPerformed(ActionEvent event) {
		// TODO 
		int selectedIndex = horizontalRangeBComboBox.getSelectedIndex();
		horizontalRangeAComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeMathComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeFilterComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeMathComboBoxActionPerformed(ActionEvent event) {
		// TODO
		int selectedIndex = horizontalRangeMathComboBox.getSelectedIndex();
		horizontalRangeAComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeBComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeFilterComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeFilterComboBoxActionPerformed(ActionEvent event) {
		// TODO
		int selectedIndex = horizontalRangeFilterComboBox.getSelectedIndex();
		horizontalRangeAComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeBComboBox.setSelectedIndex(selectedIndex);
		horizontalRangeMathComboBox.setSelectedIndex(selectedIndex);
	}

	private void horizontalRangeAComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) horizontalRangeAComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValueForHorizontalGridSpacing(horizontalRange);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void horizontalRangeBComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) horizontalRangeBComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValueForHorizontalGridSpacing(horizontalRange);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void horizontalRangeMathComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) horizontalRangeMathComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValueForHorizontalGridSpacing(horizontalRange);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void horizontalRangeFilterComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) horizontalRangeFilterComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValueForHorizontalGridSpacing(horizontalRange);
		horizontalDivisionInfoLabel.setText("Horizontal: " + selectedItem + "/div");
	}

	private void verticalRangeAComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) verticalRangeAComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		visualizer.setValueForVerticalGridSpacing(Constant.A_INDEX, verticalRange);
		aDivisionInfoLabel.setText("A: " + selectedItem + "/div");
	}

	private void verticalRangeBComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) verticalRangeBComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		visualizer.setValueForVerticalGridSpacing(Constant.B_INDEX, verticalRange);
		bDivisionInfoLabel.setText("B: " + selectedItem + "/div");
	}

	private void verticalRangeMathComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) verticalRangeMathComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		visualizer.setValueForVerticalGridSpacing(Constant.MATH_INDEX, verticalRange);
		mathDivisionInfoLabel.setText("Math: " + selectedItem + "/div");
	}

	private void verticalRangeFilterComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) verticalRangeFilterComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		visualizer.setValueForVerticalGridSpacing(Constant.FILTER_INDEX, verticalRange);
		filterDivisionInfoLabel.setText("Filter: " + selectedItem + "/div");
	}

	private void channelACheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(channelACheckBox.isSelected()) {
			setEnabledChannelA(true);
			showTab(Constant.TAB.CHANNEL_A);
			XYSeries aSeries = new XYSeries("Channel A");
			for(double i = -20; i <= 20; i = i + 0.1) {
				aSeries.add(i, 240 * Math.sin(i));
			}
			visualizer.addSeriesToDataset(Constant.A_INDEX, aSeries);
		} else {
			setEnabledChannelA(false);
			visualizer.removeAllSeriesFromDataset(Constant.A_INDEX);
		}
	}

	private void channelBCheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(channelBCheckBox.isSelected()) {
			setEnabledChannelB(true);
			showTab(Constant.TAB.CHANNEL_B);
			XYSeries aSeries = new XYSeries("Channel B");
			for(double i = -20; i <= 20; i = i + 0.1) {
				aSeries.add(i, 70 *Math.cos(i));
			}
			visualizer.addSeriesToDataset(Constant.B_INDEX, aSeries);
		} else {
			setEnabledChannelB(false);
			visualizer.removeAllSeriesFromDataset(Constant.B_INDEX);
		}
	}

	private void mathChannelCheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(mathChannelCheckBox.isSelected()) {
			setEnabledMathChannel(true);
			showTab(Constant.TAB.MATH_CHANNEL);
			XYSeries aSeries = new XYSeries("Math Channel");
			for(double i = -20; i <= 20; i = i + 0.1) {
				aSeries.add(i, 100 * Math.sin(i));
			}
			visualizer.addSeriesToDataset(Constant.MATH_INDEX, aSeries);
		} else {
			setEnabledMathChannel(false);
			visualizer.removeAllSeriesFromDataset(Constant.MATH_INDEX);
		}
	}

	private void filterChannelCheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(filterChannelCheckBox.isSelected()) {
			setEnabledFilterChannel(true);
			showTab(Constant.TAB.FILTER_CHANNEL);
			XYSeries aSeries = new XYSeries("Filter Channel");
			for(double i = -20; i <= 20; i = i + 0.1) {
				aSeries.add(i, 150 * Math.sin(i));
			}
			visualizer.addSeriesToDataset(Constant.FILTER_INDEX, aSeries);
		} else {
			setEnabledFilterChannel(false);
			visualizer.removeAllSeriesFromDataset(Constant.FILTER_INDEX);
		}
	}

	/**
	 * Change the voltage string to milivolts
	 * @param voltString The voltage string. e.g. 200 mV, 1 V,...
	 * @return the voltage in milivolts
	 */
	private int changeVoltStringToMiliVolts(String voltString) {
		int value = 0;
		switch(voltString) {
			case "20 mV":
				value = 20;
				break;
			case "50 mV":
				value = 50;
				break;
			case "100 mV":
				value = 100;
				break;
			case "200 mV":
				value = 200;
				break;
			case "500 mV":
				value = 500;
				break;
			case "1 V":
				value = 1000;
				break;
			case "2 V":
				value = 2000;
				break;
			default:
				// Cannot reach here
				value = 20;
		}
		return value;
	}
	
	/**
	 * Change time string to micro seconds
	 * @param timeString The time string. e.g. 1 us, 500 ms
	 * @return the microseconds
	 */
	private int changeTimeStringToMicroSeconds(String timeString) {
		int value = 0;
		switch(timeString) {
			case "1 us":
				value = 1;
				break;
			case "2 us":
				value = 2;
				break;
			case "5 us":
				value = 5;
				break;
			case "10 us":
				value = 10;
				break;
			case "20 us":
				value = 20;
				break;
			case "50 us":
				value = 50;
				break;
			case "100 us":
				value = 100;
				break;
			case "200 us":
				value = 200;
				break;
			case "500 us":
				value = 500;
				break;
			case "1 ms":
				value = 1000;
				break;
			case "2 ms":
				value = 2000;
				break;
			case "5 ms":
				value = 5000;
				break;
			case "10 ms":
				value = 10000;
				break;
			case "20 ms":
				value = 20000;
				break;
			case "50 ms":
				value = 50000;
				break;
			case "100 ms":
				value = 100000;
				break;
			case "200 ms":
				value = 200000;
				break;
			case "500 ms":
				value = 500000;
				break;
			case "1 s":
				value = 1000000;
				break;
			default:
				// Cannot reach here
				value = 1;
		}
		return value;
	}
	
	/**
	 * Create default chart panel
	 * @param chart 
	 * @return ChartPanel instance
	 */
	private ChartPanel createDefaultChartPanel(JFreeChart chart) {
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setMouseZoomable(false);
		return chartPanel;
	}

	private void mainWindowClosed(WindowEvent event) {
		getLaunchWindow().setStatus("To connect, please enter the IP address!", Constant.NORMAL);
		getLaunchWindow().setVisible(true);
	}

	public LaunchWindow getLaunchWindow() {
		return launchWindow_;
	}

	public void setLaunchWindow(LaunchWindow launchWindow) {
		this.launchWindow_ = launchWindow;
	}
}
