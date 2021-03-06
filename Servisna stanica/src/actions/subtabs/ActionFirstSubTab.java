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
 * Singleton class that extends {@link AbstractAction}. It selects the first
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
public class ActionFirstSubTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionFirstSubTab} object.
	 */
	private static ActionFirstSubTab instance = null;

	/**
	 * Returns the only instance of {@link ActionFirstSubTab} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionFirstSubTab}
	 */
	public static ActionFirstSubTab getInstance() {
		if (instance == null) {
			instance = new ActionFirstSubTab();
		}
		return instance;
	}

	/**
	 * {@link ActionFirstSubTab} private constructor. Initializes the
	 * object with name and short description from localization properties file,
	 * as well with small icon and accelerator key (Ctrl + Shift + Q).
	 * 
	 */
	private ActionFirstSubTab() {
		putValue(NAME, Application.getResourceBundle().getString("FirstSubTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/firstSubtab.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("FirstSubTabDesc"));
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
	}

	/**
	 * Selects the first sub-tab (first child table) of the current child
	 * {@link TabbedPane}, if it exists.
	 */
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("FirstSubTabStart"));
		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		TablesPane tablesPane = (TablesPane) tabbedPane.getSelectedComponent();
		TabbedPane childTabbedPane = (TabbedPane) tablesPane.getComponent(1);
		int tabCount = childTabbedPane.getTabCount();
		if (tabCount > 0) {
			childTabbedPane.setSelectedIndex(0);
		}
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("FirstSubTabStart"));
	}

}
