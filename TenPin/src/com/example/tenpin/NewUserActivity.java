package com.example.tenpin;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewUserActivity extends Activity implements OnClickListener{

	Button done;
	EditText input;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		getActionBar().setTitle("Add New User");

		input = (EditText)findViewById(R.id.user_input);
		done = (Button)findViewById(R.id.done_button);
		done.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_user, menu);
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
	 * click listener for done button
	 */
	@Override
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    		case R.id.done_button:
    			
    			Intent i = new Intent();
    			i.putExtra("user", input.getText().toString());
    			setResult(RESULT_OK, i);
    			this.finish();
    			break;
    		default:
    			break;
    	}
    	
    }

}
