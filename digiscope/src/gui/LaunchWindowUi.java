package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

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
	protected JLabel statusLabel;
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
		statusLabel = new JLabel();
		secondRowPanel_ = new JPanel();
		ipAddressLabel_ = new JLabel();
		ipAddressTextField = new JTextField();
		connectButton = new JButton("Connect");
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(Constant.APPLICATION_TITLE);
		setResizable(false);
		setSize(new Dimension(480, 205));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		
		// mainPanel_ contains firstRowPanel_, statusLabel, secondRowPanel
		mainPanel_.setPreferredSize(new Dimension(470, 170));
		mainPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//mainPanel_.setBorder(BorderFactory.createTitledBorder(""));
		
		// firstRowPanel_ contains ipAddressLabel_, ipAddressTextField
		firstRowPanel_.setPreferredSize(new Dimension(460, 55));
		firstRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

		ipAddressLabel_.setText("IP Address: ");
		ipAddressLabel_.setFont(new Font("Lucida Grande", 1, 16));
		firstRowPanel_.add(ipAddressLabel_);

		ipAddressTextField.setPreferredSize(new Dimension(300, 34));
		ipAddressTextField.setFont(new Font("Lucida Grande", 0, 15));
		firstRowPanel_.add(ipAddressTextField);
		mainPanel_.add(firstRowPanel_);

		statusLabel.setForeground(Color.RED);
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setPreferredSize(new Dimension(460, 20));
		mainPanel_.add(statusLabel);
		
		// secondRowPanel contains connectButton
		secondRowPanel_.setPreferredSize(new Dimension(460, 75));
		secondRowPanel_.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

		connectButton.setPreferredSize(new Dimension(140, 40));
		connectButton.setFont(new Font("Lucida Grande", 1, 16));
		secondRowPanel_.add(connectButton);
		mainPanel_.add(secondRowPanel_);
		
		getContentPane().add(mainPanel_);

	}
}
