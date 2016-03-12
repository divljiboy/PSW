package listeners.table;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import gui.tables.Table;

/**
 * Implementation of {@link FocusListener} interface.
 * Sets which {@link Table} is focused when focus is gained.
 * This way if focus is lost to a non {@code Table} component, information of the last focused {@code Table} will not be lost.
 * 
 * @author Milan Radeta
 *
 */
public class TableFocusListener implements FocusListener {

	private Table table;
	
	/**
	 * Constructor for {@link TableFocusListener}.
	 * Parameter {@code table} is a reference to the {@link Table} this listener belongs to.
	 * 
	 * @param table
	 */
	public TableFocusListener(Table table) {
		this.table = table;
	}
	
	/**
	 * Handler method for {@link FocusEvent}.
	 * When a {@link Table} component gains focus, it will be marked as the focused component among {@code Tables}.
	 */
	@Override
	public void focusGained(FocusEvent e) {
		Table.setFocusedTable(table);
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

}
