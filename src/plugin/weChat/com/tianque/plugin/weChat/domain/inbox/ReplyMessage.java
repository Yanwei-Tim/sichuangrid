package com.tianque.plugin.weChat.domain.inbox;

import com.tianque.core.base.BaseDomain;

/**主动回复消息成功后的记录信息*/
public class ReplyMessage extends BaseDomain {
	/**回复的记录信息id*/
	private Long replyMessageId;
	/**回复内容*/
	private String content;
	/**模糊消息id*/
	private Long inboxId;
	/**精确消息id*/
	private Long preciseInboxId;
	/**消息接收者*/
	private String receiveUser;
	/**消息发送者（服务号）*/
	private String wechatUserId;
	/**消息发送者openId*/
	private String fromUserName;
	/**与回复消息相关发送者发送的消息是否转为事件(0为否,1为是)*/
	private Long isIssue;
	/**事件服务单号*/
	private String serviceId;

	public String getWechatUserId() {
		return wechatUserId;
	}

	public void setWechatUserId(String wechatUserId) {
		this.wechatUserId = wechatUserId;
	}

	public Long getReplyMessageId() {
		return replyMessageId;
	}

	public void setReplyMessageId(Long replyMessageId) {
		this.replyMessageId = replyMessageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getInboxId() {
		return inboxId;
	}

	public void setInboxId(Long inboxId) {
		this.inboxId = inboxId;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public Long getPreciseInboxId() {
		return preciseInboxId;
	}

	public void setPreciseInboxId(Long preciseInboxId) {
		this.preciseInboxId = preciseInboxId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getIsIssue() {
		return isIssue;
	}

	public void setIsIssue(Long isIssue) {
		this.isIssue = isIssue;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
}
