package com.tianque.plugin.weChat.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.sms.SmsAccount;

/**
 * 短信账号
 * @ClassName: SmsAccountDao 
 * @author: he.simin
 * @date: 2015年11月5日 下午3:24:11
 */
public interface SmsAccountDao {
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
	 * 
	 * @Title: getSmsAccountByNameAndOrgCode 
	 * @param name 必须
	 * @param orgCode 非必须
	 * @return: SmsAccount
	 */
	public SmsAccount getSmsAccountByNameAndOrgCode(String name, String orgCode);


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
	public PageInfo<SmsAccount> findSmsAccount(SmsAccount smsAccount,
			Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public List<SmsAccount> findSmsAccountByOrgCode(String orgCode);
}
