package listeners.form;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import gui.tables.forms.ComboBox;
import gui.tables.forms.RecordForm;
import model.ComboBoxItem;
import model.Record;
import model.TableColumn;

/**
 * Implementation of {@link ItemListener} linked to {@link ComboBox}.
 * 
 * @see ComboBox
 * @see ItemListener
 * @author Milan Radeta
 *
 */
public class ComboBoxListener implements ItemListener {

	private ComboBox comboBox;
	private static boolean updating = false;

	/**
	 * Constructor that sets reference to {@link ComboBox} and resets the static
	 * boolean member {@link #updating} to <code>false</code>.
	 * 
	 * @see ComboBox
	 * @param comboBox
	 */
	public ComboBoxListener(ComboBox comboBox) {
		updating = true;
		this.setComboBox(comboBox);
	}

	/**
	 * Implemented method that updates other {@link ComboBox}es of the
	 * {@link RecordForm}, if needed.
	 * 
	 * @see ComboBox
	 * @see RecordForm
	 * @see ItemEvent
	 * @param arg0
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (!updating) {
			updating = true;
			RecordForm recordForm = comboBox.getRecordForm();
			Record record = recordForm.getRecord();
			record.getValues().put(comboBox.getColumn(),
					comboBox.getSelectedItem() != null ? ((ComboBoxItem) comboBox.getSelectedItem()).getValue() : null);
			ArrayList<TableColumn> columns = comboBox.getColumn().getTable().getColumns();
			for (int i = columns.indexOf(comboBox.getColumn()) + 1; i < columns.size(); i++) {
				if (recordForm.getComboBoxes().containsKey(columns.get(i))) {
					recordForm.getComboBoxes().get(columns.get(i)).updateItems();
				}
			}
			updating = false;
		}
	}

	/**
	 * Returns the reference to {@link ComboBox}.
	 * 
	 * @see ComboBox
	 * @return {@link ComboBox}
	 */
	public ComboBox getComboBox() {
		return comboBox;
	}

	/**
	 * Sets the reference to {@link ComboBox}.
	 * 
	 * @see ComboBox
	 * @param comboBox
	 */
	public void setComboBox(ComboBox comboBox) {
		this.comboBox = comboBox;
	}

	/**
	 * Returns whether the other {@link ComboBox}es of the {@link RecordForm}
	 * are being updated currently or not.
	 * 
	 * @see ComboBox
	 * @see RecordForm
	 * @return boolean
	 */
	public static boolean isUpdating() {
		return updating;
	}

	/**
	 * Sets static boolean member {@link #updating}.
	 * 
	 * @param updating
	 */
	public static void setUpdating(boolean updating) {
		ComboBoxListener.updating = updating;
	}

}
