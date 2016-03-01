package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueEvaluateDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;
import com.tianque.fourTeams.fourTeamsIssue.validator.FourTeamsIssueEvaluateValidatorImpl;

@Repository("fourTeamsIssueEvaluateDao")
@SuppressWarnings("unchecked")
public class FourTeamsIssueEvaluateDaoImpl extends AbstractBaseDao implements
		FourTeamsIssueEvaluateDao {
	@Qualifier("fourTeamsIssueEvaluateValidator")
	@Autowired
	private FourTeamsIssueEvaluateValidatorImpl issueEvaluateValidator;

	@Override
	public FourTeamsIssueEvaluate addIssueEvaluate(
			FourTeamsIssueEvaluate issueEvaluate) {
		validateIssueEvaluate(issueEvaluate);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueEvaluate.addIssueEvaluate", issueEvaluate);
		return getIssueEvaluateById(id);
	}

	@Override
	public FourTeamsIssueEvaluate updateIssueEvaluate(
			FourTeamsIssueEvaluate issueEvaluate) {
		validateIssueEvaluate(issueEvaluate);
		getSqlMapClientTemplate().update("issueEvaluate.updateIssueEvaluate",
				issueEvaluate);
		return getIssueEvaluateById(issueEvaluate.getId());
	}

	@Override
	public FourTeamsIssueEvaluate getIssueEvaluateByIssueId(Long issueId) {
		return (FourTeamsIssueEvaluate) getSqlMapClientTemplate()
				.queryForObject("issueEvaluate.getIssueEvaluateByIssueId",
						issueId);
	}

	@Override
	public FourTeamsIssueEvaluate getIssueEvaluateById(Long id) {
		return (FourTeamsIssueEvaluate) getSqlMapClientTemplate()
				.queryForObject("issueEvaluate.getIssueEvaluateById", id);
	}

	private void validateIssueEvaluate(FourTeamsIssueEvaluate issueEvaluate) {
		ValidateResult result = issueEvaluateValidator.validate(issueEvaluate);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
	}
}
