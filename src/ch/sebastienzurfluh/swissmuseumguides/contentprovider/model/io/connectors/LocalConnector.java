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

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.interfaces.OConnector;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.AffiliationsContract;
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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * When asked, triggers
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public class LocalConnector extends SQLiteOpenHelper implements OConnector {
	
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
    		, "CREATE TABLE IF NOT EXISTS pages ("
    		+ "  id INTEGER PRIMARY KEY,"
    		+ "  title TEXT NOT NULL,"
    		+ "  subtitle TEXT NOT NULL,"
    		+ "  content text NOT NULL,"
    		+ "  menu_id INTEGER NOT NULL"
    		+ ");"
    		, "CREATE TABLE IF NOT EXISTS resources ("
    		+ "  id INTEGER PRIMARY KEY,"
    		+ "  title TEXT NOT NULL,"
    		+ "  url TEXT NOT NULL,"
    		+ "  description TEXT NOT NULL,"
    		+ "  type TEXT NOT NULL"
    		+ ");"
    		, "CREATE INDEX IF NOT EXISTS pages_menu_id ON pages (menu_id);"
    		, "CREATE INDEX IF NOT EXISTS groups_menu_id ON groups (menu_id);"};

	private Context context = null;
	
	
	public LocalConnector(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("LocalConnector: onCreate");
		
		for (String createTableStatement : CREATE_TABLES) {
			db.execSQL(createTableStatement);
		}
		
		Toast.makeText(context, "Database updated.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
		    			String query = "INSERT INTO " + affiliations.getRowElement() + " VALUES ("
		    					+ affiliation.getId() + ", "
		    					+ affiliation.getPageId() + ", "
		    					+ affiliation.getGroupId() + ", "
		    					+ affiliation.getOrder() + ");";
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
		    			String query = "INSERT INTO " + groups.getRowElement() + " VALUES ("
		    					+ group.getId() + ", "
		    					+ group.getName() + ", "
		    					+ group.getMenuId() + ");";
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
		    			String query = "INSERT INTO " + menus.getRowElement() + " VALUES ("
		    					+ menu.getId() + ", "
		    					+ menu.getTitle() + ", "
		    					+ menu.getDescription() + ", "
		    					+ menu.getImgURL() + ", "
		    					+ menu.getThumbImgURL() + ");";
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
		    			String query = "INSERT INTO " + pages.getRowElement() + " VALUES ("
		    					+ page.getId() + ", "
		    					+ page.getTitle() + ", "
		    					+ page.getSubtitle() + ", "
		    					+ page.getImgURL() + ");";
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
		    			String query = "INSERT INTO " + resources.getRowElement() + " VALUES ("
		    					+ resource.getId() + ", "
		    					+ resource.getTitle() + ", "
		    					+ resource.getDescription() + ", "
		    					+ resource.getURL() + ", "
		    					+ resource.getType() + ");";
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

}
