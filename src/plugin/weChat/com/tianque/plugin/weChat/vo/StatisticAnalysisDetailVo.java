package com.tianque.plugin.weChat.vo;

/**统计分析群组统计类*/
public class StatisticAnalysisDetailVo {
	/**群组名*/
	private String groupName;
	/**上报数量*/
	private long reportNum;
	/**受理数量*/
	private long acceptNum;
	/**有效数*/
	private long availabilityNum;
	/**转发数量*/
	private long forwardingNum;
	/**粉丝名*/
	private String fanName;
	/**已办结数*/
	private long completedNum;
	/**未办结数*/
	private long unCompletedNum;
	/**备注名*/
	private String nickName;
	
	/**事件大类id*/
	private String issueTypeDomainName;
	/**事件类型Id(大类)*/
	private Long issueTypeDomainId;
	
	/**事件小类id*/
	private String issueTypeName;
	
	private Long id;
	
	public long getAvailabilityNum() {
		return availabilityNum;
	}

	public void setAvailabilityNum(long availabilityNum) {
		this.availabilityNum = availabilityNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}

	public void setAcceptNum(int acceptNum) {
		this.acceptNum = acceptNum;
	}

	public long getReportNum() {
		return reportNum;
	}

	public void setReportNum(long reportNum) {
		this.reportNum = reportNum;
	}

	public long getAcceptNum() {
		return acceptNum;
	}

	public void setAcceptNum(long acceptNum) {
		this.acceptNum = acceptNum;
	}

	public long getForwardingNum() {
		return forwardingNum;
	}

	public void setForwardingNum(long forwardingNum) {
		this.forwardingNum = forwardingNum;
	}

	public String getFanName() {
		return fanName;
	}

	public void setFanName(String fanName) {
		this.fanName = fanName;
	}

	public long getCompletedNum() {
		return completedNum;
	}

	public void setCompletedNum(long completedNum) {
		this.completedNum = completedNum;
	}

	public long getUnCompletedNum() {
		return unCompletedNum;
	}

	public void setUnCompletedNum(long unCompletedNum) {
		this.unCompletedNum = unCompletedNum;
	}

	public String getIssueTypeDomainName() {
		return issueTypeDomainName;
	}

	public void setIssueTypeDomainName(String issueTypeDomainName) {
		this.issueTypeDomainName = issueTypeDomainName;
	}

	public String getIssueTypeName() {
		return issueTypeName;
	}

	public void setIssueTypeName(String issueTypeName) {
		this.issueTypeName = issueTypeName;
	}

	public Long getIssueTypeDomainId() {
		return issueTypeDomainId;
	}

	public void setIssueTypeDomainId(Long issueTypeDomainId) {
		this.issueTypeDomainId = issueTypeDomainId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
