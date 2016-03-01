package com.tianque.mobile.vo;

import java.util.HashMap;

public class Remind {
	/** 未读信息 */
	public static String UNREAD_MESSAGES = "unread_messages";
	/** 待办事项 */
	public static String TO_DO_TASKS = "to_do_tasks";
	/** 报表 */
	public static String WORKING_REPORTS = "working_reports";
	/** 收发文 */
	public static String RECEIVE_DOCUMENTS = "receive_doucuments";

	/** 待验证 */
	public static String VERIFICATION_NUM = "verification_num";

	/** 待评分 */
	public static String CHECKGRADE_NUM = "checkgrade_num";

	public HashMap<String, Integer> dataCount = new HashMap<String, Integer>();

	public HashMap<String, Integer> getDataCount() {
		return dataCount;
	}

	public void setDataCount(HashMap<String, Integer> dataCount) {
		this.dataCount = dataCount;
	}

	public void putCount(String key, int count) {
		dataCount.put(key, Integer.valueOf(count));
	}

}
