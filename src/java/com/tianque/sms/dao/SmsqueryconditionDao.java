package com.tianque.sms.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.sms.domain.Smsquerycondition;

/**
 * 查询条件管理表:数据操作层接口
 * 
 * @author
 * @date 2013-07-03 15:25:55
 */
public interface SmsqueryconditionDao extends
		BaseDao<Smsquerycondition, Smsquerycondition> {
	/**
	 * 根据发送对象模板Id 得到查询条件集合
	 * 
	 * @param id
	 * @return
	 */
	public List<Smsquerycondition> findSmsqueryconditionsBySmsSendObjectId(
			Long id);

	/**
	 * 验证sql语句片段是否合法
	 * 
	 * @param sql
	 * @return
	 */
	public void validateSql(String sql);

	/**
	 * 根据发送对象模板Id 查询sql基础语句
	 * 
	 * @param id
	 * @return
	 */
	public Smsquerycondition getSqlBasicStatement(Long id);

	/**
	 * 验证中描述是否重复，重复返回false,否则返回true
	 * 
	 * @param objectId
	 * @param field
	 * @return
	 */
	public Smsquerycondition validateDescription(Long objectId, String field);

	/**
	 * 验证中间key是否重复，重复返回false,否则返回true
	 * 
	 * @param objectId
	 * @param field
	 * @return
	 */
	public Smsquerycondition validateKey(Long objectId, String field);

	/**
	 * 根据发送对象模板Id 删除sql
	 * 
	 * @param objectId
	 * @return
	 */
	public void deleteSmsqueryconditionByObjectId(Long objectId);
}
