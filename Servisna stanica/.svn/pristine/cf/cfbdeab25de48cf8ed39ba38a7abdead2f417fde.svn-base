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
 * Singleton class that extends {@link AbstractAction}. It closes currently selected tab in
 * main {@link TabbedPane}.
 * 
 * @author Milan Radeta
 * @author Ivan Divljak
 * @see AbstractAction
 * @see TabbedPane
 *
 */
public class ActionCloseTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionCloseTab} object.
	 */
	private static ActionCloseTab instance = null;

	/**
	 * Returns the only instance of {@link ActionCloseTab} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionCloseTab}
	 */
	public static ActionCloseTab getInstance() {
		if (instance == null) {
			instance = new ActionCloseTab();
		}
		return instance;
	}

	/**
	 * {@link ActionCloseTab} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon and accelerator key (Ctrl + W).
	 * 
	 */
	private ActionCloseTab() {
		putValue(NAME, Application.getResourceBundle().getString("CloseSelectedTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/x.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("CloseSelectedTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
	}


	/**
	 * Closes currently selected tab of main {@link TabbedPane}.
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("CloseSelectedTabStart"));
		
		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		int selectedIndex = tabbedPane.getSelectedIndex();
		if (selectedIndex >= 0) {
			tabbedPane.remove(selectedIndex);
		}
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("CloseSelectedTabFinish"));		
	}

}
