package gui.dialog;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import actions.exit.ActionExit;
import actions.login.ActionLogin;
import actions.signup.ActionSignUp;
import application.Application;

/**
 * Extension of the {@link JDialog} class.
 * It is used for logging into the application. 
 * Contains a username and a password field.
 * If the user does not have an account yet, he or she can choose to create one.
 * 
 * @author Borko Arsovic
 * @author Milan Radeta
 * @see SignUpDialog
 */
public class LoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private JLabel labelUsername;
	private JLabel labelPassword;
	private JLabel labelStatus;

	private JTextField fieldUsername;
	private JPasswordField fieldPassword;

	private JButton buttonLogin;
	private JButton buttonExit;
	private JButton buttonSignUp;

	/**
	 * Constructor of the {@link LoginDialog} class.
	 * Creates a small dialog for either logging or signing into the application.
	 */
	public LoginDialog() {
		setTitle(Application.getResourceBundle().getString("LoginForm"));

		ActionLogin.getInstance().setLoginDialog(this);
		ActionSignUp.getInstance().setLoginDialog(this);

		labelUsername = new JLabel(Application.getResourceBundle().getString("LoginUsername"));
		labelPassword = new JLabel(Application.getResourceBundle().getString("LoginPassword"));
		fieldUsername = new JTextField(20);
		fieldUsername.setText("a");
		fieldPassword = new JPasswordField(20);
		fieldPassword.setText("a");
		labelStatus = new JLabel("");
		labelStatus.setHorizontalAlignment(SwingConstants.CENTER);

		buttonLogin = new JButton(ActionLogin.getInstance());
		buttonExit = new JButton(ActionExit.getInstance());
		buttonSignUp = new JButton(ActionSignUp.getInstance());

		panel = new JPanel(new GridBagLayout());
		add(panel);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.ipadx = 10;
		constraints.ipady = 10;
		constraints.insets = new Insets(5, 5, 5, 5);

		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(labelUsername, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(labelPassword, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		panel.add(fieldUsername, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		panel.add(fieldPassword, constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(buttonLogin, constraints);
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(buttonExit, constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		panel.add(buttonSignUp, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		panel.add(labelStatus, constraints);

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(Application.class.getResource("/icons/logo.png")).getImage());
		setMinimumSize(new Dimension(300, 150));
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Returns a {@link JPanel} containing all the {@link JComponent} objects of this dialog.
	 * @return {@link JPanel}
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Sets a {@link JPanel} containing all the {@link JComponent} objects of this dialog.
	 * @param panel
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * Returns the {@link JLabel} of the username field.
	 * @return {@link JLabel}
	 */
	public JLabel getLabelUsername() {
		return labelUsername;
	}

	/**
	 * Sets the {@link JLabel} of the username field.
	 * @param labelUsername
	 */
	public void setLabelUsername(JLabel labelUsername) {
		this.labelUsername = labelUsername;
	}

	/**
	 * Returns the {@link JLabel} of the password field.
	 * @return {@link JLabel}
	 */
	public JLabel getLabelPassword() {
		return labelPassword;
	}

	/**
	 * Sets the {@link JLabel} of the password field.
	 * @param labelPassword
	 */
	public void setLabelPassword(JLabel labelPassword) {
		this.labelPassword = labelPassword;
	}

	/**
	 * Returns the {@link JLabel} of the login status.
	 * @return {@link JLabel}
	 */
	public JLabel getLabelStatus() {
		return labelStatus;
	}

	/**
	 * Sets the {@link JLabel} of the login status.
	 * @param labelStatus
	 */
	public void setLabelStatus(JLabel labelStatus) {
		this.labelStatus = labelStatus;
	}

	/**
	 * Returns the username {@link JTextField}.
	 * @return {@link JTextField}
	 */
	public JTextField getFieldUsername() {
		return fieldUsername;
	}

	/**
	 * Sets the username {@link JTextField}.
	 * @param fieldUsername
	 */
	public void setFieldUsername(JTextField fieldUsername) {
		this.fieldUsername = fieldUsername;
	}

	/**
	 * Returns the password {@link JPasswordField}.
	 * @return {@link JPasswordField}
	 */
	public JPasswordField getFieldPassword() {
		return fieldPassword;
	}

	/**
	 * Sets the password {@link JPasswordField}.
	 * @param fieldPassword
	 */
	public void setFieldPassword(JPasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	/**
	 * Returns the {@link JButton} for logging in.
	 * @return {@link JButton}
	 */
	public JButton getButtonLogin() {
		return buttonLogin;
	}

	/**
	 * Sets the {@link JButton} for logging in.
	 * @param buttonLogin
	 */
	public void setButtonLogin(JButton buttonLogin) {
		this.buttonLogin = buttonLogin;
	}

	/**
	 * Returns the {@link JButton} for exiting the application.
	 * @return {@link JButton}
	 */
	public JButton getButtonExit() {
		return buttonExit;
	}

	/**
	 * Sets the {@link JButton} for exiting the application.
	 * @param buttonExit
	 */
	public void setButtonExit(JButton buttonExit) {
		this.buttonExit = buttonExit;
	}

	/**
	 * Returns the {@link JButton} for signing up.
	 * @return {@link JButton}
	 */
	public JButton getButtonSignUp() {
		return buttonSignUp;
	}

	/**
	 * Sets the {@link JButton} for signing up.
	 * @param buttonSignUp
	 */
	public void setButtonSignUp(JButton buttonSignUp) {
		this.buttonSignUp = buttonSignUp;
	}
}
