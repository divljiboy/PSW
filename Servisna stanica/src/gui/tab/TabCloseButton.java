package gui.tab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import gui.tables.TabbedPane;
import listeners.tab.TabCloseButtonActionListener;
import listeners.tab.TabCloseButtonMouseListener;
import model.TreeNode;

/**
 * Extension of {@link JButton} which is used by {@link ButtonTabComponent} of
 * {@link TabbedPane}. It's an X button which closes the tab.
 * 
 * @see JButton
 * @see ButtonTabComponent
 * @see TabbedPane
 * @author Milan Radeta
 *
 */
public class TabCloseButton extends JButton {
	private static final long serialVersionUID = 1L;

	/**
	 * {@link ActionListener} for {@link TabCloseButton}.
	 * 
	 * @see TabCloseButtonActionListener
	 */
	private TabCloseButtonActionListener actionListener;
	/**
	 * {@link MouseListener} for {@link TabCloseButton}.
	 * 
	 * @see TabCloseButtonMouseListener
	 */
	private TabCloseButtonMouseListener mouseListener;

	/**
	 * Constructs a new {@link TabCloseButton} component with added mouse and
	 * action listeners.
	 * 
	 * @param tabbedPane
	 * @param node
	 */
	public TabCloseButton(TabbedPane tabbedPane, TreeNode node) {
		int size = 17;
		setPreferredSize(new Dimension(size, size));
		setUI(new BasicButtonUI());
		setContentAreaFilled(false);
		setFocusable(false);
		setBorder(BorderFactory.createEtchedBorder());
		setBorderPainted(false);
		mouseListener = new TabCloseButtonMouseListener(this);
		addMouseListener(mouseListener);
		setRolloverEnabled(true);
		actionListener = new TabCloseButtonActionListener(tabbedPane, node);
		addActionListener(actionListener);
	}

	/**
	 * Paints X across the button. Also it paints the X red if it's rolled over
	 * and translated if it's pressed.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		// shift the image for pressed buttons
		if (getModel().isPressed()) {
			g2.translate(1, 1);
		}
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLACK);
		if (getModel().isRollover()) {
			g2.setColor(Color.RED);
		}
		int delta = 6;
		g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
		g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
		g2.dispose();
	}
}
