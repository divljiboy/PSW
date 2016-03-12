package gui.tables.forms;

import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import application.Application;
import database.DatabaseConnection;
import listeners.form.ComboBoxListener;
import model.ComboBoxItem;
import model.Record;
import model.TableColumn;
import model.TableSchema;

/**
 * Extension of {@link JComboBox} which is used for selecting foreign key values in {@link RecordForm}.
 * 
 * @author Milan Radeta
 * @author Isidora Škulec
 *
 */
public class ComboBox extends JComboBox<ComboBoxItem> {
	private static final long serialVersionUID = 1L;

	/**
	 * {@link RecordForm} {@link ComboBox} is linked to.
	 * 
	 * @see RecordForm
	 */
	private RecordForm recordForm;

	/**
	 * {@link TableColumn} from {@link TableSchema} {@link ComboBox} is linked
	 * to.
	 * 
	 * @see TableColumn
	 * @see TableSchema
	 */
	private TableColumn column;
	
	/**
	 * Empty constructor.
	 */
	public ComboBox() {
		
	}

	/**
	 * Constructor which sets {@link RecordForm} and {@link TableColumn}
	 * {@link ComboBox} is linked to. It also adds {@link ComboBoxListener} as
	 * {@link ItemListener} and updates items.
	 * 
	 * @param recordForm
	 * @param column
	 * @see RecordForm
	 * @see TableColumn
	 * @see ComboBox
	 * @see ComboBoxListener
	 * @see ItemListener
	 * @see #updateItems()
	 * 
	 */
	public ComboBox(RecordForm recordForm, TableColumn column) {
		this.setRecordForm(recordForm);
		this.setColumn(column);
		addItemListener(new ComboBoxListener(this));
		updateItems();
	}

	/**
	 * Gets values for {@link TableColumn} by creating an SQL SELECT
	 * {@link PreparedStatement}. Initially it clears the {@link ComboBox}
	 * items. If {@link #column} is nullable, it adds <code>null</code> as an
	 * item. Then it gets values of first referenced table column from
	 * referenced table column's table. Additionally, {@link PreparedStatement}
	 * adds WHERE conditions if there are other columns that reference the
	 * columns in the table same table as referenced column.
	 */
	public void updateItems() {
		removeAllItems();

		if (column.isNullable()) {
			addItem(null);
		}
		TableColumn referencedColumn = column.getReferencedTableColumns()
				.get(0);
		// Create query to get all possible values for this foreign
		// key.
		StringBuilder query = new StringBuilder("SELECT "
				+ referencedColumn.getCode() + " FROM "
				+ referencedColumn.getTable().getCode());

		// Check if child table had composite primary key including
		// parent primary key.
		ArrayList<String> conditions = new ArrayList<>();
		ArrayList<Object> conditionParameters = new ArrayList<>();

		Record record = recordForm.getRecord();
		for (TableColumn col : column.getTable().getColumns()) {
			if (col != column) {
				for (TableColumn refCol : col.getReferencedTableColumns()) {
					if (refCol.getTable() == referencedColumn.getTable()
							&& refCol.isPrimaryKey()
							&& refCol != referencedColumn
							&& record.getValues().get(col) != null) {
						conditions.add(refCol.getCode() + " = ?");
						conditionParameters.add(record.getValues().get(col));
						break;
					}
				}
			}
		}

		// Include composite key conditions in the query.
		if (conditions.size() > 0) {
			query.append(" WHERE ");
			for (String condition : conditions) {
				query.append(condition + " and ");
			}
			query.replace(query.length() - 5, query.length(), "");
		}

		// Perform database query to get possible values.
		try {
			PreparedStatement statement = DatabaseConnection.getInstance()
					.getConn().prepareStatement(query.toString());
			for (int i = 0; i < conditionParameters.size(); i++) {
				statement.setObject(i + 1, conditionParameters.get(i));
			}

			ResultSet rsReferenced = statement.executeQuery();
			while (rsReferenced.next()) {
				addItem(new ComboBoxItem(rsReferenced.getObject(1),
						rsReferenced.getObject(1)));
			}
			Object refValue = recordForm.getTableModel().findParentRefColValue(
					column);
			if (refValue != null) {
				setEnabled(false);
				for (int i = 0; i < getItemCount(); i++) {
					ComboBoxItem item = getItemAt(i);
					if (item != null && item.getValue().equals(refValue)) {
						setSelectedIndex(i);
						break;
					}
				}
			}
			rsReferenced.close();
			statement.close();

		} catch (SQLException e1) {
			Application.showSqlExceptionError(e1);
		}
	}

	/**
	 * Returns the parent {@link RecordForm} of this combo box.
	 * @return {@link RecordForm}
	 */
	public RecordForm getRecordForm() {
		return recordForm;
	}

	/**
	 * Sets the parent {@link RecordForm} of this combo box.
	 * @param recordForm
	 */
	public void setRecordForm(RecordForm recordForm) {
		this.recordForm = recordForm;
	}

	/**
	 * Returns the {@link TableColumn} for which the combo box determines value.
	 * @return {@link TableColumn}
	 */
	public TableColumn getColumn() {
		return column;
	}

	/**
	 * Sets the {@link TableColumn} for which the combo box determines value.
	 * @param column
	 */
	public void setColumn(TableColumn column) {
		this.column = column;
	}
}
