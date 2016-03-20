package com.liuzhimin.activity;

import java.util.ArrayList;

import com.liuzhimin.adapter.BasePagerAdapter;
import com.liuzhimin.healthmanager.R;
import com.liuzhimin.healthmanager.SharedConfig;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

//绗竴娆¤繍琛岀殑寮曞椤典唬鐮�
public class WelcomeActivity extends Activity implements OnPageChangeListener,
		OnClickListener {
	private Context context;
	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private Button loginButton;
	private Button regButton;
	private LinearLayout indicatorLayout;
	private ArrayList<View> views;
	private ImageView[] indicators = null;
	private int[] images;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		context = this;
		// 璁剧疆寮曞鍥剧墖
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  浠呴渶鍦ㄨ繖璁剧疆鍥剧墖 鎸囩ず鍣ㄥ拰page鑷姩娣诲姞
		images = new int[] { R.drawable.welcome_01, R.drawable.welcome_02,
				R.drawable.welcome_03 };
		initView();
	}

	// 鍒濆鍖栬鍥�
	private void initView() {
		// 瀹炰緥鍖栬鍥炬帶浠�
		viewPager = (ViewPager) findViewById(R.id.viewpage);
		loginButton = (Button) findViewById(R.id.login_Button);
		regButton = (Button) findViewById(R.id.reg_Button);
		loginButton.setOnClickListener(this);
		indicatorLayout = (LinearLayout) findViewById(R.id.indicator);
		views = new ArrayList<View>();
		indicators = new ImageView[images.length]; // 瀹氫箟鎸囩ず鍣ㄦ暟缁勫ぇ锟�?
		for (int i = 0; i < images.length; i++) {
			// 寰幆鍔犲叆鍥剧墖
			ImageView imageView = new ImageView(context);
			imageView.setBackgroundResource(images[i]);
			views.add(imageView);
			// 寰幆鍔犲叆鎸囩ず鍣�
			indicators[i] = new ImageView(context);
			indicators[i].setBackgroundResource(R.drawable.indicators_default);
			if (i == 0) {
				indicators[i].setBackgroundResource(R.drawable.indicators_now);
			}
			indicatorLayout.addView(indicators[i]);
		}
		pagerAdapter = new BasePagerAdapter(views);
		viewPager.setAdapter(pagerAdapter); // 璁剧疆閫傞厤锟�?
		viewPager.setOnPageChangeListener(this);
	}
	//鎸夐挳鐨勭偣鍑讳簨锟�?
	@Override
	public void onClick(View v) {
		//鐧诲綍浜嬩欢
		if (v.getId() == R.id.login_Button) {
			SharedPreferences shared = new SharedConfig(this).GetConfig();
			Editor editor = shared.edit();
			editor.putBoolean("First", false);
			editor.commit();
			
			startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
			overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			this.finish();
		}
		//娉ㄥ唽浜嬩欢
		if (v.getId() == R.id.reg_Button){
			
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	// 鐩戝惉viewpage
	@Override
	public void onPageSelected(int arg0) {
		// 鏄剧ず锟�?鍚庝竴涓浘鐗囨椂鏄剧ず鎸夐挳
		if (arg0 == indicators.length - 1) {
			loginButton.setVisibility(View.VISIBLE);
			regButton.setVisibility(View.VISIBLE);
		} else {
			loginButton.setVisibility(View.INVISIBLE);
			regButton.setVisibility(View.INVISIBLE);
		}
		// 鏇存敼鎸囩ず鍣ㄥ浘鐗�
		for (int i = 0; i < indicators.length; i++) {
			indicators[arg0].setBackgroundResource(R.drawable.indicators_now);
			if (arg0 != i) {
				indicators[i]
						.setBackgroundResource(R.drawable.indicators_default);
			}
		}
	}
}
