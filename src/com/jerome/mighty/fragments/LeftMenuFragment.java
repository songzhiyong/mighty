/**
 * LeftMenu.java
 * com.jerome.mighty.fragments
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-6-14 		Jerry
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */

package com.jerome.mighty.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jerome.mighty.R;
import com.jerome.mighty.app.MainActivity;
import com.jerome.mighty.temp.ColorFragment;

/**
 * ClassName:LeftMenu <br>
 * Function: TODO ADD <br>
 * FUNCTION Reason: TODO ADD <br>
 * REASON
 * 
 * @author Jerry
 * @version
 * @since Ver 1.1
 * @Date 2013-6-14 上午10:53:04
 * 
 * @see
 */
public class LeftMenuFragment extends ListFragment {
	private String[] menuLabels;
	private String baseIconName = "navigation_tab_";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		menuLabels = getResources().getStringArray(R.array.left_menu);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < menuLabels.length; i++) {
			adapter.add(new SampleItem(menuLabels[i],
					getResourceId(baseIconName + (i + 1))));
		}
		setListAdapter(adapter);

	}

	private class SampleItem {
		public String tag;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView
					.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}

	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = new ColorFragment(position);
		if (newContent != null)
			switchFragment(position);
	}

	private void switchFragment(int pos) {
		if (getActivity() == null)
			return;
		if (getActivity() instanceof MainActivity) {
			MainActivity activity = (MainActivity) getActivity();
			activity.switchContent(pos);
		}
	}

	private int getResourceId(String name) {
		return getResources().getIdentifier(name, "drawable",
				getActivity().getPackageName());
	}
}
