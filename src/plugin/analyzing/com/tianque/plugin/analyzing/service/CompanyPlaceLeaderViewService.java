package com.tianque.plugin.analyzing.service;

import java.util.List;

import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PopulationAreaDataVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;

/**
 * @Description:单位场所领导视图service接口
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-19 上午10:09:17
 */
public interface CompanyPlaceLeaderViewService {
	/**
	 * 领导视图 统计（本月）
	 * 
	 * @param orgId
	 * @param moduleKey
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsBaseInfo(Long orgId,
			String moduleKey);

	/**
	 * 领导视图 线形图（历史）
	 * 
	 * @param orgId
	 * @param moduleKey
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsSummary(Long orgId,
			String moduleKey);

	/**
	 * 单位场所领导视图当月数据统计job(每隔一段时间统计一次然后放到缓存，只针对县级以上)
	 */
	public void companyPlaceLeaderViewStatistics();

	/**
	 * 单位场所研判分析统计统计历史月份的数据
	 */
	public void companyPlaceAnalyzStatistics();

	/**
	 * 根据开始和结束时间统计单位场所当前月份的数据
	 * 
	 * @param monthStart
	 * @param nextMonthStart
	 */
	public void addCompanyPlaceAnalyzStatisticsByTime(Long orgId,
			String orgInternalCode, List<String> moduleKeys, Integer nowYear,
			Integer nowMonth);

	/**
	 * 根据orgId和类型查询单位场所的区域分布图（当月）
	 * 
	 * @param orgId
	 * @param moduleType
	 * @return
	 */
	public HighchartColumnVo getCompanyPlaceColumnByOrgIdAndType(Long orgId,
			String moduleType);

	/**
	 * 根据orgId、类型、年、月查询区域分布图（历史月份）
	 * 
	 * @param orgId
	 * @param moduleType
	 * @param year
	 * @param month
	 * @return
	 */
	public HighchartColumnVo getCompanyPlaceColumnByTime(Long orgId,
			String moduleType, int year, int month);

	/**
	 * 列表信息当月的数据的
	 * 
	 * @param orgId
	 * @param moduleType
	 * @param orgLevelDistance
	 * @return
	 */
	public List<PopulationAreaDataVo> getCurrentAreaDate(Long orgId,
			String moduleType, Integer orgLevelDistance);

	/**
	 * 列表信息历史月份的数据
	 * 
	 * @param orgId
	 * @param moduleType
	 * @param year
	 * @param month
	 * @param orgLevelDistance
	 * @return
	 */
	public List<PopulationAreaDataVo> getAreaDateByDate(Long orgId,
			String moduleType, int year, int month, Integer orgLevelDistance);

	/**
	 * 根据类别和orgId和时间统计出当前月的数据生成报表
	 * 
	 * @param year
	 * @param month
	 * @param orgId
	 * @param moduleType
	 */
	public void addStatisticsCompanyPlace(int year, int month, Long orgId,
			String moduleType);

	/**
	 * 趋势图（只查询历史月份的）
	 * 
	 * @param moduleType
	 * @param orgId
	 * @return
	 */
	public HighchartColumnVo findTendencyChart(String moduleType, Long orgId);

	/**
	 * 类型分布表
	 * 
	 * @param year
	 * @param month
	 * @param orgId
	 * @param moduleType
	 * @return
	 */
	public List<Object[]> getCompanyPlacePieInfo(Integer year, Integer month,
			Long orgId, String moduleType);

	public void companyPlaceLeaderViewStatisticsforAdd();
}
