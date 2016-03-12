package actions.records;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import application.Application;
import gui.MainFrame;
import gui.tables.Table;
import gui.tables.forms.DatePicker;
import gui.tables.forms.TimePicker;
import gui.tables.forms.DateTimePicker;
import gui.tables.forms.FormattedField;
import gui.tables.forms.QueryForm;
import model.ComboBoxItem;
import model.Condition;
import model.TableColumn;
import model.TableModel;
import model.TableSchema;

/**
 * 
 * Singleton class that extends {@link AbstractAction}. When action is performed
 * it closes {@link QueryForm} and creates WHERE conditions with values from
 * {@link QueryForm} for SQL SELECT {@link PreparedStatement} used to fill the
 * {@link Table}'s {@link TableModel}.
 * 
 * @see AbstractAction
 * @see QueryForm
 * @see PreparedStatement
 * @author Milan Radeta
 * @author Borko Arsović
 *
 */
public class ActionConfirmSearch extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionConfirmSearch} object.
	 */
	private static ActionConfirmSearch instance = null;
	/**
	 * {@link QueryForm} to which {@link ActionConfirmSearch} is linked to.
	 */
	private QueryForm queryForm;

	/**
	 * Returns the only instance of {@link ActionConfirmSearch} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionConfirmSearch}
	 */
	public static ActionConfirmSearch getInstance() {
		if (instance == null) {
			instance = new ActionConfirmSearch();
		}

		return instance;
	}

	/**
	 * {@link ActionConfirmSearch} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon. Action is initially enabled.
	 * 
	 */
	private ActionConfirmSearch() {
		putValue(NAME, Application.getResourceBundle().getString("ButtonConfirm"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/confirm.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("ButtonConfirmDesc"));
		setEnabled(true);
	}

	/**
	 * Sets focused {@link Table}'s {@link TableModel}'s where conditions
	 * with values from {@link QueryForm}. These conditions are used when
	 * getting data from database to fill the table.
	 * 
	 * @see Table
	 * @see TableModel
	 * @see QueryForm
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		HashMap<TableColumn, Object> conditionValues = new HashMap<>();
		HashMap<TableColumn, Condition> conditions = new HashMap<>();
		TableModel tableModel = queryForm.getTableModel();
		TableSchema tableSchema = tableModel.getTableSchema();
		ArrayList<TableColumn> columns = tableSchema.getColumns();

		HashMap<TableColumn, JComponent> inputs = queryForm.getInputs();

		for (TableColumn col : columns) {
			if (queryForm.getInputs().containsKey(col) && queryForm.getConditions().containsKey(col)) {
				JComponent input = inputs.get(col);
				String value = null;
				if (input instanceof DateTimePicker) {
					value = ((DateTimePicker) input).getValue();
				} else if (input instanceof DatePicker) {
					value = ((DatePicker) input).getValue();
				} else if (input instanceof TimePicker) {
					value = ((TimePicker) input).getValueAsString();
				} else if (input instanceof FormattedField) {
					value = ((FormattedField) input).getText();
				} else if (input instanceof JTextArea) {
					value = ((JTextArea) input).getText();
				} else if (input instanceof JTextField) {
					value = ((JTextField) input).getText();
				} else if (input instanceof JComboBox<?>) {
					@SuppressWarnings("unchecked")
					JComboBox<ComboBoxItem> comboBox = (JComboBox<ComboBoxItem>) input;
					ComboBoxItem item = (ComboBoxItem) comboBox.getSelectedItem();
					value = item != null ? item.getValue().toString() : null;
				}
				if (value != null && !value.equals("")) {
					conditionValues.put(col, value);
					conditions.put(col, (Condition) queryForm.getConditions().get(col).getSelectedItem());
				}
			} else if (col.getType() == Types.BIT || col.getType() == Types.BOOLEAN) {
				conditions.put(col, (Condition) queryForm.getConditions().get(col).getSelectedItem());
			}

		}
		tableModel.setConditionValues(conditionValues);
		tableModel.setConditions(conditions);
		queryForm.dispose();
		MainFrame.getInstance().getStatusBar().setMessage("");
		tableModel.refreshData(TableModel.NONE);
	}

	/**
	 * Returns {@link QueryForm} {@link ActionConfirmSearch} is linked to.
	 * 
	 * @return {@link QueryForm}
	 * 
	 */
	public QueryForm getQueryForm() {
		return queryForm;
	}

	/**
	 * Sets {@link QueryForm} {@link ActionConfirmSearch} is linked to.
	 * 
	 */
	public void setQueryForm(QueryForm queryForm) {
		this.queryForm = queryForm;
	}

}
