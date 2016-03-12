package listeners.tab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import actions.manager.ActionsManager;
import gui.tab.TabCloseButton;
import gui.tables.TabbedPane;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Implementation of {@link ActionListener} interface.
 * Used by the {@link TabCloseButton} to determine which tab has been closed.
 * 
 * @author Milan Radeta
 * @see TabCloseButtonMouseListener
 */
public class TabCloseButtonActionListener implements ActionListener {

	private TabbedPane tabbedPane;
	private TreeNode node;
	
	/**
	 * Constructor of the {@link TabCloseButtonActionListener} class.
	 * Parameter {@code tabbedPane} takes a reference to the {@link TabbedPane} object containing the tab.
	 * Parameter {@code node} takes a reference to the {@link TreeNode} object which this tab represents.
	 * 
	 * @param tabbedPane
	 * @param node
	 * @see Tree
	 */
	public TabCloseButtonActionListener(TabbedPane tabbedPane, TreeNode node) {
		this.tabbedPane = tabbedPane;
		this.node = node;
	}
	
	/**
	 * Event handler method of {@link TabCloseButtonActionListener}.
	 * When {@link TabCloseButton} is pressed, it finds which tab it belongs to, and removes it form the {@link TabbedPane}.
	 * After that it updates which actions are enabled based on the number of remaining tabs and newly selected tab.
	 *  
	 * @see ActionsManager
	 */
	@Override
    public void actionPerformed(ActionEvent e) {
		int index = tabbedPane.getOpenedNodes().indexOf(node);
        if (index >= 0) {
        	tabbedPane.remove(index);
        }
        ActionsManager.updateTabActions();
		ActionsManager.updateTreeActions();
        
    }

}
