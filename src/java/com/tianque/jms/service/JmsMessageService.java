package com.tianque.jms.service;

import java.util.List;

import com.tianque.jms.domain.JmsMessage;

public interface JmsMessageService {
	public JmsMessage addJmsMessage(JmsMessage jmsMessage);

	public JmsMessage updateJmsMessage(JmsMessage jmsMessage);

	public void deleteJmsMessage(Long id);

	public JmsMessage getJmsMessageById(Long id);
	
	public List<JmsMessage> queryJmsMessageForList();
	
	public JmsMessage getJmsMessageByType(String jmsMessageType);
}
