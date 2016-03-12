package renderers;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import application.Application;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Extension of the {@link DefaultTreeCellRenderer} class.
 * It is used to set icons for different types of nodes in the {@link Tree}.
 * Static fields of this class are said icons.
 * 
 * @author Isidora Škulec
 * @author Milan Radeta
 * @author Borko Arsović
 *
 */
public class TreeRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = 1L;
	
    private static ImageIcon rootIcon =  new ImageIcon( new ImageIcon(Application.class.getResource("/icons/home.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH) );
    private static ImageIcon projectIcon = new ImageIcon( new ImageIcon(Application.class.getResource("/icons/project.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH) );
    private static ImageIcon closedPackageIcon =  new ImageIcon( new ImageIcon(Application.class.getResource("/icons/packageClosed.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH) );
    private static ImageIcon openPackageIcon =  new ImageIcon( new ImageIcon(Application.class.getResource("/icons/packageOpen.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH) );
    private static ImageIcon closedTableIcon = new ImageIcon( new ImageIcon(Application.class.getResource("/icons/tableNode.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH) );
    private static ImageIcon openTableIcon =  new ImageIcon( new ImageIcon(Application.class.getResource("/icons/document.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH) );
    private static ImageIcon leafTableIcon = new ImageIcon( new ImageIcon(Application.class.getResource("/icons/document.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH) );
    
    /**
     * Depending on the type of {@link TreeNode}, set the appropriate icon.
     */
	public Component getTreeCellRendererComponent(
			JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
                
                TreeNode node = (TreeNode) value;
                
                String type = node.getNode().get("type").asText();
                
                switch (type) {
					case "root":
						setIcon(rootIcon);
						break;
					case "project":
						setIcon(projectIcon);
						break;
					case "package":
						if(tree.isExpanded(new TreePath(node.getPath()))) {
							setIcon(openPackageIcon);
						}
						else {
							setIcon(closedPackageIcon);
						}
						break;
					case "table":
						if(node.isLeaf()) {
							setIcon(leafTableIcon);
						}
						else if(tree.isExpanded(new TreePath(node.getPath()))) {
							setIcon(openTableIcon);
						}
						else {
							setIcon(closedTableIcon);
						}
						break;
				}
                
           return this;
	}

	
}
