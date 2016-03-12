package actions.help;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import application.Application;
import gui.MainFrame;
import gui.StatusBar;

/**
 * Singleton class that extends {@link AbstractAction}.
 * Not fully implemented yet. Should open User Manual.
 * 
 * @author Ivan Divljak
 * @author Milan Radeta
 * @author Borko Arsović
 * @see AbstractAction
 *
 */
public class ActionUserManual extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionUserManual} object.
	 */
	private static ActionUserManual instance = null;

	/**
	 * Returns the only instance of {@link ActionUserManual} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionUserManual}
	 */
	public static ActionUserManual getInstance() {
		if (instance == null) {
			instance = new ActionUserManual();
		}
		return instance;
	}

	/**
	 * {@link ActionUserManual} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (F1).
	 * 
	 */
	private ActionUserManual() {
		putValue(NAME, Application.getResourceBundle().getString("UserManual"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/help.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("UserManualDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
	}

	/**
	 * Not fully implemented yet. Opens User Manual and updates {@link MainFrame}'s {@link StatusBar}.
	 * @see MainFrame
	 * @see StatusBar
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("UserManualStart"));
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("UserManualFinish"));
	}

}