package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;

import data.Constant;
import gui.LaunchWindowUi;

/**
 *
 * @author ToanHo
 */
public class LaunchWindow extends LaunchWindowUi {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow_;

	/**
	 * Constructor
	 */
	public LaunchWindow() {
		super();
		addListenersToComponents();
		setStatus("To connect, please enter the IP address!", Constant.NORMAL);
		ipAddressTextField.setText(Constant.DEFAULT_IP_ADDRESS);
	}

	private void addListenersToComponents() {
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				connectButtonActionPerformed(event);
			}
		});
		
		ipAddressTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyEvent) {
				ipAddressTextFieldKeyTyped(keyEvent);
			}
		});
	}

	/**
	 * Connect to the hardware when connect button is clicked
	 * @param event
	 */
	private void connectButtonActionPerformed(ActionEvent event) {
		connect(ipAddressTextField.getText());
	}

	private void ipAddressTextFieldKeyTyped(KeyEvent keyEvent) {
		if (ipAddressTextField.isEditable()) {
			char keyChar = keyEvent.getKeyChar();
			if (keyChar == KeyEvent.VK_ENTER) {
				connect(ipAddressTextField.getText());
			} else if ((keyChar < '0' || keyChar > '9') && (keyChar != '.') 
					&& (keyChar != KeyEvent.VK_BACK_SPACE)) {
				keyEvent.consume();
			} else {
				setStatus("", Constant.NORMAL);
			}
		} else {
			keyEvent.consume();
		}
	}

	/**
	 * Connect to the hardware
	 * @param ipAddress
	 */
	private void connect(String ipAddress) {
		setEnabled(false);
		if(ipAddress.trim().equals("")) {
			setStatus("Please enter the IP address!", Constant.ERROR);
			setEnabled(true);
		} else {
			if (validateIpAddress(ipAddress)) {
				this.setStatus("Connecting to the device...", Constant.NORMAL);
				LaunchWindow that = this;
				SwingWorker<String, Void> swingWorker = new SwingWorker<String, Void>() {
					Socket clientSocket = null;
					@Override
					protected String doInBackground() throws Exception {
						try {
							clientSocket = new Socket();
							clientSocket.connect(new InetSocketAddress(ipAddress, Constant.PORT_NUMBER), 3000);
							setMainWindow(new MainWindow(that, clientSocket));
							getMainWindow().setVisible(true);
						} catch (UnknownHostException e) {
							clientSocket = null;
							setStatus(e.getMessage(), Constant.ERROR);
							setEnabled(true);
						} catch (IOException e) {
							clientSocket = null;
							setStatus(e.getMessage(), Constant.ERROR);
							setEnabled(true);
						}
						return null;
					}

					@Override
					protected void done() {
						if(clientSocket != null) {
							setEnabled(true);
							setVisible(false);
						}
					}
				};
				swingWorker.execute();
			} else {
				setStatus("The IP address is invalid!", Constant.ERROR);
				setEnabled(true);
			}
		}
	}

	public MainWindow getMainWindow() {
		return mainWindow_;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow_ = mainWindow;
	}
	
	private boolean validateIpAddress(String ipAddress) {
		Pattern pattern = Pattern.compile(Constant.IP_ADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
	
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		ipAddressTextField.setEditable(enabled);
	}
}
