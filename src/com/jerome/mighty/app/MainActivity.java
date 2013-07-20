package com.jerome.mighty.app;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;

import com.jerome.mighty.R;
import com.jerome.mighty.fragments.LeftMenuFragment;
import com.jerome.mighty.temp.ColorFragment;
import com.jerome.mighty.temp.PicsFragment;
import com.jerome.mighty.temp.WebViewFragment;
import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.jfeinstein.jazzyviewpager.JazzyViewPager.TransitionEffect;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 
 * ClassName:MainActivity <br >
 * Function: TODO ADD FUNCTION <br >
 * Reason: TODO ADD REASON <br >
 * 
 * @author Jerome Song
 * @version
 * @since Ver 1.1
 * @Date 2013 2013-6-7 下午9:07:50
 * 
 * @see
 */
public class MainActivity extends SlidingFragmentActivity {
	private static final String[] CONTENT = new String[] { "本地", "跟帖", "图片",
			"话题", "投票", "音乐" };
	protected Fragment mFrag;
	private JazzyViewPager vp;
	private TabPageIndicator indicator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_main);
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		mFrag = new LeftMenuFragment();
		t.replace(R.id.menu_frame, mFrag);
		t.commit();
		// customize the SlidingMenu////////////////////
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		// sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// sm.setMode(SlidingMenu.LEFT_RIGHT);
		// sm.setSecondaryMenu(R.layout.menu_frame_two);
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.menu_frame_two, new SampleListFragment())
		// .commit();

		getSlidingMenu().setBehindWidth(
				(int) (getResources().getDisplayMetrics().widthPixels * 0.5f));
		getSlidingMenu().requestLayout();

		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// viewpager/////////////////////////////////
		vp = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		vp.setTransitionEffect(TransitionEffect.CubeOut);
		vp.setAdapter(new ColorPagerAdapter(getSupportFragmentManager()));
		// vp.setAdapter(new MainAdapter());
		vp.setPageMargin(30);
		vp.setCurrentItem(0);
		setBehindContentView(R.layout.menu_frame);
		indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(vp);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		indicator.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageScrollStateChanged(int arg0) {
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					Log.i("MainActivity", "TOUCHMODE_FULLSCREEN");
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				default:
					Log.i("MainActivity", "TOUCHMODE_MARGIN");
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_MARGIN);
					break;
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			getSlidingMenu().toggle();
		}
		return super.onKeyDown(keyCode, event);
	}

	public class ColorPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> mFragments;
		private final int[] COLORS = new int[] { R.color.red, R.color.green,
				R.color.blue, R.color.holo_blue, R.color.black, R.color.gray };

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			Object obj = super.instantiateItem(container, position);
			vp.setObjectForPosition(obj, position);
			return obj;
		}

		public ColorPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			for (int color : COLORS) {
				if (color == R.color.holo_blue) {
					mFragments.add(new WebViewFragment());
				} else if (color == R.color.blue) {
					mFragments.add(new PicsFragment());
				} else {
					mFragments.add(new ColorFragment(color));
				}
			}
		}

		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length].toUpperCase();
		}

		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
	}

	public void switchContent(int pos) {
		vp.setCurrentItem(pos);
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);
	}

	public void showTopIndicator() {
		indicator.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.slide_in_from_top));
		indicator.setVisibility(View.VISIBLE);
	}

	public void hideTopIndicator() {
		indicator.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.slide_out_to_top));
		indicator.setVisibility(View.GONE);
	}

}
