package com.yangyu.mytitlebar02;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

/**
 * @author yangyu
 *	功能描述：更多Activity页面
 */
public class MoreActivity extends Activity {
	boolean isConnected;
	Spinner from;
	Spinner to;
	ImageButton convert;
	GetWebsite getwebsite;
	//double[] rates;
	double[] rates={1.3814,145.02,1.9558,27.445,7.4599,0.8366,297.27,3.4528,0.7018,4.1515,4.4625,8.9785,1.2234,8.4475,7.6385,44.961,2.9859,1.5506,3.2519,1.4731,8.3841,10.712,16830,4.8080,85.623,1456.6,18.123,4.5416,1.6893,61.264,1.7509,45.414,14.444};
	EditText money;
	CheckBox online;
	ConnectivityManager cm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_activity);
		try {  
		    getWindow().addFlags(WindowManager.LayoutParams.class.getField("FLAG_NEEDS_MENU_KEY").getInt(null));  
		}catch (NoSuchFieldException e) {  
		    // Ignore since this field won't exist in most versions of Android  
		}catch (IllegalAccessException e) {  
		    Log.w("feelyou.info", "Could not access FLAG_NEEDS_MENU_KEY in addLegacyOverflowButton()", e);  
		}  
		addListenerOnButton(); 
		cm =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
		                      activeNetwork.isConnectedOrConnecting();
		if (isConnected == true){
			try{
			rates = getwebsite.GetWebsite();
			} catch (NullPointerException e){
				
				//money.setText("Rates update failed");
			}

		}
		

				
	}
	//get the selected dropdown list value
	
			public void addListenerOnButton() {
				from = (Spinner)findViewById(R.id.spinner2);
				to = (Spinner)findViewById(R.id.spinner3);
				convert = (ImageButton)findViewById(R.id.login_bt);
//				getwebsite = new GetWebsite();
//				rates = getwebsite.GetWebsite();
				money = (EditText)findViewById(R.id.editText1);
				online = (CheckBox)findViewById(R.id.checkBox1);
				money.setRawInputType(Configuration.KEYBOARD_QWERTY);
				
				convert.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
					//	result.setText(String.valueOf(from.getSelectedItemPosition()));
				         if (online.isChecked()) {
				        	 try{
						rates = getwebsite.GetWebsite();
				        	 } catch (Exception e){
				        		// money.setText("null");
				        	 }
				         }

						Double fromRate = rates[from.getSelectedItemPosition()];
						Double toRate = rates[to.getSelectedItemPosition()];
						Double newRate = toRate/fromRate;
						try{
							Double editTextValue = Double.parseDouble(money.getText().toString());
							money.setText(String.valueOf(new java.text.DecimalFormat(".00").format(editTextValue * newRate )));
							
//							if(rates.length==0)
//								money.setText("null");
//							else
//								money.setText(String.valueOf(rates[1]));

						} catch (NumberFormatException e) {
							money.setText("faint");
						}

					}						

				});

			}	

}
