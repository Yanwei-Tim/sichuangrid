package com.tianque.domain.vo;

public class IssueDealRegradeReason extends DefaultRegradedReason {

	private String issueSerialNumber;
	private Long logId;
	private String dealType;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getIssueSerialNumber() {
		return issueSerialNumber;
	}

	public void setIssueSerialNumber(String issueSerialNumber) {
		this.issueSerialNumber = issueSerialNumber;
	}

	@Override
	public String getKeyString() {
		return IssueDealRegradeReason.class.getName() + issueSerialNumber + ";"
				+ logId;
	}

	@Override
	public String getIssueNumber() {
		return issueSerialNumber;
	}

}
