package com.tianque.xichang.working.statisticsAccountTimeout.service;

import java.util.List;

import com.tianque.core.vo.GridPage;
import com.tianque.xichang.working.statisticsAccountTimeout.domain.StatisticsAccountTimeout;

/**
 * 时限考核成绩业务接口
 * */
public interface StatisticsAccountTimeoutService {

	/**
	 * @param year
	 * @param month
	 * @param parentOrgId
	 * @param internalId
	 *            根据年，月，父组织id，组织类型 查询
	 * @param sord
	 * @param sidx
	 * */
	public GridPage<StatisticsAccountTimeout> findStatisticsAccountTimeoutByParentOrgIdAndTime(
			Long parentOrgId, int internalId, Integer year, Integer month,
			String sortField, String sord);

	/**
	 * job对StatisticsAccountTimeouts表添加统计数据
	 * */
	public void addStatisticsAccountTimeoutData();

	/**
	 * 将统计好的成绩插入
	 * 
	 * @param statisticsAccountTimeout
	 */
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
	 * 将统计后的时限成绩进行操作，是新增还是修改
	 * 
	 * @param statisticsAccountTimeout
	 */
	public void processReportStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout);

	/**
	 * 统计是否有脏数据
	 * 
	 * @param idModList
	 * @param fetchNum
	 * @param taskParameter
	 * @param year
	 * @param month
	 * @return
	 */
	public Integer countDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month);

	/**
	 * 刪除脏数据
	 * 
	 * @param idModList
	 * @param fetchNum
	 * @param taskParameter
	 * @param year
	 * @param month
	 */
	public void deleteDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month);

	/**
	 * 批量新增
	 * 
	 * @param statisticsAccountTimeouts
	 */
	public void batchAddDate(
			List<StatisticsAccountTimeout> statisticsAccountTimeouts);

}
