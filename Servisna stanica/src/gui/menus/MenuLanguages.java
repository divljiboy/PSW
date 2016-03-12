package gui.menus;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

import actions.languages.ActionEnglishUS;
import actions.languages.ActionSerbianCyrilic;
import actions.languages.ActionSerbianLatin;
import application.Application;

/**
 * Extension of {@link JMenu} class representing the languages menu.
 * User can switch the language of the whole application.
 * 
 * @author Milan Radeta
 * 
 */
public class MenuLanguages extends JMenu {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "Language";

	/**
	 * Constructor of {@link MenuLanguages} class.
	 * Creates a menu with necessary actions.
	 */
	public MenuLanguages() {
		super(Application.getResourceBundle().getString(NAME));
		setMnemonic(KeyEvent.VK_L);
		setIcon(new ImageIcon(Application.class.getResource("/icons/language.png")));
		add(ActionSerbianLatin.getInstance());
		add(ActionSerbianCyrilic.getInstance());
		add(ActionEnglishUS.getInstance());
	}

}
