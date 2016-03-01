package com.tianque.plugin.analyzing.service;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.youths.vo.SearchYouthsVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;

public interface LeaderViewService {

	/**
	 * 当前月统计(各个模块)
	 * 
	 * @param orgId
	 * @param tableName
	 * @return
	 */
	List<StatisticsBaseInfoAddCountVo> statisticsBaseInfoAddCountByOrgIdForArea(
			Long orgId, String tableName, boolean isGrid);

	public void statisticsPopulationAddCountByOrgIdForJob();

	public void statisticsHouseInfoAddCountByOrgIdForJob();

	public void statisticsAttentionPopulationAddCountByOrgIdForJob();

	/**
	 * 民政当前月统计(各个模块)
	 * 
	 * @param orgId
	 * @param tableName
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsBaseInfo(Long orgId,
			String tableName, boolean isGrid);

	List<StatisticsBaseInfoAddCountVo> statisticsBaseInfoSummary(Long orgId,
			String tableName);

	/**
	 * 总况当前月统计
	 * 
	 * @param orgId
	 * @param tableType
	 * @return
	 */
	List<StatisticsBaseInfoAddCountVo> personGeneralCondition(Long orgId,
			String tableType);

	List<StatisticsBaseInfoAddCountVo> personMonthGeneralCondition(Long orgId,
			String tableType);

	/**
	 * 实有人口总况的往月统计
	 * 
	 * @param orgId
	 * @param tableName
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> populationMonthGeneralCondition(
			Long orgId, String tableType);

	/**
	 * 民政总况的往月统计
	 * 
	 * @param orgId
	 * @param tableType
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> monthGeneralCondition(Long orgId,
			String tableType);

	/**
	 * 实有人口各个模块的往月统计
	 * 
	 * @param orgId
	 * @param tableName
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsPopulationSummary(
			Long orgId, String tableName);

	/**
	 * 民政各个模块的往月统计
	 * 
	 * @param orgId
	 * @param tableName
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsSummary(Long orgId,
			String tableName);

	/**
	 * 手机接口，统计实有人口和实有房屋信息
	 * 
	 * @param orgId
	 * @param tableType
	 * @return
	 */
	public Map personGeneralConditionForMobile(Long orgId, String tableType);

	/**
	 * 青少年总数统计
	 * 
	 * @param orgId
	 * @param tableName
	 * @param isGrid
	 * @return
	 */
	List<StatisticsBaseInfoAddCountVo> statisticsYouthsCount(
			SearchYouthsVo searchYouthsVo, boolean isGrid);

	/**
	 * 青少年按月统计
	 * 
	 * @param orgId
	 * @param tableName
	 * @param isGrid
	 * @return
	 */
	List<StatisticsBaseInfoAddCountVo> statisticsYouthsMonthSummary(Long orgId,
			String tableName, boolean isGrid);

	/**
	 * 青少年总数统计缓存job
	 */
	public void statisticsYouthsAddCountByOrgIdForJob();

	/**
	 * 单独为某一类型的人口总数统计缓存job
	 * 
	 * @param singleJobType
	 */
	public void statisticsPopulationSingleContentForJob(String singleJobType);

	/**
	 * 生成领导视图下面历史数据job每个月跑一次并且生成后的数据存入缓存表里面（县级以上的数据）
	 */
	public void createLeaderViewSummaryData();

	/**
	 * 单位场所领导视图历史月份
	 */
	public void createCompanyplaceLeaderViewSummaryData();

	/**
	 * 生成实口的领导视图历史月份
	 * 
	 * @param jobType
	 */
	public void createActualPopulationLeaderViewSummaryData();

	/**
	 * 生成业务人员的领导视图历史月份
	 * 
	 * @param jobType
	 */
	public void createBussinessPopulationLeaderViewSummaryData();

	/**
	 * 生成青少年的领导视图历史月份
	 * 
	 * @param jobType
	 */
	public void createYouthsLeaderViewSummaryData();

	/**
	 * 生成房屋的领导视图历史月份
	 * 
	 * @param jobType
	 */
	public void createHouseLeaderViewSummaryData();

	/**
	 * 手动调用生成领导视图历史月份数据
	 * 
	 * @param jobType
	 */
	public void createLeaderViewSummaryDataByType(String jobType);
}
