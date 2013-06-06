package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.PagesContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowAbstract;

/**
 * Defines getters for a line in the pages table.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class Page extends RowAbstract {
	public Page(String pageJSONString) throws JSONException {
		super(pageJSONString);
	}

	public int getId() throws JSONException {
		return getInt(PagesContract.COLUMN_NAME_ID);
	}

	public String getTitle() throws JSONException {
		return getString(PagesContract.COLUMN_NAME_TITLE);
	}

	public String getSubtitle() throws JSONException {
		return getString(PagesContract.COLUMN_NAME_SUBTITLE);
	}

	public String getImgURL() throws JSONException {
		return getString(PagesContract.COLUMN_NAME_CONTENT);
	}
	
	public int getMenuId() throws JSONException {
		return getInt(PagesContract.COLUMN_NAME_MENU_ID);
	}
}