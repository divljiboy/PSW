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
 * Singleton class that extends {@link AbstractAction}. It closes the next tab
 * main {@link TabbedPane}, if it exists.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see TabbedPane
 *
 */
public class ActionNextTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionNextTab} object.
	 */
	private static ActionNextTab instance = null;

	/**
	 * Returns the only instance of {@link ActionNextTab} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionNextTab}
	 */
	public static ActionNextTab getInstance() {
		if (instance == null) {
			instance = new ActionNextTab();
		}
		return instance;
	}
	/**
	 * {@link ActionNextTab} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon and accelerator key (E).
	 * 
	 */
	private ActionNextTab() {
		putValue(NAME, Application.getResourceBundle().getString("NextTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/tabNext.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("NextTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, 0));
	}

	/**
	 * Selects the next tab of main {@link TabbedPane}, if it exists.
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("NextTabStart"));

		TabbedPane tabbedPane = MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
		int selectedIndex = tabbedPane.getSelectedIndex();
		int maxIndex = tabbedPane.getTabCount() - 1;
		if (selectedIndex >= 0 && selectedIndex < maxIndex) {
			tabbedPane.setSelectedIndex(selectedIndex + 1);
		}
		ActionsManager.updateTabActions();
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("NextTabFinish"));
	}

}
