package gui.tables.forms;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import listeners.form.FormFieldListener;

/**
 * Extension of the {@link JFormattedTextField} class.
 * It is used for numerical fields in {@link RecordForm}.
 * A {@link NumberFormat} determines determines valid value and format of the {@code FormattedField}.
 * If an invalid value is entered, it will be erased or converted to match the {@code NumberFormat}.
 * 
 * @author Isidora Škulec
 * @see FormFieldListener
 */
public class FormattedField extends JFormattedTextField {

	private static final long serialVersionUID = 867132918875242855L;
	
	/**
	 * Constructor for {@link FormattedField} class.
	 * Parameter {@code columns} specifies the number of columns (characters) which determine the width of the component.
	 * Parameter {@code numFormat} takes a {@link NumberFormat} instance and determines valid value and format of the {@code FormattedField}.
	 * 
	 * @param columns
	 * @param numFormat
	 */
	public FormattedField(int columns, NumberFormat numFormat) {
		super(numFormat);
		
		//setColumns(columns);
		addPropertyChangeListener("value", new FormFieldListener());
	}
	
	
}
