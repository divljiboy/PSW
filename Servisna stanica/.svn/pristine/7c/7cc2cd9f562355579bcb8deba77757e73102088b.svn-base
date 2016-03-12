package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import actions.manager.ActionsManager;
import gui.MainFrame;
import gui.MainMenuBar;
import gui.dialog.StartDialog;
import gui.tab.ButtonTabComponent;
import gui.tables.TabbedPane;
import gui.tables.TablesPane;
import gui.toolbars.QuickSearchTextField;
import gui.tree.Tree;
import model.TableModel;
import model.TableSchema;
import model.TreeNode;

/**
 * Main class of the application. Contains fields and methods relevant for the
 * whole application and starts it.
 * 
 * @author Borko Arsović
 * @author Milan Radeta
 * @author Isidora Škulec
 *
 */
public class Application {
	private static Locale locale = new Locale("en", "US");
	private static ResourceBundle resourceBundle = getNewBundle();
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();

	/**
	 * Main method of the application. Sets the look and feel of the GUI and
	 * prompts the user to login and opens the {@link StartDialog}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		StartDialog startDialog = new StartDialog();
		startDialog.setVisible(true);
	}

	/**
	 * Gets {@link ResourceBundle} according to value of {@code locale}. Method is customized
	 * for Serbian (Latin) and Serbian (Cyrilic).
	 * @see Locale
	 * @see ResourceBundle
	 * @see Control
	 * @see Control#getCandidateLocales(String, Locale)
	 * @return {@link ResourceBundle}
	 */
	public static ResourceBundle getNewBundle() {
		return ResourceBundle.getBundle("gui.MessageResources.MessageResources", locale, new Control() {
			@Override
			public List<Locale> getCandidateLocales(String baseName, Locale locale) {
				if (baseName == null)
					throw new NullPointerException();
				if (locale.getLanguage().equals("sr") && locale.getCountry().equals("RS")) {
					if (locale.getScript().equals("Latn"))
						return Arrays.asList(new Locale("sr", "RS", "latin"));
					else
						return Arrays.asList(new Locale("sr", "RS", "cyrilic"));
				}
				return super.getCandidateLocales(baseName, locale);
			}

		});
	}

	/**
	 * Changes the language of all GUI elements given the new {@link Locale}.
	 * 
	 * @param locale
	 */
	public static void updateLanguage(Locale locale) {
		setLocale(locale);
		resourceBundle = getNewBundle();
		ActionsManager.updateActionLanguage(locale);

		// menu
		MainMenuBar mainMenuBar = MainFrame.getInstance().getMainMenuBar();
		mainMenuBar.getMenuEdit().setText(resourceBundle.getString("Edit"));
		mainMenuBar.getMenuFile().setText(resourceBundle.getString("File"));
		mainMenuBar.getMenuHelp().setText(resourceBundle.getString("Help"));
		mainMenuBar.getMenuLanguages().setText(resourceBundle.getString("Language"));
		mainMenuBar.getMenuWindow().setText(resourceBundle.getString("Window"));

		// status bar
		MainFrame.getInstance().getStatusBar().setMessage(resourceBundle.getString("Welcome"));

		// Column
		for (TableSchema schema : TableSchema.getTableSchemas().values()) {
			schema.updateColumnNames();
		}

		// Tab
		for (int i = 0; i < TabbedPane.getMainTabbedPane().getTabCount(); i++) {
			ButtonTabComponent tabComponent = (ButtonTabComponent) TabbedPane.getMainTabbedPane().getTabComponentAt(i);
			TreeNode node = TabbedPane.getMainTabbedPane().getOpenedNodes().get(i);
			tabComponent.getLabel()
					.setText(resourceBundle.getString("Table_" + node.getNode().get("code").asText().toUpperCase()));
			TablesPane tablesPane = (TablesPane) TabbedPane.getMainTabbedPane().getComponentAt(i);
			QuickSearchTextField quickSearchTextField = tablesPane.getTablePanel().getToolbar().getQuickSearchPanel()
					.getQuickSearchTextField();
			quickSearchTextField.setPlaceholder(Application.getResourceBundle().getString("QuickSearch"));
			quickSearchTextField.setToolTipText(Application.getResourceBundle().getString("QuickSearch"));
			if (tablesPane.getTabbedPane() != null) {
				TabbedPane childTabbedPane = tablesPane.getTabbedPane();
				for (int j = 0; j < childTabbedPane.getTabCount(); j++) {
					TablesPane childTablesPane = (TablesPane) childTabbedPane.getComponentAt(j);
					quickSearchTextField = childTablesPane.getTablePanel().getToolbar().getQuickSearchPanel()
							.getQuickSearchTextField();
					quickSearchTextField.setPlaceholder(Application.getResourceBundle().getString("QuickSearch"));
					quickSearchTextField.setToolTipText(Application.getResourceBundle().getString("QuickSearch"));
					node = childTabbedPane.getOpenedNodes().get(j);
					childTabbedPane.setTabComponentAt(j, new JLabel(
							resourceBundle.getString("Table_" + node.getNode().get("code").asText().toUpperCase())));
				}
			}
		}

		// MainFrame
		MainFrame.getInstance().setTitle(resourceBundle.getString("ServiceStation"));

		// Table Models
		for (TableModel tableModel : TableModel.getOpenedTableModels()) {
			int row = tableModel.getTable().getSelectedRow();
			int childRows[] = new int[tableModel.getChildTableModels().size()];
			int index = 0;
			for (TableModel childTableModel : tableModel.getChildTableModels()) {
				childRows[index++] = childTableModel.getTable().getSelectedRow();
			}
			tableModel.fireTableStructureChanged();
			if (row >= 0) {
				tableModel.getTable().setRowSelectionInterval(row, row);
			}
			index = 0;
			for (TableModel childTableModel : tableModel.getChildTableModels()) {
				childTableModel.fireTableStructureChanged();
				if (childRows[index] >= 0) {
					childTableModel.getTable().setRowSelectionInterval(childRows[index], childRows[index]);
				}
				index++;
			}
		}

		// Tree
		Tree.getInstance().updateUI();

		UIManager.put("OptionPane.yesButtonText", resourceBundle.getString("ButtonYes"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getString("ButtonNo"));
		UIManager.put("OptionPane.titleText", resourceBundle.getString("Message"));
	}

	/**
	 * Given the {@link String} with the key of the error message, it creates a
	 * popup dialog informing the user of an occurred error. Parameter
	 * messageKey is used to reference a localised error message.
	 * 
	 * @param messageKey
	 * @see #showErrorMessage(String, Object[])
	 * @see #showSqlExceptionError(SQLException)
	 */
	public static void showErrorMessage(String messageKey) {
		JOptionPane.showMessageDialog(MainFrame.getInstance(), resourceBundle.getString(messageKey),
				resourceBundle.getString(messageKey + "Title"), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Given the {@link String} with the key of the error message, it creates a
	 * popup dialog informing the user of an occurred error. Parameter
	 * {@code messageKey} is used to reference a localised error message.
	 * Parameter {@code args} (an array of {@link Object}{@code s}) is used if
	 * there are objects affected by the error.
	 * 
	 * @param messageKey
	 * @param args
	 * @see #showErrorMessage(String)
	 * @see #showSqlExceptionError(SQLException)
	 */
	public static void showErrorMessage(String messageKey, Object[] args) {
		MessageFormat formatter = new MessageFormat("");
		formatter.applyPattern(resourceBundle.getString(messageKey));
		JOptionPane.showMessageDialog(MainFrame.getInstance(), formatter.format(args),
				resourceBundle.getString(messageKey + "Title"), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * A special error message method specifically for SQL errors. Parameter
	 * {@code e} is a {@link SQLException} object.
	 * 
	 * @param e
	 * @see #showErrorMessage(String)
	 * @see #showErrorMessage(String, Object[])
	 */
	public static void showSqlExceptionError(SQLException e) {
		JPanel panel = new JPanel(new BorderLayout());

		JLabel label = new JLabel();
		switch (e.getErrorCode()) {
		case 0:
			label.setText(resourceBundle.getString("SQLConnectionError"));
			break;
		case 547:
			label.setText(resourceBundle.getString("SQLReferenceError"));
			break;
		case 2627:
			label.setText(resourceBundle.getString("SQLDuplicatePrimaryKeyError"));
			break;
		default:
			label.setText(resourceBundle.getString("SQLGeneralError"));
		}
		panel.add(label, BorderLayout.NORTH);
		JTextPane jtp = new JTextPane();
		Document doc = jtp.getDocument();
		MessageFormat formatter = new MessageFormat("");
		formatter.applyPattern(resourceBundle.getString("SQLErrorDescription"));
		Object[] args = { e.getErrorCode(), e.getMessage() };
		try {
			doc.insertString(doc.getLength(), formatter.format(args), new SimpleAttributeSet());
		} catch (BadLocationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		jtp.setSize(new Dimension(480, 10));
		jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
		jtp.setEditable(false);
		panel.add(jtp, BorderLayout.SOUTH);
		JOptionPane.showMessageDialog(MainFrame.getInstance(), panel, resourceBundle.getString("SQLErrorTitle"),
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Returns the {@link ResourceBundle} object of this application.
	 * 
	 * @return {@link ResourceBundle}
	 */
	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/**
	 * Sets the {@link ResourceBundle} object of this application.
	 * 
	 * @param resourceBundle
	 */
	public static void setResourceBundle(ResourceBundle resourceBundle) {
		Application.resourceBundle = resourceBundle;
	}

	/**
	 * Returns the {@link Toolkit} used by this application.
	 * 
	 * @return {@link Toolkit}
	 */
	public static Toolkit getToolkit() {
		return toolkit;
	}

	/**
	 * Sets the {@link Toolkit} used by this application.
	 * 
	 * @param toolkit
	 */
	public static void setToolkit(Toolkit toolkit) {
		Application.toolkit = toolkit;
	}

	/**
	 * Returns the {@link Locale} used by this application.
	 * 
	 * @return {@link Locale}
	 */
	public static Locale getLocale() {
		return locale;
	}

	/**
	 * Sets the {@link Locale} used by this application.
	 * 
	 * @param locale
	 */
	public static void setLocale(Locale locale) {
		Application.locale = locale;
	}
	
}
