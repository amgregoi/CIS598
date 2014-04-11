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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class PlayerActivity extends Activity implements OnClickListener, OnItemClickListener{

	ImageButton add_game;
	ImageButton add_series;
	ImageButton statistics;
	ListView recordList;
	List<Record> records;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		recordList = (ListView)findViewById(R.id.player_record_list);
		records = new ArrayList<Record>();
		
		records.add(new Game("new GAme"));
		records.add(new Series("NEW SERIES!"));
		
		ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, records);
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
		switch(v.getId())
		{
			case R.id.add_game_button:
				Toast.makeText(getApplicationContext(), "GAME", Toast.LENGTH_SHORT).show();
				break;
			case R.id.add_series_button:
				Toast.makeText(getApplicationContext(), "SERIES", Toast.LENGTH_SHORT).show();
				break;
			case R.id.stats_button:
				Toast.makeText(getApplicationContext(), "STATS", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}
	
	@Override
    public void onItemClick(AdapterView parent, View v, int position, long id)
    {
    	//startActivity(new Intent(this, GameActivity.class));
    	
    	
    	Record temp = records.get(position);
    	if(temp instanceof Game)
    	{
    		Intent i = new Intent(this, GameActivity.class);
        	i.putExtra("game", records.get(position));
        	startActivity(i);
    	}
    	else if(temp instanceof Series)
    	{
    		Intent i = new Intent(this, SeriesActivity.class);
        	i.putExtra("series", records.get(position));
        	startActivity(i);
    	}
    }

}
