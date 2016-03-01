package com.tianque.xichang.working.flow.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.xichang.working.flow.domain.AccountStep;

public interface AccountStepDao {
	public void addAccountStep(AccountStep accountStep);

	public void updateAccountStep(AccountStep accountStep);

	/**
	 * @param map
	 * @param targetOrgId
	 *            根据时间和orgid查询出所有的 accountstep 用于扣分统计Job
	 * */
	public List<AccountStep> getAccountStepByDateAndTargetOrgId(
			Map<String, Date> date, Long targetOrgId);

	public AccountStep getLastAccountStepByAccountIdAndAccountTypeAndOrgCodeAndIsFinish(
			Long accountId, String accountType, String orgCode, Integer isFinish);

	public AccountStep getLastAccountStepByAccountIdAndAccountTypeAndOrgIdAndIsFinish(
			Long accountId, String accountType, Long orgId, Integer isFinish);

}
