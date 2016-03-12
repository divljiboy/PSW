package model;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.Application;
import gui.tree.Tree;

/**
 * Extension of the {@link DefaultTreeModel} class.
 * Used as the model for the {@link Tree} class.
 * It contains a hierarchy of {@link TreeNode} objects representing the {@link Tree}.
 * The structure of the hierarchy is written in a JSON file.
 * 
 * @author Isidora Škulec
 * @author Milan Radeta
 *
 */
public class TreeModel extends DefaultTreeModel {
	private static final long serialVersionUID = 1L;

	public static String jsonPath = "/document.json";

	private static ArrayList<TreeNode> tableNodes = new ArrayList<TreeNode>();
	
	/**
	 * Constructor of the {@link TreeModel} class.
	 * Parameter {@code root} takes a {@link TreeNode} objects which is the root (first) node of the tree.
	 * 
	 * @param root
	 */
	public TreeModel(TreeNode root) {
		super(root);
		for (TreeNode node : tableNodes) {
			try {
				TableSchema.getTable(node);
			} catch (SQLException e) {
				Application.showSqlExceptionError(e);
			}
		}
	}
	
	
	/**
	 * Getter method for the {@link String} path of the JSON file.
	 * @return String
	 */
	public static String getJsonPath() { return jsonPath; }
	/**
	 * Setter method for the {@link String} path of the JSON file.
	 * @param jsonPath
	 */
	public static void setJsonPath(String jsonPath) { TreeModel.jsonPath = jsonPath; }



	/**
	 * A static method used for obtaining {@link TreeModel} from the JSON file it is written in.
	 * If there is no such file, a dialog will be shown informing the user that the model was not obtained.
	 *  
	 * @return {@link TreeModel}
	 */
	public static TreeModel getTreeModel() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonRoot;
		try {
			jsonRoot = mapper.readTree(Application.class.getResource(jsonPath));
			return iterateTree(jsonRoot, null);
		} catch (Exception e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
		
	}

	/**
	 * A private static method for parsing the JSON file in which the {@link TreeModel} is written.
	 * It creates a hierarchy of {@link TreeNode} objects and puts them into the {@link TreeModel}.
	 * Used internally by the {@code getTreeModel} method. 
	 * 
	 * @param node
	 * @param parent
	 * @return {@link TreeModel}
	 */
	public static TreeModel iterateTree(JsonNode node, TreeNode parent) {
		// If node has no parent, that it's the root node
		
		JsonNode children = node.get("children");
		
		TreeNode tn = new TreeNode(node);
		if (tn.getNode().get("type").asText().equals("table")) {
			tableNodes.add(tn);
		}
		
		if(children != null) {
			for(JsonNode el : children){
				iterateTree(el, tn);			
			}
		}

		if (parent != null) {
			parent.add(tn);
		}
		else {
			return new TreeModel(tn);
		}
		return null;
		
	}
	
}
