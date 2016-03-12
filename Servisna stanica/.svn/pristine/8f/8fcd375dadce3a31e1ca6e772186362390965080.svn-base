package actions.logout;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import application.Application;
import gui.MainFrame;
import gui.dialog.LoginDialog;

/**
 * Singleton class that extends {@link AbstractAction}. Logs out the user from
 * the system and opens {@link LoginDialog}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Isidora Škulec
 * @see AbstractAction
 * @see LoginDialog
 *
 */
public class ActionLogout extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionLogout} object.
	 */
	private static ActionLogout instance = null;

	/**
	 * {@link ActionLogout} private constructor. Initializes the object with name
	 * and short description from localization properties file, as well with
	 * small icon.
	 * 
	 */
	private ActionLogout() {
		putValue(NAME, Application.getResourceBundle().getString("Logout"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/start.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("LogoutDesc"));
	}

	/**
	 * Returns the only instance of {@link ActionLogout} object. If it does not
	 * exist, it will be created.
	 * 
	 * @return {@link ActionLogout}
	 */
	public static ActionLogout getInstance() {
		if (instance == null) {
			instance = new ActionLogout();
		}
		return instance;
	}

	/**
	 * Disposes {@link MainFrame} and opens {@link LoginDialog}.
	 * 
	 * @see MainFrame
	 * @see LoginDialog
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().dispose();
		LoginDialog loginDialog = new LoginDialog();
		loginDialog.setVisible(true);
	}

}
