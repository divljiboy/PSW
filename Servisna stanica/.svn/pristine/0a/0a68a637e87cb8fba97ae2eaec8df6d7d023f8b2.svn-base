package listeners.tab;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.tab.TabCloseButton;
import gui.tables.TabbedPane;

/**
 * Implementation of {@link MouseListener} interface.
 * Used to visually distinguish when a {@link TabCloseButton} is pressed.
 * 
 * @author Milan Radeta
 * @see TabCloseButtonActionListener
 * @see TabbedPane
 */
public class TabCloseButtonMouseListener implements MouseListener {
	
	private TabCloseButton tabButton;
	
	/**
	 * Constructor for {@link TabCloseButtonMouseListener}.
	 * Takes a reference to the {@link TabCloseButton} object whose pressing visuals will be drawn.
	 * @param button
	 */
	public TabCloseButtonMouseListener(TabCloseButton button) {
		tabButton = button;
	}
	
	/**
	 * Method handler for {@link MouseEvent} fired when mouse enters {@link TabCloseButton}.
	 * It paints the border of {@code TabCloseButton}.
	 * 
	 * 
	 */
    public void mouseEntered(MouseEvent e) {
        Component component = e.getComponent();
        if (component == tabButton) {
            tabButton.setBorderPainted(true);
        }
    }
    
    /**
	 * Method handler for {@link MouseEvent} fired when mouse leaves {@link TabCloseButton}.
	 * It erases the border of {@code TabCloseButton}.
	 * 
	 * 
	 */
    public void mouseExited(MouseEvent e) {
        Component component = e.getComponent();
        if (component == tabButton) {
            tabButton.setBorderPainted(false);
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
};
