package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;
import org.json.JSONObject;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowFactory;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.TableAbstract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.ResourcesContract;

public class Resources extends TableAbstract<Resource> {
	private static final long serialVersionUID = 1L;

	public Resources(String tableJSON) throws JSONException {
		super(tableJSON);
	}

	@Override
	public String getRowElement() {
		return ResourcesContract.ROW_ELEMENT_NAME;
	}
	
	@Override
	public String getTableName() {
		return ResourcesContract.TABLE_NAME;
	}

	@Override
	public RowFactory<Resource> getRowFactory() {
		return new RowFactory<Resource>(){
			@Override
			public Resource make(JSONObject jsonObject) throws JSONException {
				return new Resource(jsonObject.toString());
			}
		};
	}
}
