package com.tianque.issue.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.issue.state.IssueState;

public class IssueOperateStep extends BaseDomain {

	private Organization source;

	private Organization target;

	private String state;

	private int stateCode;

	private Date entryDate;

	private Date endDate;

	private Date lastDealDate;

	private Issue issue;

	private int superviseLevel = IssueState.NO_SUPERVISE;
	/**
	 * 如果可以回退，该步将回退到哪里
	 */
	private IssueOperateStep backTo;

	public IssueOperateStep getBackTo() {
		return backTo;
	}

	public void setBackTo(IssueOperateStep backTo) {
		this.backTo = backTo;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getLastDealDate() {
		return lastDealDate;
	}

	public void setLastDealDate(Date lastDealDate) {
		this.lastDealDate = lastDealDate;
	}

	public int getSuperviseLevel() {
		return superviseLevel;
	}

	public void setSuperviseLevel(int superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

}
