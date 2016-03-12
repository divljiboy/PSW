package ra81_2012;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.databind.JsonNode;

import model.TableColumn;
import model.TableSchema;
import model.TreeNode;

/**
 * {@link JUnit4} test class for {@link TableSchema}.
 * 
 * @author Milan Radet
 *
 */
public class TableSchemaTest {

	TableSchema tableSchema;
	MockupConnection connection;

	/**
	 * Sets up {@link TableSchemaTest}'s members. Creates a default test
	 * {@link TableSchema} with test {@link TableColumn}s and a default
	 * {@link MockupConnection}.
	 * 
	 * @see TableSchema
	 * @see TableColumn
	 * @see MockupConnection
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		tableSchema = new TableSchema();
		tableSchema.setCode("Test");
		TableSchema.getTableSchemas().clear();
		TableSchema.getTableSchemas().put(tableSchema.getCode(), tableSchema);
		for (int i = 0; i < 3; i++) {
			TableColumn col = new TableColumn();
			col.setCode("Col" + (i + 1));
			tableSchema.getColumns().add(col);
		}
		connection = new MockupConnection();
		tableSchema.setConnection(connection);
	}

	/**
	 * Tests {@link TableSchema#setPrimaryKeys()} by checking every
	 * {@link TableColumn} if it's the primary or not. First 2 are, the others
	 * are not.
	 * 
	 * @see TableSchema
	 * @see TableSchema#setPrimaryKeys()
	 * @see TableSchema#getColumns()
	 * @see TableColumn#isPrimaryKey()
	 */
	@Test
	public void testSetPrimaryKeys() throws SQLException {
		tableSchema.setPrimaryKeys();
		for (int i = 0; i < tableSchema.getColumns().size(); i++) {
			if (i < 2) {
				assertSame(true, tableSchema.getColumns().get(i).isPrimaryKey());
			} else {
				assertSame(false, tableSchema.getColumns().get(i).isPrimaryKey());
			}
		}
	}

	/**
	 * Tests {@link TableSchema#setForeignKeys()} by creating another two
	 * {@link TableSchema}s whose some {@link TableColumn}s are referenced by
	 * the original {@link TableSchema} and checking its {@link TableColumn}s
	 * via {@link TableColumn#getReferencedTableColumns()} method.
	 * 
	 * @see TableSchema
	 * @see TableSchema#setForeignKeys()
	 * @see TableSchema#getColumns()
	 * @see TableColumn#getReferencedTableColumns()
	 */
	@Test
	public void testSetForeignKeys() throws SQLException {
		TableSchema refTable1 = new TableSchema();
		refTable1.setCode("RefTable1");
		refTable1.setConnection(tableSchema.getConnection());
		TableSchema refTable2 = new TableSchema();
		refTable2.setCode("RefTable2");
		refTable2.setConnection(tableSchema.getConnection());

		TableSchema.getTableSchemas().put(refTable1.getCode(), refTable1);
		TableSchema.getTableSchemas().put(refTable2.getCode(), refTable2);

		for (int i = 0; i < 3; i++) {
			TableColumn col = new TableColumn();
			col.setCode("OrgCol" + (i + 1));
			refTable1.getColumns().add(col);
			col = new TableColumn();
			col.setCode("OrgCol" + (i + 1));
			refTable2.getColumns().add(col);
		}

		tableSchema.setForeignKeys();
		for (TableColumn column : tableSchema.getColumns()) {
			if (column.getCode().equals("Col1")) {
				assertSame(1, column.getReferencedTableColumns().size());
				assertSame(refTable1.getColumns().get(0), column.getReferencedTableColumns().get(0));
			} else if (column.getCode().equals("Col2")) {
				assertSame(1, column.getReferencedTableColumns().size());
				assertSame(refTable2.getColumns().get(1), column.getReferencedTableColumns().get(0));

			} else {
				assertSame(0, column.getReferencedTableColumns().size());
			}
		}

	}

	/**
	 * Tests {@link TableSchema#findColumn(String)()} by checking every
	 * {@link TableColumn}s' code and the result of the method whose parameter
	 * is that code.
	 * 
	 * @see TableSchema
	 * @see TableSchema#findColumn(String)
	 * @see TableSchema#getColumns()
	 */
	@Test
	public void testFindColumn() {
		assertSame(tableSchema.getColumns().get(0), tableSchema.findColumn("Col1"));
		assertSame(tableSchema.getColumns().get(1), tableSchema.findColumn("Col2"));
		assertSame(tableSchema.getColumns().get(2), tableSchema.findColumn("Col3"));
	}

	/**
	 * Tests {@link TableSchema#getTable(String)} static method by searching the
	 * original {@link TableSchema} by its code.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getTable(String)
	 * @throws SQLException
	 */
	@Test
	public void testGetTableString() throws SQLException {
		assertSame(tableSchema, TableSchema.getTable("Test"));
	}

	/**
	 * Tests {@link TableSchema#getTable(TreeNode)} static method by searching
	 * the original {@link TableSchema} by its {@link TreeNode}.
	 * {@link TreeNode} uses {@link MockupJsonNode}.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getTable(TreeNode)
	 * @see TreeNode
	 * @see MockupJsonNode
	 * @see JsonNode
	 * @throws SQLException
	 */
	@Test
	public void testGetTableTreeNode() throws SQLException {
		JsonNode jsonNode = new MockupJsonNode();
		TreeNode node = new TreeNode(jsonNode);
		assertSame(tableSchema, TableSchema.getTable(node));
	}

	/**
	 * Tests {@link TableSchema#getTableSchemas()} static method by comparing it
	 * with new {@link HashMap}, which contains the same {@link TableSchema} as
	 * the result of the method.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getTableSchemas()
	 * @see HashMap
	 */
	@Test
	public void testGetTableSchemas() {
		assertEquals(1, TableSchema.getTableSchemas().size());
		HashMap<String, TableSchema> cmpMap = new HashMap<>();
		cmpMap.put(tableSchema.getCode(), tableSchema);
		assertEquals(cmpMap, TableSchema.getTableSchemas());
	}

	/**
	 * Tests {@link TableSchema#setTableSchemas(HashMap)} static method by
	 * comparing its content, size and keys.
	 * 
	 * @see TableSchema
	 * @see TableSchema#setTableSchemas(HashMap)
	 * @see HashMap
	 */
	@Test
	public void testSetTableSchemas() {
		TableSchema tableSchema = new TableSchema();
		tableSchema.setCode("Test2");
		TableSchema.getTableSchemas().clear();
		TableSchema.getTableSchemas().put(tableSchema.getCode(), tableSchema);
		assertSame(1, TableSchema.getTableSchemas().size());
		assertSame(true, TableSchema.getTableSchemas().containsKey("Test2"));
		assertSame(tableSchema, TableSchema.getTableSchemas().get("Test2"));
	}

	/**
	 * Tests {@link TableSchema#getName()} method by setting {@link TableSchema}
	 * 's name and comparing it to its new value.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getName()
	 */
	@Test
	public void testGetName() {
		tableSchema.setName("Test");
		assertSame("Test", tableSchema.getName());
	}

	/**
	 * Tests {@link TableSchema#setName(String)} method by setting
	 * {@link TableSchema} 's name and comparing it to its new value.
	 * 
	 * @see TableSchema
	 * @see TableSchema#setName(String)
	 */
	@Test
	public void testSetName() {
		tableSchema.setName("Test");
		assertSame("Test", tableSchema.getName());
	}

	/**
	 * Tests {@link TableSchema#getCode()} method by comparing it to
	 * {@link String}.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getCode()
	 * @see String
	 */
	@Test
	public void testGetCode() {
		assertSame("Test", tableSchema.getCode());
	}

	/**
	 * Tests {@link TableSchema#setCode(String)} method by setting it to a new
	 * value and comparing to it.
	 * 
	 * @see TableSchema
	 * @see TableSchema#setCode(String)
	 */
	@Test
	public void testSetCode() {
		tableSchema.setCode("Test2");
		assertSame("Test2", tableSchema.getCode());
	}

	/**
	 * Tests {@link TableSchema#getColumns()} by comparing the codes of
	 * {@link TableColumn}s to the ones initially given.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getColumns()
	 * @see TableColumn
	 * @see TableColumn#getCode()
	 */
	@Test
	public void testGetColumns() {
		for (int i = 0; i < tableSchema.getColumns().size(); i++) {
			assertEquals("Col" + (i + 1), tableSchema.getColumns().get(i).getCode());
		}
	}

	/**
	 * Tests {@link TableSchema#setColumns(ArrayList)} by creating a new
	 * {@link ArrayList} of {@link TableColumn}s with new codes, setting it to
	 * original {@link TableSchema}, and comparing the references to the
	 * {@link TableColumn}s.
	 * 
	 * @see TableSchema
	 * @see TableSchema#setColumns(ArrayList)
	 * @see ArrayList
	 * @see TableColumn
	 */
	@Test
	public void testSetColumns() {
		ArrayList<TableColumn> newCols = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			TableColumn col = new TableColumn();
			col.setCode("NewCol" + (i + 1));
			newCols.add(col);
		}
		tableSchema.setColumns(newCols);
		assertSame(newCols, tableSchema.getColumns());
		for (int i = 0; i < tableSchema.getColumns().size(); i++) {
			assertSame(newCols.get(i), tableSchema.getColumns().get(i));
		}
	}

	/**
	 * Tests {@link TableSchema#getNode()} by creating a new {@link TreeNode}
	 * which uses a {@link MockupJsonNode}, setting it to original
	 * {@link TableSchema} and comparing references.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getNode()
	 * @see TableSchema#setNode(TreeNode)
	 * @see TreeNode
	 * @see MockupJsonNode
	 * @see JsonNode
	 */
	@Test
	public void testGetNode() {
		TreeNode node = new TreeNode(new MockupJsonNode());
		tableSchema.setNode(node);
		assertSame(node, tableSchema.getNode());
	}

	/**
	 * Tests {@link TableSchema#setNode(TreeNode)} by creating a new
	 * {@link TreeNode} which uses a {@link MockupJsonNode}, setting it to
	 * original {@link TableSchema} and comparing references.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getNode()
	 * @see TableSchema#setNode(TreeNode)
	 * @see TreeNode
	 * @see MockupJsonNode
	 * @see JsonNode
	 */
	@Test
	public void testSetNode() {
		TreeNode node = new TreeNode(new MockupJsonNode());
		tableSchema.setNode(node);
		assertSame(node, tableSchema.getNode());
	}

	/**
	 * Tests {@link TableSchema#updateColumnNames()} by comparing the names of
	 * {@link TableColumn}s to their expected values.
	 * 
	 * @see TableSchema
	 * @see TableSchema#updateColumnNames()
	 * @see TableColumn
	 * @see TableColumn#getName()
	 */
	@Test
	public void testUpdateColumnNames() {
		tableSchema.updateColumnNames();
		for (int i = 0; i < tableSchema.getColumns().size(); i++) {
			assertEquals("Col" + (i + 1), tableSchema.getColumns().get(i).getName());
		}
	}

	/**
	 * Tests {@link TableSchema#getConnection()} by comparing to the initial
	 * {@link MockupConnection}.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getConnection()
	 * @see MockupConnection
	 * @see Connection
	 */
	@Test
	public void testGetConnection() {
		assertSame(connection, tableSchema.getConnection());
	}

	/**
	 * Tests {@link TableSchema#setConnection(Connection)} by setting the
	 * connection to a new {@link MockupConnection} object and comparing it to
	 * it.
	 * 
	 * @see TableSchema
	 * @see TableSchema#getConnection()
	 * @see TableSchema#setConnection(Connection)
	 * @see MockupConnection
	 * @see Connection
	 */
	@Test
	public void testSetConnection() {
		connection = new MockupConnection();
		tableSchema.setConnection(connection);
		assertSame(connection, tableSchema.getConnection());
	}

}
