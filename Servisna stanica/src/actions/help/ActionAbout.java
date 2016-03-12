package actions.help;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import application.Application;
import gui.MainFrame;
import gui.StatusBar;
import gui.dialog.DialogAbout;

/**
 * Singleton class that extends {@link AbstractAction}. When performed, it
 * opens the {@link DialogAbout} window.
 * 
 * @author Milan Radeta
 * @author Ivan Divljak
 * @author Isidora Škulec
 * @author Borko Arsović
 * @see AbstractAction
 * @see DialogAbout
 *
 */
public class ActionAbout extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionAbout} object.
	 */
	private static ActionAbout instance = null;

	/**
	 * Returns the only instance of {@link ActionAbout} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionAbout}
	 */
	public static ActionAbout getInstance() {
		if (instance == null) {
			instance = new ActionAbout();
		}
		return instance;
	}

	/**
	 * {@link ActionAbout} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (F2).
	 * 
	 */
	private ActionAbout() {
		putValue(NAME, Application.getResourceBundle().getString("About"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/authors.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("AboutDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
	}

	/**
	 * Opens {@link DialogAbout} and updates {@link MainFrame}'s {@link StatusBar}.
	 * @see DialogAbout
	 * @see StatusBar
	 * @see StatusBar
	 */
	public void actionPerformed(ActionEvent e) {
		StatusBar statusBar = MainFrame.getInstance().getStatusBar();
		statusBar.setMessage(Application.getResourceBundle().getString("AboutStart"));
		DialogAbout dialog = new DialogAbout();
		dialog.setVisible(true);
		statusBar.setMessage(Application.getResourceBundle().getString("AboutFinish"));
	}

}