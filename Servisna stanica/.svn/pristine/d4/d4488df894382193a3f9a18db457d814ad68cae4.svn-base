package listeners.table;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.tables.Table;
import model.TableModel;

/**
 * Implementation of {@link ListSelectionListener} interface.
 * 
 * @author Milan Radeta
 *
 */
public class TableSelectionListener implements ListSelectionListener {

	private Table table;

	/**
	 * Constructor of {@link TableSelectionListener}. Parameter {@code table} is
	 * a reference to the {@link Table} object this listener belongs to.
	 * 
	 * @param table
	 */
	public TableSelectionListener(Table table) {
		this.setTable(table);
	}

	/**
	 * Implemented method used for refreshing child {@link TableModel}s, if they
	 * exist.
	 */
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (table.getTableModel().getChildTableModels().size() > 0) {
			for (TableModel child : table.getTableModel().getChildTableModels()) {
				child.getConditions().clear();
				child.getConditionValues().clear();
				child.refreshData(TableModel.NONE);
			}
		}
	}

	/**
	 * Getter for {@link Table} referenced in {@link TableSelectionListener}.
	 * 
	 * @return {@link Table}
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Setter for {@link Table} referenced in {@link TableSelectionListener}.
	 * 
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

}
