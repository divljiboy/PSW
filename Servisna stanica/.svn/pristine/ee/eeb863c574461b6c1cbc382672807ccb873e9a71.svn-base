package actions.login;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import actions.exit.ActionExit;
import actions.signup.ActionSignUp;
import application.Application;
import database.DatabaseConnection;
import gui.MainFrame;
import gui.dialog.LoginDialog;

/**
 * Singleton class that extends {@link AbstractAction}. Tries to login the user
 * to the system via {@link LoginDialog}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @see AbstractAction
 * @see LoginDialog
 *
 */
public class ActionLogin extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionLogin} object.
	 */
	private static ActionLogin instance = null;
	/**
	 * Represents the {@link LoginDialog} {@link ActionLogin} is linked
	 * to.
	 */
	private LoginDialog loginDialog;

	/**
	 * {@link ActionLogin} private constructor. Initializes the object with name
	 * and short description from localization properties file, as well with
	 * small icon.
	 * 
	 */
	private ActionLogin() {
		putValue(NAME, Application.getResourceBundle().getString("Login"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/start.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("LoginDesc"));
	}

	/**
	 * Returns the only instance of {@link ActionLogin} object. If it does not
	 * exist, it will be created.
	 * 
	 * @return {@link ActionLogin}
	 */
	public static ActionLogin getInstance() {
		if (instance == null) {
			instance = new ActionLogin();
		}
		return instance;
	}

	/**
	 * Tries to login user to the system via {@link LoginDialog}. It first
	 * checks if {@link LoginDialog}'s fields are not empty. It they are not,
	 * they become disabled, as well the actions {@link ActionLogin} and
	 * {@link ActionExit} and the default close operation of {@link LoginDialog}
	 * is {@link WindowConstants}.<i><b>DO_NOTHING_ON_CLOSE.</b></i>. Then a
	 * {@link PreparedStatement} is set to select the user from database whose
	 * username and password are equal to the ones in {@link LoginDialog}'s
	 * fields. If {@link ResultSet} contains results, {@link MainFrame} is
	 * opened and {@link LoginDialog}'s disposed. Otherwise, {@link LoginDialog}
	 * 's status will write that wrong username or password has been inserted.
	 * All fields and actions are enabled afterwards.
	 * 
	 * @see LoginDialog
	 * @see ActionExit
	 * @see WindowConstants
	 * @see PreparedStatement
	 * @see ResultSet
	 * @see MainFrame
	 * @throws SQLException
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!loginDialog.getFieldUsername().getText().isEmpty()
				&& loginDialog.getFieldPassword().getPassword().length > 0) {
			loginDialog.getLabelStatus().setText(Application.getResourceBundle().getString("LoginProgress"));
			loginDialog.getFieldUsername().setEnabled(false);
			loginDialog.getFieldPassword().setEnabled(false);
			setEnabled(false);
			ActionExit.getInstance().setEnabled(false);
			ActionSignUp.getInstance().setEnabled(false);
			loginDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			loginDialog.pack();
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						PreparedStatement statement;
						statement = DatabaseConnection.getInstance().getConn()
								.prepareStatement("SELECT * FROM KORISNICI WHERE username = ? AND password = ?");
						statement.setString(1, loginDialog.getFieldUsername().getText());
						statement.setString(2, new String(loginDialog.getFieldPassword().getPassword()));
						ResultSet resultSet = statement.executeQuery();
						if (resultSet.next()) {
							loginDialog.getLabelStatus().setText(Application.getResourceBundle().getString("AppInit"));
							MainFrame.getInstance().setVisible(true);
							loginDialog.dispose();
						} else {
							loginDialog.getLabelStatus()
									.setText(Application.getResourceBundle().getString("WrongUsernameOrPassword"));
							loginDialog.getFieldUsername().setText("");
							loginDialog.getFieldPassword().setText("");
						}
					} catch (SQLException e2) {
						if (e2.getErrorCode() == 0) {
							loginDialog.getLabelStatus()
									.setText(Application.getResourceBundle().getString("InvalidConnection"));
						} else {
							loginDialog.getLabelStatus().setText(Application.getResourceBundle().getString("LoginFail"));
						}
						loginDialog.getFieldUsername().setText("");
						loginDialog.getFieldPassword().setText("");
					}

					loginDialog.getFieldUsername().setEnabled(true);
					loginDialog.getFieldPassword().setEnabled(true);
					setEnabled(true);
					ActionExit.getInstance().setEnabled(true);
					ActionSignUp.getInstance().setEnabled(true);
					loginDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					loginDialog.pack();
				}
			};
			
			Thread thread = new Thread(runnable);
			thread.start();
		} else {
			loginDialog.getLabelStatus().setText(Application.getResourceBundle().getString("EmptyFields"));
			loginDialog.pack();
		}
	}

	/**
	 * Returns {@link LoginDialog} {@link ActionLogin} is linked to.
	 * 
	 * @return {@link LoginDialog}
	 * 
	 */
	public LoginDialog getLoginDialog() {
		return loginDialog;
	}

	/**
	 * Sets {@link LoginDialog} {@link LoginDialog} is linked to.
	 * 
	 */
	public void setLoginDialog(LoginDialog loginDialog) {
		this.loginDialog = loginDialog;
	}

}
