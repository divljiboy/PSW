package gui.tree;

import javax.swing.JTree;

import listeners.tree.TreeExpansionListener;
import listeners.tree.TreeKeyListener;
import listeners.tree.TreeMouseListener;
import listeners.tree.TreeSelectionListener;
import model.TreeModel;
import renderers.TreeRenderer;

/**
 * Singleton extension of {@link JTree}, which has specialized
 * {@link TreeModel}, {@link TreeRenderer}, {@link TreeMouseListener},
 * {@link TreeKeyListener}, {@link TreeSelectionListener} and
 * {@link TreeExpansionListener}.
 * 
 * @see TreeModel
 * @see TreeRenderer
 * @see TreeMouseListener
 * @see TreeKeyListener
 * @see TreeSelectionListener
 * @see TreeExpansionListener
 * @author Milan Radeta
 * @author Isidora Škulec
 * @author Ivan Divljak
 *
 */
public class Tree extends JTree {

	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link Tree} class.
	 */
	private static Tree instance = null;

	/**
	 * Private {@link Tree} constructor which creates its own specialized
	 * {@link TreeModel}, {@link TreeMouseListener}, {@link TreeRenderer},
	 * {@link TreeKeyListener}, {@link TreeSelectionListener} and
	 * {@link TreeExpansionListener}. It shows root handles and initially
	 * expands second row.
	 * 
	 * @see TreeModel
	 * @see TreeRenderer
	 * @see TreeMouseListener
	 * @see TreeKeyListener
	 * @see TreeSelectionListener
	 * @see TreeExpansionListener
	 */
	private Tree() {
		super(TreeModel.getTreeModel());
		addMouseListener(new TreeMouseListener());
		setCellRenderer(new TreeRenderer());
		expandRow(1);
		setShowsRootHandles(true);
		addKeyListener(new TreeKeyListener());
		addTreeSelectionListener(new TreeSelectionListener());
		addTreeExpansionListener(new TreeExpansionListener());
	}

	/**
	 * Returns the only instance of {@link Tree} object. If it does not exist,
	 * it will be created.
	 * 
	 * @return {@link Tree}
	 */
	public static Tree getInstance() {
		if (instance == null) {
			instance = new Tree();
		}
		return instance;
	}

}
