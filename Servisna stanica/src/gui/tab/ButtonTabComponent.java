package gui.tab;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.Application;
import gui.tables.TabbedPane;
import gui.tables.Table;
import model.TableModel;
import model.TreeNode;

/**
 * Tab component used by {@link TabbedPane}'s tabs. It extends {@link JPanel}.
 * It contains a label and a {@link TabCloseButton} component.
 * 
 * @see TabbedPane
 * @see JPanel
 * @see TabCloseButton
 * @author Milan Radeta
 *
 */
public class ButtonTabComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * {@link JLabel} component which holds the tab's name.
	 */
	private JLabel label;

	/**
	 * Creates a new {@link ButtonTabComponent} with {@link FlowLayout}, a
	 * localized {@link Table}'s name in its {@code label} and a {@link TabCloseButton}.
	 * 
	 * @see FlowLayout
	 * @see JLabel
	 * @see TabCloseButton
	 * @see Table
	 * @see TableModel
	 * @see TreeNode
	 * @see TabbedPane
	 * @param tabbedPane
	 * @param node
	 */
	public ButtonTabComponent(TabbedPane tabbedPane, TreeNode node) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setOpaque(false);

		label = new JLabel(Application.getResourceBundle()
				.getString("Table_" + node.getNode().get("code").asText().toUpperCase()));

		add(label);
		// add more space between the label and the button
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		JButton button = new TabCloseButton(tabbedPane, node);
		add(button);
		// add more space to the top of the component
		setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
	}

	/**
	 * Returns {@link ButtonTabComponent}'s {@link JLabel} component.
	 * @return {@link JLabel}
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * 
	/**
	 * Sets {@link ButtonTabComponent}'s {@link JLabel} component.
	 * @param label
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}

}
