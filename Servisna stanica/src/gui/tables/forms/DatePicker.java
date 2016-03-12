package gui.tables.forms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.calendar.SingleDaySelectionModel;

import application.Application;

/**
 * GUI component, which is extension of {@link JXDatePicker}, which allows to
 * choose a calendar date.
 * 
 * @author Isidora Škulec
 * @author Milan Radeta
 * @author Ivan Divljak
 * 
 * @see TimePicker
 * @see DateTimePicker
 * @see JXDatePicker
 * @see JXMonthView
 * 
 */
public class DatePicker extends JXDatePicker {
	private static final long serialVersionUID = 1L;

	/**
	 * Empty link panel. Used to override {@link JXDatePicker}'s default link
	 * panel.
	 * 
	 * @see JPanel
	 */
	private JPanel emptyPanel;

	/**
	 * {@link DatePicker}'s constructor. It constructs localized date picker and
	 * uses {@link DateFormat#DEFAULT}.
	 * 
	 * @see JXDatePicker
	 * @see DateFormat
	 * @see JXMonthView
	 */
	public DatePicker() {
		super(Application.getLocale());
		getMonthView().setSelectionModel(new SingleDaySelectionModel());
		getMonthView().setLocale(Application.getLocale());
		setFormats(DateFormat.getDateInstance(DateFormat.DEFAULT, Application.getLocale()));
		setDate(null);
	}

	/**
	 * Overrides {@link JXDatePicker#getLinkPanel()} so that it doesn't show
	 * anything on the bottom of {@link JXDatePicker}'s {@link JXMonthView}
	 * component.
	 * 
	 * @see JPanel
	 * @see JXDatePicker
	 * @see JXMonthView
	 */
	@Override
	public JPanel getLinkPanel() {
		if (emptyPanel == null) {
			emptyPanel = new JPanel();
		}
		return emptyPanel;
	}

	/**
	 * Returns date formated with {@link SimpleDateFormat} as a {@link String}
	 * in the yyyy-MM-dd pattern, which is compatible with SQL Server's date
	 * format. If value is null, it returns empty string.
	 * 
	 * @return {@link String}
	 */
	public String getValue() {
		try {
			return (new SimpleDateFormat("yyyy-MM-dd")).format(getDate());
		} catch (NullPointerException e) {
			return "";
		}
	}

}
