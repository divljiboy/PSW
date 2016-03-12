package actions.cancel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import application.Application;
import gui.MainFrame;
import gui.StatusBar;
import gui.dialog.SignUpDialog;

/**
 * 
 * Singleton class that extends {@link AbstractAction}.
 * When performed, it disposes the opened dialog.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * 
 * @see AbstractAction
 * @see JDialog
 *
 */
public class ActionCancel extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionCancel} class.
	 */
	private static ActionCancel instance = null;
	/**
	 * Represents the current {@link JDialog} {@link ActionCancel} is linked to.
	 */
	private JDialog dialog;
	
	/**
	 * Returns the only instance of {@link ActionCancel} object.
	 * If it does not exist, it will be created.
	 * 
	 * @return {@link ActionCancel}
	 */
	public static ActionCancel getInstance() {
		if(instance == null) {
			instance = new ActionCancel();
		}
		return instance;
	}
	
	/**
	 * {@link ActionCancel} private constructor.
	 * Initializes the object with name and short description
	 * from localization properties file, as well
	 * with small icon.
	 * 
	 */
	private ActionCancel() {
		putValue(NAME, Application.getResourceBundle().getString("Cancel"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/cancel.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("CancelDesc"));
	}
	
	/**
	 * Disposes {@link JDialog} that {@link ActionCancel} is linked to.
	 * If {@link JDialog} is not {@link SignUpDialog}, it updates the
	 * {@link MainFrame}'s {@link StatusBar}.
	 * 
	 * @see JDialog
	 * @see SignUpDialog
	 * @see MainFrame
	 * @see StatusBar
	 */
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dialog.dispose();
		if (!(dialog instanceof SignUpDialog)) {
			MainFrame.getInstance().getStatusBar().setMessage("");
		}
	}

	/**
	 * Returns {@link JDialog} {@link ActionCancel} is linked to.
	 * 
	 * @return {@link JDialog}
	 * 
	 */
	public JDialog getDialog() {
		return dialog;
	}

	/**
	 * Sets {@link JDialog} {@link ActionCancel} is linked to.
	 * 
	 */
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

}
