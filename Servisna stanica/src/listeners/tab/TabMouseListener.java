package listeners.tab;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import actions.manager.ActionsManager;
import gui.popups.TabPopupMenu;
import gui.tables.TabbedPane;

/**
 * Implementation of {@link MouseListener} interface.
 * Checks which tab is selected and updates which actions are enabled.
 * If a tab is clicked on with mouse wheel or middle button, that tab is closed.
 * If a tab click is a trigger, a popup menu will be shown.
 * 
 * @author Milan Radeta
 * @author Ivan Divljak
 *
 */
public class TabMouseListener implements MouseListener {

	private TabbedPane tabbedPane;

	/**
	 * Constructor of {@link TabMouseListener}.
	 * Parameter {@code tabbedPane} is a reference to the {@link TabbedPane} this listener belongs to.
	 * 
	 * @param tabbedPane
	 * 
	 */
	public TabMouseListener(TabbedPane tabbedPane) {
		this.setTabbedPane(tabbedPane);
	}

	/**
	 * Handler method for {@link MouseEvent} when tab is clicked on.
	 * If the source was middle mouse button or mouse wheel, the tab is closed.
	 * If the source was a trigger, popup menu will be shown.
	 * Enabled actions will be updated accordingly.
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (tabbedPane == TabbedPane.getMainTabbedPane()) {
			if (SwingUtilities.isMiddleMouseButton(e)) {
				int selectedIndex = tabbedPane.getSelectedIndex();

				if (selectedIndex >= 0) {
					tabbedPane.remove(selectedIndex);
				}

				ActionsManager.updateTabActions();
				ActionsManager.updateTreeActions();
			}
			if (e.isPopupTrigger()) {
				TabPopupMenu.getInstance().show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Handler method for {@link MouseEvent} when mouse button is pressed on a tab.
	 * This method changes the currently selected tab and shows a popuf if it's triggered.
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		TabbedPane.setLastSelectedTabbedPane(tabbedPane);
		if (tabbedPane == TabbedPane.getMainTabbedPane()) {
			if (e.isPopupTrigger()) {
				TabPopupMenu.getInstance().show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	/**
	 * Handler method for {@link MouseEvent} when mouse button is released on a tab.
	 * This method registers the release event and shows a popup if it's triggered. 
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (tabbedPane == TabbedPane.getMainTabbedPane()) {
			if (e.isPopupTrigger()) {
				TabPopupMenu.getInstance().show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	/**
	 * Getter for {@link TabbedPane} referenced in {@link TabMouseListener}.
	 * @return {@link TabbedPane}
	 */
	public TabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Setter for {@link TabbedPane} referenced in {@link TabMouseListener}.
	 * @param tabbedPane
	 */
	public void setTabbedPane(TabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

}
