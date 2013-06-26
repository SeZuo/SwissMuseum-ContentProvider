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

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.ResourcesContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowAbstract;

/**
 * Defines getters for a line in the pages table.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class Resource extends RowAbstract {
	public Resource(String resourceJSONString) throws JSONException {
		super(resourceJSONString);
	}

	public int getId() throws JSONException {
		return getInt(ResourcesContract.COLUMN_NAME_ID);
	}

	public String getTitle() throws JSONException {
		return getString(ResourcesContract.COLUMN_NAME_TITLE);
	}

	public String getDescription() throws JSONException {
		return getString(ResourcesContract.COLUMN_NAME_DESCRIPTION);
	}

	public String getURL() throws JSONException {
		return getString(ResourcesContract.COLUMN_NAME_URL);
	}
	
	public String getType() throws JSONException {
		return getString(ResourcesContract.COLUMN_NAME_TYPE);
	}
}