package com.example.earthnetworkspics;

import java.util.List;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends ActionBarActivity {

	// I use an AdapterView because it'll work for both GridView and ListView

	AdapterView aView;
	ArrayAdapter sa;
	final String endURL = "http://entest-webappslab.rhcloud.com/data/data.json";
	ActionBarActivity aba;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		aView = (AdapterView) findViewById(R.id.aView);
		aba = this;
		new setList().execute(null, null, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class setList extends AsyncTask<Void, Void, List<Pic>> {

		@Override
		protected List<Pic> doInBackground(Void... v) {
			return PicJSONParser.getListFromString(endURL);

		}

		@Override
		protected void onPostExecute(List<Pic> lp) {
			MyAdapter ma = new MyAdapter(getApplicationContext(), lp);
			aView.setAdapter(ma);

			aView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapter, View view,
						int position, long arg) {
					MyAdapter ma = (MyAdapter)adapter.getAdapter();
					Pic pic = ma.getItem(position);
					final Dialog dialog = new Dialog(aba);
					dialog.setContentView(R.layout.custom_dialog);
					dialog.setTitle(pic.getName());
					TextView text = (TextView) dialog.findViewById(R.id.text);
					text.setText(pic.getDescription());
					ImageView image = (ImageView) dialog
							.findViewById(R.id.image);
					Picasso.with(aba).load(pic.getUrl()).into(image);

					Button dialogButton = (Button) dialog
							.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					});

					dialog.show();

				}
			});
		}
	}
}