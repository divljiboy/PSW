package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import gui.menus.MenuEdit;
import gui.menus.MenuFile;
import gui.menus.MenuHelp;
import gui.menus.MenuLanguages;
import gui.menus.MenuWindow;

/**
 * Class representing the look of the main manu bar of the application and its actions.
 * Contains {@link MenuFile}, {@link MenuEdit}, {@link MenuWindow}, {@link MenuHelp} and {@link MenuLanguages} menus. 
 * 
 * @author Ivan Divljak
 * @author Borko Arsovic
 * @author Milan Radeta
 * @author Isidora Skulec
 */

public class MainMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	private MenuFile menuFile;
	private MenuEdit menuEdit;
	private MenuWindow menuWindow;
	private MenuHelp menuHelp;
	private MenuLanguages menuLanguages;
	
	/**
	 * Constructor of the {@link MainMenuBar} class.
	 * Creates an extension of {@link JMenu} class for each menu in the menu bar.
	 */
	public MainMenuBar() {
		menuFile = new MenuFile();
		add(menuFile);
		
		menuEdit = new MenuEdit();
		add(menuEdit);
		
		menuWindow = new MenuWindow();
		add(menuWindow);
		
		menuHelp = new MenuHelp();
		add(menuHelp);
		
		menuLanguages = new MenuLanguages();
		menuHelp.add(menuLanguages);
	}

	/**
	 * Returns the {@link MenuFile} object of the menu bar.
	 * @return {@link MenuFile}
	 */
	public MenuFile getMenuFile() {
		return menuFile;
	}

	/**
	 * Sets the {@link MenuFile} object of the menu bar.
	 * @param menuFile
	 */
	public void setMenuFile(MenuFile menuFile) {
		this.menuFile = menuFile;
	}

	/**
	 * Returns the {@link MenuEdit} object of the menu bar.
	 * @return {@link MenuEdit}
	 */
	public MenuEdit getMenuEdit() {
		return menuEdit;
	}

	/**
	 * Sets the {@link MenuEdit} object of the menu bar.
	 * @param menuEdit
	 */
	public void setMenuEdit(MenuEdit menuEdit) {
		this.menuEdit = menuEdit;
	}

	/**
	 * Returns the {@link MenuWindow} object of the menu bar.
	 * @return {@link MenuWindow}
	 */
	public MenuWindow getMenuWindow() {
		return menuWindow;
	}

	/**
	 * Sets the {@link MenuWindow} object of the menu bar.
	 * @param menuWindow
	 */
	public void setMenuWindow(MenuWindow menuWindow) {
		this.menuWindow = menuWindow;
	}

	/**
	 * Returns the {@link MenuHelp} object of the menu bar.
	 * @return {@link MenuHelp}
	 */
	public MenuHelp getMenuHelp() {
		return menuHelp;
	}

	/**
	 * Sets the {@link MenuHelp} object of the menu bar.
	 * @param menuHelp
	 */
	public void setMenuHelp(MenuHelp menuHelp) {
		this.menuHelp = menuHelp;
	}

	/**
	 * Returns the {@link MenuLanguages} object of the menu bar.
	 * @return {@link MenuLanguages}
	 */
	public MenuLanguages getMenuLanguages() {
		return menuLanguages;
	}

	/**
	 * Sets the {@link MenuLanguages} object of the menu bar.
	 * @param menuLanguages
	 */
	public void setMenuLanguages(MenuLanguages menuLanguages) {
		this.menuLanguages = menuLanguages;
	}	
}
