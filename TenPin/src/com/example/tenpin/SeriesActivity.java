package com.example.tenpin;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SeriesActivity extends DBManagment implements OnItemClickListener{
	ListView gameList; 
	List<Game> games;
	Series s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_series);

		Intent i = getIntent();
		int ids[] = null;
		
		gameList = (ListView)findViewById(R.id.gameListView);
		games = new ArrayList<Game>();
		
		/*
		 * get this instance of selected series
		 */
		Cursor c = database.query(true, "players", new String[] {"name", "type", "object"}, "(type is " + "'Series') AND _id is ?", new String[] {Integer.toString(i.getIntExtra("id", -1))}, null, null, null, null, null);
		if(c.getCount() == 0)
		{
			this.finishActivity(-1);
		}
		while(c.moveToNext())
		{
			s = new Gson().fromJson(c.getString(2), Series.class);
			ids = s.getGameIds();
		}
		
		this.setTitle(s.toString());
					
		/* 
		 * get the three games associated with this series
		 */
		c = database.query(true, "players", new String[] {"name", "type", "object", "_id"}, "(type is " + "'GSeries')",null, null, null, null, null, null);

		if(c.getCount() == 0 || c.getCount() < 3)
		{
			super.finish();
		}
		s.setScore(0);
		while(c.moveToNext())
		{
			String json = c.getString(2);
			int id = c.getInt(3);
			if(id >= ids[0] && id <= ids[2]+1)
			{
				Game g = new Gson().fromJson(json, Game.class);
				games.add(g);
				s.setScore(s.getScore()+g.getScore());
			}
		}
		
		ArrayAdapter<Game> adapter = new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, games);
		gameList.setAdapter(adapter);	
		gameList.setOnItemClickListener(this);
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

	/*
	 * (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 * starts chosen game activity
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void onItemClick(AdapterView parent, View v, int position, long id)
    {
		Intent i = new Intent(this, GameActivity.class);
		i.putExtra("id", games.get(position).getId());
		i.putExtra("name", games.get(position).getName());
    	startActivityForResult(i,1);
    }
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){      
				//nothing special happens on the return of this activity, I added this on the chance I changed the implementation
				System.out.println("AWWWWWWWWW");
				finish();
				startActivity(getIntent());
			}
		}
	}
	
	@Override
	public void onPause()
	{
		Intent i = new Intent();
		database.execSQL("UPDATE players SET object = ? WHERE _id = ?", new Object[] {new Gson().toJson(s), s.getId()});
		setResult(RESULT_OK, i);
		super.onPause();
	}
}
