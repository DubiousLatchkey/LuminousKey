package com.yangyu.mytitlebar02;
//download by http://www.codefans.net
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyu.mytitlebar02.home.HomeActivity;

/**
 * @author yangyu
 *	功能描述：主Activity程序入口类
 */

public class MainActivity extends TabActivity implements OnCheckedChangeListener {
	

	//定义Tab选项卡标示符
	private static final String HOME_TAB = "home_tab";
	private static final String MENTION_TAB = "mention_tab";
	private static final String PERSON_TAB = "person_tab";
	private static final String MORE_TAB = "more_tab";

	//定义Intent对象
	private Intent mHomeIntent,mMentionIntent,mPersonIntent,mMoreIntent;

	//定义TabHost对象
	private TabHost mTabHost;

	//定义单选按钮对象
	private RadioButton homeRb,mentionRb,personRb,moreRb;
	
	//定义消息提示文本对象


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintabs_activity);
		try {  
		    getWindow().addFlags(WindowManager.LayoutParams.class.getField("FLAG_NEEDS_MENU_KEY").getInt(null));  
		}catch (NoSuchFieldException e) {  
		    // Ignore since this field won't exist in most versions of Android  
		}catch (IllegalAccessException e) {  
		    Log.w("feelyou.info", "Could not access FLAG_NEEDS_MENU_KEY in addLegacyOverflowButton()", e);  
		}  

		initView();
		initData();


	}
	
	/**
	 * 初始化组件
	 */
	private void initView(){
		//得到TabHost
		mTabHost = getTabHost();
		
		//得到Intent对象
		mHomeIntent = new Intent(this, HomeActivity.class);
		mMentionIntent = new Intent(this, MentionActivity.class);
		mPersonIntent = new Intent(this, PersonInfoActivity.class);
		mMoreIntent = new Intent(this, MoreActivity.class);
		
		//得到消息提示文本对象
		
		
		//得到单选按钮对象
		homeRb = ((RadioButton) findViewById(R.id.radio_home));
		mentionRb = ((RadioButton) findViewById(R.id.radio_mention));
		personRb = ((RadioButton) findViewById(R.id.radio_person_info));
		moreRb = ((RadioButton) findViewById(R.id.radio_more));
		
		
		
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(){
		//给单选按钮设置监听
		homeRb.setOnCheckedChangeListener(this);
		mentionRb.setOnCheckedChangeListener(this);
		personRb.setOnCheckedChangeListener(this);
		moreRb.setOnCheckedChangeListener(this);
		
		//给消息提示文本设置文字
		
		//添加进Tab选项卡
		mTabHost.addTab(buildTabSpec(HOME_TAB, mHomeIntent));
		mTabHost.addTab(buildTabSpec(MENTION_TAB, mMentionIntent));
		mTabHost.addTab(buildTabSpec(PERSON_TAB, mPersonIntent));
		mTabHost.addTab(buildTabSpec(MORE_TAB, mMoreIntent));
		
		//设置当前默认的Tab选项卡页面
		homeRb.setChecked(true);
		mTabHost.setCurrentTabByTag(HOME_TAB);			
	}						

	private TabHost.TabSpec buildTabSpec(String tag, Intent intent) {
		TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tag);
		tabSpec.setContent(intent).setIndicator("");
		
		return tabSpec;
	}

	/**
	 * Tab按钮选中监听事件
	 */
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.radio_home:
				mTabHost.setCurrentTabByTag(HOME_TAB);
				break;
			case R.id.radio_mention:
				mTabHost.setCurrentTabByTag(MENTION_TAB);
				//VISIBLE:0  意思是可见的;INVISIBILITY:4 意思是不可见的，但还占着原来的空间;GONE:8  意思是不可见的，不占用原来的布局空间 
				break;
			case R.id.radio_person_info:
				mTabHost.setCurrentTabByTag(PERSON_TAB);
				break;
			case R.id.radio_more:
				mTabHost.setCurrentTabByTag(MORE_TAB);
				break;
			default:
				break;
			}
		}
	}
	


	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if ((event.getAction() == KeyEvent.ACTION_DOWN) && (event.getKeyCode() == KeyEvent.KEYCODE_BACK)) {
			quitDialog();
		}
		return super.dispatchKeyEvent(event);
	}

	/**
	 * 退出对话框
	 */
	private void quitDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.alerm_title)
				.setIcon(null) 
				.setCancelable(false)
				.setMessage(R.string.alert_quit_confirm)
				.setPositiveButton(R.string.alert_yes_button,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								MainActivity.this.finish();
							}
						})
				.setNegativeButton(R.string.alert_no_button,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).create().show();
	}
	

	
}
