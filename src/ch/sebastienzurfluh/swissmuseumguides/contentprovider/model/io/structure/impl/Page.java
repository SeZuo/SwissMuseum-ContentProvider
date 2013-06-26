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

	public String getContent() throws JSONException {
		return getString(PagesContract.COLUMN_NAME_CONTENT);
	}
	
	public int getMenuId() throws JSONException {
		return getInt(PagesContract.COLUMN_NAME_MENU_ID);
	}
}