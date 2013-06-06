package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.AffiliationsContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowAbstract;

/**
 * Defines getters for a line in the affiliations table.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class Affiliation extends RowAbstract {
	public Affiliation(String affiliationJSONString) throws JSONException {
		super(affiliationJSONString);
	}

	public int getId() throws JSONException {
		return getInt(AffiliationsContract.COLUMN_NAME_ID);
	}

	public int getPageId() throws JSONException {
		return getInt(AffiliationsContract.COLUMN_NAME_PAGE_ID);
	}

	public int getGroupId() throws JSONException {
		return getInt(AffiliationsContract.COLUMN_NAME_GROUP_ID);
	}

	public int getOrder() throws JSONException {
		return getInt(AffiliationsContract.COLUMN_NAME_ORDER);
	}
}