package com.tianque.domain.vo;

public class IssueRegradedReason implements RegradedReason {
	private Long issueId;
	private Long issueLogId;
	private String serialNumber;
	private Double points;

	@Override
	public String getRegradedResonDescription() {
		return "对服务单号为" + serialNumber + "的事件处理"
				+ Points.doublePointToString(points);
	}

	@Override
	public String getKeyString() {
		return "issueId:" + issueId + ";";
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getIssueLogId() {
		return issueLogId;
	}

	public void setIssueLogId(Long issueLogId) {
		this.issueLogId = issueLogId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String getIssueNumber() {
		return serialNumber;
	}

}
