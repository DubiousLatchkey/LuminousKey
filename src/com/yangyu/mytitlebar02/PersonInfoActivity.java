package com.yangyu.mytitlebar02;
//Download by http://www.codefans.net
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yangyu
 *	功能描述：个人资料Activity页面
 */
public class PersonInfoActivity extends Activity {
	
	private static final String TOTAL_COUNT = "TOTAL_COUNT";
	private static final String BUTTON_COUNT = "BUTTON_COUNT";
	private static final String HIGH_SCORE = "HIGH_SCORE";

	public static int i = 0;
	public int a = 0;
	public int j = 0;
	public Button button1;
	public Button button2;
	public Button button3;
	public static Button pps;
	public static Button pressText;
	public static Button totalPress;
	public static Button timer;
	public Switch sound;
	public static double y = 0;
	String yy = Double.toString(y);

	MediaPlayer click1;
	Intent intent;

	// Intent intent = new Intent(this, Info.class);

	// private static final String TAG = "MainActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);

		setContentView(R.layout.personalinfo_activity);
		// Log.v(TAG, "Initializing  sounds...");
		// final MediaPlayer mp = MediaPlayer.create(this, R.drawable.click);
		try {  
		    getWindow().addFlags(WindowManager.LayoutParams.class.getField("FLAG_NEEDS_MENU_KEY").getInt(null));  
		}catch (NoSuchFieldException e) {  
		    // Ignore since this field won't exist in most versions of Android  
		}catch (IllegalAccessException e) {  
		    Log.w("feelyou.info", "Could not access FLAG_NEEDS_MENU_KEY in addLegacyOverflowButton()", e);  
		}  
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		pressText = (Button) findViewById(R.id.button5);
		totalPress = (Button) findViewById(R.id.button8);
		timer = (Button) findViewById(R.id.button10);
		pps = (Button) findViewById(R.id.button4);
		sound = (Switch) findViewById(R.id.switch1);
		
		


		final MyCounter timer = new MyCounter(30000, 1000);

		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				timer.start();
				i = 0;
				pressText.setText("0");

			}
		});
		if (savedInstanceState == null) {
			try {
				j = getTotal();
				y = getHighScore();


				Log.d(HIGH_SCORE, "highScore saved to y");

			} catch (Exception e) {
				totalSave();
				highScoreSave();

				e.printStackTrace(System.err);
			}
		} else {
			i = savedInstanceState.getInt(BUTTON_COUNT);
			j = savedInstanceState.getInt(TOTAL_COUNT);
			y = savedInstanceState.getDouble(HIGH_SCORE);

		}
		if (i == 1) {
			pressText.setText(Integer.toString(i));

		}
		if (i > 1) {
			pressText.setText(Integer.toString(i));
			 
			

		}
		if (j > 1)

		totalPress.setText(Integer.toString(j));
		
		
		if (j == 1) {
			totalPress.setText(Integer.toString(j));
		}

		click();
		reset();
		highScoreSave();
		totalSave();
	}

	public void click() {

		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				click1 = MediaPlayer.create(PersonInfoActivity.this, R.raw.click1);
				// Log.v(TAG, "Playing Sound..");
				click1.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer click1) {
						click1.release();
					}
				});
				sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							a = 1;
						} else {
							a = 0;
						}
					}
				});
				sound.setOnClickListener (new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						
						if (a == 1){
							a = 0;
						} else {
							a = 1;
						}
						
					}
					
				
					
				});
				if (a == 1) {
					click1.start();
				}
				j++;
				if (j > 1) {
					totalPress.setText(Integer.toString(j));

					
				} else if (j == 1) {
					totalPress.setText("1");

				}
				i++;
				if (i == 1) {
					pressText.setText("1");


				}
				if (i > 1) {
					pressText.setText(Integer.toString(i));
				}
			}
		});
		// Log.v(TAG, "Sounds Initialized");
	}

	public void reset() {
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				highScoreSave();
				totalSave();
				i = 0;
				Toast.makeText(PersonInfoActivity.this, "reset!", Toast.LENGTH_SHORT)
						.show();
				pressText.setText("0");
			}
		});
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		menuInflater.inflate(R.menu.leaderboard, menu);

		return true;
	}

	@Override
	protected void onPause() {

		super.onPause();
		totalSave();
		highScoreSave();
		Log.d(HIGH_SCORE, "highScore Saved");

		save(sound.isChecked());

	}

	protected void onStop() {

		super.onPause();
		totalSave();
		highScoreSave();

	}

	protected void onDestroy() {

		super.onPause();
		totalSave();
		highScoreSave();

	}

	protected void onResume() {

		super.onResume();
		sound.setChecked(load());
		if (sound.isChecked()) {
			a = 1;
		} else {
			a = 0;
		}
		getTotal();
		getHighScore();

	}

	public int getTotal() {
		SharedPreferences sharedPref = getSharedPreferences("j",
				Context.MODE_PRIVATE);
		String Total2 = sharedPref.getString("SavedButtonCount", null);
		int buttonCount2 = Integer.parseInt(Total2);
		return buttonCount2;

	}

	public double getHighScore() {
		SharedPreferences sharedPref1 = getSharedPreferences("y", 0);
		String highScore1 = sharedPref1.getString("highScore", null);
		double highScore = Double.parseDouble(highScore1);
		Log.d(HIGH_SCORE, "getHighScore used");

		return highScore;
	}

	public void totalSave() {
		String total = Integer.toString(j);
		SharedPreferences sharedPref = getSharedPreferences("j",
				Context.MODE_PRIVATE);
		Editor editor = sharedPref.edit();
		editor.putString("SavedButtonCount", total);
		editor.commit();

	}

	public void highScoreSave() {

		String highScore = Double.toString(y);
		SharedPreferences sharedPref1 = getSharedPreferences("y", 0);
		Editor editor1 = sharedPref1.edit();
		editor1.putString("highScore", highScore);
		editor1.commit();
		Log.d(HIGH_SCORE, "highScore saved");

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putDouble(BUTTON_COUNT, i);
		outState.putInt(TOTAL_COUNT, j);
		outState.putDouble(HIGH_SCORE, y);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_settings) {
			Toast.makeText(PersonInfoActivity.this, "All numbers have been reset",
					Toast.LENGTH_SHORT).show();
			i = 0;
			j = 0;
			pps.setText("");
			totalPress.setText("0");
			pressText.setText("0");
			pps.setText("0 pps");

			return true;
		} else if (item.getItemId() == R.id.leaderboard) {
			leaderboard();
			Toast.makeText(PersonInfoActivity.this, yy +" pps", Toast.LENGTH_SHORT).show();

			return true;
		}
		

		else {
			return super.onOptionsItemSelected(item);
		}
	}

	private void save(final boolean isChecked) {
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean("check", isChecked);
		editor.commit();
	}

	private boolean load() {
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean("check", false);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}


	public void leaderboard() {

		yy = Double.toString(y);


	}
}
