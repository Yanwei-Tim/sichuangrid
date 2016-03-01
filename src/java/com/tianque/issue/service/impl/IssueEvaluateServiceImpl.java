package com.tianque.issue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.dao.IssueEvaluateDao;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.domain.IssueEvaluate;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.issue.service.IssueEvaluateService;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.validator.IssueEvaluateValidatorImpl;

@Service("issueEvaluateService")
@Transactional
public class IssueEvaluateServiceImpl implements IssueEvaluateService {

	@Autowired
	private IssueEvaluateDao issueEvaluateDao;
	@Autowired
	private IssueService issueService;
	@Qualifier("issueEvaluateValidator")
	@Autowired
	private IssueEvaluateValidatorImpl issueEvaluateValidator;
	@Autowired
	private IssueServiceFactory issueServiceFactory;

	@Override
	public IssueEvaluate evaluate(IssueEvaluate issueEvaluate) {
		validateIssueEvaluate(issueEvaluate);
		isEvaluate(issueEvaluate);
		List<IssueAttachFile> files = issueEvaluate.getIssueAttachFiles();
		issueEvaluateDao.deleteIssueEvaluateByIssueId(issueEvaluate.getIssue()
				.getId());
		issueEvaluate = issueEvaluateDao.addIssueEvaluate(issueEvaluate);
		issueServiceFactory.getIssueServiceBySeries(
				IssueServiceFactory.DEFAULT_SERIES).addIssueEvaluateAttachFile(
				issueEvaluate, files);
		return issueEvaluate;
	}

	@Override
	public IssueEvaluate evaluateForGrade(IssueEvaluate issueEvaluate) {
		validateIssueEvaluate(issueEvaluate);
		issueEvaluateDao.deleteIssueEvaluateByIssueId(issueEvaluate.getIssue()
				.getId());
		issueEvaluate = issueEvaluateDao.addIssueEvaluate(issueEvaluate);
		return issueEvaluate;
	}

	@Override
	public IssueEvaluate getIssueEvaluateByIssueId(Long issueId) {
		IssueEvaluate issueEvaluate = issueEvaluateDao
				.getIssueEvaluateByIssueId(issueId);
		if (null != issueEvaluate) {
			issueEvaluate
					.setIssueAttachFiles(issueServiceFactory
							.getIssueServiceBySeries(
									IssueServiceFactory.DEFAULT_SERIES)
							.getIssueEvaluateAttachFileAttachFileByIssueId(
									issueId));
		}
		return issueEvaluate;
	}

	@Override
	public IssueEvaluate getIssueEvaluateByIssueIdForGrade(Long issueId) {
		IssueEvaluate issueEvaluate = issueEvaluateDao
				.getIssueEvaluateByIssueId(issueId);
		return issueEvaluate;
	}

	private void isEvaluate(IssueEvaluate issueEvaluate) {
		IssueNew issue = issueService.getFullIssueByIssueId(issueEvaluate
				.getIssue().getId());
		if (!issue.getStatus().equals(IssueState.COMPLETE)) {
			throw new BusinessValidationException("该事件未办结，不能验证！");
		}
		if (!ThreadVariable.getOrganization().getId()
				.equals(issue.getCreateOrg().getId())) {
			throw new BusinessValidationException("该事件只能由创建部门验证！");
		}
	}

	private void validateIssueEvaluate(IssueEvaluate issueEvaluate) {
		ValidateResult result = issueEvaluateValidator.validate(issueEvaluate);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
	}

}
