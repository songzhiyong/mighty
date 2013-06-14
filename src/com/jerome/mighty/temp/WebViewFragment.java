/**
 * WebViewFragment.java
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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jerome.mighty.app.MainActivity;

/**
 * ClassName:WebViewFragment <br>
 * Function: TODO ADD <br>
 * FUNCTION Reason: TODO ADD<br>
 * REASON
 * 
 * @author Jerry
 * @version
 * @since Ver 1.1
 * @Date 2013-6-14 下午3:14:26
 * 
 * @see
 */
public class WebViewFragment extends Fragment {

	private WebView webView;
	private boolean isHide;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		webView = new WebView(getActivity());
		return webView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		webView.loadUrl("http://www.hao123.com");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.requestFocus();
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
			}
		});
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		final GestureDetector gd = new GestureDetector(sogl);
		webView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gd.onTouchEvent(event);
				return false;
			}
		});
		super.onActivityCreated(savedInstanceState);
	}

	GestureDetector.SimpleOnGestureListener sogl = new GestureDetector.SimpleOnGestureListener() {
		public boolean onDown(MotionEvent event) {
			return true;
		}

		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {
			if (event1.getRawY() > event2.getRawY()
					&& event1.getRawX() - event2.getRawX() < 30) {
				if (!isHide) {
					((MainActivity) getActivity()).hideTopIndicator();
					isHide = true;
				}
			} else {
				if (isHide) {
					((MainActivity) getActivity()).showTopIndicator();
					isHide = false;
				}
			}
			return true;
		}
	};
}
