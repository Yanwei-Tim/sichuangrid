package com.tianque.sms.domain;

import java.util.Date;

public class DownlinkMobileMessage extends com.tianque.core.base.BaseDomain implements
		java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8323730387589683535L;
	/**
	 * 短信息出口的标识
	 */
	private Long toId;
	/**
	 * 短信息出口系统
	 */
	private String toSystem = "";
	/**
	 * 短信息远端ID
	 */
	private String serverId = "";
	/**
	 * 消息体
	 */
	private String message = "";

	/**
	 * 发送人
	 */
	private String sender = "";
	/**
	 * 接收者，号码
	 */
	private String receiver = "";

	/**
	 * 上发时间
	 */
	private Date receiveTime;
	/**
	 * 开始处理时间
	 */
	private Date startDealTime;
	/**
	 * 处理时间
	 */
	private Date processTime;

	private int type;

	public Long getToId() {
		return toId;
	}

	public void setToId(Long toId) {
		this.toId = toId;
	}

	public String getToSystem() {
		return toSystem;
	}

	public void setToSystem(String toSystem) {
		this.toSystem = toSystem;
	}

	public String getServerId() {
		return serverId;
	}

	public Date getStartDealTime() {
		return startDealTime;
	}

	public void setStartDealTime(Date startDealTime) {
		this.startDealTime = startDealTime;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
