package com.example.tenpin;


import android.app.Activity;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class DBManagment extends Activity {
	
	 protected SQLiteDatabase database;

	 
	 
     private static final String DATABASE_NAME = "Test_DB";   
     private static final String DATABASE_TABLE_NAME = "players";   
     //private static final int DATABASE_VERSION = 1;    
     public static int object_id = -1;
     //  Table1 properties   
   public static final String KEY_ROWID = "_id";   
       public static final String KEY_NAME = "name";   
       public static final String object_type = "type";  
       public static final String object = "object";   
   // Create Script   
   private static final String DATABASE_CREATE_PLAYER = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_NAME + "( " + KEY_ROWID + " INTEGER, " + KEY_NAME + " TEXT, " + object_type + " TEXT, " + object + " TEXT)";      
	 
	 
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbmanagment);
			
		database = this.openOrCreateDatabase(DATABASE_NAME, Context.MODE_WORLD_WRITEABLE, null);
		database.execSQL(DATABASE_CREATE_PLAYER);
		object_id = (int) DatabaseUtils.queryNumEntries(database, "players");
	}
	
    public void onUpgrade(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
    } 
	
	@Override
	protected void onPause(){
		//database.close();
		super.onPause();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dbmanagment, menu);
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

}
