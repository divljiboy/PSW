package actions.languages;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import application.Application;
import gui.MainFrame;
import gui.StatusBar;

/**
 * Singleton class that extends {@link AbstractAction}. Sets {@link Application}
 * 's {@link ResourceBundle} {@link Locale} to en_US.
 * 
 * @author Ivan Divljak
 * @author Milan Radeta
 * @author Borko Arsović
 * @see AbstractAction
 * @see Application
 * @see ResourceBundle
 * @see Locale
 *
 */
public class ActionEnglishUS extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionEnglishUS} object.
	 */
	private static ActionEnglishUS instance = null;

	/**
	 * Returns the only instance of {@link ActionEnglishUS} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionEnglishUS}
	 */
	public static ActionEnglishUS getInstance() {
		if (instance == null) {
			instance = new ActionEnglishUS();
		}
		return instance;
	}

	/**
	 * {@link ActionEnglishUS} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon.
	 * 
	 */
	private ActionEnglishUS() {
		putValue(NAME, Application.getResourceBundle().getString("EnglishUS"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/english.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("EnglishUSDesc"));
	}

	/**
	 * Sets {@link Application}'s {@link ResourceBundle} {@link Locale} to en_US
	 * and updates {@link MainFrame}'s {@link StatusBar}.
	 * 
	 * @see Application
	 * @see ResourceBundle
	 * @see Locale
	 * @see MainFrame
	 * @see StatusBar
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("EnglishUSStart"));
		Application.updateLanguage(new Locale("en", "US"));
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("EnglishUSFinish"));
	}

}
