/**
 * 
 */
package ra180_2012;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JTable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import listeners.table.TableColumnAdjuster;

/**
 * Test class for {@link TableColumnAdjuster}.
 * 
 * @author Ivan Divljak
 * @see JUnit4
 * @see TableColumnAdjuster
 *
 */
public class TableColumnAdjusterTest {

	TableColumnAdjuster tca;
	TableColumnAdjuster tca2;
	JTable jt;
	JTable jt2;

	/**
	 * Set up method for {@link TableColumnAdjusterTest}. Creates two test
	 * {@link JTable}s with their own {@link TableColumnAdjuster}s.
	 * 
	 * @throws java.lang.Exception
	 * @see JTable
	 * @see TableColumnAdjuster
	 */
	@Before
	public void setUp() throws Exception {
		String[] columnNames = { "First Name", "Last Name", "Sport", "# of Years", "Vegetarian" };
		Object[][] data = { { "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		jt = new JTable(data, columnNames);
		jt2 = new JTable(data, columnNames);

		tca = new TableColumnAdjuster(jt);
		tca2 = new TableColumnAdjuster(jt2);
	}

	/**
	 * Test method for {@link listeners.table.TableColumnAdjuster#getTable()}.
	 * 
	 * @see TableColumnAdjuster
	 * @see TableColumnAdjuster#getTable()
	 * @see TableColumnAdjuster#setTable(JTable)
	 * @see JTable
	 */
	@Test
	public void testGetTable() {
		tca.setTable(null);
		Assert.assertSame(null, tca.getTable());
		tca.setTable(jt);
		Assert.assertSame(jt, tca.getTable());
	}

	/**
	 * Test method for
	 * {@link listeners.table.TableColumnAdjuster#setTable(javax.swing.JTable)}.
	 * 
	 * @see TableColumnAdjuster
	 * @see TableColumnAdjuster#getTable()
	 * @see TableColumnAdjuster#setTable(JTable)
	 * @see JTable
	 */
	@Test
	public void testSetTable() {
		tca.setTable(null);
		Assert.assertSame(null, tca.getTable());
		tca.setTable(jt);
		Assert.assertSame(jt, tca.getTable());
	}

	/**
	 * Test method for
	 * {@link listeners.table.TableColumnAdjuster#adjustColumns()}.
	 * 
	 * @see TableColumnAdjuster
	 * @see TableColumnAdjuster#adjustColumns()
	 */
	@Test
	public void testAdjustColumns() {

		assertEquals(tca.getTable().getColumnModel().getColumn(1).getWidth(),
				tca2.getTable().getColumnModel().getColumn(1).getWidth());
		tca.adjustColumns();
		assertNotEquals(tca.getTable().getColumnModel().getColumn(1).getWidth(),
				tca2.getTable().getColumnModel().getColumn(1).getWidth());

	}

	/**
	 * Test method for
	 * {@link listeners.table.TableColumnAdjuster#adjustColumn(int)}.
	 * 
	 * @see TableColumnAdjuster
	 * @see TableColumnAdjuster#adjustColumn(int)
	 */
	@Test
	public void testAdjustColumn() {
		assertEquals(tca.getTable().getColumnModel().getColumn(1).getWidth(),
				tca2.getTable().getColumnModel().getColumn(1).getWidth());
		tca.adjustColumn(1);
		assertNotEquals(tca.getTable().getColumnModel().getColumn(1).getWidth(),
				tca2.getTable().getColumnModel().getColumn(1).getWidth());

	}

	/**
	 * Test method for
	 * {@link listeners.table.TableColumnAdjuster#restoreColumns()}.
	 * 
	 * @see TableColumnAdjuster
	 * @see TableColumnAdjuster#restoreColumns()
	 */
	@Test
	public void testRestoreColumns() {
		tca.adjustColumns();
		assertNotEquals(tca.getTable().getColumnModel().getColumn(1).getWidth(),
				tca2.getTable().getColumnModel().getColumn(1).getWidth());
		tca.restoreColumns();
		assertEquals(tca.getTable().getColumnModel().getColumn(1).getWidth(),
				tca2.getTable().getColumnModel().getColumn(1).getWidth());

	}

	/**
	 * Test method for
	 * {@link listeners.table.TableColumnAdjuster#setDynamicAdjustment(boolean)}
	 * .
	 */
	@Test
	public void testSetDynamicAdjustment() {

		tca.setDynamicAdjustment(true);

		assertTrue(tca.isDynamicAdjustment());
		tca.setDynamicAdjustment(false);

		assertFalse(tca.isDynamicAdjustment());

	}

}
