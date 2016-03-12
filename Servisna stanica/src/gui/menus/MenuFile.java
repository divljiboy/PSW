package gui.menus;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import actions.exit.ActionExit;
import actions.logout.ActionLogout;
import actions.tree.ActionCollapseAll;
import actions.tree.ActionCollapseNode;
import actions.tree.ActionExpandAll;
import actions.tree.ActionExpandNode;
import actions.tree.ActionOpenInTab;
import application.Application;

/**
 * Extension of {@link JMenu} class representing the file menu.
 * It contains open ({@link ActionOpenInTab}) 
 * and node ({@link ActionExpandNode}, {@link ActionCollapseNode}, {@link ActionExpandAll}, {@link ActionCollapseAll}) actions.
 * 
 * @author Milan Radeta
 * 
 */
public class MenuFile extends JMenu {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "File";

	/**
	 * Constructor of {@link MenuFile} class.
	 * Creates a menu with necessary actions.
	 */
	public MenuFile() {
		super(Application.getResourceBundle().getString(NAME));
		setMnemonic(KeyEvent.VK_F);

		add(ActionOpenInTab.getInstance());
		add(ActionExpandNode.getInstance());
		add(ActionCollapseNode.getInstance());
		addSeparator();
		add(ActionExpandAll.getInstance());
		add(ActionCollapseAll.getInstance());
		addSeparator();
		add(ActionLogout.getInstance());
		add(ActionExit.getInstance());
	}

}
