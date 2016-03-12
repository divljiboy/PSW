/**
 * 
 */
package ra180_2012;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import gui.StatusBar;

/**
 * Test class for {@link StatusBar}.
 * 
 * @author Ivan Divljak
 * @see JUnit4
 * @see StatusBar
 *
 */
public class StatusBarTest {

	StatusBar statusBar;

	/**
	 * Set up method for {@link StatusBarTest}. Creates test instance of
	 * {@link StatusBar}.
	 * 
	 * @throws java.lang.Exception
	 * @see StatusBar
	 * @see JUnit4
	 */
	@Before
	public void setUp() throws Exception {
		statusBar = new StatusBar();
	}

	/**
	 * Test method for {@link gui.StatusBar#getMessage()}.
	 * 
	 * @see StatusBar
	 * @see StatusBar#getMessage()
	 * @see StatusBar#setMessage(String)
	 * @see String
	 */
	@Test
	public void testGetMessage() {
		String message = "Poruka";
		statusBar.setMessage(null);
		Assert.assertSame(null, statusBar.getMessage().getText());
		statusBar.setMessage(message);
		Assert.assertSame(message, statusBar.getMessage().getText());
	}

	/**
	 * Test method for {@link gui.StatusBar#setMessage(java.lang.String)}.
	 * 
	 * @see StatusBar
	 * @see StatusBar#getMessage()
	 * @see StatusBar#setMessage(String)
	 * @see String
	 */
	@Test
	public void testSetMessage() {
		String message = "Poruka";
		statusBar.setMessage(null);
		Assert.assertSame(null, statusBar.getMessage().getText());
		statusBar.setMessage(message);
		Assert.assertSame(message, statusBar.getMessage().getText());
	}

}
