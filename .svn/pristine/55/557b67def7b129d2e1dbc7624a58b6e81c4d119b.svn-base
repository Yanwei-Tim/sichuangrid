package com.tianque.plugin.account.state;

public interface ThreeRecordsIssueState {

	final static Integer DEALING = 1; // 办理中 issues
	final static Integer CLOSED = 250;// 关闭
	final static Integer DEALOVER = 200;//
	final static Integer COMPLETE = 150;// 事件办结（待验证）
	final static Integer VERIFICATION = 300; // 事件结案，验证通过
	final static Integer GRADEDELAY = 301;// 已评分，仅仅做页面显示不做实际存储

	public final static int RED_CARD_SUPERVISE = 200;
	public final static int YELLOW_CARD_SUPERVISE = 100;
	public final static int NORMAL_SUPERVISE = 1;
	public final static int NO_SUPERVISE = -1;
	public static final int ISSUE_EXTENDED = 1;// 超期督办
	public static final int ISSUE_NOT_EXTENDED = 0;// 取消督办

	public final static int UNGIVETO_CODE = 80;
	public final static int NEWISSUE_CODE = 100;
	public final static int UNCONCEPTED_CODE = 110;// 待受理
	public final static int DEALING_CODE = 120; // 处理中
	public final static int UNREAD_CODE = 140; // 待阅读
	public final static int STEPCOMPLETE_CODE = 500; // 转到下一个流程（新增一条流程信息）前，设置的值
	public final static int PERIOD_CODE = 600;// 阶段办结
	public final static int SUBSTANCE_CODE = 700;// 实质办结
	public final static int ISSUEVERIFICATION_CODE = 800;// 待验证
	public final static int ISSUEUNVERIFICATION_CODE = 900;// 验证未通过,回退 900
	public final static int ISSUECOMPLETE_CODE = 1000; // 已验证事件（验证通过的）
	/** 备案 即完成状态 */
	public final static int BACKUPS_CODE = 1100;
	public final static int UNSUPPORT_CODE = 160; // 待协助办理
	public final static int SUPPORTING_CODE = 170; // 协助办理中
}
