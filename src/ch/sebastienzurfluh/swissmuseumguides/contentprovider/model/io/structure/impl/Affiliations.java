package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;
import org.json.JSONObject;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowFactory;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.TableAbstract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.AffiliationsContract;

public class Affiliations extends TableAbstract<Affiliation> {
	private static final long serialVersionUID = 1L;

	public Affiliations(String tableJSON) throws JSONException {
		super(tableJSON);
	}

	@Override
	public String getRowElement() {
		return AffiliationsContract.ROW_ELEMENT_NAME;
	}
	
	@Override
	public String getTableName() {
		return AffiliationsContract.TABLE_NAME;
	}

	@Override
	public RowFactory<Affiliation> getRowFactory() {
		return new RowFactory<Affiliation>(){
			@Override
			public Affiliation make(JSONObject jsonObject) throws JSONException {
				return new Affiliation(jsonObject.toString());
			}
		};
	}
}
