/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.dreger.calculator;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Sergey
 */
public class ResultLabel extends JLabel{

	public ResultLabel() {
		Font font = new Font("Momospase", Font.BOLD|Font.ITALIC, 24);
		setFont(font);
		setHorizontalAlignment(JLabel.RIGHT);
		setBorder(BorderFactory.createLoweredBevelBorder());
	}
}
