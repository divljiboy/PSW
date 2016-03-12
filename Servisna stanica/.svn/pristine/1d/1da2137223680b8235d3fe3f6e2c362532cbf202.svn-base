package ra32_2011;

import static org.junit.Assert.assertSame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import gui.dialog.LoginDialog;

/**
 * Test class for {@link LoginDialog}.
 * 
 * @author Borko Arsović
 * @see JUnit4
 */

public class LoginDialogTest {

	LoginDialog loginDialog;
	JPanel panel = new JPanel();

	private JLabel labelUsername = new JLabel("Username");
	private JLabel labelPassword = new JLabel("Password");
	private JLabel labelStatus = new JLabel("status");

	private JTextField fieldUsername = new JTextField("Borko");
	private JPasswordField fieldPassword = new JPasswordField("Borko12345");

	private JButton buttonLogin;
	private JButton buttonExit;
	private JButton buttonSingUp;

	/**
	 * Set up method for {@link LoginDialogTest}. Creates test
	 * {@link LoginDialog}.
	 * 
	 * @throws Exception
	 * @see JUnit4
	 * @see LoginDialog
	 */
	@Before
	public void setUp() throws Exception {
		loginDialog = new LoginDialog();
	}

	/**
	 * Tear down method for {@link LoginDialogTest}. Disposes of
	 * {@link LoginDialog} test instance.
	 * 
	 * @throws Exception
	 * @see JUnit4
	 */
	@After
	public void tearDown() throws Exception {
		loginDialog.dispose();
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getPanel()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setPanel(JPanel)
	 * @see LoginDialog#getPanel()
	 * @see JPanel
	 */
	@Test
	public void testGetPanel() {
		loginDialog.setPanel(null);
		assertSame(null, loginDialog.getPanel());
		loginDialog.setPanel(panel);
		assertSame(panel, loginDialog.getPanel());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#setPanel(JPanel)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setPanel(JPanel)
	 * @see LoginDialog#getPanel()
	 * @see JPanel
	 */
	@Test
	public void testSetPanel() {
		loginDialog.setPanel(null);
		assertSame(null, loginDialog.getPanel());
		loginDialog.setPanel(panel);
		assertSame(panel, loginDialog.getPanel());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getLabelUsername()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setLabelUsername(JLabel)
	 * @see LoginDialog#getLabelUsername()
	 * @see JLabel
	 */
	@Test
	public void testGetLabelUsername() {
		loginDialog.setLabelUsername(null);
		assertSame(null, loginDialog.getLabelUsername());
		loginDialog.setLabelUsername(labelUsername);
		assertSame(labelUsername, loginDialog.getLabelUsername());

	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#setLabelUsername(JLabel)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setLabelUsername(JLabel)
	 * @see LoginDialog#getLabelUsername()
	 * @see JLabel
	 */
	@Test
	public void testSetLabelUsername() {
		loginDialog.setLabelUsername(null);
		assertSame(null, loginDialog.getLabelUsername());
		loginDialog.setLabelUsername(labelUsername);
		assertSame(labelUsername, loginDialog.getLabelUsername());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getLabelPassword()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setLabelPassword(JLabel)
	 * @see LoginDialog#getLabelPassword()
	 * @see JLabel
	 */
	@Test
	public void testGetLabelPassword() {
		loginDialog.setLabelPassword(null);
		assertSame(null, loginDialog.getLabelPassword());
		loginDialog.setLabelPassword(labelPassword);
		assertSame(labelPassword, loginDialog.getLabelPassword());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#setLabelPassword(JLabel)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setLabelPassword(JLabel)
	 * @see LoginDialog#getLabelPassword()
	 * @see JLabel
	 */
	@Test
	public void testSetLabelPassword() {
		loginDialog.setLabelPassword(null);
		assertSame(null, loginDialog.getLabelPassword());
		loginDialog.setLabelPassword(labelPassword);
		assertSame(labelPassword, loginDialog.getLabelPassword());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getLabelStatus()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setLabelStatus(JLabel)
	 * @see LoginDialog#getLabelStatus()
	 * @see JLabel
	 */
	@Test
	public void testGetLabelStatus() {
		loginDialog.setLabelStatus(null);
		assertSame(null, loginDialog.getLabelStatus());
		loginDialog.setLabelStatus(labelStatus);
		assertSame(labelStatus, loginDialog.getLabelStatus());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#setLabelStatus(JLabel)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setLabelStatus(JLabel)
	 * @see LoginDialog#getLabelStatus()
	 * @see JLabel
	 */
	@Test
	public void testSetLabelStatus() {
		loginDialog.setLabelStatus(null);
		assertSame(null, loginDialog.getLabelStatus());
		loginDialog.setLabelStatus(labelStatus);
		assertSame(labelStatus, loginDialog.getLabelStatus());

	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getFieldUsername()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setFieldUsername(JTextField)
	 * @see LoginDialog#getFieldUsername()
	 * @see JTextField
	 */
	@Test
	public void testGetFieldUsername() {
		loginDialog.setFieldUsername(null);
		assertSame(null, loginDialog.getFieldUsername());
		loginDialog.setFieldUsername(fieldUsername);
		assertSame(fieldUsername, loginDialog.getFieldUsername());
	}

	/**
	 * Test method for
	 * {@link gui.dialog.LoginDialog#setFieldUsername(JTextField)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setFieldUsername(JTextField)
	 * @see LoginDialog#getFieldUsername()
	 * @see JTextField
	 */
	@Test
	public void testSetFieldUsername() {
		loginDialog.setFieldUsername(null);
		assertSame(null, loginDialog.getFieldUsername());
		loginDialog.setFieldUsername(fieldUsername);
		assertSame(fieldUsername, loginDialog.getFieldUsername());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getFieldPassword()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setFieldPassword(JPasswordField)
	 * @see LoginDialog#getFieldPassword()
	 * @see JPasswordField
	 */
	@Test
	public void testGetFieldPassword() {
		loginDialog.setFieldPassword(null);
		assertSame(null, loginDialog.getFieldPassword());
		loginDialog.setFieldPassword(fieldPassword);
		assertSame(fieldPassword, loginDialog.getFieldPassword());
	}

	/**
	 * Test method for
	 * {@link gui.dialog.LoginDialog#setFieldPassword(JPasswordField)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setFieldPassword(JPasswordField)
	 * @see LoginDialog#getFieldPassword()
	 * @see JPasswordField
	 */
	@Test
	public void testSetFieldPassword() {
		loginDialog.setFieldPassword(null);
		assertSame(null, loginDialog.getFieldPassword());
		loginDialog.setFieldPassword(fieldPassword);
		assertSame(fieldPassword, loginDialog.getFieldPassword());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getButtonLogin()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setButtonLogin(JButton)
	 * @see LoginDialog#getButtonLogin()
	 * @see JButton
	 */
	@Test
	public void testGetButtonLogin() {
		loginDialog.setButtonLogin(null);
		assertSame(null, loginDialog.getButtonLogin());
		loginDialog.setButtonLogin(buttonLogin);
		assertSame(buttonLogin, loginDialog.getButtonLogin());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#setButtonLogin(JButton)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setButtonLogin(JButton)
	 * @see LoginDialog#getButtonLogin()
	 * @see JButton
	 */
	@Test
	public void testSetButtonLogin() {
		loginDialog.setButtonLogin(null);
		assertSame(null, loginDialog.getButtonLogin());
		loginDialog.setButtonLogin(buttonLogin);
		assertSame(buttonLogin, loginDialog.getButtonLogin());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getButtonExit()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setButtonExit(JButton)
	 * @see LoginDialog#getButtonExit()
	 * @see JButton
	 */
	@Test
	public void testGetButtonExit() {
		loginDialog.setButtonExit(null);
		assertSame(null, loginDialog.getButtonExit());
		loginDialog.setButtonExit(buttonExit);
		assertSame(buttonExit, loginDialog.getButtonExit());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#setButtonExit(JButton)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setButtonExit(JButton)
	 * @see LoginDialog#getButtonExit()
	 * @see JButton
	 */
	@Test
	public void testSetButtonExit() {
		loginDialog.setButtonExit(null);
		assertSame(null, loginDialog.getButtonExit());
		loginDialog.setButtonExit(buttonExit);
		assertSame(buttonExit, loginDialog.getButtonExit());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#getButtonSignUp()}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setButtonSignUp(JButton)
	 * @see LoginDialog#getButtonSignUp()
	 * @see JButton
	 */
	@Test
	public void testGetButtonSignUp() {
		loginDialog.setButtonSignUp(null);
		assertSame(null, loginDialog.getButtonSignUp());
		loginDialog.setButtonSignUp(buttonSingUp);
		assertSame(buttonSingUp, loginDialog.getButtonSignUp());
	}

	/**
	 * Test method for {@link gui.dialog.LoginDialog#setButtonSignUp(JButton)}.
	 * 
	 * @see LoginDialog
	 * @see LoginDialog#setButtonSignUp(JButton)
	 * @see LoginDialog#getButtonSignUp()
	 * @see JButton
	 */
	@Test
	public void testSetButtonSignUp() {
		loginDialog.setButtonSignUp(null);
		assertSame(null, loginDialog.getButtonSignUp());
		loginDialog.setButtonSignUp(buttonSingUp);
		assertSame(buttonSingUp, loginDialog.getButtonSignUp());

	}

}
