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

package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.connectors;

import org.json.JSONException;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.interfaces.IOConnector;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.AffiliationsContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.GroupsContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.MenusContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.PagesContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.ResourcesContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Affiliation;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Affiliations;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Group;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Groups;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Menu;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Menus;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Page;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Pages;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Resource;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Resources;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Creates then lets the user fill the local database.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class LocalConnector extends SQLiteOpenHelper implements IOConnector {
	
	private static final String DATABASE_NAME = "swissmuseumguides";
	private static final int DATABASE_VERSION = 2;
	private static final String[] CREATE_TABLES = {
		"CREATE TABLE IF  NOT EXISTS " + AffiliationsContract.TABLE_NAME + " ("
    		+ "  " + AffiliationsContract.COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
    		+ "  " + AffiliationsContract.COLUMN_NAME_PAGE_ID + " INTEGER NOT NULL,"
    		+ "  " + AffiliationsContract.COLUMN_NAME_GROUP_ID + " INTEGER NOT NULL,"
    		+ "  \'" + AffiliationsContract.COLUMN_NAME_ORDER + "\' INTEGER NOT NULL"
    		+ ");"
    		, "CREATE TABLE IF NOT EXISTS groups ("
    		+ "  id INTEGER PRIMARY KEY,"
    		+ "  name TEXT NOT NULL,"
    		+ "  menu_id INTEGER NOT NULL"
    		+ ");"
    		, "CREATE TABLE IF NOT EXISTS menus ("
    		+ "  id INTEGER PRIMARY KEY,"
    		+ "  title TEXT NOT NULL,"
    		+ "  description TEXT NOT NULL,"
    		+ "  thumb_img_url TEXT NOT NULL,"
    		+ "  img_url TEXT NOT NULL"
    		+ ");"
    		, "CREATE TABLE IF NOT EXISTS " + PagesContract.TABLE_NAME + " ("
    		+ "  " + PagesContract.COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
    		+ "  " + PagesContract.COLUMN_NAME_TITLE + " TEXT NOT NULL,"
    		+ "  " + PagesContract.COLUMN_NAME_SUBTITLE + " TEXT NOT NULL,"
    		+ "  " + PagesContract.COLUMN_NAME_CONTENT + " text NOT NULL,"
    		+ "  " + PagesContract.COLUMN_NAME_MENU_ID + " INTEGER NOT NULL"
    		+ ");"
    		, "CREATE TABLE IF NOT EXISTS resources ("
    		+ "  id INTEGER PRIMARY KEY,"
    		+ "  title TEXT NOT NULL,"
    		+ "  url TEXT NOT NULL,"
    		+ "  description TEXT NOT NULL,"
    		+ "  type TEXT NOT NULL"
    		+ ");"
    		, "CREATE INDEX IF NOT EXISTS pages_menu_id ON " + PagesContract.TABLE_NAME + " (menu_id);"
    		, "CREATE INDEX IF NOT EXISTS groups_menu_id ON groups (menu_id);"};
	private static final String[] DROP_TABLES = {
		"DROP TABLE IF EXISTS " + AffiliationsContract.TABLE_NAME + ";",
		"DROP TABLE IF EXISTS " + GroupsContract.TABLE_NAME + ";",
		"DROP TABLE IF EXISTS " + MenusContract.TABLE_NAME + ";",
		"DROP TABLE IF EXISTS " + PagesContract.TABLE_NAME + ";",
		"DROP TABLE IF EXISTS " + ResourcesContract.TABLE_NAME + ";"};
	

	public LocalConnector(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("LocalConnector: onCreate");
		
		for (String createTableStatement : CREATE_TABLES) {
			db.execSQL(createTableStatement);
		}
		System.out.println("LocalConnector: onCreate completed");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (String dropTableStatement : DROP_TABLES) {
			db.execSQL(dropTableStatement);
		}
		onCreate(db);
	}

	@Override
	public void addAll(final Affiliations affiliations) {
		new Thread() {
		    @Override
		    public void run() {
		    	SQLiteDatabase writableDatabase = getWritableDatabase();
		    	
		    	writableDatabase.beginTransaction();
		    	try {
		    		for (Affiliation affiliation : affiliations) {
		    			String query = "INSERT OR REPLACE INTO " + affiliations.getTableName() + " VALUES (\""
		    					+ affiliation.getId() + "\", \""
		    					+ affiliation.getPageId() + "\", \""
		    					+ affiliation.getGroupId() + "\", \""
		    					+ affiliation.getOrder() + "\");";
		    			getWritableDatabase().execSQL(query);
		    		}
		    	    writableDatabase.setTransactionSuccessful();
		    	} catch (JSONException e) {
		    		System.out.println("Malformed json in affiliations");
					e.printStackTrace();
				} finally {
		    	    writableDatabase.endTransaction();
		    	}
		    }
		}.start();
	}

	@Override
	public void addAll(final Groups groups) {
		new Thread() {
		    @Override
		    public void run() {
		    	SQLiteDatabase writableDatabase = getWritableDatabase();
		    	
		    	writableDatabase.beginTransaction();
		    	try {
		    		for (Group group : groups) {
		    			String query = "INSERT OR REPLACE INTO " + groups.getTableName() + " VALUES (\""
		    					+ group.getId() + "\", \""
		    					+ group.getName() + "\", \""
		    					+ group.getMenuId() + "\");";
		    			getWritableDatabase().execSQL(query);
		    		}
		    	    writableDatabase.setTransactionSuccessful();
		    	} catch (JSONException e) {
		    		System.out.println("Malformed json in groups");
					e.printStackTrace();
				} finally {
		    	    writableDatabase.endTransaction();
		    	}
		    }
		}.start();
	}

	@Override
	public void addAll(final Menus menus) {
		new Thread() {
		    @Override
		    public void run() {
		    	SQLiteDatabase writableDatabase = getWritableDatabase();
		    	
		    	writableDatabase.beginTransaction();
		    	try {
		    		for (Menu menu : menus) {
		    			String query = "INSERT OR REPLACE INTO " + menus.getTableName() + " VALUES (\""
		    					+ menu.getId() + "\", \""
		    					+ menu.getTitle() + "\", \""
		    					+ menu.getDescription() + "\", \""
		    					+ menu.getThumbImgURL() + "\", \""
		    					+ menu.getImgURL() + "\");";
		    			getWritableDatabase().execSQL(query);
		    		}
		    	    writableDatabase.setTransactionSuccessful();
		    	} catch (JSONException e) {
		    		System.out.println("Malformed json in menus");
					e.printStackTrace();
				} finally {
		    	    writableDatabase.endTransaction();
		    	}
		    }
		}.start();
	}

	@Override
	public void addAll(final Pages pages) {
		new Thread() {
		    @Override
		    public void run() {
		    	SQLiteDatabase writableDatabase = getWritableDatabase();
		    	
		    	writableDatabase.beginTransaction();
		    	try {
		    		for (Page page : pages) {
		    			String query = "INSERT OR REPLACE INTO " + pages.getTableName() + " VALUES (\""
		    					+ page.getId() + "\", \""
		    					+ page.getTitle() + "\", \""
		    					+ page.getSubtitle() + "\", \""
		    					+ page.getContent() + "\", \""
		    					+ page.getMenuId() + "\");";
		    			getWritableDatabase().execSQL(query);
		    		}
		    	    writableDatabase.setTransactionSuccessful();
		    	} catch (JSONException e) {
		    		System.out.println("Malformed json in pages");
					e.printStackTrace();
				} finally {
		    	    writableDatabase.endTransaction();
		    	}
		    }
		}.start();
	}

	@Override
	public void addAll(final Resources resources) {
		new Thread() {
		    @Override
		    public void run() {
		    	SQLiteDatabase writableDatabase = getWritableDatabase();
		    	
		    	writableDatabase.beginTransaction();
		    	try {
		    		for (Resource resource : resources) {
		    			String query = "INSERT OR REPLACE INTO " + resources.getTableName() + " VALUES (\""
		    					+ resource.getId() + "\", \""
		    					+ resource.getTitle() + "\", \""
		    					+ resource.getURL() + "\", \""
		    					+ resource.getDescription() + "\", \""
		    					+ resource.getType() + "\");";
		    			getWritableDatabase().execSQL(query);
		    		}
		    	    writableDatabase.setTransactionSuccessful();
		    	} catch (JSONException e) {
		    		System.out.println("Malformed json in resources");
					e.printStackTrace();
				} finally {
		    	    writableDatabase.endTransaction();
		    	}
		    }
		}.start();
	}

	/**
	 * @param groupId or use -1 if you want to get all pages' menus.
	 */
	@Override
	public Cursor getPagesMenusInGroup(int groupId) {
		String query = "SELECT "
				+ MenusContract.ID + " AS \"_id\", " // Cursor needs _id
				+ MenusContract.TITLE + ", "
				+ MenusContract.DESCRIPTION + ", "
				+ MenusContract.THUMB_IMG_URL + ", "
				+ MenusContract.IMG_URL + ", "
				+ AffiliationsContract.PAGE_ID + ", " // we want to differentiate page_id from _id
				+ AffiliationsContract.ORDER
				+ " FROM "
				+ PagesContract.TABLE_NAME + ", "
				+ MenusContract.TABLE_NAME + ", "
				+ AffiliationsContract.TABLE_NAME
				+ " WHERE "
				+ PagesContract.ID + "=" + AffiliationsContract.PAGE_ID
				+ " AND "
				+ AffiliationsContract.GROUP_ID + "= ?"
				+ " AND "
				+ MenusContract.ID + "=" + PagesContract.MENU_ID
				+ " ORDER BY "
				+ AffiliationsContract.ORDER + ";";
		
		String[] groupIdArgs = (groupId >= 0) ?
				new String[]{String.valueOf(groupId)} : new String[]{"*"};

		return getReadableDatabase().rawQuery(query, groupIdArgs);
	}

	@Override
	public Cursor getAllPagesMenus() {
		return getPagesMenusInGroup(-1);
	}
	
	
	@Override
	public Cursor getPage(int pageId) {
		//TODO
		return null;
	}


}
