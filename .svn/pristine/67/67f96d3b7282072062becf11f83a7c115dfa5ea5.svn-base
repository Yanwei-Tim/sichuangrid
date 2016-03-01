package com.tianque.fourTeams.fourTeamsIssue.state;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.tianque.mobile.vo.MobileActionType;

public class FourTeamsIssueOperate implements Serializable {
	public final static int COMMENT_CODE = 1;
	private final static int REPORT_TO_CODE = 11;
	private final static int ASSIGN_CODE = 21;
	private final static int GIVETO_CODE = 26;
	private final static int COMPLETE_CODE = 31;
	private final static int BACKUPS_CODE = 32;
	private final static int SUBMIT_CODE = 41;
	public final static int INSTRUCT_CODE = 51;
	public final static int CONCEPT_CODE = 61;
	private final static int READ_CODE = 71;
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

	public final static int FEEDBACK_CODE = 42;

	public final static Map<Integer, FourTeamsIssueOperate> allOperates = new HashMap<Integer, FourTeamsIssueOperate>();
	static {
		allOperates.put(COMMENT_CODE, new FourTeamsIssueOperate(COMMENT_CODE, "办理中", 0,
				true, MobileActionType.COMMENT_CODE));
		allOperates.put(REPORT_TO_CODE, new FourTeamsIssueOperate(REPORT_TO_CODE,
				"上报指挥中心", 1, true, MobileActionType.REPORT_TO_CODE));
		allOperates.put(ASSIGN_CODE, new FourTeamsIssueOperate(ASSIGN_CODE, "交办", 1,
				true, MobileActionType.ASSIGN_CODE));
		allOperates.put(COMPLETE_CODE, new FourTeamsIssueOperate(COMPLETE_CODE, "结案", 1,
				true, MobileActionType.COMPLETE_CODE));
		allOperates.put(SUBMIT_CODE, new FourTeamsIssueOperate(SUBMIT_CODE, "上报", 1,
				true, MobileActionType.SUBMIT_CODE));
		allOperates.put(INSTRUCT_CODE, new FourTeamsIssueOperate(INSTRUCT_CODE, "领导批示",
				0, true, MobileActionType.INSTRUCT_CODE));
		allOperates.put(CONCEPT_CODE, new FourTeamsIssueOperate(CONCEPT_CODE, "受理", 0,
				true, MobileActionType.CONCEPT_CODE));
		allOperates.put(READ_CODE, new FourTeamsIssueOperate(READ_CODE, "阅读", 0, true,
				MobileActionType.READ_CODE));
		allOperates.put(NORMAL_SUPERVISE_CODE, new FourTeamsIssueOperate(
				NORMAL_SUPERVISE_CODE, "普通督办", 0, false,
				MobileActionType.NORMAL_SUPERVISE_CODE));
		allOperates.put(YELLOW_SUPERVISE_CODE, new FourTeamsIssueOperate(
				YELLOW_SUPERVISE_CODE, "黄牌督办", 5, false,
				MobileActionType.YELLOW_SUPERVISE_CODE));
		allOperates.put(RED_SUPERVISE_CODE, new FourTeamsIssueOperate(
				RED_SUPERVISE_CODE, "红牌督办", 10, false,
				MobileActionType.RED_SUPERVISE_CODE));
		allOperates.put(CANCEL_SUPERVISE_CODE, new FourTeamsIssueOperate(
				CANCEL_SUPERVISE_CODE, "取消督办", 0, true,
				MobileActionType.CANCEL_SUPERVISE_CODE));
		allOperates.put(FED_BACK_CODE, new FourTeamsIssueOperate(FED_BACK_CODE, "反馈", 0,
				true, MobileActionType.FED_BACK_CODE));
		allOperates.put(DEAL_OVER_CODE, new FourTeamsIssueOperate(DEAL_OVER_CODE, "完成",
				1, true, MobileActionType.DEAL_OVER_CODE));
		allOperates.put(GIVETO_CODE, new FourTeamsIssueOperate(GIVETO_CODE, "交办", 1,
				true, MobileActionType.GIVETO_CODE));
		allOperates.put(CLOSE_CODE, new FourTeamsIssueOperate(CLOSE_CODE, "关闭", 0, true,
				MobileActionType.CLOSE_CODE));
		allOperates.put(BACK_CODE, new FourTeamsIssueOperate(BACK_CODE, "回退", 0, true,
				MobileActionType.BACK_COE));
		allOperates.put(BACKUPS_CODE, new FourTeamsIssueOperate(BACKUPS_CODE, "备案", 0,
				true));
		allOperates.put(URGENT_CODE, new FourTeamsIssueOperate(URGENT_CODE, "加急", 0,
				true, MobileActionType.URGENT_CODE));
		allOperates.put(CANCEL_URGENT_CODE, new FourTeamsIssueOperate(
				CANCEL_URGENT_CODE, "取消加急", 0, true,
				MobileActionType.CANCEL_URGENT_CODE));
		allOperates.put(HISTORIC_CODE, new FourTeamsIssueOperate(HISTORIC_CODE,
				"设置历史遗留", 0, true, MobileActionType.HISTORIC_CODE));
		allOperates.put(CANCEL_HISTORIC_CODE, new FourTeamsIssueOperate(
				CANCEL_HISTORIC_CODE, "取消历史遗留", 0, true,
				MobileActionType.CANCEL_HISTORIC_CODE));
	}

	public final static Map<String, FourTeamsIssueOperate> pressionOperate = new HashMap<String, FourTeamsIssueOperate>();
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

	/**
	 * 办理中
	 */
	public final static FourTeamsIssueOperate COMMENT = allOperates.get(COMMENT_CODE);
	/**
	 * 上报指挥中心
	 */
	public final static FourTeamsIssueOperate REPORT_TO = allOperates
			.get(REPORT_TO_CODE);
	/**
	 * 派单（交办）
	 */
	public final static FourTeamsIssueOperate ASSIGN = allOperates.get(ASSIGN_CODE);
	/**
	 * 下单（指挥中心下派）
	 */
	public final static FourTeamsIssueOperate GIVETO = allOperates.get(GIVETO_CODE);
	/**
	 * 受理
	 */
	public final static FourTeamsIssueOperate CONCEPT = allOperates.get(CONCEPT_CODE);

	/**
	 * 处理完成，用于需要反馈的流程
	 */
	public final static FourTeamsIssueOperate DEALOVER = allOperates.get(DEAL_OVER_CODE);

	/**
	 * 反馈
	 */
	public final static FourTeamsIssueOperate FEEDBACK = allOperates.get(BACK_CODE);
	/**
	 * 上报
	 */
	public final static FourTeamsIssueOperate SUBMIT = allOperates.get(SUBMIT_CODE);
	/**
	 * 批示
	 */
	public final static FourTeamsIssueOperate INSTRUCT = allOperates.get(INSTRUCT_CODE);
	/**
	 * 阅读
	 */
	public final static FourTeamsIssueOperate READ = allOperates.get(READ_CODE);
	/**
	 * 红牌督办
	 */
	public final static FourTeamsIssueOperate RED_SUPERVISE = allOperates
			.get(RED_SUPERVISE_CODE);
	/**
	 * 黄牌督办
	 */
	public final static FourTeamsIssueOperate YELLOW_SUPERVISE = allOperates
			.get(YELLOW_SUPERVISE_CODE);
	/**
	 * 普通督办
	 */
	public final static FourTeamsIssueOperate NORMAL_SUPERVISE = allOperates
			.get(NORMAL_SUPERVISE_CODE);
	/**
	 * 取消督办
	 */
	public final static FourTeamsIssueOperate CANCEL_SUPERVISE = allOperates
			.get(CANCEL_SUPERVISE_CODE);
	/**
	 * 反馈
	 */
	public final static FourTeamsIssueOperate FED_BACK = allOperates.get(FED_BACK_CODE);
	/**
	 * 整个事件按照流程处理完毕
	 */
	public final static FourTeamsIssueOperate COMPLETE = allOperates.get(COMPLETE_CODE);
	/**
	 * 不继续办理，直接中止
	 */
	public final static FourTeamsIssueOperate CLOSE = allOperates.get(CLOSE_CODE);

	/**
	 * 回退
	 */
	public final static FourTeamsIssueOperate BACK = allOperates.get(BACK_CODE);

	/**
	 * 备案
	 */
	public final static FourTeamsIssueOperate BACKUPS = allOperates.get(BACKUPS_CODE);

	/**
	 * 取消加急
	 */
	public final static FourTeamsIssueOperate CANCEL_URGENT = allOperates
			.get(CANCEL_URGENT_CODE);

	/**
	 * 加急
	 */
	public final static FourTeamsIssueOperate URGENT = allOperates.get(URGENT_CODE);

	/**
	 * 取消历史遗留
	 */
	public final static FourTeamsIssueOperate CANCEL_HISTORIC = allOperates
			.get(CANCEL_HISTORIC_CODE);

	/**
	 * 历史遗留
	 */
	public final static FourTeamsIssueOperate HISTORIC = allOperates.get(HISTORIC_CODE);

	public static FourTeamsIssueOperate parse(int type) {
		return allOperates.get(type);
	}

	private String desc;

	private int code;

	private boolean increase;

	private double cent;

	private String mobileCode;

	private FourTeamsIssueOperate(int code, String desc, double cent, boolean increase,
			String mobileCode) {
		this.code = code;
		this.desc = desc;
		this.cent = cent;
		this.increase = increase;
		this.mobileCode = mobileCode;
	}

	private FourTeamsIssueOperate(int code, String desc, double cent, boolean increase) {
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FourTeamsIssueOperate other = (FourTeamsIssueOperate) obj;
		if (code != other.code)
			return false;
		return true;
	}

}
