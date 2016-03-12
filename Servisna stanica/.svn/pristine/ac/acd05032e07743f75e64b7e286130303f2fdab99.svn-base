package listeners.tree;

import javax.swing.event.TreeExpansionEvent;

import actions.manager.ActionsManager;

/**
 * Implementation of {@link javax.swing.event.TreeExpansionListener} interface.
 * Listens for expansion and collapse events and updates enabled actions accordingly.
 * 
 * @author Milan Radeta
 *
 */
public class TreeExpansionListener implements javax.swing.event.TreeExpansionListener {

	/**
	 * Handler method for {@link TreeExpansionEvent} when the tree is expanded.
	 * Updates enabled actions.
	 */
	@Override
	public void treeCollapsed(TreeExpansionEvent event) {
		ActionsManager.updateTreeActions();
	}

	/**
	 * Handler method for {@link TreeExpansionEvent} when the tree is collapsed.
	 * Updates enabled actions.
	 */
	@Override
	public void treeExpanded(TreeExpansionEvent event) {
		ActionsManager.updateTreeActions();
	}

}
