/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.dreger.calculator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Sergey
 */
public class DigitButtonsListener implements ActionListener{
	private ResultLabel resultLabel;

	public DigitButtonsListener(ResultLabel resultLabel) {
		this.resultLabel = resultLabel;
	}
	@Override
	public void actionPerformed(final ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				String labelText = resultLabel.getText();
				String result;
				if(((JButton)(e.getSource())).getText().equals("."))
//					result = (labelText.endsWith(".") || labelText.contains(".")) ? labelText : labelText+((JButton)(e.getSource())).getText(); // 1 точка на строку
//					result = (labelText.endsWith(".")) ? labelText : labelText+((JButton)(e.getSource())).getText(); // запрет двух точек рядом
					result = dotCheck(labelText);
				else
					result = (labelText.equals("0")) ? ((JButton)(e.getSource())).getText() : labelText+((JButton)(e.getSource())).getText();
				resultLabel.setText(result);
			}
		});
	}
	private String dotCheck(String labelText){
		int signIndex = (labelText.indexOf("+"))+(labelText.indexOf("-"))+(labelText.indexOf("×"))+(labelText.indexOf("÷")) + 3;
		if(signIndex > 0){
			if(labelText.indexOf(".") < signIndex && labelText.lastIndexOf(".") == labelText.indexOf(".")) 
				labelText += (signIndex == labelText.length()-1) ? "0." : ".";
		}else{
			if(!labelText.contains(".")) labelText += ".";
		}
		return labelText;
	}
}
