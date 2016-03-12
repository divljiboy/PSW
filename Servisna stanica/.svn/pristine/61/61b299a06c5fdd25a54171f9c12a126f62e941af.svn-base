package gui.toolbars;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

import application.Application;
import gui.tables.Table;
import listeners.QuickSearchListener;
import listeners.table.QuickSearchFocusListener;

/**
 * Extension of the {@link JTextField} class.
 * This field is used for filtering the {@link Table} to rows containing the text written in this field as it is being written.
 * 
 * @author Borko Arsović
 *
 */
public class QuickSearchTextField extends JTextField {
	private static final long serialVersionUID = 7435785802918773502L;
	private String placeholder;

	/**
	 * Constructor of the {@link QuickSearchTextField} class.
	 * Creates a field used for filtering the {@link Table} to rows containing the text written in this field as it is being written.
	 */
	public QuickSearchTextField() {
		setText("");
		setColumns(15);
		setPlaceholder(Application.getResourceBundle().getString("QuickSearch"));
		setToolTipText(Application.getResourceBundle().getString("QuickSearch"));
		setFont(new Font(getFont().getName(), getFont().getStyle(), 20));
		addFocusListener(new QuickSearchFocusListener());
		getDocument().addDocumentListener(new QuickSearchListener(this));
	}

	/**
	 * Overridden method used for painting the placeholder text.
	 */
	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);

		if (placeholder.length() == 0 || getText().length() > 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getDisabledTextColor());
		g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
	}

	/**
	 * Returns the default text present in the form while it is not being used.
	 * @return {@link String}
	 */
	public String getPlaceholder() {
		return placeholder;
	}

	/**
	 * Sets the default {@link String} text present in the form while it is not being used.
	 * @param s
	 */
	public void setPlaceholder(final String s) {
		placeholder = s;
	}
}
