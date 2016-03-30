package core;

import java.awt.EventQueue;

import core.LaunchWindow;
import data.Constant;

/**
 *
 * @author ToanHo
 */
public class Digiscope {
	
	private LaunchWindow launchWindow_;
	
	public Digiscope() {
		launchWindow_ = new LaunchWindow();
		launchWindow_.setVisible(true);
	}

	public static void main(String[] args) {
		Constant.setLookAndFeel();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Digiscope();
			}
		});
	}

}
