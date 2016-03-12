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
 * Singleton class that extends {@link AbstractAction}. It collapses all
 * {@link TreeNode}s in the {@link MainFrame}'s {@link Tree}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see MainFrame
 * @see Tree
 *
 */
public class ActionCollapseAll extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionCollapseAll} object.
	 */
	private static ActionCollapseAll instance = null;

	/**
	 * Returns the only instance of {@link ActionCollapseAll} object. If it does not
	 * exist, it will be created.
	 * 
	 * @return {@link ActionCollapseAll}
	 */
	public static ActionCollapseAll getInstance() {
		if (instance == null) {
			instance = new ActionCollapseAll();
		}
		return instance;
	}

	/**
	 * {@link ActionCollapseAll} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Ctrl + Shift + P).
	 * 
	 */
	private ActionCollapseAll() {
		putValue(NAME, Application.getResourceBundle().getString("CollapseAll"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/home.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("CollapseAllDesc"));
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
	}

	/**
	 * Collapses all {@link TreeNode}s in {@link MainFrame}'s {@link Tree}.
	 * 
	 * @see TreeNode
	 * @see MainFrame
	 * @see Tree
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("CollapseAllStart"));

		ArrayList<TreeNode> offsprings = ((TreeNode) Tree.getInstance().getModel().getRoot()).getOffspringNodes();
		for (int i = offsprings.size() - 1; i >= 0; i--) {
			Tree.getInstance().collapsePath(new TreePath(offsprings.get(i).getPath()));
		}
		Tree.getInstance().collapseRow(0);
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("CollapseAllFinish"));
	}

}