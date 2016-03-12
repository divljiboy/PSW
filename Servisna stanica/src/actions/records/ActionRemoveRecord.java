package actions.records;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import application.Application;
import database.DatabaseConnection;
import gui.MainFrame;
import gui.tables.Table;
import model.Record;
import model.TableColumn;
import model.TableModel;

/**
 * Klasa koja omogucuje brisanje prethodno izabranog sloga. Klasa nasledjuje
 * <code>AbstracAction</code> Od atributa sadrzi sliku,precicu,ime i kratak opis
 * 
 * @author Ivan
 *
 */
public class ActionRemoveRecord extends AbstractAction {
	private static final long serialVersionUID = 1L;

	private static ActionRemoveRecord instance = null;

	public static ActionRemoveRecord getInstance() {
		if (instance == null) {
			instance = new ActionRemoveRecord();
		}
		return instance;
	}

	private ActionRemoveRecord() {
		putValue(NAME, Application.getResourceBundle().getString("RemoveRecord"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/recordDelete.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("RemoveRecordDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		Table.setFocusedTableViaAction(e);
		if (Table.getFocusedTable().getSelectedRowCount() == 1) {
			int odg = JOptionPane.showConfirmDialog(null,
					Application.getResourceBundle().getString("RemoveRecordConfirm"),
					Application.getResourceBundle().getString("RemoveRecord"),
					JOptionPane.YES_NO_OPTION);

			if (odg == JOptionPane.YES_OPTION) {
				MainFrame.getInstance().getStatusBar()
						.setMessage(Application.getResourceBundle().getString("RemoveRecordStart"));
				Table table = Table.getFocusedTable();
				TableModel tableModel = table.getTableModel();
				if ((tableModel.getParentTableModel() == null)
						|| (tableModel.getParentTableModel().getTable().getSelectedRowCount() == 1)) {

					try {
						Record record = tableModel.getRecords()
								.get(table.convertRowIndexToModel(table.getSelectedRow()));
						String tableName = tableModel.getTableSchema().getCode();

						// kupi primarne kljuceve i njegove vrednosti
						ArrayList<String> keys = new ArrayList<>();
						ArrayList<String> values = new ArrayList<>();
						for (TableColumn col : tableModel.getTableSchema().getColumns()) {
							if (col.isPrimaryKey()) {
								keys.add(col.getCode() + " = ?");
								values.add(record.getValues().get(col).toString());
							}
						}

						// kreiranje upita
						StringBuilder query = new StringBuilder("DELETE FROM " + tableName + " WHERE ");

						// Dodavanje uslova u upit
						if (keys.size() > 0) {
							for (String key : keys) {
								query.append(key + " and ");
							}
							query.replace(query.length() - 5, query.length(), "");
						}

						PreparedStatement statement = DatabaseConnection.getInstance().getConn()
								.prepareStatement(query.toString());
						// popunjavanje ?
						for (int i = 0; i < values.size(); i++) {
							statement.setObject(i + 1, values.get(i));

						}

						statement.executeUpdate();
						statement.close();
						tableModel.refreshData(TableModel.REMOVE);
					} catch (SQLException e2) {
						Application.showSqlExceptionError(e2);
					}

					MainFrame.getInstance().getStatusBar()
							.setMessage(Application.getResourceBundle().getString("RemoveRecordFinish"));

				} else {
					Application.showErrorMessage("ParentTableSelectedNone");
				}
			}
		} else {
			Application.showErrorMessage("SelectOneRow");
		}
	}

}