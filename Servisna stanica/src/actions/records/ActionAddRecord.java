package actions.records;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import application.Application;
import gui.MainFrame;
import gui.StatusBar;
import gui.tables.Table;
import gui.tables.forms.RecordForm;
import gui.toolbars.TableToolbar;
import model.TableModel;

/**
 * 
 * Singleton class that extends {@link AbstractAction}. When action is performed
 * it opens {@link RecordForm} dialog and updates the {@link MainFrame}'s
 * {@link StatusBar}.
 * 
 * @see AbstractAction
 * @see RecordForm
 * @see MainFrame
 * @see StatusBar
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @author Isidora Škulec
 *
 */
public class ActionAddRecord extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionAddRecord} object.
	 */
	private static ActionAddRecord instance = null;

	/**
	 * Returns the only instance of {@link ActionAddRecord} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionAddRecord}
	 */
	public static ActionAddRecord getInstance() {
		if (instance == null) {
			instance = new ActionAddRecord();
		}
		return instance;
	}

	/**
	 * {@link ActionAddRecord} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Insert). Action is initially
	 * disabled.
	 * 
	 */
	private ActionAddRecord() {
		putValue(NAME, Application.getResourceBundle().getString("AddRecord"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/recordAdd.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("AddRecordDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
		setEnabled(false);
	}

	/**
	 * If e.getSource() is a {@link JComponent} whose parent is
	 * {@link TableToolbar} it sets focus to its {@link Table}. It gets the
	 * {@link TableModel} from the focused table and opens an empty
	 * {@link RecordForm}, if the table model does not have a parent table model
	 * or has exactly one record selected in parent table. If neither condition
	 * is satisfied it displays an error message. It also updates
	 * {@link MainFrame}'s {@link StatusBar}.
	 * 
	 * @see JComponent
	 * @see TableToolbar
	 * @see Table
	 * @see TableModel
	 * @see RecordForm
	 * @see MainFrame
	 * @see StatusBar
	 * @see Table#setFocusedTableViaAction(ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("AddRecordStart"));
		Table.setFocusedTableViaAction(e);
		TableModel tableModel = Table.getFocusedTable().getTableModel();
		if (tableModel != null) {
			if ((tableModel.getParentTableModel() == null)
					|| (tableModel.getParentTableModel().getTable().getSelectedRowCount() == 1)) {
				RecordForm addForm = new RecordForm(tableModel, true);
				addForm.setVisible(true);
			} else {
				Application.showErrorMessage("ParentTableSelectedNone");
			}
		} else {
			MainFrame.getInstance().getStatusBar().setMessage("");
		}
	}

}