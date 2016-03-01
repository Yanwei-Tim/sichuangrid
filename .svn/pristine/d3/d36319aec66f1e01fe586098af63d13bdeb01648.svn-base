package com.tianque.plugin.analysisNew.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.CalendarUtil;
import com.tianque.plugin.analysisNew.dao.ActualCompanyStatNewDao;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;
import com.tianque.service.impl.LogableService;
import com.tianque.plugin.analysisNew.util.PopulationCatalog;
import com.tianque.plugin.analysisNew.util.PopulationType;

@Service("actualCompanyStatNewService")
public class ActualCompanyStatNewServiceImpl extends LogableService implements
		ActualCompanyStatNewService {
	@Autowired
	private ActualCompanyStatNewDao actualCompanyStatNewDao;

	private StatisticSearchVo createStatisticSearchVoByPopulationCatalog(
			PopulationCatalog populationCatalog) {
		StatisticSearchVo statisticSearchVo = new StatisticSearchVo();

		statisticSearchVo.setDomainName(populationCatalog
				.getStatisticListSetting().getDomainName());

		statisticSearchVo.setTable(populationCatalog.getStatisticListSetting()
				.getTableName());
		statisticSearchVo.setPropertyField(populationCatalog
				.getStatisticListSetting().getPropertyField());
		statisticSearchVo.setCountFieldMap(populationCatalog
				.getStatisticListSetting().getCountMap());
		return statisticSearchVo;
	}

	private Map<String, Object> buildMapByMapAndStatisticSearchVo(
			StatisticSearchVo statisticSearchVo, Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return new HashMap<String, Object>();
		}
		map.put("year", statisticSearchVo.getYear());
		map.put("month", statisticSearchVo.getMonth());
		map.put("orgInternalcode", statisticSearchVo.getOrginternalcode());
		map.put("type", statisticSearchVo.getType());
		return map;
	}

	private void createHistoryStatistic(int year, int month, String type,
			StatisticSearchVo statisticSearchVo) {
		statisticSearchVo.setYear(year);
		statisticSearchVo.setMonth(month);
		statisticSearchVo.setEndDate(CalendarUtil
				.getNextMonthStart(year, month));
		statisticSearchVo.setStartDate(CalendarUtil.getMonthStart(year, month));
		statisticSearchVo.setType(type);

		// baseInfoStatisticsDao.getStatisticListForHistorty(statisticSearchVo);

		// 删除原有记录
		actualCompanyStatNewDao.deleteCompanyStatType(
				statisticSearchVo.getOrginternalcode(), year, month, type);

		actualCompanyStatNewDao.addHistoryStatistic(statisticSearchVo);

		Map<String, Object> map = new HashMap<String, Object>();
		actualCompanyStatNewDao
				.updateMonthCreateAttentionNumAndTotal(buildMapByMapAndStatisticSearchVo(
						statisticSearchVo, map));

	}

	@Override
	public void addCompanyStat() {
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getLastMonth();
		PopulationCatalog companyCatalog = PopulationCatalog
				.populationCatalog(PopulationType.ACTUAL_COMPANY);
		StatisticSearchVo statisticSearchVo = createStatisticSearchVoByPopulationCatalog(companyCatalog);

		createHistoryStatistic(year, month, PopulationCatalog.ACTUAL_COMPANY,
				statisticSearchVo);
	}

}
