package gui.tables;

import javax.swing.JSplitPane;

import model.TableModel;
import model.TreeNode;

/**
 * Extension of {@link JSplitPane} class.
 * It splits the tab of the {@link TabbedPane} if a table has children.
 * Top half is the parent table, bottom half is a {@code TabbedPane} with child tables.
 * 
 * @author Milan Radeta
 *
 */
public class TablesPane extends JSplitPane {
	private static final long serialVersionUID = 1L;

	private TabbedPane tabbedPane = null;
	private TablePanel tablePanel = null;
	
	/**
	 * Constructor of {@link TablesPane}.
	 * Given a {@link TablePanel} it puts it in the top half and puts nothing (null) in the bottom.
	 * This is for tables without children.
	 * @param tablePanel
	 */
	private TablesPane(TablePanel tablePanel) {
		super(JSplitPane.VERTICAL_SPLIT, tablePanel, null);
		this.tablePanel = tablePanel;
		
	}
	
	/**
	 * Constructor of {@link TablesPane}.
	 * Given two {@link TablePanel} objects it puts first in the top half and second in the bottom.
	 * This is for tables with children.
	 * 
	 * @param tablePanel
	 * @param childTablePanel
	 */
	private TablesPane(TablePanel tablePanel, TablePanel childTablePanel) {
		super(JSplitPane.VERTICAL_SPLIT, tablePanel, childTablePanel);
		TableModel parentModel = tablePanel.getTable().getTableModel();
		parentModel.getChildTableModels().add(childTablePanel.getTable().getTableModel());
		setDividerLocation(400);
		this.tablePanel = tablePanel;
	}

	/**
	 * Constructor of {@link TablesPane}.
	 * Given a {@link TablePanel} and a {@link TabbedPane} it puts first in the top half and second in the bottom.
	 * @param tablePanel
	 * @param pane
	 */
	private TablesPane(TablePanel tablePanel, TabbedPane pane) {
		super(JSplitPane.VERTICAL_SPLIT, tablePanel, pane);
		tabbedPane = pane;
		TableModel parentModel = tablePanel.getTable().getTableModel();
		for (int i = 0; i < pane.getTabCount(); i++) {
			TablePanel panel = (TablePanel) ((TablesPane) pane.getComponentAt(i)).getComponent(0);
			parentModel.getChildTableModels().add(panel.getTable().getTableModel());
		}
		setDividerLocation(400);
		this.tablePanel = tablePanel;
	}
	
	/**
	 * Creates a {@link TablesPane} from a {@link TabbedPane}, a {@link TreeNode} and a {@link TableModel} representing the parent table.
	 * @param tabbedPane
	 * @param node
	 * @param parentTableModel
	 * @return {@link TablesPane}
	 */
	public static TablesPane createTablesPane(TabbedPane tabbedPane, TreeNode node, TableModel parentTableModel) {
		if (tabbedPane != TabbedPane.getMainTabbedPane()) {
			return new TablesPane(new TablePanel(node, parentTableModel));
		}
		switch (node.getChildCount()) {
			case 0:
				return new TablesPane(new TablePanel(node, parentTableModel));
			/*case 1:
				TablePanel parentPanel = new TablePanel(node, parentTableModel); 
				return new TablesPane(parentPanel, new TablePanel((TreeNode) node.getChildAt(0), parentPanel.getTable().getTableModel()));*/
			default:
				TablePanel parentPanel = new TablePanel(node, parentTableModel); 
				return new TablesPane(parentPanel, new TabbedPane(node, parentPanel.getTable().getTableModel()));
		}
	}
	
	/**
	 * Returns the bottom {@link TabbedPane} from this {@link TablesPane}.
	 * @return {@link TabbedPane}
	 */
	public TabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Returns the top {@link TablePanel} from this {@link TablesPane}.
	 * @return {@link TablePanel}
	 */
	public TablePanel getTablePanel() {
		return tablePanel;
	}

	/**
	 * Sets the top {@link TablePanel} from this {@link TablesPane}.
	 * @param tablePanel
	 */
	public void setTablePanel(TablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}
	
}
