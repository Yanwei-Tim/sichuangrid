package com.tianque.plugin.analysisNew.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.HighchartDataColumnVo;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;
import com.tianque.plugin.analysisNew.domain.TendencyChartRsNewVO;
import com.tianque.plugin.analysisNew.domain.TendencyChartRsVo;
import com.tianque.plugin.analysisNew.domain.TendencyChartTotalVo;
import com.tianque.plugin.analysisNew.domain.TotalDatatSearchVo;
import com.tianque.plugin.analysisNew.util.PopulationCatalog;

@Repository("baseInfoStatisticsNewDao")
public class BaseInfoStatisticsNewDaoImpl extends AbstractBaseDao implements
		BaseInfoStatisticNewDao {

	public List<Map<String, Object>> getRealTimeIdleYouth(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getRealTimeIdleYouthStatisticList",
				getIdleYouthMap(statisticSearchVo));
	}

	public List<Map<String, Object>> getStatisticList(
			StatisticSearchVo statisticSearchVo) {
		// 添加此处判断原因：出租房添加管理负责人时，传递参数为：RENTALHOUSE；而在研判分析读取出租房区域分布图柱形图的时候，传递参数是rentalHouse
		// 因为在servicememberhasobject关联关系表中，出租房全部存的是大写RENTALHOUSE，所以在这里修改参数合理。
		if ("rentalHouse".equals(statisticSearchVo.getType())) {
			statisticSearchVo.setType("RENTALHOUSE");
		}

		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getStatisticList", statisticSearchVo);
	}

	public List<Map<String, Object>> getRealTimeStatisticNoType(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getRealTimeStatisticNoType",
				statisticSearchVo);
	}

	private Map<String, Object> getIdleYouthMap(
			StatisticSearchVo statisticSearchVo) {
		Map<String, Object> map = new HashMap<String, Object>();

		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		nowCalendar.add(Calendar.YEAR, -6);
		map.put("year1", nowCalendar.getTime());
		nowCalendar.add(Calendar.YEAR, -12);
		map.put("year2", nowCalendar.getTime());
		nowCalendar.add(Calendar.YEAR, -7);
		map.put("year3", nowCalendar.getTime());
		map.put("orgId", statisticSearchVo.getOrgId());
		map.put("year", statisticSearchVo.getYear());
		map.put("month", statisticSearchVo.getMonth());
		map.put("type", statisticSearchVo.getType());
		map.put("table", statisticSearchVo.getTable());
		map.put("displayName", statisticSearchVo.getDisplayName());
		map.put("createUser", statisticSearchVo.getCreateUser());
		map.put("createDate", statisticSearchVo.getCreateDate());
		map.put("startDate", statisticSearchVo.getStartDate());
		map.put("endDate", statisticSearchVo.getEndDate());
		map.put("organizationLevel", statisticSearchVo.getOrganizationLevel());
		map.put("orginternalcode", statisticSearchVo.getOrginternalcode());
		map.put("organizationType", statisticSearchVo.getOrganizationType());
		map.put("isemphasis", statisticSearchVo.getIsemphasis());
		map.put("orgType", statisticSearchVo.getOrgType());
		map.put("propertyDomainId", statisticSearchVo.getPropertyDomainId());
		return map;
	}

	public List<Map<String, Object>> getStatisticListFromHistory(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getStatisticListFromHistory",
				statisticSearchVo);
	}

	/**
	 * 暂无Service调用该方法
	 */
	public List<Map<String, Object>> getIdleYouthStatisticListFromHistorty(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getIdleYouthStatisticListFromHistorty",
				statisticSearchVo);
	}

	public List<Map<String, Object>> getStatisticListFromHistoryNoType(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getStatisticListFromHistoryNoType",
				statisticSearchVo);
	}

	@Override
	public void deleteHistoryStatistic(String orgInternalcode, int year,
			int month, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalcode", orgInternalcode);
		map.put("year", year);
		map.put("month", month);
		map.put("type", type);
		getSqlMapClientTemplate().delete(
				"baseInfoStatisticNew.deleteStatisticByMap", map);
	}

	@Override
	public Integer countByMap(String typeName, String type, String timeStr,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeName", typeName);
		map.put("type", type);
		// 获取年月
		String time[] = timeStr.split("-");
		map.put("year", time[0]);
		map.put("month", time[1].startsWith("0") ? time[1].substring(1)
				: time[1]);
		map.put("orgId", orgId);
		Integer sum = (Integer) (getSqlMapClientTemplate().queryForObject(
				"baseInfoStatisticNew.getCountByMonth", map));
		return sum == null ? 0 : sum;
	}

	public List<Map<String, Object>> getIdleYouthRealTimeCountGroupByAge(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getIdleYouthRealTimeCountGroupByAge",
				getIdleYouthMap(statisticSearchVo));
	}

	public List<Map<String, Object>> getIdleYouthRealTimeCountGroupByType(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getIdleYouthRealTimeCountGroupByType",
				statisticSearchVo);
	}

	public List<Map<String, Object>> getRealTimeCountByCountField(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getRealTimeCountByCountField",
				statisticSearchVo);
	}

	public List<Map<String, Object>> getRealTimeCountByTypeName(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getRealTimeCountByTypeName",
				statisticSearchVo);
	}

	public Integer getTotalFromTableByOrgCode(String table, String orgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", table);
		map.put("orginternalcode", orgCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatisticNew.countAll", map);
	}

	@Override
	public int countByMapFromHistorySta(Map<String, Object> map) {
		Object result = getSqlMapClientTemplate().queryForObject(
				"baseInfoStatisticNew.getCountByMonth", map);
		return (Integer) (result == null ? 0 : result);
	}

	@Override
	public List<Map<String, Object>> getTotalStatisticList(
			List<StatisticSearchVo> baseinfoStatisticSearchList,
			Integer orgLevelDistance, Long orgType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseinfoStatisticSearchList", baseinfoStatisticSearchList);
		map.put("orgLevelDistance", orgLevelDistance);
		map.put("orgType", orgType);
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getTotalStatisticList", map);
	}

	@Override
	public List<Map<String, Object>> getTotalStatisticListFromHistory(
			List<StatisticSearchVo> statisticSearchVoList,
			Integer orgLevelDistance, Long orgType,String typeName) {
		StatisticSearchVo vo = statisticSearchVoList.get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseinfoStatisticSearchList", statisticSearchVoList);
		map.put("year", vo.getYear());
		map.put("month", vo.getMonth());
		map.put("type", vo.getType());
		map.put("orgId", vo.getOrgId());
		map.put("organizationType", vo.getOrganizationType());
		map.put("orgLevelDistance", orgLevelDistance);
		map.put("orginternalcode", vo.getOrginternalcode());
		map.put("orgType", orgType);
		map.put("typeName", typeName);
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getTotalStatisticListFromHistory", map);
	}

	@Override
	public void updateMonthCreateAttentionNumAndTotal(Map<String, Object> map) {
		getSqlMapClientTemplate().update(
				"baseInfoStatisticNew.updateMonthCreateAttentionNumAndTotal",
				map);
	}

	@Override
	public Integer getOneTypeBaseinfoTotal(List<PopulationCatalog> list,
			String timeStr, Long orgId,String typeName) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<String> tables = new ArrayList<String>();
		for (PopulationCatalog catalog : list) {
			tables.add(catalog.getCatalog());
		}

		queryMap.put("tables", tables);
		String[] date = timeStr.split("-");
		queryMap.put("year", date[0]);
		queryMap.put("month", date[1].startsWith("0") ? date[1].substring(1)
				: date[1]);
		queryMap.put("orgId", orgId);
		queryMap.put("typeNames", typeName);

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatisticNew.getOneTypeBaseinofTotal", queryMap);

	}

	@Override
	public int[] getAllTypeBaseinfoTotal(String[] times, String typeNames,
			String orginternalcode) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("time1", times[0]);
		queryMap.put("time2", times[1]);
		queryMap.put("time3", times[2]);
		queryMap.put("time4", times[3]);
		queryMap.put("time5", times[4]);
		queryMap.put("time6", times[5]);
		queryMap.put("time7", times[6]);
		queryMap.put("time8", times[7]);
		queryMap.put("time9", times[8]);
		queryMap.put("time10", times[9]);
		queryMap.put("time11", times[10]);
		queryMap.put("time12", times[11]);

		queryMap.put("typeNames", typeNames);
		queryMap.put("orginternalcode", orginternalcode);

		TendencyChartTotalVo result = (TendencyChartTotalVo) getSqlMapClientTemplate()
				.queryForObject("baseInfoStatisticNew.getAllTypeBaseinofTotal",
						queryMap);

		int[] data = new int[12];
		Class clazz = TendencyChartTotalVo.class;
		try {
			for (int i = 0; i < data.length; i++) {
				Field f = clazz.getDeclaredField("month" + (i + 1));
				f.setAccessible(true);
				Integer num = (Integer) f.get(result);
				data[i] = num.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

	@Override
	public List<TendencyChartRsNewVO> getTendencyChartRsNewVOs(
			List<TotalDatatSearchVo> searchVoList) {
		Map map = new HashMap();
		map.put("list", searchVoList);
		return (List<TendencyChartRsNewVO>) this.getSqlMapClientTemplate()
				.queryForList("baseInfoStatisticNew.getTendencyChartSearchVos",
						map);

	}

	public List<Map<String, Object>> getTotalArealDistributionList(
			Map<String, Object> queryMap) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getTotalArealDistributionList", queryMap);
	}

	public List<Map<String, Object>> getTotalArealListFromHistory(
			Map<String, Object> queryMap) {
		return getSqlMapClientTemplate()
				.queryForList(
						"baseInfoStatisticNew.getTotalArealDistributionListFromHistory",
						queryMap);

	}

	public List<Map<String, Object>> getArealDistributionListFromHistory(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getArealDistributionListFromHistory",
				statisticSearchVo);

	}

	public List<Map<String, Object>> getArealDistributionListFromHistoryNoType(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate()
				.queryForList(
						"baseInfoStatisticNew.getArealDistributionListFromHistoryNoType",
						statisticSearchVo);

	}

	private Map<String, Object> searchIdleYouthMap(
			StatisticSearchVo statisticSearchVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		Date year0 = nowCalendar.getTime();
		nowCalendar.add(Calendar.YEAR, -6);
		Date year1 = nowCalendar.getTime();
		nowCalendar.add(Calendar.YEAR, -12);
		Date year2 = nowCalendar.getTime();
		nowCalendar.add(Calendar.YEAR, -7);
		Date year3 = nowCalendar.getTime();
		nowCalendar.add(Calendar.YEAR, -10);
		Date year4 = nowCalendar.getTime();
		String displayName = statisticSearchVo.getPropertyDict()
				.getDisplayName();
		if (displayName.startsWith("0~")) {
			map.put("year1", year0);
			map.put("year2", year1);
		} else if (displayName.startsWith("6~")) {
			map.put("year1", year1);
			map.put("year2", year2);
		} else if (displayName.startsWith("18~")) {
			map.put("year1", year2);
			map.put("year2", year3);
		} else if (displayName.startsWith("25~")) {
			map.put("year1", year3);
			map.put("year2", year4);
		}
		map.put("orginternalcode", statisticSearchVo.getOrginternalcode());
		map.put("statisticSearchVo", statisticSearchVo);
		map.put("isemphasis", statisticSearchVo.getIsemphasis());
		return map;
	}

	@Override
	public List<Map<String, Object>> getIsHelpFromHistory(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getIsHelpFromHistory", statisticSearchVo);
	}

	public List<Map<String, Object>> getIsHelpStatisticList(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getIsHelpStatisticList",
				statisticSearchVo);
	}

	@Override
	public List<Map<String, Object>> countHelpedByField(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.countHelpedByField", statisticSearchVo);
	}

	@Override
	public List<Map<String, Object>> countIdleYouthHelpedByAge(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.countIdleYouthHelpedByAge",
				searchIdleYouthMap(statisticSearchVo));
	}

	@Override
	public List<Map<String, Object>> countIdleYouthHelpedByType(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.countIdleYouthHelpedByType",
				statisticSearchVo);
	}

	@Override
	public List<Map<String, Object>> countHelpedByFieldNoType(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.countHelpedByFieldNoType",
				statisticSearchVo);
	}

	@Override
	public List<Map<String, Object>> countPositiveinfosByField(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.countPositiveinfosByField",
				statisticSearchVo);
	}

	/**
	 * 总况的区域分布图
	 */
	@Override
	public HighchartColumnVo getTotalArealDistributionList(
			List<PopulationCatalog> list, Long orgId, Long orgType) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		String type = null;
		List<String> tables = new ArrayList<String>();
		for (PopulationCatalog catalog : list) {
			type = catalog.getParentCatalog();
			tables.add(catalog.getStatisticListSetting().getTableName());
		}
		queryMap.put("orgId", orgId);
		queryMap.put("tables", tables);
		queryMap.put("orgType", orgType);
		List<Map<String, Object>> resultList = getSqlMapClientTemplate()
				.queryForList(
						"baseInfoStatisticNew.getTotalArealDistributionList",
						queryMap);
		StatisticSearchVo vo = new StatisticSearchVo();
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		vo.setTableDisplayName(catalog.getDisplayName());
		return createHighchartColumnVoByListNoTypeName(resultList, vo);
	}

	private HighchartColumnVo createHighchartColumnVoByListNoTypeName(
			List<Map<String, Object>> list, StatisticSearchVo statisticSearchVo) {
		if (list == null || list.size() == 0) {
			return new HighchartColumnVo();
		}
		HighchartColumnVo chartColumn = new HighchartColumnVo();
		List<HighchartDataColumnVo> series = new ArrayList<HighchartDataColumnVo>(); // 各类型对应的数据
		HighchartDataColumnVo column = new HighchartDataColumnVo();
		chartColumn.setModuleName(statisticSearchVo.getTableDisplayName());
		List<String> categoriesList = new ArrayList<String>();
		List<Integer> values = new ArrayList<Integer>();
		int len = list.size();

		for (int i = 0; i < len; i++) {
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = Integer.parseInt(((BigDecimal) list.get(i).get(
					"SUMNUM")).toString());
			categoriesList.add(orgName);
			values.add(sum);
		}
		String[] categories = new String[categoriesList.size()];
		categoriesList.toArray(categories);
		chartColumn.setCategories(categories);
		column.setName(statisticSearchVo.getTableDisplayName());
		column.setStack(statisticSearchVo.getTableDisplayName());
		Integer[] data = new Integer[values.size()];
		values.toArray(data);
		column.setData(getIntByInteger(data));
		series.add(column);
		chartColumn.setSeries(series);
		return chartColumn;
	}

	private int[] getIntByInteger(Integer[] integers) {
		int len = integers.length;
		int[] results = new int[len];
		for (int i = 0; i < len; i++) {
			results[i] = integers[i];
		}
		return results;
	}

	@Override
	public Integer countTotalByMap(String typeName, String type,
			String timeStr, Long orgId,String typeNames) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeName", typeName);
		map.put("type", type);
		// 获取年月
		String time[] = timeStr.split("-");
		map.put("year", time[0]);
		map.put("month", time[1].startsWith("0") ? time[1].substring(1)
				: time[1]);
		map.put("orgId", orgId);
		map.put("typeNames", typeNames);
		Integer attentionSum = (Integer) (getSqlMapClientTemplate()
				.queryForObject("baseInfoStatisticNew.countTotalByMap", map));
		return attentionSum == null ? 0 : attentionSum;
	}

	@Override
	public Map getMonthTotal(StatisticSearchVo statisticSearchVo) {
		return (Map) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatisticNew.getMonthTotal", statisticSearchVo);
	}

	@Override
	public List<TendencyChartRsVo> getTendencyChartSearchVo(String type,
			String orgCode, String[] time) {
		Map map = new HashMap();
		map.put("type", type);
		map.put("orgCode", orgCode);
		map.put("time", time);
		return (List<TendencyChartRsVo>) getSqlMapClientTemplate()
				.queryForList("baseInfoStatisticNew.getTendencyChartSearchVo",
						map);
	}

	@Override
	public List<TendencyChartRsNewVO> getTendencyChartSearchVoNEW(String type,
			String orgCode) {
		Map map = new HashMap();
		map.put("type", type);
		map.put("orgCode", orgCode);
		return (List<TendencyChartRsNewVO>) getSqlMapClientTemplate()
				.queryForList(
						"baseInfoStatisticNew.getTendencyChartSearchVoNEW", map);
	}

	@Override
	public void generateHistoryStatisticByType(int year, int month,
			Date startDate, Date endDate, String type, String tableName,
			String propertyField) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		map.put("tableName", tableName);
		map.put("propertyField", propertyField);
		map.put("nowDate", CalendarUtil.today());
		getSqlMapClientTemplate().insert(
				"baseInfoStatisticNew.generateHistoryStatisticByType", map);
	}

	@Override
	public void generateIdleYouthHistoryStatisticByType(int year, int month,
			Date startDate, Date endDate, String type, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		map.put("tableName", tableName);
		map.put("nowDate", CalendarUtil.today());
		getSqlMapClientTemplate().insert(
				"baseInfoStatisticNew.generateIdleYouthHistoryStatisticByType",
				map);
	}

	@Override
	public void generateIdleYouthHistoryStatisticByAge(int year, int month,
			Date startDate, Date endDate, String type, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		map.put("tableName", tableName);
		map.put("nowDate", CalendarUtil.today());
		getSqlMapClientTemplate().insert(
				"baseInfoStatisticNew.generateIdleYouthHistoryStatisticByAge",
				map);
	}

	@Override
	public void generateIdleYouthByAgeAndLeaderCount(int year, int month,
			Date startDate, Date endDate, String type, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		map.put("tableName", tableName);
		map.put("nowDate", CalendarUtil.today());
		getSqlMapClientTemplate().insert(
				"baseInfoStatisticNew.generateIdleYouthByAgeAndLeaderCount",
				map);
	}

	@Override
	public void generatePositiveInfoHistoryStatistic(int year, int month,
			Date startDate, Date endDate, String type, String tableName,
			String propertyField) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		map.put("tableName", tableName);
		map.put("nowDate", CalendarUtil.today());
		map.put("propertyField", propertyField);
		getSqlMapClientTemplate().insert(
				"baseInfoStatisticNew.generatePositiveInfoHistoryStatistic",
				map);

	}

	@Override
	public void generateActualHouseHistoryStatistic(int year, int month,
			Date startDate, Date endDate, String type, String tableName,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		map.put("tableName", tableName);
		map.put("nowDate", CalendarUtil.today());
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate()
				.insert("baseInfoStatisticNew.generateActualHouseHistoryStatistic",
						map);
	}

	@Override
	public void generateHistoryStatisticWithoutPropertyDict(int year,
			int month, Date startDate, Date endDate, String type,
			String tableName, String displayName, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		map.put("tableName", tableName);
		map.put("displayName", displayName);
		map.put("nowDate", CalendarUtil.today());
		if (shardCode != null) {
			map.put("tableName", tableName + "_" + shardCode);
		} else {
			map.put("tableName", tableName);
		}
		getSqlMapClientTemplate()
				.insert("baseInfoStatisticNew.generateHistoryStatisticWithoutPropertyDict",
						map);
	}

	public List<Map<String, Object>> getRealTimeYouth(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getRealTimeYouthStatisticList",
				getYouthMap(statisticSearchVo));
	}

	public List<Map<String, Object>> getYouthRealTimeCountGroupByAge(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getYouthRealTimeCountGroupByAge",
				getYouthMap(statisticSearchVo));
	}

	private Map<String, Object> getYouthMap(StatisticSearchVo statisticSearchVo) {
		Map<String, Object> map = new HashMap<String, Object>();

		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		nowCalendar.add(Calendar.YEAR, -6);
		map.put("year1", nowCalendar.getTime());
		nowCalendar.add(Calendar.YEAR, -8);
		map.put("year2", nowCalendar.getTime());
		nowCalendar.add(Calendar.YEAR, -4);
		map.put("year3", nowCalendar.getTime());
		nowCalendar.add(Calendar.YEAR, -10);
		map.put("year4", nowCalendar.getTime());
		nowCalendar.add(Calendar.YEAR, -7);
		map.put("year5", nowCalendar.getTime());
		if (null != statisticSearchVo.getDisplayName())
			if (statisticSearchVo.getDisplayName().startsWith("0~")) {
				map.put("beginDate", map.get("year1"));
				map.put("toDate", nowDate);
			} else if (statisticSearchVo.getDisplayName().startsWith("6~")) {
				map.put("beginDate", map.get("year2"));
				map.put("toDate", map.get("year1"));
			} else if (statisticSearchVo.getDisplayName().startsWith("14~")) {
				map.put("beginDate", map.get("year3"));
				map.put("toDate", map.get("year2"));
			} else if (statisticSearchVo.getDisplayName().startsWith("18~")) {
				map.put("beginDate", map.get("year4"));
				map.put("toDate", map.get("year3"));
			} else if (statisticSearchVo.getDisplayName().startsWith("28~")) {
				map.put("beginDate", map.get("year5"));
				map.put("toDate", map.get("year4"));
			}
		if (null != statisticSearchVo && null != statisticSearchVo.getTable()) {
			String[] tableName = statisticSearchVo.getTable().split(",");
			fillTableNameToYouthSelectMap(tableName, map);
		}
		if (null != statisticSearchVo
				&& null != statisticSearchVo.getPropertyDict()) {
			map.put("propertyDictDisplayName", statisticSearchVo
					.getPropertyDict().getDisplayName());
			map.put("propertyDictDisplaySeq", statisticSearchVo
					.getPropertyDict().getDisplaySeq());
			map.put("propertyDictId", statisticSearchVo.getPropertyDict()
					.getId());
		}
		map.put("orgId", statisticSearchVo.getOrgId());
		map.put("year", statisticSearchVo.getYear());
		map.put("month", statisticSearchVo.getMonth());
		map.put("type", statisticSearchVo.getType());
		map.put("displayName", statisticSearchVo.getDisplayName());
		map.put("createUser", statisticSearchVo.getCreateUser());
		map.put("createDate", statisticSearchVo.getCreateDate());
		map.put("startDate", statisticSearchVo.getStartDate());
		map.put("endDate", statisticSearchVo.getEndDate());
		map.put("organizationLevel", statisticSearchVo.getOrganizationLevel());
		map.put("orginternalcode", statisticSearchVo.getOrginternalcode());
		map.put("organizationType", statisticSearchVo.getOrganizationType());
		map.put("isemphasis", statisticSearchVo.getIsemphasis());
		// 生成历史数据的时候要用
		map.put("monthCreate", statisticSearchVo.getMonthCreate());
		map.put("attentionNum", statisticSearchVo.getAttentionNum());
		map.put("total", statisticSearchVo.getTotal());
		map.put("startDate", statisticSearchVo.getStartDate());
		map.put("endDate", statisticSearchVo.getEndDate());
		return map;
	}

	private void fillTableNameToYouthSelectMap(String[] tableNames,
			Map<String, Object> map) {
		if (tableNames == null || tableNames.length < 1) {
			return;
		}
		for (String tableName : tableNames) {
			if (BaseInfoTables.personnelTables.get(
					BaseInfoTables.UNSETTEDPOPULATION_KEY).equalsIgnoreCase(
					tableName)) {
				map.put("table1", tableName);
			} else if (tableName.startsWith(BaseInfoTables.personnelTables
					.get(BaseInfoTables.HOUSEHOLDSTAFF_KEY))) {
				map.put("table2", tableName);
			} else if (BaseInfoTables.personnelTables.get(
					BaseInfoTables.FLOATINGPOPULATION_KEY).equalsIgnoreCase(
					tableName)) {
				map.put("table3", tableName);
			} else {
				map.put("table4", tableName);
			}
		}
	}

	@Override
	public List<Map<String, Object>> countYouthByAge(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.countYouthByAge",
				getYouthMap(statisticSearchVo));
	}

	@Override
	public List<Map<String, Object>> countYouthByType(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.countYouthByType",
				getYouthMap(statisticSearchVo));
	}

	public Integer getTotalYouthByOrgCode(StatisticSearchVo statisticSearchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatisticNew.countAllYouth",
				getYouthMap(statisticSearchVo));
	}

	public List<Map<String, Object>> getYouthRealTimeCountGroupByType(
			StatisticSearchVo statisticSearchVo) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfoStatisticNew.getYouthRealTimeCountGroupByType",
				getYouthMap(statisticSearchVo));
	}

	public List<StatisticSearchVo> countHistoryDataByType(
			StatisticSearchVo statisticSearchVo) {

		if (PropertyTypes.POLITICAL_BACKGROUND.equals(statisticSearchVo
				.getDomainName())) {
			return getSqlMapClientTemplate().queryForList(
					"baseInfoStatisticNew.countYouthHistoryDataByType",
					getYouthMap(statisticSearchVo));
		} else {
			return getSqlMapClientTemplate().queryForList(
					"baseInfoStatisticNew.countYouthHistoryDataByAge",
					getYouthMap(statisticSearchVo));
		}

	}

	public void addHistoryStatistic(List<StatisticSearchVo> list) {
		batchInsertDate(list, "baseInfoStatisticNew.addhistory");
	}

	@Override
	public Map<String, Object> getYouthMonthCreateAttentionNumAndTotal(
			StatisticSearchVo statisticSearchVo) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatisticNew.getYouthMonthCreateAttentionNumAndTotal",
				getYouthMap(statisticSearchVo));
	}

}
