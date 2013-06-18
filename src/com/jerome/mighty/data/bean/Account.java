/**
 * Account.java
 * com.jerome.mighty.data.bean
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013-6-18 		Jerry
 *
 * Copyright (c) 2013, JEROME All Rights Reserved.
 */

package com.jerome.mighty.data.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * ClassName:Account Function: TODO ADD FUNCTION
 * 
 * @author Jerry
 * @version
 * @Date 2013-6-18 上午10:19:48
 * 
 * @see
 */
@DatabaseTable(tableName = "tb_accounts")
public class Account {

	@DatabaseField(canBeNull=false)
	private String name;
}
