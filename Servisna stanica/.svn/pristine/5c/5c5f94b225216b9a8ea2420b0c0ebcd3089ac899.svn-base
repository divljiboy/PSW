package ra94_2012;

import model.TreeModel;

/**
 * Class used for {@link TreeModelTest} as a test JSON {@link String}.
 * 
 * @author Isidora Škulec
 * @see TreeModelTest
 * @see TreeModel
 * @see String
 *
 */
public class JsonStringCreator {

	/**
	 * Static method that returns a test JSON {@link String}, that represents a
	 * {@link TreeModel} structure.
	 * 
	 * @return String
	 * @see String
	 * @see TreeModel
	 */
	public static String getTestJson() {
		String json = "";

		StringBuilder builder = new StringBuilder();
		builder.append("{ \"code\": \"Workspace\", \"type\": \"root\", \"children\": [");
		builder.append("{ \"code\": \"SERVISNA_STANICA\", \"type\": \"project\", \"children\": [");
		builder.append("{ \"code\": \"ZAJEDNICKO\", \"type\": \"package\", \"children\": [");
		builder.append("{ \"code\": \"TERITORIJALNA_ORGANIZACIJA\", \"type\": \"package\", \"children\": [");
		builder.append("{ \"code\": \"DRZAVA\", \"type\": \"table\", \"columns\": [");
		builder.append("{ \"code\": \"DR_OZNAKA\", \"semantic\": \"DR_NAZIV\" }, { \"code\": \"DR_NAZIV\" }");
		builder.append("]}]}]}]}]}");

		json = new String(builder);

		return json;
	}
}
