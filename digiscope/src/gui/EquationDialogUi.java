package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

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
	protected JTextArea expressionTextArea;
	private JPanel firstRowPanel_;
	private JPanel fourthRowPanel_;
	protected JButton okButton;
	private JScrollPane scrollPane;
	private JPanel secondRowPanel_;
	private JLabel statusLabel_;
	private JPanel thirdRowPanel_;
	protected MainWindow mainWindow;

	/**
	 * Creates new form EquationDialogUi
	 */
	public EquationDialogUi(MainWindow mainWindow) {
		super(mainWindow, true);
		initializeComponents();
		this.mainWindow = mainWindow;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initializeComponents() {

		String[] labelStrings = new String[] { "A", "B", "F", "Delete", "7", "8", "9", "*", "/", "4", "5", "6", "+",
				"-", "1", "2", "3", "^", "\u03C0", // "\u03C0" is Pi
				"e", "0", ".", "(", ")" };
		buttons = new JButton[labelStrings.length];
		firstRowPanel_ = new JPanel();
		expressionTextArea = new JTextArea();
		scrollPane = new JScrollPane();
		secondRowPanel_ = new JPanel();
		statusLabel_ = new JLabel();
		thirdRowPanel_ = new JPanel();
		fourthRowPanel_ = new JPanel();
		cancelButton = new JButton();
		okButton = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("New Expression");
		getContentPane().setLayout(new FlowLayout());

		firstRowPanel_.setPreferredSize(new Dimension(490, 55));
		firstRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 8));

		expressionTextArea.setEditable(false);
		expressionTextArea.setFont(new Font("Monospaced", 0, 18));

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(480, 40));
		scrollPane.setViewportView(expressionTextArea);

		firstRowPanel_.add(scrollPane);

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

		for (int i = 0; i < labelStrings.length; i++) {
			buttons[i] = new JButton(labelStrings[i]);
			if (i == 3) { // button Delete is bigger
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
		buttons[18].setFont(new Font("Monospaced", 0, 16)); // button pi

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
	 * 
	 * @param message
	 * @param statusType
	 *            There are 2 types: Constant.ERROR or Constant.NORMAL
	 */
	public void setStatus(String message, int statusType) {
		if (statusType == Constant.ERROR) {
			statusLabel_.setForeground(Color.RED);
		} else if (statusType == Constant.NORMAL) {
			statusLabel_.setForeground(Color.BLUE);
		}
		statusLabel_.setText(message);
	}

}
