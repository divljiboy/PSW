package model;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Class representing a column of a {@link TableSchema}.
 * It contains all metadata for one column.
 * That includes data as name, code, size and references to {@link JsonNode} from the {@link TreeModel} and {@link TableSchema}.
 * 
 * @author Milan Radeta
 *
 */
public class TableColumn {
	private TableSchema table;
	private String code;
	private String name;
	private boolean primaryKey;
	private boolean nullable;
	private int type;
	private int size;
	private int decimalDigits;
	private TableColumn semanticTableColumn;
	private ArrayList<TableColumn> referencedTableColumns = new ArrayList<>();

	/**
	 * Default constructor for {@link TableColumn}.
	 * Does nothing.
	 */
	public TableColumn() {
		
	}
	
	/**
	 * Constructor for {@link TableColumn} using table metadata.
	 * Parameter table is a reference to the parent {@link TableSchema}.
	 * Parameter rsColumns is the {@link ResultSet} which contains metadata for the table.
	 * 
	 * @param table
	 * @param rsColumns
	 * @throws SQLException
	 */
	public TableColumn(TableSchema table, ResultSet rsColumns) throws SQLException {
		setTable(table);
		setCode(rsColumns.getString("COLUMN_NAME"));
		setNullable(rsColumns.getInt("NULLABLE") == DatabaseMetaData.columnNoNulls ? false : true);
		setType(rsColumns.getInt("DATA_TYPE"));
		setSize(rsColumns.getInt("COLUMN_SIZE"));
		setDecimalDigits(rsColumns.getInt("DECIMAL_DIGITS"));
	}

	/**
	 * Getter method for the parent {@link TableSchema}.
	 * @return {@link TableSchema}.
	 */
	public TableSchema getTable() {
		return table;
	}

	/**
	 * Setter method for the parent {@link TableSchema}.
	 * @param table
	 */
	public void setTable(TableSchema table) {
		this.table = table;
	}

	/**
	 * Getter method for the column code used in the database.
	 * @return {@link String}
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Getter method for the column code used in the database.
	 * Parameter code is a {@link String}.
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter method for the name of the column used by the application GUI.
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for the name of the column used by the application GUI.
	 * Parameter name is a {@link String}.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method returning information whether this column is the primary key of the parent table.  
	 * @return boolean
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Setter method for information whether this column is the primary key of the parent table.
	 * Parameter primary key is a boolean.
	 * @param primaryKey
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Getter method returning information whether this column can hold a null value.
	 * @return boolean
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * Setter method for information whether this column can hold a null value.
	 * @param nullable
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * Getter method for the type of the column value.
	 * Type is an integer from the {@link Types} class.
	 * @return int
	 */
	public int getType() {
		return type;
	}

	/**
	 * Setter method for the type of the column value.
	 * Type is an integer from the {@link Types} class.
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Getter for the size of the column value.
	 * @return int
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Setter for the size of the column value.
	 * Parameter size in an integer.
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Getter returning number of decimal point of the column value.
	 * @return int
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}

	/**
	 * Setter for number of decimal point of the column value.
	 * Paramenter decimalDigits is an integer.
	 * @param decimalDigits
	 */
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	/**
	 * Getter for the {@link TableColumn} containing the semantic meaning of this column value.
	 * @return {@link TableColumn}
	 */
	public TableColumn getSemanticTableColumn() {
		return semanticTableColumn;
	}

	/**
	 * Setter for the {@link TableColumn} containing the semantic meaning of this column value.
	 * @param semanticTableColumn
	 */
	public void setSemanticTableColumn(TableColumn semanticTableColumn) {
		this.semanticTableColumn = semanticTableColumn;
	}

	/**
	 * Getter for columns referenced by this {@link TableColumn}.
	 * Returns an {@link ArrayList} of {@code TableColumn} objects.
	 * @return {@link ArrayList}
	 */
	public ArrayList<TableColumn> getReferencedTableColumns() {
		return referencedTableColumns;
	}

	/**
	 * Setter for columns referenced by this {@link TableColumn}.
	 * Parameter referencedTableColumns is an {@link ArrayList} of {@code TableColumn} objects.
	 * @param referencedTableColumns
	 */
	public void setReferencedTableColumns(ArrayList<TableColumn> referencedTableColumns) {
		this.referencedTableColumns = referencedTableColumns;
	}

}
