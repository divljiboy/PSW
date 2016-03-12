package model;

import gui.tables.forms.ComboBox;

/**
 * A class for {@link ComboBox} items which contain a value and a semantic representation (called label).
 * The label will be shown in the {@code ComboBox} and will be returned from the {@code toString} method.
 * The value will be stored in the database.
 * 
 * @author Isidora Škulec
 *
 */
public class ComboBoxItem {
	private Object value;
	private Object label;
	
	/**
	 * Constructor of the {@link ComboBoxItem} class.
	 * Parameter value represents the {@link Object} which holds the value of {@link ComboBox}.
	 * Parameter label represents the {@code Object} which will be shown to the user.
	 * 
	 * @param value
	 * @param label
	 */
	public ComboBoxItem(Object value, Object label) {
		super();
		this.value = value;
		this.label = label;
	}

	/**
	 * Getter method for {@link ComboBoxItem} value.
	 * @return {@link Object}
	 */
	public Object getValue() { return value; }
	/**
	 * Setter method for {@link ComboBoxItem} value.
	 * Parameter value is an {@link Object}.
	 * @param value
	 */
	public void setValue(Object value) { this.value = value; }

	/**
	 * Getter method for {@link ComboBoxItem} label.
	 * @return {@link Object}
	 */
	public Object getLabel() { return label; }
	/**
	 * Setter method for {@link ComboBoxItem} label.
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
