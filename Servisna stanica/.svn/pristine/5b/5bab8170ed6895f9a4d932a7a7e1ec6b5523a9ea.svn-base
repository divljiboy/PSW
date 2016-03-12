package ra81_2012;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import gui.tables.Table;
import model.Condition;
import model.Record;
import model.TableColumn;
import model.TableModel;
import model.TableSchema;

/**
 * {@link JUnit4} test class for {@link TableModel}.
 * 
 * @author Milan Radeta
 *
 */
public class TableModelTest {

	TableModel model;
	TableSchema schema;
	MockupConnection connection;

	/**
	 * Sets up {@link TableModelTest}'s members. Creates a default
	 * {@link TableModel}, a {@link TableSchema} and a {@link MockupConnection}.
	 * The model is linked to schema and schema has a reference to the
	 * connection. Schema is called "Test" and has three test columns ("Colx",
	 * where x represents column number 1-3), of type {@link Types#VARCHAR}.
	 * 
	 * @see TableModel
	 * @see TableSchema
	 * @see MockupConnection
	 * @see Connection
	 * @see Types
	 * @see Types#VARCHAR
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		connection = new MockupConnection();
		model = new TableModel();
		schema = new TableSchema();
		schema.setCode("Test");
		for (int i = 0; i < 3; i++) {
			TableColumn col = new TableColumn();
			col.setCode("Col" + (i + 1));
			col.setName("Col" + (i + 1));
			col.setTable(schema);
			col.setType(Types.VARCHAR);
			schema.getColumns().add(col);
		}
		TableSchema.getTableSchemas().clear();
		TableSchema.getTableSchemas().put(schema.getCode(), schema);
		model.setTableSchema(schema);
		model.setConnection(connection);
	}

	/**
	 * Tests {@link TableModel#isCellEditable(int, int)} by checking if the
	 * result of the method is always false, for any 2 parameters, as it should
	 * be.
	 * 
	 * @see TableModel
	 * @see TableModel#isCellEditable(int, int)
	 */
	@Test
	public void testIsCellEditable() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assertFalse(model.isCellEditable(i, j));
			}
		}
	}

	/**
	 * Tests {@link TableModel#refreshData(int)}. It tests 4 different cases: 1.
	 * {@link TableModel} without parent and conditions; 2. without a parent and
	 * with conditions; 3. with parent and without conditions; 4. with parent
	 * and conditions
	 * 
	 * @see TableModel
	 * @see TableModel#refreshData(int)
	 * @see TableModel#getConditions()
	 * @see TableModel#getConditionValues()
	 * @see TableModel#getParentTableModel()
	 */
	@Test
	public void testRefreshData() {
		// no conditions, no parent
		model.refreshData(TableModel.NONE);
		assertSame(2, model.getRecords().size());
		int recordIndex = 0;
		for (Record record : model.getRecords()) {
			TableColumn[] cols = new TableColumn[3];
			for (TableColumn col : record.getValues().keySet()) {
				int index = col.getCode().length() - 1;
				int num = Integer.parseInt(col.getCode().substring(index, index + 1));
				cols[num - 1] = col;
				assertEquals("R" + (recordIndex + 1) + "NPNC" + num, record.getValues().get(col));
			}
			for (int i = 0; i < cols.length; i++) {
				assertSame(schema.getColumns().get(i), cols[i]);
			}
			recordIndex++;
		}

		// with conditions, no parent
		model.getConditions().put(schema.getColumns().get(0), new Condition(Condition.EQUALS, "Equals"));
		model.getConditionValues().put(schema.getColumns().get(0), "R1NPNC1");

		model.refreshData(TableModel.NONE);
		recordIndex = 0;
		assertSame(1, model.getRecords().size());
		for (Record record : model.getRecords()) {
			TableColumn[] cols = new TableColumn[3];
			for (TableColumn col : record.getValues().keySet()) {
				int index = col.getCode().length() - 1;
				int num = Integer.parseInt(col.getCode().substring(index, index + 1));
				cols[num - 1] = col;
				assertEquals("R" + (recordIndex + 1) + "NPWC" + num, record.getValues().get(col));
			}
			for (int i = 0; i < cols.length; i++) {
				assertSame(schema.getColumns().get(i), cols[i]);
			}
			recordIndex++;
		}
		// no conditions, with parent
		model.getConditions().clear();
		model.getConditionValues().clear();

		TableModel parentModel = new TableModel();
		model.setParentTableModel(parentModel);
		TableSchema parentSchema = new TableSchema();
		parentSchema.setCode("Test");
		{
			TableColumn col = new TableColumn();
			col.setCode("RefCol");
			col.setType(Types.VARCHAR);
			parentSchema.getColumns().add(col);
			schema.getColumns().get(0).getReferencedTableColumns().add(col);
			Record parentRecord = new Record();
			parentRecord.getValues().put(col, "RefVal");
			parentModel.getRecords().add(parentRecord);
			parentModel.setTable(new MockupTable());
		}
		TableSchema.getTableSchemas().clear();
		TableSchema.getTableSchemas().put(parentSchema.getCode(), parentSchema);
		parentModel.setTableSchema(parentSchema);
		parentModel.setConnection(new MockupConnection());

		model.refreshData(TableModel.NONE);
		assertSame(2, model.getRecords().size());
		recordIndex = 0;
		for (Record record : model.getRecords()) {
			TableColumn[] cols = new TableColumn[3];
			for (TableColumn col : record.getValues().keySet()) {
				int index = col.getCode().length() - 1;
				int num = Integer.parseInt(col.getCode().substring(index, index + 1));
				cols[num - 1] = col;
				if (num == 1) {
					assertEquals("RefVal", record.getValues().get(col));
				} else {
					assertEquals("R" + (recordIndex + 1) + "WPNC" + num, record.getValues().get(col));
				}
			}
			for (int i = 0; i < cols.length; i++) {
				assertSame(schema.getColumns().get(i), cols[i]);
			}
			recordIndex++;
		}
		// with conditions, with parent
		model.getConditions().put(schema.getColumns().get(1), new Condition(Condition.EQUALS, "Equals"));
		model.getConditionValues().put(schema.getColumns().get(1), "R1WPWC2");

		model.refreshData(TableModel.NONE);
		assertSame(1, model.getRecords().size());
		recordIndex = 0;
		for (Record record : model.getRecords()) {
			TableColumn[] cols = new TableColumn[3];
			for (TableColumn col : record.getValues().keySet()) {
				int index = col.getCode().length() - 1;
				int num = Integer.parseInt(col.getCode().substring(index, index + 1));
				cols[num - 1] = col;
				if (num == 1) {
					assertEquals("RefVal", record.getValues().get(col));
				} else {
					assertEquals("R" + (recordIndex + 1) + "WPWC" + num, record.getValues().get(col));
				}
			}
			for (int i = 0; i < cols.length; i++) {
				assertSame(schema.getColumns().get(i), cols[i]);
			}
			recordIndex++;
		}
	}

	/**
	 * Tests {@link TableModel#getRowCount()} with test {@link Record}s.
	 * 
	 * @see TableModel
	 * @see TableModel#getRowCount()
	 * @see Record
	 */
	@Test
	public void testGetRowCount() {
		assertSame(0, model.getRowCount());
		ArrayList<Record> records = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Record record = new Record();
			records.add(record);
			model.setRecords(records);
			assertSame(i + 1, model.getRowCount());
		}
	}

	/**
	 * Tests {@link TableModel#getValueAt(int, int)} with test {@link Record}s.
	 * 
	 * @see TableModel
	 * @see TableModel#getValueAt(int, int)
	 * @see Record
	 */
	@Test
	public void testGetValueAt() {
		ArrayList<Record> records = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Record record = new Record();
			records.add(record);
			for (int j = 0; j < schema.getColumns().size(); j++) {
				record.getValues().put(schema.getColumns().get(j), "R" + (i + 1) + "C" + (j + 1));
			}
		}
		model.setRecords(records);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < schema.getColumns().size(); j++) {
				assertSame(records.get(i).getValues().get(schema.getColumns().get(j)), model.getValueAt(i, j));
			}
		}
	}

	/**
	 * Tests {@link TableModel#getColumnClass(int)} with several
	 * {@link TableColumn}s of different {@link Types}.
	 * 
	 * @see TableModel
	 * @see TableModel#getColumnClass(int)
	 * @see TableColumn
	 * @see Types
	 */
	@Test
	public void testGetColumnClassInt() {
		schema.getColumns().clear();
		TableColumn col = new TableColumn();
		col.setType(Types.VARCHAR);
		schema.getColumns().add(col);
		assertEquals(String.class, model.getColumnClass(0));

		col = new TableColumn();
		col.setType(Types.INTEGER);
		schema.getColumns().add(col);
		assertEquals(Integer.class, model.getColumnClass(1));

		col = new TableColumn();
		col.setType(Types.DOUBLE);
		schema.getColumns().add(col);
		assertEquals(Double.class, model.getColumnClass(2));
	}

	/**
	 * Tests {@link TableModel#getColumnName(int)} by comparing default
	 * {@link TableColumn}s' names.
	 * 
	 * @see TableModel
	 * @see TableModel#getColumnName(int)
	 * @see TableColumn
	 * @see TableColumn#getName()
	 */
	@Test
	public void testGetColumnNameInt() {
		for (int j = 0; j < schema.getColumns().size(); j++) {
			assertEquals("Col" + (j + 1), model.getColumnName(j));
		}
	}

	/**
	 * Tests {@link TableModel#getColumnCount()} by comparing {@link TableModel}
	 * 's number of columns to {@link TableSchema}'s number of columns.
	 * 
	 * @see TableModel
	 * @see TableModel#getColumnCount()
	 * @see TableSchema
	 * @see TableSchema#getColumns()
	 */
	@Test
	public void testGetColumnCount() {
		assertSame(schema.getColumns().size(), model.getColumnCount());
	}

	/**
	 * Tests {@link TableModel#getName()} by comparing {@link TableModel} 's
	 * name to {@link TableSchema}'s name.
	 * 
	 * @see TableModel
	 * @see TableModel#getName()
	 * @see TableSchema
	 * @see TableSchema#getColumns()
	 */
	@Test
	public void testGetName() {
		assertSame(schema.getName(), model.getName());
	}

	/**
	 * Tests {@link TableModel#getRecords()} with an {@link ArrayList} of
	 * several different {@link Record}s.
	 * 
	 * @see TableModel
	 * @see TableModel#getRecords()
	 * @see TableModel#setRecords(ArrayList)
	 * @see ArrayList
	 * @see Record
	 */
	@Test
	public void testGetRecords() {
		ArrayList<Record> records = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Record record = new Record();
			records.add(record);
			for (int j = 0; j < schema.getColumns().size(); j++) {
				record.getValues().put(schema.getColumns().get(j), "R" + (i + 1) + "C" + (j + 1));
			}
		}
		model.setRecords(records);
		assertSame(records, model.getRecords());
	}

	/**
	 * Tests {@link TableModel#setRecords(ArrayList)} with an {@link ArrayList}
	 * of several different {@link Record}s.
	 * 
	 * @see TableModel
	 * @see TableModel#getRecords()
	 * @see TableModel#setRecords(ArrayList)
	 * @see ArrayList
	 * @see Record
	 */
	@Test
	public void testSetRecords() {
		ArrayList<Record> records = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Record record = new Record();
			records.add(record);
			for (int j = 0; j < schema.getColumns().size(); j++) {
				record.getValues().put(schema.getColumns().get(j), "R" + (i + 1) + "C" + (j + 1));
			}
		}
		model.setRecords(records);
		assertSame(records, model.getRecords());
	}

	/**
	 * Tests {@link TableModel#getTableSchema()} by comparing its result and
	 * inital {@link TableSchema}.
	 * 
	 * @see TableModel
	 * @see TableModel#getTableSchema()
	 * @see TableSchema
	 */
	@Test
	public void testGetTableSchema() {
		assertSame(schema, model.getTableSchema());
	}

	/**
	 * Tests {@link TableModel#setTableSchema(TableSchema)} by comparing the
	 * result of {@link TableModel#getTableSchema()} and newly set
	 * {@link TableSchema}.
	 * 
	 * @see TableModel
	 * @see TableModel#getTableSchema()
	 * @see TableModel#setTableSchema(TableSchema)
	 * @see TableSchema
	 */
	@Test
	public void testSetTableSchema() {
		TableSchema newTableSchema = new TableSchema();
		model.setTableSchema(newTableSchema);
		assertSame(newTableSchema, model.getTableSchema());
	}

	/**
	 * Tests {@link TableModel#getChildTableModels()} with an {@link ArrayList}
	 * of one {@link TableModel}.
	 * 
	 * @see TableModel
	 * @see TableModel#getChildTableModels()
	 * @see TableModel#setChildTableModels(ArrayList)
	 * @see ArrayList
	 */
	@Test
	public void testGetChildTableModels() {
		ArrayList<TableModel> children = new ArrayList<>();
		assertEquals(children, model.getChildTableModels());
		TableModel child = new TableModel();
		children.add(child);
		model.setChildTableModels(children);
		assertSame(children, model.getChildTableModels());
	}

	/**
	 * Tests {@link TableModel#setChildTableModels(ArrayList)} with an
	 * {@link ArrayList} of one {@link TableModel}.
	 * 
	 * @see TableModel
	 * @see TableModel#getChildTableModels()
	 * @see TableModel#setChildTableModels(ArrayList)
	 * @see ArrayList
	 */
	@Test
	public void testSetChildTableModels() {
		ArrayList<TableModel> children = new ArrayList<>();
		TableModel child = new TableModel();
		children.add(child);
		model.setChildTableModels(children);
		assertSame(children, model.getChildTableModels());
	}

	/**
	 * Tests {@link TableModel#getTable()} by comparing its result with a newly
	 * set {@link MockupTable}.
	 * 
	 * @see TableModel
	 * @see TableModel#getTable()
	 * @see TableModel#setTable(Table)
	 * @see Table
	 * @see MockupTable
	 */
	@Test
	public void testGetTable() {
		assertSame(null, model.getTable());
		Table table = new MockupTable();
		model.setTable(table);
		assertSame(table, model.getTable());
	}

	/**
	 * Tests {@link TableModel#setTable(Table)} with a {@link MockupTable} by
	 * comparing it with the result of {@link TableModel#getTable()}
	 * 
	 * @see TableModel
	 * @see TableModel#getTable()
	 * @see TableModel#setTable(Table)
	 * @see Table
	 * @see MockupTable
	 */
	public void testSetTable() {
		Table table = new MockupTable();
		model.setTable(table);
		assertSame(table, model.getTable());
	}

	/**
	 * Tests {@link TableModel#getParentTableModel()} by comparing its result
	 * with a newly set {@link TableModel}.
	 * 
	 * @see TableModel
	 * @see TableModel#getParentTableModel()
	 * @see TableModel#setParentTableModel(TableModel)
	 */
	@Test
	public void testGetParentTableModel() {
		assertSame(null, model.getParentTableModel());
		TableModel table = new TableModel();
		model.setParentTableModel(table);
		assertSame(table, model.getParentTableModel());
	}

	/**
	 * Tests {@link TableModel#setParentTableModel(TableModel)} with a new
	 * {@link TableModel} by comparing it with the return value of
	 * {@link TableModel#getParentTableModel()}.
	 * 
	 * @see TableModel
	 * @see TableModel#getParentTableModel()
	 * @see TableModel#setParentTableModel(TableModel)
	 */
	@Test
	public void testSetParentTableModel() {
		TableModel table = new TableModel();
		model.setParentTableModel(table);
		assertSame(table, model.getParentTableModel());
	}

	/**
	 * Tests static method {@link TableModel#getOpenedTableModels()} by
	 * comparing its result with a newly set {@link ArrayList} of
	 * {@link TableModel}s.
	 * 
	 * @see TableModel
	 * @see TableModel#getOpenedTableModels()
	 * @see TableModel#setOpenedTableModels(ArrayList)
	 * @see ArrayList
	 */
	@Test
	public void testGetOpenedTableModels() {
		ArrayList<TableModel> models = new ArrayList<>();
		models.add(model);
		TableModel.setOpenedTableModels(models);
		assertSame(models, TableModel.getOpenedTableModels());
	}

	/**
	 * Tests static method {@link TableModel#setOpenedTableModels(ArrayList)}
	 * with an {@link ArrayList} of {@link TableModel}s by comparing it with the
	 * result of {@link TableModel#getOpenedTableModels()}.
	 * 
	 * @see TableModel
	 * @see TableModel#getOpenedTableModels()
	 * @see TableModel#setOpenedTableModels(ArrayList)
	 * @see ArrayList
	 */
	@Test
	public void testSetOpenedTableModels() {
		ArrayList<TableModel> models = new ArrayList<>();
		models.add(model);
		TableModel.setOpenedTableModels(models);
		assertSame(models, TableModel.getOpenedTableModels());
	}

	/**
	 * Tests {@link TableModel#getConditionValues()} by comparing its result
	 * with a newly set {@link HashMap} of {@link TableColumn}s and
	 * {@link Object}s.
	 * 
	 * @see TableModel
	 * @see TableModel#getConditionValues()
	 * @see TableModel#setConditionValues(HashMap)
	 * @see HashMap
	 * @see Object
	 */
	@Test
	public void testGetConditionValues() {
		HashMap<TableColumn, Object> conditionValues = new HashMap<TableColumn, Object>();
		assertEquals(conditionValues, model.getConditionValues());
		conditionValues.put(schema.getColumns().get(0), "Test");
		model.setConditionValues(conditionValues);
		assertSame(conditionValues, model.getConditionValues());
	}

	/**
	 * Tests {@link TableModel#setConditionValues(HashMap)} with a
	 * {@link HashMap} of {@link TableColumn}s and {@link Object}s by comparing
	 * it with the result of {@link TableModel#getConditionValues()}.
	 * 
	 * @see TableModel
	 * @see TableModel#getConditionValues()
	 * @see TableModel#setConditionValues(HashMap)
	 * @see HashMap
	 * @see Object
	 */
	@Test
	public void testSetConditionValues() {
		HashMap<TableColumn, Object> conditionValues = new HashMap<TableColumn, Object>();
		conditionValues.put(schema.getColumns().get(0), "Test");
		model.setConditionValues(conditionValues);
		assertSame(conditionValues, model.getConditionValues());
	}

	/**
	 * Tests {@link TableModel#getConditions()} by comparing its result with a
	 * newly set {@link HashMap} of {@link TableColumn}s and {@link Condition}s.
	 * 
	 * @see TableModel
	 * @see TableModel#getConditions()
	 * @see TableModel#setConditions(HashMap)
	 * @see HashMap
	 * @see Condition
	 */
	@Test
	public void testGetConditions() {
		HashMap<TableColumn, Condition> conditions = new HashMap<TableColumn, Condition>();
		assertEquals(conditions, model.getConditions());
		conditions.put(schema.getColumns().get(0), new Condition(0, "Test"));
		model.setConditions(conditions);
		assertSame(conditions, model.getConditions());
	}

	/**
	 * Tests {@link TableModel#setConditions(HashMap)} with a {@link HashMap} of
	 * {@link TableColumn}s and {@link Condition}s by comparing it with the
	 * result of {@link TableModel#getConditions()}.
	 *
	 * @see TableModel
	 * @see TableModel#getConditions()
	 * @see TableModel#setConditions(HashMap)
	 * @see HashMap
	 * @see Condition
	 */
	@Test
	public void testSetConditions() {
		HashMap<TableColumn, Condition> conditions = new HashMap<TableColumn, Condition>();
		conditions.put(schema.getColumns().get(0), new Condition(0, "Test"));
		model.setConditions(conditions);
		assertSame(conditions, model.getConditions());
	}

	/**
	 * Tests {@link TableModel#findParentRefColValue(TableColumn)} with the
	 * first {@link TableColumn} of default {@link TableSchema} and a newly set
	 * parent {@link TableModel} whose {@link TableColumn} is referenced by the
	 * tested {@link TableModel}'s {@link TableColumn}. Parent
	 * {@link TableModel} contains one test {@link Record}.
	 *
	 * @see TableModel
	 * @see TableModel#findParentRefColValue(TableColumn)
	 * @see TableSchema
	 * @see TableSchema#getColumns()
	 * @see TableColumn
	 * @see TableColumn#getReferencedTableColumns()
	 * @see Record
	 */
	@Test
	public void testFindParentRefColValue() {
		assertSame(null, model.findParentRefColValue(schema.getColumns().get(0)));
		TableModel parentModel = new TableModel();
		model.setParentTableModel(parentModel);
		assertSame(null, model.findParentRefColValue(schema.getColumns().get(0)));

		TableSchema parentSchema = new TableSchema();
		parentModel.setTableSchema(parentSchema);
		{
			TableColumn col = new TableColumn();
			col.setCode("RefCol");
			parentSchema.getColumns().add(col);
			Record record = new Record();
			record.getValues().put(col, "RefVal");
			parentModel.getRecords().add(record);
			schema.getColumns().get(0).getReferencedTableColumns().add(col);
		}
		parentModel.setTable(new MockupTable());
		assertEquals("RefVal", model.findParentRefColValue(schema.getColumns().get(0)));
	}

	/**
	 * Tests
	 * {@link TableModel#checkOtherReferencesToSameColumn(TableColumn, TableColumn)}
	 * with {@link TableColumn}s of default {@link TableSchema} and a test
	 * {@link TableColumn} which is referenced by one of the default
	 * {@link TableColumn}s.
	 *
	 * @see TableModel
	 * @see TableModel#checkOtherReferencesToSameColumn(TableColumn,
	 *      TableColumn)
	 * @see TableColumn
	 * @see TableColumn#getReferencedTableColumns()
	 * @see TableSchema
	 */
	@Test
	public void testCheckOtherReferencesToSameColumn() {
		TableColumn refCol = new TableColumn();
		for (TableColumn col : schema.getColumns()) {
			assertSame(false, model.checkOtherReferencesToSameColumn(col, refCol));
		}
		schema.getColumns().get(0).getReferencedTableColumns().add(refCol);
		for (int i = 0; i < schema.getColumns().size(); i++) {
			TableColumn col = schema.getColumns().get(i);
			if (i == 0) {
				assertSame(false, model.checkOtherReferencesToSameColumn(col, refCol));
			} else {
				assertSame(true, model.checkOtherReferencesToSameColumn(col, refCol));
			}
		}
	}

	/**
	 * Tests {@link TableModel#getConnection()} by comparing its result with
	 * default {@link MockupConnection}.
	 * 
	 * @see TableModel
	 * @see TableModel#getConnection()
	 * @see MockupConnection
	 * @see Connection
	 */
	@Test
	public void testGetConnection() {
		assertSame(connection, model.getConnection());
	}

	/**
	 * Tests {@link TableModel#setConnection(Connection)} with a new
	 * {@link MockupConnection} by comparing it with the result of
	 * {@link TableModel#getConnection()}.
	 * 
	 * @see TableModel
	 * @see TableModel#getConnection()
	 * @see TableModel#setConnection(Connection)
	 * @see MockupConnection
	 * @see Connection
	 */
	@Test
	public void testSetConnection() {
		MockupConnection connection = new MockupConnection();
		model.setConnection(connection);
		assertSame(connection, model.getConnection());
	}

}
