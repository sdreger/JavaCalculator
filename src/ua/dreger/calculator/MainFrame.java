/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.dreger.calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Sergey
 */
public class MainFrame extends JFrame{
	private String[] buttonLabel = {"MRC","M-","M+","÷","7","8","9","×","4","5","6","-","1","2","3","+","0",".","C","="};
	private JButton[] buttons = new JButton[20];
	private InputTextField inputExpression;
	private ResultLabel resultLabel;
//	private String titleString = "Калькулятор v1.0";
	private String titleString = "Calc v1.0";

	public String getTitleString() {
		return titleString;
	}
	
	public JLabel getResult() {
		return resultLabel;
	}

	public MainFrame() throws HeadlessException {
		
		setTitle(titleString);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		resultLabel = new ResultLabel();
		resultLabel.setText("0");
		getContentPane().add(resultLabel, BorderLayout.NORTH);
		
		inputExpression = new InputTextField("Введите выражение, и нажмите Enter...");
		inputExpression.addKeyListener(new EvaluateExpression(resultLabel));
		getContentPane().add(inputExpression, BorderLayout.SOUTH);
		
		JPanel mainPanel = new JPanel(new GridLayout(5, 4, 5, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		createButtons(mainPanel);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public final void createButtons(JPanel panel){
		ActionListener digitButtonListener = new DigitButtonsListener(resultLabel);
		ActionListener controlButtonListener = new ControlButtonsLister(resultLabel, inputExpression, this);
		KeyboardLstener keyboardLstener = new KeyboardLstener(buttons, resultLabel);
//		"MRC","M-","M+","/","7","8","9","X","4","5","6","-","1","2","3","+","0",".","C","="
		for (int i = 0; i < buttonLabel.length; i++) {
			buttons[i] = new JButton(buttonLabel[i]);
			if(i == 0 || i == 1 || i == 2 || i == 3 || i == 7 || i == 11 || i == 15 || i == 18 || i == 19){
				buttons[i].addActionListener(controlButtonListener);
			}else{
				buttons[i].addActionListener(digitButtonListener);
			}
			buttons[i].setPreferredSize(new Dimension(65, 50));
			panel.add(buttons[i]);
			buttons[i].addKeyListener(keyboardLstener);
		}
	}
}
