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
 * Copyright (c) 2013, JEROME All Rights Reserved.
 */

package com.jerome.mighty.temp;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jerome.mighty.R;
import com.jerome.utils.media.BitmapUtils;

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
	private int mSelectedRow;
	private QuickAction mQuickAction;

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
		mQuickAction = new QuickAction(getActivity());
		ActionItem addItem = new ActionItem(0, "Add", getResources()
				.getDrawable(R.drawable.ic_add));
		ActionItem acceptItem = new ActionItem(1, "Accept", getResources()
				.getDrawable(R.drawable.ic_accept));
		ActionItem uploadItem = new ActionItem(2, "Upload", getResources()
				.getDrawable(R.drawable.ic_up));

		acceptItem.setSelected(true);
		mQuickAction.addActionItem(addItem);
		mQuickAction.addActionItem(acceptItem);
		mQuickAction.addActionItem(uploadItem);
		// setup the action item click listener
		mQuickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction quickAction, int pos,
							int actionId) {
						ActionItem actionItem = quickAction.getActionItem(pos);

						if (actionId == 0) { // Add item selected
							Toast.makeText(getActivity(),
									"Add item selected on row " + mSelectedRow,
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(
									getActivity(),
									actionItem.getTitle()
											+ " item selected on row "
											+ mSelectedRow, Toast.LENGTH_SHORT)
									.show();
						}
					}
				});

		// setup on dismiss listener, set the icon back to normal
		mQuickAction.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				// mMoreIv.setImageResource(R.drawable.ic_list_more);
			}
		});
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mSelectedRow = position;
				mQuickAction.show(view);
			}
		});
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
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			ImageView icon = (ImageView) convertView.findViewById(R.id.iv_pic);
			icon.setImageBitmap(BitmapUtils.getRoundedCornerBitmap(bitmap, 5,
					bitmap.getWidth(), bitmap.getHeight()));
			TextView title = (TextView) convertView.findViewById(R.id.tv_text);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}
}
