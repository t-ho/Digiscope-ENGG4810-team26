package core;

import javax.swing.SwingUtilities;

import core.LaunchWindow;
import data.Constant;
import data.Constant.LookAndFeel;

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
		Constant.setLookAndFeel(LookAndFeel.Metal);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Digiscope();
			}
		});
	}

}
