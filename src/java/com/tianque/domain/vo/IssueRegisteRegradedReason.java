package com.tianque.domain.vo;

public class IssueRegisteRegradedReason extends DefaultRegradedReason {
	private String issueSerialNumber;

	public String getIssueSerialNumber() {
		return issueSerialNumber;
	}

	public void setIssueSerialNumber(String issueSerialNumber) {
		this.issueSerialNumber = issueSerialNumber;
	}

	@Override
	public String getKeyString() {
		return IssueRegisteRegradedReason.class.getName() + issueSerialNumber;
	}

	@Override
	public String getIssueNumber() {
		return issueSerialNumber;
	}

}
