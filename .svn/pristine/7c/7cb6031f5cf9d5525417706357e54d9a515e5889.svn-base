package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.listener.FourTeamsIssueChangeListener;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.state.impl.FourTeamsDealingState;

@Service("fourTeamsDefaultIssueWorkFlowEngine")
@Transactional
public class FourTeamsDefaultIssueWorkFlowEngineImpl extends
		FourTeamsAbstractIssueWorkFlowEngineImpl {

	@Autowired
	@Qualifier("defaultFourTeamsIssueListeners")
	private ArrayList<FourTeamsIssueChangeListener> listeners;

	@Override
	protected FourTeamsIssueStep createEntryIssueStep(FourTeamsIssueNew issue) {
		FourTeamsIssueStep step = new FourTeamsIssueStep();
		step.setState(FourTeamsDealingState.class.getName());
		step.setStateCode(FourTeamsIssueState.DEALING_CODE);
		step.setEntryDate(issue.getCreateDate());
		step.setLastDealDate(step.getEntryDate());
		step.setSource(issue.getCreateOrg());
		step.setTarget(issue.getCreateOrg());
		step.setIssue(issue);
		return step;
	}

	@Override
	protected List<FourTeamsIssueChangeListener> getIssueChangeListeners() {
		return listeners;
	}

}
