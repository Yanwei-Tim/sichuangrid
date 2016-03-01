package com.tianque.xichang.working.statisticsAccountTimeout.dao;

import java.util.List;

import com.tianque.xichang.working.statisticsAccountTimeout.domain.StatisticsAccountTimeout;

/**
 * 时限考核成绩dao接口
 * */
public interface StatisticsAccountTimeoutDao {
	/**
	 * @param statisticsAccountTimeout
	 *            插入 statisticsAccountTimeout
	 * */
	public void addStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout);

	/**
	 * 修改时限成绩
	 * 
	 * @param statisticsAccountTimeout
	 */
	public void updateStatisticsAccountTimeoutByStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout);

	/**
	 * 根据组织机构id 年份月份查找
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @return
	 */
	public StatisticsAccountTimeout getStatisticsAccountTimeoutByOrgIdAndMonthAndYear(
			Long orgId, Integer year, Integer month);

	/**
	 * @param parentOrgId
	 * @param orgType
	 *            (组织类型) 根据，年月日时间父id组织类型查找statisticsAccountTimeout
	 * */
	public List<StatisticsAccountTimeout> findStatisticsAccountTimeoutByParentOrgIdAndTime(
			Long parentOrgId, Long orgType, Integer year, Integer month,
			String sortField, String sord);

	public Integer countDirtyDataByModel(List<Long> orgIdList, int year,
			int month);

	public void deleteDirtyDataByModel(List<Long> orgIdList, int year, int month);

	public void batchAddDate(
			List<StatisticsAccountTimeout> statisticsAccountTimeouts);

}
