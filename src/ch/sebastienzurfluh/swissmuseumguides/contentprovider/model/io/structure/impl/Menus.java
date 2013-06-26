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

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowFactory;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.TableAbstract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.MenusContract;

public class Menus extends TableAbstract<Menu> {
	private static final long serialVersionUID = 1L;

	public Menus(String tableJSON) throws JSONException {
		super(tableJSON);
	}

	@Override
	public String getRowElement() {
		return MenusContract.ROW_ELEMENT_NAME;
	}
	
	@Override
	public String getTableName() {
		return MenusContract.TABLE_NAME;
	}

	@Override
	public RowFactory<Menu> getRowFactory() {
		return new RowFactory<Menu>(){
			@Override
			public Menu make(JSONObject jsonObject) throws JSONException {
				return new Menu(jsonObject.toString());
			}
		};
	}
}
