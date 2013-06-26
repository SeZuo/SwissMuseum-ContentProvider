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

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Represents a complete table, with row representations.
 * 
 * @author Sebastien Zurfluh
 *
 * @param <R> 
 */
public abstract class TableAbstract<R extends RowAbstract> extends LinkedList<R> implements Table<R> {
	/**
	 * Serialisation purposes.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a table object from a JSON formatted string.
	 * 
	 * 
	 * 
	 * @param tableJSON usually the format given by cakePHP is 
	 * ARRAY(OBJECT("the thing we want"),OBJECT,...) which translate to
	 * [table name:{row 1},{row 2}]
	 * @throws JSONException
	 */
	public TableAbstract(String tableJSON) throws JSONException {

		this(new JSONArray(tableJSON));
	}



	public TableAbstract(JSONArray tableJSON) {
		for (int i=0; i < tableJSON.length(); i++) {
			try {
				add(getRowFactory().make(tableJSON.getJSONObject(i).getJSONObject(getRowElement())));
			} catch (JSONException e) {
				e.printStackTrace();;
			}
		}
	}

	@Override
	public abstract String getRowElement();

	/**
	 * Take care of the row conversion.
	 */
	public abstract RowFactory<R> getRowFactory();
}