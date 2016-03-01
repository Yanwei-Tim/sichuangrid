package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import org.springframework.stereotype.Repository;

import com.tianque.domain.Organization;
import com.tianque.platformMessage.domain.PlatformMessage;

/**
 * 发短信
 */
@Repository("fourTeamsSendSmsMessage")
public class FourTeamsSendSmsMessage extends FourTeamsSendMessageListener {

	@Override
	protected void sendMessageToOrg(Organization target, PlatformMessage pm) {

	}

}
