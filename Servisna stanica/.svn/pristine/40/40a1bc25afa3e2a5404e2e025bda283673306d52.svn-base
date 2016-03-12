package listeners.tree;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.tree.TreePath;

import com.fasterxml.jackson.databind.JsonNode;

import application.Application;
import gui.MainFrame;
import gui.tables.TabbedPane;
import gui.tables.Table;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Implementation of {@link KeyListener} interface.
 * Opens a table if {@code carriage return} ({@code Enter}) button is pressed.
 * 
 * @author Milan Radeta
 *
 */
public class TreeKeyListener implements KeyListener {

	/**
	 * Handler method for {@link KeyEvent}.
	 * If {@code carriage return} ({@code Enter}) button is pressed, {@link Table} represented by the selected {@link TreeNode} in the {@link Tree} will be open in a separate tab in the {@link TabbedPane}.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			TreePath selPath = Tree.getInstance().getSelectionPath();
			if (selPath != null) {
				TreeNode selectedNode = (TreeNode) selPath.getLastPathComponent();
				JsonNode jsonNode = selectedNode.getNode().get("type");
				Tree.getInstance().expandPath(selPath);
				if (jsonNode != null) {
					switch (jsonNode.asText()) {
						case "table":
							MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("OpenInTabStart"));
							TabbedPane tabbedPane = (TabbedPane) MainFrame.getInstance().getMainSplitPane().getMainPanel().getTabbedPane();
							tabbedPane.createTabIfNotExists(selectedNode, null);
							MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("OpenInTabFinish"));
							break;
					}
					
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
