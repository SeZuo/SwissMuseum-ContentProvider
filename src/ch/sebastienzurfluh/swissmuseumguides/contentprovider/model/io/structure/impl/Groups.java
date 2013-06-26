package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;
import org.json.JSONObject;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowFactory;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.TableAbstract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.GroupsContract;

public class Groups extends TableAbstract<Group> {
	private static final long serialVersionUID = 1L;

	public Groups(String tableJSON) throws JSONException {
		super(tableJSON);
	}

	@Override
	public String getRowElement() {
		return GroupsContract.ROW_ELEMENT_NAME;
	}
	
	@Override
	public String getTableName() {
		return GroupsContract.TABLE_NAME;
	}

	@Override
	public RowFactory<Group> getRowFactory() {
		return new RowFactory<Group>(){
			@Override
			public Group make(JSONObject jsonObject) throws JSONException {
				return new Group(jsonObject.toString());
			}
		};
	}
}
