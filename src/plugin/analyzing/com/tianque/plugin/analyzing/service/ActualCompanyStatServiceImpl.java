package com.tianque.plugin.analyzing.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.util.CalendarUtil;
import com.tianque.plugin.analyzing.dao.ActualCompanyStatDao;
import com.tianque.plugin.analyzing.domain.StatisticSearchVo;
import com.tianque.service.impl.LogableService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;

public class ActualCompanyStatServiceImpl extends LogableService implements
		ActualCompanyStatService {
	@Autowired
	private ActualCompanyStatDao actualCompanyStatDao;

	private StatisticSearchVo createStatisticSearchVoByPopulationCatalog(
			PopulationCatalog populationCatalog) {
		StatisticSearchVo statisticSearchVo = new StatisticSearchVo();

		statisticSearchVo
				.setDomainName(populationCatalog.getStatisticListSetting().getDomainName());

		statisticSearchVo.setTable(populationCatalog.getStatisticListSetting().getTableName());
		statisticSearchVo.setPropertyField(populationCatalog.getStatisticListSetting()
				.getPropertyField());
		statisticSearchVo.setCountFieldMap(populationCatalog.getStatisticListSetting()
				.getCountMap());
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
		statisticSearchVo.setEndDate(CalendarUtil.getNextMonthStart(year, month));
		statisticSearchVo.setStartDate(CalendarUtil.getMonthStart(year, month));
		statisticSearchVo.setType(type);

		// baseInfoStatisticsDao.getStatisticListForHistorty(statisticSearchVo);

		// 删除原有记录
		actualCompanyStatDao.deleteCompanyStatType(statisticSearchVo.getOrginternalcode(), year,
				month, type);

		actualCompanyStatDao.addHistoryStatistic(statisticSearchVo);

		Map<String, Object> map = new HashMap<String, Object>();
		actualCompanyStatDao
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

		createHistoryStatistic(year, month, PopulationCatalog.ACTUAL_COMPANY, statisticSearchVo);
	}

}
