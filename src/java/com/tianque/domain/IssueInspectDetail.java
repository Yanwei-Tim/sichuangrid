package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class IssueInspectDetail extends BaseDomain {
	private long issueType;
	private long issueCount;
	private long dealCount;
	private long finishCount;
	private long inssueId;
	private String remark;

	public long getIssueType() {
		return issueType;
	}

	public void setIssueType(long issueType) {
		this.issueType = issueType;
	}

	public long getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(long issueCount) {
		this.issueCount = issueCount;
	}

	public long getDealCount() {
		return dealCount;
	}

	public void setDealCount(long dealCount) {
		this.dealCount = dealCount;
	}

	public long getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(long finishCount) {
		this.finishCount = finishCount;
	}

	public long getInssueId() {
		return inssueId;
	}

	public void setInssueId(long inssueId) {
		this.inssueId = inssueId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
