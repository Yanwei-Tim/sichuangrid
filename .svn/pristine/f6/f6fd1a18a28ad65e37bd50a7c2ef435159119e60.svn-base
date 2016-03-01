package com.tianque.issue.event.listener;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueStepGroup;

/**
 * 事件监听器的默认实现
 */
public abstract class NothingDoIssueChangeListener extends AbstractBaseService
		implements IssueChangeListener {

	protected String getCurrentLoginedUserName() {
		return ThreadVariable.getSession().getUserRealName();
	}

	protected Organization getCurrentLoginedOrganization() {
		return ThreadVariable.getOrganization();
	}

	protected boolean currentOrgChanged(IssueChangeEvent event) {
		return IssueOperate.SUBMIT.equals(event.getOperate())
				|| IssueOperate.ASSIGN.equals(event.getOperate())
				|| IssueOperate.REPORT_TO.equals(event.getOperate())
				|| IssueOperate.GIVETO.equals(event.getOperate())
				|| IssueOperate.BACK.equals(event.getOperate());
	}

	@Override
	public void onChanged(IssueNew issue, IssueStepGroup steps,
			IssueChangeEvent event) {
	}

	@Override
	public void onComplete(IssueNew issue, IssueStep step,
			IssueChangeEvent event) {
	}

	@Override
	public void onEntry(IssueNew issue, IssueStep step) {
	}

	@Override
	public void beforeRemove(IssueNew issue) {
	}

	@Override
	public void onVerification(IssueNew issue, IssueStep step,
			IssueChangeEvent event) {
	}

}
