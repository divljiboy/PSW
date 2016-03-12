package gui.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import application.Application;
import gui.MainFrame;

/**
 * Extension of the {@link JDialog} class. Provides basic information on the
 * creators of this application.
 * 
 * @author Milan Radeta
 */
public class DialogAbout extends JDialog {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of {@link DialogAbout} class. Creates a small dialog which
	 * shows basic information on the creators of this application.
	 */
	public DialogAbout() {
		super(MainFrame.getInstance(), true);
		setSize(465, 750);
		setResizable(false);
		setLocationRelativeTo(this.getParent());
		setTitle(Application.getResourceBundle().getString("About"));

		JEditorPane editorPane;
		DialogAbout.class.getClassLoader();
		StringBuilder text = new StringBuilder();
		String imgSrc;
		try {
			String[] authors = { "Isidora", "Milan", "Ivan", "Borko" };

			text.append("<html><body><div>" + Application.getResourceBundle().getString("AppDesc") + "<ul>");

			for (int i = 0; i < authors.length; i++) {
				text.append("<li>" + Application.getResourceBundle().getString(authors[i]) + "</li>");
			}
			text.append("</ul><div>");
			for (int i = 0; i < authors.length; i++) {
				String author = authors[i];
				imgSrc = Application.class.getResource("/authors/" + author + ".png").toExternalForm();
				text.append(
						"<img align=center width=225 height=275 src='" + imgSrc + "'>" + (i % 2 == 1 ? "<br>" : ""));
			}
			text.append("</body></html>");
			editorPane = new JEditorPane("text/html", text.toString());
			editorPane.setEditable(false);
			add(editorPane, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
