package core;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.jfree.chart.ChartPanel;
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
		XYSeries aSeries = new XYSeries("Channel A");
		for(double i = -20; i <= 20; i = i + 0.1) {
			aSeries.add(i, 30 * (3*Math.sin(i)));
		}
		visualizer = new Visualizer(Constant.A_INDEX, aSeries);
		chartPanel = new ChartPanel(visualizer.getChart());
		addComponentToCanvasPanel(chartPanel);
		// endTest
	}

	private void initialize() {
		// TODO
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

	private void horizontalRangeFilterComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) horizontalRangeFilterComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValuePerHorizontalGridSpacing(horizontalRange);
	}

	private void horizontalRangeMathComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) horizontalRangeMathComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValuePerHorizontalGridSpacing(horizontalRange);
	}

	private void horizontalRangeBComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) horizontalRangeBComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValuePerHorizontalGridSpacing(horizontalRange);
	}

	private void horizontalRangeAComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) horizontalRangeAComboBox.getSelectedItem();
		int horizontalRange = changeTimeStringToMicroSeconds(selectedItem);
		visualizer.setValuePerHorizontalGridSpacing(horizontalRange);
	}

	private void verticalRangeFilterComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) verticalRangeFilterComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		visualizer.setValuePerVerticalGridSpacing(Constant.FILTER_INDEX, verticalRange);
	}

	private void verticalRangeMathComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) verticalRangeMathComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		visualizer.setValuePerVerticalGridSpacing(Constant.MATH_INDEX, verticalRange);
	}

	private void verticalRangeBComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) verticalRangeBComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		visualizer.setValuePerVerticalGridSpacing(Constant.B_INDEX, verticalRange);
	}

	private void verticalRangeAComboBoxItemStateChanged(ItemEvent event) {
		// TODO
		String selectedItem = (String) verticalRangeAComboBox.getSelectedItem();
		int verticalRange = changeVoltStringToMiliVolts(selectedItem);
		visualizer.setValuePerVerticalGridSpacing(Constant.A_INDEX, verticalRange);
	}

	private void channelACheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(channelACheckBox.isSelected()) {
			setEnabledChannelA(true);
			showTab(Constant.TAB.CHANNEL_A);
			XYSeries aSeries = new XYSeries("Channel A");
			for(double i = -20; i <= 20; i = i + 0.1) {
				aSeries.add(i, 3*Math.sin(i));
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
			XYSeries aSeries = new XYSeries("Channel B");
			for(double i = -20; i <= 20; i = i + 0.1) {
				aSeries.add(i, 2*Math.sin(i));
			}
			visualizer.addSeriesToDataset(Constant.B_INDEX, aSeries);
			showTab(Constant.TAB.CHANNEL_B);
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
		} else {
			setEnabledMathChannel(false);
		}
	}

	private void filterChannelCheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(filterChannelCheckBox.isSelected()) {
			setEnabledFilterChannel(true);
			showTab(Constant.TAB.FILTER_CHANNEL);
		} else {
			setEnabledFilterChannel(false);
		}
	}

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
	
	private int changeTimeStringToMicroSeconds(String voltString) {
		int value = 0;
		switch(voltString) {
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
