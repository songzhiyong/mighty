/**
 * PicsFragment.java
 * com.jerome.mighty.temp
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-6-14 		Jerry
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */

package com.jerome.mighty.temp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jerome.mighty.R;

/**
 * ClassName:PicsFragment<br>
 * Function: TODO ADD FUNCTION<br>
 * Reason: TODO ADD REASON<br>
 * 
 * @author Jerry
 * @version
 * @since Ver 1.1
 * @Date 2013-6-14 下午4:06:23
 * 
 * @see
 */
public class PicsFragment extends ListFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_pics, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < 20; i++) {
			adapter.add(new SampleItem("Sample List",
					android.R.drawable.ic_menu_search));
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
						R.layout.item_pic, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.iv_pic);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.tv_text);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}
}
