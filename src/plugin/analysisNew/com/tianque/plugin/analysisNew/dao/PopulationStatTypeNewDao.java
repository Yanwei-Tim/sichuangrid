package com.tianque.plugin.analysisNew.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.plugin.analysisNew.domain.PopulationStatType;
import com.tianque.plugin.analysisNew.domain.StatisticSearchPopulationVo;

public interface PopulationStatTypeNewDao {

	public List<Map<String, Object>> findPopulationStatTypeForOrg(Long orgId,
			String populationType, int year, int month, String orgCode,
			Integer orgLevelDistance, Long orgType);

	public void addpopulationStatType(PopulationStatType populationType,
			String tableName, Date createDate, Date nextMonthStart,
			String orgCode);

	/**
	 * 获取每个表格根据组织机构的统计 tableName 表名
	 * 
	 * @return
	 */
	public int getCountByTableName(String tableName, String orgCode);

	/**
	 * 实时获取各个表的数据统计 tableName 表名称 orgId 当前组织机构id createDate 统计开始时间
	 * nextMonthStart 统计结束时间
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getCountByOrgInternalCodeAndTableNameAndMap(
			String tableName, Long orgId, String orgCode,
			Integer orgLevelDistance, Long orgType);

	/**
	 * 根据年月统计统计历史表中的数据供区域分布图使用
	 * 
	 * @param populationType
	 * @param orgId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<Map<String, Object>> getPopulationColumnByTime(Long orgId,
			String populationType, Integer year, Integer month, Long orgType);

	/**
	 * 趋势图统计
	 * 
	 * @param populationType
	 * @param orgCode
	 * @param time
	 * @return
	 */
	public int findTendencyChart(String populationType, Long orgId,
			int year, int month);

	public int findTendencyChart(String populationType, Long orgId,
			String time);

	public void deletePopulationStatType(String orgCode, int year, int month,
			String populationType);

	/**
	 * 实时类型图的total
	 * 
	 * */
	public int getHouseholdStaffCountByTypes(String orgInternalCode, Long id,
			String startDate, String endDate, String tableName, String type,
			Long logOut);

	/**
	 * 删除实有人员类型分布历史单表的记录
	 * */
	public void deleteHouseholdStaffHistoryStatistic(String orginternalcode,
			int year, int month, String type);

	/**
	 * 将数据插入到对应月份的实有人口的的类型分布表中
	 * */
	public void addHouseholdStaffHistoryStatistic(
			StatisticSearchPopulationVo statisticSearchPopulationVo);

	/**
	 * 实有人口类型分布图的大类的总数
	 * */
	public Long countByMapFromHistorySta(Map<String, Object> map);

	/**
	 * 删除青少年人员
	 * 
	 * @param year
	 * @param month
	 */
	public void deleteYouthStatType(int year, int month);

	/**
	 * 插入青少年人员分布表
	 * 
	 * @param populationStat
	 */
	public void addYouthStatType(PopulationStatType populationStat);
}
