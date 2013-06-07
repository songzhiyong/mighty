package com.jerome.mighty.app;

import com.jerome.base.BaseApplication;
import com.jerome.utils.Start;

public class MightyApp extends BaseApplication {

	@Override
	protected void setActivitySwitchMode() {
		ACTIVITY_SWITCH_MODE = Start.MODE_LEFT_IN_RIGHT_OUT;
	}

	@Override
	protected void setDistribute() {
		STAT_DISTRIBUTE = DEBUG;
	}

}
