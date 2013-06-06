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

package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.interfaces;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Affiliations;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Groups;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Menus;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Pages;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Resources;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.patterns.AsyncCallback;

/**
 * This connector lets the user get data from the database, table by table.
 *
 * @author Sebastien Zurfluh
 *
 */
public interface BasicInputConnector {
	/**
	 * Get the content of the {@code affiliations} table in the DB.
	 * @param callback tells what to do with the result
	 */
	void getAffiliations(AsyncCallback<Affiliations> callback);
	
	/**
	 * Get the content of the {@code groups} table in the DB.
	 * @param callback tells what to do with the result
	 */
	void getGroups(AsyncCallback<Groups> callback);
	
	/**
	 * Get the content of the {@code menus} table in the DB.
	 * @param callback tells what to do with the result
	 */
	void getMenus(AsyncCallback<Menus> callback);
	
	/**
	 * Get the content of the {@code pages} table in the DB.
	 * @param callback tells what to do with the result
	 */
	void getPages(AsyncCallback<Pages> callback);
	
	/**
	 * Get the content of the {@code resources} table in the DB.
	 * @param callback tells what to do with the result
	 */
	void getResources(AsyncCallback<Resources> callback);
}
