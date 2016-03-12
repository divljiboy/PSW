package actions.signup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import application.Application;
import gui.dialog.LoginDialog;
import gui.dialog.SignUpDialog;

/**
 * Singleton class that extends {@link AbstractAction} that opens up a {@link SignUpDialog}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Isidora Škulec
 * @see AbstractAction
 * @see SignUpDialog
 *
 */
public class ActionSignUp extends AbstractAction {
	static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionSignUp} object.
	 */
	private static ActionSignUp instance = null;
	/**
	 * Represents the {@link LoginDialog} {@link ActionSignUp} is linked
	 * to.
	 */
	private LoginDialog loginDialog = null;

	/**
	 * Returns the only instance of {@link ActionSignUp} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionSignUp}
	 */
	public static ActionSignUp getInstance() {
		if(instance == null) {
			instance = new ActionSignUp();
		}
		return instance;
	}
	

	/**
	 * {@link ActionSignUp} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon.
	 * 
	 */
	private ActionSignUp() {
		putValue(NAME, Application.getResourceBundle().getString("SignUp"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/createAccount.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("SignUpDesc"));
	}

	/** 
	 * Opens up a {@link SignUpDialog}.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SignUpDialog signUpDialog = new SignUpDialog();
		signUpDialog.setVisible(true);
	}


	/**
	 * Returns {@link LoginDialog} {@link ActionSignUp} is linked to.
	 * 
	 * @return {@link LoginDialog}
	 * 
	 */
	public LoginDialog getLoginDialog() {
		return loginDialog;
	}


	/**
	 * Sets {@link LoginDialog} {@link ActionSignUp} is linked to.
	 * 
	 */
	public void setLoginDialog(LoginDialog loginDialog) {
		this.loginDialog = loginDialog;
	}

}
