package com.tianque.xichang.working.flow.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.xichang.working.flow.domain.AccountStep;

public interface AccountStepService {

	public void addAccountStep(AccountStep accountStep);

	public void updateAccountStep(AccountStep accountStep);

	public void registerAccountStep(Long accountId, String accountType);

	/**
	 * @param map
	 * @param targetOrgId
	 *            根据时间和orgid查询出所有的 accountstep 用于扣分统计Job
	 * */
	public List<AccountStep> getAccountStepByDateAndTargetOrgId(
			Map<String, Date> date, Long targetOrgId);

	/***
	 * 查询下辖台账的最后一条处理步骤
	 * 
	 * @param accountId
	 * @param AccountType
	 * @param orgCode
	 * @return
	 */
	public AccountStep getLastAccountStepByAccountIdAndAccountTypeAndOrgCodeAndIsFinish(
			Long accountId, String accountType, String orgCode, Integer isFinish);

	public AccountStep getLastAccountStepByAccountIdAndAccountTypeAndOrgIdAndIsFinish(
			Long accountId, String accountType, Long orgId, Integer isFinish);

}
