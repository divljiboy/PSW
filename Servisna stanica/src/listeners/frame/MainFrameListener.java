package listeners.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import actions.manager.ActionsManager;
import gui.MainFrame;

/**
 * Listener used for {@link MainFrame} class.
 * Implements {@link WindowListener} interface and implements the {@code windowOpened} event handler method.
 * 
 * @author Milan Radeta
 */
public class MainFrameListener implements WindowListener {
	
	/**
	 * Method for handling {@link WindowEvent} fired on {@link MainFrame} opening.
	 * It is used to initialise which actions are first enabled when the application is started.
	 * 
	 * @see ActionsManager
	 */
	@Override
	public void windowOpened(WindowEvent arg0) {
		ActionsManager.updateTabActions();
		ActionsManager.updateTreeActions();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
