package com.tianque.jms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.cache.service.impl.MemCacheService;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.dao.JmsMessageDAO;
import com.tianque.jms.domain.JmsMessage;
import com.tianque.jms.service.JmsMessageService;

@Service("jmsMessageService")
@Transactional
public class JmsMessageServiceImpl implements JmsMessageService {

	@Autowired
	private JmsMessageDAO jmsMessageDAO;
	@Autowired
	private MemCacheService memCacheService;

	@Override
	public JmsMessage addJmsMessage(JmsMessage jmsMessage) {
		validationJmsMessage(jmsMessage);
		if (getJmsMessageByType(jmsMessage.getMessageType()) != null) {
			throw new BusinessValidationException("已存在相同类型的jms信息，无法保存。");
		}
		return getJmsMessageById(jmsMessageDAO.addJmsMessage(jmsMessage));
	}

	@Override
	public JmsMessage updateJmsMessage(JmsMessage jmsMessage) {
		validationJmsMessage(jmsMessage);
		jmsMessageDAO.updateJmsMessage(jmsMessage);
		jmsMessage = getJmsMessageById(jmsMessage.getId());
		memCacheService.set("jmsMessageType_" + jmsMessage.getMessageType(),
				jmsMessage);
		return jmsMessage;
	}

	private void validationJmsMessage(JmsMessage jmsMessage) {
		if (!StringUtil.isStringAvaliable(jmsMessage.getJmsUrl())
				|| !StringUtil.isStringAvaliable(jmsMessage.getJmsQueue())
				|| !StringUtil.isStringAvaliable(jmsMessage.getMessageType())
				|| jmsMessage.getIsSenderMessage() == null) {
			throw new BusinessValidationException("jms参数不能为空，无法保存。");
		}
	}

	@Override
	public void deleteJmsMessage(Long id) {
		if (id == null) {
			throw new BusinessValidationException("删除id不能为空。");
		}
		JmsMessage jmsMessage = getJmsMessageById(id);
		if (jmsMessage == null) {
			return;
		}
		jmsMessageDAO.deleteJmsMessage(id);
		memCacheService.remove("jmsMessageType_" + jmsMessage.getMessageType());
	}

	@Override
	public JmsMessage getJmsMessageById(Long id) {
		return jmsMessageDAO.getJmsMessageById(id);
	}

	@Override
	public List<JmsMessage> queryJmsMessageForList() {
		return jmsMessageDAO.queryJmsMessageForList(new JmsMessage());
	}

	@Override
	public JmsMessage getJmsMessageByType(String jmsMessageType) {
		if (!StringUtil.isStringAvaliable(jmsMessageType)) {
			return null;
		}
		JmsMessage jmsMessage = (JmsMessage) memCacheService
				.get("jmsMessageType_" + jmsMessageType);
		if (jmsMessage != null) {
			return jmsMessage;
		}
		jmsMessage = jmsMessageDAO.getJmsMessageByType(jmsMessageType);
		if (jmsMessage != null) {
			memCacheService
					.set("jmsMessageType_" + jmsMessage.getMessageType(),
							jmsMessage);
		}
		return jmsMessage;
	}

}
