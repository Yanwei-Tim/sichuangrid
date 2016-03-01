package com.tianque.xichang.working.statisticsAccountTimeout.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.xichang.working.statisticsAccountTimeout.dao.StatisticsAccountTimeoutDao;
import com.tianque.xichang.working.statisticsAccountTimeout.domain.StatisticsAccountTimeout;

/**
 * 时限考核成绩dao实现
 * */
@Repository("statisticsAccountTimeoutDaoImpl")
public class StatisticsAccountTimeoutDaoImpl extends AbstractBaseDao implements
		StatisticsAccountTimeoutDao {

	@Override
	public void addStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout) {
		this.getSqlMapClientTemplate().insert(
				"statisticsAccountTimeout.addStatisticsAccountTimeout",
				statisticsAccountTimeout);

	}

	@Override
	public List<StatisticsAccountTimeout> findStatisticsAccountTimeoutByParentOrgIdAndTime(
			Long parentOrgId, Long orgType, Integer year, Integer month,
			String sortField, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentOrgId", parentOrgId);
		map.put("orgType", orgType);
		map.put("year", year);
		map.put("month", month);
		if (sortField != null) {
			map.put("sortField", sortField);
			map.put("sord", sord);
		}
		return getSqlMapClientTemplate()
				.queryForList(
						"statisticsAccountTimeout.getstatisticsAccountTimeoutByParentorgidAndOrgType",
						map);
	}

	@Override
	public void updateStatisticsAccountTimeoutByStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout) {
		getSqlMapClientTemplate()
				.update("statisticsAccountTimeout.updateStatisticsAccountTimeoutByStatisticsAccountTimeout",
						statisticsAccountTimeout);
	}

	@Override
	public StatisticsAccountTimeout getStatisticsAccountTimeoutByOrgIdAndMonthAndYear(
			Long orgId, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("month", month);
		List<StatisticsAccountTimeout> list = getSqlMapClientTemplate()
				.queryForList(
						"statisticsAccountTimeout.getStatisticsAccountTimeoutByOrgIdAndMonthAndYear",
						map);
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}

	@Override
	public Integer countDirtyDataByModel(List<Long> orgIdList, int year,
			int month) {
		if (orgIdList == null || orgIdList.size() == 0) {
			return 0;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgIdList",
					ParametersConvertUtil.convertToListString(orgIdList));
			map.put("year", year);
			map.put("month", month);
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"statisticsAccountTimeout.countDirtyDataByModel", map);
		}
	}

	@Override
	public void deleteDirtyDataByModel(List<Long> orgIdList, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (orgIdList == null || orgIdList.size() == 0) {
			return;
		}
		map.put("orgIdList",
				ParametersConvertUtil.convertToListString(orgIdList));
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().delete(
				"statisticsAccountTimeout.deleteDirtyDataByModel", map);

	}

	@Override
	public void batchAddDate(
			List<StatisticsAccountTimeout> statisticsAccountTimeouts) {
		batchInsertDate(statisticsAccountTimeouts,
				"statisticsAccountTimeout.addStatisticsAccountTimeout");
	}
}
