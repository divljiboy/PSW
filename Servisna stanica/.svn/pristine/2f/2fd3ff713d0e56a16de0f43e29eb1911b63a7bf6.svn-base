package model;

import gui.tables.forms.QueryComboBox;

/**
 * Class representing conditions in {@link QueryComboBox}.
 * Valid values are stored as static fields:
 * text (STARTS_WITH, ENDS_WITH, CONTAINS),
 * numeric (EQUALS, NOT_EQUAL_TO, GREATER, GREATER_OR_EQUAL, LESSER, LESSER_OR_EQUAL),
 * date and time (AFTER, BEFORE)
 * and boolean (TRUE, FALSE, TRUE_AND_FALSE) conditions.
 * 
 * @author Milan Radeta
 */
public class Condition {

	private int value;
	private Object label;

	public static final int EQUALS = 1;
	public static final int NOT_EQUAL_TO = 2;
	public static final int GREATER = 3;
	public static final int GREATER_OR_EQUAL = 4;
	public static final int LESSER = 5;
	public static final int LESSER_OR_EQUAL = 6;

	public static final int STARTS_WITH = 7;
	public static final int ENDS_WITH = 8;
	public static final int CONTAINS = 9;

	public static final int TRUE = 10;
	public static final int FALSE = 11;
	public static final int TRUE_AND_FALSE = 12;
	
	public static final int AFTER = 10;
	public static final int BEFORE = 11;
	
	/**
	 * Constructor of {@link Condition} class.
	 * Parameter value is an integer. Valid values are present as static fields in the class.
	 * Parameter label is an {@link Object} and is shown to the user as the semantic representation of the value.
	 * 
	 * @param value
	 * @param label
	 */
	public Condition(int value, Object label) {
		super();
		this.value = value;
		this.label = label;
	}

	/**
	 * Getter method for the value of {@link Condition}.
	 * @return int
	 */
	public int getValue() { return value; }
	/**
	 * Setter method for the value of {@link Condition}.
	 * Parameter value is an integer.
	 * @param value
	 */
	public void setValue(int value) { this.value = value; }

	/**
	 * Getter method for the label of {@link Condition}.
	 * @return {@link Object}
	 */
	public Object getLabel() { return label; }
	/**
	 * Setter method for the label of {@link Condition}.
	 * Parameter label is an {@link Object}.
	 * @param label
	 */
	public void setLabel(Object label) { this.label = label; }

	/**
	 * Returns the string representation of the label {@link Object}.
	 */
	@Override
	public String toString() {
		return label.toString();
	}
}
