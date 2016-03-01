package com.tianque.jms.solr;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class SolrMessageConverter implements MessageConverter {

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		SolrMessage solrMessage = new SolrMessage();
		if (message != null) {
			MapMessage msg = (MapMessage) message;
			solrMessage.setId(msg.getLong("id"));
			solrMessage.setIdCardNo(msg.getString("idCardNo"));
			solrMessage.setMode(msg.getString("mode"));
			solrMessage.setType(msg.getString("type"));
		}

		return solrMessage;
	}

	@Override
	public Message toMessage(Object object, Session session) throws JMSException,
			MessageConversionException {
		MapMessage mapMessage = session.createMapMessage();
		if (null != object) {
			SolrMessage solrMessage = (SolrMessage) object;

			if (null != solrMessage.getId()) {
				mapMessage.setLong("id", solrMessage.getId());
			}
			if (null != solrMessage.getType()) {
				mapMessage.setString("type", solrMessage.getType());
			}
			if (null != solrMessage.getMode()) {
				mapMessage.setString("mode", solrMessage.getMode());
			}
			if (null != solrMessage.getIdCardNo()) {
				mapMessage.setString("idCardNo", solrMessage.getIdCardNo());
			}
		}

		return mapMessage;
	}

}
