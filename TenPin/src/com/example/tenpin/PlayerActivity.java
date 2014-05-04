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
				//System.out.println("NOOO - PLAYER NAME: " + player.getName()+"  game owner: "+g.getOwner() + "   bool: "+player.getName().equals(g.getOwner()));
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
				Game g = new Game("(G) "+dateFormat+" "+Integer.toString(object_id), player.getName());
				g.setId(object_id);
				records.add(g);				
				player.setPlayerRecordList(records);
				addNewRecordDB(g.getName(), new Gson().toJson(g), "Game", object_id);
				break;
			case R.id.add_series_button:
				Toast.makeText(getApplicationContext(), "SERIES", Toast.LENGTH_SHORT).show();
				dateFormat = new SimpleDateFormat("dd-MM-yy").format(now);
				Series s = new Series("(S) "+dateFormat+" " + Integer.toString(object_id), player.getName());
				s.setId(object_id);
				records.add(s);
				player.setPlayerRecordList(records);
				addNewRecordDB(s.getName(), new Gson().toJson(s), "Series", object_id);
				break;
			case R.id.stats_button:
				//Toast.makeText(getApplicationContext(), "STATS", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		adapter.notifyDataSetChanged();
	}
	
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
        	i.putExtra("series", records.get(position));
        	adapter.notifyDataSetChanged();
        	startActivityForResult(i,1);
    	}
    	
    }
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){      
				
				/*String result3 = data.getStringExtra("name");
				String result2 = data.getStringExtra("object");
				String result = data.getStringExtra("type");

				System.out.println("NOOO - RESULT1: " + result);
				System.out.println("NOOO - RESULT2: : " + result2);
				System.out.println("NOOO - RESULT3: : " + result3);
				if(result != null)
				{

					ContentValues cv = new ContentValues();
					cv.put("object", result2);
					database.update("players", cv, "type is " + result + "AND object is " + result2 + " AND name is "+ result3, null);
					System.out.println("NOOO: "+ result3 + " updated fine");
			        //records.set(position, result);
			        //adapter.notifyDataSetChanged();
				}*/
			}
		}
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

