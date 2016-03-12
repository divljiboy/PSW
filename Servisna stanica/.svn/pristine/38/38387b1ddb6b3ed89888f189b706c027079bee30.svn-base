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
 * Singleton class that extends {@link AbstractAction}. It selects the last tab
 * main {@link TabbedPane}, if it exists.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see TabbedPane
 *
 */
public class ActionLastTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionLastTab} object.
	 */
	private static ActionLastTab instance = null;

	/**
	 * Returns the only instance of {@link ActionLastTab} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionLastTab}
	 */
	public static ActionLastTab getInstance() {
		if(instance==null) {
			instance = new ActionLastTab();
		}
		return instance;
	}

	/**
	 * {@link ActionLastTab} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon and accelerator key (Ctrl + E).
	 * 
	 */
	private ActionLastTab() {
		putValue(NAME, Application.getResourceBundle().getString("LastTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/tabLast.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("LastTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
	}
	
	/**
	 * Selects last tab of main {@link TabbedPane}, if it exists.
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage( Application.getResourceBundle().getString("LastTabStart"));
		
		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		int tabCount = tabbedPane.getTabCount();
		if (tabCount > 0) {
			tabbedPane.setSelectedIndex(tabCount - 1);
		}
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar().setMessage( Application.getResourceBundle().getString("LastTabFinish"));
	}

}
