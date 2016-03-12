package gui.dialog;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import actions.exit.ActionExit;
import actions.manager.ActionsManager;
import actions.start.ActionStartApplication;
import application.Application;
import listeners.ComboBoxLanguageListener;

/**
 * Extension of the {@link JDialog} class. Represents the starting dialog of the
 * application. Contains the options for choosing the language of the
 * application and the connection type based on user's location.
 * 
 * @author Milan Radeta
 * 
 */
public class StartDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private static String[] addresses = { "UNS", "Other" };
	private static String[] languages = { "EnglishUS", "SerbianLatin", "SerbianCyrilic" };

	private JPanel panel;

	private JLabel labelLocation;
	private JLabel labelLanguage;
	private JLabel labelStatus;

	private JComboBox<String> comboBoxIP;
	private JComboBox<String> comboBoxLanguage;

	private JButton buttonStart;
	private JButton buttonExit;

	/**
	 * Constructor for {@link StartDialog} class. Creates a small dialog with
	 * the options for choosing the language of the application and the
	 * connection type based on user's location.
	 */
	public StartDialog() {
		ActionStartApplication.getInstance().setStartDialog(this);
		labelLocation = new JLabel(Application.getResourceBundle().getString("StartLocation"));
		labelLanguage = new JLabel(Application.getResourceBundle().getString("StartLanguage"));
		labelStatus = new JLabel("");
		labelStatus.setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxIP = new JComboBox<String>();
		for (String address : addresses) {
			comboBoxIP.addItem(Application.getResourceBundle().getString(address));
		}
		comboBoxLanguage = new JComboBox<String>();
		for (String language : languages) {
			comboBoxLanguage.addItem(Application.getResourceBundle().getString(language));
		}
		comboBoxLanguage.addActionListener(new ComboBoxLanguageListener(this));
		buttonStart = new JButton(ActionStartApplication.getInstance());
		buttonExit = new JButton(ActionExit.getInstance());

		setTitle(Application.getResourceBundle().getString("ConnectionAndLanguageSettings"));

		panel = new JPanel(new GridBagLayout());
		add(panel);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.ipadx = 10;
		constraints.ipady = 10;
		constraints.insets = new Insets(5, 5, 5, 5);

		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(labelLocation, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(labelLanguage, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		panel.add(comboBoxIP, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		panel.add(comboBoxLanguage, constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(buttonStart, constraints);
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(buttonExit, constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		panel.add(labelStatus, constraints);

		setIconImage(new ImageIcon(Application.class.getResource("/icons/logo.png")).getImage());
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(300, 150));
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * This method updates the GUI components of {@link StartDialog} to the
	 * given {@link Locale} language.
	 * 
	 * @param locale
	 */
	public void updateLanguage(Locale locale) {
		Application.setLocale(locale);
		Application.setResourceBundle(Application.getNewBundle());
		ActionsManager.updateActionLanguage(locale);

		labelLocation.setText(Application.getResourceBundle().getString("StartLocation"));
		labelLanguage.setText(Application.getResourceBundle().getString("StartLanguage"));
		if (labelStatus.getText().length() > 0) {
			labelStatus.setText(Application.getResourceBundle().getString("InvalidConnection"));
		}
		int selectedIndex = comboBoxIP.getSelectedIndex();
		comboBoxIP.removeAllItems();
		for (String address : addresses) {
			comboBoxIP.addItem(Application.getResourceBundle().getString(address));
		}
		comboBoxIP.setSelectedIndex(selectedIndex);

		selectedIndex = comboBoxLanguage.getSelectedIndex();
		comboBoxLanguage.removeAllItems();
		for (String language : languages) {
			comboBoxLanguage.addItem(Application.getResourceBundle().getString(language));
		}
		comboBoxLanguage.setSelectedIndex(selectedIndex);

		setTitle(Application.getResourceBundle().getString("ConnectionAndLanguageSettings"));
		UIManager.put("OptionPane.yesButtonText", Application.getResourceBundle().getString("ButtonYes"));
		UIManager.put("OptionPane.noButtonText", Application.getResourceBundle().getString("ButtonNo"));
		UIManager.put("OptionPane.titleText", Application.getResourceBundle().getString("Message"));
	}

	/**
	 * Returns an array containing all possible user locations.
	 * 
	 * @return {@link String}[]
	 */
	public static String[] getAddresses() {
		return addresses;
	}

	/**
	 * Sets an array of {@link String} objects containing all possible user
	 * locations.
	 * 
	 * @param addresses
	 */
	public static void setAddresses(String[] addresses) {
		StartDialog.addresses = addresses;
	}

	/**
	 * Returns an array containing all possible application languages.
	 * 
	 * @return {@link String}[]
	 */
	public static String[] getLanguages() {
		return languages;
	}

	/**
	 * Sets an array of {@link String} objects containing all possible
	 * application languages.
	 * 
	 * @param languages
	 */
	public static void setLanguages(String[] languages) {
		StartDialog.languages = languages;
	}

	/**
	 * Returns a {@link JPanel} containing all the {@link JComponent} objects of
	 * this dialog.
	 * 
	 * @return {@link JPanel}
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Sets a {@link JPanel} containing all the {@link JComponent} objects of
	 * this dialog.
	 * 
	 * @param panel
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * Returns the {@link JLabel} of the location combo box.
	 * 
	 * @return {@link JLabel}
	 */
	public JLabel getLabelLocation() {
		return labelLocation;
	}

	/**
	 * Sets the {@link JLabel} of the location combo box.
	 * 
	 * @param labelLocation
	 */
	public void setLabelLocation(JLabel labelLocation) {
		this.labelLocation = labelLocation;
	}

	/**
	 * Returns the {@link JLabel} of the language combo box.
	 * 
	 * @return {@link JLabel}
	 */
	public JLabel getLabelLanguage() {
		return labelLanguage;
	}

	/**
	 * Sets the {@link JLabel} of the language combo box.
	 * 
	 * @param labelLanguage
	 */
	public void setLabelLanguage(JLabel labelLanguage) {
		this.labelLanguage = labelLanguage;
	}

	/**
	 * Returns the {@link JLabel} of the status.
	 * 
	 * @return {@link JLabel}
	 */
	public JLabel getLabelStatus() {
		return labelStatus;
	}

	/**
	 * Sets the {@link JLabel} of the status.
	 * 
	 * @param labelStatus
	 */
	public void setLabelStatus(JLabel labelStatus) {
		this.labelStatus = labelStatus;
	}

	/**
	 * Returns the {@link JComboBox} of possible user locations.
	 * 
	 * @return {@link JComboBox}
	 */
	public JComboBox<String> getComboBoxIP() {
		return comboBoxIP;
	}

	/**
	 * Sets the {@link JComboBox} of possible user locations.
	 * 
	 * @param comboBoxIP
	 */
	public void setComboBoxIP(JComboBox<String> comboBoxIP) {
		this.comboBoxIP = comboBoxIP;
	}

	/**
	 * Returns the {@link JComboBox} of possible languages.
	 * 
	 * @return {@link JComboBox}
	 */
	public JComboBox<String> getComboBoxLanguage() {
		return comboBoxLanguage;
	}

	/**
	 * Sets the {@link JComboBox} of possible languages.
	 * 
	 * @param comboBoxLanguage
	 */
	public void setComboBoxLanguage(JComboBox<String> comboBoxLanguage) {
		this.comboBoxLanguage = comboBoxLanguage;
	}

	/**
	 * Returns the {@link JButton} for starting the application.
	 * 
	 * @return {@link JButton}
	 */
	public JButton getButtonStart() {
		return buttonStart;
	}

	/**
	 * Sets the {@link JButton} for starting the application.
	 * 
	 * @param buttonStart
	 */
	public void setButtonStart(JButton buttonStart) {
		this.buttonStart = buttonStart;
	}

	/**
	 * Returns the {@link JButton} for exiting the application.
	 * 
	 * @return {@link JButton}
	 */
	public JButton getButtonExit() {
		return buttonExit;
	}

	/**
	 * Sets the {@link JButton} for exiting the application.
	 * 
	 * @param buttonExit
	 */
	public void setButtonExit(JButton buttonExit) {
		this.buttonExit = buttonExit;
	}

}
