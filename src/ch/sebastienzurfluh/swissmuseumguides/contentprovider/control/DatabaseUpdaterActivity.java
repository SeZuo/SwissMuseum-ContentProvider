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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(false);

		setContentView(R.layout.activity_update_db);

		mProgress = (ProgressBar) findViewById(R.id.progressBar);
		mProgress.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));

		mProgress.setMax(6);

		final LocalConnector localConnector = new LocalConnector(DatabaseUpdaterActivity.this);
		mProgress.setProgress(++mProgressStatus);


		new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector("http://192.168.50.64/SwissMuseumParcours/CakePHPv2/index.php")
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
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector("http://192.168.50.64/SwissMuseumParcours/CakePHPv2/index.php")
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
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector("http://192.168.50.64/SwissMuseumParcours/CakePHPv2/index.php")
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
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector("http://192.168.50.64/SwissMuseumParcours/CakePHPv2/index.php")
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
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				new RemoteConnector("http://192.168.50.64/SwissMuseumParcours/CakePHPv2/index.php")
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
		}).start();
	}
}
