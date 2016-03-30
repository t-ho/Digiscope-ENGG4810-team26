package data;

import javax.swing.UIManager;

/**
 *
 * @author ToanHo
 */
public class Constant {

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
