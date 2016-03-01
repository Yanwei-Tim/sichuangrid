package com.tianque.sms.domain;

import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

/**
 * 短信收件箱:实体类(SMSDOWNLINK)
 * 
 * @author
 * @date 2013-07-08 17:27:15
 */
public class Smsdownlink extends com.tianque.core.base.BaseDomain implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 发送时间(SENDTIME) */
	private java.util.Date sendTime;
	/** 接收时间(RECEIVETIME) */
	private java.util.Date receiveTime;
	/** 接收者手机(RECEIVERMOBILE) */
	private String receiverMobile;
	/** 发送者(SENDER) */
	private String sender;
	/** 接收者所属网格(RECEIVERORGID) */
	private Long receiverOrgId;
	/** 发送状态(STATUS) */
	private Long status;
	/** 接收者所属网格编号(RECEIVERORGINTERNALCODE) */
	private String receiverOrgInternalCode;
	/** 发送者姓名(SENDERNAME) */
	private String senderName;
	/** 接收者姓名(RECEIVERNAME) */
	private String receiverName;
	/** 发送内容(CONTENT) */
	private String content;
	/** 是否已读 0未读 1已读 */
	private Long isRead;

	public Smsdownlink() {

	}

	public Smsdownlink(java.util.Date sendTime, java.util.Date receiveTime,
			String receiverMobile, String sender, Long receiverOrgId,
			Long status, String receiverOrgInternalCode, String senderName,
			String receiverName, String content) {
		this.sendTime = sendTime;
		this.receiveTime = receiveTime;
		this.receiverMobile = receiverMobile;
		this.sender = sender;
		this.receiverOrgId = receiverOrgId;
		this.status = status;
		this.receiverOrgInternalCode = receiverOrgInternalCode;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.content = content;
	}

	/** get 发送时间(sendTime) */
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public java.util.Date getSendTime() {
		return sendTime;
	}

	/** set 发送时间(SENDTIME) */
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}

	/** get 接收时间(receiveTime) */
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public java.util.Date getReceiveTime() {
		return receiveTime;
	}

	/** set 接收时间(RECEIVETIME) */
	public void setReceiveTime(java.util.Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	/** get 接收者手机(receiverMobile) */
	public String getReceiverMobile() {
		return receiverMobile;
	}

	/** set 接收者手机(RECEIVERMOBILE) */
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	/** get 发送者(sender) */
	public String getSender() {
		return sender;
	}

	/** set 发送者(SENDER) */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/** get 接收者所属网格(receiverOrgId) */
	public Long getReceiverOrgId() {
		return receiverOrgId;
	}

	/** set 接收者所属网格(RECEIVERORGID) */
	public void setReceiverOrgId(Long receiverOrgId) {
		this.receiverOrgId = receiverOrgId;
	}

	/** get 发送状态(status) */
	public Long getStatus() {
		return status;
	}

	/** set 发送状态(STATUS) */
	public void setStatus(Long status) {
		this.status = status;
	}

	/** get 接收者所属网格编号(receiverOrgInternalCode) */
	public String getReceiverOrgInternalCode() {
		return receiverOrgInternalCode;
	}

	/** set 接收者所属网格编号(RECEIVERORGINTERNALCODE) */
	public void setReceiverOrgInternalCode(String receiverOrgInternalCode) {
		this.receiverOrgInternalCode = receiverOrgInternalCode;
	}

	/** get 发送者姓名(senderName) */
	public String getSenderName() {
		return senderName;
	}

	/** set 发送者姓名(SENDERNAME) */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/** get 接收者姓名(receiverName) */
	public String getReceiverName() {
		return receiverName;
	}

	/** set 接收者姓名(RECEIVERNAME) */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/** get 发送内容(content) */
	public String getContent() {
		return content;
	}

	/** set 发送内容(CONTENT) */
	public void setContent(String content) {
		this.content = content;
	}

	public Long getIsRead() {
		return isRead;
	}

	public void setIsRead(Long isRead) {
		this.isRead = isRead;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
}
