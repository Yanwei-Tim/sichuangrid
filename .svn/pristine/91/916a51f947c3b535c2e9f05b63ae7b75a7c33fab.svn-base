package com.tianque.issue.event.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.domain.Contacter;
import com.tianque.domain.Organization;
import com.tianque.domain.SmsSendBox;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.SmsSendBoxService;

/**
 * 发送平台消息
 */
@Repository("sendPlatformMessage")
public class SendPlatformMessage extends SendMessageListener {

	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private SmsSendBoxService smsSendBoxService;

	@Override
	protected void sendMessageToOrg(Organization target, PlatformMessage pm) {

		platformMessageService.sendPlatformMessageToOrg(target.getId(), pm);

	}

	@Override
	protected void sendSMSingToOrg(SmsSendBox smsSendBox,
			List<Contacter> contacters) {
		smsSendBoxService.addSmsSendBox(smsSendBox, contacters);
		// smsReceivedBoxService.addSmsReceivedBox(smsReceivedBox);
	}
}
