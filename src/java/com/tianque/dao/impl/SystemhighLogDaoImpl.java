package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SystemhighLogDao;
import com.tianque.domain.SystemhighLog;

@Repository("systemhighLogDao")
public class SystemhighLogDaoImpl extends AbstractBaseDao implements SystemhighLogDao {

	@Override
	public SystemhighLog addSystemhighLog(SystemhighLog systemhighLog) {
		Long id = (Long) getSqlMapClientTemplate().insert("systemhighLog.addSystemhighLog",
				systemhighLog);
		return getSystemhighLogById(id);
	}

	@Override
	public PageInfo<SystemhighLog> findAllSystemhighLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, String modelName, Integer operationType,
			String operationUserName, int pageNum, int pageSize, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("modelName", modelName);
		map.put("operationType", operationType);
		map.put("operationUserName", operationUserName);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"systemhighLog.countAllSystemhighlogs", map);

		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		List list = getSqlMapClientTemplate().queryForList(
				"systemhighLog.findAllSystemhighLogsForPage", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<SystemhighLog> findAllSystemhighLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, int pageNum, int pageSize, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		// map.put("modelName", modelName);
		// map.put("operationType", operationType);
		// map.put("operationUserName", operationUserName);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"systemhighLog.countAllSystemhighlogs", map);

		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		List<SystemhighLog> list = getSqlMapClientTemplate().queryForList(
				"systemhighLog.findAllSystemhighLogsForPage", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SystemhighLog> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List<SystemhighLog> list) {
		PageInfo<SystemhighLog> pageInfo = new PageInfo<SystemhighLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public SystemhighLog getSystemhighLogById(Long id) {
		return (SystemhighLog) getSqlMapClientTemplate().queryForObject(
				"systemhighLog.getSystemhighLogById", id);

	}

}
