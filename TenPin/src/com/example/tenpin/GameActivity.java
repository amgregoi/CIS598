package com.example.tenpin;


import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
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
	
	public void onClick(View v){
		ImageView temp;
		switch(v.getId())
		{
			case R.id.pin1_image:
				temp = (ImageView)findViewById(R.id.pin1_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				break;
			case R.id.pin2_image:
				temp = (ImageView)findViewById(R.id.pin2_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin2", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pin3_image:
				temp = (ImageView)findViewById(R.id.pin3_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin3", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pin4_image:
				temp = (ImageView)findViewById(R.id.pin4_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin4", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pin5_image:
				temp = (ImageView)findViewById(R.id.pin5_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin5", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pin6_image:
				temp = (ImageView)findViewById(R.id.pin6_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin6", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pin7_image:
				temp = (ImageView)findViewById(R.id.pin7_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin7", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pin8_image:
				temp = (ImageView)findViewById(R.id.pin8_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin8", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pin9_image:
				temp = (ImageView)findViewById(R.id.pin9_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin9", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pin10_image:
				temp = (ImageView)findViewById(R.id.pin10_image);
				temp.setImageAlpha(~temp.getImageAlpha());
				Toast.makeText(getApplicationContext(), "pin10", Toast.LENGTH_SHORT).show();
				break;
			case R.id.next_throw_button:
				Toast.makeText(getApplicationContext(), "next!", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}


}
