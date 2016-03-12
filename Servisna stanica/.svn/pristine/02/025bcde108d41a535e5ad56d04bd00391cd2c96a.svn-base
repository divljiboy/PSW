package model;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import application.Application;
import gui.tree.Tree;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Extension of the {@link DefaultMutableTreeNode} class. Used as a node in the
 * {@link TreeModel} of the {@link Tree} class. Represents one table or package
 * from the JSON file which holds the hierarchy of database tables.
 * 
 * @author Isidora Škulec
 * @author Milan Radeta
 *
 */
public class TreeNode extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 1L;

	private JsonNode node;

	/**
	 * Constructor of the {@link TreeNode} class. Parameter {@code node} is a
	 * {@link JsonNode} parsed from the JSON file which holds the hierarchy of
	 * database tables.
	 * 
	 * @param node
	 */
	public TreeNode(JsonNode node) {
		super();
		this.node = node;
	}

	/**
	 * Returns the {@link JsonNode} taken from the JSON file which holds the
	 * hierarchy of database tables.
	 * 
	 * @return {@link JsonNode}
	 */
	public JsonNode getNode() {
		return this.node;
	}

	/**
	 * Sets the {@link JsonNode} taken from the JSON file which holds the
	 * hierarchy of database tables.
	 * 
	 * @param node
	 */
	public void setNode(JsonNode node) {
		this.node = node;
	}

	/**
	 * Returns immediate (first level) child {@link TreeNode} objects for this
	 * {@code TreeNode}. For child nodes all the way to the leaf level, see
	 * {@link #getOffspringNodes() getOffspringNodes} method.
	 * 
	 * @return {@link ArrayList}
	 */
	public ArrayList<TreeNode> getChildrenNodes() {
		ArrayList<TreeNode> children = new ArrayList<TreeNode>();
		for (int i = 0; i < getChildCount(); i++) {
			children.add((TreeNode) getChildAt(i));
		}
		return children;
	}

	/**
	 * Returns all child {@link TreeNode} objects (children of children etc.)
	 * for this {@code TreeNode}. For immediate (first level) children, see
	 * {@link #getChildrenNodes() getChildrenNodes} method.
	 * 
	 * @return {@link ArrayList}
	 */
	public ArrayList<TreeNode> getOffspringNodes() {
		ArrayList<TreeNode> offsprings = new ArrayList<TreeNode>();
		TreeNode currentNode = this;
		int listIndex = -1;
		do {
			for (int i = 0; i < currentNode.getChildCount(); i++) {
				offsprings.add((TreeNode) currentNode.getChildAt(i));
			}
		} while (++listIndex < offsprings.size() && (currentNode = offsprings.get(listIndex)) != null);
		return offsprings;
	}

	/**
	 * Returns the {@link String} which is the code of this {@link TreeNode}.
	 * Prefix "Project", "Package" or "Table" will be added to the code if the
	 * node represents a project, a package or a table respectfully.
	 * 
	 */
	@Override
	public String toString() {
		switch (node.get("type").asText()) {
		case "root":
			return Application.getResourceBundle().getString(node.get("code").asText());
		case "project":
			return Application.getResourceBundle().getString("Project_" + node.get("code").asText().toUpperCase());
		case "package":
			return Application.getResourceBundle().getString("Package_" + node.get("code").asText().toUpperCase());
		case "table":
			return Application.getResourceBundle().getString("Table_" + node.get("code").asText().toUpperCase());
		}

		return node.get("code").asText();
	}	
	
}
