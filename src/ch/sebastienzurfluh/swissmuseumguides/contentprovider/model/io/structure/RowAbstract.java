package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public abstract class RowAbstract extends JSONObject implements Row {
	public RowAbstract(String affiliationJSONString) throws JSONException {
		super(affiliationJSONString);
	}
}
