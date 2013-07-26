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

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowAbstract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.MenusContract;

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