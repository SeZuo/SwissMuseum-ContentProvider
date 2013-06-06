package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.MenusContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowAbstract;

/**
 * Defines getters for a line in the menus table.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class Menu extends RowAbstract {
	public Menu(String menuJSONString) throws JSONException {
		super(menuJSONString);
	}

	public int getId() throws JSONException {
		return getInt(MenusContract.COLUMN_NAME_ID);
	}

	public String getTitle() throws JSONException {
		return getString(MenusContract.COLUMN_NAME_TITLE);
	}

	public String getDescription() throws JSONException {
		return getString(MenusContract.COLUMN_NAME_DESCRIPTION);
	}

	public String getImgURL() throws JSONException {
		return getString(MenusContract.COLUMN_NAME_IMG_URL);
	}
	
	public String getThumbImgURL() throws JSONException {
		return getString(MenusContract.COLUMN_NAME_THUMB_IMG_URL);
	}
}