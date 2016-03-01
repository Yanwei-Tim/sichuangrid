package com.tianque.xichang.working.flow.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.xichang.working.flow.domain.AccountLogs;

public interface AccountLogsDao {
	public boolean addAccountLogs(AccountLogs accountLogs);

	public PageInfo<AccountLogs> findAccountLogsForPageByAccountId(
			Long accountId, String accountType, Pager genaratePager);

	public AccountLogs updateAccountLogs(AccountLogs accountLogs);

	public boolean deleteAccountLogsById(Long accountLogsId);

	public AccountLogs getAccountLogsById(Long id);

	/**
	 * @param accountId
	 * @param accountType
	 * @param targetOrgId
	 *            根据台账id、targetOrgId和type查询台账的记录
	 * */
	public List<AccountLogs> findAccountLogsByAccountIdAndAccountType(
			Long accountId, String accountType, Long targetOrgId);

}
