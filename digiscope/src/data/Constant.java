package data;

import java.awt.Color;
import java.awt.Image;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author ToanHo
 */
public class Constant {

	public static final String APPLICATION_TITLE = "Digiscope - Team 26";

	public static final String CSV_FILE_EXTENSION = "csv";
	
	public static final int PORT_NUMBER = 4810;
	public static final String DEFAULT_IP_ADDRESS = "192.168.137.8";
//	public static final String DEFAULT_IP_ADDRESS = "127.0.0.1";
	
	public static final int DEFAULT_NUMBER_OF_SAMPLES = 25000;
	
	public static final int FIR = 1;
	public static final int IIR = 2;
	
	public static final String ONE_MILIVOLT = "1 mV";
	public static final String TEN_MILIVOLTS = "10 mV";
	public static final String TWENTY_MILIVOLTS = "20 mV";
	public static final String FIFTY_MILIVOLTS = "50 mV";
	public static final String ONE_HUNDRED_MILIVOLTS = "100 mV";
	public static final String TWO_HUNDRED_MILIVOLTS = "200 mV";
	public static final String FIVE_HUNDRED_MILIVOLTS = "500 mV";
	public static final String ONE_VOLT = "1 V";
	public static final String TWO_VOLTS = "2 V";
	
	public static final String[] VERTICAL_RANGE_VALUES = new String[] {
		TWENTY_MILIVOLTS, FIFTY_MILIVOLTS, ONE_HUNDRED_MILIVOLTS, TWO_HUNDRED_MILIVOLTS,
		FIVE_HUNDRED_MILIVOLTS, ONE_VOLT, TWO_VOLTS };
	
	public static final String[] VERTICAL_OFFSET_UNITS = new String[] {
		ONE_MILIVOLT, TEN_MILIVOLTS, ONE_HUNDRED_MILIVOLTS, ONE_VOLT};
        
        public static final String[] VOLTAGE_UNITS = new String[] { ONE_MILIVOLT,
                ONE_VOLT };

	public static final String ONE_MICROSECOND = "1 us";
	public static final String TWO_MICROSECONDS = "2 us";
	public static final String FIVE_MICROSECONDS = "5 us";
	public static final String TEN_MICROSECONDS = "10 us";
	public static final String TWENTY_MICROSECONDS = "20 us";
	public static final String FIFTY_MICROSECONDS = "50 us";
	public static final String ONE_HUNDRED_MICROSECONDS = "100 us";
	public static final String TWO_HUNDRED_MICROSECONDS = "200 us";
	public static final String FIVE_HUNDRED_MICROSECONDS = "500 us";
	public static final String ONE_MILISECOND = "1 ms";
	public static final String TWO_MILISECONDS = "2 ms";
	public static final String FIVE_MILISECONDS = "5 ms";
	public static final String TEN_MILISECONDS = "10 ms";
	public static final String TWENTY_MILISECONDS = "20 ms";
	public static final String FIFTY_MILISECONDS = "50 ms";
	public static final String ONE_HUNDRED_MILISECONDS = "100 ms";
	public static final String TWO_HUNDRED_MILISECONDS = "200 ms";
	public static final String FIVE_HUNDRED_MILISECONDS = "500 ms";
	public static final String ONE_SECOND = "1 s";
	
	public static final String[] HORIZONTAL_RANGE_VALUES = new String[] {
		ONE_MICROSECOND, TWO_MICROSECONDS, FIVE_MICROSECONDS,
		TEN_MICROSECONDS, TWENTY_MICROSECONDS, FIFTY_MICROSECONDS,
		ONE_HUNDRED_MICROSECONDS, TWO_HUNDRED_MICROSECONDS, FIVE_HUNDRED_MICROSECONDS,
		ONE_MILISECOND, TWO_MILISECONDS, FIVE_MILISECONDS,
		TEN_MILISECONDS, TWENTY_MILISECONDS, FIFTY_MILISECONDS,
		ONE_HUNDRED_MILISECONDS, TWO_HUNDRED_MILISECONDS, FIVE_HUNDRED_MILISECONDS,
		ONE_SECOND };

	public static final String[] HORIZONTAL_OFFSET_UNITS = new String[] {
		ONE_MICROSECOND, TEN_MICROSECONDS, ONE_HUNDRED_MICROSECONDS,
		ONE_MILISECOND, TEN_MILISECONDS, ONE_HUNDRED_MILISECONDS, ONE_SECOND};

	public static final String SINE_NAME = "Sine";
	public static final String SQUARE_NAME = "Square";
	public static final String TRIANGLE_NAME = "Triangle";
	public static final String RAMP_NAME = "Ramp";
	public static final String RANDOM_NOISE_NAME = "Random noise";
	
	public static final String[] WAVE_TYPES = new String[] {
		SINE_NAME, SQUARE_NAME, TRIANGLE_NAME, RAMP_NAME, RANDOM_NOISE_NAME };
	
	public static final int SINE = 0;
	public static final int SQUARE = 1;
	public static final int TRIANGLE = 2;
	public static final int RAMP = 3;
	public static final int RANDOM_NOISE = 4;
	
	public static final String CHANNEL_A = "Channel A";
	public static final String CHANNEL_B = "Channel B";
	public static final String MATH_CHANNEL = "Math Channel";
	public static final String FILTER_CHANNEL = "Filter Channel";
	public static final String GENERATOR_CHANNEL = "Generator Channel";

	public static final String AUTO_MODE_NAME = "Auto";
	public static final String SINGLE_MODE_NAME = "Single";
	public static final String NORMAL_MODE_NAME = "Normal";

	public static final int AUTO_MODE = 0;
	public static final int SINGLE_MODE = 1;
	public static final int NORMAL_MODE = 2;

	public static final String[] TRIGGER_MODES = new String[] { 
		AUTO_MODE_NAME, SINGLE_MODE_NAME, NORMAL_MODE_NAME };

	public static final String LEVEL_TYPE = "Level";
	public static final String RISING_EDGE_TYPE = "Rising edge";
	public static final String FALLING_EDGE_TYPE = "Falling edge";

	public static final int LEVEL = 0;
	public static final int RISING = 1;
	public static final int FALLING = 2;

	public static final String [] TRIGGER_TYPES = new String[] { 
		LEVEL_TYPE, RISING_EDGE_TYPE, FALLING_EDGE_TYPE };

	public static final int DC = 0;
	public static final int AC = 1;
	
	public static final int GENERATOR_OFF = 0;
	public static final int GENERATOR_ON = 1;
	
	public static final int MODE_8BIT = 0;
	public static final int MODE_12_BIT = 1;

	public static final int ERROR = 1;

	public static final int NORMAL = 0;

	public static final String IP_ADDRESS_PATTERN =
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static enum TAB {CHANNEL_A, CHANNEL_B, MATH_CHANNEL, FILTER_CHANNEL, GENERATOR_CHANNEL};
	
	public static int VERTICAL_GRID_SPACINGS = 10;
	public static int HORIZONTAL_GRID_SPACINGS = 16;
	
	public static double DEFAULT_VERTICAL_RANGE = 0.02;
	public static int DEFAULT_HORIZONTAL_RANGE = 1;
	
	public static final int NUMBER_OF_CHANNELS = 4;
	public static final int A_INDEX = 0;
	public static final int B_INDEX = 1;
	public static final int MATH_INDEX = 2;
	public static final int FILTER_INDEX = 3;
	
	public static final Color A_COLOR = new Color(255, 0, 0); // red
	public static final Color B_COLOR = new Color(0, 0, 255); // blue
	public static final Color MATH_COLOR = new Color(0, 152, 125); // green
	public static final Color FILTER_COLOR = new Color(177, 75, 255); // purple
	public static final Color GENERATOR_COLOR = new Color(120, 75, 75); // brown
	
	public static final Color A_LIGHT_COLOR = new Color(252, 73, 73); // light red
	public static final Color B_LIGHT_COLOR = new Color(65, 114, 248); // light blue
	public static final Color MATH_LIGHT_COLOR = new Color(0, 165, 135); // light green
	public static final Color FILTER_LIGHT_COLOR = new Color(186, 100, 252); // light purple
	public static final Color GENERATOR_LIGHT_COLOR = new Color(140, 115, 115); // light brown
	
	public static final String MAX_VOLTAGE = "Max voltage";
	public static final String MIN_VOLTAGE = "Min voltage";
	public static final String MAX_P2P_VOLTAGE = "Max peak to peak voltage";
	public static final String AVERAGE_VOLTAGE = "Average voltage";
	public static final String STANDARD_DEVIATION_VOLTAGE = "Standard deviation of voltage";
	public static final String FREQUENCY = "Frequency";

	public static final byte CONFIRMATION = (byte) 0xFF;
	public static final byte REQUEST = (byte) 0x00;
	public static final int IGNORE = 0;

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
	
	/**
	 * Return round string with 4 decimal places
	 * @param value
	 * @return
	 */
	public static String roundString(Double value) {
	    DecimalFormat decimalFormat = new DecimalFormat("#.####");
	    return decimalFormat.format(value);
	}
}
