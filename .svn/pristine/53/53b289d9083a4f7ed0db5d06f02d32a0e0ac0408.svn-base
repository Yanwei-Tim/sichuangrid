package com.tianque.sms.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.sms.dao.SmsqueryconditionDao;
import com.tianque.sms.domain.Smsquerycondition;

/**
 * 查询条件管理表:数据操作层
 * 
 * @author
 * @date 2013-07-03 15:25:55
 */
@Repository("smsqueryconditionDao")
public class SmsqueryconditionDaoImpl extends
		BaseDaoImpl<Smsquerycondition, Smsquerycondition> implements
		SmsqueryconditionDao {

	@Override
	public List<Smsquerycondition> findSmsqueryconditionsBySmsSendObjectId(
			Long id) {

		return getSqlMapClientTemplate()
				.queryForList(
						"smsquerycondition.findSmsqueryconditionsBySmsSendObjectId",
						id);
	}

	@Override
	public void validateSql(String sql) {
		getSqlMapClientTemplate().queryForList("smsquerycondition.validateSql",
				sql);

	}

	@Override
	public Smsquerycondition getSqlBasicStatement(Long id) {
		return (Smsquerycondition) getSqlMapClientTemplate().queryForObject(
				"smsquerycondition.getSqlBasicStatement", id);
	}

	@Override
	public Smsquerycondition validateDescription(Long objectId, String field) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectId", objectId);
		map.put("field", field);
		return (Smsquerycondition) getSqlMapClientTemplate().queryForObject(
				"smsquerycondition.validateDescription", map);
	}

	@Override
	public Smsquerycondition validateKey(Long objectId, String field) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectId", objectId);
		map.put("field", field);
		return (Smsquerycondition) getSqlMapClientTemplate().queryForObject(
				"smsquerycondition.validateKey", map);
	}

	@Override
	public void deleteSmsqueryconditionByObjectId(Long objectId) {
		getSqlMapClientTemplate()
				.delete("smsquerycondition.deleteSmsqueryconditionByObjectId",
						objectId);
	}
}
