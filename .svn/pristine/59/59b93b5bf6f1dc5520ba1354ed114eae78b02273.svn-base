package com.tianque.issue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.issue.service.IssueSerialNumberGenerator;
import com.tianque.issue.service.IssueWorkFlowEngine;
import com.tianque.issue.validator.IssueOperationLogValidator;
import com.tianque.issue.validator.IssueValidator;

@Service("defaultIssueService")
@Transactional
public class DefaultIssueServiceImpl extends AbstractIssueServiceImpl {
	@Autowired
	private IssueSerialNumberGenerator issueSerialNumberGenerator;
	@Autowired
	@Qualifier("defaultIssueValidator")
	private IssueValidator issueValidator;
	@Autowired
	@Qualifier("defaultIssueLogValidator")
	private IssueOperationLogValidator issueLogValidator;
	@Autowired
	@Qualifier("defaultIssueWorkFlowEngine")
	private IssueWorkFlowEngine issueWorkFlowEngine;

	@Override
	protected IssueSerialNumberGenerator getIssueSerialNumberGenerator() {
		return issueSerialNumberGenerator;
	}

	@Override
	protected IssueValidator getIssueValidator() {
		return issueValidator;
	}

	@Override
	protected IssueWorkFlowEngine getIssueWorkFlowEngine() {
		return issueWorkFlowEngine;
	}

	@Override
	protected IssueOperationLogValidator getIssueLogValidator() {
		return issueLogValidator;
	}
}
