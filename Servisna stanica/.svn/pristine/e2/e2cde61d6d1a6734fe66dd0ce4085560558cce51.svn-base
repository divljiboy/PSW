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
 * Singleton class that extends {@link AbstractAction}. It selects the previous
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
public class ActionPrevSubTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionPrevSubTab} object.
	 */
	private static ActionPrevSubTab instance = null;

	/**
	 * Returns the only instance of {@link ActionPrevSubTab} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionPrevSubTab}
	 */
	public static ActionPrevSubTab getInstance() {
		if (instance == null) {
			instance = new ActionPrevSubTab();
		}
		return instance;
	}

	/**
	 * {@link ActionPrevSubTab} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Shift + Q).
	 * 
	 */
	private ActionPrevSubTab() {
		putValue(NAME, Application.getResourceBundle().getString("PreviousSubTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/prevSubtab.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("PreviousSubTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.SHIFT_MASK));
	}

	/**
	 * Selects the previous sub-tab (previous child table) of the current child
	 * {@link TabbedPane}, if it exists.
	 */
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("PreviousSubTabStart"));

		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		TablesPane tablesPane = (TablesPane) tabbedPane.getSelectedComponent();
		TabbedPane childTabbedPane = (TabbedPane) tablesPane.getComponent(1);
		int selectedIndex = childTabbedPane.getSelectedIndex();
		if (selectedIndex >= 0) {
			childTabbedPane.setSelectedIndex(selectedIndex - 1);
		}
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("PreviousSubTabFinish"));
	}

}
