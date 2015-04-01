/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.dreger.calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 *
 * @author Sergey
 */
public final class InputTextField extends JTextField{
	private Font bold = new Font("Arial", Font.BOLD, 16);
	private Font italic = new Font("Arial", Font.ITALIC, 16);
		
	public InputTextField(String label) {
		setHintText();
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				removeHintText();
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(getText().isEmpty())
					setHintText();
			}
		});
	}
	public void setHintText(){
		setFont(italic);
		setForeground(Color.GRAY);
		setText("Введите выражение, и нажмите Enter...");
	}
	public void removeHintText(){
		setFont(bold);
		setForeground(Color.BLACK);
		setText("");
	}
}
