package com.tianque.domain.vo;

public class WorkContacterRegradedReason implements RegradedReason {

	private String content;
	private String issueSerialNumber;

	@Override
	public String getRegradedResonDescription() {
		return content;
	}

	@Override
	public String getKeyString() {
		return null;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIssueSerialNumber() {
		return issueSerialNumber;
	}

	public void setIssueSerialNumber(String issueSerialNumber) {
		this.issueSerialNumber = issueSerialNumber;
	}

	@Override
	public String getIssueNumber() {
		return issueSerialNumber;
	}

}
