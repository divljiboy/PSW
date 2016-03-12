package gui.tables;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.toolbars.TableToolbar;
import gui.tree.Tree;
import model.TableModel;
import model.TreeNode;

/**
 * Extension of {@link JPanel} class.
 * A panel that contains a {@link Table} object and its {@link TableToolbar}.
 * @author Milan Radeta
 *
 */
public class TablePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Table table;
	private RowNumberTable rowNumberTable;
	private TableToolbar toolbar;

	/**
	 * Constructor of {@link TablePanel} class.
	 * Parameter {@code node} is a {@link TreeNode} representing the table in the {@link Tree}.
	 * Parameter {@code parentTableModel} is a {@link TableModel} of the table.
	 * 
	 * @param node
	 * @param parentTableModel
	 */
	public TablePanel(TreeNode node, TableModel parentTableModel) {
		super(new BorderLayout());
		table = new Table(this, node, parentTableModel);
		toolbar = new TableToolbar(table);

		JScrollPane scrollPane = new JScrollPane(table);
		rowNumberTable = new RowNumberTable(table, scrollPane);

		table.setFillsViewportHeight(true);
		add(toolbar, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * Returns the {@link Table} in this panel.
	 * @return {@link Table}
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Sets the {@link Table} in this panel.
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * Returns the {@link TableToolbar} in this panel.
	 * @return {@link TableToolbar}
	 */
	public TableToolbar getToolbar() {
		return toolbar;
	}

	/**
	 * Sets the {@link TableToolbar} in this panel.
	 * @param toolbar
	 */
	public void setToolbar(TableToolbar toolbar) {
		this.toolbar = toolbar;
	}
	/**
	 * Returns the {@link RowNumberTable} in this panel.
	 * @return {@link RowNumberTable}
	 */
	public RowNumberTable getRowNumberTable() {
		return rowNumberTable;
	}

	/**
	 * Sets the {@link RowNumberTable} in this panel.
	 * @param rowNumberTable
	 */
	public void setRowNumberTable(RowNumberTable rowNumberTable) {
		this.rowNumberTable = rowNumberTable;
	}

}
