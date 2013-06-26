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

package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Factory to create the proper row object
 */
public interface RowFactory<T> {
	/**
	 * Create the right row object.
	 * 
	 * @param jsonObject representing a row. It should not be nested like [affiliations: {row}] but
	 * plain like {row}
	 * @throws JSONException if the given json object is malformed.
	 */
	T make(JSONObject jsonObject) throws JSONException;
}
