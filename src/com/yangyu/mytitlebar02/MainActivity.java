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
 *	������������Activity���������
 */

public class MainActivity extends TabActivity implements OnCheckedChangeListener {
	
	public Button button1;
	public Button button2;
	public Button button3;
	//����Tabѡ���ʾ��
	private static final String HOME_TAB = "home_tab";
	private static final String MENTION_TAB = "mention_tab";
	private static final String PERSON_TAB = "person_tab";
	private static final String MORE_TAB = "more_tab";

	//����Intent����
	private Intent mHomeIntent,mMentionIntent,mPersonIntent,mMoreIntent;

	//����TabHost����
	private TabHost mTabHost;

	//���嵥ѡ��ť����
	private RadioButton homeRb,mentionRb,personRb,moreRb;
	
	//������Ϣ��ʾ�ı�����
	private TextView mMessageTipsMention,mMessageTipsPerson;

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

/*		info1();
		info2();
		info3(); */
	}
	
	/**
	 * ��ʼ�����
	 */
	private void initView(){
		//�õ�TabHost
		mTabHost = getTabHost();
		
		//�õ�Intent����
		mHomeIntent = new Intent(this, HomeActivity.class);
		mMentionIntent = new Intent(this, MentionActivity.class);
		mPersonIntent = new Intent(this, PersonInfoActivity.class);
		mMoreIntent = new Intent(this, MoreActivity.class);
		
		//�õ���Ϣ��ʾ�ı�����
		mMessageTipsMention = (TextView) findViewById(R.id.message_mention);
		mMessageTipsPerson = (TextView) findViewById(R.id.message_person);
		
		//�õ���ѡ��ť����
		homeRb = ((RadioButton) findViewById(R.id.radio_home));
		mentionRb = ((RadioButton) findViewById(R.id.radio_mention));
		personRb = ((RadioButton) findViewById(R.id.radio_person_info));
		moreRb = ((RadioButton) findViewById(R.id.radio_more));
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		
	}
	
	/**
	 * ��ʼ������
	 */
	private void initData(){
		//����ѡ��ť���ü���
		homeRb.setOnCheckedChangeListener(this);
		mentionRb.setOnCheckedChangeListener(this);
		personRb.setOnCheckedChangeListener(this);
		moreRb.setOnCheckedChangeListener(this);
		
		//����Ϣ��ʾ�ı���������
		mMessageTipsMention.setText("2");
		mMessageTipsPerson.setText("4"); 
		
		//��ӽ�Tabѡ�
		mTabHost.addTab(buildTabSpec(HOME_TAB, mHomeIntent));
		mTabHost.addTab(buildTabSpec(MENTION_TAB, mMentionIntent));
		mTabHost.addTab(buildTabSpec(PERSON_TAB, mPersonIntent));
		mTabHost.addTab(buildTabSpec(MORE_TAB, mMoreIntent));
		
		//���õ�ǰĬ�ϵ�Tabѡ�ҳ��
		homeRb.setChecked(true);
		mTabHost.setCurrentTabByTag(HOME_TAB);			
	}						

	private TabHost.TabSpec buildTabSpec(String tag, Intent intent) {
		TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tag);
		tabSpec.setContent(intent).setIndicator("");
		
		return tabSpec;
	}

	/**
	 * Tab��ťѡ�м����¼�
	 */
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.radio_home:
				mTabHost.setCurrentTabByTag(HOME_TAB);
				break;
			case R.id.radio_mention:
				mTabHost.setCurrentTabByTag(MENTION_TAB);
				//VISIBLE:0  ��˼�ǿɼ���;INVISIBILITY:4 ��˼�ǲ��ɼ��ģ�����ռ��ԭ���Ŀռ�;GONE:8  ��˼�ǲ��ɼ��ģ���ռ��ԭ���Ĳ��ֿռ� 
				mMessageTipsMention.setVisibility(8);
				break;
			case R.id.radio_person_info:
				mTabHost.setCurrentTabByTag(PERSON_TAB);
				mMessageTipsPerson.setVisibility(8);
				break;
			case R.id.radio_more:
				mTabHost.setCurrentTabByTag(MORE_TAB);
				break;
			default:
				break;
			}
		}
	}
	
	public void info1(){
		
		button1.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Toast.makeText(MainActivity.this, "The Calculator is a basic everyday calculator.", Toast.LENGTH_LONG).show();
				Toast.makeText(MainActivity.this, "It can do the 4 operations, squares and roots as well as having a memory function.", Toast.LENGTH_LONG).show();
			}
		});
	}
	public void info2(){
		
		button2.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(
						MainActivity.this,
						"Pressing 'start timer' will start the game where you press the button as many times as you can in 30 seconds.",
						Toast.LENGTH_LONG).show();
				Toast.makeText(
						MainActivity.this,
						"You do not have to start the timer to use the counter",
						Toast.LENGTH_LONG).show();
				Toast.makeText(
						MainActivity.this,
						"Without the timer, the counter will function as normal",
						Toast.LENGTH_LONG).show();
				
			}
	
	
			
		});
	}
	
	public void info3(){
		
		button3.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Toast.makeText(MainActivity.this, "The Exchanger can exchange currencies from around the world.", Toast.LENGTH_LONG).show();
				Toast.makeText(MainActivity.this, "Select your currencies, type your amount of money in the gap, and press the convert symbol.", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if ((event.getAction() == KeyEvent.ACTION_DOWN) && (event.getKeyCode() == KeyEvent.KEYCODE_BACK)) {
			quitDialog();
		}
		return super.dispatchKeyEvent(event);
	}

	/**
	 * �˳��Ի���
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
