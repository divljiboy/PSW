package listeners;

import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import gui.tables.Table;
import gui.toolbars.QuickSearchTextField;
import model.TableModel;

/**
 * Implementation of {@link DocumentListener} used for filtering {@link Table}
 * via {@link RowFilter} and {@link RowFilter#regexFilter(String, int...)}.
 * 
 * @see DocumentListener
 * @see RowFilter
 * @see RowFilter#regexFilter(String, int...)
 * @see Table
 * @author Milan Radeta
 * @author Borko Arsović
 *
 */
public class QuickSearchListener implements DocumentListener {

	private QuickSearchTextField textField;

	/**
	 * {@link QuickSearchListener}'s constructor, that sets reference to
	 * {@link QuickSearchTextField}.
	 * 
	 * @param textField
	 */
	public QuickSearchListener(QuickSearchTextField textField) {
		this.setTextField(textField);
	}

	/**
	 * Implemented method that filters the {@link Table} via {@link RowFilter}.
	 * @param e
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(Table.getFocusedTable().getTableModel());

		Table.getFocusedTable().setRowSorter(rowSorter);

		String text = textField.getText().replace("\\", "/");

		if (text.trim().length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		}
	}

	/**
	 * Implemented method that filters the {@link Table} via {@link RowFilter}.
	 * @param e
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(Table.getFocusedTable().getTableModel());

		Table.getFocusedTable().setRowSorter(rowSorter);

		String text = textField.getText().replace("\\", "/");

		if (text.trim().length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		}

	}

	/**
	 * Implemented method that filters the {@link Table} via {@link RowFilter}.
	 * @param e
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(Table.getFocusedTable().getTableModel());

		Table.getFocusedTable().setRowSorter(rowSorter);

		String text = textField.getText().replace("\\", "/");

		if (text.trim().length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		}
	}

	/**
	 * Returns reference to {@link QuickSearchTextField}.
	 * @return {@link QuickSearchTextField}
	 * @see QuickSearchTextField
	 */
	public QuickSearchTextField getTextField() {
		return textField;
	}

	/**
	 * Sets reference to {@link QuickSearchTextField}.
	 * @param textField
	 * @see QuickSearchTextField
	 */
	public void setTextField(QuickSearchTextField textField) {
		this.textField = textField;
	}

}
