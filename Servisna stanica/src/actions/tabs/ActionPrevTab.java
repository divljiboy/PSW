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
 * Singleton class that extends {@link AbstractAction}. It selects the previous
 * tab main {@link TabbedPane}, if it exists.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see TabbedPane
 *
 */
public class ActionPrevTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionPrevTab} object.
	 */
	private static ActionPrevTab instance = null;

	/**
	 * Returns the only instance of {@link ActionPrevTab} object. If it does not
	 * exist, it will be created.
	 * 
	 * @return {@link ActionPrevTab}
	 */
	public static ActionPrevTab getInstance() {
		if (instance == null) {
			instance = new ActionPrevTab();
		}
		return instance;
	}

	/**
	 * {@link ActionPrevTab} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Q).
	 * 
	 */
	private ActionPrevTab() {
		putValue(NAME, Application.getResourceBundle().getString("PreviousTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/tabPrevious.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("PreviousTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0));
	}

	/**
	 * Selects the previous tab of main {@link TabbedPane}, if it exists.
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("PreviousTabStart"));

		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		int selectedIndex = tabbedPane.getSelectedIndex();
		if (selectedIndex > 0) {
			tabbedPane.setSelectedIndex(selectedIndex - 1);
		}
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("PreviousTabFinish"));
	}

}
