package actions.records;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import actions.manager.ActionsManager;
import application.Application;
import gui.MainFrame;
import gui.tables.Table;
/**
 * Klasa koja omogucuje selekciju prethodnog reda. Klasa
 * nasledjuje <code>AbstracAction</code> Od atributa sadrzi sliku,precicu,ime i
 * kratak opis
 * 
 * @author Ivan
 *
 */
public class ActionPreviousRecord extends AbstractAction {
	private static final long serialVersionUID = 1L;

	
	private static ActionPreviousRecord instance = null;
	
	
	public static ActionPreviousRecord getInstance() {
		if(instance==null) {
			instance = new ActionPreviousRecord();
		}
		return instance;
	}
	
	private ActionPreviousRecord() {
		putValue(NAME, Application.getResourceBundle().getString("PreviousRecord"));
		putValue(SMALL_ICON, new ImageIcon(Application.class.getResource("/icons/rowPrevious.png")));
		putValue(SHORT_DESCRIPTION, Application.getResourceBundle().getString("PreviousRecordDesc"));
		setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		Table.setFocusedTableViaAction(e);
		Table table = Table.getFocusedTable();
		int selectedIndex = table.getSelectedRow();
		if (selectedIndex > 0) {
			MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("PreviousRecordStart"));
			table.setRowSelectionInterval(selectedIndex - 1, selectedIndex-1);
			MainFrame.getInstance().getStatusBar().setMessage(Application.getResourceBundle().getString("PreviousRecordFinish"));
		}
		
		ActionsManager.updateRecordActions();
		

	}

}
