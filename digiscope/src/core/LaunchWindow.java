package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.LaunchWindowUi;

/**
 *
 * @author ToanHo
 */
public class LaunchWindow extends LaunchWindowUi {
	private MainWindow mainWindow_;

	public LaunchWindow() {
		super();
		initialize();
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
		statusLabel.setText("Not implement yet!");
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
