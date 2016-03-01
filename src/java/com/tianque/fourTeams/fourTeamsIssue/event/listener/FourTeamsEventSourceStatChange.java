package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.domain.property.IssueSourceType;
import com.tianque.eventSource.service.EventSourceService;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

/**
 * 更新社情民意
 */
@Repository("fourTeamsEventSourceStatChange")
public class FourTeamsEventSourceStatChange extends FourTeamsNothingDoIssueChangeListener {
	@Autowired
	private EventSourceService eventSourceService;

	@Override
	public void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step,
			FourTeamsIssueChangeEvent event) {
		eventSourceService.updateInformationStateBySerialNumber(issue
				.getSerialNumber(), IssueSourceType.TRANS_EVENT_DONE);
	}

	@Override
	public void onChanged(FourTeamsIssueNew issue, FourTeamsIssueStepGroup steps,
			FourTeamsIssueChangeEvent event) {
		if (!event.getOperate().equals(FourTeamsIssueOperate.COMMENT)) {
			eventSourceService.updateInformationStateBySerialNumber(issue
					.getSerialNumber(), IssueSourceType.TRANS_EVENT_DONE);
		}
	}
}
