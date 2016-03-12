package gui.toolbars;

import javax.swing.AbstractAction;
import javax.swing.JToolBar;

import actions.records.ActionAddRecord;
import actions.records.ActionAdvancedSearch;
import actions.records.ActionFirstRecord;
import actions.records.ActionLastRecord;
import actions.records.ActionModifyRecord;
import actions.records.ActionNextRecord;
import actions.records.ActionPreviousRecord;
import actions.records.ActionRefresh;
import actions.records.ActionRemoveRecord;
import gui.tables.Table;

/**
 * Extension of {@link JToolBar}, which has specialized {@link AbstractAction}s
 * for record manipulation and navigation in {@link Table} and
 * {@link QuickSearchPanel} as well.
 * 
 * @see JToolBar
 * @see AbstractAction
 * @see Table
 * @see ActionAddRecord
 * @see ActionModifyRecord
 * @see ActionRemoveRecord
 * @see ActionFirstRecord
 * @see ActionPreviousRecord
 * @see ActionNextRecord
 * @see ActionLastRecord
 * @see ActionRefresh
 * @see ActionAdvancedSearch
 * @see QuickSearchPanel
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @author Isidora Škulec
 *
 */
public class TableToolbar extends JToolBar {
	private static final long serialVersionUID = 1L;
	private QuickSearchPanel quickSearchPanel;
	private Table table;

	/**
	 * Empty constructor.
	 */
	public TableToolbar() {
	};

	/**
	 * Creates {@link JToolBar} extension linked to {@link Table} parameter
	 * {@code table} argument, specialized {@link AbstractAction}s for record
	 * manipulation and navigation in {@link Table} and {@link QuickSearchPanel}
	 * as well.
	 * 
	 * @see JToolBar
	 * @see AbstractAction
	 * @see Table
	 * @see ActionAddRecord
	 * @see ActionModifyRecord
	 * @see ActionRemoveRecord
	 * @see ActionFirstRecord
	 * @see ActionPreviousRecord
	 * @see ActionNextRecord
	 * @see ActionLastRecord
	 * @see ActionRefresh
	 * @see ActionAdvancedSearch
	 * @see QuickSearchPanel
	 * 
	 * @param table
	 */
	public TableToolbar(Table table) {
		super(table.getNode().toString());
		this.setTable(table);
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

		addSeparator();
		add(ActionAdvancedSearch.getInstance());
		quickSearchPanel = new QuickSearchPanel();
		add(quickSearchPanel);
	}

	/**
	 * Returns {@link QuickSearchPanel} component of {@link TableToolbar}.
	 * 
	 * @return {@link QuickSearchPanel}
	 */
	public QuickSearchPanel getQuickSearchPanel() {
		return quickSearchPanel;
	}

	/**
	 * Sets {@link QuickSearchPanel} member of {@link TableToolbar}.
	 * 
	 * @param quickSearchPanel
	 */
	public void setQuickSearchPanel(QuickSearchPanel quickSearchPanel) {
		this.quickSearchPanel = quickSearchPanel;
	}

	/**
	 * Returns {@link Table} reference member of {@link TableToolbar}.
	 * 
	 * @return {@link Table}
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Sets {@link Table} reference member of {@link TableToolbar}.
	 * 
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}
}
