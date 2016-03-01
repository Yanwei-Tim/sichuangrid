package com.tianque.plugin.analysisNew.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;
import com.tianque.plugin.analysisNew.util.PopulationType;

@Repository("actualCompanyStatNewDao")
public class ActualCompanyStatNewDaoImpl extends AbstractBaseDao implements
		ActualCompanyStatNewDao {

	@Override
	public void deleteCompanyStatType(String orgCode, int year, int month,
			String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("orgCode", orgCode);
		map.put("year", year);
		map.put("month", month);
		getSqlMapClientTemplate().delete(
				"baseInfoStatisticNew.deleteStatisticByMap", map);
	}

	private boolean isActualCompany(StatisticSearchVo statisticSearchVo) {
		String type = statisticSearchVo.getType();
		return PopulationType.ACTUAL_COMPANY.equals(type);
	}

	@Override
	public void addHistoryStatistic(StatisticSearchVo statisticSearchVo) {
		if (isActualCompany(statisticSearchVo)) {

			getSqlMapClientTemplate().insert(
					"baseInfoStatisticNew.addIdleYouthHistortyStatistic",
					statisticSearchVo);
		}
	}

	@Override
	public void updateMonthCreateAttentionNumAndTotal(Map<String, Object> map) {
		getSqlMapClientTemplate().update(
				"baseInfoStatisticNew.updateMonthCreateAttentionNumAndTotal",
				map);
	}

}
