package actions.records;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import actions.manager.ActionsManager;
import application.Application;
import gui.MainFrame;
import gui.tables.Table;

/**
 * Klasa koja omogucuje selekciju narednog reda. Klasa nasledjuje
 * <code>AbstracAction</code> Od atributa sadrzi sliku,precicu,ime i kratak opis
 * 
 * @author Ivan
 *
 */
public class ActionNextRecord extends AbstractAction {
	private static final long serialVersionUID = 1L;

	private static ActionNextRecord instance = null;

	public static ActionNextRecord getInstance() {
		if (instance == null) {
			instance = new ActionNextRecord();
		}
		return instance;
	}

	private ActionNextRecord() {
		putValue(NAME, Application.getResourceBundle().getString("NextRecord"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/rowNext.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("NextRecordDesc"));
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		Table.setFocusedTableViaAction(e);
		Table table = Table.getFocusedTable();
		int selectedIndex = table.getSelectedRow();
		int maxIndex = table.getRowCount() - 1;
		if (selectedIndex >= 0 && selectedIndex < maxIndex) {
			MainFrame.getInstance().getStatusBar()
					.setMessage(Application.getResourceBundle().getString("NextRecordStart"));
			table.setRowSelectionInterval(selectedIndex + 1, selectedIndex + 1);
			MainFrame.getInstance().getStatusBar()
					.setMessage(Application.getResourceBundle().getString("NextRecordFinish"));
		}
		ActionsManager.updateRecordActions();

	}

}
