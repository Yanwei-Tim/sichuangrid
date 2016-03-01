package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTypes;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.platformMessage.factory.FourTeamsPlatformMessageFactory;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 发消息监听器
 */
public abstract class FourTeamsSendMessageListener extends FourTeamsNothingDoIssueChangeListener {
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	protected IssueTypeService issueTypeService;
	@Autowired
	private FourTeamsPlatformMessageFactory platformMessageFactory;

	protected List<IssueType> getImportantTypes() {
		List<IssueType> importantTypes = issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.CONTRADICTION);
		importantTypes.addAll(issueTypeService
				.findIssueTypesByParentName(FourTeamsIssueTypes.SECURITYTROUBLE));
		return importantTypes;
	}

	protected PlatformMessage getSeriousIssueMessage(FourTeamsIssueNew issue) {

		return platformMessageFactory.createSeriousIssuePlatformMessage(issue);
	}

	protected abstract void sendMessageToOrg(Organization target,
			PlatformMessage pm);

	protected boolean isSeriousIssue(FourTeamsIssueNew issue) {
		List<IssueType> types = getImportantTypes();
		types.retainAll(issue.getIssueTypes());
		return types.size() > 0;
	}

	@Override
	public void onEntry(FourTeamsIssueNew issue, FourTeamsIssueStep step) {
		if (issue.getIsEmergency() == null || !issue.getIsEmergency()
				|| issue.getImportant() == null || !issue.getImportant()
				|| issue.getRelatePeopleCount() == null) {
			return;
		}
		if (isSeriousIssue(issue) && issue.getRelatePeopleCount() >= 50) {

			PlatformMessage message = getSeriousIssueMessage(issue);

			Organization messageTarget = null;
			if (issue.getOccurOrg().getOrgLevel().getInternalId() >= OrganizationLevel.TOWN) {
				messageTarget = issue.getOccurOrg();
			} else {
				messageTarget = organizationDubboService.findSomeLevelParentOrg(
						issue.getOccurOrg().getId(), OrganizationLevel.TOWN);
			}
			if (messageTarget != null) {
				sendMessageToOrg(messageTarget, message);
			}
			if (issue.getRelatePeopleCount() >= 100 && messageTarget != null
					&& messageTarget.getParentOrg() != null) {
				messageTarget = organizationDubboService
						.getSimpleOrgById(messageTarget.getParentOrg().getId());
				sendMessageToOrg(messageTarget, message);
			}
			if (issue.getRelatePeopleCount() >= 200 && messageTarget != null
					&& messageTarget.getParentOrg() != null) {
				messageTarget = organizationDubboService
						.getSimpleOrgById(messageTarget.getParentOrg().getId());
				sendMessageToOrg(messageTarget, message);
			}
		}
	}

	@Override
	public void onChanged(FourTeamsIssueNew issue, FourTeamsIssueStepGroup steps,
			FourTeamsIssueChangeEvent event) {
		if (!currentOrgChanged(event)) {
			return;
		}
		// 给新增该事件的部门发送平台消息
		sendMessageToOrg(issue.getCreateOrg(), getPm(issue, event.getOperate(),
				steps));

		PlatformMessage pm = platformMessageFactory
				.createNeedDoIssuePlatformMessage(issue);
		// 给要处理该事件的目标部门发送平台消息
		sendMessageToOrg(steps.getKeyStep().getTarget(), pm);
	}

	@Override
	public void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueChangeEvent event) {
		// 结案时给新增该事件的部门发送平台消息
		sendMessageToOrg(issue.getCreateOrg(), getPm(issue, event.getOperate(),
				null));
	}

	private PlatformMessage getPm(FourTeamsIssueNew issue, FourTeamsIssueOperate issueOper,
			FourTeamsIssueStepGroup steps) {

		return platformMessageFactory.createIssueTipPlatformMessage(issue,
				issueOper, steps);

	}

}
