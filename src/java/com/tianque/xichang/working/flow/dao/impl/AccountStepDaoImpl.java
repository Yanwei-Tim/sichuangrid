package com.tianque.xichang.working.flow.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.xichang.working.flow.dao.AccountStepDao;
import com.tianque.xichang.working.flow.domain.AccountLogs;
import com.tianque.xichang.working.flow.domain.AccountStep;

@Repository("accountStepDao")
public class AccountStepDaoImpl extends BaseDaoImpl<AccountLogs> implements
		AccountStepDao {

	@Override
	public void addAccountStep(AccountStep accountStep) {
		getSqlMapClientTemplate().insert("accountStep.addAccountStep",
				accountStep);

	}

	@Override
	public void updateAccountStep(AccountStep accountStep) {
		getSqlMapClientTemplate().update("accountStep.updateAccountStep",
				accountStep);

	}

	@Override
	public List<AccountStep> getAccountStepByDateAndTargetOrgId(
			Map<String, Date> date, Long targetOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targetOrgId", targetOrgId);
		map.put("startDate", date.get("START_DATE"));
		map.put("endDate", date.get("END_DATE"));
		return getSqlMapClientTemplate().queryForList(
				"accountStep.getAccountStepByDateAndTargetOrgId", map);
	}

	@Override
	public AccountStep getLastAccountStepByAccountIdAndAccountTypeAndOrgCodeAndIsFinish(
			Long accountId, String accountType, String orgCode, Integer isFinish) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("accountType", accountType);
		map.put("orgCode", orgCode);
		map.put("isFinish", isFinish);
		return (AccountStep) getSqlMapClientTemplate()
				.queryForObject(
						"accountStep.getLastAccountStepByAccountIdAndAccountTypeAndOrgCodeAndIsFinish",
						map);
	}

	@Override
	public AccountStep getLastAccountStepByAccountIdAndAccountTypeAndOrgIdAndIsFinish(
			Long accountId, String accountType, Long orgId, Integer isFinish) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("accountType", accountType);
		map.put("orgId", orgId);
		map.put("isFinish", isFinish);
		return (AccountStep) getSqlMapClientTemplate()
				.queryForObject(
						"accountStep.getLastAccountStepByAccountIdAndAccountTypeAndOrgIdAndIsFinish",
						map);
	}
}
