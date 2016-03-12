package actions.records;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import application.Application;
import gui.MainFrame;
import gui.tables.Table;
import model.TableModel;

/**
 * Klasa koja omogucuje osvezavanje tabele ukoliko se u prethodnom koraku desila
 * neka izmena. Klasa nasledjuje <code>AbstracAction</code> Od atributa sadrzi
 * sliku,precicu,ime i kratak opis
 * 
 * @author Ivan
 *
 */
public class ActionRefresh extends AbstractAction {
	private static final long serialVersionUID = 1L;

	private static ActionRefresh instance = null;

	public static ActionRefresh getInstance() {
		if (instance == null) {
			instance = new ActionRefresh();
		}
		return instance;
	}

	private ActionRefresh() {
		putValue(NAME, Application.getResourceBundle().getString("RefreshTable"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/recordRefresh.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("RefreshTableDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Table.setFocusedTableViaAction(e);
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("RefreshTableStart"));
		TableModel tableModel = Table.getFocusedTable().getTableModel();
		tableModel.getConditions().clear();
		tableModel.getConditionValues().clear();
		tableModel.refreshData(TableModel.NONE);
		tableModel.getTable().getTableColumnAdjuster().adjustColumns();
		MainFrame.getInstance().getStatusBar()
				.setMessage(Application.getResourceBundle().getString("RefreshTableFinish"));

	}

}
