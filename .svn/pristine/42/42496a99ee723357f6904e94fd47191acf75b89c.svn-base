package com.tianque.plugin.analyzing.dao;

import java.util.List;
import java.util.Map;

public interface StatisticsBaseInfoDao {
	public Integer getCountByOrgInternalCodeAndTableNameAndMap(
			String orgInternalCode, String tableName, Map<String, Object> map);

	/**
	 * 统计区域分布图，历史数据
	 * 
	 * @param orgInternalCode
	 * @param tableName
	 * @param map
	 * @return
	 */
	public int getCountFromHistory(Map<String, Object> map);

	/**
	 * 统计区域分布图，历史数据，帮扶情况
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getHelpedCountFromHistory(
			Long orgInternalCode, String baseinfotype, String tableName,
			int year, int month);

	/**
	 * 统计已帮教情况，历史数据
	 * 
	 * @param orgId
	 * @param baseinfotype
	 * @param tableName
	 * @param year
	 * @param month
	 * @return
	 */
	public int getIsHelpFromHistory(String orgInternalCode, String typeName,
			String baseinfotype, int year, int month);

	/**
	 * 统计未帮教情况，历史数据
	 * 
	 * @param orgId
	 * @param baseinfotype
	 * @param tableName
	 * @param year
	 * @param month
	 * @return
	 */
	public int getNoHelpFromHistory(String orgInternalCode, String typeName,
			String baseinfotype, int year, int month);
}
