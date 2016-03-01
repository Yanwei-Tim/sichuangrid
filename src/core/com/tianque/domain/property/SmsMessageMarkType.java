package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmsMessageMarkType {
	/**
	 * 事件的操作类型（办理，领导批示，督办，阅读等）
	 */
	/** 办理 */
	public final static int DEAL_CODE = 1;
	/** 领导批示 */
	public final static int INSTRUCT_CODE = 11;
	/** 受理 */
	public final static int CONCEPT_CODE = 21;
	/** 阅读 */
	public final static int READ_CODE = 31;
	/** 普通督办 */
	public final static int NORMAL_SUPERVISE_CODE = 41;
	/** 黄牌督办 */
	public final static int YELLOW_SUPERVISE_CODE = 51;
	/** 红牌督办 */
	public final static int RED_SUPERVISE_CODE = 61;
	/** 取消督办 */
	public final static int CANCEL_SUPERVISE_CODE = 71;

	/**
	 * 事件办理的操作类型（上报，交办，越级，验证等）
	 */
	/** 办理中 */
	public final static int COMMENT_CODE = 1001;
	/** 交办 */
	private final static int ASSIGN_CODE = 1011;
	/** 上报 */
	private final static int SUBMIT_CODE = 1021;
	/** 结案 */
	private final static int COMPLETE_CODE = 1031;
	/** 验证 */
	private final static int VERIFICATION_CODE = 1041;
	/** 回退 */
	private final static int BACK_CODE = 1051;
	/** 评分 */
	private final static int GRADEDELAY_CODE = 1061;

	public final static Map<Integer, SmsMessageMarkType> allOperates = new HashMap<Integer, SmsMessageMarkType>();
	static {
		allOperates.put(DEAL_CODE, new SmsMessageMarkType(DEAL_CODE, "办理"));
		allOperates.put(INSTRUCT_CODE, new SmsMessageMarkType(INSTRUCT_CODE,
				"领导批示"));
		allOperates.put(CONCEPT_CODE,
				new SmsMessageMarkType(CONCEPT_CODE, "受理"));
		allOperates.put(READ_CODE, new SmsMessageMarkType(READ_CODE, "阅读"));
		allOperates.put(NORMAL_SUPERVISE_CODE, new SmsMessageMarkType(
				NORMAL_SUPERVISE_CODE, "普通督办"));
		allOperates.put(YELLOW_SUPERVISE_CODE, new SmsMessageMarkType(
				YELLOW_SUPERVISE_CODE, "黄牌督办"));
		allOperates.put(RED_SUPERVISE_CODE, new SmsMessageMarkType(
				RED_SUPERVISE_CODE, "红牌督办"));
		allOperates.put(CANCEL_SUPERVISE_CODE, new SmsMessageMarkType(
				CANCEL_SUPERVISE_CODE, "取消督办"));
	}

	public final static Map<Integer, SmsMessageMarkType> dealOperates = new HashMap<Integer, SmsMessageMarkType>();
	static {
		dealOperates.put(COMMENT_CODE, new SmsMessageMarkType(COMMENT_CODE,
				"办理中"));
		dealOperates
				.put(ASSIGN_CODE, new SmsMessageMarkType(ASSIGN_CODE, "交办"));
		dealOperates
				.put(SUBMIT_CODE, new SmsMessageMarkType(SUBMIT_CODE, "上报"));
		dealOperates.put(COMPLETE_CODE, new SmsMessageMarkType(COMPLETE_CODE,
				"结案"));
		dealOperates.put(VERIFICATION_CODE, new SmsMessageMarkType(
				VERIFICATION_CODE, "验证"));
		dealOperates.put(BACK_CODE, new SmsMessageMarkType(BACK_CODE, "回退"));
		dealOperates.put(GRADEDELAY_CODE, new SmsMessageMarkType(
				GRADEDELAY_CODE, "评分"));
	}

	/**
	 * 办理
	 */
	public final static SmsMessageMarkType DEAL = allOperates.get(DEAL_CODE);
	/**
	 * 领导批示
	 */
	public final static SmsMessageMarkType INSTRUCT = allOperates
			.get(INSTRUCT_CODE);
	/**
	 * 受理
	 */
	public final static SmsMessageMarkType CONCEPT = allOperates
			.get(CONCEPT_CODE);
	/**
	 * 阅读
	 */
	public final static SmsMessageMarkType READ = allOperates.get(READ_CODE);
	/**
	 * 普通督办
	 */
	public final static SmsMessageMarkType NORMAL_SUPERVISE = allOperates
			.get(NORMAL_SUPERVISE_CODE);
	/**
	 * 黄牌督办
	 */
	public final static SmsMessageMarkType YELLOW_SUPERVISE = allOperates
			.get(YELLOW_SUPERVISE_CODE);
	/**
	 * 红牌督办
	 */
	public final static SmsMessageMarkType RED_SUPERVISE = allOperates
			.get(RED_SUPERVISE_CODE);

	/**
	 * 取消督办
	 */
	public final static SmsMessageMarkType CANCEL_SUPERVISE = allOperates
			.get(CANCEL_SUPERVISE_CODE);

	/**
	 * 办理中
	 */
	public final static SmsMessageMarkType COMMENT = dealOperates
			.get(COMMENT_CODE);
	/**
	 * 交办
	 */
	public final static SmsMessageMarkType ASSIGN = dealOperates
			.get(ASSIGN_CODE);
	/**
	 * 上报
	 */
	public final static SmsMessageMarkType SUBMIT = dealOperates
			.get(SUBMIT_CODE);
	/**
	 * 结案
	 */
	public final static SmsMessageMarkType COMPLETE = dealOperates
			.get(COMPLETE_CODE);
	/**
	 * 验证
	 */
	public final static SmsMessageMarkType VERIFICATION = dealOperates
			.get(VERIFICATION_CODE);
	/**
	 * 回退
	 */
	public final static SmsMessageMarkType BACK = dealOperates.get(BACK_CODE);
	/**
	 * 评分
	 */
	public final static SmsMessageMarkType GRADEDELAY = dealOperates
			.get(GRADEDELAY_CODE);

	public static SmsMessageMarkType allOperates(int type) {
		return allOperates.get(type);
	}

	public static List<SmsMessageMarkType> findOperatesList() {
		List<SmsMessageMarkType> operatesList = new ArrayList<SmsMessageMarkType>();
		operatesList.add(DEAL);
		operatesList.add(INSTRUCT);
		operatesList.add(CONCEPT);
		operatesList.add(READ);
		operatesList.add(NORMAL_SUPERVISE);
		operatesList.add(YELLOW_SUPERVISE);
		operatesList.add(RED_SUPERVISE);
		operatesList.add(CANCEL_SUPERVISE);
		return operatesList;
	}

	public static List<SmsMessageMarkType> findDealList() {
		List<SmsMessageMarkType> dealList = new ArrayList<SmsMessageMarkType>();
		dealList.add(COMMENT);
		dealList.add(ASSIGN);
		dealList.add(SUBMIT);
		dealList.add(COMPLETE);
		dealList.add(VERIFICATION);
		dealList.add(BACK);
		dealList.add(GRADEDELAY);
		return dealList;
	}

	public static SmsMessageMarkType dealOperates(int type) {
		return dealOperates.get(type);
	}

	private int code;
	private String name;

	private SmsMessageMarkType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
