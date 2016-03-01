package com.tianque.platformMessage.domain;

import java.util.Date;
import java.util.List;

import com.tianque.core.util.DateUtil;

public class SearchPlatformMessageVo {
	/** 发件人id */
	private Long sendUserId;
	/** 收件人id */
	private Long userId;
	/** 收件人所在部门id */
	private Long orgId;
	/** 收件人的岗位id */
	private Long[] roleIds;
	/** 快速搜索的搜索条件 */
	private String fastSearchCondition;
	/** 消息发送类型 */
	private String sendType;
	/** 消息标题 */
	private String title;
	/** 消息类型 */
	private Integer messageType;
	/** 发送开始时间 */
	private Date sendTimeStart;
	/** 发送结束时间 */
	private Date sendTimeEnd;
	/** 消息内容 */
	private String content;
	/** 收件人名称 */
	private String receiverNames;
	/** 是否有附件 */
	private Integer hasAttach;
	/** 阅读状态 */
	private Integer readState;
	/** 排序字段 */
	private String sortField;
	/** 排序方式 */
	private String order;

	/** 查询系统消息 */
	private Integer isAdmin;

	/** 是否草稿箱 */
	private Integer isDraft;
	
	private List<Integer> platformMessageTypes;

	public Integer getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Long getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Long sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getSendTimeStart() {
		return sendTimeStart;
	}

	public void setSendTimeStart(Date sendTimeStart) {
		this.sendTimeStart = sendTimeStart;
	}

	public Date getSendTimeEnd() {
		return sendTimeEnd;
	}

	public void setSendTimeEnd(Date sendTimeEnd) {
		this.sendTimeEnd = DateUtil.getEndTime(sendTimeEnd);
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getReceiverNames() {
		return receiverNames;
	}

	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}

	public Integer getHasAttach() {
		return hasAttach;
	}

	public void setHasAttach(Integer hasAttach) {
		this.hasAttach = hasAttach;
	}

	public String getFastSearchCondition() {
		return fastSearchCondition;
	}

	public void setFastSearchCondition(String fastSearchCondition) {
		this.fastSearchCondition = fastSearchCondition;
	}

	public Integer getReadState() {
		return readState;
	}

	public void setReadState(Integer readState) {
		this.readState = readState;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public List<Integer> getPlatformMessageTypes() {
		return platformMessageTypes;
	}

	public void setPlatformMessageTypes(List<Integer> platformMessageTypes) {
		this.platformMessageTypes = platformMessageTypes;
	}

}
