package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.SuperviseLogDao;
import com.tianque.domain.SuperviseLog;

@SuppressWarnings("serial")
@Repository("superviseLogDao")
public class SuperviseLogDaoImpl extends AbstractBaseDao implements SuperviseLogDao {

	@Override
	public SuperviseLog addSuperviseLog(SuperviseLog superviseLog) {
		Long id = (Long) getSqlMapClientTemplate().insert("superviseLog.addSuperviseLog",
				superviseLog);
		return getSuperviseLogById(id);
	}

	@Override
	public List<SuperviseLog> findErrorSuperviseLogs(Long dealType, Long overType,
			String superviseType) {
		Map map = new HashMap();
		map.put("dealType", dealType);
		map.put("overType", overType);
		map.put("superviseType", superviseType);
		return getSqlMapClientTemplate().queryForList("superviseLog.findErrorSuperviseLogs", map);
	}

	@Override
	public SuperviseLog getSuperviseLogById(Long id) {
		return (SuperviseLog) getSqlMapClientTemplate().queryForObject(
				"superviseLog.getSuperviseLogById", id);
	}

	@Override
	public SuperviseLog updateSuperviseLogSuccess(Long id) {
		getSqlMapClientTemplate().update("superviseLog.updateSuperviseLogSuccess", id);
		return getSuperviseLogById(id);
	}

}
