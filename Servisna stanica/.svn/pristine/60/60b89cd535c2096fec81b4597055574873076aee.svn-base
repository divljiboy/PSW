package listeners.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.tree.TreePath;

import com.fasterxml.jackson.databind.JsonNode;

import application.Application;
import gui.MainFrame;
import gui.popups.TreePopupMenu;
import gui.tables.TabbedPane;
import gui.tables.Table;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Implementation of {@link MouseListener} interface.
 * Opens a table if a {@link TreeNode} is double-clicked.
 * Open a popup menu if the event is  trigger.  
 * 
 * @author Milan Radeta
 * @author Ivan Divljak
 */
public class TreeMouseListener implements MouseListener {
	/**
	 * Handler method for {@link MouseEvent}
	 * When a {@link TreeNode} is double-clicked, {@link Table} represented by the selected {@code TreeNode} in the {@link Tree} will be open in a separate tab in the {@link TabbedPane}.
	 * If the source was a trigger, popup menu will be shown.
	 * 
	 */
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount() == 2 && !arg0.isConsumed()) {
			TreePath selPath = Tree.getInstance().getSelectionPath();
			if (selPath != null) {
				TreeNode selectedNode = (TreeNode) selPath.getLastPathComponent();
				JsonNode jsonNode = selectedNode.getNode().get("type");
				if (jsonNode != null && jsonNode.asText().equals("table")) {
					MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("OpenInTabStart"));
					TabbedPane tabbedPane = (TabbedPane) MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
					tabbedPane.createTabIfNotExists(selectedNode, null);
					MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("OpenInTabFinish"));
				}
			}
		}
		if (arg0.isPopupTrigger()) {
			TreePopupMenu.getInstance().show(arg0.getComponent(), arg0.getX(), arg0.getY());
		}
		
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Handler method for {@link MouseEvent} when mouse button is pressed on a {@link TreeNode}.
	 * The {@code TreeNode} will be set as the selected one. 
	 * If the source was a trigger, popup menu will be shown.
	 * 
	 */
	public void mousePressed(MouseEvent arg0) {
		if (arg0.isPopupTrigger()) {
			Tree tree = Tree.getInstance();
			tree.setSelectionPath(tree.getPathForLocation(arg0.getX (), arg0.getY()));
			TreePopupMenu.getInstance().show(arg0.getComponent(), arg0.getX(), arg0.getY());
		}
	}

	/**
	 * Handler method for {@link MouseEvent} when mouse button is released on a {@link TreeNode}.
	 * The {@code TreeNode} will be set as the selected one. 
	 * If the source was a trigger, popup menu will be shown.
	 * 
	 */
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.isPopupTrigger()) {
			Tree tree = Tree.getInstance();
			tree.setSelectionPath(tree.getPathForLocation(arg0.getX (), arg0.getY()));
			TreePopupMenu.getInstance().show(arg0.getComponent(), arg0.getX(), arg0.getY());
		}
	}

}
