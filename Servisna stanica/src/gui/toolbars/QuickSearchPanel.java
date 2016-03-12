package gui.toolbars;

import java.awt.GridLayout;

import javax.swing.JPanel;

import gui.tables.Table;

/**
 * Extension of {@link JPanel} which contains {@link QuickSearchTextField}, used
 * for quick filtering {@link Table}.
 * 
 * @see JPanel
 * @see QuickSearchTextField
 * @see Table
 * 
 * @author Borko Arsović
 *
 */
public class QuickSearchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private QuickSearchTextField quickSearchTextField;
	/**
	 * Creates {@link JPanel} extension with {@link GridLayout} and {@link QuickSearchTextField}.
	 * 
	 * @see JPanel
	 * @see GridLayout
	 * @see QuickSearchTextField
	 */
	public QuickSearchPanel() {
		super(new GridLayout(0, 2));
		quickSearchTextField = new QuickSearchTextField();
		add(quickSearchTextField);
	}
	/**
	 * Returns {@link QuickSearchPanel}'s {@link QuickSearchTextField}.
	 * @return {@link QuickSearchTextField}
	 */
	public QuickSearchTextField getQuickSearchTextField() {
		return quickSearchTextField;
	}
	/**
	 * Sets {@link QuickSearchPanel}'s {@link QuickSearchTextField}.
	 * @param quickSearchTextField
	 */
	public void setQuickSearchTextField(QuickSearchTextField quickSearchTextField) {
		this.quickSearchTextField = quickSearchTextField;
		
	}

}
