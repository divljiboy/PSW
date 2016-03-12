package listeners.table;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * Class to manage the widths of columns in a table.
 *
 * Various properties control how the width of the column is calculated. Another
 * property controls whether column width calculation should be dynamic.
 * Finally, various Actions will be added to the table to allow the user to
 * customize the functionality.
 *
 * This class was designed to be used with tables that use an auto resize mode
 * of AUTO_RESIZE_OFF. With all other modes you are constrained as the width of
 * the columns must fit inside the table. So if you increase one column, one or
 * more of the other columns must decrease. Because of this the resize mode of
 * RESIZE_ALL_COLUMNS will work the best.
 */
public class TableColumnAdjuster implements PropertyChangeListener, TableModelListener {
	private JTable table;

	private int spacing;
	private boolean isColumnHeaderIncluded;
	private boolean isColumnDataIncluded;
	private boolean isOnlyAdjustLarger;
	private boolean isDynamicAdjustment;
	private Map<TableColumn, Integer> columnSizes = new HashMap<TableColumn, Integer>();

	/**
	 * Constructor that sets reference to {@link JTable}.
	 * 
	 * @param table
	 */
	public TableColumnAdjuster(JTable table) {
		this(table, 6);
	}

	/**
	 * Constructor that sets reference to {@link JTable} and spacing.
	 * 
	 * @param table
	 * @param spacing
	 */
	public TableColumnAdjuster(JTable table, int spacing) {
		this.table = table;
		this.spacing = spacing;
		setColumnHeaderIncluded(true);
		setColumnDataIncluded(true);
		setOnlyAdjustLarger(false);
		setDynamicAdjustment(true);
		installActions();
	}

	/**
	 * Returns reference to main table.
	 * 
	 * @return {@link JTable}
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * Sets reference to main table.
	 * 
	 * @param table
	 * @see JTable
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * Adjusts the widths of all the columns in the table
	 */
	public void adjustColumns() {
		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++) {
			adjustColumn(i);
		}
	}

	/**
	 * Adjusts the width of the specified column in the table.
	 * 
	 * @param column
	 */
	public void adjustColumn(final int column) {
		TableColumn tableColumn = table.getColumnModel().getColumn(column);

		if (!tableColumn.getResizable())
			return;

		int columnHeaderWidth = getColumnHeaderWidth(column);
		int columnDataWidth = getColumnDataWidth(column);
		int preferredWidth = Math.max(columnHeaderWidth, columnDataWidth);

		updateTableColumn(column, preferredWidth);
	}

	/**
	 * Calculates the width based on the column name.
	 * 
	 * @param column
	 * @return int
	 */
	private int getColumnHeaderWidth(int column) {
		if (!isColumnHeaderIncluded)
			return 0;

		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		Object value = tableColumn.getHeaderValue();
		TableCellRenderer renderer = tableColumn.getHeaderRenderer();

		if (renderer == null) {
			renderer = table.getTableHeader().getDefaultRenderer();
		}

		Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
		return c.getPreferredSize().width;
	}

	/**
	 * Calculates the width based on the widest cell renderer for the given
	 * column.
	 * 
	 * @param column
	 * @return int
	 */
	private int getColumnDataWidth(int column) {
		if (!isColumnDataIncluded)
			return 0;

		int preferredWidth = 0;
		int maxWidth = table.getColumnModel().getColumn(column).getMaxWidth();

		for (int row = 0; row < table.getRowCount(); row++) {
			preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));

			// We've exceeded the maximum width, no need to check other rows

			if (preferredWidth >= maxWidth)
				break;
		}

		return preferredWidth;
	}

	/**
	 * Gets the preferred width for the specified cell.
	 * 
	 * @param row
	 * @param column
	 * @return int
	 */
	private int getCellDataWidth(int row, int column) {
		// Invoke the renderer for the cell to calculate the preferred width

		TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		Component c = table.prepareRenderer(cellRenderer, row, column);
		int width = c.getPreferredSize().width + table.getIntercellSpacing().width;

		return width;
	}

	/**
	 * Update the {@link TableColumn} with the newly calculated width.
	 * 
	 * @param column
	 * @param width
	 * @see TableColumn
	 */
	private void updateTableColumn(int column, int width) {
		final TableColumn tableColumn = table.getColumnModel().getColumn(column);

		if (!tableColumn.getResizable())
			return;

		width += spacing;

		// Don't shrink the column width

		if (isOnlyAdjustLarger) {
			width = Math.max(width, tableColumn.getPreferredWidth());
		}

		columnSizes.put(tableColumn, new Integer(tableColumn.getWidth()));

		table.getTableHeader().setResizingColumn(tableColumn);
		tableColumn.setWidth(width);
	}

	/**
	 * Restores the widths of the columns in the table to its previous width.
	 */
	public void restoreColumns() {
		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++) {
			restoreColumn(i);
		}
	}

	/**
	 * Restores the width of the specified column to its previous width.
	 * 
	 * @param column
	 */
	private void restoreColumn(int column) {
		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		Integer width = columnSizes.get(tableColumn);

		if (width != null) {
			table.getTableHeader().setResizingColumn(tableColumn);
			tableColumn.setWidth(width.intValue());
		}
	}

	/**
	 * Indicates whether to include the header in the width calculation.
	 * 
	 * @param isColumnHeaderIncluded
	 */
	public void setColumnHeaderIncluded(boolean isColumnHeaderIncluded) {
		this.isColumnHeaderIncluded = isColumnHeaderIncluded;
	}

	/**
	 * Returns <code>true</code> if column header is included in the width
	 * calculation.
	 * 
	 * @return boolean
	 */
	public boolean isColumnHeaderIncluded() {
		return isColumnHeaderIncluded;
	}
	
	/**
	 * Indicates whether to include the model data in the width calculation.
	 * 
	 * @param isColumnDataIncluded
	 */
	public void setColumnDataIncluded(boolean isColumnDataIncluded) {
		this.isColumnDataIncluded = isColumnDataIncluded;
	}
	
	/**
	 * Returns <code>true</code> if the model data is included in the width
	 * calculation.
	 * 
	 * @return boolean
	 */
	public boolean isColumnDataIncluded() {
		return isColumnDataIncluded;
	}
	
	/**
	 * Indicates whether columns can only be increased in size.
	 * 
	 * @param isOnlyAdjustLarger
	 */
	public void setOnlyAdjustLarger(boolean isOnlyAdjustLarger) {
		this.isOnlyAdjustLarger = isOnlyAdjustLarger;
	}

	/**
	 * Returns <code>true</code> if the columns can only increase in size.
	 * 
	 * @return boolean
	 */
	public boolean isOnlyAdjustLarger() {
		return isOnlyAdjustLarger;
	}
	
	/**
	 * Indicates whether changes to the model should cause the width to be
	 * dynamically recalculated.
	 * 
	 * @param isDynamicAdjustment
	 */
	public void setDynamicAdjustment(boolean isDynamicAdjustment) {
		// May need to add or remove the TableModelListener when changed

		if (this.isDynamicAdjustment != isDynamicAdjustment) {
			if (isDynamicAdjustment) {
				table.addPropertyChangeListener(this);
				table.getModel().addTableModelListener(this);
			} else {
				table.removePropertyChangeListener(this);
				table.getModel().removeTableModelListener(this);
			}
		}

		this.isDynamicAdjustment = isDynamicAdjustment;
	}


	/**
	 * Returns <code>true</code> if changes to the model should cause the width
	 * to be dynamically recalculated.
	 * 
	 * @return boolean
	 */
	public boolean isDynamicAdjustment() {
		return isDynamicAdjustment;
	}

	/**
	 * Implemented {@link PropertyChangeListener} method. When the
	 * {@link TableModel} changes, it updates the listeners and column widths.
	 * 
	 * @param e
	 */
	public void propertyChange(PropertyChangeEvent e) {

		if ("model".equals(e.getPropertyName())) {
			TableModel model = (TableModel) e.getOldValue();
			model.removeTableModelListener(this);

			model = (TableModel) e.getNewValue();
			model.addTableModelListener(this);
			adjustColumns();
		}
	}

	/**
	 * Implemented {@link TableModelListener} method. Executes when table is
	 * changed and calls methods that update column widths.
	 */
	public void tableChanged(final TableModelEvent e) {
		if (!isColumnDataIncluded)
			return;

		// Needed when table is sorted.

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// A cell has been updated

				int column = table.convertColumnIndexToView(e.getColumn());

				if (e.getType() == TableModelEvent.UPDATE && column != -1) {
					// Only need to worry about an increase in width for this
					// cell

					if (isOnlyAdjustLarger) {
						int row = e.getFirstRow();
						TableColumn tableColumn = table.getColumnModel().getColumn(column);

						if (tableColumn.getResizable()) {
							int width = getCellDataWidth(row, column);
							updateTableColumn(column, width);
						}
					}

					// Could be an increase of decrease so check all rows

					else {
						adjustColumn(column);
					}
				}

				// The update affected more than one column so adjust all
				// columns

				else {
					adjustColumns();
				}
			}
		});
	}

	/**
	 * Install {@link Action}s to give user control of certain functionality.
	 */
	private void installActions() {
		installColumnAction(true, true, "adjustColumn", "control ADD");
		installColumnAction(false, true, "adjustColumns", "control shift ADD");
		installColumnAction(true, false, "restoreColumn", "control SUBTRACT");
		installColumnAction(false, false, "restoreColumns", "control shift SUBTRACT");

		installToggleAction(true, false, "toggleDynamic", "control MULTIPLY");
		installToggleAction(false, true, "toggleLarger", "control DIVIDE");
	}

	/**
	 * Updates the input and action maps with a new {@link ColumnAction}.
	 * 
	 * @param isSelectedColumn
	 * @param isAdjust
	 * @param key
	 * @param keyStroke
	 */
	private void installColumnAction(boolean isSelectedColumn, boolean isAdjust, String key, String keyStroke) {
		Action action = new ColumnAction(isSelectedColumn, isAdjust);
		KeyStroke ks = KeyStroke.getKeyStroke(keyStroke);
		table.getInputMap().put(ks, key);
		table.getActionMap().put(key, action);
	}

	/**
	 * Updates the input and action maps with new {@link ToggleAction}.
	 * 
	 * @param isToggleDynamic
	 * @param isToggleLarger
	 * @param key
	 * @param keyStroke
	 */
	private void installToggleAction(boolean isToggleDynamic, boolean isToggleLarger, String key, String keyStroke) {
		Action action = new ToggleAction(isToggleDynamic, isToggleLarger);
		KeyStroke ks = KeyStroke.getKeyStroke(keyStroke);
		table.getInputMap().put(ks, key);
		table.getActionMap().put(key, action);
	}

	/**
	 * Action to adjust or restore the width of a single column or all columns.
	 * 
	 * @see AbstractAction
	 */
	class ColumnAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private boolean isSelectedColumn;
		private boolean isAdjust;

		public ColumnAction(boolean isSelectedColumn, boolean isAdjust) {
			this.isSelectedColumn = isSelectedColumn;
			this.isAdjust = isAdjust;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Handle selected column(s) width change actions

			if (isSelectedColumn) {
				int[] columns = table.getSelectedColumns();

				for (int i = 0; i < columns.length; i++) {
					if (isAdjust)
						adjustColumn(columns[i]);
					else
						restoreColumn(columns[i]);
				}
			} else {
				if (isAdjust)
					adjustColumns();
				else
					restoreColumns();
			}
		}
	}

	/**
	 * Toggles properties of the {@link TableColumnAdjuster} so the user can
	 * customize the functionality to their preferences
	 * 
	 * @see AbstractAction
	 */
	class ToggleAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		private boolean isToggleDynamic;
		private boolean isToggleLarger;

		public ToggleAction(boolean isToggleDynamic, boolean isToggleLarger) {
			this.isToggleDynamic = isToggleDynamic;
			this.isToggleLarger = isToggleLarger;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (isToggleDynamic) {
				setDynamicAdjustment(!isDynamicAdjustment);
				return;
			}

			if (isToggleLarger) {
				setOnlyAdjustLarger(!isOnlyAdjustLarger);
				return;
			}
		}
	}
}