package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure;

import java.util.Collection;

/**
 * Super interface of the other table classes
 *
 *
 * @author Sebastien Zurfluh
 *
 */
public interface Table<RowGeneric extends Row> extends Collection<RowGeneric> {
	public String getRowElement();
	public String getTableName();
}