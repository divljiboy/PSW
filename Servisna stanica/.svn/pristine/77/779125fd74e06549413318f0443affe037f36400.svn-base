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

import actions.cancel.ActionCancel;
import actions.signup.ActionCreateAccount;
import actions.signup.ActionSignUp;
import application.Application;
/**
 * Extension of the {@link JDialog} class.
 * Represents a sign up form for the application.
 * Contains a username and a password field.
 * On successful action user gains the right to use the application.
 * 
 * @author Borko Arsović
 * @author Milan Radeta
 * @see LoginDialog
 */
public class SignUpDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private JLabel labelUsername;
	private JLabel labelPassword;
	private JLabel labelStatus;

	private JTextField fieldUsername;
	private JPasswordField fieldPassword;

	private JButton buttonCreate;
	private JButton buttonCancel;

	/**
	 * Constructor for the {@link SignUpDialog} class.
	 * Creates a form to set up an account for the application.
	 */
	public SignUpDialog() {
		super(ActionSignUp.getInstance().getLoginDialog(), true);
		setTitle(Application.getResourceBundle().getString("SignUpForm"));

		ActionCreateAccount.getInstance().setSignUpDialog(this);
		ActionCancel.getInstance().setDialog(this);

		labelUsername = new JLabel(Application.getResourceBundle().getString("SignUpUsername"));
		labelPassword = new JLabel(Application.getResourceBundle().getString("SignUpPassword"));
		fieldUsername = new JTextField(20);
		fieldPassword = new JPasswordField(20);

		labelStatus = new JLabel("");
		labelStatus.setHorizontalAlignment(SwingConstants.CENTER);

		buttonCreate = new JButton(ActionCreateAccount.getInstance());
		buttonCancel = new JButton(ActionCancel.getInstance());

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
		panel.add(buttonCreate, constraints);
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(buttonCancel, constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		panel.add(labelStatus, constraints);

		setMinimumSize(new Dimension(300, 150));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(Application.class.getResource("/icons/logo.png")).getImage());
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
	 * Returns the {@link JLabel} of the signup status.
	 * @return {@link JLabel}
	 */
	public JLabel getLabelStatus() {
		return labelStatus;
	}

	/**
	 * Sets the {@link JLabel} of the signup status.
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
	 * Returns the {@link JButton} for creating an account.
	 * @return {@link JButton}
	 */
	public JButton getButtonCreate() {
		return buttonCreate;
	}

	/**
	 * Sets the {@link JButton} for creating an account.
	 * @param buttonCreate
	 */
	public void setButtonCreate(JButton buttonCreate) {
		this.buttonCreate = buttonCreate;
	}

	/**
	 * Returns the {@link JButton} for cancelling the creation of a new account.
	 * @return {@link JButton}
	 */
	public JButton getButtonCancel() {
		return buttonCancel;
	}

	/**
	 * Sets the {@link JButton} for cancelling the creation of a new account.
	 * @param buttonCancel
	 */
	public void setButtonCancel(JButton buttonCancel) {
		this.buttonCancel = buttonCancel;
	}

}
