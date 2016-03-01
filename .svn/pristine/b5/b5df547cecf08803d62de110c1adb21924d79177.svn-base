package com.tianque.plugin.analyzing.dao;

import java.util.List;
import java.util.Map;

public interface StatAnalysePlaceDao {

	/**
	 * 类型分布图，实时获取
	 * 
	 * @param tableName
	 * @param orgInternalCode
	 * @param keyType
	 * @return
	 */
	public int getStatAnalysePlace(String tableName, String orgInternalCode,
			String keyType);

	/**
	 * 统计已帮教的情况 ， 实时获取
	 * 
	 * @param tableName
	 * @param orgInternalCode
	 * @param keyType
	 * @return
	 */
	public int getIsHelpStatAnalysePlace(String tableName,
			String orgInternalCode, String keyType);

	/**
	 * 实时表中统计帮教情况
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getHelpedStatAnalysePlace(
			String tableName, long orgId, String keyType, Long orgType);

	/**
	 * 删除某年、某月、某一类型的数据
	 * 
	 * @param year
	 * @param month
	 * @param typeName
	 */
	public void deleteByYearAndMonthAndType(int year, int month, String typeName);

}
