package com.tianque.sms.service;

import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.sms.domain.Smsquerycondition;

/**
 * 查询条件管理:业务逻辑层接口
 * 
 * @author
 * @date 2013-07-03 15:25:55
 */
public interface SmsqueryconditionService extends
		BaseService<Smsquerycondition, Smsquerycondition> {
	/**
	 * 根据发送对象模板Id 得到查询条件集合
	 * 
	 * @param id
	 * @return
	 */
	public List<Smsquerycondition> findSmsqueryconditionsBySmsSendObjectId(
			Long id);

	/**
	 * 验证中描述是否重复，重复返回false,否则返回true
	 * 
	 * @param id
	 * @param field
	 * @param str
	 * @return
	 */
	public boolean validateDescription(Long id, Long objectId, String field);

	/**
	 * 验证中间key是否重复，重复返回false,否则返回true
	 * 
	 * @param id
	 * @param field
	 * @param str
	 * @return
	 */
	public boolean validateKey(Long id, Long objectId, String field);

	/**
	 * 根据发送对象模板Id 查询sql基础语句
	 * 
	 * @param objectId
	 * @return
	 */
	public Smsquerycondition getSqlBasicStatementByObjectId(Long objectId);

	/**
	 * 根据发送对象模板Id 删除sql
	 * 
	 * @param objectId
	 * @return
	 */
	public void deleteSmsqueryconditionByObjectId(Long objectId);
}
