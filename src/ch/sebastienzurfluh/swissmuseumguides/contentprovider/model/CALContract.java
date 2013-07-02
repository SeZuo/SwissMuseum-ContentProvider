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

/**
 * This is the structure of the data logic.
 * 
 * This contract is exposed to the content provider client and should be used to query it.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public abstract class CALContract {
	/**
	 * Each line in the Groups table contains groups of pages.
	 * Through this table you can access the group's data, and it's menu.
	 */
	public static abstract class Groups {
		public static final String TABLE_NAME = "groups";

		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_NAME = "name";
		
		// from the db affiliations' table
		public static final String COLUMN_NAME_ORDER = "order";
		
		// from the db menus' table
		public static final String COLUMN_NAME_MENU_TITLE = "title";
		public static final String COLUMN_NAME_MENU_DESCRIPTION = "description";
		public static final String COLUMN_NAME_MENU_THUMB_IMG_URL = "thumb_img_url";
		public static final String COLUMN_NAME_MENU_IMG_URL = "img_url";
	}
	
	/**
	 * Each line in the Pages table represents a page.
	 * Through this table you can access the page's data, and it's menu.
	 */
	public static abstract class Pages {
		public static final String TABLE_NAME = "pages";
		
		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_SUBTITLE = "subtitle";
		public static final String COLUMN_NAME_CONTENT = "content";
		
		// from the db affiliations' table
		public static final String COLUMN_NAME_ORDER = "order";
				
		// from the db menus' table
		public static final String COLUMN_NAME_MENU_TITLE = "title";
		public static final String COLUMN_NAME_MENU_DESCRIPTION = "description";
		public static final String COLUMN_NAME_MENU_THUMB_IMG_URL = "thumb_img_url";
		public static final String COLUMN_NAME_MENU_IMG_URL = "img_url";
	}

	public static abstract class Resources {
		public static final String TABLE_NAME = "resources";

		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_URL = "url";
		public static final String COLUMN_NAME_DESCRIPTION = "description";
		public static final String COLUMN_NAME_TYPE = "type";
	}
}
