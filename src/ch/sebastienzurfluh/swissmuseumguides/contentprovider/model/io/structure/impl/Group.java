package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.GroupsContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowAbstract;

/**
 * Defines getters for a line in the groups table.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class Group extends RowAbstract {
	public Group(String groupJSONString) throws JSONException {
		super(groupJSONString);
	}
	
	public int getId() throws JSONException {
		return getInt(GroupsContract.COLUMN_NAME_ID);
	}

	public String getName() throws JSONException {
		return getString(GroupsContract.COLUMN_NAME_NAME);
	}

	public int getMenuId() throws JSONException {
		return getInt(GroupsContract.COLUMN_NAME_MENU_ID);
	}
}