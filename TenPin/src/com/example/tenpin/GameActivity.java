package com.example.tenpin;

/*
 * 
 * CHANGE QUERY TO USE ID RATHER THAN NAME  *** IMPORTANT
 * 
 * 
 */
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;


@SuppressLint("NewApi")
public class GameActivity extends DBManagment {
	InputMethodManager imm;
	int activeFrame = 0, ballThrown = 0;
	char[][] pinLayout;
	String[][] scoreSheet;
	String[] frameScore;
	ImageView cur;
	Game game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		imm  = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		Intent i = getIntent();
		int id = i.getIntExtra("id",  -1);
		//Cursor c = database.query(true, "players", new String[] {"name", "type", "object"}, "(type is " + "'Game' OR type is " + "'Series') AND name is ?", new String[] {i.getStringExtra("name")}, null, null, null, null, null);
		Cursor c = database.query(true, "players", new String[] {"name", "type", "object"}, "(type is " + "'Game' OR type is " + "'Series') AND _id is ?", new String[] {Integer.toString(i.getIntExtra("id", -1))}, null, null, null, null, null);
		System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + Integer.toString(c.getCount()));

		/*
		Cursor c = database.query(true, "players", new String[] {"name", "type", "object", "_id"}, "(type is " + "'Game' OR type is " + "'Series')", null, null, null, null, null, null);
		System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + Integer.toString(c.getCount()));
		while(c.moveToNext())
		{
			String json = c.getString(2);
			String type = c.getString(1);

				//Game g = new Gson().fromJson(json, Game.class);
				if(c.getInt(3) == id)
				{
					System.out.println("NOOO: "+Integer.toString(id) + "     " + Integer.toString(c.getInt(3)));
					game = new Gson().fromJson(json, Game.class);
					break;
				}
		}*/
		
		if(c.getCount() == 0)
		{
			super.finish();
		}
		while(c.moveToNext())
		{
			String json = c.getString(2);
			String type = c.getString(1);
			if(type.equals("Game"))
				game = new Gson().fromJson(json, Game.class);	
		}	
		
		getActionBar().setTitle(game.toString());
		
		if(game.getPinLayout() == null)
		{
			pinLayout = new char[10][10];
			scoreSheet = new String[10][3];
			init();
			game.setPinLayout(pinLayout);
		}
		else
		{
			pinLayout = game.getPinLayout();
			scoreSheet = game.getScoreSheet();
			setFramePinLayout(0);		
		}
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	    // your code here
		updateCanvasOnStart();
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
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    switch (keyCode) {
	        case KeyEvent.KEYCODE_0:
	        	System.out.println("NOOO THIS KEY: 0");
	        	frameScore[ballThrown] = "0";
	        	ballThrown++;
	            break;
	        case KeyEvent.KEYCODE_1:
	        	System.out.println("NOOO THIS KEY: 1");
	        	frameScore[ballThrown] = "1";
	        	ballThrown++;
	            break;
	        case KeyEvent.KEYCODE_2:
	        	System.out.println("NOOO THIS KEY: 2");
	        	frameScore[ballThrown] = "2";
	        	ballThrown++;
	        	break;
	        	case KeyEvent.KEYCODE_3:
	        	System.out.println("NOOO THIS KEY: 3");
	        	frameScore[ballThrown] = "3";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_4:
	        	System.out.println("NOOO THIS KEY: 4");
	        	frameScore[ballThrown] = "4";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_5:
	        	System.out.println("NOOO THIS KEY: 5");
	        	frameScore[ballThrown] = "5";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_6:
	        	System.out.println("NOOO THIS KEY: 6");
	        	frameScore[ballThrown] = "6";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_7:
	        	System.out.println("NOOO THIS KEY: 7");
	        	frameScore[ballThrown] = "7";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_8:
	        	System.out.println("NOOO THIS KEY: 8");
	        	frameScore[ballThrown] = "8";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_9:
	        	System.out.println("NOOO THIS KEY: 9");
	        	frameScore[ballThrown] = "9";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_SLASH:
	        	System.out.println("NOOO THIS KEY: /");
	        	frameScore[ballThrown] = "/";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_X:
	        	System.out.println("NOOO THIS KEY: X");
	        	frameScore[ballThrown] = "X";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_MINUS:
	        	System.out.println("NOOO THIS KEY: -");
	        	frameScore[ballThrown] = "-";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_ENTER:
	        	System.out.println("NOOO THIS KEY: Enter");
	        	final InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, 0);
	        	ballThrown = 0;
	        	break;
	        default:
	            return super.onKeyUp(keyCode, event);
	    }
	    if(activeFrame < 9) ballThrown = ballThrown % 2;
	    else ballThrown = ballThrown % 3;
	    
	    setFrameScore(frameScore[0], frameScore[1], frameScore[2], activeFrame);
	    updateFrame(cur,  activeFrame);
	    return true;
	}
	
	public void onClick(View v){
		ImageView temp;
		switch(v.getId())
		{
			case R.id.pin1_image:
				temp = (ImageView)findViewById(R.id.pin1_image);
				setPinTransparency(temp);				
				pinLayout[activeFrame][0] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin2_image:
				temp = (ImageView)findViewById(R.id.pin2_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][1] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin3_image:
				temp = (ImageView)findViewById(R.id.pin3_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][2] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin4_image:
				temp = (ImageView)findViewById(R.id.pin4_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][3] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin5_image:
				temp = (ImageView)findViewById(R.id.pin5_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][4] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin6_image:
				temp = (ImageView)findViewById(R.id.pin6_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][5] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin7_image:
				temp = (ImageView)findViewById(R.id.pin7_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][6] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin8_image:
				temp = (ImageView)findViewById(R.id.pin8_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][7] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin9_image:
				temp = (ImageView)findViewById(R.id.pin9_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][8] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin10_image:
				temp = (ImageView)findViewById(R.id.pin10_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][9] = valToAlpha(temp.getAlpha());
				break;
			case R.id.Frame1:
				cur = (ImageView)findViewById(R.id.Frame1);//(ImageView)findViewById(R.id.Frame1);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 0);
				break;
			case R.id.Frame2:
				cur = (ImageView)findViewById(R.id.Frame2);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 1);
				break;
			case R.id.Frame3:
				cur = (ImageView)findViewById(R.id.Frame3);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 2);
				break;
			case R.id.Frame4:
				cur = (ImageView)findViewById(R.id.Frame4);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 3);
				break;
			case R.id.Frame5:
				cur = (ImageView)findViewById(R.id.Frame5);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 4);
				break;
			case R.id.Frame6:
				cur = (ImageView)findViewById(R.id.Frame6);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 5);
				break;
			case R.id.Frame7:
				cur = (ImageView)findViewById(R.id.Frame7);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 6);
				break;
			case R.id.Frame8:
				cur = (ImageView)findViewById(R.id.Frame8);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 7);
				break;
			case R.id.Frame9:
				cur = (ImageView)findViewById(R.id.Frame9);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 8);
				break;
			case R.id.Frame10:
				cur = (ImageView)findViewById(R.id.Frame10);
				frameScore = new String[]{"","",""}; ballThrown = 0;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 9);
				break;
			default:
				break;
		}
	}
	private void setFrameScoreCanvas(ImageView frame, String ball1, String ball2, String ball3){
		int x1 = 10;
		int y1 = 42;
		
		int x2 = 120;
		int y2 = 70;
		int width,height;
		if(frame == (ImageView)findViewById(R.id.Frame10))
		{
			width = 280;
			height = 50;
		}
		else
		{
			width = 180;
			height = 100;
		}
		
		int x3 = 200;
		frame.buildDrawingCache();
		Bitmap tempBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		Canvas tempCanvas = new Canvas(tempBitmap);
		tempCanvas.drawBitmap(frame.getDrawingCache(), 0, 0, null);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setTextSize(50);
		
		tempCanvas.drawText(ball1, x1, y1, paint);
		tempCanvas.drawText(ball2, x2, y1, paint);

		
		if(frame.getId() == R.id.Frame10)
			tempCanvas.drawText(ball3, x3, y1, paint);

		//Attach the canvas to the ImageView
		frame.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
	}
	
	private void setPinTransparency(ImageView pin)
	{
		if(pin.getAlpha() == 1.0)
			pin.setAlpha((float).5);
		else if(pin.getAlpha() == .5)
			pin.setAlpha((float)0);
		else
			pin.setAlpha((float)1);
	}

	
	private void setFramePinLayout(int frame){
		char[] layout = pinLayout[frame];
		findViewById(R.id.pin1_image).setAlpha(alphaToVal(layout[0]));
		findViewById(R.id.pin2_image).setAlpha(alphaToVal(layout[1]));
		findViewById(R.id.pin3_image).setAlpha(alphaToVal(layout[2]));
		findViewById(R.id.pin4_image).setAlpha(alphaToVal(layout[3]));
		findViewById(R.id.pin5_image).setAlpha(alphaToVal(layout[4]));
		findViewById(R.id.pin6_image).setAlpha(alphaToVal(layout[5]));
		findViewById(R.id.pin7_image).setAlpha(alphaToVal(layout[6]));
		findViewById(R.id.pin8_image).setAlpha(alphaToVal(layout[7]));
		findViewById(R.id.pin9_image).setAlpha(alphaToVal(layout[8]));
		findViewById(R.id.pin10_image).setAlpha(alphaToVal(layout[9]));
		
	}
	
	private float alphaToVal(char x)
	{
		if(x == '2') return (float)1;
		else if(x == '1') return (float).5;
		else return (float)0;
	}

	private char valToAlpha(float x)
	{
		if(x == 0)
			return '0';
		else if(x == (float).5)
			return '1';
		else 
			return '2';
	}
	
	private void init(){
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<10; j++)
				pinLayout[i][j] = '2';
		}	
	}
	
	private void updateCanvasOnStart()
	{
		updateFrame((ImageView)findViewById(R.id.Frame1), 0);
		updateFrame((ImageView)findViewById(R.id.Frame2), 1);
		updateFrame((ImageView)findViewById(R.id.Frame3), 2);
		updateFrame((ImageView)findViewById(R.id.Frame4), 3);
		updateFrame((ImageView)findViewById(R.id.Frame5), 4);
		updateFrame((ImageView)findViewById(R.id.Frame6), 5);
		updateFrame((ImageView)findViewById(R.id.Frame7), 6);
		updateFrame((ImageView)findViewById(R.id.Frame8), 7);
		updateFrame((ImageView)findViewById(R.id.Frame9), 8);
		updateFrame((ImageView)findViewById(R.id.Frame10), 9);
	}
	
	private void updateFrame(ImageView temp, int frame)
	{
		
		activeFrame = frame;
		setFramePinLayout(frame);
		String[] S = getFrameScore(frame);
		if(S[0] != null)
			setFrameScoreCanvas(temp, S[0], S[1], "");

	}
	
	
	private void setFrameScore(String val1, String val2, String val3, int frame)
	{
		scoreSheet[frame][0] = val1;
		scoreSheet[frame][1] = val2;
		scoreSheet[frame][2] = val3;
	}
	
	private String[] getFrameScore(int frame)
	{
		return new String[] {scoreSheet[frame][0],scoreSheet[frame][1], scoreSheet[frame][2]};
	}
	
	@Override
	public void onBackPressed()
	{
		game.setPinLayout(pinLayout);
		game.setScoreSheet(scoreSheet);
		Intent i = new Intent();
		database.execSQL("UPDATE players SET object = ? WHERE _id = ?", new Object[] {new Gson().toJson(game), game.getId()});
		setResult(RESULT_OK, i);
		super.onBackPressed();
	}
	
	@Override
	public void onPause()
	{
		game.setPinLayout(pinLayout);
		game.setScoreSheet(scoreSheet);
		Intent i = new Intent();
		database.execSQL("UPDATE players SET object = ? WHERE _id = ?", new Object[] {new Gson().toJson(game), game.getId()});
		setResult(RESULT_OK, i);
		super.onPause();
	}

	
}
