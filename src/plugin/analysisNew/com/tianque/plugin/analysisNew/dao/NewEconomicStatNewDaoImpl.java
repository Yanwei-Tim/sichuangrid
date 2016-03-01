package com.tianque.plugin.analysisNew.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;
import com.tianque.plugin.analysisNew.util.PopulationType;

@Repository("newEconomicStatNewDao")
public class NewEconomicStatNewDaoImpl extends AbstractBaseDao implements
		NewEconomicStatNewDao {

	private boolean isNewEcomomic(StatisticSearchVo statisticSearchVo) {
		String type = statisticSearchVo.getType();
		return PopulationType.NEW_ECONOMIC.equals(type);
	}

	@Override
	public void deleteNewEconomicStatType(String orgCode, int year, int month,
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
	public void addHistoryStatistic(StatisticSearchVo statisticSearchVo) {
		if (isNewEcomomic(statisticSearchVo)) {

			getSqlMapClientTemplate().insert("baseInfoStatisticNew.addhistory",
					statisticSearchVo);
		}
	}

	@Override
	public void updateMonthCreateAttentionNumAndTotal(Map<String, Object> map) {
		getSqlMapClientTemplate().update(
				"baseInfoStatisticNew.updateMonthCreateAttentionNumAndTotal", map);
	}

}
