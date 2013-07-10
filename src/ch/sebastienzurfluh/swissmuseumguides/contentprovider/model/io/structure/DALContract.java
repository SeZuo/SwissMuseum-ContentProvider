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

import android.provider.BaseColumns;

/**
 * This is the structure of the database, table by table.
 * 
 * This contract is used by the local SQLite3 implementation, and by the remote MySQL
 * implementation.
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public abstract class DALContract {
	private static final String POINT = ".";
	
	public static abstract class AffiliationsContract implements BaseColumns {
		public static final String TABLE_NAME = "affiliations";
		/**
		 * This is the name of a row element according to cake... this should not be part of
		 * the contract but cake's queries are tricky.
		 */
		public static final String ROW_ELEMENT_NAME = "affiliations";
		public static final String COLUMN_NAME_ID = TABLE_NAME + POINT + "id";
		public static final String COLUMN_NAME_PAGE_ID = TABLE_NAME + POINT + "page_id";
		public static final String COLUMN_NAME_GROUP_ID = TABLE_NAME + POINT + "group_id";
		public static final String COLUMN_NAME_ORDER = TABLE_NAME + POINT + "order";
	}
	
	public static abstract class GroupsContract implements BaseColumns {
		public static final String TABLE_NAME = "groups";
		/**
		 * This is the name of a row element according to cake... this should not be part of
		 * the contract but cake's queries are tricky.
		 */
		public static final String ROW_ELEMENT_NAME = "Group";
		public static final String COLUMN_NAME_ID = TABLE_NAME + POINT + "id";
		public static final String COLUMN_NAME_NAME = TABLE_NAME + POINT + "name";
		public static final String COLUMN_NAME_MENU_ID = TABLE_NAME + POINT + "menu_id";
	}
	
	public static abstract class MenusContract implements BaseColumns {
		public static final String TABLE_NAME = "menus";
		/**
		 * This is the name of a row element according to cake... this should not be part of
		 * the contract but cake's queries are tricky.
		 */
		public static final String ROW_ELEMENT_NAME = "Menu";
		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_DESCRIPTION = "description";
		public static final String COLUMN_NAME_THUMB_IMG_URL = "thumb_img_url";
		public static final String COLUMN_NAME_IMG_URL = "img_url";
	}
	
	public static abstract class PagesContract implements BaseColumns {
		/**
		 *  Cake CANNOT use the 'pages' name for a table. It is called 'page_elements' instead.
		 */
		public static final String TABLE_NAME = "page_elements";
		/**
		 * This is the name of a row element according to cake... this should not be part of
		 * the contract but cake's queries are tricky.
		 */
		public static final String ROW_ELEMENT_NAME = "PageElement";
		public static final String COLUMN_NAME_ID = TABLE_NAME + POINT + "id";
		public static final String COLUMN_NAME_TITLE = TABLE_NAME + POINT + "title";
		public static final String COLUMN_NAME_SUBTITLE = TABLE_NAME + POINT + "subtitle";
		public static final String COLUMN_NAME_CONTENT = TABLE_NAME + POINT + "content";
		public static final String COLUMN_NAME_MENU_ID = TABLE_NAME + POINT + "menu_id";
	}

	public static abstract class ResourcesContract implements BaseColumns {
		public static final String TABLE_NAME = "resources";
		/**
		 * This is the name of a row element according to cake... this should not be part of
		 * the contract but cake's queries are tricky.
		 */
		public static final String ROW_ELEMENT_NAME = "Resource";
		public static final String COLUMN_NAME_ID = TABLE_NAME + POINT + "id";
		public static final String COLUMN_NAME_TITLE = TABLE_NAME + POINT + "title";
		public static final String COLUMN_NAME_URL = TABLE_NAME + POINT + "url";
		public static final String COLUMN_NAME_DESCRIPTION = TABLE_NAME + POINT + "description";
		public static final String COLUMN_NAME_TYPE = TABLE_NAME + POINT + "type";
	}
}
