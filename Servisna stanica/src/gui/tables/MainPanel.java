package gui.tables;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import gui.MainSplitPane;
/**
 * Extension of the {@link JPanel} class.
 * This panel is in the right half of {@link MainSplitPane} and contains the {@link TabbedPane} with tables.
 * 
 * @author Ivan Divljak
 * @author Borko Arsovic
 * @author Milan Radeta
 * @author Isidora Skulec
 */
public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private TabbedPane tabbedPane;
	
	/**
	 * Constructor of {@link MainPanel} class.
	 * Creates a new {@link TabbedPane} which will hold tabs with tables.
	 */
	public MainPanel() {
		super(new BorderLayout());
		this.tabbedPane = new TabbedPane(true);
		add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * Returns the {@link TabbedPane} with tabs holding tables.
	 * @return {@link TabbedPane}
	 */
	public TabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Sets the {@link TabbedPane} with tabs holding tables.
	 * @param tabbedPane
	 */
	public void setTabbedPane(TabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
}
