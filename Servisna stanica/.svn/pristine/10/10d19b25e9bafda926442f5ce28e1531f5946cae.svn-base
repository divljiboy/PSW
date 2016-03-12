package gui.menus;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import actions.help.ActionAbout;
import actions.help.ActionUserManual;
import application.Application;
/**
 * Extension of {@link JMenu} class representing the help menu.
 * It contains the manual {@link ActionUserManual} and about {@link ActionAbout} actions.
 * @author Milan Radeta
 * 
 */

public class MenuHelp extends JMenu {
	private static final long serialVersionUID = 1L;
	
	public static final String NAME = "Help";

	/**
	 * Constructor of {@link MenuHelp} class.
	 * Creates a menu with necessary actions.
	 */
	public MenuHelp() {
		super(Application.getResourceBundle().getString(NAME));
		setMnemonic(KeyEvent.VK_H);
		add(ActionUserManual.getInstance());
		add(ActionAbout.getInstance());
		
	}
}
