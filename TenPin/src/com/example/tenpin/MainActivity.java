/*
 * 
 * 
 * MAKE ANOTHER TABLE FOR GAMES AND USE PLAYER NAMES AS A KEY TO LINK THE TWO TABLES
 * 
 * 
 * 
 */


package com.example.tenpin;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends DBManagment implements OnClickListener, OnItemClickListener{

	ListView  playerList;
	ImageButton add_player;
	ImageButton delete_player;
	String user;
	List<Player> players;
	ArrayAdapter<Player> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		
		playerList = (ListView)findViewById(R.id.playerList);
		players = new ArrayList<Player>();

		
		Cursor c = database.query(true, "players", new String[] {"name", "type", "object"}, "type is " + "'Player'", null, null, null, null, null, null);
		
		while(c.moveToNext())
		{
			String json = c.getString(2);
			players.add(new Gson().fromJson(json, Player.class));
			//players.add(new Player(c.getString(1)));
		}
		
		
		//players.add(new Player("Andy"));
		//players.add(new Player("Shea"));
		
        adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, players);
        playerList.setAdapter(adapter);	               
	    
        add_player = (ImageButton)findViewById(R.id.add_player_button);
    	delete_player = (ImageButton)findViewById(R.id.delete_player_button);    
    	add_player.setOnClickListener(this);
    	delete_player.setOnClickListener(this);
    	playerList.setOnItemClickListener(this);
		
	}
	
	@Override 
	protected void onPause()
	{
		super.onPause();
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

    @Override
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    		case R.id.add_player_button:
    			startActivityForResult(new Intent(this, NewUserActivity.class), 1);
    			break;
    		case R.id.delete_player_button:
    			onUpgrade(database);		//temporary clean database
    			players.clear();
    			adapter.notifyDataSetChanged();
    			//serialPlayers();
    			break;
    		default:
    			break;
    	}
    }
    
    
    @Override
    public void onItemClick(AdapterView parent, View v, int position, long id)
    {
    	Intent i = new Intent(this, PlayerActivity.class);
    	i.putExtra("player", players.get(position));
    	startActivity(i);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){      
				String result=data.getStringExtra("user");
				//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
				if(result.length() > 0)
				{
						Player p = new Player(result);
						addNewUserDB(result, new Gson().toJson(p, Player.class), "Player");
						players.add(p);
						adapter.notifyDataSetChanged();
						//players.add(new Player(result));
				}
			}
		}
		adapter.notifyDataSetChanged();
    }
    
    private void addNewUserDB(String name, String obj, String obj_type)
    {
    	ContentValues cv = new ContentValues();
		cv.put("_id", object_id++);
		cv.put("name", name);
		cv.put("object", obj);
		cv.put("type", obj_type);
		
		database.insert("players", "name", cv);
		
		//Cursor c = database.query(true, "players", new String[] {name}, null, null, null, null, null, null, null);
		
    }


	

}
