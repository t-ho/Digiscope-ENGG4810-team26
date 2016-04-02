package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.Constant;
import gui.LaunchWindowUi;

/**
 *
 * @author ToanHo
 */
public class LaunchWindow extends LaunchWindowUi {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow_;

	public LaunchWindow() {
		super();
		initialize();
		setStatus("Please enter the IP Address!", Constant.NORMAL);
	}

	private void initialize() {
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				connectButtonActionPerformed(event);
			}
		});
	}

	private void connectButtonActionPerformed(ActionEvent event) {
		setMainWindow(new MainWindow(this));
		setVisible(false);
		getMainWindow().setVisible(true);
	}

	public MainWindow getMainWindow() {
		return mainWindow_;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow_ = mainWindow;
	}
}
