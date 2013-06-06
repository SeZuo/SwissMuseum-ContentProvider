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

/**
 * This connector lets the user store data in the database.
 *
 * @author Sebastien Zurfluh
 *
 */
public interface OConnector {
	/**
	 * Add {@code affiliations} in the DB.
	 */
	void addAll(Affiliations affiliations);
	
	/**
	 * Add {@code groups} in the DB.
	 */
	void addAll(Groups groups);
	
	/**
	 * Add {@code menus} in the DB.
	 */
	void addAll(Menus menus);
	
	/**
	 * Add {@code pages} in the DB.
	 */
	void addAll(Pages pages);
	
	/**
	 * Add {@code resources} in the DB.
	 */
	void addAll(Resources resources);
}