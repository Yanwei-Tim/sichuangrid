package com.tianque.plugin.analysisNew.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;

@Component("builddataStatNewDao")
public class BuilddataStatNewDaoImpl extends AbstractBaseDao implements
		BuilddataStatNewDao {

	@Override
	public void addHistoryStatistic(StatisticSearchVo statisticSearchVo) {
		getSqlMapClientTemplate().insert(
				"baseInfoStatisticNew.addBuilddataHistortyStatistic",
				statisticSearchVo);
	}

	@Override
	public void deleteBuilddataStatType(String orgCode, int year, int month,
			String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("orgCode", orgCode);
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().delete(
				"baseInfoStatisticNew.deleteStatisticByMap", map);
	}

	@Override
	public void updateMonthCreateAttentionNumAndTotal(Map<String, Object> map) {
		getSqlMapClientTemplate().update(
				"baseInfoStatisticNew.updateMonthCreateAttentionNumAndTotal",
				map);
	}

	public Map<String, Object> getMonthCreateNumAndTotal(
			StatisticSearchVo statisticSearchVo) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatisticNew.getMonthTotal", statisticSearchVo);
	}

}
