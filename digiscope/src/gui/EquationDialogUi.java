package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jfree.data.xy.XYSeries;

import core.MainWindow;
import data.Constant;

/**
 *
 * @author ToanHo
 */
public class EquationDialogUi extends JDialog {

	private static final long serialVersionUID = 1L;
	protected JButton[] buttons;
    protected JButton cancelButton;
    protected JTextField equationTextField;
    private JPanel firstRowPanel_;
    private JPanel fourthRowPanel_;
    protected JButton okButton;
    private JPanel secondRowPanel_;
    private JLabel statusLabel_;
    private JPanel thirdRowPanel_;

    /**
     * Creates new form EquationDialogUi
     */
    public EquationDialogUi(Frame parent, boolean modal) {
        super(parent, modal);
        initializeComponents();
        if(parent instanceof MainWindow){
            MainWindow mainWindow = (MainWindow) parent;
            Map<String, XYSeries> xYSeries = mainWindow.getRawXYSeries();
            if(xYSeries.containsKey(Constant.CHANNEL_A)) {
            	buttons[0].setEnabled(true); // button A
            }
            if(xYSeries.containsKey(Constant.CHANNEL_B)) {
            	buttons[1].setEnabled(true); // button B
            }
            if(xYSeries.containsKey(Constant.FILTER_CHANNEL)) {
            	buttons[2].setEnabled(true); // button F
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initializeComponents() {

    	String[] labelStrings = new String[] {
    			"A", "B", "F", "Delete",
    			"7", "8", "9", "*", "/",
    			"4", "5", "6", "+", "-",
    			"1", "2", "3", "^", "\u03C0", // "\u03C0" is Pi
    			"e", "0", ".", "(", ")"
    	};
    	buttons = new JButton[labelStrings.length];
        firstRowPanel_ = new JPanel();
        equationTextField = new JTextField();
        secondRowPanel_ = new JPanel();
        statusLabel_ = new JLabel();
        thirdRowPanel_ = new JPanel();
        fourthRowPanel_ = new JPanel();
        cancelButton = new JButton();
        okButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Equation");
        getContentPane().setLayout(new FlowLayout());

        firstRowPanel_.setPreferredSize(new Dimension(490, 45));
        firstRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 8));

        equationTextField.setEnabled(false);
        equationTextField.setPreferredSize(new Dimension(480, 33));
		equationTextField.setFont(new Font("Courier New", 0, 18));
        firstRowPanel_.add(equationTextField);

        getContentPane().add(firstRowPanel_);

        secondRowPanel_.setPreferredSize(new Dimension(490, 25));
        secondRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        statusLabel_.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel_.setPreferredSize(new Dimension(480, 14));
		statusLabel_.setFont(new Font("Tahoma", 0, 14));
        secondRowPanel_.add(statusLabel_);

        getContentPane().add(secondRowPanel_);

        thirdRowPanel_.setPreferredSize(new Dimension(335, 210));
        thirdRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        for(int i = 0; i < labelStrings.length; i++) {
        	buttons[i] = new JButton(labelStrings[i]);
        	if(i == 3) { // button Delete is bigger
        		buttons[i].setPreferredSize(new Dimension(120, 30));
        	} else {
        		buttons[i].setPreferredSize(new Dimension(55, 30));
        	}
        	thirdRowPanel_.add(buttons[i]);
        }
        
        /* By default button A, B, F is disabled */
        buttons[0].setEnabled(false); // button A
        buttons[1].setEnabled(false); // button B
        buttons[2].setEnabled(false); // button F
		buttons[18].setFont(new Font("Courier New", 0, 16)); // button pi

        getContentPane().add(thirdRowPanel_);

        fourthRowPanel_.setPreferredSize(new Dimension(490, 45));
        fourthRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        cancelButton.setText("Cancel");
        cancelButton.setPreferredSize(new Dimension(80, 30));
        fourthRowPanel_.add(cancelButton);

        okButton.setText("Ok");
        okButton.setPreferredSize(new Dimension(80, 30));
        fourthRowPanel_.add(okButton);

        getContentPane().add(fourthRowPanel_);

        setSize(new Dimension(520, 400));
        setLocationRelativeTo(null);
    }

    /**
	 * Set application status.
	 * @param message
	 * @param statusType There are 2 types: Constant.ERROR or Constant.NORMAL
	 */
	public void setStatus(String message, int statusType) {
		if(statusType == Constant.ERROR) {
			statusLabel_.setForeground(Color.RED);
		} else if(statusType == Constant.NORMAL){
			statusLabel_.setForeground(Color.BLUE);
		}
		statusLabel_.setText(message);
	}

}
