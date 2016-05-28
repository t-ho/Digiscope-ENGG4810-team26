package core;

import javax.swing.SwingUtilities;

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
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		launchWindow_.setVisible(true);
	}

	public static void main(String[] args) {
		Constant.setLookAndFeel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Digiscope();
			}
		});
	}

}
