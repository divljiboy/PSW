package ra94_2012;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.TableSchema;
import model.TreeModel;
import model.TreeNode;

/**
 * Test class for {@link TreeModel} class.
 * Valid and invalid objects of this class are created and tested for errors.
 * 
 * @author Isidora Skulec
 *
 */
public class TreeModelTest {
	
	TreeModel model;
	TreeNode treeNode;
	JsonNode jsonNode;

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
		TableSchema fake = new TableSchema();
		fake.setCode("DRZAVA");
		TableSchema.getTableSchemas().put(fake.getCode(), fake);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = JsonStringCreator.getTestJson();
		jsonNode = mapper.readTree(jsonString);
		treeNode = new TreeNode(jsonNode);
		model = new TreeModel(treeNode);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Method for testing the getter {@link TreeModel#getJsonPath()} method.
	 * Checks whether the right string value is returned.
	 */
	@Test
	public final void testGetJsonPath() {
		Assert.assertEquals("/document.json", TreeModel.getJsonPath()); 
	}

	/**
	 * Method for testing the getter {@link TreeModel#setJsonPath(String)} method.
	 * Checks whether the right string value is set.
	 * @see TreeModel
	 * @see TreeModel#setJsonPath(String)
	 * @see String
	 */
	@Test
	public final void testSetJsonPath() {
		TreeModel.setJsonPath("asdf");
		Assert.assertEquals("asdf", TreeModel.getJsonPath());
		TreeModel.setJsonPath("/document.json");
		Assert.assertEquals("/document.json", TreeModel.getJsonPath());
	}

	/**
	 * Test method for {@link TreeModel#getTreeModel()}.
	 * It creates the model which is expected and compares it to the returned model.
	 * The hierarchy and each node is checked whether it is the same by content.
	 * 
	 */
	@Test
	public final void testGetTreeModel() {
		TreeModel.setJsonPath("/testOK.json");
		TreeModel newModel = TreeModel.getTreeModel();
		
		TreeNode rootNode = ((TreeNode)newModel.getRoot());
		
		ArrayList<TreeNode> treeNodesToCheck = new ArrayList<>();
		treeNodesToCheck.add(rootNode);
		ArrayList<JsonNode> jsonNodesToCheck = new ArrayList<>();
		jsonNodesToCheck.add(jsonNode);
		
		while(!treeNodesToCheck.isEmpty() && !jsonNodesToCheck.isEmpty() ) {
			TreeNode currentTreeNode = treeNodesToCheck.get(0);
			JsonNode currentJsonNode = jsonNodesToCheck.get(0);
			
			treeNodesToCheck.remove(0);
			jsonNodesToCheck.remove(0);
			
			Assert.assertEquals(currentJsonNode , currentTreeNode.getNode() );
			
			if( currentJsonNode.get("children") != null ) {
				Assert.assertSame( currentJsonNode.get("children").size() , currentTreeNode.getChildCount());
				
				if(currentJsonNode.get("children").size() > 0) {
					
					for(JsonNode el : currentJsonNode.get("children")) {
						jsonNodesToCheck.add(el);
					}
					
					for(int i = 0; i < currentTreeNode.getChildCount(); i++) {
						treeNodesToCheck.add((TreeNode) currentTreeNode.getChildAt(i));
					}
				}
			
			}
		}
	}

	/**
	 * Test method for {@link TreeModel#iterateTree(JsonNode, TreeNode)}.
	 * Simulates the iteration through {@link JsonNode} and compares it to the returned result.
	 * The hierarchy and each node is checked whether it is the same by content.
	 * 
	 */
	@Test
	public final void testIterateTree() {
		TreeModel newModel = TreeModel.iterateTree(jsonNode, null);
		
		TreeNode rootNode = ((TreeNode)newModel.getRoot());
		
		ArrayList<TreeNode> treeNodesToCheck = new ArrayList<>();
		treeNodesToCheck.add(rootNode);
		ArrayList<JsonNode> jsonNodesToCheck = new ArrayList<>();
		jsonNodesToCheck.add(jsonNode);
		
		while(!treeNodesToCheck.isEmpty() && !jsonNodesToCheck.isEmpty() ) {
			TreeNode currentTreeNode = treeNodesToCheck.get(0);
			JsonNode currentJsonNode = jsonNodesToCheck.get(0);
			
			treeNodesToCheck.remove(0);
			jsonNodesToCheck.remove(0);
			
			Assert.assertSame( currentJsonNode , currentTreeNode.getNode() );
			
			if( currentJsonNode.get("children") != null ) {
				Assert.assertSame( currentJsonNode.get("children").size() , currentTreeNode.getChildCount());
				
				if(currentJsonNode.get("children").size() > 0) {
					
					for(JsonNode el : currentJsonNode.get("children")) {
						jsonNodesToCheck.add(el);
					}
					
					for(int i = 0; i < currentTreeNode.getChildCount(); i++) {
						treeNodesToCheck.add((TreeNode) currentTreeNode.getChildAt(i));
					}
				}
			
			}
		}
		
	}

}
