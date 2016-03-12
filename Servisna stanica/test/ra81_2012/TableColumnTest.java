package ra81_2012;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.databind.JsonNode;

import model.TableColumn;
import model.TableSchema;

/**
 * {@link JUnit4} test class for {@link TableColumn}.
 * 
 * @author Milan Radeta
 *
 */
public class TableColumnTest {

	TableColumn tableColumn;
	TableSchema tableSchema;
	JsonNode jsonNode;

	/**
	 * Sets up {@link TableColumnTest}'s members. Creates a default
	 * {@link TableColumn}, a {@link MockupJsonNode} and a default
	 * {@link TableSchema}.
	 * 
	 * @see TableColumn
	 * @see MockupJsonNode
	 * @see JsonNode
	 * @see TableSchema
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		tableColumn = new TableColumn();
		jsonNode = new MockupJsonNode();
		tableSchema = new TableSchema();
	}

	/**
	 * Tests {@link TableColumn#getTable()} by comparing the result of the
	 * method and value used in {@link TableColumn#setTable(TableSchema)}. Uses
	 * default {@link TableSchema}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getTable()
	 * @see TableColumn#setTable(TableSchema)
	 * @see TableSchema
	 */
	@Test
	public void testGetTable() {
		tableColumn.setTable(null);
		Assert.assertSame(null, tableColumn.getTable());
		tableColumn.setTable(tableSchema);
		Assert.assertSame(tableSchema, tableColumn.getTable());
	}

	/**
	 * Tests {@link TableColumn#setTable(TableSchema)} by comparing the used
	 * value and result of the {@link TableColumn#getTable()}. Uses default
	 * {@link TableSchema}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getTable()
	 * @see TableColumn#setTable(TableSchema)
	 * @see TableSchema
	 */
	@Test
	public void testSetTable() {
		tableColumn.setTable(null);
		Assert.assertSame(null, tableColumn.getTable());
		tableColumn.setTable(tableSchema);
		Assert.assertSame(tableSchema, tableColumn.getTable());
	}

	/**
	 * Tests {@link TableColumn#getCode()} by comparing the result of the method
	 * and value used in {@link TableColumn#setCode(String)}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getCode()
	 * @see TableColumn#setCode(String)
	 */
	@Test
	public void testGetCode() {
		tableColumn.setCode(null);
		Assert.assertEquals(null, tableColumn.getCode());
		tableColumn.setCode("test");
		Assert.assertEquals("test", tableColumn.getCode());

	}

	/**
	 * Tests {@link TableColumn#setCode(String)} by comparing the used value and
	 * result of the {@link TableColumn#getCode()}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getCode()
	 * @see TableColumn#setCode(String)
	 */
	@Test
	public void testSetCode() {
		tableColumn.setCode("test");
		Assert.assertEquals("test", tableColumn.getCode());
	}

	/**
	 * Tests {@link TableColumn#getName()} by comparing the result of the method
	 * and value used in {@link TableColumn#setName(String)}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getName()
	 * @see TableColumn#setName(String)
	 */
	@Test
	public void testGetName() {
		tableColumn.setName("test");
		Assert.assertEquals("test", tableColumn.getName());
	}

	/**
	 * Tests {@link TableColumn#setName(String)} by comparing the used value and
	 * result of the {@link TableColumn#getName()}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getName()
	 * @see TableColumn#setName(String)
	 */
	@Test
	public void testSetName() {
		tableColumn.setName("test");
		Assert.assertEquals("test", tableColumn.getName());
	}

	/**
	 * Tests {@link TableColumn#isPrimaryKey()} by comparing the result of the
	 * method and value used in {@link TableColumn#setPrimaryKey(boolean)}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#isPrimaryKey()
	 * @see TableColumn#setPrimaryKey(boolean)
	 */
	@Test
	public void testIsPrimaryKey() {
		tableColumn.setPrimaryKey(true);
		Assert.assertSame(true, tableColumn.isPrimaryKey());
		tableColumn.setPrimaryKey(false);
		Assert.assertSame(false, tableColumn.isPrimaryKey());
	}

	/**
	 * Tests {@link TableColumn#setPrimaryKey(boolean)} by comparing the used
	 * value and result of the {@link TableColumn#isPrimaryKey()}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#isPrimaryKey()
	 * @see TableColumn#setPrimaryKey(boolean)
	 */
	@Test
	public void testSetPrimaryKey() {
		tableColumn.setPrimaryKey(true);
		Assert.assertSame(true, tableColumn.isPrimaryKey());
		tableColumn.setPrimaryKey(false);
		Assert.assertSame(false, tableColumn.isPrimaryKey());
	}

	/**
	 * Tests {@link TableColumn#isNullable()} by comparing the result of the
	 * method and value used in {@link TableColumn#setNullable(boolean)}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#isNullable()
	 * @see TableColumn#setNullable(boolean)
	 */
	@Test
	public void testIsNullable() {
		tableColumn.setNullable(true);
		Assert.assertEquals(true, tableColumn.isNullable());
		tableColumn.setNullable(false);
		Assert.assertEquals(false, tableColumn.isNullable());
	}

	/**
	 * Tests {@link TableColumn#setNullable(boolean)} by comparing the used
	 * value and result of the {@link TableColumn#isNullable()}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#isNullable()
	 * @see TableColumn#setNullable(boolean)
	 */
	@Test
	public void testSetNullable() {
		tableColumn.setNullable(true);
		Assert.assertSame(true, tableColumn.isNullable());
		tableColumn.setNullable(false);
		Assert.assertSame(false, tableColumn.isNullable());
	}

	/**
	 * Tests {@link TableColumn#getType()} by comparing the result of the method
	 * and value used in {@link TableColumn#setType(int)}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getType()
	 * @see TableColumn#setType(int)
	 */
	@Test
	public void testGetType() {
		for (int i = 0; i < 100; i++) {
			tableColumn.setType(i);
			Assert.assertSame(i, tableColumn.getType());
		}
	}

	/**
	 * Tests {@link TableColumn#setType(int)} by comparing the used value and
	 * result of the {@link TableColumn#getType()}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getType()
	 * @see TableColumn#setType(int)
	 */
	@Test
	public void testSetType() {
		for (int i = 0; i < 100; i++) {
			tableColumn.setType(i);
			Assert.assertSame(i, tableColumn.getType());
		}
	}

	/**
	 * Tests {@link TableColumn#getSize()} by comparing the result of the method
	 * and value used in {@link TableColumn#setSize(int)}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getSize()
	 * @see TableColumn#setSize(int)
	 */
	@Test
	public void testGetSize() {
		for (int i = 0; i < 100; i++) {
			tableColumn.setSize(i);
			Assert.assertSame(i, tableColumn.getSize());
		}
	}

	/**
	 * Tests {@link TableColumn#setSize(int)} by comparing the used value and
	 * result of the {@link TableColumn#getSize()}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getSize()
	 * @see TableColumn#setSize(int)
	 */
	@Test
	public void testSetSize() {
		for (int i = 0; i < 100; i++) {
			tableColumn.setSize(i);
			Assert.assertSame(i, tableColumn.getSize());
		}
	}

	/**
	 * Tests {@link TableColumn#getDecimalDigits()} by comparing the result of
	 * the method and value used in {@link TableColumn#setDecimalDigits(int)}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getDecimalDigits()
	 * @see TableColumn#setDecimalDigits(int)
	 */
	@Test
	public void testGetDecimalDigits() {
		for (int i = 0; i < 100; i++) {
			tableColumn.setDecimalDigits(i);
			Assert.assertEquals(i, tableColumn.getDecimalDigits());
		}
	}

	/**
	 * Tests {@link TableColumn#setDecimalDigits(int)} by comparing the used
	 * value and result of the {@link TableColumn#getDecimalDigits()}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getDecimalDigits()
	 * @see TableColumn#setDecimalDigits(int)
	 */
	@Test
	public void testSetDecimalDigits() {
		for (int i = 0; i < 100; i++) {
			tableColumn.setDecimalDigits(i);
			Assert.assertSame(i, tableColumn.getDecimalDigits());
		}
	}

	/**
	 * Tests {@link TableColumn#getSemanticTableColumn()} by comparing the
	 * result of the method and value used in
	 * {@link TableColumn#setSemanticTableColumn(TableColumn)}. Uses default
	 * {@link TableColumn}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getSemanticTableColumn()
	 * @see TableColumn#setSemanticTableColumn(TableColumn)
	 */
	@Test
	public void testGetSemanticTableColumn() {
		tableColumn.setSemanticTableColumn(null);
		Assert.assertSame(null, tableColumn.getSemanticTableColumn());
		TableColumn anotherColumn = new TableColumn();
		tableColumn.setSemanticTableColumn(anotherColumn);
		Assert.assertSame(anotherColumn, tableColumn.getSemanticTableColumn());
	}

	/**
	 * Tests {@link TableColumn#setSemanticTableColumn(TableColumn)} by
	 * comparing the used value and result of the
	 * {@link TableColumn#getSemanticTableColumn()}. Uses default
	 * {@link TableColumn}.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getSemanticTableColumn()
	 * @see TableColumn#setSemanticTableColumn(TableColumn)
	 */
	@Test
	public void testSetSemanticTableColumn() {
		tableColumn.setSemanticTableColumn(null);
		Assert.assertSame(null, tableColumn.getSemanticTableColumn());
		TableColumn anotherColumn = new TableColumn();
		tableColumn.setSemanticTableColumn(anotherColumn);
		Assert.assertSame(anotherColumn, tableColumn.getSemanticTableColumn());
	}

	/**
	 * Tests {@link TableColumn#getReferencedTableColumns()} by comparing the
	 * result of the method and value used in
	 * {@link TableColumn#setReferencedTableColumns(ArrayList)}. Uses default
	 * {@link ArrayList} of default {@link TableColumn}s.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getReferencedTableColumns()
	 * @see TableColumn#setReferencedTableColumns(ArrayList)
	 * @see ArrayList
	 */
	@Test
	public void testGetReferencedTableColumns() {
		ArrayList<TableColumn> columns = new ArrayList<>();
		tableColumn.setReferencedTableColumns(columns);
		Assert.assertSame(columns, tableColumn.getReferencedTableColumns());
		columns = new ArrayList<>();
		Assert.assertNotSame(columns, tableColumn.getReferencedTableColumns());
		columns.add(new TableColumn());
		Assert.assertNotSame(columns, tableColumn.getReferencedTableColumns());
		tableColumn.setReferencedTableColumns(columns);
		Assert.assertSame(columns, tableColumn.getReferencedTableColumns());
	}

	/**
	 * Tests {@link TableColumn#setReferencedTableColumns(ArrayList)} by
	 * comparing the used value and result of the
	 * {@link TableColumn#getReferencedTableColumns()}. Uses default
	 * {@link ArrayList} of default {@link TableColumn}s.
	 * 
	 * @see TableColumn
	 * @see TableColumn#getReferencedTableColumns()
	 * @see TableColumn#setReferencedTableColumns(ArrayList)
	 * @see ArrayList
	 */
	@Test
	public void testSetReferencedTableColumns() {
		ArrayList<TableColumn> columns = new ArrayList<>();
		tableColumn.setReferencedTableColumns(columns);
		Assert.assertSame(columns, tableColumn.getReferencedTableColumns());
		columns = new ArrayList<>();
		Assert.assertNotSame(columns, tableColumn.getReferencedTableColumns());
		columns.add(new TableColumn());
		Assert.assertNotSame(columns, tableColumn.getReferencedTableColumns());
		tableColumn.setReferencedTableColumns(columns);
		Assert.assertSame(columns, tableColumn.getReferencedTableColumns());
	}

}
