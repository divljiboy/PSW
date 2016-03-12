package actions.subtabs;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import actions.manager.ActionsManager;
import application.Application;
import gui.MainFrame;
import gui.tables.TabbedPane;
import gui.tables.TablesPane;

/**
 * Singleton class that extends {@link AbstractAction}. It selects the last
 * sub-tab (child table) in child {@link TabbedPane}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Isidora Škulec
 * @author Ivan Divljak
 * @see AbstractAction
 * @see TabbedPane
 *
 */
public class ActionLastSubTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionLastSubTab} object.
	 */
	private static ActionLastSubTab instance = null;
	

	/**
	 * Returns the only instance of {@link ActionLastSubTab} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionLastSubTab}
	 */
	public static ActionLastSubTab getInstance() {
		if(instance==null) {
			instance = new ActionLastSubTab();
		}
		return instance;
	}

	/**
	 * {@link ActionLastSubTab} private constructor. Initializes the
	 * object with name and short description from localization properties file,
	 * as well with small icon and accelerator key (Ctrl + Shift + E).
	 * 
	 */
	private ActionLastSubTab() {
		putValue(NAME, Application.getResourceBundle().getString("LastSubTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/lastSubtab.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("LastSubTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
	}

	/**
	 * Selects the last sub-tab (last child table) of the current child
	 * {@link TabbedPane}, if it exists.
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("LastSubTabStart"));
		
		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		TablesPane tablesPane = (TablesPane) tabbedPane.getSelectedComponent();
		TabbedPane childTabbedPane = (TabbedPane) tablesPane.getComponent(1);
		int tabCount = childTabbedPane.getTabCount();
		if (tabCount > 0) {
			childTabbedPane.setSelectedIndex(tabCount-1);
		}
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("LastSubTabStart"));
	}
}
