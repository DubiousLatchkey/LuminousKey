package com.yangyu.mytitlebar02.home;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.yangyu.mytitlebar02.MainActivity;
import com.yangyu.mytitlebar02.R;

/**
 * @author yangyu
 *	功能描述：贴吧首页Activity页面
 */
public class HomeActivity extends ActivityGroup implements OnClickListener{
	//定义帧布局对象
	private FrameLayout mContent;
	public Button button1;
	public Button button2;
	public Button button3;
	
	//定义图片按钮对象
	
	//定义标示符


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		
		//得到按钮对象

		
		//按钮设置监听

		info1();
		info2();
		info3(); 
		//初始化默认显示的页面

	}
	
	/**
	 * 添加视图
	 */
	public void addView(String id, Class<?> clazz) {
		Intent intent = new Intent(this, clazz);
		//移除这个布局中所有的组件
		mContent.removeAllViews();
		mContent.addView(getLocalActivityManager().startActivity(id, intent).getDecorView());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}	
	public void info1(){
		
		button1.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Toast.makeText(HomeActivity.this, "The Calculator is a basic everyday calculator.", Toast.LENGTH_LONG).show();
				Toast.makeText(HomeActivity.this, "It can do the 4 operations, squares and roots as well as having a memory function.", Toast.LENGTH_LONG).show();
			}
		});
	}
	public void info2(){
		
		button2.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(
						HomeActivity.this,
						"Pressing 'start timer' will start the game where you press the button as many times as you can in 30 seconds.",
						Toast.LENGTH_LONG).show();
				Toast.makeText(
						HomeActivity.this,
						"You do not have to start the timer to use the counter",
						Toast.LENGTH_LONG).show();
				Toast.makeText(
						HomeActivity.this,
						"Without the timer, the counter will function as normal",
						Toast.LENGTH_LONG).show();
				
			}
	
	
			
		});
	}
	
	public void info3(){
		
		button3.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Toast.makeText(HomeActivity.this, "The Exchanger can exchange currencies from around the world.", Toast.LENGTH_LONG).show();
				Toast.makeText(HomeActivity.this, "Select your currencies, type your amount of money in the gap, and press the convert symbol.", Toast.LENGTH_LONG).show();
			}
		});
	}

}



	/**
	 * 显示我的贴吧页面
	 */

	
	/**
	 * 显示我的标签页面
	 */


























