package actions.tree;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import application.Application;
import gui.MainFrame;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Singleton class that extends {@link AbstractAction}. It expands the
 * currently selected {@link TreeNode}s in the {@link MainFrame}'s {@link Tree}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see MainFrame
 * @see Tree
 *
 */
public class ActionExpandNode extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionExpandNode} object.
	 */
	private static ActionExpandNode instance = null;

	/**
	 * Returns the only instance of {@link ActionExpandNode} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionExpandNode}
	 */
	public static ActionExpandNode getInstance() {
		if (instance == null) {
			instance = new ActionExpandNode();
		}
		return instance;
	}

	/**
	 * {@link ActionExpandNode} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Ctrl + O).
	 * 
	 */
	private ActionExpandNode() {
		putValue(NAME, Application.getResourceBundle().getString("ExpandNode"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/expand.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("ExpandNode"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
	}

	/**
	 * Expands the currently selected {@link TreeNode}s in {@link MainFrame}'s
	 * {@link Tree}.
	 * 
	 * @see TreeNode
	 * @see MainFrame
	 * @see Tree
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("ExpandNodeStart"));

		Tree tree = Tree.getInstance();
		TreePath selPath = tree.getSelectionPath();
		TreeNode selNode = (TreeNode) selPath.getLastPathComponent();
		ArrayList<TreeNode> offsprings = selNode.getOffspringNodes();

		tree.expandPath(new TreePath(selNode.getPath()));

		for (int i = offsprings.size() - 1; i >= 0; i--) {
			tree.expandPath(new TreePath(offsprings.get(i).getPath()));
		}
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("ExpandNodeFinish"));
	}

}