/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.dreger.calculator;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;

/**
 *
 * @author Sergey
 */
public class EvaluateExpression extends KeyAdapter{
	private JLabel result;

	public EvaluateExpression(JLabel result) {
		this.result = result;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && (e.getSource() instanceof InputTextField)){
			String expression = ((InputTextField)e.getSource()).getText();
			final String resultString = evaluate(expression);
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(resultString != null) result.setText(resultString);
					else result.setText("Ошибка в строке ввода");
				}
			});
		}
	}
	public static String evaluate(String expression){
		String result = null;
		if(expression != null && !expression.isEmpty()){
			Pattern pattern = Pattern.compile("^(-?[0-9.,]+)([+-/*xX//×÷]{1})([0-9.,]+)");
			Matcher matcher = pattern.matcher(expression.trim().replace(" ","").replace(",", "."));
			if(matcher.find()){
				try{
//					System.out.println(matcher.group(1) + " ... " + matcher.group(3));
					NumberFormat formatter = NumberFormat.getInstance(Locale.ENGLISH);
					double first = formatter.parse(matcher.group(1)).doubleValue();
					double second = formatter.parse(matcher.group(3)).doubleValue();
//					double first = Double.parseDouble(matcher.group(1));
//					double second = Double.parseDouble(matcher.group(3));
//					System.out.println(" "+ first+" "+" "+" "+second);
					double doubleResult;
					switch(matcher.group(2)){
						case "+": doubleResult = first + second; break;
						case "-": doubleResult = first - second; break;
						case "×": 
						case "x": 
						case "X": 
						case "*": doubleResult = first * second; break;
						case "÷": 
						case "/": doubleResult = first / second; break;
						default : doubleResult = 0;
					}
					if(matcher.group(1).contains(".") || matcher.group(1).contains(",") || matcher.group(3).contains(".") || matcher.group(3).contains(",") || matcher.group(2).contains("÷")){
						result = String.valueOf(doubleResult);
					}else{
						result = String.valueOf(Math.round(doubleResult));
					}
				}catch (ParseException e){System.out.println("Parse Exception: "+e.getMessage());}
			}
		}
	return (result != null && result.endsWith(".0")) ? result.substring(0,result.length()-2) : result;
	}
}
