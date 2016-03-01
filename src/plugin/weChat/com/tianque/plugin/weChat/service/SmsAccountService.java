package com.tianque.plugin.weChat.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.RequestResult;
import com.tianque.plugin.weChat.domain.sms.SmsAccount;

public interface SmsAccountService {
	/**
	 * 添加短信账号
	 * @Title: saveSmsAccount 
	 * @param smsAccount
	 * @return: 
	 */
	public Long saveSmsAccount(SmsAccount smsAccount);

	/**
	 * id获取短信账号
	 * @Title: getById 
	 * @Description: TODO
	 * @param id
	 * @return: SmsAccount
	 */
	public SmsAccount getById(Long id);

	/**
	 * 更新短信账号
	 * @Title: updateSmsAccount 
	 * @param smsAccount
	 * @return: void
	 */
	public void updateSmsAccount(SmsAccount smsAccount);

	/**
	 * 分页查询短信账号
	 * @Title: findSmsAccount 
	 * @param smsAccount
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return: PageInfo<SmsAccount>
	 */
	public PageInfo<SmsAccount> findSmsAccount(SmsAccount smsAccount, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public List<SmsAccount> findSmsAccountByOrgCode(String orgCode);

	/**
	 * 查询账户余额
	 * @Title: querySmsAccountBalance 
	 * @param smsAccountName
	 * @return: Result
	 */
	public RequestResult querySmsAccountBalance(Long id);

}
