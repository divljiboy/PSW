package gui.tables.forms;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Types;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import actions.cancel.ActionCancel;
import actions.records.ActionConfirmAddRecord;
import actions.records.ActionConfirmModifyRecord;
import application.Application;
import gui.MainFrame;
import gui.tables.Table;
import listeners.form.ComboBoxListener;
import listeners.form.NullFocusListener;
import model.ComboBoxItem;
import model.Record;
import model.TableColumn;
import model.TableModel;
import model.TableSchema;

/**
 * This class contains a form created dynamically from {@link TableSchema}. It
 * extends the {@link JDialog} class. All labels and inputs are inside a
 * {@link JPanel} with {@link GridBagLayout}. Additionally, form can be
 * initialized with values of a selected {@link Record} from focused
 * {@link Table}'s {@link TableModel}, if it's a Modify Record Form.
 * 
 * @author Isidora Škulec
 * @author Milan Radeta
 */
public class RecordForm extends JDialog {

	private static final long serialVersionUID = 1L;

	private static JFrame mainFrame = null;
	
	/**
	 * {@link TableModel} which {@link RecordForm} is linked to.
	 */
	private TableModel tableModel;
	/**
	 * {@link Record} which {@link RecordForm} is linked to if a record is being
	 * modified.
	 */
	private Record record;
	/**
	 * {@link HashMap} of inputs, where keys are {@link TableColumn}.
	 */
	private HashMap<TableColumn, JComponent> inputs = new HashMap<>();
	/**
	 * {@link HashMap} of comboBoxes, where keys are {@link TableColumn}.
	 */
	private HashMap<TableColumn, ComboBox> comboBoxes = new HashMap<>();

	/**
	 * Constructor of {@link RecordForm} class. Parameter {@code add} signifies
	 * whether this form is called with adding ({@code true}) of modifying (
	 * {@code false}) purpose. Parameter {@code model} is a reference to the
	 * {@link TableModel} object, whose data is used to create the form. It
	 * dynamically creates inputs and labels from {@code model}'s
	 * {@link TableSchema}. For foreign key columns, {@link ComboBox} of
	 * {@link ComboBoxItem}s is used.
	 * 
	 * @param model
	 * @param add
	 */
	public RecordForm(TableModel model, boolean add) {
		super(mainFrame, true);
		
		this.tableModel = model;
		// Is this Record going to be new or modified?
		if (add) {
			ActionConfirmAddRecord.getInstance().setRecordForm(this);
			record = new Record();
		} else {
			ActionConfirmModifyRecord.getInstance().setRecordForm(this);
			Table table = Table.getFocusedTable();
			int row = table.convertRowIndexToModel(table.getSelectedRow());
			record = new Record(model.getRecords().get(row));
		}
		// Set buttons.
		ActionCancel.getInstance().setDialog(this);

		// Set StatusBar message, title and icon.
		if (add) {
			if (mainFrame != null) {
				MainFrame mf = (MainFrame) mainFrame;
				mf.getStatusBar()
					.setMessage(Application.getResourceBundle().getString("AddRecordFinish"));
			}
			setTitle(Application.getResourceBundle().getString("AddRecord"));
			setIconImage(new ImageIcon(Application.class.getResource("/icons/recordAdd.png")).getImage());
		} else {
			if (mainFrame != null) {
				MainFrame mf = (MainFrame) mainFrame;
				mf.getStatusBar()
					.setMessage(Application.getResourceBundle().getString("ModifyRecordFinish"));
			}
			setTitle(Application.getResourceBundle().getString("ModifyRecord"));
			setIconImage(new ImageIcon(Application.class.getResource("/icons/recordEdit.png")).getImage());
		}

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
		for (TableColumn column : model.getTableSchema().getColumns()) {
			int columnType = column.getType();
			int columnSize = column.getSize();

			Object refValue = tableModel.findParentRefColValue(column);
			// Label the row.
			gbc.gridx = 0;
			gbc.gridy = rows;
			String lblText = column.getName() + (!column.isNullable() ? "* :" : ":");
			JLabel lbl = new JLabel(lblText);
			internalPanel.add(lbl, gbc);

			// Prepare the field layout.
			gbc.gridx = 1;

			// Is this column a foreign key?
			ArrayList<TableColumn> referencedColumns = column.getReferencedTableColumns();

			if (!referencedColumns.isEmpty() && column.getSemanticTableColumn() == null) {
				// Create comboBox and add values.
				ComboBox cb = new ComboBox(this, column);
				if (refValue == null) {
					if (!add) {
						Object value = record.getValues().get(column);
						if (value != null) {
							for (int i = 0; i < cb.getItemCount(); i++) {
								ComboBoxItem item = cb.getItemAt(i);
								if (item != null && item.getValue().equals(value)) {
									cb.setSelectedIndex(i);
									break;
								}
							}
						}
					} else {
						record.getValues().put(column, cb.getItemAt(0) != null ? cb.getItemAt(0).getLabel() : null);
					}
				}
				internalPanel.add(cb, gbc);
				inputs.put(column, cb);
				comboBoxes.put(column, cb);
				rows++;
			} else {
				// Is it a string? Is it a plane?
				switch (columnType) {

				// Textual fields:
				case Types.CHAR:
				case Types.NCHAR:
				case Types.VARCHAR:
				case Types.NVARCHAR:
				case Types.LONGVARCHAR:
				case Types.LONGNVARCHAR:
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
						if (!add) {
							Object value = record.getValues().get(column);
							if (value != null) {
								txtField.setText(value.toString());
							}
						}

						if (!column.isNullable()) {
							txtField.addFocusListener(new NullFocusListener(txtField));
						}

						rows++;
					} else {
						// Small textual field.
						JTextField txtField = new JTextField(textFieldSize);
						txtField.setDocument(new JTextFieldLimit(columnSize));
						internalPanel.add(txtField, gbc);
						inputs.put(column, txtField);
						if (!add) {
							Object value = record.getValues().get(column);
							if (value != null) {
								txtField.setText(value.toString());
							}
						}

						if (!column.isNullable()) {
							txtField.addFocusListener(new NullFocusListener(txtField));
						}

						rows++;
					}

					break;

				// Integer fields:
				case Types.INTEGER:
				case Types.SMALLINT:
				case Types.BIGINT:
				case Types.TINYINT:
					FormattedField intField = new FormattedField(columnSize, NumberFormat.getIntegerInstance());
					internalPanel.add(intField, gbc);
					inputs.put(column, intField);
					if (!add) {
						Object value = record.getValues().get(column);
						if (value != null) {
							intField.setText(value.toString());
						}
					}

					if (!column.isNullable()) {
						intField.addFocusListener(new NullFocusListener(intField));
					}

					rows++;
					break;

				// Universal numerical fields:
				case Types.NUMERIC:
				case Types.DECIMAL:
					// Set precision.
					NumberFormat nf = NumberFormat.getNumberInstance();
					nf.setMaximumIntegerDigits(columnSize);
					nf.setMaximumFractionDigits(column.getDecimalDigits());
					// Format field.
					FormattedField numDecField = new FormattedField(columnSize, nf);
					internalPanel.add(numDecField, gbc);
					inputs.put(column, numDecField);
					if (!add) {
						Object value = record.getValues().get(column);
						if (value != null) {
							numDecField.setText(value.toString());
						}
					}

					if (!column.isNullable()) {
						numDecField.addFocusListener(new NullFocusListener(numDecField));
					}

					rows++;
					break;

				// Real numerical fields:
				case Types.DOUBLE:
				case Types.FLOAT:
				case Types.REAL:
					NumberFormat rf = NumberFormat.getNumberInstance();
					// Set precision.
					rf.setMaximumFractionDigits(column.getDecimalDigits());
					FormattedField decField = new FormattedField(columnSize, rf);
					internalPanel.add(decField, gbc);
					inputs.put(column, decField);
					if (!add) {
						Object value = record.getValues().get(column);
						if (value != null) {
							decField.setText(value.toString());
						}
					}

					if (!column.isNullable()) {
						decField.addFocusListener(new NullFocusListener(decField));
					}

					rows++;

					break;

				// Boolean fields:
				case Types.BIT:
				case Types.BOOLEAN:
					JCheckBox cBox = new JCheckBox();
					internalPanel.add(cBox, gbc);
					inputs.put(column, cBox);
					if (!add) {
						Object value = record.getValues().get(column);
						if (value != null) {
							cBox.setSelected(value.toString().equals("1") ? true : false);
						}
					}
					rows++;
					break;

				// Temporal fields:
				case Types.DATE:
					DatePicker dp = new DatePicker();
					internalPanel.add(dp, gbc);
					inputs.put(column, dp);
					if (!add) {
						Object value = record.getValues().get(column);
						if (value != null) {
							Calendar cal = Calendar.getInstance();
							cal.setTime((java.sql.Date) value);
							dp.setDate(cal.getTime());
						}
					}
					rows++;
					break;
				case Types.TIME:
					TimePicker tp = new TimePicker(null);
					internalPanel.add(tp, gbc);
					inputs.put(column, tp);
					if (!add) {
						Object value = record.getValues().get(column);
						if (value != null) {
							Calendar cal = Calendar.getInstance();
							cal.setTime((java.sql.Time) value);
							tp.setValue(cal.getTime());
						}
					}
					rows++;
					break;
				case Types.TIMESTAMP:
					DateTimePicker dtp = new DateTimePicker();
					internalPanel.add(dtp, gbc);
					rows++;
					inputs.put(column, dtp);

					if (!add) {
						Object value = record.getValues().get(column);
						if (value != null) {
							Calendar cal = Calendar.getInstance();
							cal.setTime((java.sql.Timestamp) value);
							dtp.setDate(cal.getTime());
						}
					}
					break;
				}
			}
		}

		// Add buttons
		JButton bConfirm;
		if (add) {
			bConfirm = new JButton(ActionConfirmAddRecord.getInstance());
		} else {
			bConfirm = new JButton(ActionConfirmModifyRecord.getInstance());
		}
		JButton bCancel = new JButton(ActionCancel.getInstance());
		gbc.gridx = 0;
		gbc.gridy = rows;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		internalPanel.add(bConfirm, gbc);
		gbc.gridx = 1;
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
		ComboBoxListener.setUpdating(false);
	}

	/**
	 * Validates fields by checking all primary key columns and non-nullable
	 * columns.
	 * 
	 * @return boolean
	 */
	public boolean validateFields() {
		for (TableColumn column : tableModel.getTableSchema().getColumns()) {
			if (column.isPrimaryKey() || !column.isNullable()) {
				JComponent input = inputs.get(column);
				if (input instanceof FormattedField) {
					String value = ((FormattedField) input).getText();
					if (value.equals("")) {
						return false;
					}
				} else if (input instanceof JTextArea) {
					String value = ((JTextArea) input).getText();
					if (value.equals("")) {
						return false;
					}
				} else if (input instanceof JTextField) {
					String value = ((JTextField) input).getText();
					if (value.equals("")) {
						return false;
					}
				} else if (input instanceof JComboBox<?>) {
					@SuppressWarnings("unchecked")
					JComboBox<ComboBoxItem> comboBox = (JComboBox<ComboBoxItem>) input;
					ComboBoxItem item = (ComboBoxItem) comboBox.getSelectedItem();
					if (item == null) {
						return false;
					}
				} else if (input instanceof DateTimePicker) {
					String value = ((DateTimePicker) input).getValue();
					if (value == "") {
						return false;
					}
				} else if (input instanceof TimePicker) {
					String value = ((TimePicker) input).getValueAsString();
					if (value == "") {
						return false;
					}
				} else if (input instanceof DatePicker) {
					String value = ((DatePicker) input).getValue();
					if (value == "") {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * Getter method for model representing the table this form is for.
	 * 
	 * @return {@link TableModel}
	 */
	public TableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Setter method for model representing the table this form is for.
	 * 
	 * @param tableModel
	 */
	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * Getter for {@link HashMap} containing all input fields (
	 * {@link JComponent}) for each {@link TableColumn}.
	 * 
	 * @return {@link HashMap}
	 */
	public HashMap<TableColumn, JComponent> getInputs() {
		return inputs;
	}

	/**
	 * Setter for {@link HashMap} containing all input fields (
	 * {@link JComponent}) for each {@link TableColumn}.
	 * 
	 * @param inputs
	 */
	public void setInputs(HashMap<TableColumn, JComponent> inputs) {
		this.inputs = inputs;
	}

	/**
	 * Returns a {@link HashMap} of all combo boxes in {@link RecordForm}, keyed
	 * by {@link TableColumn}.
	 * 
	 * @return {@link HashMap}
	 */
	public HashMap<TableColumn, ComboBox> getComboBoxes() {
		return comboBoxes;
	}

	/**
	 * Sets a {@link HashMap} of combo boxes in {@link RecordForm}, keyed by
	 * {@link TableColumn}.
	 * 
	 * @param comboBoxes
	 */
	public void setComboBoxes(HashMap<TableColumn, ComboBox> comboBoxes) {
		this.comboBoxes = comboBoxes;
	}

	/**
	 * Returns a {@link Record} linked to the {@link RecordForm}.
	 * 
	 * @return {@link Record}
	 */
	public Record getRecord() {
		return record;
	}

	/**
	 * Sets a {@link Record} in {@link RecordForm}.
	 * 
	 * @param record
	 */
	public void setRecord(Record record) {
		this.record = record;
	}

	/**
	 * Returns reference to parent {@link JFrame}.
	 * @return {@link JFrame}
	 */
	public static JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * Sets reference to parent {@link JFrame}.
	 * @param mainFrame
	 */
	public static void setMainFrame(JFrame mainFrame) {
		RecordForm.mainFrame = mainFrame;
	}
	
	
}
