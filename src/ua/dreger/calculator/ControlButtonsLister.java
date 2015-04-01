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
public class ControlButtonsLister implements ActionListener{
/*****************************************************************************/
	private ResultLabel resultLabel;
	private InputTextField inputField;
	private MainFrame mainFrame;
	private boolean signIsSet = false;
	private boolean mrcPressed = false;
	private double memory = 0;

/*****************************************************************************/
	public ControlButtonsLister(ResultLabel resultLabel, InputTextField inputField, MainFrame mainFrame) {
		this.resultLabel = resultLabel;
		this.inputField = inputField;
		this.mainFrame = mainFrame;
	}
/*****************************************************************************/	
	@Override
	public void actionPerformed(final ActionEvent e) {
		String action = ((JButton)(e.getSource())).getText();
		String resultLabelString = resultLabel.getText();
		char lastSymbol = resultLabelString.charAt(resultLabelString.length()-1);
		//------------ нажатие MRC ----------------
		if(action.equals("MRC")){
			if(mrcPressed){
				memory = 0;
				setMemTitle(memory);
				return;
			}else{
				mrcPressed = true;
				String memStr = (String.valueOf(memory)).endsWith(".0") ? (String.valueOf(memory)).substring(0, (String.valueOf(memory)).length() - 2) : String.valueOf(memory) ;
				if(resultLabelString.equals("0"))
					updateUI(memStr, false);
				else	
					updateUI(resultLabelString + memStr, false);
				setMemTitle(memory);
				return;
			}
		}
		mrcPressed = false;
		//------------ нажатие M+ M- ----------------
		if(action.equals("M+") || action.equals("M-")){
			if(resultLabelString.contains("+") || resultLabelString.contains("-") || resultLabelString.contains("×") || resultLabelString.contains("÷")){
				setMemTitle(memory);
				return;
			}else{
				if(action.equals("M+")) memory += Double.parseDouble(resultLabelString);
				else memory -= Double.parseDouble(resultLabelString);
			}
			setMemTitle(memory);
			return;
		}
		//------------ нажатие C ----------------
		if(action.equals("C")) { //нажата кнопка С
			clear();
			return;
		}
		//------------ нажатие +-*/ ----------------
		if(signIsSet){ // если знак уже установлен
			if(lastSymbol == '+' || lastSymbol == '-' || lastSymbol == '×' || lastSymbol == '÷'){
//				System.out.println("SignSet + signLast");
				if(!action.equals("=")) // если нажата одна из кнопок +-*/
					updateUI(resultLabelString.substring(0, resultLabelString.length()-1) + action, false);
				else{ // если нажата кнопка равно, при вводе типа 3+=, выполнить 3+3 и вывести 9
					signIsSet = false;
					updateUI(EvaluateExpression.evaluate(resultLabelString + resultLabelString.substring(0, resultLabelString.length()-1)), false);
				}
			}else{ // стандартное выполнение операции (числ (знак) число)
//				System.out.println("SignSet + NotSignLast");
				updateUI(EvaluateExpression.evaluate(resultLabelString), false);
				signIsSet = false;
			}
		}else{ // если знак еще не установлен, введено, только первое число
//				System.out.println("SignNotSet + NotSignLast");
				signIsSet = true;
				if(action.equals("=")) signIsSet=false;
				else updateUI(resultLabelString + action, false);
		}
	}
/*****************************************************************************/
	private void setMemTitle(final double memory){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.setTitle(mainFrame.getTitleString()+"  Memory: "+memory);
			}
		});
	}
/*****************************************************************************/
	private void clear(){
		signIsSet = false;
		updateUI("0", true);
	}
/*****************************************************************************/
	private void updateUI(final String labelText, final boolean updateInputField){
			EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(updateInputField) inputField.setHintText();
				resultLabel.setText(labelText);
			}
		});
	}
}
