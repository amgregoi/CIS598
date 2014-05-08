package com.example.tenpin;

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
import android.widget.TextView;


@SuppressLint("NewApi")
public class GameActivity extends DBManagment {
	InputMethodManager imm;
	int activeFrame = 0, ballThrown = 0;
	float[][] pinLayout;
	String[][] scoreSheet;
	String[] frameScore;
	ImageView cur;
	Game game;
	boolean fScore = false;
	TextView et;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		et = (TextView)findViewById(R.id.finalScoretext);
		imm  = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		Intent i = getIntent();
		Cursor c = database.query(true, "players", new String[] {"name", "type", "object"}, "(type is " + "'Game' OR type is " + "'GSeries') AND _id is ?", new String[] {Integer.toString(i.getIntExtra("id", -1))}, null, null, null, null, null);
		
		if(c.getCount() == 0)
		{
			this.finishActivity(-1);
		}
		while(c.moveToNext())
		{
			String json = c.getString(2);
			game = new Gson().fromJson(json, Game.class);	
		}	
		
		getActionBar().setTitle(game.toString());
		
		if(game.getPinLayout() == null)
		{
			pinLayout = new float[10][10];
			scoreSheet = new String[10][3];
			init();
			game.setPinLayout(pinLayout);
		}
		else
		{
			pinLayout = game.getPinLayout();
			scoreSheet = game.getScoreSheet();
		}
		
		et.setText("Final Score: "+Integer.toString(game.getScore()));
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onWindowFocusChanged(boolean)
	 * updates the scoreSheet canvas elements to correct scores when activity loads
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	    // your code here
		updateCanvasOnStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		menu.add("Enter Final Score");
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
	 * @see android.app.Activity#onKeyUp(int, android.view.KeyEvent)
	 * Restricts keypresses to 0-9, X,  and /
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    switch (keyCode) {
	        case KeyEvent.KEYCODE_0:
	        	frameScore[ballThrown] = "0";
	        	ballThrown++;
	            break;
	        case KeyEvent.KEYCODE_1:
	        	frameScore[ballThrown] = "1";
	        	ballThrown++;
	            break;
	        case KeyEvent.KEYCODE_2:
	        	frameScore[ballThrown] = "2";
	        	ballThrown++;
	        	break;
	        	case KeyEvent.KEYCODE_3:
	        	frameScore[ballThrown] = "3";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_4:
	        	frameScore[ballThrown] = "4";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_5:
	        	frameScore[ballThrown] = "5";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_6:
	        	frameScore[ballThrown] = "6";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_7:
	        	frameScore[ballThrown] = "7";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_8:
	        	frameScore[ballThrown] = "8";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_9:
	        	frameScore[ballThrown] = "9";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_SLASH:
	        	frameScore[ballThrown] = "/";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_X:
	        	frameScore[ballThrown] = "X";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_MINUS:
	        	frameScore[ballThrown] = "-";
	        	ballThrown++;
	        	break;
	        case KeyEvent.KEYCODE_ENTER:
	        	final InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, 0);
	        	ballThrown = 0;
	        	break;
	        default:
	            return super.onKeyUp(keyCode, event);
	    }
	    
	    if(activeFrame == 9 || fScore) ballThrown = ballThrown % 3;
	    else ballThrown = ballThrown % 2;
	    
	    if(fScore)
	    {
	    	String score = frameScore[0]+frameScore[1]+frameScore[2];
	    	et.setText("Final Score: "+ score.replaceFirst ("^0*", ""));
	    	game.setScore(Integer.parseInt(score));
	    	return true;
	    }
	    
	    setFrameScore(frameScore[0], frameScore[1], frameScore[2], activeFrame);
	    updateFrame(cur,  activeFrame);
	    return true;
	}
	
	/*
	 * Click listener for canvas and pin objects
	 */
	public void onClick(View v){
		ImageView temp;
		switch(v.getId())
		{
			case R.id.pin1_image:
				temp = (ImageView)findViewById(R.id.pin1_image);
				setPinTransparency(temp);				
				pinLayout[activeFrame][0] = temp.getAlpha();
				break;
			case R.id.pin2_image:
				temp = (ImageView)findViewById(R.id.pin2_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][1] = (temp.getAlpha());
				break;
			case R.id.pin3_image:
				temp = (ImageView)findViewById(R.id.pin3_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][2] = (temp.getAlpha());
				break;
			case R.id.pin4_image:
				temp = (ImageView)findViewById(R.id.pin4_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][3] = (temp.getAlpha());
				break;
			case R.id.pin5_image:
				temp = (ImageView)findViewById(R.id.pin5_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][4] = (temp.getAlpha());
				break;
			case R.id.pin6_image:
				temp = (ImageView)findViewById(R.id.pin6_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][5] = (temp.getAlpha());
				break;
			case R.id.pin7_image:
				temp = (ImageView)findViewById(R.id.pin7_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][6] = (temp.getAlpha());
				break;
			case R.id.pin8_image:
				temp = (ImageView)findViewById(R.id.pin8_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][7] = (temp.getAlpha());
				break;
			case R.id.pin9_image:
				temp = (ImageView)findViewById(R.id.pin9_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][8] = (temp.getAlpha());
				break;
			case R.id.pin10_image:
				temp = (ImageView)findViewById(R.id.pin10_image);
				setPinTransparency(temp);
				pinLayout[activeFrame][9] = (temp.getAlpha());
				break;
			case R.id.Frame1:
				cur = (ImageView)findViewById(R.id.Frame1);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 0);
				break;
			case R.id.Frame2:
				cur = (ImageView)findViewById(R.id.Frame2);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 1);
				break;
			case R.id.Frame3:
				cur = (ImageView)findViewById(R.id.Frame3);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 2);
				break;
			case R.id.Frame4:
				cur = (ImageView)findViewById(R.id.Frame4);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 3);
				break;
			case R.id.Frame5:
				cur = (ImageView)findViewById(R.id.Frame5);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 4);
				break;
			case R.id.Frame6:
				cur = (ImageView)findViewById(R.id.Frame6);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 5);
				break;
			case R.id.Frame7:
				cur = (ImageView)findViewById(R.id.Frame7);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 6);
				break;
			case R.id.Frame8:
				cur = (ImageView)findViewById(R.id.Frame8);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);
				updateFrame(cur, 7);
				break;
			case R.id.Frame9:
				cur = (ImageView)findViewById(R.id.Frame9);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 8);
				break;
			case R.id.Frame10:
				cur = (ImageView)findViewById(R.id.Frame10);
				frameScore = new String[]{"","",""}; ballThrown = 0; fScore = false;
				imm.toggleSoftInput(0, 0);				
				updateFrame(cur, 9);
				break;
			case R.id.finalScoretext:
				frameScore = new String[]{"","",""}; ballThrown = 0;
				//activeFrame = 10;
				fScore = true;
				imm.toggleSoftInput(0, 0);
			default:
				break;
		}
	}
	
	/*
	 * Draws user input to canvas frame
	 */
	private void setFrameScoreCanvas(ImageView frame, String ball1, String ball2, String ball3){
		
		final float scale = getResources().getDisplayMetrics().density;
		
		int y1 = (int) (24 * scale + 0.5f);
		
		int x1 = (int) (10 * scale + 0.5f);
        int x2 = (int) (60 * scale + 0.5f);
        int x3 = (int) (105 * scale + 0.5f);
		
		
		frame.buildDrawingCache();
		Bitmap tempBitmap = Bitmap.createBitmap(frame.getWidth(), frame.getHeight(), Bitmap.Config.RGB_565);
		Canvas tempCanvas = new Canvas(tempBitmap);
		tempCanvas.drawBitmap(frame.getDrawingCache(), 0, 0, null);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setTextSize((int) (30 * scale + 0.5f));
		
		
		tempCanvas.drawText(ball1, x1, y1, paint);
		tempCanvas.drawText(ball2, x2, y1, paint);

		
		if(frame.getId() == R.id.Frame10)
		{
			tempCanvas.drawText(ball3, x3, y1, paint);
		}

		//Attach the canvas to the ImageView
		frame.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
	}
	
	/*
	 * set the transparency of the pin object from visible, 1/2 visible, and transparent
	 */
	private void setPinTransparency(ImageView pin)
	{
		if(pin.getAlpha() == 1.0)
			pin.setAlpha((float).5);
		else if(pin.getAlpha() == .5)
			pin.setAlpha((float)0);
		else
			pin.setAlpha((float)1);
	}

	
	/*
	 * sets the pin transparency levels for the selected frame
	 */
	private void setFramePinLayout(int frame){
		float[] layout = pinLayout[frame];
		findViewById(R.id.pin1_image).setAlpha((layout[0]));
		findViewById(R.id.pin2_image).setAlpha((layout[1]));
		findViewById(R.id.pin3_image).setAlpha((layout[2]));
		findViewById(R.id.pin4_image).setAlpha((layout[3]));
		findViewById(R.id.pin5_image).setAlpha((layout[4]));
		findViewById(R.id.pin6_image).setAlpha((layout[5]));
		findViewById(R.id.pin7_image).setAlpha((layout[6]));
		findViewById(R.id.pin8_image).setAlpha((layout[7]));
		findViewById(R.id.pin9_image).setAlpha((layout[8]));
		findViewById(R.id.pin10_image).setAlpha((layout[9]));
		
	}
	
	/*
	 * initialize pin layouts if already null
	 */
	private void init(){
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<10; j++)
				pinLayout[i][j] = (float) 1.0;
		}	
	}
	
	/*
	 * updates canvas elements with scoresheet values on activity start
	 */
	private void updateCanvasOnStart()
	{
		updateFrame((ImageView)findViewById(R.id.Frame2), 1);
		updateFrame((ImageView)findViewById(R.id.Frame3), 2);
		updateFrame((ImageView)findViewById(R.id.Frame4), 3);
		updateFrame((ImageView)findViewById(R.id.Frame5), 4);
		updateFrame((ImageView)findViewById(R.id.Frame6), 5);
		updateFrame((ImageView)findViewById(R.id.Frame7), 6);
		updateFrame((ImageView)findViewById(R.id.Frame8), 7);
		updateFrame((ImageView)findViewById(R.id.Frame9), 8);
		updateFrame((ImageView)findViewById(R.id.Frame10),9);
		updateFrame((ImageView)findViewById(R.id.Frame1), 0);
	}
	
	/*
	 * updates frame elements 
	 */
	private void updateFrame(ImageView temp, int frame)
	{
		
		activeFrame = frame;
		setFramePinLayout(frame);
		String[] S = getFrameScore(frame);
		if(S[0] != null)
			setFrameScoreCanvas(temp, S[0], S[1], S[2]);
	}
	
	/*
	 * set scoreSheet values for frame
	 */
	private void setFrameScore(String val1, String val2, String val3, int frame)
	{
		scoreSheet[frame][0] = val1;
		scoreSheet[frame][1] = val2;
		scoreSheet[frame][2] = val3;
	}
	
	/*
	 * get scoresheet values for frame
	 */
	private String[] getFrameScore(int frame)
	{
		return new String[] {scoreSheet[frame][0],scoreSheet[frame][1], scoreSheet[frame][2]};
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 * override back button to updating game object in database before exiting activity
	 */
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
	
	/*
	 * (non-Javadoc)
	 * @see com.example.tenpin.DBManagment#onPause()
	 * override onPause to updating game object in database before exiting activity
	 */
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
