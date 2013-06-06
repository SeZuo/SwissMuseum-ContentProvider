package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.ResourcesContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowAbstract;

/**
 * Defines getters for a line in the pages table.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class Resource extends RowAbstract {
	public Resource(String resourceJSONString) throws JSONException {
		super(resourceJSONString);
	}

	public int getId() throws JSONException {
		return getInt(ResourcesContract.COLUMN_NAME_ID);
	}

	public String getTitle() throws JSONException {
		return getString(ResourcesContract.COLUMN_NAME_TITLE);
	}

	public String getDescription() throws JSONException {
		return getString(ResourcesContract.COLUMN_NAME_DESCRIPTION);
	}

	public String getURL() throws JSONException {
		return getString(ResourcesContract.COLUMN_NAME_URL);
	}
	
	public String getType() throws JSONException {
		return getString(ResourcesContract.COLUMN_NAME_TYPE);
	}
}