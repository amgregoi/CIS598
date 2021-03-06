package com.example.tenpin;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class PlayerActivity extends DBManagment implements OnClickListener, OnItemClickListener{

	private ImageButton add_game;
	private ImageButton add_series;
	private ImageButton statistics;
	private ListView recordList;
	private static List<Record> records;
	private Player player;
	private ArrayAdapter<Record> adapter;
	Date now = new Date();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		
		Intent i = getIntent();
		player = i.getParcelableExtra("player");
		getActionBar().setTitle(player.toString());
		records = new ArrayList<Record>();
		
		
		
		Cursor c = database.query(true, "players", new String[] {"name", "type", "object"}, "(type is " + "'Game' OR type is " + "'Series')", null, null, null, null, null, null);
		while(c.moveToNext())
		{
			String json = c.getString(2);
			String type = c.getString(1);
			if(type.equals("Game"))
			{
				Game g = new Gson().fromJson(json, Game.class);
				if(player.getName().equals(g.getOwner()))
					records.add(g);
			}
			else
			{
				Series s = new Gson().fromJson(json, Series.class);
				if(player.getName().equals(s.getOwner()))
					records.add(s);
			}
		}
		
		recordList = (ListView)findViewById(R.id.player_record_list);
		adapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, records);
        recordList.setAdapter(adapter);	
		
		
        /* Init objects and listeners */
		add_game = (ImageButton)findViewById(R.id.add_game_button);
		add_series = (ImageButton)findViewById(R.id.add_series_button);
		statistics = (ImageButton)findViewById(R.id.stats_button);
		add_game.setOnClickListener(this);
		add_series.setOnClickListener(this);
		statistics.setOnClickListener(this);
		recordList.setOnItemClickListener(this);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player, menu);
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
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * Checks button clicks and carries out actions
	 */
	@Override
	public void onClick(View v)
	{
		String dateFormat;
		switch(v.getId())
		{
			case R.id.add_game_button:
				object_id++;
				Toast.makeText(getApplicationContext(), "GAME", Toast.LENGTH_SHORT).show();
				dateFormat = new SimpleDateFormat("dd-MM-yy").format(now);
				Game g = new Game("(G) "+dateFormat, player.getName());
				g.setId(object_id);
				records.add(g);				
				player.setPlayerRecordList(records);
				addNewRecordDB(g.getName(), new Gson().toJson(g), "Game", object_id);
				break;
			case R.id.add_series_button:
				object_id++;
				Toast.makeText(getApplicationContext(), "Series", Toast.LENGTH_SHORT).show();
				dateFormat = new SimpleDateFormat("dd-MM-yy").format(now);
				Series s = new Series("(S) " + dateFormat, player.getName());
				s.setId(object_id);
				records.add(s);				
				player.setPlayerRecordList(records);
				s.setGameIds(new int[] {object_id+1, object_id+2, object_id+3});
				//object_id++;
				
				/*
				 * Fix record constructor to take in id rather than set it manually after initialization
				 */
				/*
				addNewRecordDB(s.getName(), new Gson().toJson(new Game("(G) "+dateFormat+" "+Integer.toString(object_id), player.getName())), "GSeries", ++object_id);
				addNewRecordDB(s.getName(), new Gson().toJson(new Game("(G) "+dateFormat+" "+Integer.toString(object_id), player.getName())), "GSeries", ++object_id);
				addNewRecordDB(s.getName(), new Gson().toJson(new Game("(G) "+dateFormat+" "+Integer.toString(object_id), player.getName())), "GSeries", ++object_id);
				*/
				
				
				Game g1 = new Game("(G) "+dateFormat+" "+Integer.toString(object_id+1), player.getName());
				g1.setId(object_id+1);
				Game g2 = new Game("(G) "+dateFormat+" "+Integer.toString(object_id+2), player.getName());
				g2.setId(object_id+2);
				Game g3 = new Game("(G) "+dateFormat+" "+Integer.toString(object_id+3), player.getName());
				g3.setId(object_id+3);
				
				
				addNewRecordDB(s.getName(), new Gson().toJson(g1), "GSeries", ++object_id);
				addNewRecordDB(s.getName(), new Gson().toJson(g2), "GSeries", ++object_id);
				addNewRecordDB(s.getName(), new Gson().toJson(g3), "GSeries", ++object_id);

				addNewRecordDB(s.getName(), new Gson().toJson(s), "Series", s.getId());
				break;
			case R.id.stats_button:
				//Toast.makeText(getApplicationContext(), "STATS", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		adapter.notifyDataSetChanged();
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 * checks for game/series chosen in listview and starts appropriate activity
	 */
	@SuppressWarnings("rawtypes")
	@Override
    public void onItemClick(AdapterView parent, View v, int position, long id)
    {    	
    	Record temp = records.get(position);
    	if(temp instanceof Game)
    	{
    		Intent i = new Intent(this, GameActivity.class);
    		i.putExtra("id", records.get(position).getId());
    		i.putExtra("name", records.get(position).getName());
    		adapter.notifyDataSetChanged();
        	startActivityForResult(i,1);
    	}
    	else if(temp instanceof Series)
    	{  		
    		Intent i = new Intent(this, SeriesActivity.class);
        	//i.putExtra("series", records.get(position));
    		i.putExtra("id", records.get(position).getId());
        	adapter.notifyDataSetChanged();
        	startActivityForResult(i,1);
    	}
    	
    }
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){      
				//nothing special happens on the return of this activity, I added this on the chance I changed the implementation
				finish();
				startActivity(getIntent());
			}
		}
	}
	
	/*
	 * adds game/series object to the database
	 */
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

