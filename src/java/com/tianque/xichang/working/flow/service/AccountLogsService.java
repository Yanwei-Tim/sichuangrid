package com.tianque.xichang.working.flow.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.xichang.working.flow.domain.AccountLogs;

public interface AccountLogsService {

	public boolean addAccountLogs(AccountLogs accountLogs);

	public PageInfo<AccountLogs> findAccountLogsForPageByAccountId(
			Long accountId, String accountType, Pager genaratePager);

	public AccountLogs updateAccountLogs(AccountLogs accountLogs);

	public boolean deleteAccountLogsById(Long accountLogsId);

	public AccountLogs getAccountLogsById(Long id);

	public boolean registerAccountLogs(AccountLogs accountLogs);

	/**
	 * @param accountId
	 * @param accountType
	 * @param targetOrgId
	 *            根据台账id、targetOrgId和type查询台账的记录
	 * */
	public List<AccountLogs> findAccountLogsByAccountIdAndAccountType(
			Long accountId, String accountType, Long targetOrgId);

	/**
	 * 根据目标处理部门的orgId和关键字查询【行政部门】组织机构
	 * 
	 * @param targetOrgId
	 *            目标处理部门的组织机构id
	 * @param tag
	 *            查询的关键字
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo findAdminTargetsByName(Long targetOrgId, String tag,
			Integer page, Integer rows);

	/**
	 * 根据目标处理部门的orgId和关键字查询【职能部门】组织机构
	 * 
	 * @param targetOrgId
	 *            目标处理部门的组织机构id
	 * @param tag
	 *            查询的关键字
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo findFunctionTargetsByName(Long targetOrgId, String tag,
			Integer page, Integer rows);
	
	/**
	 * 根据当前ORGid查询当前层级及下辖的所有只能部门
	 */
	public PageInfo findFunctionTargetsByOrgId(Long targetOrgId,String tag,Integer page,Integer rows);

}
