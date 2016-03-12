package gui.toolbars;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JToolBar;

import actions.help.ActionAbout;
import actions.help.ActionUserManual;
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
import actions.tree.ActionCollapseAll;
import actions.tree.ActionCollapseNode;
import actions.tree.ActionExpandAll;
import actions.tree.ActionExpandNode;
import actions.tree.ActionOpenInTab;
import gui.MainFrame;

/**
 * Extension of {@link JToolBar}, which has specialized {@link AbstractAction}s
 * which represent shortcut icons to the ones that are in {@link MainFrame}'s
 * {@link JMenu}.
 * 
 * @see JToolBar
 * @see AbstractAction
 * @see MainFrame
 * @see JMenu
 * @see ActionOpenInTab
 * @see ActionExpandNode
 * @see ActionCollapseNode
 * @see ActionExpandAll
 * @see ActionCollapseAll
 * @see ActionFirstTab
 * @see ActionPrevTab
 * @see ActionNextTab
 * @see ActionLastTab
 * @see ActionCloseTab
 * @see ActionCloseAllTabs
 * @see ActionFirstSubTab
 * @see ActionPrevSubTab
 * @see ActionNextSubTab
 * @see ActionLastSubTab
 * @see ActionUserManual
 * @see ActionAbout
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 *
 */
public class MainToolbar extends JToolBar {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates {@link JToolBar} extension with {@link AbstractAction}s which
	 * represent shortcut icons to the ones that are in {@link MainFrame}'s
	 * {@link JMenu}.
	 * 
	 * 
	 * @see JToolBar
	 * @see AbstractAction
	 * @see MainFrame
	 * @see JMenu
	 * @see ActionOpenInTab
	 * @see ActionExpandNode
	 * @see ActionCollapseNode
	 * @see ActionExpandAll
	 * @see ActionCollapseAll
	 * @see ActionFirstTab
	 * @see ActionPrevTab
	 * @see ActionNextTab
	 * @see ActionLastTab
	 * @see ActionCloseTab
	 * @see ActionCloseAllTabs
	 * @see ActionFirstSubTab
	 * @see ActionPrevSubTab
	 * @see ActionNextSubTab
	 * @see ActionLastSubTab
	 * @see ActionUserManual
	 * @see ActionAbout
	 */
	public MainToolbar() {
		add(ActionOpenInTab.getInstance());
		add(ActionExpandNode.getInstance());
		add(ActionCollapseNode.getInstance());

		addSeparator();

		add(ActionExpandAll.getInstance());
		add(ActionCollapseAll.getInstance());

		addSeparator();

		add(ActionFirstTab.getInstance());
		add(ActionPrevTab.getInstance());
		add(ActionNextTab.getInstance());
		add(ActionLastTab.getInstance());
		add(ActionCloseTab.getInstance());
		add(ActionCloseAllTabs.getInstance());

		addSeparator();

		add(ActionFirstSubTab.getInstance());
		add(ActionPrevSubTab.getInstance());
		add(ActionNextSubTab.getInstance());
		add(ActionLastSubTab.getInstance());

		addSeparator();

		add(ActionUserManual.getInstance());
		add(ActionAbout.getInstance());

	}

}
