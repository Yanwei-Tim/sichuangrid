package com.tianque.issue.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.dao.IssueEvaluateDao;
import com.tianque.issue.domain.IssueEvaluate;
import com.tianque.issue.validator.IssueEvaluateValidatorImpl;

@Repository("issueEvaluateDao")
@SuppressWarnings("unchecked")
public class IssueEvaluateDaoImpl extends AbstractBaseDao implements
		IssueEvaluateDao {
	@Qualifier("issueEvaluateValidator")
	@Autowired
	private IssueEvaluateValidatorImpl issueEvaluateValidator;

	@Override
	public IssueEvaluate addIssueEvaluate(IssueEvaluate issueEvaluate) {
		validateIssueEvaluate(issueEvaluate);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueEvaluate.addIssueEvaluate", issueEvaluate);
		return getIssueEvaluateById(id);
	}

	@Override
	public IssueEvaluate updateIssueEvaluate(IssueEvaluate issueEvaluate) {
		validateIssueEvaluate(issueEvaluate);
		getSqlMapClientTemplate().update("issueEvaluate.updateIssueEvaluate",
				issueEvaluate);
		return getIssueEvaluateById(issueEvaluate.getId());
	}

	@Override
	public IssueEvaluate getIssueEvaluateByIssueId(Long issueId) {
		return (IssueEvaluate) getSqlMapClientTemplate().queryForObject(
				"issueEvaluate.getIssueEvaluateByIssueId", issueId);
	}

	@Override
	public IssueEvaluate getIssueEvaluateById(Long id) {
		return (IssueEvaluate) getSqlMapClientTemplate().queryForObject(
				"issueEvaluate.getIssueEvaluateById", id);
	}

	public void deleteIssueEvaluateByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"issueEvaluate.deleteIssueEvaluateByIssueId", issueId);
	}

	private void validateIssueEvaluate(IssueEvaluate issueEvaluate) {
		ValidateResult result = issueEvaluateValidator.validate(issueEvaluate);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
	}
}
