package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JComboBox;

import actions.manager.ActionsManager;
import gui.dialog.StartDialog;

/**
 * Listener used for the {@link JComboBox} used in {@link StartDialog}. Implements {@link ActionListener}.
 * It is used to change {@link Locale} and choose the language of the software.
 * Locales (languages) it currently supports are {@code en_US} (English, United States), {@code sr_RS_latin} (Serbian, Latin) and {@code sr_RS_cyrilic} (Serbian, Cyrilic).
 * 
 * 
 * @author Milan Radeta
 */
public class ComboBoxLanguageListener implements ActionListener {

	private StartDialog startDialog;
	
	/**
	 * Default constructor for {@link ComboBoxLanguageListener} class.
	 * 
	 */
	public ComboBoxLanguageListener() {}
	
	/**
	 * Constructor for {@link ComboBoxLanguageListener} class. Parameter {@code startDialog} takes a reference to a {@link StartDialog} class object, which is used to determine the language of the software.
	 * @param startDialog
	 *   
	 */
	public ComboBoxLanguageListener(StartDialog startDialog) {
		this.startDialog = startDialog;
	}
	
	/**
	 * Method which handles {@link ActionEvent} fired from the source {@link JComboBox}.
	 * For the language chosen in the {@code JComboBox} it changes the {@link Locale} of the software.
	 * Locales (languages) it currently supports are {@code en_US} (English, United States), {@code sr_RS_#Latn} (Serbian, Latin) and {@code sr_RS} (Serbian, Cyrilic).
	 *  
	 *  @see ComboBoxLanguageListener
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		@SuppressWarnings("unchecked")
		int indexLanguage = ((JComboBox<String>) arg0.getSource()).getSelectedIndex();
		Locale locale = null;
        switch (indexLanguage) {
			case 0:
				locale = new Locale("en", "US");
				break;
			case 1:
				locale = new Locale.Builder().setLanguage("sr").setRegion("RS").setScript("Latn").build();
				break;
			case 2:
				locale = new Locale("sr", "RS");
		}
        if (locale != null) {
			startDialog.updateLanguage(locale);
			ActionsManager.updateActionLanguage(locale);
        }
	}
	
	/**
	 * Getter for the {@link StartDialog} containing the {@link JComboBox}.
	 * 
	 * @see ComboBoxLanguageListener
	 * @return {@link StartDialog}
	 */
	public StartDialog getStartDialog() {
		return startDialog;
	}
	
	/**
	 * Sets the new {@link StartDialog} which must contain {@link JComboBox} with following
	 * locales (languages): {@code en_US} (English, United States), {@code sr_RS_latin} (Serbian, Latin) and {@code sr_RS_cyrilic} (Serbian, Cyrilic).
	 * @param startDialog
	 * 
	 * @see Locale
	 * @see ComboBoxLanguageListener
	 * 
	 */
	public void setStartDialog(StartDialog startDialog) {
		this.startDialog = startDialog;
	}

}
