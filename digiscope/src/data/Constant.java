package data;

import java.awt.Image;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author ToanHo
 */
public class Constant {

	public static final String APPLICATION_TITLE = "Digiscope - Team 26";
	
	public static final String[] VERTICAL_RANGE_VALUES = new String[] {
		"20 mV", "50 mV", "100 mV", "200 mV", "500 mV", "1 V", "2 V" };

	public static final String[] HORIZONTAL_RANGE_VALUES = new String[] {
		"1 us", "2 us", "5 us", "10 us", "20 us", "50 us", "100 us", "200 us",
		"500 us", "1 ms", "2 ms", "5 ms", "10 ms", "20 ms", "50 ms", "100 ms",
		"200 ms", "500 ms", "1 s" };

	public static final String[] WAVE_TYPES = new String[] {
		"Sine", "Square", "Triangle", "Ramp", "Random noise" };

	public static final String[] INPUT_CHANNELS = new String[] { 
		"Channel A", "Channel B", "Math Channel" };

	public static final String[] TRIGGER_MODES = new String[] { 
		"Auto", "Normal", "Single" };

	public static final  String [] TRIGGER_TYPES = new String[] { 
		"Rising edge", "Falling edge", "Level" };

	public static final int ERROR = 1;

	public static final int NORMAL = 0;

	public static final String IP_ADDRESS_PATTERN =
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static enum TAB {CHANNEL_A, CHANNEL_B, MATH_CHANNEL, FILTER_CHANNEL};
	
	public static int VERTICAL_GRID_SPACINGS = 10;
	public static int HORIZONTAL_GRID_SPACINGS = 16;
	
	public static int DEFAULT_VERTICAL_RANGE = 20;
	public static int DEFAULT_HORIZONTAL_RANGE = 1;
	
	public static final int NUMBER_OF_CHANNELS = 4;
	public static final int A_INDEX = 0;
	public static final int B_INDEX = 1;
	public static final int MATH_INDEX = 2;
	public static final int FILTER_INDEX = 3;

	/**
	 * Set application's LookAndFeel based on operating system type.
	 * Use default system look and feel when in Microsoft Windows.
	 * Otherwise, cross-platform look and feel is applied. 
	 */
	public static void setLookAndFeel() {
		try {
			String os = System.getProperty("os.name");
			if(os.contains("Windows")) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} else {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				/* Turn off metal's use bold fonts */
				UIManager.put("swing.boldMetal", Boolean.FALSE);
			}
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/** Set an icon for application
	 * @return */
	public static void setApplicationIcon(JFrame frame) {
		ImageIcon imageIcon = new ImageIcon(frame.getClass().getResource("/icons/oscilloscope_48x48.png")); 
		Image image = imageIcon.getImage();
		String os = System.getProperty("os.name");
		if (os.contains("Mac")) {
			try {
				Class<?> applicationClass = Class.forName("com.apple.eawt.Application");
				Method getAppMethod = applicationClass.getMethod("getApplication");
				Method setDockIconMethod = applicationClass.getMethod("setDockIconImage", Image.class);
				Object app = getAppMethod.invoke(applicationClass);
				setDockIconMethod.invoke(app, image);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			frame.setIconImage(image);
		}

	}
}
