package gui.popups;

import javax.swing.JPopupMenu;

import actions.records.ActionAddRecord;
import actions.records.ActionAdvancedSearch;
import actions.records.ActionLastRecord;
import actions.records.ActionFirstRecord;
import actions.records.ActionModifyRecord;
import actions.records.ActionNextRecord;
import actions.records.ActionPreviousRecord;
import actions.records.ActionRefresh;
import actions.records.ActionRemoveRecord;

/**
 * Extension of the {@link JPopupMenu} class. Implements the singleton pattern.
 * Contains actions for modifying ({@link ActionAddRecord},
 * {@link ActionModifyRecord}, {@link ActionRemoveRecord}) and navigating (
 * {@link ActionFirstRecord}, {@link ActionPreviousRecord},
 * {@link ActionNextRecord}, {@link ActionLastRecord}) table records.
 * 
 * @author Milan Radeta
 * 
 */
public class TablePopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;

	private static TablePopupMenu instance;

	/**
	 * Private constructor of {@link TablePopupMenu} class. Adds instances of
	 * necessary actions to the menu.
	 */
	private TablePopupMenu() {
		add(ActionAddRecord.getInstance());
		add(ActionModifyRecord.getInstance());
		add(ActionRemoveRecord.getInstance());

		addSeparator();

		add(ActionFirstRecord.getInstance());
		add(ActionPreviousRecord.getInstance());
		add(ActionNextRecord.getInstance());
		add(ActionLastRecord.getInstance());

		addSeparator();

		add(ActionRefresh.getInstance());
		add(ActionAdvancedSearch.getInstance());
	}

	/**
	 * Returns the instance of {@link TablePopupMenu}. If there is none, new one
	 * is created.
	 * 
	 * @return {@link TablePopupMenu}
	 */
	public static TablePopupMenu getInstance() {
		if (instance == null) {
			instance = new TablePopupMenu();

		}
		return instance;
	}

}
