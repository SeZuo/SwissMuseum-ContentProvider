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

package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.connectors.LocalConnector;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Façade for the SwissMuseumGuides model.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class Database extends ContentProvider {
	
	private static final String AUTHORITY = "ch.sebastienzurfluh.swissmuseumguides.contentprovider";
	
	

	private SQLiteOpenHelper database;

	@Override
	public boolean onCreate() {
		database = new LocalConnector(this.getContext());
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		//TODO: this is the only function we have to implement here!
	
		// here comes the functions we need.
		
		
		return null;
	}
	
	
	private Cursor menusListAllPageMenusFromGroup() {
		return null;
	}
	
	private Cursor pagesGetData(int pageId) {
		return null;
	}
	
	private Cursor resourcesGet(int resourceId) {
		//TODO later.... we don't need this just yet (but we will once we get rid of the web views)
		return null;
	}
	

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
}
