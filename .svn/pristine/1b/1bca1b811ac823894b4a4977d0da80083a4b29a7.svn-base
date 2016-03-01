package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

/**
 * 事件监听器的默认实现
 */
public abstract class FourTeamsNothingDoIssueChangeListener extends AbstractBaseService
		implements FourTeamsIssueChangeListener {

	protected String getCurrentLoginedUserName() {
		return ThreadVariable.getSession().getUserRealName();
	}

	protected Organization getCurrentLoginedOrganization() {
		return ThreadVariable.getOrganization();
	}

	protected boolean currentOrgChanged(FourTeamsIssueChangeEvent event) {
		return FourTeamsIssueOperate.SUBMIT.equals(event.getOperate())
				|| FourTeamsIssueOperate.ASSIGN.equals(event.getOperate())
				|| FourTeamsIssueOperate.REPORT_TO.equals(event.getOperate())
				|| FourTeamsIssueOperate.GIVETO.equals(event.getOperate())
				|| FourTeamsIssueOperate.BACK.equals(event.getOperate());
	}

	@Override
	public void onChanged(FourTeamsIssueNew issue, FourTeamsIssueStepGroup steps,
			FourTeamsIssueChangeEvent event) {
	}

	@Override
	public void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueChangeEvent event) {
	}

	@Override
	public void onEntry(FourTeamsIssueNew issue, FourTeamsIssueStep step) {
	}

	@Override
	public void beforeRemove(FourTeamsIssueNew issue) {
	}
}
