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
			aSeries.add(i, 3*Math.sin(i));
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

	}

	private void channelACheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(channelACheckBox.isSelected()) {
			setEnabledChannelA(true);
			showTab(Constant.TAB.CHANNEL_A);
		} else {
			setEnabledChannelA(false);
		}
	}

	private void channelBCheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(channelBCheckBox.isSelected()) {
			setEnabledChannelB(true);
			showTab(Constant.TAB.CHANNEL_B);
		} else {
			setEnabledChannelB(false);
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
