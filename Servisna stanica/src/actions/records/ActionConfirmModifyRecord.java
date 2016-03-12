package actions.records;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import application.Application;
import database.DatabaseConnection;
import gui.MainFrame;
import gui.tables.Table;
import gui.tables.forms.DatePicker;
import gui.tables.forms.TimePicker;
import gui.tables.forms.DateTimePicker;
import gui.tables.forms.FormattedField;
import gui.tables.forms.RecordForm;
import model.ComboBoxItem;
import model.Record;
import model.TableColumn;
import model.TableModel;

/**
 * 
 * Singleton class that extends {@link AbstractAction}. When action is performed
 * it closes {@link RecordForm} and creates an SQL UPDATE
 * {@link PreparedStatement} with values from {@link RecordForm}. If the
 * statement is processed correctly, it updates focused {@link Table}'s
 * {@link TableModel}.
 * 
 * @see AbstractAction
 * @see RecordForm
 * @see PreparedStatement
 * @author Milan Radeta
 * @author Borko Arsović
 *
 */
public class ActionConfirmModifyRecord extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionConfirmAddRecord} object.
	 */
	private static ActionConfirmModifyRecord instance = null;
	/**
	 * {@link RecordForm} to which {@link ActionConfirmAddRecord} is linked to.
	 */
	private RecordForm recordForm = null;

	/**
	 * Returns the only instance of {@link ActionConfirmModifyRecord} object. If
	 * it does not exist, it will be created.
	 * 
	 * @return {@link ActionConfirmModifyRecord}
	 */
	public static ActionConfirmModifyRecord getInstance() {
		if (instance == null) {
			instance = new ActionConfirmModifyRecord();
		}
		return instance;
	}

	/**
	 * {@link ActionConfirmModifyRecord} private constructor. Initializes the
	 * object with name and short description from localization properties file,
	 * as well with small icon. Action is initially enabled.
	 * 
	 */
	private ActionConfirmModifyRecord() {
		putValue(NAME, Application.getResourceBundle().getString("ButtonConfirm"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/confirm.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("ButtonConfirmDesc"));
		setEnabled(true);
	}

	/**
	 * Validates linked {@link RecordForm}'s fields and, if it passes
	 * validation, creates an SQL UPDATE {@link PreparedStatement} with values
	 * from {@link RecordForm}'s input fields. If validation is not passed,
	 * database is not changed even though input fields are or an SQL Exception
	 * is thrown it shows error message.
	 * 
	 * @see RecordForm
	 * @see PreparedStatement
	 * @throws SQLException
	 */
	public void actionPerformed(ActionEvent e) {
		if (recordForm.validateFields()) {
			TableModel tableModel = recordForm.getTableModel();
			HashMap<TableColumn, JComponent> inputs = recordForm.getInputs();
			StringBuilder query = new StringBuilder("UPDATE " + tableModel.getTableSchema().getCode() + " SET ");
			for (TableColumn column : tableModel.getTableSchema().getColumns()) {
				query.append(column.getCode() + " = ?, ");
			}
			query.replace(query.length() - 2, query.length(), "");
			query.append(" WHERE ");
			for (TableColumn column : tableModel.getTableSchema().getColumns()) {
				if (column.isPrimaryKey()) {
					query.append(column.getCode() + " = ? and ");
				}
			}
			query.replace(query.length() - 5, query.length(), "");

			try {
				PreparedStatement statement = DatabaseConnection.getInstance().getConn()
						.prepareStatement(query.toString());
				int paramIndex = 0;
				for (TableColumn column : tableModel.getTableSchema().getColumns()) {
					JComponent input = inputs.get(column);
					paramIndex++;
					if (input instanceof DateTimePicker) {
						String value = ((DateTimePicker) input).getValue();
						statement.setString(paramIndex, value.equals("") ? null : value);
					} else if (input instanceof DatePicker) {
						String value = ((DatePicker) input).getValue();
						statement.setString(paramIndex, value.equals("") ? null : value);
					} else if (input instanceof TimePicker) {
						String value = ((TimePicker) input).getValueAsString();
						statement.setString(paramIndex, value.equals("") ? null : value);
					} else if (input instanceof FormattedField) {
						String value = ((FormattedField) input).getText();
						statement.setString(paramIndex, value.equals("") ? null : value);
					} else if (input instanceof JTextArea) {
						String value = ((JTextArea) input).getText();
						statement.setString(paramIndex, value.equals("") ? null : value);
					} else if (input instanceof JTextField) {
						String value = ((JTextField) input).getText();
						statement.setString(paramIndex, value.equals("") ? null : value);
					} else if (input instanceof JCheckBox) {
						statement.setBoolean(paramIndex, ((JCheckBox) input).isSelected());
					} else if (input instanceof JComboBox<?>) {
						@SuppressWarnings("unchecked")
						JComboBox<ComboBoxItem> comboBox = (JComboBox<ComboBoxItem>) input;
						ComboBoxItem item = (ComboBoxItem) comboBox.getSelectedItem();
						statement.setString(paramIndex, item != null ? item.getValue().toString() : null);
					}
				}
				Table table = Table.getFocusedTable();
				Record record = tableModel.getRecords().get(table.convertRowIndexToModel(table.getSelectedRow()));
				for (TableColumn column : tableModel.getTableSchema().getColumns()) {
					if (column.isPrimaryKey()) {
						paramIndex++;
						statement.setObject(paramIndex, record.getValues().get(column));
					}
				}
				if (statement.executeUpdate() == 1) {
					// success
					Table.getFocusedTable().getTableModel().refreshData(TableModel.MODIFY);
					recordForm.dispose();
					MainFrame.getInstance().getStatusBar().setMessage("");
				} else {
					Application.showErrorMessage("ExecuteUpdateZero");
				}
			} catch (SQLException e1) {
				Application.showSqlExceptionError(e1);
			}
		} else {
			Application.showErrorMessage("FormValidation");
		}

	}

	/**
	 * Returns {@link RecordForm} {@link ActionConfirmModifyRecord} is linked
	 * to.
	 * 
	 * @return {@link RecordForm}
	 * 
	 */
	public RecordForm getRecordForm() {
		return recordForm;
	}

	/**
	 * Sets {@link RecordForm} {@link ActionConfirmModifyRecord} is linked to.
	 * 
	 */
	public void setRecordForm(RecordForm addForm) {
		this.recordForm = addForm;
	}
}
