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
		"500 us", "1ms", "2 ms", "5 ms", "10 ms", "20 ms", "50 ms", "100 ms",
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
	
	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
