package com.example.tenpin;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


@SuppressLint("NewApi")
public class GameActivity extends Activity {
	int activeFrame = 1;
	char[][] pinLayout;//= new char[10][10];
	Game game;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		Intent i = getIntent();
		game = i.getParcelableExtra("game");
		
		getActionBar().setTitle(game.toString());
		
		if(game.getPinLayout() == null)
		{
			System.out.println("Done this STUFF");
			pinLayout = new char[10][10];
			init();
			game.setPinLayout(pinLayout);
		}
		else
			pinLayout = game.getPinLayout();
		
		System.out.println(pinLayout[0][0]+"FF");
		System.out.println(game.getPinLayout()[0][0]+"FF");
	}
	
	@Override
    public void onPause() {
        super.onPause();
        game.setPinLayout(pinLayout);
    }
	
	@Override
    public void onStop() {
        super.onStop();
        game.setPinLayout(pinLayout);
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
				setPinTransparency(temp);				
				pinLayout[activeFrame-1][0] = valToAlpha(temp.getAlpha());
				break;
			case R.id.pin2_image:
				temp = (ImageView)findViewById(R.id.pin2_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][1] = valToAlpha(temp.getAlpha());

				break;
			case R.id.pin3_image:
				temp = (ImageView)findViewById(R.id.pin3_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][2] = valToAlpha(temp.getAlpha());

				break;
			case R.id.pin4_image:
				temp = (ImageView)findViewById(R.id.pin4_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][3] = valToAlpha(temp.getAlpha());

				break;
			case R.id.pin5_image:
				temp = (ImageView)findViewById(R.id.pin5_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][4] = valToAlpha(temp.getAlpha());

				break;
			case R.id.pin6_image:
				temp = (ImageView)findViewById(R.id.pin6_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][5] = valToAlpha(temp.getAlpha());

				break;
			case R.id.pin7_image:
				temp = (ImageView)findViewById(R.id.pin7_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][6] = valToAlpha(temp.getAlpha());

				break;
			case R.id.pin8_image:
				temp = (ImageView)findViewById(R.id.pin8_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][7] = valToAlpha(temp.getAlpha());

				break;
			case R.id.pin9_image:
				temp = (ImageView)findViewById(R.id.pin9_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][8] = valToAlpha(temp.getAlpha());

				break;
			case R.id.pin10_image:
				temp = (ImageView)findViewById(R.id.pin10_image);
				setPinTransparency(temp);
				pinLayout[activeFrame-1][9] = valToAlpha(temp.getAlpha());

				break;
			case R.id.next_throw_button:
				Toast.makeText(getApplicationContext(), "next!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame1:
				temp = (ImageView)findViewById(R.id.Frame1);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 1;
				setFramePinLayout(0);
				//temp.setColorFilter(0x00FFFF);
				break;
			case R.id.Frame2:
				temp = (ImageView)findViewById(R.id.Frame2);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 2;
				setFramePinLayout(1);
				break;
			case R.id.Frame3:
				temp = (ImageView)findViewById(R.id.Frame3);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 3;
				setFramePinLayout(2);
				break;
			case R.id.Frame4:
				temp = (ImageView)findViewById(R.id.Frame4);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 4;
				setFramePinLayout(3);
				break;
			case R.id.Frame5:
				temp = (ImageView)findViewById(R.id.Frame5);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 5;
				setFramePinLayout(4);
				break;
			case R.id.Frame6:
				temp = (ImageView)findViewById(R.id.Frame6);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 6;
				setFramePinLayout(5);
				break;
			case R.id.Frame7:
				temp = (ImageView)findViewById(R.id.Frame7);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 7;
				setFramePinLayout(6);
				break;
			case R.id.Frame8:
				temp = (ImageView)findViewById(R.id.Frame8);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 8;
				setFramePinLayout(7);
				break;
			case R.id.Frame9:
				temp = (ImageView)findViewById(R.id.Frame9);
				setFrameScore(temp, "6", "/", "");
				activeFrame = 9;
				setFramePinLayout(8);
				break;
			case R.id.Frame10:
				temp = (ImageView)findViewById(R.id.Frame10);
				setFrameScore(temp, "6", "/", "X");
				activeFrame = 10;
				setFramePinLayout(9);
				break;
			default:
				break;
		}
	}
	
	private void setFrameScore(ImageView frame, String ball1, String ball2, String ball3){
		int x1 = 10;
		int y1 = 42;
		
		int x2 = 120;
		int y2 = 70;
		
		int x3 = 200;
		frame.buildDrawingCache();
				
		Bitmap tempBitmap = Bitmap.createBitmap(frame.getWidth(), frame.getHeight(), Bitmap.Config.RGB_565);
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
			for(int j=0; j<10; j++)
				pinLayout[i][j] = '2';
	}
	
}
