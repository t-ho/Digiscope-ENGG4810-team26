package data;

import javax.swing.UIManager;

/**
 *
 * @author ToanHo
 */
public class Constant {

	public static final String APPLICATION_TITLE = "Digiscope - Team 26";
	
	public static final String[] VERTICAL_RANGE_VALUES = new String[] {
		"1 us", "2 us", "5 us", "10 us", "20 us", "50 us", "100 us", "200 us",
		"500 us", "1 ms", "2 ms", "5 ms", "10 ms", "20 ms", "50 ms", "100 ms",
		"200 ms", "500 ms", "1 s" };

	public static final String[] HORIZONTAL_RANGE_VALUES = new String[] {
		"20 mV", "50 mV", "100 mV", "200 mV", "500 mV", "1 V", "2 V" };

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
	
}
