package com.tianque.issueAbutmentJoint.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @Description:事件对接 步骤
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:02:00
 */
public class IssueJointStep extends BaseDomain {

	/** 该步骤来源部门 */
	private Organization source;
	/** 该步骤的处理部门 */
	private Organization target;
	/** 进入该步骤的时间 */
	private Date entryDate;
	/** 该步骤的结束时间 */
	private Date endDate;
	/** 该步骤的最后处理时间 */
	private Date lastDealDate;
	/** 该步骤的状态代码 */
	private int stateCode;
	/** 该步骤处理的事件 */
	private IssueJoint issueJoint;

	public Organization getSource() {
		return source;
	}

	public void setSource(Organization source) {
		this.source = source;
	}

	public Organization getTarget() {
		return target;
	}

	public void setTarget(Organization target) {
		this.target = target;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLastDealDate() {
		return lastDealDate;
	}

	public void setLastDealDate(Date lastDealDate) {
		this.lastDealDate = lastDealDate;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public IssueJoint getIssueJoint() {
		return issueJoint;
	}

	public void setIssueJoint(IssueJoint issueJoint) {
		this.issueJoint = issueJoint;
	}

}
