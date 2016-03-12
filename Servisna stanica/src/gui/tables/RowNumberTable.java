package gui.tables;

import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * Extends {@link JTable} class, implements {@link ChangeListener},
 * {@link PropertyChangeListener} and {@link TableModelListener} interfaces.
 * Represents the first column of a table with row numbers.
 * 
 * @author Rob Camick
 * @author Ivan Divljak
 * @author Milan Radeta
 */
public class RowNumberTable extends JTable implements ChangeListener, PropertyChangeListener, TableModelListener {
	private static final long serialVersionUID = 1L;

	private JTable table;

	/**
	 * Constructor of the {@link RowNumberTable} class. Creates a {@link JTable}
	 * which will hold row numbers of the given {@code JTable}. Adds this class
	 * as a {@link TableModelListener} to the parent {@code JTable}.
	 * 
	 * @see JTable
	 * @see TableModelListener
	 * @see JScrollPane
	 * @param table
	 * @param scrollPane
	 */
	public RowNumberTable(JTable table, JScrollPane scrollPane) {
		this.table = table;
		table.addPropertyChangeListener(this);
		table.getModel().addTableModelListener(this);

		setFocusable(false);
		setAutoCreateColumnsFromModel(false);
		setSelectionModel(table.getSelectionModel());

		TableColumn column = new TableColumn();
		column.setHeaderValue(" ");
		addColumn(column);
		column.setCellRenderer(new RowNumberRenderer());

		getColumnModel().getColumn(0).setPreferredWidth(50);
		setPreferredScrollableViewportSize(getPreferredSize());

		scrollPane.setRowHeaderView(this);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, getTableHeader());
	}

	/**
	 * Implemented method that keeps scrolling of the row table in sync with the
	 * main table.
	 */
	@Override
	public void addNotify() {
		super.addNotify();
		Component c = getParent();

		if (c instanceof JViewport) {
			JViewport viewport = (JViewport) c;
			viewport.addChangeListener(this);
		}
	}

	/**
	 * Implemented method that delegate method to main table.
	 */
	@Override
	public int getRowCount() {
		return table.getRowCount();
	}

	/**
	 * Implemented method that returns gets the row height of the main table.
	 * 
	 * @param row
	 */
	@Override
	public int getRowHeight(int row) {
		int rowHeight = table.getRowHeight(row);

		if (rowHeight != super.getRowHeight(row)) {
			super.setRowHeight(row, rowHeight);
		}

		return rowHeight;
	}

	/**
	 * Implemented method that returns the value of the cell, which is the
	 * number of its row.
	 * 
	 * @param row
	 * @param column
	 */
	@Override
	public Object getValueAt(int row, int column) {
		return Integer.toString(row + 1);
	}

	/**
	 * Implemented method that always returns false.
	 * 
	 * @param row
	 * @param column
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * Empty implemented method, since cells are read only.
	 */
	@Override
	public void setValueAt(Object value, int row, int column) {
	}

	/**
	 * Event handler method for {@link ChangeEvent}. Scrolls the
	 * {@link RowNumberTable} together with the parent {@link JTable}.
	 */
	public void stateChanged(ChangeEvent e) {
		JViewport viewport = (JViewport) e.getSource();
		JScrollPane scrollPane = (JScrollPane) viewport.getParent();
		scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
	}

	/**
	 * Event handler of {@link PropertyChangeEvent}. When a certain property of
	 * the parent {@link JTable} changes, the same applies to the
	 * {@link RowNumberTable}. Currently supported properties are
	 * selectionModel, rowHeight, model and rowSorter.
	 */
	public void propertyChange(PropertyChangeEvent e) {
		if ("selectionModel".equals(e.getPropertyName())) {
			setSelectionModel(table.getSelectionModel());
		}

		if ("rowHeight".equals(e.getPropertyName())) {
			repaint();
		}

		if ("model".equals(e.getPropertyName())) {
			table.getModel().addTableModelListener(this);
			revalidate();
		}

		if ("rowSorter".equals(e.getPropertyName())) {
			repaint();
			revalidate();
		}
	}

	//
	// Implement the TableModelListener
	//
	/**
	 * Implemented {@link TableModelListener}'s method, that will revalidate the
	 * table.
	 * 
	 * @see TableModelEvent
	 * @param e
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		revalidate();
	}

	/**
	 * Private class, extends {@link DefaultTableCellRenderer}. Renders the
	 * {@link RowNumberTable} class as the first column of the parent
	 * {@link JTable}.
	 * 
	 * @author Ivan Divljak
	 * @author Rob Camick
	 */
	private static class RowNumberRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		/**
		 * Constructor of the {@link RowNumberRenderer} class. Crates a renderer
		 * for the {@link RowNumberTable} class.
		 */
		public RowNumberRenderer() {
			setHorizontalAlignment(JLabel.CENTER);
		}

		/**
		 * Returns this {@link RowNumberRenderer}, while setting header,
		 * foreground, background and font of the cell.
		 * 
		 * @param table
		 * @param value
		 * @param isSelected
		 * @param hasFocus
		 */
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (table != null) {
				JTableHeader header = table.getTableHeader();

				if (header != null) {
					setForeground(header.getForeground());
					setBackground(header.getBackground());
					setFont(header.getFont());
				}
			}

			if (isSelected) {
				setFont(getFont().deriveFont(Font.BOLD));
			}

			setText((value == null) ? "" : value.toString());
			// setBorder(UIManager.getBorder("TableHeader.cellBorder"));

			return this;
		}
	}
}