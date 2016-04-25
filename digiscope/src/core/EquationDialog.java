package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import org.jfree.data.xy.XYSeries;

import data.Constant;
import gui.EquationDialogUi;

/**
 *
 * @author ToanHo
 */
public class EquationDialog extends EquationDialogUi implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Evaluator evaluator;

	public EquationDialog(MainWindow mainWindow) {
		super(mainWindow);
		evaluator = new Evaluator();
		addListionersToComponents();
		Map<String, XYSeries> xYSeries = this.mainWindow.getRawXYSeries();
		if (xYSeries.containsKey(Constant.CHANNEL_A)) {
			buttons[0].setEnabled(true); // button A
			evaluator.setVariableValue("A", 0.0);
		}
		if (xYSeries.containsKey(Constant.CHANNEL_B)) {
			buttons[1].setEnabled(true); // button B
			evaluator.setVariableValue("B", 0.0);
		}
		if (xYSeries.containsKey(Constant.FILTER_CHANNEL)) {
			buttons[2].setEnabled(true); // button F
			evaluator.setVariableValue("F", 0.0);
		}
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
			try {
				Double result = evaluator.evaluate(expression, evaluator.getVariables());
				if(result != Double.POSITIVE_INFINITY && 
						result != Double.NEGATIVE_INFINITY) {
					mainWindow.setExpressionForMathChannel(expression);
					this.dispose();
				} else {
					setStatus("Infinity number", Constant.ERROR);
				}
			} catch(IllegalArgumentException iae) {
				if(iae.getMessage() == null) {
					setStatus("Invalid expression", Constant.ERROR);
				} else {
					setStatus(iae.getMessage(), Constant.ERROR);
				}
			}
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
		if(event.getActionCommand().equals("Delete")) {
			if(currentString.length() > 0) {
				expressionTextArea.setText(currentString.substring(0,
						currentString.length() - 1));
			}
		} else {
			expressionTextArea.setText(currentString + event.getActionCommand());
		}
		setStatus("", Constant.NORMAL);
	}
}
