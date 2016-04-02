package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import data.Constant;

/**
 *
 * @author ToanHo
 */
public class LaunchWindowUi extends JFrame {

	private JPanel mainPanel_;
	private JPanel firstRowPanel_;
	private JLabel statusLabel_;
	private JPanel secondRowPanel_;
	private JLabel ipAddressLabel_;
	protected JTextField ipAddressTextField;
	protected JButton connectButton;
	
	public LaunchWindowUi() {
		initializeComponents();
		validate();
	}

	private void initializeComponents() {
		
		mainPanel_ = new JPanel();
		firstRowPanel_ = new JPanel();
		statusLabel_ = new JLabel();
		secondRowPanel_ = new JPanel();
		ipAddressLabel_ = new JLabel();
		ipAddressTextField = new JTextField();
		connectButton = new JButton("Connect");
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(Constant.APPLICATION_TITLE);
		setResizable(false);
		setSize(new Dimension(480, 200));
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		
		// mainPanel_ contains firstRowPanel_, statusLabel, secondRowPanel
		mainPanel_.setPreferredSize(new Dimension(470, 170));
		mainPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		mainPanel_.setBorder(BorderFactory.createTitledBorder(""));
		
		// firstRowPanel_ contains ipAddressLabel_, ipAddressTextField
		firstRowPanel_.setPreferredSize(new Dimension(460, 50));
		firstRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 22));

		ipAddressLabel_.setText("IP Address: ");
		ipAddressLabel_.setFont(new Font("Lucida Grande", 1, 16));
		firstRowPanel_.add(ipAddressLabel_);

		ipAddressTextField.setPreferredSize(new Dimension(300, 34));
		ipAddressTextField.setFont(new Font("Lucida Grande", 0, 15));
		firstRowPanel_.add(ipAddressTextField);
		mainPanel_.add(firstRowPanel_);

		statusLabel_.setForeground(Color.RED);
		statusLabel_.setFont(new Font("Lucida Grande", 0, 14));
		statusLabel_.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel_.setPreferredSize(new Dimension(460, 20));
		mainPanel_.add(statusLabel_);
		
		// secondRowPanel contains connectButton
		secondRowPanel_.setPreferredSize(new Dimension(460, 75));
		secondRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

		connectButton.setPreferredSize(new Dimension(140, 40));
		connectButton.setFont(new Font("Lucida Grande", 1, 18));
		secondRowPanel_.add(connectButton);
		mainPanel_.add(secondRowPanel_);
		
		getContentPane().add(mainPanel_,BorderLayout.CENTER);
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
