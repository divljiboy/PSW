package gui.menus;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import actions.subtabs.ActionFirstSubTab;
import actions.subtabs.ActionLastSubTab;
import actions.subtabs.ActionNextSubTab;
import actions.subtabs.ActionPrevSubTab;
import actions.tabs.ActionCloseAllTabs;
import actions.tabs.ActionCloseTab;
import actions.tabs.ActionFirstTab;
import actions.tabs.ActionLastTab;
import actions.tabs.ActionNextTab;
import actions.tabs.ActionPrevTab;
import application.Application;

/**
 * Extension of {@link JMenu} class representing the window menu. It contains
 * navigation ({@link ActionFirstTab}, {@link ActionPrevTab},
 * {@link ActionNextTab}, {@link ActionLastTab}, {@link ActionFirstSubTab},
 * {@link ActionPrevSubTab}, {@link ActionNextSubTab}, {@link ActionLastSubTab})
 * and closing ({@link ActionCloseAllTabs}, {@link ActionCloseTab}) actions.
 * 
 * @author Milan Radeta
 * 
 */
public class MenuWindow extends JMenu {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "Window";

	/**
	 * Constructor of {@link MenuWindow} class. Creates a menu with necessary
	 * actions.
	 */
	public MenuWindow() {
		super(Application.getResourceBundle().getString(NAME));
		setMnemonic(KeyEvent.VK_W);
		add(ActionFirstTab.getInstance());
		add(ActionPrevTab.getInstance());
		add(ActionNextTab.getInstance());
		add(ActionLastTab.getInstance());
		addSeparator();
		add(ActionCloseTab.getInstance());
		add(ActionCloseAllTabs.getInstance());
		addSeparator();
		add(ActionFirstSubTab.getInstance());
		add(ActionPrevSubTab.getInstance());
		add(ActionNextSubTab.getInstance());
		add(ActionLastSubTab.getInstance());
	}

}