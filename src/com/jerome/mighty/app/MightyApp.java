package com.jerome.mighty.app;

import com.jerome.base.BaseApplication;
import com.jerome.utils.Start;

/**
 * 
 * ClassName:MightyApp <br >
 * Function: TODO ADD FUNCTION <br >
 * Reason: TODO ADD REASON <br >
 * 
 * @author Jerome Song
 * @version
 * @since Ver 1.1
 * @Date 2013 2013-6-7 下午9:08:04
 * 
 * @see
 */
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
