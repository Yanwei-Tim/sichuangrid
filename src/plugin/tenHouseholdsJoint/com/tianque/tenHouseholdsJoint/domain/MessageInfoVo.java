/**
 * 
 */
package com.tianque.tenHouseholdsJoint.domain;

import java.util.Date;

/**
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年3月4日 下午1:33:30
 */
public class MessageInfoVo {
	private Long orgId;
	private String orgCode;
	private Long messageId;
	private Long teamId;
	private Long userId;
	private String userIds;
	private String teamIds;
	private String userName;
	private Long messageTypeId;
	private String houseMaster;
	private Date startSendDate;
	private Date endSendDate;
	private String text;
	private Boolean isReport;
	private String telephone;
	private String sortField;
	private String order;
	

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getMessageTypeId() {
		return messageTypeId;
	}

	public void setMessageTypeId(Long messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	public String getHouseMaster() {
		return houseMaster;
	}

	public void setHouseMaster(String houseMaster) {
		this.houseMaster = houseMaster;
	}

	public Date getStartSendDate() {
		return startSendDate;
	}

	public void setStartSendDate(Date startSendDate) {
		this.startSendDate = startSendDate;
	}

	public Date getEndSendDate() {
		return endSendDate;
	}

	public void setEndSendDate(Date endSendDate) {
		this.endSendDate = endSendDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsReport() {
		return isReport;
	}

	public void setIsReport(Boolean isReport) {
		this.isReport = isReport;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(String teamIds) {
		this.teamIds = teamIds;
	}

}
