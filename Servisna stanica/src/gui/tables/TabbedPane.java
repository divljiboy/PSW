package gui.tables;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import com.fasterxml.jackson.databind.JsonNode;

import actions.manager.ActionsManager;
import application.Application;
import gui.tab.ButtonTabComponent;
import listeners.tab.TabChangeListener;
import listeners.tab.TabMouseListener;
import model.TableModel;
import model.TreeNode;

/**
 * Extension of {@link JTabbedPane} class. Contains tabs which hold opened
 * tables.
 * 
 * @author Ivan Divljak
 *
 */
public class TabbedPane extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	private ArrayList<TreeNode> openedNodes = new ArrayList<TreeNode>();
	private TabChangeListener tabChangeListener;
	private TabMouseListener tabMouseListener;
	private static TabbedPane lastSelectedTabbedPane = null;

	private boolean isMainTabbedPane;

	private static TabbedPane mainTabbedPane;

	/**
	 * Constructor of the {@link TabbedPane} class. Parameters
	 * {@code parentNode} and {@code parentTableModel} are {@link TreeNode} and
	 * {@link TableModel} used for making {@link TabbedPane} with child tables.
	 * 
	 * @param parentNode
	 * @param parentTableModel
	 */
	public TabbedPane(TreeNode parentNode, TableModel parentTableModel) {
		this(false);
		for (int i = 0; i < parentNode.getChildCount(); i++) {
			TreeNode childNode = (TreeNode) parentNode.getChildAt(i);
			createTabIfNotExists(childNode, parentTableModel);
		}
		setSelectedIndex(0);
	}

	/**
	 * Constructor of {@link TabbedPane} class. Parameter {@code isTabbedPane}
	 * determines whether this {@code TabbedPane} will be created as a parent or
	 * as a child {@code TabbedPane}.
	 * 
	 * @param isMainTabbedPane
	 */
	public TabbedPane(boolean isMainTabbedPane) {
		super();
		this.isMainTabbedPane = isMainTabbedPane;
		setTabChangeListener(new TabChangeListener(this));
		addChangeListener(tabChangeListener);
		setTabMouseListener(new TabMouseListener(this));
		addMouseListener(tabMouseListener);
		if (isMainTabbedPane) {
			setMainTabbedPane(this);
		}

	}

	/**
	 * Returns a list of all opened nodes (tables).
	 * 
	 * @return {@link ArrayList}
	 */
	public ArrayList<TreeNode> getOpenedNodes() {
		return openedNodes;
	}

	/**
	 * Sets an {@link ArrayList} of all opened nodes (tables).
	 * 
	 * @param openedNodes
	 */
	public void setOpenedNodes(ArrayList<TreeNode> openedNodes) {
		this.openedNodes = openedNodes;
	}

	/**
	 * Given the {@link TreeNode} and {@link TableModel} of the parent table, it
	 * creates a tab with the corresponding {@link Table} if one does not
	 * already exist. In that case, the already opened tab is selected.
	 * 
	 * @param node
	 * @param parentTableModel
	 */
	public void createTabIfNotExists(TreeNode node, TableModel parentTableModel) {
		for (int i = 0; i < openedNodes.size(); i++) {
			if (openedNodes.get(i) == node) {
				setSelectedIndex(i);
				return;
			}
		}

		JsonNode jsonNode = node.getNode();

		openedNodes.add(node);
		addTab(Application.getResourceBundle().getString("Table_" + jsonNode.get("code").asText().toUpperCase()), null,
				TablesPane.createTablesPane(this, node, parentTableModel));

		int index = getTabCount() - 1;
		if (this == mainTabbedPane) {
			setTabComponentAt(index, new ButtonTabComponent(this, node));
			ActionsManager.updateTabActions();
		}
		setSelectedIndex(index);

	}

	/**
	 * Returns the {@link TabChangeListener} of {@link TabbedPane}.
	 * 
	 * @return {@link TabChangeListener}
	 */
	public TabChangeListener getTabChangeListener() {
		return tabChangeListener;
	}

	/**
	 * Sets the {@link TabChangeListener} of {@link TabbedPane}.
	 * 
	 * @param tabChangeListener
	 */
	public void setTabChangeListener(TabChangeListener tabChangeListener) {
		this.tabChangeListener = tabChangeListener;
	}

	/**
	 * Returns the {@link TabMouseListener} of {@link TabbedPane}.
	 * 
	 * @return {@link TabMouseListener}
	 */
	public TabMouseListener getTabMouseListener() {
		return tabMouseListener;
	}

	/**
	 * Sets the {@link TabMouseListener} of {@link TabbedPane}.
	 * 
	 * @param tabMouseListener
	 */
	public void setTabMouseListener(TabMouseListener tabMouseListener) {
		this.tabMouseListener = tabMouseListener;
	}

	/**
	 * Returns {@code true} if this {@link TabbedPane} is a main (parent)
	 * {@code TabbedPane}.
	 * 
	 * @return boolean
	 */
	public boolean isMainTabbedPane() {
		return isMainTabbedPane;
	}

	/**
	 * Sets ({@code true} or {@code false}) whether this {@link TabbedPane} is a
	 * main (parent) {@code TabbedPane}.
	 * 
	 * @param isMainTabbedPane
	 */
	public void setMainTabbedPane(boolean isMainTabbedPane) {
		this.isMainTabbedPane = isMainTabbedPane;
	}

	/**
	 * Returns the main (parent) {@link TabbedPane}.
	 * 
	 * @return {@link TabbedPane}
	 */
	public static TabbedPane getMainTabbedPane() {
		return mainTabbedPane;
	}

	/**
	 * Sets the main (parent) {@link TabbedPane}.
	 * 
	 * @param mainTabbedPane
	 */
	public static void setMainTabbedPane(TabbedPane mainTabbedPane) {
		TabbedPane.mainTabbedPane = mainTabbedPane;
	}

	/**
	 * Returns the last selected {@link TabbedPane} (main, or one of the
	 * children).
	 * 
	 * @return {@link TabbedPane}
	 */
	public static TabbedPane getLastSelectedTabbedPane() {
		return lastSelectedTabbedPane;
	}

	/**
	 * Sets the last selected {@link TabbedPane} (main, or one of the children).
	 * 
	 * @param tabbedPane
	 */
	public static void setLastSelectedTabbedPane(TabbedPane tabbedPane) {
		TabbedPane.lastSelectedTabbedPane = tabbedPane;
		if (tabbedPane.getTabCount() > 0 && tabbedPane.getSelectedComponent() != null) {
			((TablesPane) tabbedPane.getSelectedComponent()).getTablePanel().getTable().requestFocusInWindow();
		} else {
			Table.setFocusedTable(null);
		}
	}

	/**
	 * Overriden {@link JTabbedPane#remove(int)} method. It additionally removes
	 * linked nodes from {@link #openedNodes} and {@link TableModel}s from
	 * opened table models.
	 */
	@Override
	public void remove(int index) {
		super.remove(index);
		openedNodes.remove(index);
		TableModel.getOpenedTableModels().remove(index);
	}

	/**
	 * Overriden {@link JTabbedPane#removeAll()} method. It additionally clears
	 * {@link #openedNodes} list and opened table models list.
	 */
	@Override
	public void removeAll() {
		super.removeAll();
		openedNodes.clear();
		TableModel.getOpenedTableModels().clear();
	}

}
