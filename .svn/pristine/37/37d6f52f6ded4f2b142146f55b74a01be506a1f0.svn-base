package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.OperateLogDao;
import com.tianque.domain.OperateLog;
import com.tianque.exception.base.BusinessValidationException;

@Repository("operateLogDao")
public class OperateLogDaoImpl extends AbstractBaseDao implements OperateLogDao {

	public OperateLog addOperateLog(OperateLog operateLog) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"operateLog.addoperateLog", operateLog);
		return getOperateLogById(id);
	}

	public OperateLog getOperateLogById(Long id) {
		return (OperateLog) getSqlMapClientTemplate().queryForObject(
				"operateLog.getoperateLogById", id);
	}

	public int deleteOperateLogsByDateRange(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new BusinessValidationException("未指定明确的时间区间");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		return getSqlMapClientTemplate().delete(
				"operateLog.deleteoperateLogsByDateRange", map);
	}

	public int statisticsOperateLogs(String orgCode, int operationType,
			String moduleType, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("operationType", operationType);
		map.put("moduleType", moduleType);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"operateLog.statisticsOperateLogs", map);
	}

	public List<Map<String, Object>> statisticsAllOperate(
			List<Integer> operateTypes, List<String> moduleTypes,
			String orgCode, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("operatetypes", operateTypes);
		map.put("moduletypes", moduleTypes);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (List<Map<String, Object>>) getSqlMapClientTemplate()
				.queryForList("operateLog.statisticsAllOperate", map);
	}
}
