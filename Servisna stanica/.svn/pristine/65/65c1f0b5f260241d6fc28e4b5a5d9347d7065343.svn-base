package gui.tables;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import actions.manager.ActionsManager;
import gui.toolbars.TableToolbar;
import gui.tree.Tree;
import listeners.table.TableColumnAdjuster;
import listeners.table.TableFocusListener;
import listeners.table.TableMouseListener;
import listeners.table.TableSelectionListener;
import model.TableModel;
import model.TreeNode;

/**
 * Extension of {@link JTable} class.
 * Used for showing rows from a database table to the user.
 * 
 * @author Borko Arsović
 *
 */
public class Table extends JTable {
	private static final long serialVersionUID = 1L;

	private TreeNode node;
	private TableModel tableModel;
	private TableSelectionListener listener;
	private TableColumnAdjuster tableColumnAdjuster;
	private static Table focusedTable = null;

	/**
	 * Empty constructor.
	 */
	public Table() {}
	
	/**
	 * Constructor of the {@link Table} class.
	 * Parameter {@code tablePanel} takes a {@link TablePanel} in which the table will be.
	 * Parameter {@code node} takes a {@link TreeNode} which represents this table in the {@link Tree}.
	 * Parameter {@code parentTableModel} takes the {@link TableModel} of the parent table (if it has any) or null. 
	 * 
	 * @param tablePanel
	 * @param node
	 * @param parentTableModel
	 */
	public Table(TablePanel tablePanel, TreeNode node, TableModel parentTableModel) {
		super();
		addFocusListener(new TableFocusListener(this));
		this.node = node;
		tableModel = new TableModel(node, this, parentTableModel);
		setModel(tableModel);
		listener = new TableSelectionListener(this);
		getSelectionModel().addListSelectionListener(listener);
		addMouseListener(new TableMouseListener(this));		
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAutoCreateRowSorter(true);
		/*Autoresize se koristi kako bi se kolone namestile proporcionalno,
		a ne da poslednja kolona bude najduza*/
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setTableColumnAdjuster(new TableColumnAdjuster(this));
		tableColumnAdjuster.adjustColumns();
	}
	
	/**
	 * Returns the {@link TreeNode} representing this table in the {@link Tree}.
	 * @return {@link TreeNode}
	 */
	public TreeNode getNode() {
		return node;
	}

	/**
	 * Returns the {@link TableModel} of this table.
	 * @return {@link TableModel}
	 */
	public TableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Sets the {@link TableModel} of this table.
	 * @param model
	 * @see TableModel
	 */
	public void setTableModel(TableModel model) {
		this.tableModel = model;
	}

	/**
	 * Returns the currently focused (active) table.
	 * @return {@link Table}
	 */
	public static Table getFocusedTable() {
		return focusedTable;
	}

	/**
	 * Sets the currently focused (active) table.
	 * @param focusedTable
	 */
	public static void setFocusedTable(Table focusedTable) {
		Table.focusedTable = focusedTable;
		if (focusedTable != null) {
			//focusedTable.getTableModel().refreshData(TableModel.NONE);
		}
		ActionsManager.updateRecordActions();
	}

	/**
	 * Returns the {@link TableColumnAdjuster} of this table.
	 * @return {@link TableColumnAdjuster}
	 */
	public TableColumnAdjuster getTableColumnAdjuster() {
		return tableColumnAdjuster;
	}

	/**
	 * Sets the {@link TableColumnAdjuster} of this table.
	 * @param tableColumnAdjuster
	 */
	public void setTableColumnAdjuster(TableColumnAdjuster tableColumnAdjuster) {
		this.tableColumnAdjuster = tableColumnAdjuster;
	}

	/**
	 * Sets the currently focused (active) table based on {@link ActionEvent}'s source.
	 * If e.getSource() is a {@link JComponent} whose parent is {@link TableToolbar}
	 * it sets focus to its {@link Table}.
	 * @param e
	 */
	public static void setFocusedTableViaAction(ActionEvent e) {
		JComponent componentSource = (JComponent) e.getSource();
		if (componentSource.getParent() instanceof TableToolbar) {
			Table.setFocusedTable(((TableToolbar) componentSource.getParent()).getTable());
		}
	}
	
}
