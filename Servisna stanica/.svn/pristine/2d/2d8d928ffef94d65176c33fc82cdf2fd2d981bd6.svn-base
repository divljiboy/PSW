package listeners.table;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import gui.tables.Table;
import gui.tables.TablePanel;
import gui.toolbars.QuickSearchTextField;

/**
 * Implementation of {@link FocusListener} linked to
 * {@link QuickSearchTextField}.
 * 
 * @author Borko Arsović
 *
 */
public class QuickSearchFocusListener implements FocusListener {

	/**
	 * Implemented method that calls {@link Table#setFocusedTable(Table)}.
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		QuickSearchTextField qstf = (QuickSearchTextField) arg0.getSource();
		TablePanel tp = (TablePanel) qstf.getParent().getParent().getParent();
		Table.setFocusedTable(tp.getTable());
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

}
