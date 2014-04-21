package com.example.tenpin;


import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
			case R.id.Frame1:
				
				/* TEST CODE: COME BACK TO THIS
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 */
				Toast.makeText(getApplicationContext(), "Frame 1!", Toast.LENGTH_SHORT).show();
				temp = (ImageView)findViewById(R.id.Frame1);
				temp.setColorFilter(0x77000000);
				
				/*
				Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), v.getId());
				if(bmp == null) System.out.println("BITMAP IS NULL\n");
				else System.out.println("SOMETHING ELSE\n");
				Canvas c = new Canvas(bmp.copy(Bitmap.Config.ARGB_8888, true));
				//c.setBitmap(bmp);
				
				
				Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	            // text color - #3D3D3D
	            paint.setColor(Color.BLACK);
				c.drawText("6", 50, 50, paint);
				c.drawColor(Color.RED);
				*/
				
				
				break;
			case R.id.Frame2:
				Toast.makeText(getApplicationContext(), "Frame 2!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame3:
				Toast.makeText(getApplicationContext(), "Frame 3!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame4:
				Toast.makeText(getApplicationContext(), "Frame 4!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame5:
				Toast.makeText(getApplicationContext(), "Frame 5!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame6:
				Toast.makeText(getApplicationContext(), "Frame 6!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame7:
				Toast.makeText(getApplicationContext(), "Frame 7!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame8:
				Toast.makeText(getApplicationContext(), "Frame 8!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame9:
				Toast.makeText(getApplicationContext(), "Frame 9!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Frame10:
				Toast.makeText(getApplicationContext(), "Frame 10!", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}


}
