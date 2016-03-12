package listeners.form;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

/**
 * Implementation of {@link FocusListener} designed for form fields.
 * When the focus is lost on a {@link JComponent} with mandatory value (any field which has this listener), 
 * the field will be marked.
 * 
 * @author Isidora Škulec
 *
 */
public class NullFocusListener implements FocusListener {
	
	Border defaultBorder;
	Border markedBorder;
	
	/**
	 * Creates a new {@link NullFocusListener}.
	 * Gets and keeps the default {@link Border} of the parent {@link JComponent}.
	 * Creates a marked border which will be set when the focus is lost and the field is empty.
	 * 
	 * @param parent
	 */
	public NullFocusListener(JComponent parent) {
		super();
		this.defaultBorder = parent.getBorder();
		this.markedBorder = BorderFactory.createLineBorder(Color.RED, 1);
	}

	/**
	 * When the field gains focus it will be unmarked.
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		JComponent source = (JComponent) arg0.getSource();
		source.setBorder(defaultBorder);
	}

	/**
	 * If the field loses focus and has no value specified it will be marked.
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		JComponent source = (JComponent) arg0.getSource();
		
		if(source instanceof JTextComponent) {
			source = (JTextComponent)source;
			String txt = ((JTextComponent) source).getText();
			if(txt == null || txt.trim().equals("")) {
				source.setBorder(markedBorder);
			}
		}
				
	}

}
