package gui.tables.forms;

import java.awt.Color;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;

import application.Application;

/**
 * GUI component, which is extension of {@link DatePicker}, which allows to
 * choose a calendar date. It also contains {@link TimePicker}.
 * 
 * @author Isidora Škulec
 * @author Milan Radeta
 * @author Ivan Divljak
 * 
 * @see TimePicker
 * @see DatePicker
 * @see JXDatePicker
 * @see JXMonthView
 * 
 */
public class DateTimePicker extends DatePicker {
	private static final long serialVersionUID = 1L;
	/**
	 * {@link TimePicker} component of {@link DateTimePicker}. It's contained in
	 * the link panel.
	 */
	private TimePicker timePicker;

	/**
	 * Link panel, which contains {@link TimePicker}.
	 */
	private JPanel timePanel;

	/**
	 * Constructs new {@link DateTimePicker} component with default formats for
	 * date and time.
	 * 
	 * @see DateFormat
	 */
	public DateTimePicker() {
		super();
		setFormats(DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Application.getLocale()));
	}

	/**
	 * Overridden {@link JXDatePicker#commitEdit()} method. It commits time
	 * alongside date.
	 * 
	 * @throws ParseException
	 * @see JXDatePicker
	 * @see #commitTime()
	 */
	public void commitEdit() throws ParseException {
		commitTime();
		super.commitEdit();

	}

	/**
	 * Overridden {@link JXDatePicker#cancelEdit()} method. It additionaly sets
	 * {@link TimePicker}.
	 * 
	 * @see JXDatePicker
	 * @see #setTimeSpinners()
	 */
	public void cancelEdit() {
		super.cancelEdit();
		setTimeSpinners();
	}

	/**
	 * Overridden {@link JXDatePicker#getLinkPanel()}. It creates
	 * {@code timePanel} if it doesn't exist.
	 * 
	 * @see JPanel
	 * @see #createTimePanel()
	 * @see #setTimeSpinners()
	 * @return {@link JPanel}
	 */
	@Override
	public JPanel getLinkPanel() {
		if (timePanel == null) {
			timePanel = createTimePanel();
		}
		setTimeSpinners();
		return timePanel;
	}

	/**
	 * Creates {@code timePanel} with white background to be used as a link
	 * panel. The panel has {@link FlowLayout} and contains {@link JLabel} and
	 * {@link TimePicker}.
	 * 
	 * @see JPanel
	 * @see TimePicker
	 * @see FlowLayout
	 * @return {@link JPanel}
	 */
	private JPanel createTimePanel() {
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new FlowLayout());

		timePicker = new TimePicker(Calendar.getInstance(Application.getResourceBundle().getLocale()).getTime());
		newPanel.add(new JLabel(Application.getResourceBundle().getString("TimePickerLabel")));
		newPanel.add(timePicker);
		newPanel.setBackground(Color.WHITE);
		return newPanel;
	}

	/**
	 * Adds time value from {@link TimePicker} and adds it to {@link DatePicker}
	 * 's date value.
	 * 
	 * @see DatePicker
	 * @see #getDate()
	 * @see TimePicker
	 * @see TimePicker#getTime()
	 * @see GregorianCalendar
	 */
	private void commitTime() {
		Date date = getDate();
		if (date != null) {
			Date time = timePicker.getTime();
			GregorianCalendar timeCalendar = new GregorianCalendar(Application.getLocale());
			timeCalendar.setTime(time);

			GregorianCalendar calendar = new GregorianCalendar(Application.getLocale());
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
			calendar.set(Calendar.MILLISECOND, 0);

			setDate(calendar.getTime());
		}
	}

	/**
	 * Gets {@link DatePicker}'s date and sets {@link TimePicker}'s value to
	 * that date.
	 * 
	 * @see DatePicker
	 * @see #getDate()
	 * @see TimePicker
	 * @see TimePicker#setValue(Object)
	 */
	private void setTimeSpinners() {
		Date date = getDate();
		if (date != null) {
			timePicker.setValue(date);
		}
	}

	/**
	 * Get's {@link DateTimePicker}'s value as a string. If either
	 * {@link DatePicker}'s date value or {@link TimePicker}'s time value are
	 * equal to null or an empty string, it returns an empty string. Otherwise,
	 * it returns concatenated results of {@link DatePicker#getValue()} and
	 * {@link TimePicker#getValueAsString()}
	 * 
	 * @return {@link String}
	 */
	public String getValue() {
		if (super.getValue() != null && timePicker.getValueAsString() != null && !super.getValue().equals("")
				&& !timePicker.getValueAsString().equals(""))
			return super.getValue() + " " + timePicker.getValueAsString();
		else
			return "";
	}
}
