package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.EquationDialogUi;

/**
 *
 * @author ToanHo
 */
public class EquationDialog extends EquationDialogUi implements ActionListener{
	private static final long serialVersionUID = 1L;

	public EquationDialog(JFrame parent) {
		super(parent, true);
		addListionersToComponents();
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
	}

	private void cancelButtonActionPerformed(ActionEvent event) {
		// TODO
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO
		String currentString = equationTextField.getText();
		if(event.getActionCommand().equals("Delete")) {
			if(currentString.length() > 0) {
				equationTextField.setText(currentString.substring(0,
						currentString.length() - 1));
			}
		} else {
			equationTextField.setText(currentString + event.getActionCommand());
		}
	}
}
