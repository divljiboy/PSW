package gui.tables.forms;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 * Extension of {@link PlainDocument} which prevents the user to enter to more
 * than maximum number of digits/characters in {@link JTextComponent}.
 * 
 * @author Milan Radeta
 *
 */
public class JTextFieldLimit extends PlainDocument {
	private static final long serialVersionUID = 1L;
	private int limit;

	/**
	 * Constructor of {@link JTextFieldLimit} class.
	 * Parameter {@code limit} takes an integer which determines the maximum number of characters in the field.
	 * 
	 * @param limit
	 */
	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}
