package com.example.tenpin;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class SeriesActivity extends Activity implements OnClickListener, OnItemClickListener{
	ListView gameList; 
	List<Game> games;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_series);

		Intent i = getIntent();
		Series s = i.getParcelableExtra("series");
		
		gameList = (ListView)findViewById(R.id.gameListView);
		games = new ArrayList<Game>();
		
		games.add(new Game("Game 1", s.getOwner()));
		games.add(new Game("Game 2", s.getOwner()));
		games.add(new Game("Game 3", s.getOwner()));
		
		ArrayAdapter<Game> adapter = new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, games);
		gameList.setAdapter(adapter);	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.series, menu);
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

	@Override
	public void onItemClick(AdapterView parent, View v, int position, long id)
    {
    	Intent i = new Intent(this, PlayerActivity.class);
    	i.putExtra("players", games.get(position));
    	startActivity(i);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
