package com.tianque.platformMessage.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.User;

public class PlatformMessage extends BaseDomain {

	public PlatformMessage() {

	}

	public PlatformMessage(String title, String content, Integer sendType,
			Integer messageType) {
		this.title = title;
		this.content = content;
		this.sendType = sendType;
		this.messageType = messageType;
	}

	public PlatformMessage(String title, String content, String receiverType,
			Integer sendType, Integer messageType) {
		this.title = title;
		this.content = content;
		this.sendType = sendType;
		this.receiverType = receiverType;
		this.messageType = messageType;
	}

	public PlatformMessage(String title, String content, String url,
			String receiverType, Integer sendType, Integer messageType) {
		this.title = title;
		this.content = content;
		this.sendType = sendType;
		this.url = url;
		this.receiverType = receiverType;
		this.messageType = messageType;
	}

	public PlatformMessage(String title, String content, Integer sendType) {
		this.title = title;
		this.content = content;
		this.sendType = sendType;
	}

	/** 消息标题 */
	private String title;
	/** 消息标题全拼 */
	private String fullPinyin;
	/** 消息标题简拼 */
	private String simplePinyin;
	/** 消息内容 */
	private String content;
	/** 历史消息内容 */
	private String historyMessage;
	/** 附件 */
	private List<PlatformMessageAttachFile> pmAttachFiles;
	/** 消息类型 */
	private Integer messageType;
	/** 消息中的链接 */
	private String url;
	/** 岗位所在的orgId */
	private Long roleOrgId;
	/** 消息有效期 */
	private Integer expiryDate;
	/** 发件人 */
	private User sender;
	/** 发送日期 */
	private Date sendDate;
	/** 发送类型 0:人工发送 1：系统发送 */
	private Integer sendType;
	/** 收件人类型 */
	private String receiverType;
	/** 收件人id */
	private Long receiverId;
	/** 收件人名称 */
	private String receiverNames;
	/** 阅读状态 */
	private Integer readState = 0;
	/** 阅读日期 */
	private Date readDate;
	/** 删除状态 */
	private Integer deleteState = 0;
	/** 回复消息的ID */
	private Long replyMessageId;
	/** 是否属于草稿箱 0不是，1是 */
	private Integer isDraft;

	/** 原发件人 */
	private String originatorsName;
	/** 原件发送时间 */
	private Date originatorsDate;
	/** 存放草稿箱消息收件人的信息 */
	private String recipientInfo;
	/** mode,用于区分消息是转发还是发送还是回复 */
	private String messageMode;
	/** 区分消息来源 是草稿箱发送还是一般发送 */
	private Integer platformMessageSource;

	/** 共享资料和文件签收 提示的ID */
	private String fileInfoIds;

	public String getFileInfoIds() {
		return fileInfoIds;
	}

	public void setFileInfoIds(String fileInfoIds) {
		this.fileInfoIds = fileInfoIds;
	}

	public Integer getPlatformMessageSource() {
		return platformMessageSource;
	}

	public void setPlatformMessageSource(Integer platformMessageSource) {
		this.platformMessageSource = platformMessageSource;
	}

	public String getMessageMode() {
		return messageMode;
	}

	public void setMessageMode(String messageMode) {
		this.messageMode = messageMode;
	}

	public String getRecipientInfo() {
		return recipientInfo;
	}

	public void setRecipientInfo(String recipientInfo) {
		this.recipientInfo = recipientInfo;
	}

	public String getOriginatorsName() {
		return originatorsName;
	}

	public void setOriginatorsName(String originatorsName) {
		this.originatorsName = originatorsName;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOriginatorsDate() {
		return originatorsDate;
	}

	public void setOriginatorsDate(Date originatorsDate) {
		this.originatorsDate = originatorsDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public Integer getReadState() {
		return readState;
	}

	public void setReadState(Integer readState) {
		this.readState = readState;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Long getReplyMessageId() {
		return replyMessageId;
	}

	public void setReplyMessageId(Long replyMessageId) {
		this.replyMessageId = replyMessageId;
	}

	public Integer getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Integer expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public Long getRoleOrgId() {
		return roleOrgId;
	}

	public void setRoleOrgId(Long roleOrgId) {
		this.roleOrgId = roleOrgId;
	}

	public String getReceiverNames() {
		return receiverNames;
	}

	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}

	public List<PlatformMessageAttachFile> getPmAttachFiles() {
		return pmAttachFiles;
	}

	public void setPmAttachFiles(List<PlatformMessageAttachFile> pmAttachFiles) {
		this.pmAttachFiles = pmAttachFiles;
	}

	public String getHistoryMessage() {
		return historyMessage;
	}

	public void setHistoryMessage(String historyMessage) {
		this.historyMessage = historyMessage;
	}

	public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}

	public Integer getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}

	// public String getEncryptId() {
	// return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), null,
	// null);
	// }

}
