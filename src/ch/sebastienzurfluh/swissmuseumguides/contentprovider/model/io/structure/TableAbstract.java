package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Represents a complete table, with row representations.
 * 
 * @author Sebastien Zurfluh
 *
 * @param <R> 
 */
public abstract class TableAbstract<R extends RowAbstract> extends LinkedList<R> implements Table<R> {
	/**
	 * Serialisation purposes.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a table object from a JSON formatted string.
	 * 
	 * 
	 * 
	 * @param tableJSON usually the format given by cakePHP is 
	 * ARRAY(OBJECT("the thing we want"),OBJECT,...) which translate to
	 * [table name:{row 1},{row 2}]
	 * @throws JSONException
	 */
	public TableAbstract(String tableJSON) throws JSONException {

		this(new JSONArray(tableJSON));
	}



	public TableAbstract(JSONArray tableJSON) {
		for (int i=0; i < tableJSON.length(); i++) {
			try {
				add(getRowFactory().make(tableJSON.getJSONObject(i).getJSONObject(getRowElement())));
			} catch (JSONException e) {
				e.printStackTrace();;
			}
		}
	}

	@Override
	public abstract String getRowElement();

	/**
	 * Take care of the row conversion.
	 */
	public abstract RowFactory<R> getRowFactory();
}