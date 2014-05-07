package com.example.tenpin;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

public class SeriesActivity extends DBManagment implements OnClickListener, OnItemClickListener{
	ListView gameList; 
	List<Game> games;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_series);

		Intent i = getIntent();
		Series s;
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
					
		/*
		 * get the three games associated with this series
		 */
		//c = database.query(true, "players", new String[] {"name", "type", "object"}, "(type is " + "'GSeries') AND _id >= ? AND _id <= ?", new String[] {Integer.toString(ids[0]), Integer.toString(ids[2])}, null, null, null, null, null);
		c = database.query(true, "players", new String[] {"name", "type", "object", "_id"}, "(type is " + "'GSeries')",null, null, null, null, null, null);

		if(c.getCount() == 0 || c.getCount() < 3)
		{
			super.finish();
		}
		while(c.moveToNext())
		{
			String json = c.getString(2);
			String type = c.getString(1);
			int id = c.getInt(3);
			if(id >= ids[0] && id <= ids[2]+1)
				games.add(new Gson().fromJson(json, Game.class));	
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
	@Override
	public void onItemClick(AdapterView parent, View v, int position, long id)
    {
		Intent i = new Intent(this, GameActivity.class);
		i.putExtra("id", games.get(position).getId());
		i.putExtra("name", games.get(position).getName());
    	startActivityForResult(i,1);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	
    private void addNewRecordDB(String name, String obj, String obj_type, int id)
    {
    	ContentValues cv = new ContentValues();
		cv.put("_id", id);
		cv.put("name", name);
		cv.put("object", obj);
		cv.put("type", obj_type);
		
		database.insert("players", "_id", cv);		
    }
}
