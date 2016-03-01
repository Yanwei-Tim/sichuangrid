package com.tianque.plugin.account.state;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.tianque.plugin.account.constants.LedgerActionType;

public class ThreeRecordsIssueOperate implements Serializable {

	private String desc;

	private int code;

	private boolean increase;

	private double cent;

	private String mobileCode;

	public final static int COMMENT_CODE = 1;
	private final static int TMP_COMMENT_CODE = 5;
	private final static int REPORT_TO_CODE = 11;
	private final static int ASSIGN_CODE = 21;
	private final static int GIVETO_CODE = 26;
	private final static int LEVEL_ASSIGN_CODE = 28;
	private final static int VERIFICATION_CODE = 30;
	private final static int COMPLETE_CODE = 31;
	private final static int BACKUPS_CODE = 32;
	private final static int SUBMIT_CODE = 41;
	private final static int DECLARE_CODE = 45;
	private final static int TURN_CODE = 50;

	public final static int INSTRUCT_CODE = 51;
	public final static int CONCEPT_CODE = 61;
	private final static int READ_CODE = 71;
	private final static int SUPPORT_CODE = 75;
	public final static int NORMAL_SUPERVISE_CODE = 81;
	public final static int YELLOW_SUPERVISE_CODE = 83;
	public final static int RED_SUPERVISE_CODE = 86;
	public final static int CANCEL_SUPERVISE_CODE = 88;
	public final static int FED_BACK_CODE = 91;
	public final static int DEAL_OVER_CODE = 101;
	public final static int CLOSE_CODE = 111;
	public final static int BACK_CODE = 200;
	public final static int URGENT_CODE = 1001;
	public final static int CANCEL_URGENT_CODE = 1011;
	public final static int HISTORIC_CODE = 1101;
	public final static int CANCEL_HISTORIC_CODE = 1111;
	/*
	 * 阶段办结
	 */
	private final static int PERIOD_CODE = 92;
	/*
	 * 程序办结
	 */
	private final static int PROGRAM_CODE = 93;

	public final static int FEEDBACK_CODE = 42;

	// 事件对接log办理描述
	public final static String ISSUE_JOINT_LOG_DESCRIPTION = "新增";
	public final static int CMS_DISTRIBUTION = 1302;// 转入坐席分流中心

	public final static Map<Integer, ThreeRecordsIssueOperate> allOperates = new HashMap<Integer, ThreeRecordsIssueOperate>();
	static {
		allOperates.put(TMP_COMMENT_CODE, new ThreeRecordsIssueOperate(
				TMP_COMMENT_CODE, "添加措施", 0, true,
				LedgerActionType.TMP_COMMENT_CODE));
		allOperates.put(COMMENT_CODE, new ThreeRecordsIssueOperate(
				COMMENT_CODE, "办理中", 0, true, LedgerActionType.COMMENT_CODE));
		allOperates.put(REPORT_TO_CODE, new ThreeRecordsIssueOperate(
				REPORT_TO_CODE, "上报指挥中心", 1, true,
				LedgerActionType.REPORT_TO_CODE));
		allOperates.put(ASSIGN_CODE, new ThreeRecordsIssueOperate(ASSIGN_CODE,
				"交办", 1, true, LedgerActionType.ASSIGN_CODE));

		allOperates.put(COMPLETE_CODE,
				new ThreeRecordsIssueOperate(COMPLETE_CODE, "实质性办结", 1, true,
						LedgerActionType.COMPLETE_CODE));
		allOperates.put(PERIOD_CODE, new ThreeRecordsIssueOperate(PERIOD_CODE,
				"阶段性办结", 1, true, LedgerActionType.PERIOD_CODE));
		allOperates.put(PROGRAM_CODE, new ThreeRecordsIssueOperate(
				PROGRAM_CODE, "程序性办结", 1, true, LedgerActionType.PROGRAM_CODE));
		allOperates.put(VERIFICATION_CODE, new ThreeRecordsIssueOperate(
				VERIFICATION_CODE, "验证", 1, true,
				LedgerActionType.VERIFICATION_CODE));
		allOperates.put(SUBMIT_CODE, new ThreeRecordsIssueOperate(SUBMIT_CODE,
				"上报", 1, true, LedgerActionType.SUBMIT_CODE));

		allOperates.put(DECLARE_CODE, new ThreeRecordsIssueOperate(
				DECLARE_CODE, "申报", 1, true, LedgerActionType.DECLARE_CODE));

		allOperates.put(TURN_CODE, new ThreeRecordsIssueOperate(TURN_CODE,
				"转办", 1, true, LedgerActionType.TURN_CODE));
		allOperates.put(SUPPORT_CODE, new ThreeRecordsIssueOperate(
				SUPPORT_CODE, "协助办理", 1, true, LedgerActionType.SUPPORT_CODE));
		allOperates
				.put(INSTRUCT_CODE, new ThreeRecordsIssueOperate(INSTRUCT_CODE,
						"领导批示", 0, true, LedgerActionType.INSTRUCT_CODE));
		allOperates.put(LEVEL_ASSIGN_CODE, new ThreeRecordsIssueOperate(
				LEVEL_ASSIGN_CODE, "交办", 1, true,
				LedgerActionType.LEVEL_ASSIGN_CODE));
		allOperates.put(CONCEPT_CODE, new ThreeRecordsIssueOperate(
				CONCEPT_CODE, "受理", 0, true, LedgerActionType.CONCEPT_CODE));
		allOperates.put(READ_CODE, new ThreeRecordsIssueOperate(READ_CODE,
				"阅读", 0, true, LedgerActionType.READ_CODE));
		allOperates.put(NORMAL_SUPERVISE_CODE, new ThreeRecordsIssueOperate(
				NORMAL_SUPERVISE_CODE, "普通督办", 0, false,
				LedgerActionType.NORMAL_SUPERVISE_CODE));
		allOperates.put(YELLOW_SUPERVISE_CODE, new ThreeRecordsIssueOperate(
				YELLOW_SUPERVISE_CODE, "黄牌督办", 5, false,
				LedgerActionType.YELLOW_SUPERVISE_CODE));
		allOperates.put(RED_SUPERVISE_CODE, new ThreeRecordsIssueOperate(
				RED_SUPERVISE_CODE, "红牌督办", 10, false,
				LedgerActionType.RED_SUPERVISE_CODE));
		allOperates.put(CANCEL_SUPERVISE_CODE, new ThreeRecordsIssueOperate(
				CANCEL_SUPERVISE_CODE, "取消督办", 0, true,
				LedgerActionType.CANCEL_SUPERVISE_CODE));
		allOperates.put(FED_BACK_CODE, new ThreeRecordsIssueOperate(
				FED_BACK_CODE, "反馈", 0, true, LedgerActionType.FED_BACK_CODE));
		allOperates
				.put(DEAL_OVER_CODE, new ThreeRecordsIssueOperate(
						DEAL_OVER_CODE, "完成", 1, true,
						LedgerActionType.DEAL_OVER_CODE));
		allOperates.put(GIVETO_CODE, new ThreeRecordsIssueOperate(GIVETO_CODE,
				"交办", 1, true, LedgerActionType.GIVETO_CODE));
		allOperates.put(CLOSE_CODE, new ThreeRecordsIssueOperate(CLOSE_CODE,
				"关闭", 0, true, LedgerActionType.CLOSE_CODE));
		allOperates.put(BACK_CODE, new ThreeRecordsIssueOperate(BACK_CODE,
				"回退", 0, true, LedgerActionType.BACK_COE));
		allOperates.put(BACKUPS_CODE, new ThreeRecordsIssueOperate(
				BACKUPS_CODE, "备案", 0, true));
		allOperates.put(URGENT_CODE, new ThreeRecordsIssueOperate(URGENT_CODE,
				"加急", 0, true, LedgerActionType.URGENT_CODE));
		allOperates.put(CANCEL_URGENT_CODE, new ThreeRecordsIssueOperate(
				CANCEL_URGENT_CODE, "取消加急", 0, true,
				LedgerActionType.CANCEL_URGENT_CODE));
		allOperates.put(HISTORIC_CODE, new ThreeRecordsIssueOperate(
				HISTORIC_CODE, "设置历史遗留", 0, true,
				LedgerActionType.HISTORIC_CODE));
		allOperates.put(CANCEL_HISTORIC_CODE, new ThreeRecordsIssueOperate(
				CANCEL_HISTORIC_CODE, "取消历史遗留", 0, true,
				LedgerActionType.CANCEL_HISTORIC_CODE));
		allOperates.put(CMS_DISTRIBUTION, new ThreeRecordsIssueOperate(
				CMS_DISTRIBUTION, "转入分流中心", 0, true,
				LedgerActionType.CMS_DISTRIBUTION));
	}

	public final static Map<String, ThreeRecordsIssueOperate> pressionOperate = new HashMap<String, ThreeRecordsIssueOperate>();
	static {
		pressionOperate.put("normalIssue", allOperates
				.get(NORMAL_SUPERVISE_CODE));
		pressionOperate.put("yellowCardIssue", allOperates
				.get(YELLOW_SUPERVISE_CODE));
		pressionOperate
				.put("redCardIssue", allOperates.get(RED_SUPERVISE_CODE));
		pressionOperate.put("cancleSuperviseIssue", allOperates
				.get(CANCEL_SUPERVISE_CODE));
	}

	public final static ThreeRecordsIssueOperate TMPCOMMENT = allOperates
			.get(TMP_COMMENT_CODE);

	/**
	 * 办理中
	 */
	public final static ThreeRecordsIssueOperate COMMENT = allOperates
			.get(COMMENT_CODE);
	/**
	 * 上报指挥中心
	 */
	public final static ThreeRecordsIssueOperate REPORT_TO = allOperates
			.get(REPORT_TO_CODE);
	/**
	 * 派单（交办）
	 */
	public final static ThreeRecordsIssueOperate ASSIGN = allOperates
			.get(ASSIGN_CODE);
	/**
	 * 下单（指挥中心下派）
	 */
	public final static ThreeRecordsIssueOperate GIVETO = allOperates
			.get(GIVETO_CODE);
	/**
	 * 受理
	 */
	public final static ThreeRecordsIssueOperate CONCEPT = allOperates
			.get(CONCEPT_CODE);

	/**
	 * 处理完成，用于需要反馈的流程
	 */
	public final static ThreeRecordsIssueOperate DEALOVER = allOperates
			.get(DEAL_OVER_CODE);

	/**
	 * 反馈
	 */
	public final static ThreeRecordsIssueOperate FEEDBACK = allOperates
			.get(BACK_CODE);
	/**
	 * 上报
	 */
	public final static ThreeRecordsIssueOperate SUBMIT = allOperates
			.get(SUBMIT_CODE);
	/**
	 * 转办
	 */
	public final static ThreeRecordsIssueOperate TURN = allOperates
			.get(TURN_CODE);

	/**
	 * 申报
	 */
	public final static ThreeRecordsIssueOperate DECLARE = allOperates
			.get(DECLARE_CODE);
	/**
	 * 从办
	 */
	public final static ThreeRecordsIssueOperate SUPPORT = allOperates
			.get(SUPPORT_CODE);
	/**
	 * 批示
	 */
	public final static ThreeRecordsIssueOperate INSTRUCT = allOperates
			.get(INSTRUCT_CODE);
	/**
	 * 阅读
	 */
	public final static ThreeRecordsIssueOperate READ = allOperates
			.get(READ_CODE);
	/**
	 * 红牌督办
	 */
	public final static ThreeRecordsIssueOperate RED_SUPERVISE = allOperates
			.get(RED_SUPERVISE_CODE);
	/**
	 * 黄牌督办
	 */
	public final static ThreeRecordsIssueOperate YELLOW_SUPERVISE = allOperates
			.get(YELLOW_SUPERVISE_CODE);
	/**
	 * 普通督办
	 */
	public final static ThreeRecordsIssueOperate NORMAL_SUPERVISE = allOperates
			.get(NORMAL_SUPERVISE_CODE);
	/**
	 * 取消督办
	 */
	public final static ThreeRecordsIssueOperate CANCEL_SUPERVISE = allOperates
			.get(CANCEL_SUPERVISE_CODE);
	/**
	 * 反馈
	 */
	public final static ThreeRecordsIssueOperate FED_BACK = allOperates
			.get(FED_BACK_CODE);
	/**
	 * 结案
	 */
	public final static ThreeRecordsIssueOperate COMPLETE = allOperates
			.get(COMPLETE_CODE);
	/**
	 * 验证（事件整个流程的结束）
	 */
	public final static ThreeRecordsIssueOperate VERIFICATION = allOperates
			.get(VERIFICATION_CODE);
	/**
	 * 不继续办理，直接中止
	 */
	public final static ThreeRecordsIssueOperate CLOSE = allOperates
			.get(CLOSE_CODE);

	/**
	 * 回退
	 */
	public final static ThreeRecordsIssueOperate BACK = allOperates
			.get(BACK_CODE);

	/**
	 * 备案
	 */
	public final static ThreeRecordsIssueOperate BACKUPS = allOperates
			.get(BACKUPS_CODE);

	/**
	 * 取消加急
	 */
	public final static ThreeRecordsIssueOperate CANCEL_URGENT = allOperates
			.get(CANCEL_URGENT_CODE);

	/**
	 * 加急
	 */
	public final static ThreeRecordsIssueOperate URGENT = allOperates
			.get(URGENT_CODE);

	/**
	 * 取消历史遗留
	 */
	public final static ThreeRecordsIssueOperate CANCEL_HISTORIC = allOperates
			.get(CANCEL_HISTORIC_CODE);

	/**
	 * 历史遗留
	 */
	public final static ThreeRecordsIssueOperate HISTORIC = allOperates
			.get(HISTORIC_CODE);
	/**
	 * CMS诉求转办(转入分流中心)
	 */
	public final static ThreeRecordsIssueOperate DISTRIBUTION_DEAL = allOperates
			.get(CMS_DISTRIBUTION);

	/**
	 * 县级执能（交办）
	 */
	public final static ThreeRecordsIssueOperate LEVEL_ASSIGN = allOperates
			.get(LEVEL_ASSIGN_CODE);

	/**
	 * 阶段结案
	 */
	public final static ThreeRecordsIssueOperate PERIOD_COMPLETE = allOperates
			.get(PERIOD_CODE);

	/**
	 * 程序结案
	 */
	public final static ThreeRecordsIssueOperate PROGRAM_COMPLETE = allOperates
			.get(PROGRAM_CODE);

	public static ThreeRecordsIssueOperate parse(int type) {
		return allOperates.get(type);
	}

	private ThreeRecordsIssueOperate(int code, String desc, double cent,
			boolean increase, String mobileCode) {
		this.code = code;
		this.desc = desc;
		this.cent = cent;
		this.increase = increase;
		this.mobileCode = mobileCode;
	}

	private ThreeRecordsIssueOperate(int code, String desc, double cent,
			boolean increase) {
		this.code = code;
		this.desc = desc;
		this.cent = cent;
		this.increase = increase;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String toString() {
		return getDesc();
	}

	public boolean isIncrease() {
		return increase;
	}

	public void setIncrease(boolean increase) {
		this.increase = increase;
	}

	public double getCent() {
		return cent;
	}

	public void setCent(double cent) {
		this.cent = cent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * (prime * result + getClass().hashCode()) + code;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ThreeRecordsIssueOperate other = (ThreeRecordsIssueOperate) obj;
		if (code != other.code) {
			return false;
		}
		return true;
	}

}
