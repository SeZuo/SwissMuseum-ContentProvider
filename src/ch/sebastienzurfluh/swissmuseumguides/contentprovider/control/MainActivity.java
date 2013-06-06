package ch.sebastienzurfluh.swissmuseumguides.contentprovider.control;

import ch.sebastienzurfluh.swissmuseumguides.contentprovider.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Updates (creates if needed) the database.
	 * @param view because we have to (do we?)
	 */
	public void update(View view) {
		findViewById(R.id.updateButton).setEnabled(false);
		
		Intent updateTheDB = new Intent(this, DatabaseUpdaterActivity.class);
		startActivity(updateTheDB);
	}

}
