package com.tianque.plugin.analyzing.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.analyzing.dao.BaseInfoStatisticDao;
import com.tianque.plugin.analyzing.dao.NewEconomicStatDao;
import com.tianque.plugin.analyzing.domain.StatisticSearchVo;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.impl.LogableService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

@Service("newEconomicStatService")
public class NewEconomicStatServiceImpl extends LogableService implements
		NewEconomicStatService {
	@Autowired
	private NewEconomicStatDao newEconomicStatDao;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private BaseInfoStatisticDao baseInfoStatisticsDao;

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

	private void createHistoryStatistic(int year, int month, String type,
			StatisticSearchVo statisticSearchVo) {
		statisticSearchVo.setYear(year);
		statisticSearchVo.setMonth(month);
		statisticSearchVo.setEndDate(CalendarUtil
				.getNextMonthStart(year, month));
		statisticSearchVo.setStartDate(CalendarUtil.getMonthStart(year, month));
		statisticSearchVo.setType(type);

		tableService.createAnalyseTable(AnalyseUtil.IMPORTPERSONTABLENAME,
				AnalyseUtil.IMPORTPERSONSQL, year, month);

		// 删除原有记录
		newEconomicStatDao.deleteNewEconomicStatType(
				statisticSearchVo.getOrginternalcode(), year, month, type);

		Map<String, Object> map = new HashMap<String, Object>();
		if (statisticSearchVo.getIsemphasis() == null) {
			// 没有关注字段的表的处理，总数即为关注数
			// map =
			// baseInfoStatisticsDao.getMonthCreateNumAndTotal(statisticSearchVo);
		} else {
			// map =
			// baseInfoStatisticsDao.getMonthCreateAttentionNumAndTotal(statisticSearchVo);
		}
		statisticSearchVo.setMonthCreate(((BigDecimal) map.get("MONTHCREATE"))
				.longValue());
		statisticSearchVo
				.setAttentionNum(((BigDecimal) map.get("ATTENTIONNUM"))
						.longValue());
		statisticSearchVo.setTotal(((BigDecimal) map.get("TOTAL")).longValue());

		addHistoryStatistic(statisticSearchVo);
	}

	private void addHistoryStatistic(StatisticSearchVo statisticSearchVo) {
		List<PropertyDict> dicts = propertyDictService
				.findPropertyDictByDomainName(statisticSearchVo.getDomainName());
		List<StatisticSearchVo> list = new ArrayList<StatisticSearchVo>();
		for (int i = 0; i < dicts.size(); i++) {
			statisticSearchVo.setDisplayName(dicts.get(i).getDisplayName());
			statisticSearchVo.setPropertyDict(dicts.get(i));
			// List<StatisticSearchVo> temp = baseInfoStatisticsDao
			// .countHistoryDataByType(statisticSearchVo);
			// list.addAll(temp);
		}
		// 不需要按照类型统计 (直接统计人数 比如重点上访人员 没有分类)
		if (dicts.size() == 0) {
			statisticSearchVo.setDisplayName(statisticSearchVo
					.getTableDisplayName());
			// List<StatisticSearchVo> temp = baseInfoStatisticsDao
			// .countHistoryDataByType(statisticSearchVo);
			// list.addAll(temp);
		}
		// baseInfoStatisticsDao.addHistoryStatistic(list);
	}

	@Override
	public void addEconomicStat() {
		int year = CalendarUtil.getLastMonthYearValue();
		int month = CalendarUtil.getLastMonth();

		// PopulationCatalog newEconomicCatalog =
		// PopulationCatalog.populationCatalog(PopulationType.NEW_ECONOMIC);
		for (PopulationCatalog newEconomicCatalog : PopulationCatalog
				.getAllDoubleNewPopulationCatalog()) {
			StatisticSearchVo statisticSearchVo = createStatisticSearchVoByPopulationCatalog(newEconomicCatalog);

			createHistoryStatistic(year, month,
					newEconomicCatalog.getCatalog(), statisticSearchVo);
		}
	}

}
