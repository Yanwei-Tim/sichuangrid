package com.tianque.sms.domain.vo;


/**
 * 短信收件箱:SrarchVO(SMSDOWNLINK)
 * 
 * @author 
 * @date 2013-07-08 17:27:15
 */
public class SearchSmsdownlinkVo extends com.tianque.core.base.BaseDomain {
	
	/** 最大 发送时间 */
	private java.util.Date		maxSendTime;
	/** 最小 发送时间 */
	private java.util.Date		minSendTime;		
	/** 最大 接收时间 */
	private java.util.Date		maxReceiveTime;
	/** 最小 接收时间 */
	private java.util.Date		minReceiveTime;		
	/** 接收者手机 */
	private String		receiverMobile;			
	/** 发送者 */
	private Long		sender;			
	/** 接收者所属网格 */
	private com.tianque.domain.Organization		receiverOrgId;			
	/** 发送状态 */
	private Long		status;			
	/** 接收者所属网格编号 */
	private String		receiverOrgInternalCode;			
	/** 发送者姓名 */
	private String		senderName;			
	/** 接收者姓名 */
	private String		receiverName;			
	/** 发送内容 */
	private String		content;			
	
	
	/** get 最大 发送时间 */
	public java.util.Date getMaxSendTime() {
		return maxSendTime;
	}
	/** set 最大 发送时间 */
	public void setMaxSendTime(java.util.Date maxSendTime) {
		this.maxSendTime = maxSendTime;
	}
	/** get 最小 发送时间 */
	public java.util.Date getMinSendTime() {
		return minSendTime;
	}
	/** set 最小 发送时间 */
	public void setMinSendTime(java.util.Date minSendTime) {
		this.minSendTime = minSendTime;
	}
	
	/** get 最大 接收时间 */
	public java.util.Date getMaxReceiveTime() {
		return maxReceiveTime;
	}
	/** set 最大 接收时间 */
	public void setMaxReceiveTime(java.util.Date maxReceiveTime) {
		this.maxReceiveTime = maxReceiveTime;
	}
	/** get 最小 接收时间 */
	public java.util.Date getMinReceiveTime() {
		return minReceiveTime;
	}
	/** set 最小 接收时间 */
	public void setMinReceiveTime(java.util.Date minReceiveTime) {
		this.minReceiveTime = minReceiveTime;
	}
	
	/** get 接收者手机 */
	public String getReceiverMobile() {
		return receiverMobile;
	}
	/** set 接收者手机 */
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	
	/** get 发送者 */
	public Long getSender() {
		return sender;
	}
	/** set 发送者 */
	public void setSender(Long sender) {
		this.sender = sender;
	}
	
	/** get 接收者所属网格 */
	public com.tianque.domain.Organization getReceiverOrgId() {
		return receiverOrgId;
	}
	/** set 接收者所属网格 */
	public void setReceiverOrgId(com.tianque.domain.Organization receiverOrgId) {
		this.receiverOrgId = receiverOrgId;
	}
	
	/** get 发送状态 */
	public Long getStatus() {
		return status;
	}
	/** set 发送状态 */
	public void setStatus(Long status) {
		this.status = status;
	}
	
	/** get 接收者所属网格编号 */
	public String getReceiverOrgInternalCode() {
		return receiverOrgInternalCode;
	}
	/** set 接收者所属网格编号 */
	public void setReceiverOrgInternalCode(String receiverOrgInternalCode) {
		this.receiverOrgInternalCode = receiverOrgInternalCode;
	}
	
	/** get 发送者姓名 */
	public String getSenderName() {
		return senderName;
	}
	/** set 发送者姓名 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	/** get 接收者姓名 */
	public String getReceiverName() {
		return receiverName;
	}
	/** set 接收者姓名 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	/** get 发送内容 */
	public String getContent() {
		return content;
	}
	/** set 发送内容 */
	public void setContent(String content) {
		this.content = content;
	}
	
}
