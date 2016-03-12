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
 * 's {@link ResourceBundle} {@link Locale} to sr_RS_cyrilic.
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
public class ActionSerbianCyrilic extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionSerbianCyrilic} object.
	 */
	private static ActionSerbianCyrilic instance = null;

	/**
	 * Returns the only instance of {@link ActionSerbianCyrilic} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionSerbianCyrilic}
	 */
	public static ActionSerbianCyrilic getInstance() {
		if (instance == null) {
			instance = new ActionSerbianCyrilic();
		}
		return instance;
	}

	/**
	 * {@link ActionSerbianCyrilic} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon.
	 * 
	 */
	private ActionSerbianCyrilic() {
		putValue(NAME, Application.getResourceBundle().getString("SerbianCyrilic"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/serbian.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("SerbianCyrilicDesc"));
	}

	/**
	 * Sets {@link Application}'s {@link ResourceBundle} {@link Locale} to sr_RS
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
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("SerbianCyrilicStart"));
		Application.updateLanguage(new Locale("sr", "RS"));
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("SerbianCyrilicFinish"));
	}

}
