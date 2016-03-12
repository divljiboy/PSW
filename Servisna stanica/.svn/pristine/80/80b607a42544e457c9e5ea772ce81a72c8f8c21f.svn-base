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
 * 's {@link ResourceBundle} {@link Locale} to sr_RS_latin.
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
public class ActionSerbianLatin extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionSerbianLatin} object.
	 */
	private static ActionSerbianLatin instance = null;

	/**
	 * Returns the only instance of {@link ActionSerbianLatin} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionSerbianLatin}
	 */
	public static ActionSerbianLatin getInstance() {
		if (instance == null) {
			instance = new ActionSerbianLatin();
		}
		return instance;
	}

	/**
	 * {@link ActionSerbianLatin} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon.
	 * 
	 */
	private ActionSerbianLatin() {
		putValue(NAME, Application.getResourceBundle().getString("SerbianLatin"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/serbian.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("SerbianLatinDesc"));
	}

	/**
	 * Sets {@link Application}'s {@link ResourceBundle} {@link Locale} to sr_RS_#Latn
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
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("SerbianLatinStart"));
		Application.updateLanguage(new Locale.Builder().setLanguage("sr").setRegion("RS").setScript("Latn").build());
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("SerbianLatinFinish"));
	}

}
