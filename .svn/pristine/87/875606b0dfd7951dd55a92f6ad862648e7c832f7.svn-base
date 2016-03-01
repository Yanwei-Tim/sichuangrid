package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

/**
 * 调整事件当前处理步骤和org
 */
@Repository("fourTeamsAdjustCurrentStepAndOrg")
public class FourTeamsAdjustCurrentStepAndOrg extends FourTeamsNothingDoIssueChangeListener {

	@Autowired
	private FourTeamsIssueDao issueDao;

	@Override
	public void onEntry(FourTeamsIssueNew issue, FourTeamsIssueStep step) {
		issue.setCurrentStep(step);
		issue.setStatus(FourTeamsIssueState.DEALING);
		fillLastDealProperty(issue);
		updateIssue(issue);
	}

	@Override
	public void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueChangeEvent event) {
		issue.setCurrentStep(null);
		fillLastDealProperty(issue);
		updateIssue(issue);
	}

	@Override
	public void onChanged(FourTeamsIssueNew issue, FourTeamsIssueStepGroup steps,
			FourTeamsIssueChangeEvent event) {
		if (currentOrgChanged(event) || needChangeLastOrg(event)) {
			issue.setCurrentStep(steps.getKeyStep());
			fillLastDealProperty(issue);
			updateIssue(issue);
		}
	}

	private boolean needChangeLastOrg(FourTeamsIssueChangeEvent event) {
		return FourTeamsIssueOperate.CONCEPT.equals(event.getOperate())
				|| FourTeamsIssueOperate.COMMENT.equals(event.getOperate());
	}

	private void fillLastDealProperty(FourTeamsIssueNew issue) {
		issue.setLastOrg(getCurrentLoginedOrganization());
		issue.setLastUsername(getCurrentLoginedUserName());
	}

	private void updateIssue(FourTeamsIssueNew issue) {
		issueDao.updateIssueCurrentStepAndOrg(issue);
	}

}
