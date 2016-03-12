package ra94_2012;

import java.util.ArrayList;
import java.util.MissingResourceException;

import javax.swing.tree.MutableTreeNode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.Application;
import gui.tree.Tree;
import model.TreeNode;

/**
 * Test class for {@link TreeNode} class.
 * Valid and invalid objects of this class are created and tested for errors.
 * 
 * @author Isidora Skulec
 *
 */
public class TreeNodeTest {
	
	private TreeNode nodeOK;
	private JsonNode jsonOK;
	
	private TreeNode nodeCode;
	private JsonNode jsonCode;
	
	private TreeNode nodeNoCode;
	private JsonNode jsonNoCode;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Method that creates and prepares objects of this class for testing, along with objects needed by them.
	 * Creates valid objects, as well as ones which induce expected exceptions.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Application.setResourceBundle( Application.getNewBundle() );
		ObjectMapper mapper = new ObjectMapper();
		jsonOK = mapper.readTree(Application.class.getResource("/testOK.json"));
		jsonCode = mapper.readTree(Application.class.getResource("/testCode.json"));
		jsonNoCode = mapper.readTree(Application.class.getResource("/testNoCode.json"));
		
		nodeOK = new TreeNode(jsonOK);
		nodeCode = new TreeNode(jsonCode);
		nodeNoCode = new TreeNode(jsonNoCode);
		
		nodeCode.add((MutableTreeNode) nodeOK.clone());
		nodeOK.add(nodeCode);
		nodeOK.add(nodeNoCode);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Method for testing the getter {@link TreeNode#getNode()} method.
	 * Checks whether the right object reference is returned.
	 */
	@Test
	public final void testGetNode() {
		Assert.assertSame(jsonOK, nodeOK.getNode());
	}

	/**
	 * Method for testing the setter {@link TreeNode#setNode(JsonNode)} method.
	 * Checks whether the right object reference is set.
	 */
	@Test
	public final void testSetNode() {
		nodeOK.setNode(null);
		Assert.assertSame(null, nodeOK.getNode());
		nodeOK.setNode(jsonOK);
		Assert.assertSame(jsonOK, nodeOK.getNode());
	}

	/**
	 * Method for testing {@link TreeNode#getChildrenNodes()} method for getting first level children nodes of the {@link Tree}.
	 * Checks whether the number of returned nodes is right.
	 */
	@Test
	public final void testGetChildrenNodes() {
		ArrayList<TreeNode> children = nodeOK.getChildrenNodes();
		Assert.assertEquals(2, children.size());
	}

	/**
	 * Method for testing {@link TreeNode#getOffspringNodes()} method for getting all level children nodes of the {@link Tree}.
	 * Checks whether the number of returned nodes is right.
	 */
	@Test
	public final void testGetOffspringNodes() {
		ArrayList<TreeNode> children = nodeOK.getOffspringNodes();
		Assert.assertEquals(3, children.size());
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Tests whether the redefinition of {@link TreeNode#toString()} method returns the correct string.
	 */
	@Test
	public final void testToString() {
		Assert.assertEquals("Workspace", nodeOK.toString());
	}
	
	/**
	 * Tests whether the {@link MissingResourceException} is thrown when {@link TreeNode#toString()} method tries to reference a non defined word.
	 */
	@Test
	public final void testToStringMissingResource() {		
		thrown.expect(MissingResourceException.class);
		nodeCode.toString();
	}
	
	/**
	 * Tests whether the {@link NullPointerException} is thrown when there is no content.
	 */
	@Test
	public final void testToStringNullPointer() {		
		thrown.expect(NullPointerException.class);
		nodeNoCode.toString();
	}

}
