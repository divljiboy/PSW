package actions.tree;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import com.fasterxml.jackson.databind.JsonNode;

import actions.manager.ActionsManager;
import application.Application;
import gui.MainFrame;
import gui.tables.TabbedPane;
import gui.tables.TablesPane;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Singleton class that extends {@link AbstractAction}. It opens currently
 * selected {@link TreeNode}s in the {@link MainFrame}'s {@link Tree} in new or
 * already existing tab in main {@link TabbedPane} as {@link TablesPane}, if its
 * type is table.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see MainFrame
 * @see Tree
 * @see TabbedPane
 * @see TablesPane
 *
 */
public class ActionOpenInTab extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionOpenInTab} object.
	 */
	private static ActionOpenInTab instance = null;

	/**
	 * Returns the only instance of {@link ActionOpenInTab} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionOpenInTab}
	 */
	public static ActionOpenInTab getInstance() {
		if (instance == null) {
			instance = new ActionOpenInTab();
		}
		return instance;
	}

	/**
	 * {@link ActionOpenInTab} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Ctrl + ENTER).
	 * 
	 */
	private ActionOpenInTab() {
		putValue(NAME, Application.getResourceBundle().getString("OpenInTab"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/open.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("OpenInTabDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_MASK));
	}

	/**
	 * Opens currently selected {@link TreeNode}s in the {@link MainFrame}'s
	 * {@link Tree} in new or already existing tab in main {@link TabbedPane} as
	 * {@link TablesPane}, if its type is table.
	 * 
	 * @see MainFrame
	 * @see Tree
	 * @see TabbedPane
	 * @see TablesPane
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("OpenInTabStart"));

		TreePath selPath = MainFrame.getInstance().getMainSplitPane().getTree().getSelectionPath();
		if (selPath != null) {
			TreeNode selectedNode = (TreeNode) selPath.getLastPathComponent();
			JsonNode jsonNode = selectedNode.getNode().get("type");
			if (jsonNode != null && jsonNode.asText().equals("table")) {
				TabbedPane.getMainTabbedPane().createTabIfNotExists(selectedNode, null);
			}
		}
		ActionsManager.updateTreeActions();
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("OpenInTabFinish"));
	}
}
