package gui.tables.forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Types;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import actions.cancel.ActionCancel;
import actions.records.ActionConfirmSearch;
import application.Application;
import gui.MainFrame;
import model.Condition;
import model.TableColumn;
import model.TableModel;
import model.TableSchema;

/**
 * Used for advanced search.
 * This class contains a form created dynamically from {@link TableSchema}.
 * It extends the {@link JDialog} class.
 * All labels and inputs are inside a {@link JPanel} with {@link GridBagLayout}.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 */
public class QueryForm extends JDialog {

	private static final long serialVersionUID = 1L;

	private TableModel tableModel;
	private HashMap<TableColumn, JComponent> inputs = new HashMap<>();
	private HashMap<TableColumn, QueryComboBox> conditions = new HashMap<>();

	/**
	 * Constructor of the {@link QueryForm} class.
	 * Parameter {@code tableModel} take the {@link TableModel} object on which the advanced search query will be preformed. 
	 * 
	 * @param tableModel
	 */
	public QueryForm(TableModel tableModel) {
		super(MainFrame.getInstance(), true);
		this.setTableModel(tableModel);
		ActionConfirmSearch.getInstance().setQueryForm(this);
		ActionCancel.getInstance().setDialog(this);
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("SearchFinish"));
		setTitle(Application.getResourceBundle().getString("QueryForm"));

		// Set layout for panel.
		JPanel internalPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		gbc.insets = new Insets(5, 5, 5, 5);

		// Create input fields.
		int rows = 0;
		// Iterate columns.
		for (TableColumn column : tableModel.getTableSchema().getColumns()) {
			int columnType = column.getType();
			int columnSize = column.getSize();

			// Label the row.
			gbc.gridx = 0;
			gbc.gridy = rows;
			String lblText = column.getName() + ":";
			JLabel lbl = new JLabel(lblText);
			internalPanel.add(lbl, gbc);
			Object value = tableModel.findParentRefColValue(column);

			// Is it a string? Is it a plane?
			switch (columnType) {
			// Textual fields:
			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.LONGVARCHAR:
			case Types.LONGNVARCHAR:
				gbc.gridx = 1;
				QueryComboBox queryComboBox = new QueryComboBox(this);
				queryComboBox.addTextConditions();
				if (value != null) {
					queryComboBox.setEnabled(false);
					for (int i = 0; i < queryComboBox.getItemCount(); i++) {
						if (((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.EQUALS) {
							queryComboBox.setSelectedIndex(i);
							break;
						}
					}
				}
				internalPanel.add(queryComboBox, gbc);
				conditions.put(column, queryComboBox);
				gbc.gridx++;
				int textFieldSize = 20;
				int maxCols = 80;
				// Big textual field.
				if (columnSize > maxCols) {
					int r = columnSize / maxCols;
					// gbc.gridheight = r;
					JTextArea txtField = new JTextArea(r, maxCols);
					txtField.setBorder(UIManager.getBorder("TextField.border"));
					txtField.setLineWrap(true);
					txtField.setWrapStyleWord(true);
					txtField.setDocument(new JTextFieldLimit(columnSize));
					internalPanel.add(txtField, gbc);
					inputs.put(column, txtField);
					if (value != null) {
						txtField.setEnabled(false);
						txtField.setText(value.toString());
					}
				} else {
					// Small textual field.
					JTextField txtField = new JTextField(textFieldSize);
					txtField.setDocument(new JTextFieldLimit(columnSize));
					internalPanel.add(txtField, gbc);
					inputs.put(column, txtField);
					if (value != null) {
						txtField.setEnabled(false);
						txtField.setText(value.toString());
					}
				}
				rows++;
				break;
			// Integer fields:
			case Types.INTEGER:
			case Types.SMALLINT:
			case Types.BIGINT:
			case Types.TINYINT:
				gbc.gridx = 1;
				queryComboBox = new QueryComboBox(this);
				queryComboBox.addNumericConditions();
				internalPanel.add(queryComboBox, gbc);
				conditions.put(column, queryComboBox);
				gbc.gridx++;

				FormattedField intField = new FormattedField(columnSize, NumberFormat.getIntegerInstance());
				internalPanel.add(intField, gbc);
				inputs.put(column, intField);
				if (value != null) {
					queryComboBox.setEnabled(false);
					for (int i = 0; i < queryComboBox.getItemCount(); i++) {
						if (((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.EQUALS) {
							queryComboBox.setSelectedIndex(i);
							break;
						}
					}
					intField.setEnabled(false);
					intField.setText(value.toString());
				}

				rows++;
				break;
			// Universal numerical fields:
			case Types.NUMERIC:
			case Types.DECIMAL:
				gbc.gridx = 1;
				queryComboBox = new QueryComboBox(this);
				queryComboBox.addNumericConditions();
				internalPanel.add(queryComboBox, gbc);
				conditions.put(column, queryComboBox);
				gbc.gridx++;
				// Set precision.
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setMaximumIntegerDigits(columnSize);
				nf.setMaximumFractionDigits(column.getDecimalDigits());
				// Format field.
				FormattedField numDecField = new FormattedField(columnSize, nf);
				internalPanel.add(numDecField, gbc);
				inputs.put(column, numDecField);
				if (value != null) {
					queryComboBox.setEnabled(false);
					for (int i = 0; i < queryComboBox.getItemCount(); i++) {
						if (((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.EQUALS) {
							queryComboBox.setSelectedIndex(i);
							break;
						}
					}
					numDecField.setEnabled(false);
					numDecField.setText(value.toString());
				}
				rows++;
				break;
			// Real numerical fields:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.REAL:
				gbc.gridx = 1;
				queryComboBox = new QueryComboBox(this);
				queryComboBox.addNumericConditions();
				internalPanel.add(queryComboBox, gbc);
				conditions.put(column, queryComboBox);
				gbc.gridx++;
				NumberFormat rf = NumberFormat.getNumberInstance();
				// Set precision.
				rf.setMaximumFractionDigits(column.getDecimalDigits());
				FormattedField decField = new FormattedField(columnSize, rf);
				internalPanel.add(decField, gbc);
				inputs.put(column, decField);
				if (value != null) {
					queryComboBox.setEnabled(false);
					for (int i = 0; i < queryComboBox.getItemCount(); i++) {
						if (((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.EQUALS) {
							queryComboBox.setSelectedIndex(i);
							break;
						}
					}
					decField.setEnabled(false);
					decField.setText(value.toString());
				}
				rows++;
				break;
			// Boolean fields:
			case Types.BIT:
			case Types.BOOLEAN:
				gbc.gridx = 1;
				queryComboBox = new QueryComboBox(this);
				queryComboBox.addBooleanConditions();
				internalPanel.add(queryComboBox, gbc);
				if (value != null) {
					queryComboBox.setEnabled(false);
					for (int i = 0; i < queryComboBox.getItemCount(); i++) {
						if ((value.equals(true)
								&& ((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.TRUE)
								|| (value.equals(false)
										&& ((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.FALSE)) {
							queryComboBox.setSelectedIndex(i);
							break;
						}
					}
				}
				rows++;
				break;
			// Temporal fields:
			case Types.DATE:
				gbc.gridx = 1;
				queryComboBox = new QueryComboBox(this);
				queryComboBox.addDateTimeConditions();
				internalPanel.add(queryComboBox, gbc);
				conditions.put(column, queryComboBox);
				gbc.gridx++;
				DatePicker dp = new DatePicker();
				internalPanel.add(dp, gbc);
				inputs.put(column, dp);
				if (value != null) {
					queryComboBox.setEnabled(false);
					for (int i = 0; i < queryComboBox.getItemCount(); i++) {
						if (((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.EQUALS) {
							queryComboBox.setSelectedIndex(i);
							break;
						}
					}
					Calendar cal = Calendar.getInstance();
					cal.setTime((java.sql.Date) value);
					dp.setDate(cal.getTime());
				}
				rows++;
				break;
			case Types.TIME:
				gbc.gridx = 1;
				queryComboBox = new QueryComboBox(this);
				queryComboBox.addDateTimeConditions();
				internalPanel.add(queryComboBox, gbc);
				conditions.put(column, queryComboBox);
				gbc.gridx++;
				TimePicker tp = new TimePicker(null);
				internalPanel.add(tp, gbc);
				inputs.put(column, tp);
				if (value != null) {
					queryComboBox.setEnabled(false);
					for (int i = 0; i < queryComboBox.getItemCount(); i++) {
						if (((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.EQUALS) {
							queryComboBox.setSelectedIndex(i);
							break;
						}
					}
					Calendar cal = Calendar.getInstance();
					cal.setTime((java.sql.Time) value);
					tp.setValue(cal.getTime());
				}
				rows++;
				break;
			case Types.TIMESTAMP:
				gbc.gridx = 1;
				queryComboBox = new QueryComboBox(this);
				queryComboBox.addDateTimeConditions();
				internalPanel.add(queryComboBox, gbc);
				conditions.put(column, queryComboBox);
				gbc.gridx++;
				DateTimePicker dtp = new DateTimePicker();
				internalPanel.add(dtp, gbc);
				inputs.put(column, dtp);
				if (value != null) {
					queryComboBox.setEnabled(false);
					for (int i = 0; i < queryComboBox.getItemCount(); i++) {
						if (((Condition) queryComboBox.getItemAt(i)).getValue() == Condition.EQUALS) {
							queryComboBox.setSelectedIndex(i);
							break;
						}
					}
					Calendar cal = Calendar.getInstance();
					cal.setTime((java.sql.Timestamp) value);
					dtp.setDate(cal.getTime());
				}
				rows++;
				break;
			default:
			}
			rows++;
		}

		// Add buttons
		JButton bConfirm = new JButton(ActionConfirmSearch.getInstance());
		JButton bCancel = new JButton(ActionCancel.getInstance());
		gbc.gridx = 0;
		gbc.gridy = rows;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		internalPanel.add(bConfirm, gbc);
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		internalPanel.add(bCancel, gbc);

		/*
		 * JScrollPane scrollPane = new JScrollPane(internalPanel);
		 * scrollPane.setVerticalScrollBarPolicy(JScrollPane.
		 * VERTICAL_SCROLLBAR_ALWAYS); add(scrollPane);
		 */
		add(internalPanel);
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Returns the {@link TableModel} on which the advanced search query is performed.
	 * @return {@link TableModel}
	 */
	public TableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Sets the {@link TableModel} on which the advanced search query is performed.
	 * @param tableModel
	 */
	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * Returns a {@link HashMap} with {@link TableColumn} for key and {@link QueryComboBox} for value.
	 * The {@code HashMap} contains all conditional combo boxes.
	 * @return {@link HashMap}
	 */
	public HashMap<TableColumn, QueryComboBox> getConditions() {
		return conditions;
	}

	/**
	 * Sets a {@link HashMap} with {@link TableColumn} for key and {@link QueryComboBox} for value.
	 * The {@code HashMap} contains all conditional combo boxes.
	 * @param conditions
	 */
	public void setConditions(HashMap<TableColumn, QueryComboBox> conditions) {
		this.conditions = conditions;
	}

	/**
	 * Returns a {@link HashMap} with {@link TableColumn} for key and {@link JComponent} for value.
	 * The {@code HashMap} contains all input fields.
	 * @return {@link HashMap}
	 */
	public HashMap<TableColumn, JComponent> getInputs() {
		return inputs;
	}

	/**
	 * Sets a {@link HashMap} with {@link TableColumn} for key and {@link JComponent} for value.
	 * The {@code HashMap} contains all input fields.
	 * @param inputs
	 */
	public void setInputs(HashMap<TableColumn, JComponent> inputs) {
		this.inputs = inputs;
	}
}
