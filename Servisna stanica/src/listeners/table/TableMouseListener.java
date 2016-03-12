package listeners.table;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.popups.TablePopupMenu;
import gui.tables.Table;

/**
 * Implementation of {@link MouseListener} interface.
 * 
 * @author Milan Radeta
 *
 */
public class TableMouseListener implements MouseListener {

	/**
	 * Link to {@link TableMouseListener}'s {@link Table}. 
	 */
	private Table table;
	
	/**
	 * Constructor which sets reference to {@link Table}.
	 * @param table
	 */
	public TableMouseListener(Table table) {
		this.setTable(table);
	}
	
	/**
	 * Handler method for {@link MouseEvent} when mouse button is released on
	 * the {@link Table}. Checks if the {@code MouseEvent} is a trigger and
	 * shows a popup menu accordingly.
	 *
	 **/
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			Table.setFocusedTable(table);
			TablePopupMenu.getInstance().show(e.getComponent(), e.getX(), e.getY());
		}
	}

	/**
	 * Handler method for {@link MouseEvent} when mouse button is pressed on the
	 * {@link Table}. Checks if the {@code MouseEvent} is a trigger and shows a
	 * popup menu accordingly.
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
			Table.setFocusedTable(table);
			TablePopupMenu.getInstance().show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Handler method for {@link MouseEvent} when mouse button is clicked on the
	 * {@link Table}. Checks if the {@code MouseEvent} is a trigger and shows a
	 * popup menu accordingly.
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.isPopupTrigger()) {
			Table.setFocusedTable(table);
			TablePopupMenu.getInstance().show(e.getComponent(), e.getX(), e.getY());
		}
	}

	/**
	 * Returns {@link TableMouseListener}'s {@link Table}.
	 * @return {@link Table}
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Sets {@link TableMouseListener}'s {@link Table}.
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}
}
