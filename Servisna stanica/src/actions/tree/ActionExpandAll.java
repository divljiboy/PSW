package actions.tree;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import application.Application;
import gui.MainFrame;
import gui.tree.Tree;
import model.TreeNode;

public class ActionExpandAll extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionExpandAll} object.
	 */
	private static ActionExpandAll instance = null;

	/**
	 * Returns the only instance of {@link ActionExpandAll} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionExpandAll}
	 */
	public static ActionExpandAll getInstance() {
		if (instance == null) {
			instance = new ActionExpandAll();
		}
		return instance;
	}

	/**
	 * {@link ActionExpandAll} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Ctrl + Shift + O).
	 * 
	 */
	private ActionExpandAll() {
		putValue(NAME, Application.getResourceBundle().getString("ExpandAll"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/expandAll.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("ExpandAllDesc"));
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
	}

	/**
	 * Expands all {@link TreeNode}s in {@link MainFrame}'s {@link Tree}.
	 * 
	 * @see TreeNode
	 * @see MainFrame
	 * @see Tree
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("ExpandAllStart"));

		for (TreeNode node : ((TreeNode) Tree.getInstance().getModel().getRoot()).getOffspringNodes()) {
			Tree.getInstance().expandPath(new TreePath(node.getPath()));
		}
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("ExpandAllFinish"));
	}

}