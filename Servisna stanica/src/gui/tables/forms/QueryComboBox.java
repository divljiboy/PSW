package gui.tables.forms;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import actions.records.ActionAdvancedSearch;
import actions.records.ActionConfirmSearch;
import application.Application;
import model.Condition;

/**
 * Extension of {@link ComboBox} that is used in the <i>WHERE</i> condition when performing
 * an advanced search.
 * 
 * @author Milan Radeta
 * @see ActionAdvancedSearch
 * @see ActionConfirmSearch
 *
 */
public class QueryComboBox extends JComboBox<Condition> {
	private static final long serialVersionUID = 1L;

	private QueryForm queryForm;

	/**
	 * Constructor of the {@link QueryComboBox} class.
	 * Parameter {@code queryForm} takes the parent {@link QueryForm} object.
	 * 
	 * @param queryForm
	 */
	public QueryComboBox(QueryForm queryForm) {
		this.setQueryForm(queryForm);
		((JLabel) getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	 * Method for loading textual conditions from the {@link Condition} class as combo box values.
	 */
	public void addTextConditions() {
		addItem(new Condition(Condition.STARTS_WITH, Application.getResourceBundle().getString("ConditionStartsWith")));
		addItem(new Condition(Condition.ENDS_WITH, Application.getResourceBundle().getString("ConditionEndsWith")));
		addItem(new Condition(Condition.CONTAINS, Application.getResourceBundle().getString("ConditionContains")));
		addItem(new Condition(Condition.EQUALS, Application.getResourceBundle().getString("ConditionEquals")));
		addItem(new Condition(Condition.NOT_EQUAL_TO,
				Application.getResourceBundle().getString("ConditionNotEqualTo")));
	}

	/**
	 * Method for loading numerical conditions from the {@link Condition} class as combo box values.
	 */
	public void addNumericConditions() {
		addItem(new Condition(Condition.EQUALS, Application.getResourceBundle().getString("ConditionEquals")));
		addItem(new Condition(Condition.NOT_EQUAL_TO,
				Application.getResourceBundle().getString("ConditionNotEqualTo")));
		addItem(new Condition(Condition.GREATER, Application.getResourceBundle().getString("ConditionGreater")));
		addItem(new Condition(Condition.GREATER_OR_EQUAL,
				Application.getResourceBundle().getString("ConditionGreaterEqual")));
		addItem(new Condition(Condition.LESSER, Application.getResourceBundle().getString("ConditionLesser")));
		addItem(new Condition(Condition.LESSER_OR_EQUAL,
				Application.getResourceBundle().getString("ConditionLesserEqual")));
	}

	/**
	 * Method for loading boolean conditions from the {@link Condition} class as combo box values.
	 */
	public void addBooleanConditions() {
		addItem(new Condition(Condition.TRUE_AND_FALSE,
				Application.getResourceBundle().getString("ConditionTrueFalse")));
		addItem(new Condition(Condition.TRUE, Application.getResourceBundle().getString("ConditionTrue")));
		addItem(new Condition(Condition.FALSE, Application.getResourceBundle().getString("ConditionFalse")));
	}

	/**
	 * Method for loading temporal conditions from the {@link Condition} class as combo box values.
	 */
	public void addDateTimeConditions() {
		addItem(new Condition(Condition.AFTER, Application.getResourceBundle().getString("ConditionAfter")));
		addItem(new Condition(Condition.BEFORE, Application.getResourceBundle().getString("ConditionBefore")));
		addItem(new Condition(Condition.EQUALS, Application.getResourceBundle().getString("ConditionEquals")));
		addItem(new Condition(Condition.NOT_EQUAL_TO,
				Application.getResourceBundle().getString("ConditionNotEqualTo")));
	}

	/**
	 * Returns the parent {@link QueryForm} object.
	 * @return {@link QueryForm}
	 */
	public QueryForm getQueryForm() {
		return queryForm;
	}

	/**
	 * Sets the parent {@link QueryForm} object.
	 * @param queryForm
	 */
	public void setQueryForm(QueryForm queryForm) {
		this.queryForm = queryForm;
	}
}
