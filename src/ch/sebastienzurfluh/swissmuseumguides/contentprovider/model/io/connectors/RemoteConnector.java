/*
 * Copyright 2012-2013 Sebastien Zurfluh
 * 
 * This file is part of "Parcours".
 * 
 * "Parcours" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "Parcours" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with "Parcours".  If not, see <http://www.gnu.org/licenses/>.
 */

package ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.connectors;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.interfaces.BasicInputConnector;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.AffiliationsContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.GroupsContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.MenusContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.PagesContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.DALContract.ResourcesContract;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Affiliations;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Groups;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Menus;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Pages;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Resources;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.patterns.AsyncCallback;

/**
 * Connects to the Cake back-end in order to get the remote DB content.
 *
 * @author Sebastien Zurfluh
 *
 */
public class RemoteConnector extends AsyncTask<URI, Integer, String> implements BasicInputConnector {
	private String cakePath;
	private final static String CAKE_SUFFIX = ".json";
	private final static String CAKE_ARGS_SEPARATOR = "/";
	
	private AndroidHttpClient client;

	/**
	 * This callback will be called when the asynchronous task is finished.
	 * See {@code onPostExecute()}
	 */
	private AsyncCallback<String> asyncCallback;
	

	/**
	 * Retrieves data from a default remote location using a defined JSON format.
	 */
	public RemoteConnector() {
		this("http://www.sebastienzurfluh.ch/swissmuseumbooklets/cakePHPv2/index.php");
	}
	
	/**
	 * Retrieves data from the given remote location using a defined JSON format.
	 * (This constructor is for testing purpose as we need only one source, and one connector
	 * object.)
	 * @param cakePath the location of the remote content
	 */
	public RemoteConnector(String cakePath) {
		this.cakePath = cakePath;
	}
	
	
	/**
	 * Setup the requests for the remote cake PHP back-end.
	 * Those requests will be handled in background by {@link #doInBackground}.
	 * 
	 * @param requestString for cake php's API.
	 * @param asyncCallback the resulting JSON string
	 */
	public void remoteRequest (
			String requestString, final AsyncCallback<String> asyncCallback) {
		
		this.asyncCallback = asyncCallback;
		
		URI requestURI;
		try {
			requestURI = new URI(cakePath + CAKE_ARGS_SEPARATOR + requestString + CAKE_SUFFIX);
			execute(requestURI);
		} catch (URISyntaxException e) {
			System.out.println("RemoteConnector: sendRequest: request URI is wrong.");
			
			asyncCallback.onFailure(e);
		}
	}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        client = AndroidHttpClient.newInstance("Android");
    }

    @Override
	protected String doInBackground(URI... params) {
		URI url = params[0];
		StringBuilder builder = new StringBuilder();
		
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line).append("\n");
				}
			} else {
				// handle "error loading data"
			}
			entity.consumeContent();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			// handle "error connecting to the server"
		}
		return builder.toString();
	}
	
	@Override
    protected void onPostExecute(String result) {
		if(result != null)
			asyncCallback.onSuccess(result);
		asyncCallback.onFailure(new Throwable("Data result is empty"));
        client.close();
        client = null;
    }

	

	@Override
	public void getAffiliations(final AsyncCallback<Affiliations> asyncCallback) {
		remoteRequest(AffiliationsContract.TABLE_NAME + CAKE_ARGS_SEPARATOR + "listAll",
				
				new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				try {
					asyncCallback.onSuccess(new Affiliations(result));
				} catch (JSONException e) {
					onFailure(e);
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				asyncCallback.onFailure(caught);
			}
		});
	}

	@Override
	public void getGroups(final AsyncCallback<Groups> asyncCallback) {
		remoteRequest(GroupsContract.TABLE_NAME + CAKE_ARGS_SEPARATOR + "listAll",
				
				new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				try {
					asyncCallback.onSuccess(new Groups(result));
				} catch (JSONException e) {
					onFailure(e);
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				asyncCallback.onFailure(caught);
			}
		});
	}

	@Override
	public void getMenus(final AsyncCallback<Menus> asyncCallback) {
		remoteRequest(MenusContract.TABLE_NAME + CAKE_ARGS_SEPARATOR + "listAll",
				
				new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				try {
					asyncCallback.onSuccess(new Menus(result));
				} catch (JSONException e) {
					onFailure(e);
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				asyncCallback.onFailure(caught);
			}
		});
	}

	@Override
	public void getPages(final AsyncCallback<Pages> asyncCallback) {
		remoteRequest(PagesContract.TABLE_NAME + CAKE_ARGS_SEPARATOR + "listAll",
				
				new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				try {
					asyncCallback.onSuccess(new Pages(result));
				} catch (JSONException e) {
					onFailure(e);
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				asyncCallback.onFailure(caught);
			}
		});
	}

	@Override
	public void getResources(final AsyncCallback<Resources> asyncCallback) {
		remoteRequest(ResourcesContract.TABLE_NAME + CAKE_ARGS_SEPARATOR + "listAll",
				
				new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				try {
					asyncCallback.onSuccess(new Resources(result));
				} catch (JSONException e) {
					onFailure(e);
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				asyncCallback.onFailure(caught);
			}
		});
	}	
}
