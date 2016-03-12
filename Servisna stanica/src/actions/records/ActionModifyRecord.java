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
 *
 */
public class ActionModifyRecord extends AbstractAction {
	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionModifyRecord} object.
	 */
	private static ActionModifyRecord instance = null;

	/**
	 * Returns the only instance of {@link ActionModifyRecord} object. If it does
	 * not exist, it will be created.
	 * 
	 * @return {@link ActionModifyRecord}
	 */
	public static ActionModifyRecord getInstance() {
		if (instance == null) {
			instance = new ActionModifyRecord();
		}
		return instance;
	}

	/**
	 * {@link ActionModifyRecord} private constructor. Initializes the object with
	 * name and short description from localization properties file, as well
	 * with small icon and accelerator key (Ctrl + E). Action is initially
	 * disabled.
	 * 
	 */
	private ActionModifyRecord() {
		putValue(NAME, Application.getResourceBundle().getString("ModifyRecord"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/recordEdit.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("ModifyRecordDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
		setEnabled(false);
	}

	/**
	 * If e.getSource() is a {@link JComponent} whose parent is
	 * {@link TableToolbar} it sets focus to its {@link Table}. It gets the
	 * {@link TableModel} from the focused table and opens a
	 * {@link RecordForm} with the values of selected record in {@link Table},
	 * if the table model does not have a parent table model
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

		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("ModifyRecordStart"));

		Table.setFocusedTableViaAction(e);
		if (Table.getFocusedTable().getSelectedRowCount() == 1) {
			TableModel tableModel = Table.getFocusedTable().getTableModel();
			if ((tableModel.getParentTableModel() == null)
					|| (tableModel.getParentTableModel().getTable().getSelectedRowCount() == 1)) {
				RecordForm addForm = new RecordForm(tableModel, false);
				addForm.setVisible(true);
			} else {
				Application.showErrorMessage("ParentTableSelectedNone");
			}
		} else {
			Application.showErrorMessage("SelectOneRow");
		}
	}

}