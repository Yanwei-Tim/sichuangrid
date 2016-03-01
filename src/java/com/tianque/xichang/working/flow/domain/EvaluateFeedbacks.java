package com.tianque.xichang.working.flow.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 我的台账的评论反馈
 * */
public class EvaluateFeedbacks extends BaseDomain {
	/** 台账id(ACCOUNTID) */
	private Long accountId;
	/** 台账类型(ACCOUNTTYPE) */
	private String accountType;
	/** 反馈类型 */
	private Integer feedbacksType;
	/** 反馈意见 */
	private Integer feedbacksOpinion;
	/** 评论的用户 */
	private String evaluateUser;
	private Organization evaluateOrganization;
	private String remark;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getFeedbacksType() {
		return feedbacksType;
	}

	public void setFeedbacksType(Integer feedbacksType) {
		this.feedbacksType = feedbacksType;
	}

	public Integer getFeedbacksOpinion() {
		return feedbacksOpinion;
	}

	public void setFeedbacksOpinion(Integer feedbacksOpinion) {
		this.feedbacksOpinion = feedbacksOpinion;
	}

	public String getEvaluateUser() {
		return evaluateUser;
	}

	public void setEvaluateUser(String evaluateUser) {
		this.evaluateUser = evaluateUser;
	}

	public Organization getEvaluateOrganization() {
		return evaluateOrganization;
	}

	public void setEvaluateOrganization(Organization evaluateOrganization) {
		this.evaluateOrganization = evaluateOrganization;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
