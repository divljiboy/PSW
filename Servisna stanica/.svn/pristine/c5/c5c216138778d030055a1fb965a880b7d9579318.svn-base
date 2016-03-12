package ra32_2011;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.databind.JsonNode;

import gui.tables.Table;
import gui.toolbars.TableToolbar;
import listeners.table.TableColumnAdjuster;
import model.TableModel;
import model.TreeNode;
import ra81_2012.MockupJsonNode;

/**
 * Test class for {@link Table}.
 * 
 * @author Borko Arsović
 * @see JUnit4
 *
 */
public class TableTest {

	Table table;
	TreeNode node;
	JsonNode jsonNode;
	TableModel tableModel;
	TableColumnAdjuster tableColumnAdjuster;
	TableToolbar tableToolbar;

	/**
	 * Set up method for {@link TableTest}. Creates test instance of
	 * {@link Table}.
	 * 
	 * @throws Exception
	 * @see Table
	 */
	@Before
	public void setUp() throws Exception {
		table = new Table();
	}

	/**
	 * Test method for {@link gui.tables.Table#getNode()}.
	 * 
	 * @see Table
	 * @see Table#getNode()
	 */
	@Test
	public void testGetNode() {
		assertNull(node);
		jsonNode = new MockupJsonNode();
		node = new TreeNode(jsonNode);
		assertSame(jsonNode, node.getNode());
	}

	/**
	 * Test method for {@link gui.tables.Table#getTableModel()}.
	 */

	@Test
	public void testGetTableModel() {
		assertNull(tableModel);
		tableModel = new TableModel();
		table.setTableModel(tableModel);
		assertNotNull(tableModel);
		assertSame(tableModel, table.getTableModel());
	}

	/**
	 * Test method for {@link gui.tables.Table#setTableModel(TableModel)}.
	 */

	@Test
	public void testSetTableModel() {
		table.setTableModel(null);
		assertSame(null, table.getTableModel());
		tableModel = new TableModel();
		table.setTableModel(tableModel);
		assertSame(tableModel, table.getTableModel());
	}

	/**
	 * Test method for {@link gui.tables.Table#getFocusedTable()}.
	 */

	@Test
	public void testGetFocusedTable() {
		Table.setFocusedTable(null);
		assertSame(null, Table.getFocusedTable());
		Table.setFocusedTable(table);
		assertSame(table, Table.getFocusedTable());
	}

	/**
	 * Test method for {@link gui.tables.Table#setFocusedTable(Table)}.
	 */

	@Test
	public void testSetFocusedTable() {
		Table.setFocusedTable(null);
		assertNull(Table.getFocusedTable());
		Table.setFocusedTable(table);
		assertSame(table, Table.getFocusedTable());
	}

	/**
	 * Test method for {@link gui.tables.Table#getTableColumnAdjuster()}.
	 */

	@Test
	public void testGetTableColumnAdjuster() {
		assertNull(table.getTableColumnAdjuster());
		tableColumnAdjuster = new TableColumnAdjuster(table);
		table.setTableColumnAdjuster(tableColumnAdjuster);
		assertNotNull(table.getTableColumnAdjuster());
		assertSame(tableColumnAdjuster, table.getTableColumnAdjuster());

	}

	/**
	 * Test method for
	 * {@link gui.tables.Table#setTableColumnAdjuster(TableColumnAdjuster)}.
	 */

	@Test
	public void testSetTableColumnAdjuster() {
		assertNull(table.getTableColumnAdjuster());
		tableColumnAdjuster = new TableColumnAdjuster(table);
		table.setTableColumnAdjuster(tableColumnAdjuster);
		assertNotNull(table.getTableColumnAdjuster());
		assertSame(tableColumnAdjuster, table.getTableColumnAdjuster());
	}

	/**
	 * Test method for
	 * {@link gui.tables.Table#setFocusedTableViaAction(java.awt.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testSetFocusedTableViaAction() {
		Table.setFocusedTable(null);
		assertNull(Table.getFocusedTable());

		tableToolbar = new TableToolbar();
		tableToolbar.setTable(table);
		tableToolbar.add(table);
		ActionEvent mockEvent = new ActionEvent(tableToolbar.getTable(), ActionEvent.ACTION_PERFORMED, "focus");

		Table.setFocusedTableViaAction(mockEvent);
		assertSame(table, Table.getFocusedTable());
	}

}
