package gui.tables.forms;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import application.Application;
import model.TimePickerModel;

/**
 * Extension of {@link JSpinner}
 * 
 * @author Isidora Škulec
 * @author Milan Radeta
 * @author Ivan Divljak
 * 
 * @see DatePicker
 * @see DateTimePicker
 * @see JSpinner
 * 
 */
public class TimePicker extends JSpinner {
	private static final long serialVersionUID = 1;

	/**
	 * Format in which {@link TimePicker}'s value is represented.
	 */
	private DateFormat timeFormat;

	/**
	 * Constructs new {@link TimePicker} object, with a new
	 * {@link TimePickerModel}. It uses {@link Application}'s currently used
	 * {@link Locale} and {@link DateFormat#DEFAULT}. Initializes value to null.
	 */
	public TimePicker(Object value) {
		super(new TimePickerModel());
		((JSpinner.DefaultEditor) getEditor()).getTextField().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("value") && evt.getNewValue() == null) {
					if (getValue() == null) {
						setValue(Calendar.getInstance(Application.getResourceBundle().getLocale()).getTime());
					} else {
						setValue(null);
					}
				}
			}
		});
		setLocale(Application.getLocale());
		setTimeFormat(DateFormat.getTimeInstance(DateFormat.DEFAULT, Application.getLocale()));
		setValue(value);
	}

	/**
	 * Returns {@link TimePicker}'s {@code timeFormat}.
	 * 
	 * @see DateFormat
	 * @return {@link DateFormat}
	 */
	public DateFormat getTimeFormat() {
		return timeFormat;
	}

	/**
	 * Sets {@link TimePicker}'s {@code timeFormat}, after which it updates text
	 * field format.
	 * 
	 * @see DateFormat
	 * @see #updateTextFieldFormat()
	 * @param timeFormat
	 */
	public void setTimeFormat(DateFormat timeFormat) {
		this.timeFormat = timeFormat;
		updateTextFieldFormat();
	}

	/**
	 * Sets format of the {@link TimePicker}'s editor text field.
	 * 
	 * @see JFormattedTextField
	 * @see DefaultFormatterFactory
	 * @see DateFormatter
	 */
	private void updateTextFieldFormat() {
		JFormattedTextField tf = ((JSpinner.DefaultEditor) getEditor()).getTextField();
		DefaultFormatterFactory factory = (DefaultFormatterFactory) tf.getFormatterFactory();
		DateFormatter formatter = (DateFormatter) factory.getDefaultFormatter();
		// Change the date format to only show the hours
		formatter.setFormat(timeFormat);
	}

	/**
	 * Returns {@link TimePicker}'s value as {@link Date}.
	 * 
	 * @see Date
	 * @see #getValue()
	 * @return {@link Date}
	 */
	public Date getTime() {
		return (Date) getValue();
	}

	/**
	 * Returns {@link TimePicker}'s value as {@link String} formatted with
	 * {@link SimpleDateFormat} with pattern "HH:mm:ss", which is compatible
	 * with SQL Server. If the value is equal to null or an empty string it
	 * retuns an empty string.
	 * 
	 * @see String
	 * @see #getValue()
	 * @see #getTime()
	 * @see SimpleDateFormat
	 * @return {@link String}
	 */
	public String getValueAsString() {
		try {
			if (getValue().equals("")) {
				return "";
			}
			return (new SimpleDateFormat("HH:mm:ss")).format(getTime());
		} catch (NullPointerException e) {
			return "";
		}
	}
}
