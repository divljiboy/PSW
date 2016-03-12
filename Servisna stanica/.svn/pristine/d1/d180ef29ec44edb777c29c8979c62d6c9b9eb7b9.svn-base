package gui.popups;

import javax.swing.JPopupMenu;

import actions.tree.ActionCollapseAll;
import actions.tree.ActionCollapseNode;
import actions.tree.ActionExpandAll;
import actions.tree.ActionExpandNode;
import actions.tree.ActionOpenInTab;

/**
 * Extension of the {@link JPopupMenu} class. Implements the singleton pattern.
 * Contains actions for manipulating tree nodes ({@link ActionOpenInTab}, {@link ActionExpandNode}, {@link ActionCollapseNode}, {@link ActionExpandAll}, {@link ActionCollapseAll}).  
 * @author Milan Radeta
 * 
 */
public class TreePopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	
	private static TreePopupMenu instance;
	
	/**
	 * Private constructor of {@link TreePopupMenu} class.
	 * Adds instances of necessary actions to the menu.
	 */
	private TreePopupMenu() {
		add(ActionOpenInTab.getInstance());
		add(ActionExpandNode.getInstance());
		add(ActionCollapseNode.getInstance());
		addSeparator();
		add(ActionExpandAll.getInstance());
		add(ActionCollapseAll.getInstance());
	}
	
	/**
	 * Returns the instance of {@link TreePopupMenu}.
	 * If there is none, new one is created.
	 * @return {@link TreePopupMenu}
	 */
	public static TreePopupMenu getInstance() {
		if (instance == null) {
			instance = new TreePopupMenu();
		}
		return instance;
	}
}
