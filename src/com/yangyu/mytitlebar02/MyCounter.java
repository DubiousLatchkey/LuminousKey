package com.yangyu.mytitlebar02;

import android.os.CountDownTimer;
import android.widget.Toast;

public class MyCounter extends CountDownTimer {

	public static double three;
	public static double four;
	public static double five;
	public static double six;
	public static double newI = PersonInfoActivity.i;

	public MyCounter(long a, long b) {
		super(a, b);
	}

	@Override
	public void onFinish() {
		newI = PersonInfoActivity.i;
		System.out.println("Timer Completed.");
		PersonInfoActivity.timer.setText("Timer Completed");
		PersonInfoActivity.pps.setText(String.format("%.2f", newI / 30));

		if (PersonInfoActivity.y < PersonInfoActivity.i / 30) {
			PersonInfoActivity.y = PersonInfoActivity.i / 30;
			//Toast.makeText(null, "New Record!", Toast.LENGTH_SHORT).show();

		}

	}

	@Override
	public void onTick(long a) {
		PersonInfoActivity.timer.setText((a / 1000) + "");
		System.out.println("Timer  : " + (a / 1000));
	}

}
