package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;
import org.json.JSONObject;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.PagesContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowFactory;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.TableAbstract;

public class Pages extends TableAbstract<Page> {
	private static final long serialVersionUID = 1L;

	public Pages(String tableJSON) throws JSONException {
		super(tableJSON);
	}

	@Override
	public String getRowElement() {
		return PagesContract.ROW_ELEMENT_NAME;
	}
	
	@Override
	public String getTableName() {
		return PagesContract.TABLE_NAME;
	}

	@Override
	public RowFactory<Page> getRowFactory() {
		return new RowFactory<Page>(){
			@Override
			public Page make(JSONObject jsonObject) throws JSONException {
				return new Page(jsonObject.toString());
			}
		};
	}
}
