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
 *	����������������ҳActivityҳ��
 */
public class HomeActivity extends ActivityGroup implements OnClickListener{
	//����֡���ֶ���
	private FrameLayout mContent;
	
	//����ͼƬ��ť����
	
	//�����ʾ��


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		
		//�õ���ť����

		
		//��ť���ü���

		
		//��ʼ��Ĭ����ʾ��ҳ��

	}
	
	/**
	 * �����ͼ
	 */
	public void addView(String id, Class<?> clazz) {
		Intent intent = new Intent(this, clazz);
		//�Ƴ�������������е����
		mContent.removeAllViews();
		mContent.addView(getLocalActivityManager().startActivity(id, intent).getDecorView());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}	
}


	/**
	 * ��ʾ�ҵ�����ҳ��
	 */

	
	/**
	 * ��ʾ�ҵı�ǩҳ��
	 */


























