package com.tianque.sms.domain;

import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.User;

/**
 * 短信发件箱:实体类(SMSUPLINK)
 * 
 * @author
 * @date 2013-07-02 09:53:32
 */
public class SmsUplink extends com.tianque.core.base.BaseDomain implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 发送时间(SENDTIME) */
	private java.util.Date sendTime;
	/** 接收时间(RECEIVETIME) */
	private java.util.Date receiveTime;
	/** 接收者姓名 */
	private String receiverName;
	/** 接收者手机 */
	private String receiverMobile;
	/** 发送者(SENDER) */
	private User sender;
	/** 发送者姓名 */
	private String senderName;
	/** 发送状态(STATUS):1待处理，2处理中，3待发送，4发送成功，5发送失败 */
	private Long status;
	/** 发送内容(CONTENT) */
	private String content;
	/** 接收者所属网格 */
	private Long receiverOrgId;
	/** 接收者所属网格编号 */
	private String receiverOrgInternalCode;
	/** 发送者所属网格 */
	private Long senderOrgId;
	/** 发送者所属网格编号 */
	private String senderOrgInternalCode;
	/** 父Id */
	private Long parentId;
	/** 发送总数 */
	private Integer counts;
	/** 短信发送平台短信Id */
	private Long messageId;
	/** 所属部门 */
	private Long deptId;
	/** 手机类型 :1移动，2联通，3小灵通，4电信 */
	private int mobileType;
	/** 短信优先级,优先级逐步递减：1，2，3 */
	private Long smsLevel;
	/** 是否删除 */
	private Long deleteStatus;

	public SmsUplink() {

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

	/** get 发送者(sender) */
	public User getSender() {
		return sender;
	}

	/** set 发送者(SENDER) */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/** get 发送状态(status) */
	public Long getStatus() {
		return status;
	}

	/** set 发送状态(STATUS) */
	public void setStatus(Long status) {
		this.status = status;
	}

	/** get 发送内容(content) */
	public String getContent() {
		return content;
	}

	/** set 发送内容(CONTENT) */
	public void setContent(String content) {
		this.content = content;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public Long getReceiverOrgId() {
		return receiverOrgId;
	}

	public void setReceiverOrgId(Long receiverOrgId) {
		this.receiverOrgId = receiverOrgId;
	}

	public String getReceiverOrgInternalCode() {
		return receiverOrgInternalCode;
	}

	public void setReceiverOrgInternalCode(String receiverOrgInternalCode) {
		this.receiverOrgInternalCode = receiverOrgInternalCode;
	}

	public Long getSenderOrgId() {
		return senderOrgId;
	}

	public void setSenderOrgId(Long senderOrgId) {
		this.senderOrgId = senderOrgId;
	}

	public String getSenderOrgInternalCode() {
		return senderOrgInternalCode;
	}

	public void setSenderOrgInternalCode(String senderOrgInternalCode) {
		this.senderOrgInternalCode = senderOrgInternalCode;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public int getMobileType() {
		return mobileType;
	}

	public void setMobileType(int mobileType) {
		this.mobileType = mobileType;
	}

	public Long getSmsLevel() {
		return smsLevel;
	}

	public void setSmsLevel(Long smsLevel) {
		this.smsLevel = smsLevel;
	}

	public Long getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Long deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), null,
				null);
	}
}
