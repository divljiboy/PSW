package actions.records;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import actions.manager.ActionsManager;
import application.Application;
import gui.MainFrame;
import gui.tables.Table;
/**
 * Klasa koja vrsi pomeranje selkecije na prvi red u tabeli i selektuje ga. Klasa
 * nasledjuje <code>AbstracAction</code> Od atributa sadrzi sliku,precicu,ime i
 * kratak opis
 * 
 * @author Ivan
 *
 */
public class ActionFirstRecord extends AbstractAction {
	private static final long serialVersionUID = 1L;

	
	private static ActionFirstRecord instance = null;
	
	
	public static ActionFirstRecord getInstance() {
		if(instance==null) {
			instance = new ActionFirstRecord();
		}
		return instance;
	}
	
	private ActionFirstRecord() {
		putValue(NAME, Application.getResourceBundle().getString("FirstRecord"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/rowFirst.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("FirstRecordDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0));
		setEnabled(false);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Table.setFocusedTableViaAction(e);
		Table table = Table.getFocusedTable();
		int tableCount = table.getRowCount();
		if (tableCount > 0) {
			MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("FirstRecordStart"));
			table.setRowSelectionInterval(0,0);
			MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("FirstRecordFinish"));
		}
		ActionsManager.updateRecordActions();
		
	}

}
