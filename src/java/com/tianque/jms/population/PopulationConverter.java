package com.tianque.jms.population;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class PopulationConverter implements MessageConverter {

	@Override
	public Object fromMessage(Message arg0) throws JMSException, MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message toMessage(Object arg0, Session arg1) throws JMSException,
			MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}

}
