package com.tianque.xichang.working.flow.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.xichang.working.flow.dao.AccountStepDao;
import com.tianque.xichang.working.flow.domain.AccountStep;
import com.tianque.xichang.working.flow.service.AccountStepService;

@Service("accountStepService")
@Transactional
public class AccountStepServiceImpl implements AccountStepService {

	@Autowired
	private AccountStepDao accountStepDao;

	@Override
	public void addAccountStep(AccountStep accountStep) {
		if (null == accountStep) {
			throw new BusinessValidationException("参数错误");
		}
		accountStepDao.addAccountStep(accountStep);

	}

	@Override
	public void updateAccountStep(AccountStep accountStep) {
		if (null == accountStep) {
			throw new BusinessValidationException("参数错误");
		}
		accountStepDao.updateAccountStep(accountStep);

	}

	@Override
	public void registerAccountStep(Long accountId, String accountType) {
		AccountStep accountStep = new AccountStep();
		accountStep.setAccountId(accountId);
		accountStep.setAccountType(accountType);
		accountStep.setIsFinish(false);
		accountStep.setTargetOrg(ThreadVariable.getOrganization());
		accountStep.setOrgType(ThreadVariable.getOrganization().getOrgType()
				.getId());
		accountStepDao.addAccountStep(accountStep);
	}

	@Override
	public List<AccountStep> getAccountStepByDateAndTargetOrgId(
			Map<String, Date> date, Long targetOrgId) {
		if (date == null || targetOrgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return accountStepDao.getAccountStepByDateAndTargetOrgId(date,
				targetOrgId);
	}

	@Override
	public AccountStep getLastAccountStepByAccountIdAndAccountTypeAndOrgCodeAndIsFinish(
			Long accountId, String accountType, String orgCode, Integer isFinish) {
		if (null == accountId || !StringUtil.isStringAvaliable(accountType)
				|| !StringUtil.isStringAvaliable(orgCode) || null == isFinish) {
			throw new BusinessValidationException("参数错误");
		}
		return accountStepDao
				.getLastAccountStepByAccountIdAndAccountTypeAndOrgCodeAndIsFinish(
						accountId, accountType, orgCode, isFinish);
	}

	@Override
	public AccountStep getLastAccountStepByAccountIdAndAccountTypeAndOrgIdAndIsFinish(
			Long accountId, String accountType, Long orgId, Integer isFinish) {
		if (null == accountId || !StringUtil.isStringAvaliable(accountType)
				|| null == orgId || null == isFinish)
			throw new BusinessValidationException("参数错误");

		return accountStepDao
				.getLastAccountStepByAccountIdAndAccountTypeAndOrgIdAndIsFinish(
						accountId, accountType, orgId, isFinish);
	}
}
