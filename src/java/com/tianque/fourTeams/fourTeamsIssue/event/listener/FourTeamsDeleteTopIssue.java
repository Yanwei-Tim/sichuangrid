package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTag;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsTopIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

/**
 * 删除事件置顶状态
 * 
 * @author hjw
 */
@Repository("fourTeamsDeleteTopIssue")
public class FourTeamsDeleteTopIssue extends FourTeamsNothingDoIssueChangeListener {
	@Autowired
	protected FourTeamsTopIssueDao topIssueDao;

	@Override
	public void onChanged(FourTeamsIssueNew issue, FourTeamsIssueStepGroup steps,
			FourTeamsIssueChangeEvent event) {
		if (needDelete(event.getOperate())) {
			int issueTag = FourTeamsIssueTag.NEEDDO_ISSUE;
			if (FourTeamsIssueOperate.CANCEL_HISTORIC.equals(event.getOperate())) {
				issueTag = FourTeamsIssueTag.HISTORICA_ISSUE;
			}
			topIssueDao.deleteTopIssue(issue.getId(),
					getCurrentLoginedOrganization().getId(), issueTag);
		}
	}

	private boolean needDelete(FourTeamsIssueOperate operate) {

		return FourTeamsIssueOperate.SUBMIT.equals(operate)
				|| FourTeamsIssueOperate.ASSIGN.equals(operate)
				|| FourTeamsIssueOperate.REPORT_TO.equals(operate)
				|| FourTeamsIssueOperate.GIVETO.equals(operate)
				|| FourTeamsIssueOperate.BACK.equals(operate)
				|| FourTeamsIssueOperate.HISTORIC.equals(operate)
				|| FourTeamsIssueOperate.CANCEL_HISTORIC.equals(operate);
	}

	@Override
	public void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueChangeEvent event) {
		topIssueDao.deleteTopIssue(issue.getId(),
				getCurrentLoginedOrganization().getId(), FourTeamsIssueTag.NEEDDO_ISSUE);
	}
}
