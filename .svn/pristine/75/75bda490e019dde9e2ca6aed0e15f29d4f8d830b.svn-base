package com.tianque.jms.sender;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.redis.utils.RedisDefaultPageUtils;
import com.tianque.jms.constants.JmsMessageType;
import com.tianque.jms.conventer.HbaseMsgConverter;
import com.tianque.jms.conventer.MsgConventer;
import com.tianque.jms.domain.JmsMessage;
import com.tianque.jms.domain.JmsVo;
import com.tianque.jms.domain.SystemOperateLogDTO;
import com.tianque.jms.msg.BaseMsg;
import com.tianque.jms.msg.RedisCacheMsg;
import com.tianque.jms.service.JmsMessageService;
import com.tianque.jms.solr.SolrMessage;
import com.tianque.plugin.weChat.domain.WeChatMsg;

@Component("activeMQMessageSender")
public class ActiveMQMessageSender {
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private JmsMessageService jmsMessageService;
	private JmsTemplate jmsTemplate;
	private String oldJmsUrl;
	private String oldJmsQueueName;
	private ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	private ActiveMQQueue queue;
	private MsgConventer msgConventer = new MsgConventer();

	private static Logger logger = LoggerFactory.getLogger(ActiveMQMessageSender.class);

	public void send(BaseMsg baseMsg) {
		try {
			JmsMessage jmsMessage = getJmsMessage(baseMsg.getJmsMessageType());
			if (!jmsMessage.getIsSenderMessage()) {
				return;
			}
			getTemplate(jmsMessage).convertAndSend(baseMsg);
		} catch (Exception e) {
			logger.error("发送消息失败：" + baseMsg, e);
			if (baseMsg instanceof RedisCacheMsg) {
				RedisCacheMsg redisCacheMsg = (RedisCacheMsg) baseMsg;
				RedisDefaultPageUtils.addClearListKey(redisCacheMsg.getObjectType(),
						redisCacheMsg.getOrgId());
			}
		}
	}

	public void senderMsg(WeChatMsg weChatMsg) {
		getTemplate(GlobalSetting.JMS_QUEUE_WECHAT_NAME).convertAndSend(weChatMsg);
	}

	public void senderMsg(JmsVo jmsVo) {
		try {
			if (jmsVo instanceof SystemOperateLogDTO) {
				JmsMessage jmsMessage = getJmsMessage(JmsMessageType.HBASE_JMS_TYPE);
				if (!jmsMessage.getIsSenderMessage()) {
					return;
				}
				getTemplate(jmsMessage).convertAndSend(jmsVo);
			}
		} catch (Exception e) {
			logger.error("发送消息失败：" + jmsVo, e);
		}
	}

	public void send(SolrMessage solrMessage) {

	}

	private JmsMessage getJmsMessage(String jmsMessageType) {
		JmsMessage jmsMessage = jmsMessageService.getJmsMessageByType(jmsMessageType);
		return jmsMessage;
	}

	private JmsTemplate getTemplate(JmsMessage jmsMessage) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(jmsMessage.getJmsUrl());
		connectionFactory.setProducerWindowSize(1024000000);

		// 配置JMS模版
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setSessionTransacted(true);
		ActiveMQQueue queue = new ActiveMQQueue(jmsMessage.getJmsQueue());
		jmsTemplate.setDefaultDestination(queue);
		if (JmsMessageType.HBASE_JMS_TYPE.equals(jmsMessage.getMessageType())) {
			jmsTemplate.setMessageConverter(new HbaseMsgConverter());
		} else {
			jmsTemplate.setMessageConverter(new MsgConventer());
		}
		return jmsTemplate;
	}

	private JmsTemplate getTemplate(String jmsName) {
		String jmsUrl = globalSettingService.getGlobalValue(GlobalSetting.JMS_URL);
		String jmsQueueName = globalSettingService.getGlobalValue(jmsName);
		if (null == jmsTemplate || (null != oldJmsUrl && !oldJmsUrl.equals(jmsUrl))
				|| (null != oldJmsQueueName && !oldJmsQueueName.equals(jmsQueueName))) {
			// 配置JMS连接工厂
			setOldJmsUrl(jmsUrl);
			setOldJmsQueueName(jmsQueueName);
			connectionFactory.setBrokerURL(jmsUrl);
			connectionFactory.setProducerWindowSize(102400000);

			jmsTemplate = new JmsTemplate();
			// 配置JMS模版
			jmsTemplate.setConnectionFactory(connectionFactory);
			jmsTemplate.setSessionTransacted(true);
			queue = new ActiveMQQueue(globalSettingService.getGlobalValue(jmsName));
			jmsTemplate.setDefaultDestination(queue);
			jmsTemplate.setMessageConverter(msgConventer);
		}
		return jmsTemplate;
	}

	private void setOldJmsUrl(String jmsUrl) {
		this.oldJmsUrl = jmsUrl;
	}

	private void setOldJmsQueueName(String jmsQueueName) {
		this.oldJmsQueueName = jmsQueueName;
	}
}
