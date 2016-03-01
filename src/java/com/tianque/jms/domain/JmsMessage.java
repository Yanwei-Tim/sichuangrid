package com.tianque.jms.domain;

import com.tianque.core.base.BaseDomain;

public class JmsMessage extends BaseDomain {
	
	private String jmsUrl;
	
	private String jmsQueue;
	
	private String messageType;
	
	private Boolean isSenderMessage;

	public String getJmsUrl() {
		return jmsUrl;
	}

	public void setJmsUrl(String jmsUrl) {
		this.jmsUrl = jmsUrl;
	}

	public String getJmsQueue() {
		return jmsQueue;
	}

	public void setJmsQueue(String jmsQueue) {
		this.jmsQueue = jmsQueue;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Boolean getIsSenderMessage() {
		return isSenderMessage;
	}

	public void setIsSenderMessage(Boolean isSenderMessage) {
		this.isSenderMessage = isSenderMessage;
	}
	
}
