package model;

import java.util.HashMap;

/**
 * Class representing a single row of a table.
 * Columns are stored inside a {@link HashMap} containing {@link TableColumn} as the key and an {@link Object} as the value.
 * 
 * @author Milan Radeta
 *
 */
public class Record {
	private HashMap<TableColumn, Object> values = new HashMap<>();
	private HashMap<TableColumn, Object> semanticValues = new HashMap<>();

	/**
	 * Empty constructor
	 */
	public Record() {}
	
	/**
	 * Copy constructor.
	 * @param record
	 */
	@SuppressWarnings("unchecked")
	public Record(Record record){
		values = (HashMap<TableColumn, Object>) record.values.clone();
		semanticValues = (HashMap<TableColumn, Object>) record.semanticValues.clone();
	}
	
	/**
	 * Getter method for values of columns.
	 * @return {@link HashMap}
	 */
	public HashMap<TableColumn, Object> getValues() {
		return values;
	}
	/**
	 * Setter method for values of columns.
	 * Parameter values is a {@link HashMap} containing {@link TableColumn} as the key and an {@link Object} as the value.
	 * @param values
	 */
	public void setValues(HashMap<TableColumn, Object> values) {
		this.values = values;
	}

	/**
	 * Getter method for semantic values of columns.
	 * @return {@link HashMap}
	 */
	public HashMap<TableColumn, Object> getSemanticValues() {
		return semanticValues;
	}

	/**
	 * Setter method for semantic values of columns.
	 * Parameter semanticValues is a {@link HashMap} containing {@link TableColumn} as the key and an {@link Object} as the value.
	 * @param semanticValues
	 */
	public void setSemanticValues(HashMap<TableColumn, Object> semanticValues) {
		this.semanticValues = semanticValues;
	}

	/**
	 * Method for comparing value of the column with another.
	 * Parameter record is a {@link Record} object.
	 * @param record
	 * @return boolean
	 */
	public boolean compareValues(Record record) {
		for(TableColumn col : values.keySet()) {
			if (col.isPrimaryKey()) {
				if (!values.get(col).equals(record.getValues().get(col))) {
					return false;
				}
			}
		}
		return true;
	}

}
