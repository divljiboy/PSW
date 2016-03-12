package model;

import java.util.Date;

import javax.swing.SpinnerDateModel;

import gui.tables.forms.TimePicker;

/**
 * Extension of {@link SpinnerDateModel} used by {@link TimePicker}.
 * 
 * @see TimePicker
 * @see SpinnerDateModel
 * @author Milan Radeta
 *
 */
public class TimePickerModel extends SpinnerDateModel {
	private static final long serialVersionUID = 1L;

	private Object value = null;

	/**
	 * 
	 * Overridden {@link SpinnerDateModel#setValue(Object)} that supports null
	 * values.
	 * 
	 * @see SpinnerDateModel
	 * @see SpinnerDateModel#setValue(Object)
	 * @param value
	 * @throws IllegalArgumentException
	 */
	public void setValue(Object value) {
		Object oldValue = this.value;
		try {
			this.value = value;
			super.setValue(value);
		} catch (IllegalArgumentException e) {
			if (value != null) {
				throw e;
			} else {
				if (oldValue != null) {
					this.value = null;
					fireStateChanged();
				}
			}
		}
	}

	/**
	 * 
	 * Overridden {@link SpinnerDateModel#getValue()} that supports null values.
	 * 
	 * @see SpinnerDateModel
	 * @see SpinnerDateModel#getValue()
	 * @return {@link Object}
	 */
	@Override
	public Object getValue() {
		return getDate();
	}

	@Override
	public Date getDate() {
		try {
			if (this.value == null) {
				return null;
			}
			return (Date) this.value;
		} catch (NullPointerException e) {
			return null;
		}
	}
}
