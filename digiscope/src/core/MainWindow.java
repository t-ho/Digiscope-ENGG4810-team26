package core;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import data.Constant;
import gui.MainWindowUi;

/**
 *
 * @author ToanHo
 */
public class MainWindow extends MainWindowUi {

	private static final long serialVersionUID = 1L;
	private LaunchWindow launchWindow_;

	public MainWindow(LaunchWindow launchWindow) {
		super();
		initialize();
		setLaunchWindow(launchWindow);
	}

	private void initialize() {
		// TODO
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent event) {
				mainWindowClosed(event);
			}
		});
		
		mathChannelCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				mathChannelCheckboxItemStateChanged(event);
			}
		});
		
		filterChannelCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				filterChannelCheckboxItemStateChanged(event);
			}
		});
	}

	private void mathChannelCheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(mathChannelCheckbox.isSelected()) {
			setEnabledMathChannel(true);
		} else {
			setEnabledMathChannel(false);
		}
	}

	private void filterChannelCheckboxItemStateChanged(ItemEvent event) {
		// TODO
		if(filterChannelCheckbox.isSelected()) {
			setEnabledFilterChannel(true);
		} else {
			setEnabledFilterChannel(false);
		}
	}

	private void setEnabledMathChannel(boolean enabled) {
		equationTextField.setEnabled(enabled);
		aButton.setEnabled(enabled);
		bButton.setEnabled(enabled);
		fButton.setEnabled(enabled);
		powerButton.setEnabled(enabled);
		plusButton.setEnabled(enabled);
		minusButton.setEnabled(enabled);
		piButton.setEnabled(enabled);
		eButton.setEnabled(enabled);
		leftParatheseButton.setEnabled(enabled);
		rightParatheseButton.setEnabled(enabled);
		multiplyButton.setEnabled(enabled);
		divideButton.setEnabled(enabled);
		horizontalOffsetMathSpinner.setEnabled(enabled);
		horizontalRangeMathComboBox.setEnabled(enabled);
		verticalOffsetMathSpinner.setEnabled(enabled);
		verticalRangeMathComboBox.setEnabled(enabled);
	}

	private void setEnabledFilterChannel(boolean enabled){
		inputChannelComboBox.setEnabled(enabled);
		browseButton.setEnabled(enabled);
		horizontalOffsetFilterSpinner.setEnabled(enabled);
		horizontalRangeFilterComboBox.setEnabled(enabled);
		verticalOffsetFilterSpinner.setEnabled(enabled);
		verticalRangeFilterComboBox.setEnabled(enabled);
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
