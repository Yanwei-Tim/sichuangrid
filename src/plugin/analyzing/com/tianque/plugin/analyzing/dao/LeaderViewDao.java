package com.tianque.plugin.analyzing.dao;

import java.util.Date;
import java.util.List;

import com.tianque.baseInfo.youths.vo.SearchYouthsVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;

public interface LeaderViewDao {

	public StatisticsBaseInfoAddCountVo statisticsBaseInfoSummary(
			String orgInternalCode, String time, String tableName, int year,
			int month);

	/**
	 * 统计往月的数据(实有人员总况)
	 * 
	 * @param orgInternalCode
	 * @param time
	 * @param tableName
	 * @param year
	 * @param month
	 * @return
	 */
	public StatisticsBaseInfoAddCountVo statisticsPopulationSummary(
			String orgInternalCode, String time, String tableName, int year,
			int month);

	/**
	 * 统计往月的数据(民政总况)
	 * 
	 * @param orgInternalCode
	 * @param time
	 * @param tableName
	 * @param year
	 * @param month
	 * @return
	 */
	public StatisticsBaseInfoAddCountVo statisticsSummary(
			String orgInternalCode, String time, String tableTupe, int year,
			int month);

	/**
	 * 统计本月的数据(总况)
	 * 
	 * @param orgInternalCode
	 * @param today
	 * @param nextDay
	 * @param monthStart
	 * @param nextMonthStart
	 * @param tableName
	 * @param column
	 * @return
	 */
	public StatisticsBaseInfoAddCountVo personGeneralCondition(
			String orgInternalCode, Date today, Date nextDay, Date monthStart,
			Date nextMonthStart, String tableName, String column,
			String shardCode);

	/**
	 * 手机接口查询该组织下的今天和本月的新增信息
	 * 
	 * @param orgInternalCode
	 * @param today
	 * @param nextDay
	 * @param monthStart
	 * @param nextMonthStart
	 * @param tableName
	 * @return
	 */
	public StatisticsBaseInfoAddCountVo personGeneralConditionForMobile(
			String orgInternalCode, Date today, Date nextDay, Date monthStart,
			Date nextMonthStart, String tableName);

	public List<StatisticsBaseInfoAddCountVo> statisticsYouthsCount(
			SearchYouthsVo searchYouthsVo);

	public StatisticsBaseInfoAddCountVo statisticsYouthSummary(
			String orgInternalCode, String tableName, int year, int month);

	public List<StatisticsBaseInfoAddCountVo> statisticsBaseInfoAddCountByOrg(
			String tableName, String column, String orginternalcode,
			String shardCode);

	/***
	 * 根据条件查询单独月份数据
	 * 
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> getPopulationInfoByDate(
			Long orgId, int year, int month, String populationType, Long orgType);

}
