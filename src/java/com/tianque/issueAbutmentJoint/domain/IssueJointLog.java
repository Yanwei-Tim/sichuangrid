package com.tianque.issueAbutmentJoint.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @Description:事件对接 日志
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:01:42
 */
public class IssueJointLog extends BaseDomain {
	/** 对接事件 */
	private IssueJoint issueJoint;
	/** 处理目标id **/
	private Organization dealOrg;
	/** 处理类型 **/
	private Long dealType;
	/** 处理用户名称 **/
	private String dealUserName;
	/** 处理人 电话 **/
	private String mobile;
	/** 处理描述 **/
	private String dealDescription;
	/** 处理时间 **/
	private Date dealTime;
	/** 事件处理步骤 **/
	private IssueJointStep issueJointStep;
	/** 处理意见 */
	private String content;

	public IssueJoint getIssueJoint() {
		return issueJoint;
	}

	public void setIssueJoint(IssueJoint issueJoint) {
		this.issueJoint = issueJoint;
	}

	public Organization getDealOrg() {
		return dealOrg;
	}

	public void setDealOrg(Organization dealOrg) {
		this.dealOrg = dealOrg;
	}

	public Long getDealType() {
		return dealType;
	}

	public void setDealType(Long dealType) {
		this.dealType = dealType;
	}

	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public IssueJointStep getIssueJointStep() {
		return issueJointStep;
	}

	public void setIssueJointStep(IssueJointStep issueJointStep) {
		this.issueJointStep = issueJointStep;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
