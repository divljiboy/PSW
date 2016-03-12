package actions.tabs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import actions.manager.ActionsManager;
import application.Application;
import gui.MainFrame;
import gui.tables.TabbedPane;

/**
 * Singleton class that extends {@link AbstractAction}. It closes all tabs of
 * main {@link TabbedPane}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see TabbedPane
 *
 */
public class ActionCloseAllTabs extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionCloseAllTabs} object.
	 */
	private static ActionCloseAllTabs instance = null;

	/**
	 * Returns the only instance of {@link ActionCloseAllTabs} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionCloseAllTabs}
	 */
	public static ActionCloseAllTabs getInstance() {
		if (instance == null) {
			instance = new ActionCloseAllTabs();
		}
		return instance;
	}

	/**
	 * {@link ActionCloseAllTabs} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon and accelerator key (Ctrl + Shift + W).
	 * 
	 */
	private ActionCloseAllTabs() {
		putValue(NAME, Application.getResourceBundle().getString("CloseAllTabs"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/xCircle.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("CloseAllTabsDesc"));
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
	}

	/**
	 * Closes all tabs of main {@link TabbedPane}.
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("CloseAllTabsStart"));

		TabbedPane.getMainTabbedPane().removeAll();
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("CloseAllTabsFinish"));
	}

}
