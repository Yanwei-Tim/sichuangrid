package com.tianque.plugin.analyzing.dao;

import java.util.Map;

import com.tianque.plugin.analyzing.domain.StatisticSearchVo;

public interface ActualCompanyStatDao {

	/**
	 * 根据年月 ,组织机构编码删除存在的记录
	 * 
	 * @param orgInternalcode
	 * @param year
	 * @param month
	 */
	public void deleteCompanyStatType(String orgCode, int year, int month, String companyType);

	/**
	 * 根据statisticSearchVo查询某一月份的统计报表，并把结果插入的历史报表中
	 * 
	 * @param statisticSearchVo
	 */
	public void addHistoryStatistic(StatisticSearchVo statisticSearchVo);

	/**
	 * 更新历史记录表中的本月新增记录数，关注数，总人数
	 * 
	 * @param map
	 */
	public void updateMonthCreateAttentionNumAndTotal(Map<String, Object> map);
}
