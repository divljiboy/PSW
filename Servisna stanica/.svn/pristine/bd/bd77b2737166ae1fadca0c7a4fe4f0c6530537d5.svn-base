package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import application.Application;
import gui.tables.MainPanel;
import gui.tables.TabbedPane;
import gui.tables.forms.RecordForm;
import gui.toolbars.MainToolbar;
import gui.tree.Tree;
import listeners.frame.MainFrameListener;

/**
 * Class representing the look of the main frame of the application. Extends the
 * {@link JFrame} class. Contains {@link MainMenuBar}, {@link MainToolbar},
 * {@link StatusBar} and {@link MainSplitPane}. It is based on the singleton
 * design pattern.
 * 
 * @author Ivan Divljak
 * @author Borko Arsovic
 * @author Milan Radeta
 * @author Isidora Škulec
 */

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static MainFrame instance = null;

	private MainFrameListener mainFrameListener;

	private MainMenuBar mainMenuBar;
	private MainToolbar toolbar;
	private StatusBar statusBar;
	private MainSplitPane mainSplitPane; // deli se na stablo i centralni deo
											// ekrana

	/* Singleton */

	/**
	 * Returns the only instance of the {@link MainFrame} class for this
	 * application. If there is none, a new one will be created.
	 * 
	 * @return {@link MainFrame}
	 */
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	/**
	 * Private constructor of the {@link MainFrame} class. It sets the
	 * dimensions, icon, title, location and creates basic elements of the GUI.
	 */
	private MainFrame() {
		RecordForm.setMainFrame(this);
		Dimension screenSize = Application.getToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(new Dimension(screenWidth * 3 / 4, screenHeight * 3 / 4));

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle(Application.getResourceBundle().getString("ServiceStation"));
		setIconImage(new ImageIcon(Application.class.getResource("/icons/logo.png")).getImage());

		mainMenuBar = new MainMenuBar();
		setJMenuBar(mainMenuBar);

		statusBar = new StatusBar();
		add(statusBar, BorderLayout.SOUTH);

		toolbar = new MainToolbar();
		add(toolbar, BorderLayout.NORTH);

		mainSplitPane = new MainSplitPane();
		add(mainSplitPane, BorderLayout.CENTER);

		mainFrameListener = new MainFrameListener();
		addWindowListener(mainFrameListener);
	}

	/**
	 * Returns the {@link MainMenuBar} object which contains all menu items.
	 * 
	 * @return {@link MainMenuBar}
	 */
	public MainMenuBar getMainMenuBar() {
		return mainMenuBar;
	}

	/**
	 * Sets the {@link MainMenuBar} object which contains all menu items..
	 * 
	 * @param mainMenuBar
	 */
	public void setMainMenuBar(MainMenuBar mainMenuBar) {
		this.mainMenuBar = mainMenuBar;
	}

	/**
	 * Returns the {@link MainToolbar} object containing most common actions of
	 * the application.
	 * 
	 * @return {@link MainToolbar}
	 */
	public MainToolbar getToolbar() {
		return toolbar;
	}

	/**
	 * Sets the {@link MainToolbar} object containing most common actions of the
	 * application.
	 * 
	 * @param toolbar
	 */
	public void setToolbar(MainToolbar toolbar) {
		this.toolbar = toolbar;
	}

	/**
	 * Returns the {@link StatusBar} object of the GUI.
	 * 
	 * @return {@link StatusBar}
	 */
	public StatusBar getStatusBar() {
		return statusBar;
	}

	/**
	 * Sets the {@link StatusBar} object of the GUI.
	 * 
	 * @param statusBar
	 */
	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}

	/**
	 * Returns the {@link MainSplitPane} which contains the {@link Tree} and
	 * {@link MainPanel} with {@link TabbedPane}.
	 * 
	 * @return {@link MainSplitPane}
	 */
	public MainSplitPane getMainSplitPane() {
		return mainSplitPane;
	}

	/**
	 * Sets the {@link MainSplitPane} which contains the {@link Tree} and
	 * {@link TabbedPane}.
	 * 
	 * @param mainSplitPane
	 */
	public void setMainSplitPane(MainSplitPane mainSplitPane) {
		this.mainSplitPane = mainSplitPane;
	}

	/**
	 * Sets the {@link MainFrameListener} for the {@link MainFrame} class.
	 * 
	 * @param mainFrameListener
	 */
	public void setMainFrameListener(MainFrameListener mainFrameListener) {
		this.mainFrameListener = mainFrameListener;
	}

	/**
	 * Returns the {@link MainFrameListener} of the {@link MainFrame} class.
	 * 
	 * @return {@link MainFrameListener}
	 */
	public MainFrameListener getMainFrameListener() {
		return mainFrameListener;
	}

	/**
	 * Sets the instance of the {@link MainFrame} class.
	 * 
	 * @param instance
	 */
	public static void setInstance(MainFrame instance) {
		MainFrame.instance = instance;
	}

	/**
	 * Calls the same method of the superclass and sets instance to
	 * <code>null</code>.
	 * 
	 * @see JFrame
	 * @see #setInstance(MainFrame)
	 */
	@Override
	public void dispose() {
		super.dispose();
		setInstance(null);
	}

}
