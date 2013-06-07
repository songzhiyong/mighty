package com.jerome.mighty.app;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.jerome.mighty.R;
import com.jerome.mighty.temp.ColorFragment;
import com.jerome.mighty.temp.SampleListFragment;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends SlidingFragmentActivity {
	protected ListFragment mFrag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		mFrag = new SampleListFragment();
		t.replace(R.id.menu_frame, mFrag);
		t.commit();
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		ViewPager vp = (ViewPager) findViewById(R.id.pager);
		vp.setAdapter(new ColorPagerAdapter(getSupportFragmentManager()));
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageScrollStateChanged(int arg0) {
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				default:
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_MARGIN);
					break;
				}
			}
		});
		vp.setCurrentItem(0);
		setBehindContentView(R.layout.menu_frame);
		// TabPageIndicator indicator = (TabPageIndicator)
		// findViewById(R.id.indicator);
		// indicator.setViewPager(vp);
	}

	public class ColorPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> mFragments;
		private final int[] COLORS = new int[] { R.color.red, R.color.green,
				R.color.blue, R.color.white, R.color.black };

		public ColorPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			for (int color : COLORS)
				mFragments.add(new ColorFragment(color));
		}

		public int getCount() {
			return mFragments.size();
		}

		public Fragment getItem(int position) {
			return mFragments.get(position);
		}
	}

}
