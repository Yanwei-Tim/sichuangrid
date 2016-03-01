package com.tianque.issue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.issue.dao.IssueDao;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.state.IssueStepGroup;

/**
 * 调整事件当前处理步骤和org
 */
@Repository("adjustCurrentStepAndOrg")
public class AdjustCurrentStepAndOrg extends NothingDoIssueChangeListener {

	@Autowired
	private IssueDao issueDao;

	@Override
	public void onEntry(IssueNew issue, IssueStep step) {
		issue.setCurrentStep(step);
		issue.setStatus(IssueState.DEALING);
		fillLastDealProperty(issue);
		updateIssue(issue);
	}

	@Override
	public void onComplete(IssueNew issue, IssueStep step,
			IssueChangeEvent event) {
		issue.setCurrentStep(step);
		issue.setStatus(IssueState.COMPLETE);
		fillLastDealProperty(issue);
		updateIssue(issue);
	}

	@Override
	public void onVerification(IssueNew issue, IssueStep step,
			IssueChangeEvent event) {
		issue.setCurrentStep(null);
		fillLastDealProperty(issue);
		updateIssue(issue);
	}

	@Override
	public void onChanged(IssueNew issue, IssueStepGroup steps,
			IssueChangeEvent event) {
		if (currentOrgChanged(event) || needChangeLastOrg(event)) {
			issue.setCurrentStep(steps.getKeyStep());
			fillLastDealProperty(issue);
			updateIssue(issue);
		}
	}

	private boolean needChangeLastOrg(IssueChangeEvent event) {
		return IssueOperate.CONCEPT.equals(event.getOperate())
				|| IssueOperate.COMMENT.equals(event.getOperate());
	}

	private void fillLastDealProperty(IssueNew issue) {
		issue.setLastOrg(getCurrentLoginedOrganization());
		issue.setLastUsername(getCurrentLoginedUserName());
	}

	private void updateIssue(IssueNew issue) {
		issueDao.updateIssueCurrentStepAndOrg(issue);
	}

}
