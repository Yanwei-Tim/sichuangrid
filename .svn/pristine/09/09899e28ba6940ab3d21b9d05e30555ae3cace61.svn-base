package com.tianque.issue.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.domain.property.IssueSourceType;
import com.tianque.eventSource.service.EventSourceService;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueStepGroup;

/**
 * 更新社情民意
 */
@Repository("eventSourceStatChange")
public class EventSourceStatChange extends NothingDoIssueChangeListener {
	@Autowired
	private EventSourceService eventSourceService;

	@Override
	public void onComplete(IssueNew issue, IssueStep step, IssueChangeEvent event) {
		eventSourceService.updateInformationStateBySerialNumber(issue.getSerialNumber(),
				IssueSourceType.TRANS_EVENT_DONE);
	}

	@Override
	public void onChanged(IssueNew issue, IssueStepGroup steps, IssueChangeEvent event) {
		if (!event.getOperate().equals(IssueOperate.COMMENT)) {
			eventSourceService.updateInformationStateBySerialNumber(issue.getSerialNumber(),
					IssueSourceType.TRANS_EVENT_DONE);
		}
	}
}
