package listeners.tab;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import actions.manager.ActionsManager;
import gui.tables.TabbedPane;

/**
 * Implementation of {@link ChangeListener} interface.
 * Used by {@link TabbedPane}, it registers when selected tab changes.
 * It then changes enabled actions based on newly selected tab.
 * 
 * @author Milan Radeta
 * @see ActionsManager
 *
 */
public class TabChangeListener implements ChangeListener {

	private TabbedPane tabbedPane;
	
	/**
	 * Constructor for the {@link TabChangeListener} class.
	 * It takes a reference to the {@link TabbedPane} object which uses the listener.
	 * 
	 * @param tabbedPane
	 */
	public TabChangeListener(TabbedPane tabbedPane) {
		this.setTabbedPane(tabbedPane);
	}
	
	/**
	 * Method for handling {@link ChangeEvent}.
	 * When the selected tab is changed, enabled actions are updated based on newly selected tab.
	 * 
	 * @see ActionsManager
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		TabbedPane.setLastSelectedTabbedPane(tabbedPane);
		ActionsManager.updateTabActions();
		ActionsManager.updateTreeActions();
	}
	
	/**
	 * Getter for the {@link TabbedPane} object which uses this listener.
	 * 
	 * @return {@link TabbedPane}
	 * @see TabChangeListener
	 */
	public TabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	/**
	 * Sets the {@link TabbedPane} which uses this listener.
	 * 
	 * @param tabbedPane
	 * @see TabChangeListener
	 */
	public void setTabbedPane(TabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

}
