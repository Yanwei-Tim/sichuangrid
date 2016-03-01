package com.tianque.service;

import org.oproject.framework.orm.PageResult;

import com.tianque.domain.AccountLoginDetails;

public interface AccountLoginDetailsService {

	/***
	 * 账户登录统计列表查询
	 * 
	 * @param year
	 * @param month
	 * @param orgId
	 * @param searchType
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageResult<AccountLoginDetails> getAccountLoginDetailsList(
			Long year, Long month, Long orgId, Integer searchType,
			Integer page, Integer rows, String sidx, String sord);

	/***
	 * 生成报表
	 * 
	 * @param year
	 * @param month
	 * @param orgId
	 * @param searchType
	 */
	public void createAccountDetails(Long year, Long month);

}
