package com.tianque.jms.dao;

import java.util.List;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.jms.domain.JmsMessage;

@DynamicIbatisDAO(value = "JmsMessageDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("JmsMessageDAO")
public interface JmsMessageDAO {
	public Long addJmsMessage(JmsMessage jmsMessage);

	public int updateJmsMessage(JmsMessage jmsMessage);

	public void deleteJmsMessage(Long id);

	public JmsMessage getJmsMessageById(Long id);
	
	public List<JmsMessage> queryJmsMessageForList(JmsMessage jmsMessage);
	
	public JmsMessage getJmsMessageByType(String jmsMessageType);
}
