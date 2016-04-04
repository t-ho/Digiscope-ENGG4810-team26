package core;

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
