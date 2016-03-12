package listeners.tree;

import javax.swing.event.TreeSelectionEvent;

import actions.manager.ActionsManager;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Implementation of {@link javax.swing.event.TreeSelectionListener} interface.
 * Updates enabled actions depending on selected {@link TreeNode}.
 * 
 * @author Milan Radeta
 * @see Tree
 */
public class TreeSelectionListener implements javax.swing.event.TreeSelectionListener {

	/**
	 * Handler method for {@link TreeSelectionEvent}.
	 * Updates enabled actions depending on selected {@link TreeNode}.
	 * 
	 * @see Tree 
	 */
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		ActionsManager.updateTreeActions();
	}

}
