package gui;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import gui.tables.MainPanel;
import gui.tree.Tree;

/**
 * Class representing the main split pane in the {@link MainFrame} which is consisted of {@link Tree} on the left side and {@link MainPanel} on the right.
 * 
 * @author Ivan Divljak
 * @author Borko Arsovic
 * @author Milan Radeta
 * @author Isidora Skulec
 */
public class MainSplitPane extends JSplitPane {
	private static final long serialVersionUID = 1L;

	private Tree tree;
	private MainPanel mainPanel;

	/**
	 * Constructor of the {@link MainSplitPane} class.
	 * Creates a new {@link MainPanel} and gets the instance of {@link Tree} class.
	 */
	public MainSplitPane() {
		this(Tree.getInstance(), new MainPanel());
	}

	/**
	 * Constructor of the {@link MainSplitPane} class.
	 * Parameter {@code tree} is the {@link Tree} object which will be presented in the left part of the pane.
	 * Parameter {@code mainPanel} is the {@link MainPanel} which will be presented on the right, and will show all tables.
	 * 
	 * @param tree
	 * @param mainPanel
	 */
	private MainSplitPane(Tree tree, MainPanel mainPanel) {
		super(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree), mainPanel);
		this.tree = tree;
		this.mainPanel = mainPanel;
		setDividerLocation(220);
	}

	/**
	 * Returns the {@link Tree} object from the {@link MainSplitPane}
	 * @return {@link Tree}
	 */
	public Tree getTree() {
		return tree;
	}

	/**
	 * Sets the {@link Tree} object to the {@link MainSplitPane}
	 * @param tree
	 */
	public void setTree(Tree tree) {
		this.tree = tree;
	}

	/**
	 * Returns the {@link MainPanel} from the {@link MainSplitPane}.
	 * @return {@link MainPanel}
	 */
	public MainPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Sets the {@link MainPanel} to the {@link MainSplitPane}.
	 * @param mainPanel
	 */
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

}
