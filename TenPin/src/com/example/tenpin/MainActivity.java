package com.example.tenpin;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
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

public class MainActivity extends ActionBarActivity implements OnClickListener, OnItemClickListener{

	ListView  playerList;
	ImageButton add_player;
	ImageButton delete_player;
	String user;
	List<Player> players;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		
		playerList = (ListView)findViewById(R.id.playerList);
		players = new ArrayList<Player>();

		players.add(new Player("Andy"));
		players.add(new Player("Shea"));
		
        ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, players);
        playerList.setAdapter(adapter);	               
	    
        add_player = (ImageButton)findViewById(R.id.add_player_button);
    	delete_player = (ImageButton)findViewById(R.id.delete_player_button);    
    	add_player.setOnClickListener(this);
    	delete_player.setOnClickListener(this);
    	playerList.setOnItemClickListener(this);
		
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
    	i.putExtra("players", players.get(position));
    	startActivity(i);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){      
				String result=data.getStringExtra("user");
				//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
				if(result.length() > 0)
				{
					players.add(new Player(result));
				}
			}
		}
    }


	

}
