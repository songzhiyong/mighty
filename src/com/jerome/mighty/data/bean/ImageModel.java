/**
 * ImageModel.java
 * com.jerome.mighty.data
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-6-14 		Jerry
 *
 * Copyright (c) 2013, JEROME All Rights Reserved.
 */

package com.jerome.mighty.data.bean;

import java.io.Serializable;

/**
 * ClassName:ImageModel<br>
 * Function: TODO ADD FUNCTION<br>
 * Reason: TODO ADD REASON<br>
 * 
 * @author Jerry
 * @version
 * @since Ver 1.1
 * @Date 2013-6-14 下午5:09:05
 * 
 * @see
 */
public class ImageModel implements Serializable {

	private static final long serialVersionUID = -5157167898565087069L;
	private String name;
	private String desc;
	private long time;
	private String url;
	private String localPath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

}
