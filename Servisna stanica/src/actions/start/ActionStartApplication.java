package actions.start;

import java.awt.event.ActionEvent;
import java.sql.DriverManager;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import actions.exit.ActionExit;
import application.Application;
import database.DatabaseConnection;
import gui.dialog.LoginDialog;
import gui.dialog.StartDialog;

/**
 * Singleton class that extends {@link AbstractAction}. It tries to connect to
 * database via {@link StartDialog} and if it's successful it'll open
 * {@link LoginDialog}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @see AbstractAction
 * @see StartDialog
 * @see LoginDialog
 *
 */
public class ActionStartApplication extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionStartApplication} object.
	 */
	private static ActionStartApplication instance = null;
	/**
	 * Represents the {@link StartDialog} {@link ActionStartApplication} is
	 * linked to.
	 */
	private StartDialog startDialog;

	/**
	 * {@link ActionStartApplication} private constructor. Initializes the
	 * object with name and short description from localization properties file,
	 * as well with small icon.
	 * 
	 */
	private ActionStartApplication() {
		putValue(NAME, Application.getResourceBundle().getString("StartApp"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/start.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("StartAppDesc"));
	}

	/**
	 * Returns the only instance of {@link ActionStartApplication} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionStartApplication}
	 */
	public static ActionStartApplication getInstance() {
		if (instance == null) {
			instance = new ActionStartApplication();
		}
		return instance;
	}

	/**
	 * Tries to connect to database specified by IP field in {@link StartDialog}
	 * . It either tries to connect to UNS (192.168.77.230/psw-2015-tim3-1) or
	 * Other (147.91.175.155/psw-2015-tim3-1). Before that it disables
	 * {@link StartDialog}'s combo boxes, actions and sets default close
	 * operation to {@link WindowConstants}.<i><b>DO_NOTHING_ON_CLOSE.</b></i>.
	 * If login timeout of 5 seconds ticks out or an exception is thrown status
	 * will say connection is invalid. Otherwise, {@link LoginDialog} will be opened.
	 */
	public void actionPerformed(ActionEvent arg0) {
		startDialog.getLabelStatus().setText(Application.getResourceBundle().getString("StartAppProgress"));
		startDialog.getComboBoxIP().setEnabled(false);
		startDialog.getComboBoxLanguage().setEnabled(false);
		setEnabled(false);
		ActionExit.getInstance().setEnabled(false);
		startDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		startDialog.pack();
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				String unsUrl = "jdbc:jtds:sqlserver://192.168.77.230/psw-2015-tim3-1";
				String defaultUrl = "jdbc:jtds:sqlserver://147.91.175.155/psw-2015-tim3-1";
				String user = "psw-2015-tim3-1";
				String pass = "tim3-114009874";

				int indexIP = startDialog.getComboBoxIP().getSelectedIndex();
				try {
					DriverManager.setLoginTimeout(5);
					DatabaseConnection.getInstance()
							.setConn(DriverManager.getConnection(indexIP == 0 ? unsUrl : defaultUrl, user, pass));

					if (DatabaseConnection.getInstance().getConn() != null) {
						startDialog.dispose();
						LoginDialog loginDialog = new LoginDialog();
						loginDialog.setVisible(true);
					} else {
						startDialog.getLabelStatus().setText(Application.getResourceBundle().getString("InvalidConnection"));
					}
				} catch (Exception e1) {
					// e1.printStackTrace();
					startDialog.getLabelStatus().setText(Application.getResourceBundle().getString("InvalidConnection"));
				} finally {
					startDialog.getComboBoxIP().setEnabled(true);
					startDialog.getComboBoxLanguage().setEnabled(true);
					setEnabled(true);
					ActionExit.getInstance().setEnabled(true);
					startDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					startDialog.pack();
				}
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		
	}

	/**
	 * Returns {@link StartDialog} {@link ActionStartApplication} is linked to.
	 * 
	 * @return {@link StartDialog}
	 * 
	 */
	public StartDialog getStartDialog() {
		return startDialog;
	}

	/**
	 * Sets {@link StartDialog} {@link ActionStartApplication} is linked to.
	 * 
	 */
	public void setStartDialog(StartDialog startDialog) {
		this.startDialog = startDialog;
	}

}
