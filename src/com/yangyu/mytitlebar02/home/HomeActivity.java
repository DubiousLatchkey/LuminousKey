package com.yangyu.mytitlebar02.home;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.yangyu.mytitlebar02.R;

/**
 * @author yangyu
 *	功能描述：贴吧首页Activity页面
 */
public class HomeActivity extends ActivityGroup implements OnClickListener{
	//定义帧布局对象
	private FrameLayout mContent;
	
	//定义图片按钮对象
	
	//定义标示符


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		
		//得到按钮对象

		
		//按钮设置监听

		
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
}


	/**
	 * 显示我的贴吧页面
	 */

	
	/**
	 * 显示我的标签页面
	 */


























