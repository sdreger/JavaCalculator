/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.dreger.calculator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

/**
 *
 * @author Sergey
 */
public class KeyboardLstener extends KeyAdapter{
	private JButton[] buttons;
	private ResultLabel resultLabel;

	public KeyboardLstener(JButton[] buttons, ResultLabel resultLabel) {
		this.buttons = buttons;
		this.resultLabel = resultLabel;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
 		switch(e.getKeyCode()){
			case KeyEvent.VK_ENTER: buttons[19].doClick(); break;
			case KeyEvent.VK_BACK_SPACE: 
				if(resultLabel.getText().length() > 1){
					resultLabel.setText(resultLabel.getText().substring(0, resultLabel.getText().length()-1));
				 }else{
					resultLabel.setText("0");
				 }
				 break;
			case KeyEvent.VK_DELETE: buttons[18].doClick(); break;
			case KeyEvent.VK_DECIMAL:
			case KeyEvent.VK_PERIOD: buttons[17].doClick(); break;
			case KeyEvent.VK_NUMPAD0: 
			case KeyEvent.VK_0: buttons[16].doClick(); break;
			case KeyEvent.VK_ADD: buttons[15].doClick(); break;
			case KeyEvent.VK_EQUALS: if(e.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) buttons[15].doClick(); else buttons[19].doClick(); break;
			case KeyEvent.VK_NUMPAD3:
			case KeyEvent.VK_3: buttons[14].doClick(); break;
			case KeyEvent.VK_NUMPAD2:
			case KeyEvent.VK_2: buttons[13].doClick(); break;
			case KeyEvent.VK_NUMPAD1:
			case KeyEvent.VK_1: buttons[12].doClick(); break;
			case KeyEvent.VK_SUBTRACT: 
			case KeyEvent.VK_MINUS: buttons[11].doClick(); break;
			case KeyEvent.VK_NUMPAD6:
			case KeyEvent.VK_6: buttons[10].doClick(); break;
			case KeyEvent.VK_NUMPAD5:
			case KeyEvent.VK_5: buttons[9].doClick(); break;
			case KeyEvent.VK_NUMPAD4:
			case KeyEvent.VK_4: buttons[8].doClick(); break;
			case KeyEvent.VK_MULTIPLY: buttons[7].doClick();break;
			case KeyEvent.VK_NUMPAD9:
			case KeyEvent.VK_9: buttons[6].doClick(); break;
			case KeyEvent.VK_NUMPAD8:
			case KeyEvent.VK_8:  if(e.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) buttons[7].doClick(); else buttons[5].doClick(); break;
			case KeyEvent.VK_NUMPAD7:
			case KeyEvent.VK_7: buttons[4].doClick(); break;
			case KeyEvent.VK_DIVIDE: 
			case KeyEvent.VK_SLASH: buttons[3].doClick(); break;
		}
	}
}
