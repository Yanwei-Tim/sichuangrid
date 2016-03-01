package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueSerialNumberGenerator;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueWorkFlowEngine;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueOperationLogValidator;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueValidator;

@Service("fourTeamsDefaultIssueService")
@Transactional
public class FourTeamsDefaultIssueServiceImpl extends
		FourTeamsAbstractIssueServiceImpl {
	@Autowired
	private FourTeamsIssueSerialNumberGenerator issueSerialNumberGenerator;
	@Autowired
	@Qualifier("defaultFourTeamsIssueValidator")
	private FourTeamsIssueValidator issueValidator;
	@Autowired
	@Qualifier("fourTeamsDefaultIssueLogValidator")
	private FourTeamsIssueOperationLogValidator issueLogValidator;
	@Autowired
	@Qualifier("fourTeamsDefaultIssueWorkFlowEngine")
	private FourTeamsIssueWorkFlowEngine issueWorkFlowEngine;

	@Override
	protected FourTeamsIssueSerialNumberGenerator getIssueSerialNumberGenerator() {
		return issueSerialNumberGenerator;
	}

	@Override
	protected FourTeamsIssueValidator getIssueValidator() {
		return issueValidator;
	}

	@Override
	protected FourTeamsIssueWorkFlowEngine getIssueWorkFlowEngine() {
		return issueWorkFlowEngine;
	}

	@Override
	protected FourTeamsIssueOperationLogValidator getIssueLogValidator() {
		return issueLogValidator;
	}
}
