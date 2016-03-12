package actions.manager;

import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.tree.TreePath;

import com.fasterxml.jackson.databind.JsonNode;

import actions.cancel.ActionCancel;
import actions.exit.ActionExit;
import actions.help.ActionAbout;
import actions.help.ActionUserManual;
import actions.languages.ActionEnglishUS;
import actions.languages.ActionSerbianCyrilic;
import actions.languages.ActionSerbianLatin;
import actions.login.ActionLogin;
import actions.logout.ActionLogout;
import actions.records.ActionAddRecord;
import actions.records.ActionAdvancedSearch;
import actions.records.ActionConfirmAddRecord;
import actions.records.ActionConfirmModifyRecord;
import actions.records.ActionConfirmSearch;
import actions.records.ActionFirstRecord;
import actions.records.ActionLastRecord;
import actions.records.ActionModifyRecord;
import actions.records.ActionNextRecord;
import actions.records.ActionPreviousRecord;
import actions.records.ActionRefresh;
import actions.records.ActionRemoveRecord;
import actions.signup.ActionCreateAccount;
import actions.signup.ActionSignUp;
import actions.start.ActionStartApplication;
import actions.subtabs.ActionFirstSubTab;
import actions.subtabs.ActionLastSubTab;
import actions.subtabs.ActionNextSubTab;
import actions.subtabs.ActionPrevSubTab;
import actions.tabs.ActionCloseAllTabs;
import actions.tabs.ActionCloseTab;
import actions.tabs.ActionFirstTab;
import actions.tabs.ActionLastTab;
import actions.tabs.ActionNextTab;
import actions.tabs.ActionPrevTab;
import actions.tree.ActionCollapseAll;
import actions.tree.ActionCollapseNode;
import actions.tree.ActionExpandAll;
import actions.tree.ActionExpandNode;
import actions.tree.ActionOpenInTab;
import application.Application;
import gui.MainFrame;
import gui.tables.TabbedPane;
import gui.tables.Table;
import gui.tables.TablesPane;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Class that has static functions for updating {@link Application}'s
 * {@link AbstractAction}s, that is their states, names and descriptions.
 * 
 * @author Milan Radeta
 * @author Borko Arsović
 * @author Ivan Divljak
 * @see AbstractAction
 * @see Application
 *
 */
public class ActionsManager {
	/**
	 * Updates the states {@link AbstractAction}s that affect {@link MainFrame}'s
	 * {@link Tree} or are used via it.
	 * 
	 * @see MainFrame
	 * @see Tree
	 * @see AbstractAction
	 * @see ActionExpandNode
	 * @see ActionCollapseNode
	 * @see ActionOpenInTab
	 * 
	 */
	public static void updateTreeActions() {
		Tree tree = Tree.getInstance();
		TreePath selPath = tree.getSelectionPath();
		if (selPath != null) {
			TreeNode selectedNode = (TreeNode) selPath.getLastPathComponent();
			if (selectedNode.getChildCount() > 0) {
				ActionExpandNode.getInstance().setEnabled(true);
				ActionCollapseNode.getInstance().setEnabled(true);
			} else {
				ActionExpandNode.getInstance().setEnabled(false);
				ActionCollapseNode.getInstance().setEnabled(false);
			}
			JsonNode jsonNode = selectedNode.getNode().get("type");
			if (jsonNode != null && jsonNode.asText().equals("table")
					&& !TabbedPane.getMainTabbedPane().getOpenedNodes().contains(selectedNode)) {
				ActionOpenInTab.getInstance().setEnabled(true);
			} else {
				ActionOpenInTab.getInstance().setEnabled(false);
			}

			if (selectedNode.isLeaf()) {
				ActionExpandNode.getInstance().setEnabled(false);
				ActionCollapseNode.getInstance().setEnabled(false);
			} else {
				ActionExpandNode.getInstance().setEnabled(true);
				ActionCollapseNode.getInstance().setEnabled(true);
			}
		} else {
			ActionOpenInTab.getInstance().setEnabled(false);
			ActionExpandNode.getInstance().setEnabled(false);
			ActionCollapseNode.getInstance().setEnabled(false);
		}

	}

	/**
	 * Updates the states of {@link AbstractAction}s that affect {@link MainFrame}'s
	 * {@link TabbedPane}.
	 * 
	 * @see MainFrame
	 * @see TabbedPane
	 * @see AbstractAction
	 * @see ActionCloseTab
	 * @see ActionCloseAllTabs
	 * @see ActionFirstTab
	 * @see ActionNextTab
	 * @see ActionPrevTab
	 * @see ActionLastTab
	 * @see ActionFirstSubTab
	 * @see ActionNextSubTab
	 * @see ActionPrevSubTab
	 * @see ActionLastSubTab
	 * 
	 */
	public static void updateTabActions() {
		TabbedPane tabbedPane = TabbedPane.getMainTabbedPane();

		int tabCount = tabbedPane.getTabCount();

		if (tabCount == 0) {
			ActionCloseTab.getInstance().setEnabled(false);
			ActionCloseAllTabs.getInstance().setEnabled(false);
			ActionFirstTab.getInstance().setEnabled(false);
			ActionNextTab.getInstance().setEnabled(false);
			ActionPrevTab.getInstance().setEnabled(false);
			ActionLastTab.getInstance().setEnabled(false);
			ActionFirstSubTab.getInstance().setEnabled(false);
			ActionLastSubTab.getInstance().setEnabled(false);
			ActionNextSubTab.getInstance().setEnabled(false);
			ActionPrevSubTab.getInstance().setEnabled(false);

		} else {
			ActionCloseTab.getInstance().setEnabled(true);
			ActionCloseAllTabs.getInstance().setEnabled(true);

			int selectedIndex = tabbedPane.getSelectedIndex();

			if (selectedIndex > 0) {
				ActionFirstTab.getInstance().setEnabled(true);
				ActionPrevTab.getInstance().setEnabled(true);
			} else {
				ActionFirstTab.getInstance().setEnabled(false);
				ActionPrevTab.getInstance().setEnabled(false);
			}

			if (selectedIndex < tabCount - 1) {
				ActionLastTab.getInstance().setEnabled(true);
				ActionNextTab.getInstance().setEnabled(true);
			} else {
				ActionLastTab.getInstance().setEnabled(false);
				ActionNextTab.getInstance().setEnabled(false);
			}

			int childCount = selectedIndex >= 0 ? tabbedPane.getOpenedNodes().get(selectedIndex).getChildCount() : 0;

			if (childCount <= 1) {
				ActionFirstSubTab.getInstance().setEnabled(false);
				ActionLastSubTab.getInstance().setEnabled(false);
				ActionNextSubTab.getInstance().setEnabled(false);
				ActionPrevSubTab.getInstance().setEnabled(false);

			} else {
				TablesPane tablesPane = (TablesPane) tabbedPane.getSelectedComponent();
				TabbedPane childTabbedPane = (TabbedPane) tablesPane.getTabbedPane();
				if (childTabbedPane != null) {
					int selectedSubIndex = childTabbedPane.getSelectedIndex();

					if (selectedSubIndex > 0) {
						ActionFirstSubTab.getInstance().setEnabled(true);
						ActionPrevSubTab.getInstance().setEnabled(true);
					} else {
						ActionFirstSubTab.getInstance().setEnabled(false);
						ActionPrevSubTab.getInstance().setEnabled(false);
					}
					if (selectedSubIndex < childCount - 1) {
						ActionLastSubTab.getInstance().setEnabled(true);
						ActionNextSubTab.getInstance().setEnabled(true);
					} else {
						ActionLastSubTab.getInstance().setEnabled(false);
						ActionNextSubTab.getInstance().setEnabled(false);
					}
				}
			}
		}
	}

	/**
	 * Updates the states of {@link AbstractAction}s that manipulate records.
	 * 
	 * @see AbstractAction
	 * @see ActionAddRecord
	 * @see ActionModifyRecord
	 * @see ActionRemoveRecord
	 * @see ActionRefresh
	 * @see ActionFirstRecord
	 * @see ActionNextRecord
	 * @see ActionPreviousRecord
	 * @see ActionLastRecord
	 * @see ActionAdvancedSearch
	 * 
	 */
	public static void updateRecordActions() {
		if (Table.getFocusedTable() != null) {
			ActionAddRecord.getInstance().setEnabled(true);
			ActionModifyRecord.getInstance().setEnabled(true);
			ActionRemoveRecord.getInstance().setEnabled(true);
			ActionRefresh.getInstance().setEnabled(true);
			ActionFirstRecord.getInstance().setEnabled(true);
			ActionNextRecord.getInstance().setEnabled(true);
			ActionPreviousRecord.getInstance().setEnabled(true);
			ActionLastRecord.getInstance().setEnabled(true);
			ActionAdvancedSearch.getInstance().setEnabled(true);
		} else {
			ActionAddRecord.getInstance().setEnabled(false);
			ActionModifyRecord.getInstance().setEnabled(false);
			ActionRemoveRecord.getInstance().setEnabled(false);
			ActionRefresh.getInstance().setEnabled(false);
			ActionFirstRecord.getInstance().setEnabled(false);
			ActionNextRecord.getInstance().setEnabled(false);
			ActionPreviousRecord.getInstance().setEnabled(false);
			ActionLastRecord.getInstance().setEnabled(false);
			ActionAdvancedSearch.getInstance().setEnabled(false);
		}

	}

	/**
	 * Updates the names and descriptions of all {@link AbstractAction}s used by {@link Application}s
	 * according to the set {@link Locale}.
	 * 
	 * @see AbstractAction
	 * @see Application
	 * @see Locale
	 * 
	 */
	public static void updateActionLanguage(Locale locale) {

		// actions
		ActionCancel.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("Cancel"));
		ActionCancel.getInstance().putValue(Action.SHORT_DESCRIPTION, Application.getResourceBundle().getString("CancelDesc"));
		ActionExit.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("Exit"));
		ActionExit.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("ExitDesc"));

		// actions.help
		ActionAbout.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("About"));
		ActionAbout.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("AboutDesc"));
		ActionUserManual.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("UserManual"));
		ActionUserManual.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("UserManualDesc"));

		// actions.languages
		ActionEnglishUS.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("EnglishUS"));
		ActionEnglishUS.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("EnglishUSDesc"));
		ActionSerbianLatin.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("SerbianLatin"));
		ActionSerbianLatin.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("SerbianLatinDesc"));
		ActionSerbianCyrilic.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("SerbianCyrilic"));
		ActionSerbianCyrilic.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("SerbianCyrilicDesc"));

		// actions.login
		ActionLogin.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("Login"));
		ActionLogin.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("LoginDesc"));

		// actions.logout
		ActionLogout.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("Logout"));
		ActionLogout.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("LogoutDesc"));

		// actions.records
		ActionAddRecord.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("AddRecord"));
		ActionAddRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("AddRecordDesc"));

		ActionAdvancedSearch.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("Search"));
		ActionAdvancedSearch.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("SearchDesc"));

		ActionConfirmAddRecord.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("ButtonConfirm"));
		ActionConfirmAddRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("ButtonConfirmDesc"));
		ActionConfirmModifyRecord.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("ButtonConfirm"));
		ActionConfirmModifyRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("ButtonConfirmDesc"));
		ActionConfirmSearch.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("ButtonConfirm"));
		ActionConfirmSearch.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("ButtonConfirmDesc"));

		ActionFirstRecord.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("FirstRecord"));
		ActionFirstRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("FirstRecordDesc"));
		ActionLastRecord.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("LastRecord"));
		ActionLastRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("LastRecordDesc"));

		ActionModifyRecord.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("ModifyRecord"));
		ActionModifyRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("ModifyRecordDesc"));

		ActionNextRecord.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("NextRecord"));
		ActionNextRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("NextRecordDesc"));
		ActionPreviousRecord.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("PreviousRecord"));
		ActionPreviousRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("PreviousRecordDesc"));

		ActionRefresh.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("RefreshTable"));
		ActionRefresh.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("RefreshTableDesc"));

		ActionRemoveRecord.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("RemoveRecord"));
		ActionRemoveRecord.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("RemoveRecordDesc"));

		// actions.signup
		ActionCreateAccount.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("CreateAccount"));
		ActionCreateAccount.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("CreateAccountDesc"));
		ActionSignUp.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("SignUp"));
		ActionSignUp.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("SignUpDesc"));

		// actions.start
		ActionStartApplication.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("StartApp"));
		ActionStartApplication.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("StartAppDesc"));

		// actions.subtabs
		ActionFirstSubTab.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("FirstSubTab"));
		ActionFirstSubTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("FirstSubTabDesc"));
		ActionLastSubTab.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("LastSubTab"));
		ActionLastSubTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("LastSubTabDesc"));
		ActionNextSubTab.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("NextSubTab"));
		ActionNextSubTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("NextSubTabDesc"));
		ActionPrevSubTab.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("PreviousSubTab"));
		ActionPrevSubTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("PreviousSubTabDesc"));

		// actions.tabs
		ActionCloseAllTabs.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("CloseAllTabs"));
		ActionCloseAllTabs.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("CloseAllTabsDesc"));
		ActionCloseTab.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("CloseSelectedTab"));
		ActionCloseTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("CloseSelectedTabDesc"));
		ActionFirstTab.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("FirstTab"));
		ActionFirstTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("FirstTabDesc"));
		ActionLastTab.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("LastTab"));
		ActionLastTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("LastTabDesc"));
		ActionNextTab.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("NextTab"));
		ActionNextTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("NextTabDesc"));
		ActionPrevTab.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("PreviousTab"));
		ActionPrevTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("PreviousTabDesc"));

		// actions.tree
		ActionCollapseAll.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("CollapseAll"));
		ActionCollapseAll.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("CollapseAllDesc"));
		ActionCollapseNode.getInstance().putValue(Action.NAME,
				Application.getResourceBundle().getString("CollapseNode"));
		ActionCollapseNode.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("CollapseNodeDesc"));
		ActionExpandAll.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("ExpandAll"));
		ActionExpandAll.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("ExpandAllDesc"));
		ActionExpandNode.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("ExpandNode"));
		ActionExpandNode.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("ExpandNodeDesc"));
		ActionOpenInTab.getInstance().putValue(Action.NAME, Application.getResourceBundle().getString("OpenInTab"));
		ActionOpenInTab.getInstance().putValue(Action.SHORT_DESCRIPTION,
				Application.getResourceBundle().getString("OpenInTabDesc"));
	}
}
