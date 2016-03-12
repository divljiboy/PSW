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
 * Singleton class that extends {@link AbstractAction}. It selects the next
 * sub-tab (child table) in child {@link TabbedPane}.
 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Isidora Škulec
 * @author Ivan Divljak
 * @see AbstractAction
 * @see TabbedPane
 *
 */
public class ActionNextSubTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionNextSubTab} object.
	 */
	private static ActionNextSubTab instance = null;

	/**
	 * Returns the only instance of {@link ActionNextSubTab} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionNextSubTab}
	 */
	public static ActionNextSubTab getInstance() {
		if (instance == null) {
			instance = new ActionNextSubTab();
		}
		return instance;
	}

	/**
	 * {@link ActionNextSubTab} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Shift + E).
	 * 
	 */
	private ActionNextSubTab() {
		putValue(NAME, Application.getResourceBundle().getString("NextSubTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/nextSubtab.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("NextSubTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.SHIFT_MASK));
	}

	/**
	 * Selects the next sub-tab (next child table) of the current child
	 * {@link TabbedPane}, if it exists.
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("NextSubTabStart"));

		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		TablesPane tablesPane = (TablesPane) tabbedPane.getSelectedComponent();
		TabbedPane childTabbedPane = (TabbedPane) tablesPane.getComponent(1);
		int selectedIndex = childTabbedPane.getSelectedIndex();
		int maxIndex = childTabbedPane.getTabCount() - 1;
		if (selectedIndex < maxIndex) {
			childTabbedPane.setSelectedIndex(selectedIndex + 1);
		}
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("NextSubTabFinish"));
	}

}
