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
	
	private static final int PAGES_GET = 1;
	private static final int MENUS_LISTALLPAGEMENUSFROUMGROUP = 2;
	private static final int RESOURCES_GET = 3;
	
	private static final String AUTHORITY = "ch.sebastienzurfluh.swissmuseum.contentprovider";
	private static final String CONTENT_URI_ROOT = "content://" + AUTHORITY + "/";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
//        uriMatcher.addURI(CALContract.AUTHORITY, CONTENT_URI_ROOT + "pages/#", PAGES_GET);
        uriMatcher.addURI(
        		AUTHORITY, CONTENT_URI_ROOT + "pages/getGroup/#",
        		MENUS_LISTALLPAGEMENUSFROUMGROUP);
//        uriMatcher.addURI(CALContract.AUTHORITY, CONTENT_URI_ROOT + "resources/#", RESOURCES_GET);
    }

	private LocalConnector database;

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
		switch(uriMatcher.match(uri)) {
//		case PAGES_GET:
//			System.out.println(uri.toString());
//			break;
		case MENUS_LISTALLPAGEMENUSFROUMGROUP: // listAllPageMenusFromGroup
			System.out.println(uri.toString());
			
			//TODO retrive id in the query uri
			// database.getPagesMenusInGroup(#group_id)
			
			
			break;
//		case RESOURCES_GET:
//			System.out.println(uri.toString());
//			break;
//		case UriMatcher.NO_MATCH:
		default:
			System.out.println("URI IS WRONG : " + uri.toString());
			break;
		}
		
		
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
