package gui.popups;

import javax.swing.JPopupMenu;

import actions.tabs.ActionCloseAllTabs;
import actions.tabs.ActionCloseTab;


/**
 * Extension of the {@link JPopupMenu} class. Implements the singleton pattern.
 * Contains actions for closing tabs ({@link ActionCloseAllTabs}, {@link ActionCloseTab}).  
 * @author Milan Radeta
 * 
 */
public class TabPopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	private static TabPopupMenu instance;
	
	/**
	 * Private constructor of {@link TabPopupMenu} class.
	 * Adds instances of necessary actions to the menu.
	 */
	private TabPopupMenu() {
		add(ActionCloseTab.getInstance());
		add(ActionCloseAllTabs.getInstance());
	}
	
	/**
	 * Returns the instance of {@link TabPopupMenu}.
	 * If there is none, new one is created.
	 * @return {@link TabPopupMenu}
	 */
	public static TabPopupMenu getInstance() {
		if(instance == null) {
			instance = new TabPopupMenu();
		}
		return instance;
	}
}
