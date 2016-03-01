package com.tianque.issue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.issue.constants.IssueTag;
import com.tianque.issue.dao.TopIssueDao;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueStepGroup;

/**
 * 删除事件置顶状态
 * 
 * @author hjw
 */
@Repository("deleteTopIssue")
public class DeleteTopIssue extends NothingDoIssueChangeListener {
	@Autowired
	protected TopIssueDao topIssueDao;

	@Override
	public void onChanged(IssueNew issue, IssueStepGroup steps,
			IssueChangeEvent event) {
		if (needDelete(event.getOperate())) {
			int issueTag = IssueTag.NEEDDO_ISSUE;
			if (IssueOperate.CANCEL_HISTORIC.equals(event.getOperate())) {
				issueTag = IssueTag.HISTORICA_ISSUE;
			} else if (IssueOperate.COMPLETE.equals(event.getOperate())) {
				issueTag = IssueTag.VERIFICATION_ISSUE;
			}
			topIssueDao.deleteTopIssue(issue.getId(),
					getCurrentLoginedOrganization().getId(), issueTag);
		}
	}

	private boolean needDelete(IssueOperate operate) {

		return IssueOperate.SUBMIT.equals(operate)
				|| IssueOperate.ASSIGN.equals(operate)
				|| IssueOperate.REPORT_TO.equals(operate)
				|| IssueOperate.GIVETO.equals(operate)
				|| IssueOperate.BACK.equals(operate)
				|| IssueOperate.HISTORIC.equals(operate)
				|| IssueOperate.COMPLETE.equals(operate)
				|| IssueOperate.CANCEL_HISTORIC.equals(operate);
	}

	@Override
	public void onComplete(IssueNew issue, IssueStep step,
			IssueChangeEvent event) {
		topIssueDao.deleteTopIssue(issue.getId(),
				getCurrentLoginedOrganization().getId(), IssueTag.NEEDDO_ISSUE);
	}

}
