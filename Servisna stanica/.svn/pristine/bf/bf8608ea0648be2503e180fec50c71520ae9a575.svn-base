package actions.signup;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import actions.cancel.ActionCancel;
import application.Application;
import database.DatabaseConnection;
import gui.dialog.LoginDialog;
import gui.dialog.SignUpDialog;

/**
 * Singleton class that extends {@link AbstractAction}. Tries to create a new
 * user with an SQL INSERT {@link PreparedStatement} via {@link SignUpDialog}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @see AbstractAction
 * @see PreparedStatement
 * @see SignUpDialog
 *
 */
public class ActionCreateAccount extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionCreateAccount} object.
	 */
	private static ActionCreateAccount instance = null;
	/**
	 * Represents the {@link SignUpDialog} {@link ActionCreateAccount} is linked
	 * to.
	 */
	private SignUpDialog signUpDialog;

	/**
	 * Returns the only instance of {@link ActionCreateAccount} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionCreateAccount}
	 */
	public static ActionCreateAccount getInstance() {
		if (instance == null) {
			instance = new ActionCreateAccount();
		}
		return instance;
	}

	/**
	 * {@link ActionCreateAccount} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon. Initially it is enabled.
	 * 
	 */
	private ActionCreateAccount() {
		putValue(NAME, Application.getResourceBundle().getString("CreateAccount"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/createAccount.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("CreateAccountDesc"));
		setEnabled(true);
	}

	/**
	 * Tries to create a new user via {@link SignUpDialog}. It first checks if
	 * {@link LoginDialog}'s fields are not empty. It they are not, they become
	 * disabled, as well the actions {@link ActionCreateAccount} and
	 * {@link ActionCancel} and the default close operation of
	 * {@link SignUpDialog} is set to {@link WindowConstants}.
	 * <i><b>DO_NOTHING_ON_CLOSE.</b></i>. Then a {@link PreparedStatement} is
	 * set to select the user from database whose username is equal to the one
	 * in {@link SignUpDialog}'s fields. If {@link ResultSet} contains results,
	 * {@link SignUpDialog} 's status will write that the user with that
	 * username already exists. Otherwise, it will create an SQL INSERT
	 * {@link PreparedStatement} with values from {@link SignUpDialog}'s fields,
	 * execute it, notify the user the process is successful and dispose of
	 * {@link SignUpDialog}. All fields and actions are enabled afterwards.
	 * 
	 * @see SignUpDialog
	 * @see ActionCancel
	 * @see WindowConstants
	 * @see PreparedStatement
	 * @see ResultSet
	 * @throws SQLException
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {		
		if (!signUpDialog.getFieldUsername().getText().isEmpty()
				&& signUpDialog.getFieldPassword().getPassword().length > 0) {
			signUpDialog.getLabelStatus().setText(Application.getResourceBundle().getString("SignUpProgress"));
			signUpDialog.getFieldUsername().setEnabled(false);
			signUpDialog.getFieldPassword().setEnabled(false);
			setEnabled(false);
			ActionCancel.getInstance().setEnabled(false);
			signUpDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			signUpDialog.pack();
			
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						PreparedStatement statement;
						statement = DatabaseConnection.getInstance().getConn()
								.prepareStatement("SELECT * FROM KORISNICI WHERE USERNAME = ? ");
						statement.setString(1, signUpDialog.getFieldUsername().getText());
						ResultSet resultSet = statement.executeQuery();

						if (resultSet.next()) {
							signUpDialog.getLabelStatus()
									.setText(Application.getResourceBundle().getString("UserWithThisUsernameAlreadyExist"));

						} else {
							statement = DatabaseConnection.getInstance().getConn()
									.prepareStatement("INSERT INTO KORISNICI (USERNAME, PASSWORD) VALUES (?,?)");
							statement.setString(1, signUpDialog.getFieldUsername().getText());
							statement.setString(2, new String(signUpDialog.getFieldPassword().getPassword()));
							statement.executeUpdate();
							JOptionPane.showMessageDialog(signUpDialog,
									Application.getResourceBundle().getString("SignUpSuccess"));
							signUpDialog.dispose();
						}

					} catch (SQLException e) {
						if (e.getErrorCode() == 0) {
							signUpDialog.getLabelStatus()
									.setText(Application.getResourceBundle().getString("InvalidConnection"));
						} else {
							signUpDialog.getLabelStatus().setText(Application.getResourceBundle().getString("SignUpFail"));
						}
					}

					setEnabled(true);
					ActionCancel.getInstance().setEnabled(true);
					signUpDialog.getFieldUsername().setEnabled(true);
					signUpDialog.getFieldPassword().setEnabled(true);
					signUpDialog.getFieldUsername().setText("");
					signUpDialog.getFieldPassword().setText("");
					signUpDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					signUpDialog.pack();
				}
			};
			
			Thread thread = new Thread(runnable);
			thread.start();
		} else {
			signUpDialog.getLabelStatus().setText(Application.getResourceBundle().getString("EmptyFields"));
			signUpDialog.pack();
		}

	}

	/**
	 * Returns {@link SignUpDialog} {@link ActionCreateAccount} is linked to.
	 * 
	 * @return {@link SignUpDialog}
	 * 
	 */
	public SignUpDialog getSignUpDialog() {
		return signUpDialog;
	}

	/**
	 * Sets {@link SignUpDialog} {@link ActionCreateAccount} is linked to.
	 * 
	 */
	public void setSignUpDialog(SignUpDialog signUpDialog) {
		this.signUpDialog = signUpDialog;
	}

}
