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
 * Klasa koja vrsi pomeranje slekecije na poslednji red u tabeli i selektuje ga. Klasa
 * nasledjuje <code>AbstracAction</code> Od atributa sadrzi sliku,precicu,ime i
 * kratak opis
 * 
 * @author Ivan
 *
 */
public class ActionLastRecord extends AbstractAction {
	private static final long serialVersionUID = 1L;

	private static ActionLastRecord instance = null;
	
	
	public static ActionLastRecord getInstance() {
		if(instance==null) {
			instance = new ActionLastRecord();
		}
		return instance;
	}
	
	private ActionLastRecord() {
		putValue(NAME, Application.getResourceBundle().getString("LastRecord"));
		putValue(SMALL_ICON,new ImageIcon(Application.class.getResource("/icons/rowLast.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("LastRecordDesc"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_END, 0));
		setEnabled(false);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Table.setFocusedTableViaAction(e);
		Table table = Table.getFocusedTable();
		int tableCount = table.getRowCount();
		if (tableCount > 0) {
			MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("LastRecordStart"));
			table.setRowSelectionInterval(tableCount-1,tableCount-1);
			MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("LastRecordFinish"));
		}
		ActionsManager.updateRecordActions();
		
		
		

	}

}
