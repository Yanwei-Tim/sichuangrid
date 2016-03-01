package com.tianque.plugin.analyzing.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.StatisticSearchVo;
import com.tianque.plugin.analyzing.domain.TendencyChartRsNewVO;
import com.tianque.plugin.analyzing.domain.TendencyChartRsVo;
import com.tianque.plugin.analyzing.domain.TotalDatatSearchVo;
import com.tianque.service.util.PopulationCatalog;

public interface BaseInfoStatisticDao {

	// public List<Map<String, Object>> getRealTimeStatistic(
	// StatisticSearchVo statisticSearchVo);

	/**
	 * 根据StatisticSearchVo查询实时的统计记录，青少年
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getRealTimeIdleYouth(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getStatisticList(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getRealTimeStatisticNoType(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 根据年月 ,组织机构编码删除存在的记录
	 * 
	 * @param orgInternalcode
	 * @param year
	 * @param month
	 */
	public void deleteHistoryStatistic(String orgInternalcode, int year,
			int month, String type);

	/**
	 * 查询数据，用来组成趋势图需要的数据值（数据来源于统计报表历史表中）
	 * 
	 * @param typeName
	 * @param type
	 * @param timeStr
	 * @return
	 */
	public Integer countByMap(String typeName, String type, String timeStr,
			String orgInternalCode);

	/**
	 * 查询数据，用来组成趋势图需要的总值（数据来源于统计报表历史表中）
	 * 
	 * @param typeName
	 * @param type
	 * @param timeStr
	 * @return
	 */
	public Integer countTotalByMap(String typeName, String type,
			String timeStr, String orgInternalCode);

	/**
	 * 通过map查询历史报表中的数据
	 * 
	 * @param map
	 * @return
	 */
	public int countByMapFromHistorySta(Map<String, Object> map);

	public List<Map<String, Object>> getRealTimeCountByTypeName(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getIdleYouthRealTimeCountGroupByAge(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getIdleYouthRealTimeCountGroupByType(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getRealTimeCountByCountField(
			StatisticSearchVo statisticSearchVo);

	public Integer getTotalFromTableByOrgCode(String table, String orgCode);

	/**
	 * 获得区域分布图数据 ， 历史数据
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getArealDistributionListFromHistory(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getArealDistributionListFromHistoryNoType(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 总况的列表信息,实时数据
	 * 
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> getTotalStatisticList(
			List<StatisticSearchVo> list, Integer orgLevelDistance, Long orgType);

	/**
	 * 总况的列表信息，历史数据
	 * 
	 * @param list
	 * @return
	 */
	public List<Map<String, Object>> getTotalStatisticListFromHistory(
			List<StatisticSearchVo> statisticSearchVoList,
			Integer orgLevelDistance, Long orgType);

	/**
	 * 查询历史统计数据,从统计的历史表中查询
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getStatisticListFromHistory(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 查询历史统计数据,从统计的历史表中查询,重点青少年
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getIdleYouthStatisticListFromHistorty(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getStatisticListFromHistoryNoType(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 更新历史记录表中的本月新增记录数，关注数，总人数
	 * 
	 * @param map
	 */
	public void updateMonthCreateAttentionNumAndTotal(Map<String, Object> map);

	public List<Map<String, Object>> getTotalArealDistributionList(
			Map<String, Object> queryMap);

	/**
	 * 获得区域分布图数据从历史记录表中,有type 总况的情况
	 * 
	 * @param list
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> getTotalArealListFromHistory(
			Map<String, Object> queryMap);

	/**
	 * 在历史表中统计某一类人的总数量
	 * 
	 * @param type
	 * @param year
	 * @param month
	 * @param orgCode
	 * @return
	 */
	public Integer getOneTypeBaseinfoTotal(List<PopulationCatalog> list,
			String timeStr, String orgCode);

	/**
	 * 历史表中统计帮教情况
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getIsHelpFromHistory(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 实时表中统计帮教情况
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getIsHelpStatisticList(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 列实时表统计列表数据
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> countHelpedByField(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 列表实时统计青少年,年龄段
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> countIdleYouthHelpedByAge(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 列表实时统计青少年,类型
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> countIdleYouthHelpedByType(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> countHelpedByFieldNoType(
			StatisticSearchVo statisticSearchVo);

	/**
	 * 列实时表刑释解教
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> countPositiveinfosByField(
			StatisticSearchVo statisticSearchVo);

	public HighchartColumnVo getTotalArealDistributionList(
			List<PopulationCatalog> list, Long orgId, Long orgType);

	/**
	 * 取每月总数
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public Map getMonthTotal(StatisticSearchVo statisticSearchVo);

	/**
	 * 研判分析优化 重写查询 by zhoupengfei
	 */
	public List<TendencyChartRsVo> getTendencyChartSearchVo(String type,
			String orgCode, String[] time);

	public List<TendencyChartRsNewVO> getTendencyChartSearchVoNEW(String type,
			String orgCode);

	public void generateHistoryStatisticByType(int year, int month,
			Date startDate, Date endDate, String type, String tableName,
			String propertyField);

	public void generateIdleYouthHistoryStatisticByType(int year, int month,
			Date startDate, Date endDate, String type, String tableName);

	public void generateIdleYouthHistoryStatisticByAge(int year, int month,
			Date startDate, Date endDate, String type, String tableName);

	public void generatePositiveInfoHistoryStatistic(int year, int month,
			Date startDate, Date endDate, String type, String tableName,
			String propertyField);

	public void generateActualHouseHistoryStatistic(int year, int month,
			Date startDate, Date endDate, String type, String tableName,
			String shardCode);

	public void generateHistoryStatisticWithoutPropertyDict(int year,
			int month, Date startDate, Date endDate, String type,
			String tableName, String displayName, String shardCode);

	public int[] getAllTypeBaseinfoTotal(String[] times, String typeNmaes,
			String orginternalcode);

	public List<TendencyChartRsNewVO> getTendencyChartRsNewVOs(
			List<TotalDatatSearchVo> searchVoList);

	// 特殊处理用于青少年的领导视图
	public void generateIdleYouthByAgeAndLeaderCount(int year, int month,
			Date startDate, Date endDate, String type, String tableName);

	/**
	 * 根据StatisticSearchVo在户籍人口、流动人口、未落户人口中查询青少年
	 */
	public List<Map<String, Object>> getRealTimeYouth(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getYouthRealTimeCountGroupByAge(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> countYouthByType(
			StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> countYouthByAge(
			StatisticSearchVo statisticSearchVo);

	public Integer getTotalYouthByOrgCode(StatisticSearchVo statisticSearchVo);

	public List<Map<String, Object>> getYouthRealTimeCountGroupByType(
			StatisticSearchVo statisticSearchVo);

	public List<StatisticSearchVo> countHistoryDataByType(
			StatisticSearchVo statisticSearchVo);

	public void addHistoryStatistic(List<StatisticSearchVo> list);

	public Map<String, Object> getYouthMonthCreateAttentionNumAndTotal(
			StatisticSearchVo statisticSearchVo);
}
