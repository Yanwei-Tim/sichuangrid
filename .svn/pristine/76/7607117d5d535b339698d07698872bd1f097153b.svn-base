package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.DetailedRuleDailyLogRelaDao;
import com.tianque.domain.DetailedRuleDailyLogRela;

@Repository("detailedRuleDailyLogRelaDao")
public class DetailedRuleDailyLogRelaDaoImpl extends AbstractBaseDao implements
		DetailedRuleDailyLogRelaDao {

	@Override
	public DetailedRuleDailyLogRela addDetailedRuleDailyLogRela(
			DetailedRuleDailyLogRela detailedRuleDailyLogRela) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"detailedRuleDailyLogRela.addDetailedRuleDailyLogRela", detailedRuleDailyLogRela);
		return getDetailedRuleDailyLogRelaById(id);
	}

	private DetailedRuleDailyLogRela getDetailedRuleDailyLogRelaById(Long id) {
		return (DetailedRuleDailyLogRela) getSqlMapClientTemplate().queryForObject(
				"detailedRuleDailyLogRela.getDetailedRuleDailyLogRelaById", id);
	}

	@Override
	public void deleteDetailedRuleDailyLogRelasByDetailedRuleId(Long detailedRuleId) {
		getSqlMapClientTemplate().delete(
				"detailedRuleDailyLogRela.deleteDetailedRuleDailyLogRelasByDetailedRuleId",
				detailedRuleId);
	}

	@Override
	public DetailedRuleDailyLogRela getDetailedRuleDailyLogRelaByWorkingRecordId(Long dailyLogId) {
		return (DetailedRuleDailyLogRela) getSqlMapClientTemplate()
				.queryForObject(
						"detailedRuleDailyLogRela.getDetailedRuleDailyLogRelaByWorkingRecordId",
						dailyLogId);
	}

	@Override
	public List<DetailedRuleDailyLogRela> getDetailedRuleDailyLogRelaByWorkingRecordIds(
			Long dailyLogType, String dailyLogIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> dailyLogIdlist = new java.util.ArrayList<Long>();
		if (dailyLogIds != null && dailyLogIds.length() > 0) {
			String[] catelogues = dailyLogIds.split(",");
			for (int i = 0; i < catelogues.length; i++) {
				if (catelogues[i] != null && catelogues[i].trim().length() > 0) {
					dailyLogIdlist.add(Long.valueOf(catelogues[i].trim()));
				}
			}
		}
		map.put("dailyLogType", dailyLogType);
		map.put("dailyLogIdlist", dailyLogIdlist);
		return getSqlMapClientTemplate().queryForList(
				"detailedRuleDailyLogRela.getDetailedRuleDailyLogRelaByWorkingRecordIds", map);
	}

}
