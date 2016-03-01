package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.domain.Organization;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.service.PlatformMessageService;

/**
 * 发送平台消息
 */
@Repository("fourTeamsSendPlatformMessage")
public class FourTeamsSendPlatformMessage extends FourTeamsSendMessageListener {

	@Autowired
	private PlatformMessageService platformMessageService;

	@Override
	protected void sendMessageToOrg(Organization target, PlatformMessage pm) {

		platformMessageService.sendPlatformMessageToOrg(target.getId(), pm);
	}

}
