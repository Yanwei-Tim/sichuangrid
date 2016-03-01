package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueEvaluateDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.factory.FourTeamsIssueServiceFactory;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueEvaluateService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueService;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueEvaluateValidatorImpl;

@Service("fourTeamsIssueEvaluateService")
@Transactional
public class FourTeamsIssueEvaluateServiceImpl implements
		FourTeamsIssueEvaluateService {

	@Autowired
	private FourTeamsIssueEvaluateDao issueEvaluateDao;
	@Autowired
	private FourTeamsIssueService issueService;
	@Qualifier("fourTeamsIssueEvaluateValidator")
	@Autowired
	private FourTeamsIssueEvaluateValidatorImpl issueEvaluateValidator;
	@Autowired
	private FourTeamsIssueServiceFactory issueServiceFactory;

	@Override
	public FourTeamsIssueEvaluate evaluate(FourTeamsIssueEvaluate issueEvaluate) {
		validateIssueEvaluate(issueEvaluate);
		isEvaluate(issueEvaluate);
		List<FourTeamsIssueAttachFile> files = issueEvaluate
				.getIssueAttachFiles();
		if (issueEvaluateDao.getIssueEvaluateByIssueId(issueEvaluate.getIssue()
				.getId()) != null && issueEvaluate.getId() != null) {
			issueEvaluate = issueEvaluateDao.updateIssueEvaluate(issueEvaluate);
			issueServiceFactory.getIssueServiceBySeries(
					FourTeamsIssueServiceFactory.DEFAULT_SERIES)
					.addIssueEvaluateAttachFile(issueEvaluate, files);
			return issueEvaluate;
		} else {
			issueEvaluate = issueEvaluateDao.addIssueEvaluate(issueEvaluate);
			issueServiceFactory.getIssueServiceBySeries(
					FourTeamsIssueServiceFactory.DEFAULT_SERIES)
					.addIssueEvaluateAttachFile(issueEvaluate, files);
			return issueEvaluate;
		}
	}

	@Override
	public FourTeamsIssueEvaluate getIssueEvaluateByIssueId(Long issueId) {
		FourTeamsIssueEvaluate issueEvaluate = issueEvaluateDao
				.getIssueEvaluateByIssueId(issueId);
		if (null != issueEvaluate) {
			issueEvaluate.setIssueAttachFiles(issueServiceFactory
					.getIssueServiceBySeries(
							FourTeamsIssueServiceFactory.DEFAULT_SERIES)
					.getIssueEvaluateAttachFileAttachFileByIssueId(issueId));
		}
		return issueEvaluate;
	}

	private void isEvaluate(FourTeamsIssueEvaluate issueEvaluate) {
		FourTeamsIssueNew issue = issueService
				.getFullIssueByIssueId(issueEvaluate.getIssue().getId());
		if (!issue.getStatus().equals(FourTeamsIssueState.COMPLETE)) {
			throw new BusinessValidationException("该事件未办结，不能验证！");
		}
		if (!ThreadVariable.getOrganization().getId()
				.equals(issue.getCreateOrg().getId())) {
			throw new BusinessValidationException("该事件只能由创建部门验证！");
		}
	}

	private void validateIssueEvaluate(FourTeamsIssueEvaluate issueEvaluate) {
		ValidateResult result = issueEvaluateValidator.validate(issueEvaluate);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
	}

}
