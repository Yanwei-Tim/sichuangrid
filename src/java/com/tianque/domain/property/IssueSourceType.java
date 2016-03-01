package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.issue.constants.IssueConstants;

public class IssueSourceType extends BaseProperty {
	/** 人工录入 */
	public final static int MANUAL_INPUT = 0;
	/** 短信录入 */
	public final static int SMS_INPUT = 1;
	/** 网站录入 */
	public final static int WEB_INPUT = 2;
	/** 人工录入 */
	public final static int CITYMANAGE_INPUT = 3;
	/** 数字城管 */
	public final static int CALLCENTER_INPUT = 4;
	/** 热线电话 */
	public final static int TELEPHONE_HOTLINE = 5;
	/** 审批转入 */
	public final static int APPROVAL_INPUT = 6;
	/**
	 * 走访录入
	 */
	public final static int VISIT_INPUT = 7;
	/**
	 * 手机录入
	 */
	public final static int MOBILE_INPUT = 8;

	/**
	 * 微信录入
	 */
	public final static int WECHAT_INPUT = 9;

	/**
	 * pc录入
	 */
	public final static int PC_INPUT = 10;
	/** 为对接代办事件加上一个同步 */
	public final static int JOINT_INPUT = 11;

	public final static int TRANS_EVENT_DONE = 1; // 已转入事件并分流
	public final static int TRANS_EVENT_NOTDONE = 2; // 已转入事件但未分流
	public final static int NOT_TRANS_EVENT = 3; // 无需转入事件

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(MANUAL_INPUT,
				IssueConstants.MANUAL_INPUT));
		properties
				.add(getInternalProperty(SMS_INPUT, IssueConstants.SMS_INPUT));
		properties
				.add(getInternalProperty(WEB_INPUT, IssueConstants.WEB_INPUT));
		properties.add(getInternalProperty(CITYMANAGE_INPUT,
				IssueConstants.CITYMANAGE_INPUT));
		properties.add(getInternalProperty(CALLCENTER_INPUT,
				IssueConstants.CALLCENTER_INPUT));
		properties.add(getInternalProperty(TELEPHONE_HOTLINE,
				IssueConstants.TELEPHONE_HOTLINE));
		properties.add(getInternalProperty(APPROVAL_INPUT,
				IssueConstants.APPROVAL_INPUT));
		return properties;
	}
}
