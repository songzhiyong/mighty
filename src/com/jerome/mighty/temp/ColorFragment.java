package com.jerome.mighty.temp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jerome.mighty.R;

public class ColorFragment extends Fragment {

	private int mColorRes = -1;

	public ColorFragment() {
		this(R.color.white);
	}

	public ColorFragment(int colorRes) {
		mColorRes = colorRes;
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null)
			mColorRes = savedInstanceState.getInt("mColorRes");
		int color = getResources().getColor(mColorRes);
		TextView tv = new TextView(getActivity());
		tv.setBackgroundColor(color);
		tv.setText("TestFragment");
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("mColorRes", mColorRes);
	}

}
