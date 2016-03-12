package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import application.Application;
import database.DatabaseConnection;
import gui.tables.Table;
import gui.tables.forms.QueryForm;

/**
 * Extension of {@link AbstractTableModel} class. Contains references to
 * {@link TableSchema} with information on table columns, the parent
 * {@link TableModel} and the GUI representation {@link Table}.
 * {@code TableModel} holds all table rows in form of {@link Record} objects and
 * query conditions and condition values applicable to this table in
 * {@link HashMap} objects.
 * 
 * @author Milan Radeta
 *
 */
public class TableModel extends AbstractTableModel {
	// table model for parent table
	private static final long serialVersionUID = 1L;

	public final static int NONE = 0;
	public final static int ADD = 1;
	public final static int MODIFY = 2;
	public final static int REMOVE = 3;

	private static ArrayList<TableModel> openedTableModels = new ArrayList<>();

	private TableSchema tableSchema;
	private Table table;

	private ArrayList<Record> records = new ArrayList<>();
	private TableModel parentTableModel = null;
	private ArrayList<TableModel> childTableModels = new ArrayList<TableModel>();

	private HashMap<TableColumn, Object> conditionValues = new HashMap<>();
	private HashMap<TableColumn, Condition> conditions = new HashMap<>();

	private Connection connection;

	/**
	 * Empty constructor.
	 */
	public TableModel() {
	}

	/**
	 * {@link TableModel} constructor. Gets column names and codes from provided
	 * {@link TreeNode} that represents the database table using
	 * {@link PreparedStatement} objects. Acquires all rows from the database
	 * table the node represents. Alerts the user if the table does not exist in
	 * the database.
	 * 
	 * @param node
	 * @param table
	 * @param parentTableModel
	 * @see TreeNode
	 * @throws SQLException
	 * 
	 */
	public TableModel(TreeNode node, Table table, TableModel parentTableModel) {
		super();
		this.table = table;
		this.parentTableModel = parentTableModel;
		this.connection = DatabaseConnection.getInstance().getConn();
		try {
			tableSchema = TableSchema.getTable(node);
		} catch (SQLException e) {
			Application.showSqlExceptionError(e);
		}
		refreshData(TableModel.NONE);
		if (parentTableModel == null) {
			openedTableModels.add(this);
		}
	}

	/**
	 * Method for getting rows from the database. If an advanced search was
	 * performed on this table, all conditions will be preserved. Parameter
	 * action is an integer whose valid values are static fields of this class
	 * (ADD, MODIFY, REMOVE and NONE).
	 * 
	 * @param action
	 */
	public void refreshData(int action) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String code = tableSchema.getCode();
		ArrayList<TableColumn> columns = tableSchema.getColumns();
		ArrayList<Record> oldRecords = new ArrayList<Record>(records);
		if (parentTableModel == null) {
			// Try query.
			try {
				StringBuilder query = new StringBuilder("SELECT ");
				int count = 0;
				ArrayList<String> tables = new ArrayList<>();
				tables.add(code);
				ArrayList<String> tableAliases = new ArrayList<>();
				tableAliases.add("tbl" + (++count));
				ArrayList<String> joinConditions = new ArrayList<>();
				joinConditions.add(null);
				ArrayList<String> whereConditions = new ArrayList<String>();
				ArrayList<Object> whereConditionValues = new ArrayList<Object>();

				for (TableColumn column : columns) {
					query.append(tableAliases.get(0) + "." + column.getCode() + ", ");
					if (conditionValues.containsKey(column) && conditions.containsKey(column)) {
						Condition condition = conditions.get(column);
						Object value = conditionValues.get(column);
						switch (column.getType()) {
						case Types.CHAR:
						case Types.NCHAR:
						case Types.VARCHAR:
						case Types.NVARCHAR:
						case Types.LONGVARCHAR:
						case Types.LONGNVARCHAR:
							switch (condition.getValue()) {
							case Condition.STARTS_WITH:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " LIKE ?");
								whereConditionValues.add(value + "%");
								break;
							case Condition.ENDS_WITH:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " LIKE ?");
								whereConditionValues.add("%" + value);
								break;
							case Condition.CONTAINS:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " LIKE ?");
								whereConditionValues.add("%" + value + "%");
								break;
							case Condition.EQUALS:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " LIKE ?");
								whereConditionValues.add(value);
								break;
							case Condition.NOT_EQUAL_TO:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " NOT LIKE ?");
								whereConditionValues.add(value);
								break;
							}
							break;
						// Numeric fields:
						case Types.INTEGER:
						case Types.SMALLINT:
						case Types.BIGINT:
						case Types.TINYINT:
						case Types.NUMERIC:
						case Types.DECIMAL:
						case Types.DOUBLE:
						case Types.FLOAT:
						case Types.REAL:
							switch (condition.getValue()) {
							case Condition.EQUALS:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " = ?");
								whereConditionValues.add(value);
								break;
							case Condition.NOT_EQUAL_TO:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " <> ?");
								whereConditionValues.add(value);
								break;
							case Condition.GREATER:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " > ?");
								whereConditionValues.add(value);
								break;
							case Condition.GREATER_OR_EQUAL:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " >= ?");
								whereConditionValues.add(value);
								break;
							case Condition.LESSER:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " < ?");
								whereConditionValues.add(value);
								break;
							case Condition.LESSER_OR_EQUAL:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " <= ?");
								whereConditionValues.add(value);
								break;
							}
							break;
						// Temporal fields:
						case Types.DATE:
						case Types.TIME:
						case Types.TIMESTAMP:
							switch (condition.getValue()) {
							case Condition.EQUALS:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " = ?");
								whereConditionValues.add(value);
								break;
							case Condition.NOT_EQUAL_TO:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " <> ?");
								whereConditionValues.add(value);
								break;
							case Condition.AFTER:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " >= ?");
								whereConditionValues.add(value);
								break;
							case Condition.BEFORE:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " <= ?");
								whereConditionValues.add(value);
								break;
							}
							break;
						}
					} else if (conditions.containsKey(column)
							&& (column.getType() == Types.BIT || column.getType() == Types.BOOLEAN)) {
						Condition condition = conditions.get(column);
						switch (condition.getValue()) {
						case Condition.TRUE:
							whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " = ?");
							whereConditionValues.add(true);
							break;
						case Condition.FALSE:
							whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " = ?");
							whereConditionValues.add(false);
							break;
						}
					}
					/*
					 * if (column.getReferencedTableColumn() != null) { if
					 * (!tables.contains(column.getReferencedTable().getCode())
					 * || column.getReferencedTable().getCode().equals(code)) {
					 * tables.add(column.getReferencedTable().getCode()); String
					 * alias = "tbl" + (++count); tableAliases.add(alias);
					 * StringBuilder joinCondition = new StringBuilder(); for
					 * (TableColumn col : columns) { if
					 * (col.getReferencedTable() != null &&
					 * col.getReferencedTable().getCode()
					 * .equals(column.getReferencedTable().getCode())) {
					 * joinCondition.append(tableAliases.get(0) + "." +
					 * col.getCode() + " = " + alias + "." +
					 * col.getReferencedTableColumn().getCode() + " and "); } }
					 * joinCondition.replace(joinCondition.length() - 5,
					 * joinCondition.length(), ""); joinConditions.add(new
					 * String(joinCondition)); } }
					 */
				}

				query.replace(query.length() - 2, query.length(), " ");
				query.append("FROM");
				for (int index = 0; index < tables.size(); index++) {
					if (index > 0) {
						query.append(" LEFT OUTER JOIN");
					}
					query.append(" " + tables.get(index) + " AS " + tableAliases.get(index));
					if (index > 0) {
						query.append(" ON " + joinConditions.get(index));
					}
				}

				if (!whereConditions.isEmpty()) {
					query.append(" WHERE ");
					for (String whereCondition : whereConditions) {
						query.append(whereCondition + " and ");
					}
					query.replace(query.length() - 5, query.length(), "");
				}
				statement = connection.prepareStatement(new String(query));
				int paramIndex = 0;
				for (Object whereValue : whereConditionValues) {
					paramIndex++;
					statement.setObject(paramIndex, whereValue);
				}
				resultSet = statement.executeQuery();
			} catch (SQLException e) {
				Application.showSqlExceptionError(e);
				return;
			}
		} else {
			if (parentTableModel.getTable().getSelectedRowCount() == 1) {
				// Try query.
				try {
					StringBuilder query = new StringBuilder("SELECT ");
					int count = 0;
					ArrayList<String> tables = new ArrayList<>();
					tables.add(code);
					ArrayList<String> tableAliases = new ArrayList<>();
					tableAliases.add("tbl" + (++count));
					ArrayList<String> joinConditions = new ArrayList<>();
					joinConditions.add(null);
					ArrayList<String> whereConditions = new ArrayList<String>();
					ArrayList<Object> whereConditionValues = new ArrayList<Object>();

					HashMap<TableColumn, ArrayList<String>> foreignKeys = new HashMap<>();
					HashMap<TableColumn, Object> foreignValues = new HashMap<TableColumn, Object>();

					for (TableColumn column : columns) {
						query.append(tableAliases.get(0) + "." + column.getCode() + ", ");
						if (column.getReferencedTableColumns().size() > 0) {
							for (TableColumn col : column.getReferencedTableColumns()) {
								if (parentTableModel.getTableSchema().getColumns().contains(col)) {
									if (!foreignKeys.containsKey(col)) {
										foreignKeys.put(col, new ArrayList<String>());
									}
									ArrayList<Record> records = parentTableModel.getRecords();
									int row = parentTableModel.getTable()
											.convertRowIndexToModel(parentTableModel.getTable().getSelectedRow());
									foreignKeys.get(col).add(tableAliases.get(0) + "." + column.getCode() + " = ?");
									foreignValues.put(col, records.get(row).getValues().get(col));
								}
							}
						}

						if (conditionValues.containsKey(column) && conditions.containsKey(column)) {
							Condition condition = conditions.get(column);
							Object value = conditionValues.get(column);
							switch (column.getType()) {
							case Types.CHAR:
							case Types.NCHAR:
							case Types.VARCHAR:
							case Types.NVARCHAR:
							case Types.LONGVARCHAR:
							case Types.LONGNVARCHAR:
								switch (condition.getValue()) {
								case Condition.STARTS_WITH:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " LIKE ?");
									whereConditionValues.add(value + "%");
									break;
								case Condition.ENDS_WITH:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " LIKE ?");
									whereConditionValues.add("%" + value);
									break;
								case Condition.CONTAINS:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " LIKE ?");
									whereConditionValues.add("%" + value + "%");
									break;
								case Condition.EQUALS:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " LIKE ?");
									whereConditionValues.add(value);
									break;
								case Condition.NOT_EQUAL_TO:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " NOT LIKE ?");
									whereConditionValues.add(value);
									break;
								}
								break;
							// Numeric fields:
							case Types.INTEGER:
							case Types.SMALLINT:
							case Types.BIGINT:
							case Types.TINYINT:
							case Types.NUMERIC:
							case Types.DECIMAL:
							case Types.DOUBLE:
							case Types.FLOAT:
							case Types.REAL:
								switch (condition.getValue()) {
								case Condition.EQUALS:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " = ?");
									whereConditionValues.add(value);
									break;
								case Condition.NOT_EQUAL_TO:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " <> ?");
									whereConditionValues.add(value);
									break;
								case Condition.GREATER:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " > ?");
									whereConditionValues.add(value);
									break;
								case Condition.GREATER_OR_EQUAL:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " >= ?");
									whereConditionValues.add(value);
									break;
								case Condition.LESSER:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " < ?");
									whereConditionValues.add(value);
									break;
								case Condition.LESSER_OR_EQUAL:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " <= ?");
									whereConditionValues.add(value);
									break;
								}
								break;
							// Temporal fields:
							case Types.DATE:
							case Types.TIME:
							case Types.TIMESTAMP:
								switch (condition.getValue()) {
								case Condition.AFTER:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " >= ?");
									whereConditionValues.add(value);
									break;
								case Condition.BEFORE:
									whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " <= ?");
									whereConditionValues.add(value);
									break;
								}
								break;
							}
						} else if (conditions.containsKey(column)
								&& (column.getType() == Types.BIT || column.getType() == Types.BOOLEAN)) {
							Condition condition = conditions.get(column);
							switch (condition.getValue()) {
							case Condition.TRUE:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " = ?");
								whereConditionValues.add(true);
								break;
							case Condition.FALSE:
								whereConditions.add(tableAliases.get(0) + "." + column.getCode() + " = ?");
								whereConditionValues.add(false);
								break;
							}
						}
					}

					query.replace(query.length() - 2, query.length(), " ");
					query.append("FROM");
					for (int index = 0; index < tables.size(); index++) {
						if (index > 0) {
							query.append(" LEFT OUTER JOIN");
						}
						query.append(" " + tables.get(index) + " AS " + tableAliases.get(index));
						if (index > 0) {
							query.append(" ON " + joinConditions.get(index));
						}
					}
					if (!foreignKeys.isEmpty() || !whereConditions.isEmpty()) {
						query.append(" WHERE ");
					}
					int index = -1;
					for (Entry<TableColumn, ArrayList<String>> entry : foreignKeys.entrySet()) {
						index++;
						if (index == 0) {
							query.append("(");
						}
						if (entry.getValue().size() > 1) {
							query.append("(");
						}
						for (int i = 0; i < entry.getValue().size(); i++) {
							query.append(entry.getValue().get(i));
							if (i < entry.getValue().size() - 1) {
								query.append(" or ");
							}
						}
						if (entry.getValue().size() > 1) {
							query.append(")");
						}

						if (index < foreignKeys.size() - 1) {
							query.append(" and ");
						} else {
							query.append(")");
						}
					}
					if (!foreignKeys.isEmpty() && !whereConditions.isEmpty()) {
						query.append(" and ");
					}
					for (index = 0; index < whereConditions.size(); index++) {
						if (index == 0) {
							query.append("(");
						}
						query.append(whereConditions.get(index));
						if (index < whereConditions.size() - 1) {
							query.append(" and ");
						} else {
							query.append(")");
						}
					}
					statement = connection.prepareStatement(new String(query));
					int paramIndex = 0;

					for (Entry<TableColumn, ArrayList<String>> entry : foreignKeys.entrySet()) {
						for (int i = 0; i < entry.getValue().size(); i++) {
							paramIndex++;
							statement.setObject(paramIndex, foreignValues.get(entry.getKey()));
						}
					}
					for (Object value : whereConditionValues) {
						paramIndex++;
						statement.setObject(paramIndex, value);
					}
					resultSet = statement.executeQuery();
				} catch (SQLException e) {
					Application.showSqlExceptionError(e);
					return;
				}
			} else {
				records.clear();
			}
		}
		if (resultSet != null) {
			// Get data from result set.
			try {
				records = new ArrayList<>();
				while (resultSet.next()) {
					Record record = new Record();
					int colIndex = 0;
					for (TableColumn column : columns) {
						record.getValues().put(column, resultSet.getObject(++colIndex));
						/*
						 * if (column.getReferencedTableColumn() != null) { //
						 * record.getSemanticValues().put(column, //
						 * resultSet.getObject(++colIndex)); }
						 */
					}
					records.add(record);
				}
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				Application.showSqlExceptionError(e);
			}

		}
		boolean fired = false;
		switch (action) {
		case ADD:
			for (int i = 0; i < oldRecords.size(); i++) {
				if (!records.get(i).compareValues(oldRecords.get(i))) {
					fireTableRowsInserted(i, i);
					fired = true;
					break;
				}
			}
			if (!fired) {
				fireTableRowsInserted(records.size() - 1, records.size() - 1);
			}
			break;
		case MODIFY:
			fireTableDataChanged();
			break;
		case REMOVE:
			for (int i = 0; i < records.size(); i++) {
				if (!records.get(i).compareValues(oldRecords.get(i))) {
					fireTableRowsDeleted(i, i);
					fired = true;
					break;
				}
			}
			if (!fired) {
				fireTableRowsDeleted(records.size(), records.size());
			}
			break;
		case NONE:
			if (oldRecords.size() != records.size()) {
				fireTableDataChanged();
			} else {
				for (int i = 0; i < oldRecords.size(); i++) {
					if (!records.get(i).compareValues(oldRecords.get(i))) {
						fireTableDataChanged();
						break;
					}
				}
			}
			break;
		}
	}

	/**
	 * Implemented method that returns the size of the {@link ArrayList} of
	 * {@link Record}s.
	 * 
	 * @see ArrayList
	 * @see Record
	 */
	public int getRowCount() {
		if (records != null) {
			return records.size();
		}
		return 0;
	}

	/**
	 * Implemented method that returns the value {@link Record} at
	 * {@code rowIndex} in {@link #records} {@link ArrayList} for
	 * {@link TableColumn} at {@code columnIndex} in {@link #tableSchema}'s list
	 * of {@link TableColumn}s.
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 * @see Record
	 * @see ArrayList
	 * @see TableColumn
	 * @see TableSchema
	 * @see TableSchema#getColumns()
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		TableColumn column = tableSchema.getColumns().get(columnIndex);
		/*
		 * if (column.getReferencedTableColumn() != null) { return
		 * records.get(rowIndex).getSemanticValues().get(column); }
		 */
		Object value = records.get(rowIndex).getValues().get(column);
		Locale locale = Application.getLocale();
		Calendar cal = Calendar.getInstance(locale);
		switch (column.getType()) {
		case Types.DATE:
			if (value != null) {
				cal.setTime((java.sql.Date) value);
				SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.DEFAULT,
						locale);
				format.setCalendar(cal);
				return format.format(cal.getTime());
			} else
				return null;
		case Types.TIME:
			if (value != null) {
				cal.setTime((java.sql.Time) value);
				SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(SimpleDateFormat.DEFAULT,
						locale);
				format.setCalendar(cal);
				return format.format(cal.getTime());
			} else
				return null;
		case Types.TIMESTAMP:
			if (value != null) {
				cal.setTime((java.sql.Timestamp) value);
				SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat
						.getDateTimeInstance(SimpleDateFormat.DEFAULT, SimpleDateFormat.DEFAULT, locale);
				format.setCalendar(cal);
				return format.format(cal.getTime());
			} else
				return null;
		}
		return records.get(rowIndex).getValues().get(column);
	}

	@Override
	public int findColumn(String columnName) {
		// TODO Auto-generated method stub
		return super.findColumn(columnName);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		TableColumn column = tableSchema.getColumns().get(columnIndex);
		int columnType = column.getType();
		switch (columnType) {
		case Types.CHAR:
		case Types.NCHAR:
		case Types.VARCHAR:
		case Types.NVARCHAR:
		case Types.LONGVARCHAR:
		case Types.LONGNVARCHAR:
			return String.class;
		case Types.INTEGER:
		case Types.SMALLINT:
		case Types.BIGINT:
		case Types.TINYINT:
			return Integer.class;
		case Types.NUMERIC:
		case Types.DECIMAL:
			if (column.getDecimalDigits() == 0) {
				return Integer.class;
			}
		case Types.DOUBLE:
		case Types.FLOAT:
		case Types.REAL:
			return Double.class;
		case Types.BIT:
		case Types.BOOLEAN:
			return Boolean.class;
		case Types.DATE:
		case Types.TIME:
		case Types.TIMESTAMP:
			return Calendar.class;
		}
		return super.getColumnClass(columnIndex);
	}

	/**
	 * Implemented method that returns the name of {@link TableColumn} at index
	 * {@code column} in {@link #tableSchema}'s {@link TableColumn}s.
	 * @param column
	 */
	@Override
	public String getColumnName(int column) {
		return tableSchema.getColumns().get(column).getName();
	}

	/**
	 * Implemented method that always returns false.
	 * @param rowIndex
	 * @param columnIndex
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	/**
	 * Implemented method that returns the size of {@link #tableSchema}'s
	 * {@link ArrayList} of {@link TableColumn}s.
	 */
	@Override
	public int getColumnCount() {
		return tableSchema.getColumns().size();
	}

	/**
	 * Returns the name of the table from the {@link TableSchema} of this model.
	 * 
	 * @return {@link String}
	 */
	public String getName() {
		return tableSchema.getName();
	}

	/**
	 * Returns an {@link ArrayList} containing {@link Record} objects which
	 * represent table rows.
	 * 
	 * @return {@link ArrayList}
	 */
	public ArrayList<Record> getRecords() {
		return records;
	}

	/**
	 * Sets an {@link ArrayList} containing {@link Record} objects which
	 * represent table rows.
	 * 
	 * @param records
	 */
	public void setRecords(ArrayList<Record> records) {
		this.records = records;
	}

	/**
	 * Returns {@link TableSchema} containing info on columns of this table
	 * model.
	 * 
	 * @return {@link TableSchema}
	 */
	public TableSchema getTableSchema() {
		return tableSchema;
	}

	/**
	 * Sets {@link TableSchema} containing info on columns of this table model.
	 * 
	 * @param tableSchema
	 */
	public void setTableSchema(TableSchema tableSchema) {
		this.tableSchema = tableSchema;
	}

	/**
	 * Returns an {@link ArrayList} of {@link TableModel} objects representing
	 * child tables of this table.
	 * 
	 * @return {@link ArrayList}
	 */
	public ArrayList<TableModel> getChildTableModels() {
		return childTableModels;
	}

	/**
	 * Sets an {@link ArrayList} of {@link TableModel} objects representing
	 * child tables of this table.
	 * 
	 * @param childTableModels
	 */
	public void setChildTableModels(ArrayList<TableModel> childTableModels) {
		this.childTableModels = childTableModels;
	}

	/**
	 * Returns the {@link Table} GUI element used for showing table data.
	 * 
	 * @return {@link Table}
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Sets the {@link Table} GUI element used for showing table data. The
	 * {@code Table} must have this model set as the table model.
	 * 
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * Returns the parent {@link TableModel} of this model.
	 * 
	 * @return {@link TableModel}
	 */
	public TableModel getParentTableModel() {
		return parentTableModel;
	}

	/**
	 * Sets the parent {@link TableModel} of this model.
	 * 
	 * @param parentTableModel
	 */
	public void setParentTableModel(TableModel parentTableModel) {
		this.parentTableModel = parentTableModel;
	}

	/**
	 * Returns the static {@link ArrayList} field containing all
	 * {@link TableModel} objects of the tables currently open.
	 * 
	 * @return {@link ArrayList}
	 */
	public static ArrayList<TableModel> getOpenedTableModels() {
		return openedTableModels;
	}

	/**
	 * Sets the static {@link ArrayList} field containing all {@link TableModel}
	 * objects of the tables currently open.
	 * 
	 * @param openedTableModels
	 */
	public static void setOpenedTableModels(ArrayList<TableModel> openedTableModels) {
		TableModel.openedTableModels = openedTableModels;
	}

	/**
	 * Getter for condition values applied to this table model in the
	 * {@link QueryForm}. Returns a {@link HashMap} with {@link TableColumn} for
	 * the key and an {@link Object} for the value.
	 * 
	 * @return {@link HashMap}
	 */
	public HashMap<TableColumn, Object> getConditionValues() {
		return conditionValues;
	}

	/**
	 * Setter for condition values applied to this table model in the
	 * {@link QueryForm}. Parameter conditionValues is a {@link HashMap} with
	 * {@link TableColumn} for the key and an {@link Object} for the value.
	 * 
	 * @param conditionValues
	 */
	public void setConditionValues(HashMap<TableColumn, Object> conditionValues) {
		this.conditionValues = conditionValues;
	}

	/**
	 * Getter for conditions applied to this table model in the
	 * {@link QueryForm}. Returns a {@link HashMap} with {@link TableColumn} for
	 * the key and an {@link Object} for the value.
	 * 
	 * @return {@link HashMap}
	 */
	public HashMap<TableColumn, Condition> getConditions() {
		return conditions;
	}

	/**
	 * Setter for conditions applied to this table model in the
	 * {@link QueryForm}. Parameter conditions is a {@link HashMap} with
	 * {@link TableColumn} for the key and an {@link Object} for the value.
	 * 
	 * @param conditions
	 */
	public void setConditions(HashMap<TableColumn, Condition> conditions) {
		this.conditions = conditions;
	}

	/**
	 * Given a {@link TableColumn}, if this {@link TableModel} has a parent
	 * table and the parent table has the given column, returns the value of
	 * said column. If not, returns {@code null}.
	 * 
	 * @param column
	 * @return {@link Object}
	 */
	public Object findParentRefColValue(TableColumn column) {
		if (!column.getReferencedTableColumns().isEmpty() && parentTableModel != null) {
			for (TableColumn col : column.getReferencedTableColumns()) {
				if (parentTableModel.getTableSchema().getColumns().contains(col)) {
					if (!checkOtherReferencesToSameColumn(column, col)) {
						ArrayList<Record> records = parentTableModel.getRecords();
						int row = parentTableModel.getTable()
								.convertRowIndexToModel(parentTableModel.getTable().getSelectedRow());

						return records.get(row).getValues().get(col);
					} else {
						return null;
					}
				}
			}
			return null;
		}
		return null;
	}

	/**
	 * Returns {@code true} if there are more columns from the same table
	 * (besides {@code column}) referencing the given {@code referencedColumn}.
	 * Both parameters are {@link TableColumn} objects.
	 * 
	 * @param column
	 * @param referencedColumn
	 * @return boolean
	 */
	public boolean checkOtherReferencesToSameColumn(TableColumn column, TableColumn referencedColumn) {
		for (TableColumn otherColumn : column.getTable().getColumns()) {
			if (otherColumn != column) {
				if (otherColumn.getReferencedTableColumns().contains(referencedColumn)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns {@link TableModel}'s reference to Database {@link Connection}.
	 * 
	 * @return {@link Connection}
	 * @see Connection
	 * @see DatabaseConnection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets {@link TableModel}'s reference to Database {@link Connection}.
	 * 
	 * @param connection
	 * @see Connection
	 * @see DatabaseConnection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
