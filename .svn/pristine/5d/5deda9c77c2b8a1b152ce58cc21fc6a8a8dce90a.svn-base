package com.tianque.jms.msg;

import java.io.Serializable;

import com.tianque.jms.OperateMode;

public class BaseMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Long objectId;
	protected OperateMode mode;
	protected Long orgId;
	protected String objectType;
	protected String msgType;

	/** 传递object的list 格式为(objectId-orgId-objectType,objectId-orgId-objectType) */
	private String objectList;
	
	protected String jmsMessageType;

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public void setMode(OperateMode mode) {
		this.mode = mode;
	}

	public OperateMode getMode() {
		return mode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String toString() {
		return new StringBuffer().append("objectId:").append(objectId).append(";mode:")
				.append(mode).append(";orgId:").append(orgId).append(";objectType:")
				.append(objectType).toString();
	}

	public String getObjectList() {
		return objectList;
	}

	public void setObjectList(String objectList) {
		this.objectList = objectList;
	}

	public String getJmsMessageType() {
		return jmsMessageType;
	}

	public void setJmsMessageType(String jmsMessageType) {
		this.jmsMessageType = jmsMessageType;
	}

}
