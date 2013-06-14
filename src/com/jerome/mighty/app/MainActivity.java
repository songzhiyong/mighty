package com.jerome.mighty.app;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.jerome.mighty.R;
import com.jerome.mighty.fragments.LeftMenuFragment;
import com.jerome.mighty.temp.ColorFragment;
import com.jerome.mighty.temp.SampleListFragment;
import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.jfeinstein.jazzyviewpager.JazzyViewPager.TransitionEffect;
import com.jfeinstein.jazzyviewpager.OutlineContainer;
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
	private static final String[] CONTENT = new String[] { "1", "2", "3", "4",
			"5", "6" };
	protected ListFragment mFrag;
	private JazzyViewPager vp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// viewpager/////////////////////////////////
		vp = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		vp.setTransitionEffect(TransitionEffect.CubeOut);
		vp.setAdapter(new ColorPagerAdapter(getSupportFragmentManager()));
		// vp.setAdapter(new MainAdapter());
		vp.setPageMargin(30);
		vp.setCurrentItem(0);
		setBehindContentView(R.layout.menu_frame);
		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
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

	public class ColorPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> mFragments;
		private final int[] COLORS = new int[] { R.color.red, R.color.green,
				R.color.blue, R.color.holo_blue, R.color.black };

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			Object obj = super.instantiateItem(container, position);
			vp.setObjectForPosition(obj, position);
			return obj;
		}

		public ColorPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			for (int color : COLORS)
				mFragments.add(new ColorFragment(color));
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

	private class MainAdapter extends PagerAdapter {
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			TextView text = new TextView(MainActivity.this);
			text.setGravity(Gravity.CENTER);
			text.setTextSize(30);
			text.setTextColor(Color.WHITE);
			text.setText("Page " + position);
			text.setPadding(30, 30, 30, 30);
			int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
					(int) Math.floor(Math.random() * 128) + 64,
					(int) Math.floor(Math.random() * 128) + 64);
			text.setBackgroundColor(bg);
			container.addView(text, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			vp.setObjectForPosition(text, position);
			return text;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object obj) {
			container.removeView(vp.findViewFromObject(position));
		}

		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			if (view instanceof OutlineContainer) {
				return ((OutlineContainer) view).getChildAt(0) == obj;
			} else {
				return view == obj;
			}
		}
	}

}
