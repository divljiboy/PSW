package actions.exit;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import application.Application;

/**
 * Singleton class that extends {@link AbstractAction}. When performed, it asks
 * the user to confirm the exit from program.
 * 
 * @author Milan Radeta
 * @see AbstractAction
 *
 */

public class ActionExit extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionExit} object.
	 */
	private static ActionExit instance = null;

	/**
	 * Returns the only instance of {@link ActionExit} object. If it does not
	 * exist, it will be created.
	 * 
	 * @return {@link ActionExit}
	 */
	public static ActionExit getInstance() {
		if (instance == null) {
			instance = new ActionExit();
		}
		return instance;
	}

	/**
	 * {@link ActionExit} private constructor. Initializes the object with name
	 * and short description from localization properties file, as well with
	 * small icon and accelerator key (Alt + F4).
	 * 
	 */
	private ActionExit() {
		putValue(NAME, Application.getResourceBundle().getString("Exit"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/exit.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("ExitDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
	}

	/**
	 * Opens option dialog with YES and NO buttons which asks the user to
	 * confirm the exit from program. The message, title and button captions are
	 * read from localization properties file.
	 */
	public void actionPerformed(ActionEvent e) {
		int code = JOptionPane.showConfirmDialog(null,
				Application.getResourceBundle().getString("ExitMessage"),
				Application.getResourceBundle().getString("Exit"),
				JOptionPane.YES_NO_OPTION);

		if (code == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}