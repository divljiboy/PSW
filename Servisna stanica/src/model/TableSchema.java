package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import com.fasterxml.jackson.databind.JsonNode;

import application.Application;
import database.DatabaseConnection;

/**
 * Class containing basic information on a table. Information includes name,
 * code used in the database and reference to {@link TreeNode} from the
 * {@link TreeModel} representing this table. Columns and child tables are held
 * in {@link ArrayList} objects.
 * 
 * @author Milan Radeta
 *
 */
public class TableSchema {
	private static HashMap<String, TableSchema> tableSchemas = new HashMap<String, TableSchema>();

	private String name;
	private String code;
	private ArrayList<TableColumn> columns = new ArrayList<>();
	private TreeNode node;
	private Connection connection;

	/**
	 * Empty constructor.
	 */
	public TableSchema() {}
	
	/**
	 * Constructor of {@link TableSchema}. Parameter node gets a
	 * {@link TreeNode} which is used to reference this table get all necessary
	 * information on the table schema from the database.
	 * 
	 * @param node
	 * @throws SQLException
	 */
	public TableSchema(TreeNode node) throws SQLException {
		this(node.getNode().get("code").asText());
		setNode(node);
	}

	/**
	 * Constructor of {@link TableSchema}. Parameter code gets a {@link String}
	 * which is used to reference this table get all necessary information on
	 * the table schema from the database.
	 * 
	 * @param code
	 * @throws SQLException
	 */
	public TableSchema(String code) throws SQLException {
		tableSchemas.put(code, this);
		setCode(code);
		connection = DatabaseConnection.getInstance().getConn();
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet rsColumns = meta.getColumns(null, null, code, null);
		while (rsColumns.next()) {
			TableColumn column = new TableColumn(this, rsColumns);
			columns.add(column);
		}
		updateColumnNames();
		setPrimaryKeys();
		setForeignKeys();
		rsColumns.close();
	}

	/**
	 * Checks in the database which {@link TableColumn} objects are primary keys
	 * and sets them as primary.
	 * 
	 * @throws SQLException
	 */
	public void setPrimaryKeys() throws SQLException {
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet rsPrimaryKeys = meta.getPrimaryKeys(null, null, code);
		while (rsPrimaryKeys.next()) {
			String colName = rsPrimaryKeys.getString("COLUMN_NAME");
			findColumn(colName).setPrimaryKey(true);
		}
		rsPrimaryKeys.close();
	}

	/**
	 * Checks in the database which {@link TableColumn} objects are foreign keys
	 * and sets them as foreign.
	 * 
	 * @throws SQLException
	 */
	public void setForeignKeys() throws SQLException {
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet rsForeignKeys = meta.getImportedKeys(null, null, code);
		while (rsForeignKeys.next()) {
			String pkTableName = rsForeignKeys.getString("PKTABLE_NAME");
			String pkColumnName = rsForeignKeys.getString("PKCOLUMN_NAME");
			String fkColumnName = rsForeignKeys.getString("FKCOLUMN_NAME");
			TableColumn tableColumn = findColumn(fkColumnName);
			TableSchema referencedTable = getTable(pkTableName);
			TableColumn referencedTableColumn = referencedTable.findColumn(pkColumnName);
			tableColumn.getReferencedTableColumns().add(referencedTableColumn);
		}
		rsForeignKeys.close();
	}

	/**
	 * Returns the {@link TableColumn} with the specified name {@link String}.
	 * 
	 * @param code
	 * @return {@link TableColumn}
	 */
	public TableColumn findColumn(String code) {
		for (TableColumn column : columns) {
			if (column.getCode().equals(code)) {
				return column;
			}
		}
		return null;
	}

	/**
	 * Given the {@link String} {@code code}, it finds and returns the {@link TableSchema} with said {@code code}.
	 * If there is no such {@code TableSchema}, a new {@code TableSchema} is created.
	 *  
	 * @param code
	 * @return {@link TableSchema}
	 * @throws SQLException
	 */
	public static TableSchema getTable(String code) throws SQLException {
		if (tableSchemas.containsKey(code)) {
			return tableSchemas.get(code);
		} else {
			return new TableSchema(code);
		}
	}

	/**
	 * Given the {@link TreeNode} {@code node}, it finds and returns the {@link TableSchema} containing the same {@code code} {@code node} has.
	 * If there is no such {@code TableSchema}, a new {@code TableSchema} is created.
	 * 
	 * @param node
	 * @return {@link TableSchema}
	 * @throws SQLException
	 */
	public static TableSchema getTable(TreeNode node) throws SQLException {
		String code = node.getNode().get("code").asText();
		if (tableSchemas.containsKey(code)) {
			TableSchema schema = tableSchemas.get(code);
			schema.setNode(node);
			return schema;
		} else {
			return new TableSchema(node);
		}
	}

	/**
	 * Returns all {@link TableSchema} objects in a {@link HashMap} having {@link String} keys and {@link TableSchema} values.
	 * @return {@link HashMap}
	 */
	public static HashMap<String, TableSchema> getTableSchemas() {
		return tableSchemas;
	}

	/**
	 * Sets all {@link TableSchema} objects.
	 * Parameter {@code tableSchema} is a {@link HashMap} having {@link String} keys and {@link TableSchema} values.
	 * @param tableSchemas
	 */
	public static void setTableSchemas(HashMap<String, TableSchema> tableSchemas) {
		TableSchema.tableSchemas = tableSchemas;
	}

	/**
	 * Returns the name of the {@link TableSchema}.
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the {@link TableSchema}.
	 * Parameter {@code name} is a {@link String}.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the database code of the {@link TableSchema}.
	 * @return {@link String}
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the database code of the {@link TableSchema}.
	 * Parameter {@code code} is a {@link String}.
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Returns all {@link TableColumn} objects of the {@link TableSchema}.
	 * @return {@link ArrayList}
	 */
	public ArrayList<TableColumn> getColumns() {
		return columns;
	}

	/**
	 * Sets all {@link TableColumn} objects of the {@link TableSchema}.
	 * Parameter {@code columns} is an {@link ArrayList} of {@link TableColumn} objects.
	 * @param columns
	 */
	public void setColumns(ArrayList<TableColumn> columns) {
		this.columns = columns;
	}

	/**
	 * Returns the {@link TreeNode} object corresponding to this {@link TableSchema}.
	 * @return {@link TreeNode}
	 */
	public TreeNode getNode() {
		return node;
	}

	/**
	 * Sets the {@link TreeNode} object corresponding to this {@link TableSchema}.
	 * @param node
	 */
	public void setNode(TreeNode node) {
		this.node = node;
		name = Application.getResourceBundle().getString("Table_" + code);
		JsonNode columns = node.getNode().get("columns");
		if (columns != null) {
			// Iterate through nodes and get column info.
			for (JsonNode col : columns) {
				for (TableColumn tableColumn : this.columns) {
					if (tableColumn.getCode().equals(col.get("code").asText())) {
						if (col.get("semantic") != null) {
							TableColumn semanticTableColumn = findColumn(col.get("semantic").asText());
							if (semanticTableColumn != null) {
								tableColumn.setSemanticTableColumn(semanticTableColumn);
							}
						}
						break;
					}
				}
			}
		}
	}

	/**
	 * When the {@link Locale} of the application is changed, this method is used to update the names of the tables to the said {@code Locale}.
	 */
	public void updateColumnNames() {
		name = Application.getResourceBundle().getString("Table_" + code);
		for (TableColumn column : columns) {
			column.setName(Application.getResourceBundle()
					.getString("Column_" + code.toUpperCase() + "_" + column.getCode().toUpperCase()));
		}
	}

	/**
	 * Returns {@link TableSchema}'s reference to {@link Connection}
	 * @see Connection
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets {@link TableSchema}'s reference to {@link Connection}
	 * @see Connection
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	
	
}
