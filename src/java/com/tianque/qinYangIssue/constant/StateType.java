package com.tianque.qinYangIssue.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:青羊事件对接常量类，包含处理的状态等
 * @author zhangyouwei@hztianque.com
 * @date: 2014-12-14 下午04:52:46
 */
public class StateType {
	/** 数据处理状态待处理 */
	public static final Integer SYNC_PENDING = 0;
	/** 数据处理状态同步成功 */
	public static final Integer SYNC_SUCCESS = 1;
	/** 数据处理状态数据异常 */
	public static final Integer SYNC_DATA_ERROR = 2;
	/** 数据处理状态处理 失败 */
	public static final Integer SYNC_FAILURE = 3;

	public static final Integer UNCONCEPTED_CODE_TYPE = 0;
	public final static Integer UNCONCEPTED_CODE = 110;// 待受理

	public final static Integer DEALING_CODE_TYPE = 1; // 处理中
	public final static Integer DEALING_CODE = 120; // 处理中

	public final static Integer UNREAD_CODE_TYPE = 2; // 待阅读
	public final static Integer UNREAD_CODE = 140; // 待阅读

	public final static Integer STEPCOMPLETE_CODE_TYPE = 3; // 转到下一个流程（新增一条流程信息）前，设置的值
	public final static Integer STEPCOMPLETE_CODE = 500; // 转到下一个流程（新增一条流程信息）前，设置的值

	public final static Integer ISSUEVERIFICATION_CODE_TYPE = 4;// 待验证
	public final static Integer ISSUEVERIFICATION_CODE = 800;// 待验证

	public final static Integer ISSUEUNVERIFICATION_CODE_TYPE = 5;// 验证未通过,回退
																	// 900
	public final static Integer ISSUEUNVERIFICATION_CODE = 900;// 验证未通过,回退 900

	public final static Integer ISSUECOMPLETE_CODE_TYPE = 6; // 已验证事件（验证通过的）
	public final static Integer ISSUECOMPLETE_CODE = 1000; // 已验证事件（验证通过的）

	/** 每次循环处理数据的个数 */
	public static final int PAGE_SIZE = 1000;

	public static Map<Integer, Integer> STATUSCODE = new HashMap<Integer, Integer>();

	static {
		STATUSCODE.put(UNCONCEPTED_CODE, UNCONCEPTED_CODE_TYPE);
		STATUSCODE.put(DEALING_CODE, DEALING_CODE_TYPE);
		STATUSCODE.put(UNREAD_CODE, UNREAD_CODE_TYPE);
		STATUSCODE.put(STEPCOMPLETE_CODE, STEPCOMPLETE_CODE_TYPE);
		STATUSCODE.put(ISSUEVERIFICATION_CODE, ISSUEVERIFICATION_CODE_TYPE);
		STATUSCODE.put(ISSUEUNVERIFICATION_CODE, ISSUEUNVERIFICATION_CODE_TYPE);
		STATUSCODE.put(ISSUECOMPLETE_CODE, ISSUECOMPLETE_CODE_TYPE);
	}
}
