/*
 * Copyright 2012-2013 Sebastien Zurfluh
 * 
 * This file is part of "Swiss Museum Guides".
 * 
 * "Swiss Museum Guides" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "Swiss Museum Guides" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with "Swiss Museum Guides".  If not, see <http://www.gnu.org/licenses/>.
 */

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
