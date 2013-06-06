package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Factory to create the proper row object
 */
public interface RowFactory<T> {
	/**
	 * Create the right row object.
	 * 
	 * @param jsonObject representing a row. It should not be nested like [affiliations: {row}] but
	 * plain like {row}
	 * @throws JSONException if the given json object is malformed.
	 */
	T make(JSONObject jsonObject) throws JSONException;
}
