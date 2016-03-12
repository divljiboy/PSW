package ra81_2012;

import java.util.List;

import org.junit.runners.JUnit4;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

/**
 * A mockup of {@link JsonNode} class, used for JUnit testing.
 * Extends {@link JsonNode}.
 * @see JsonNode
 * @see JUnit4
 * @author Milan Radeta
 *
 */
public class MockupJsonNode extends JsonNode{

	String text;
	
	@Override
	public JsonToken asToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NumberType numberType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonParser traverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonParser traverse(ObjectCodec arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JsonNode _at(JsonPointer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String asText() {
		return text;
	}

	@Override
	public <T extends JsonNode> T deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JsonNode findParent(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JsonNode> findParents(String arg0, List<JsonNode> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonNode findPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonNode findValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JsonNode> findValues(String arg0, List<JsonNode> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findValuesAsText(String arg0, List<String> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonNode get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonNodeType getNodeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonNode path(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonNode path(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonNode get(String arg0) {
		if (arg0.equals("code")) {
			MockupJsonNode node = new MockupJsonNode();
			node.text = "Test";
			return node;
		}
		return null;
	}

}
