package com.tianque.xichang.working.flow.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.xichang.working.flow.dao.AccountLogsDao;
import com.tianque.xichang.working.flow.domain.AccountLogs;

@Repository("accountLogsDao")
public class AccountLogsDaoImpl extends BaseDaoImpl<AccountLogs> implements
		AccountLogsDao {

	@Override
	public boolean addAccountLogs(AccountLogs accountLogs) {
		return isAddSuccess(accountLogs, "addAccountLogs");
	}

	@Override
	public PageInfo<AccountLogs> findAccountLogsForPageByAccountId(
			Long accountId, String accountType, Pager genaratePager) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("accountType", accountType);
		return findPagerBySearchVo(map, genaratePager,
				"AccountLogsForPageByAccountId");
	}

	@Override
	public AccountLogs updateAccountLogs(AccountLogs accountLogs) {
		return update(accountLogs);
	}

	@Override
	public boolean deleteAccountLogsById(Long accountLogsId) {
		return delete(accountLogsId);
	}

	@Override
	public AccountLogs getAccountLogsById(Long id) {
		return get(id);
	}

	@Override
	public List<AccountLogs> findAccountLogsByAccountIdAndAccountType(
			Long accountId, String accountType, Long targetOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("accountType", accountType);
		map.put("targetOrgId", targetOrgId);
		return getSqlMapClientTemplate().queryForList(
				"accountLogs.findAccountLogsByAccountIdAndAccountType", map);
	}

}
