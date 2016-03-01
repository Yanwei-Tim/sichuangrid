package com.tianque.plugin.analyzing.dao;

import java.util.HashMap;
import java.util.Map;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.analyzing.domain.StatisticSearchVo;
import com.tianque.service.util.PopulationType;

public class ActualCompanyStatDaoImpl extends AbstractBaseDao implements ActualCompanyStatDao {

	@Override
	public void deleteCompanyStatType(String orgCode, int year, int month, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("orgCode", orgCode);
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().delete("baseInfoStatistic.deleteStatisticByMap", map);
	}

	private boolean isActualCompany(StatisticSearchVo statisticSearchVo) {
		String type = statisticSearchVo.getType();
		return PopulationType.ACTUAL_COMPANY.equals(type);
	}

	@Override
	public void addHistoryStatistic(StatisticSearchVo statisticSearchVo) {
		if (isActualCompany(statisticSearchVo)) {

			getSqlMapClientTemplate().insert("baseInfoStatistic.addIdleYouthHistortyStatistic",
					statisticSearchVo);
		}
	}

	@Override
	public void updateMonthCreateAttentionNumAndTotal(Map<String, Object> map) {
		getSqlMapClientTemplate().update("baseInfoStatistic.updateMonthCreateAttentionNumAndTotal",
				map);
	}

}
