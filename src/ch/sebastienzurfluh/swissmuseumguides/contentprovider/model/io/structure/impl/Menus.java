package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl;

import org.json.JSONException;
import org.json.JSONObject;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.RowFactory;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.TableAbstract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.MenusContract;

public class Menus extends TableAbstract<Menu> {
	private static final long serialVersionUID = 1L;

	public Menus(String tableJSON) throws JSONException {
		super(tableJSON);
	}

	@Override
	public String getRowElement() {
		return MenusContract.ROW_ELEMENT_NAME;
	}
	
	@Override
	public String getTableName() {
		return MenusContract.TABLE_NAME;
	}

	@Override
	public RowFactory<Menu> getRowFactory() {
		return new RowFactory<Menu>(){
			@Override
			public Menu make(JSONObject jsonObject) throws JSONException {
				return new Menu(jsonObject.toString());
			}
		};
	}
}
