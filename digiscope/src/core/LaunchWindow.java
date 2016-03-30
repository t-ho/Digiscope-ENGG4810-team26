package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.LaunchWindowUI;

/**
 *
 * @author ToanHo
 */
public class LaunchWindow extends LaunchWindowUI {
	public LaunchWindow() {
		super();
		initialize();
	}
	
	private void initialize() {
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				statusLabel.setText("Not implement yet!");
			}
		});
	}
}
