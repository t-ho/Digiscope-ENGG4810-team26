package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import data.Constant;
import gui.ExpressionDialogUi;

/**
 *
 * @author ToanHo
 */
public class ExpressionDialog extends ExpressionDialogUi implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Evaluator evaluator;

	public ExpressionDialog(MainWindow mainWindow) {
		super(mainWindow);
		evaluator = new Evaluator();
		addListionersToComponents();
		ArrayList<String> availableChannels = this.mainWindow.getAvailableDerivedChannelForMath();
		if (availableChannels.contains(Constant.CHANNEL_A)) {
			buttons[0].setEnabled(true); // button A
			evaluator.setVariableValue("A", 0.0);
		}
		if (availableChannels.contains(Constant.CHANNEL_B)) {
			buttons[1].setEnabled(true); // button B
			evaluator.setVariableValue("B", 0.0);
		}
		if (availableChannels.contains(Constant.FILTER_CHANNEL)) {
			buttons[2].setEnabled(true); // button F
			evaluator.setVariableValue("F", 0.0);
		}
	}
	
	/**
	 * Constructor for editing expression
	 * @param mainWindow
	 * @param expression
	 */
	public ExpressionDialog(MainWindow mainWindow, String expression) {
		this(mainWindow);
		expressionTextArea.setText(expression);
	}

	private void addListionersToComponents() {
		// TODO
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(this);
		}
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				cancelButtonActionPerformed(event);
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				okButtonActionPerformed(event);
			}
		});
	}

	private void okButtonActionPerformed(ActionEvent event) {
		// TODO
		String expression = expressionTextArea.getText().trim();
		if(!expression.equals("")) {
			if (expression.contains("A") || expression.contains("B") || expression.contains("F")) {
				try {
					boolean isValid = mainWindow.calculateMathChannel(expression);
					if (isValid == true) {
						mainWindow.setExpressionForMathChannel(expression);
						mainWindow.setEnabledExpressionControls(true);
						mainWindow.setEnabledMathChannelControls(true);
						mainWindow.updateInputChannelComboBox();
						this.dispose();
					} else {
						setStatus("Infinity number", Constant.ERROR);
					}
				} catch (IllegalArgumentException iae) {
					if (iae.getMessage() == null) {
						setStatus("Invalid expression", Constant.ERROR);
					} else {
						setStatus(iae.getMessage(), Constant.ERROR);
					}
			}
			} else {
				setStatus("The expression does not have any derived channels", Constant.ERROR);
			}
		} else {
			setStatus("Do not leave the expression empty!" , Constant.ERROR);
		}
	}

	private void cancelButtonActionPerformed(ActionEvent event) {
		// TODO
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO
		String currentString = expressionTextArea.getText();
		if(event.getActionCommand().equals("Del")) {
			if(currentString.length() > 0) {
				expressionTextArea.setText(currentString.substring(0,
						currentString.length() - 1));
			}
		} else if(event.getActionCommand().equals("AC")) {
			expressionTextArea.setText("");
		} else {
			expressionTextArea.setText(currentString + event.getActionCommand());
		}
		setStatus("", Constant.NORMAL);
	}
}
