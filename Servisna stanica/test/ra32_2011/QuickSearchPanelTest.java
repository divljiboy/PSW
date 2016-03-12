package ra32_2011;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import gui.toolbars.QuickSearchPanel;
import gui.toolbars.QuickSearchTextField;

/**
 * Test class for {@link QuickSearchPanel}.
 * 
 * @author Borko Arsović
 * @see JUnit4
 *
 */

public class QuickSearchPanelTest {

	QuickSearchPanel quickSearchPanel;
	QuickSearchTextField quickSearchTextField;

	/**
	 * Set up method for {@link QuickSearchPanelTest}. Creates test instances of
	 * {@link QuickSearchPanel} and {@link QuickSearchTextField}.
	 * 
	 * @throws Exception
	 * @see JUnit4
	 */
	@Before
	public void setUp() throws Exception {
		quickSearchPanel = new QuickSearchPanel();
		quickSearchTextField = new QuickSearchTextField();
	}

	/**
	 * Test method for
	 * {@link gui.toolbars.QuickSearchPanel#getQuickSearchTextField()}.
	 * 
	 * @see QuickSearchPanel
	 * @see QuickSearchPanel#setQuickSearchTextField(QuickSearchTextField)
	 * @see QuickSearchPanel#getQuickSearchTextField()
	 * @see QuickSearchTextField
	 */
	@Test
	public void testGetQuickSearchTextField() {
		quickSearchPanel.setQuickSearchTextField(null);
		assertEquals(null, quickSearchPanel.getQuickSearchTextField());
		quickSearchPanel.setQuickSearchTextField(quickSearchTextField);
		assertEquals(quickSearchTextField, quickSearchPanel.getQuickSearchTextField());
	}

	/**
	 * Test method for
	 * {@link gui.toolbars.QuickSearchPanel#setQuickSearchTextField(QuickSearchTextField)}
	 * .
	 * 
	 * @see QuickSearchPanel
	 * @see QuickSearchPanel#setQuickSearchTextField(QuickSearchTextField)
	 * @see QuickSearchPanel#getQuickSearchTextField()
	 * @see QuickSearchTextField
	 */
	@Test
	public void testSetQuickSearchTextField() {
		quickSearchPanel.setQuickSearchTextField(null);
		assertEquals(null, quickSearchPanel.getQuickSearchTextField());
		quickSearchPanel.setQuickSearchTextField(quickSearchTextField);
		assertEquals(quickSearchTextField, quickSearchPanel.getQuickSearchTextField());
	}

}
