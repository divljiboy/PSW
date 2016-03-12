package gui.menus;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import actions.records.ActionAddRecord;
import actions.records.ActionFirstRecord;
import actions.records.ActionLastRecord;
import actions.records.ActionModifyRecord;
import actions.records.ActionNextRecord;
import actions.records.ActionPreviousRecord;
import actions.records.ActionRefresh;
import actions.records.ActionRemoveRecord;
import application.Application;

/**
 * Extension of {@link JMenu} class representing the edit menu.
 * It contains modification ({@link ActionAddRecord}, {@link ActionModifyRecord}, {@link ActionRemoveRecord}) 
 * and navigation ({@link ActionFirstRecord}, {@link ActionLastRecord}, {@link ActionNextRecord}, {@link ActionPreviousRecord}) actions.
 * 
 * @author Milan Radeta
 */

public class MenuEdit extends JMenu {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "Edit";

	/**
	 * Constructor of {@link MenuEdit} class.
	 * Creates a menu with necessary actions.
	 */
	public MenuEdit() {
		super(Application.getResourceBundle().getString(NAME));
		setMnemonic(KeyEvent.VK_E);
		add(ActionAddRecord.getInstance());
		add(ActionModifyRecord.getInstance());
		add(ActionRemoveRecord.getInstance());

		addSeparator();

		add(ActionFirstRecord.getInstance());
		add(ActionPreviousRecord.getInstance());
		add(ActionNextRecord.getInstance());
		add(ActionLastRecord.getInstance());

		addSeparator();

		add(ActionRefresh.getInstance());
	}

}