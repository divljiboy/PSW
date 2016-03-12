package gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import application.Application;

/**
 * Class representing the status bar in the application.
 * It is used to show messages about currently performed action in the application.
 * 
 * 
 * @author Ivan Divljak
 * @author Borko Arsovic
 * @author Milan Radeta
 * @author Isidora Skulec
 */

public class StatusBar extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel message;
	
	/**
	 * Constructor of {@link StatusBar} class.
	 * Defines the look of the status bar and writes the initial message. 
	 */
	public StatusBar() {
		super();
		GridLayout layout=new GridLayout(1,1);
		setLayout(layout);
		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		message = new JLabel(Application.getResourceBundle().getString("Welcome"));
		message.setBorder(loweredBevel);
		add(message);
	}

	/**
	 * Returns the message shown in the {@link StatusBar}.
	 * @return {@link JLabel}
	 */
	public JLabel getMessage() {
		return message;
	}

	/**
	 * Sets the message {@link JLabel} shown in the {@link StatusBar}.
	 * @param message
	 */
	public void setMessage(String message) {
		this.message.setText(message);
	}

}
