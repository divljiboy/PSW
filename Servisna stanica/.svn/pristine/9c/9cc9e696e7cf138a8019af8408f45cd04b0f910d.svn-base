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
 * Singleton class that extends {@link AbstractAction}. It selects the first tab
 * main {@link TabbedPane}, if it exists.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see TabbedPane
 *
 */
public class ActionFirstTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionFirstTab} object.
	 */
	private static ActionFirstTab instance = null;
	

	/**
	 * Returns the only instance of {@link ActionFirstTab} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionFirstTab}
	 */
	public static ActionFirstTab getInstance() {
		if(instance==null) {
			instance = new ActionFirstTab();
		}
		return instance;
	}

	/**
	 * {@link ActionFirstTab} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon and accelerator key (Ctrl + Q).
	 * 
	 */
	private ActionFirstTab() {
		putValue(NAME, Application.getResourceBundle().getString("FirstTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/tabFirst.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("FirstTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
	}

	/**
	 * Selects first tab of main {@link TabbedPane}, if it exists.
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage( Application.getResourceBundle().getString("FirstTabStart"));
		
		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		int tabCount = tabbedPane.getTabCount();
		if (tabCount > 0) {
			tabbedPane.setSelectedIndex(0);
		}
		ActionsManager.updateTabActions();

		MainFrame.getInstance().getStatusBar().setMessage( Application.getResourceBundle().getString("FirstTabFinish"));
	}

}
