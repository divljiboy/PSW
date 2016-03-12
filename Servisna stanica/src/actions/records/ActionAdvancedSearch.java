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
import gui.tables.forms.QueryForm;
import gui.toolbars.TableToolbar;
import model.TableModel;

/**
 * 
 * Singleton class that extends {@link AbstractAction}. When action is performed
 * it opens {@link QueryForm} dialog and updates the {@link MainFrame}'s
 * {@link StatusBar}.
 * 
 * @see AbstractAction
 * @see QueryForm
 * @see MainFrame
 * @see StatusBar
 * @author Milan Radeta
 * @author Borko Arsović
 *
 */
public class ActionAdvancedSearch extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * Represents the only instance of {@link ActionAdvancedSearch} object.
	 */
	private static ActionAdvancedSearch instance = null;

	/**
	 * Returns the only instance of {@link ActionAdvancedSearch} object. If it
	 * does not exist, it will be created.
	 * 
	 * @return {@link ActionAdvancedSearch}
	 */
	public static ActionAdvancedSearch getInstance() {
		if (instance == null) {
			instance = new ActionAdvancedSearch();
		}

		return instance;
	}

	/**
	 * {@link ActionAdvancedSearch} private constructor. Initializes the object
	 * with name and short description from localization properties file, as
	 * well with small icon and accelerator key (Ctrl + F). Action is initially
	 * disabled.
	 * 
	 */
	private ActionAdvancedSearch() {
		putValue(NAME, Application.getResourceBundle().getString("Search"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/search.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("SearchDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		setEnabled(false);
	}

	/**
	 * If e.getSource() is a {@link JComponent} whose parent is
	 * {@link TableToolbar} it sets focus to its {@link Table}. It gets the
	 * {@link TableModel} from the focused table and opens an empty
	 * {@link QueryForm}, if the table model does not have a parent table model
	 * or has exactly one record selected in parent table. If neither condition
	 * is satisfied it displays an error message. It also updates
	 * {@link MainFrame}'s {@link StatusBar}.
	 * 
	 * @see JComponent
	 * @see TableToolbar
	 * @see Table
	 * @see TableModel
	 * @see QueryForm
	 * @see MainFrame
	 * @see StatusBar
	 * @see Table#setFocusedTableViaAction(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("SearchStart"));

		Table.setFocusedTableViaAction(e);
		TableModel tableModel = Table.getFocusedTable().getTableModel();
		if (tableModel != null) {
			if ((tableModel.getParentTableModel() == null)
					|| (tableModel.getParentTableModel().getTable().getSelectedRowCount() == 1)) {
				QueryForm queryForm = new QueryForm(tableModel);
				queryForm.setVisible(true);
			} else {
				Application.showErrorMessage("ParentTableSelectedNone");
			}
		} else {
			MainFrame.getInstance().getStatusBar().setMessage("");
		}
	}

}
