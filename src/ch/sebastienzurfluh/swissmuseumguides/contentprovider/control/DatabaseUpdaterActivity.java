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

package ch.sebastienzurfluh.swissmuseumguides.contentprovider.control;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.R;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.connectors.LocalConnector;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.connectors.RemoteConnector;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Affiliations;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Groups;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Menus;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Pages;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.model.io.structure.impl.Resources;
import ch.sebastienzurfluh.swissmuseumguides.contentprovider.patterns.AsyncCallback;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.app.Activity;

public class DatabaseUpdaterActivity extends Activity {

	private ProgressBar mProgress;
	private int mProgressStatus = 0;
	
	private static String domain = "http://sebastienzurfluh.dreamhosters.com/" +
			"swissmuseumbooklets/cakePHPv2/index.php";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		

		setContentView(R.layout.activity_update_db);

		mProgress = (ProgressBar) findViewById(R.id.progressBar);
		mProgress.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));

		mProgress.setMax(6);

		final LocalConnector localConnector = new LocalConnector(DatabaseUpdaterActivity.this);
		mProgress.setProgress(++mProgressStatus);


		Thread downloadAffiliations = new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector(domain)
						.getAffiliations(new AsyncCallback<Affiliations>() {

					@Override
					public void onFailure(Throwable caught) {
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}

					@Override
					public void onSuccess(Affiliations result) {
						localConnector.addAll(result);
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
						
					}
				});
			}
		});
		downloadAffiliations.start();
		

		Thread downloadGroups = new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector(domain)
						.getGroups(new AsyncCallback<Groups>() {

					@Override
					public void onFailure(Throwable caught) {
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}

					@Override
					public void onSuccess(Groups result) {
						localConnector.addAll(result);
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}
				});
			}
		});
		downloadGroups.start();
		

		Thread downloadMenus = new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector(domain)
						.getMenus(new AsyncCallback<Menus>() {

					@Override
					public void onFailure(Throwable caught) {
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}

					@Override
					public void onSuccess(Menus result) {
						localConnector.addAll(result);
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}
				});
			}
		});
		downloadMenus.start();
		

		Thread downloadPages = new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector(domain)
						.getPages(new AsyncCallback<Pages>() {

					@Override
					public void onFailure(Throwable caught) {
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}

					@Override
					public void onSuccess(Pages result) {
						localConnector.addAll(result);
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}
				});
			}
		});
		downloadPages.start();
		

		Thread downloadResources = new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector(domain)
						.getResources(new AsyncCallback<Resources>() {

					@Override
					public void onFailure(Throwable caught) {
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}

					@Override
					public void onSuccess(Resources result) {
						localConnector.addAll(result);
						mProgress.setProgress(++mProgressStatus);
						Thread.currentThread().interrupt();
					}
				});
			}
		});
		downloadResources.start();
		
	}

    @Override
    protected void onStop() {
        super.onStop();
    }
}
