/**
 * LoginActivity.java
 * com.jerome.mighty.app
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-6-18 		Jerry
 *
 * Copyright (c) 2013, JEROME All Rights Reserved.
 */

package com.jerome.mighty.app;

import android.os.Bundle;
import android.view.Window;

import com.jerome.base.BaseActivity;
import com.jerome.mighty.R;

/**
 * ClassName:LoginActivity<br>
 * Function: TODO ADD FUNCTION<br>
 * 
 * @author Jerry
 * @version
 * @Date 2013-6-18 上午8:59:56
 * 
 * @see
 */
public class LoginActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_login);
	}

}
