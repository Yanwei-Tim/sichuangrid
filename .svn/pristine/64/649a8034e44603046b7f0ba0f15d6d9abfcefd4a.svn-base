package com.tianque.working.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.DailyYear;

public interface DailyYearDao {

	public DailyYear addDailyYear(DailyYear dailyYear);

	public DailyYear getSimpleDailyYearById(Long id);

	public PageInfo<DailyYear> findDailyYearForPageByOrgId(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public List<DailyYear> findDailyYearList();

	public List<DailyYear> findDailyYearByName(String name);

	public List<DailyYear> findDailyYearByOrgId(Long orgId);

	/**
	 * 查询启用的年度台帐目录
	 * 
	 * @param orgId
	 * @param status
	 * @return
	 */
	public List<DailyYear> findDailyYearsByOrgIdAndStatus(Long orgId, Long status);

	public DailyYear getInitYear();

	public DailyYear findDailyYearByParentOrgIdAndYear(Long orgId, String name);

	/**
	 * 根据年度台帐目录orgId、年度 得到年度台帐目录
	 * 
	 * @param orgId
	 * @param year
	 * @return
	 */
	public int countDailyYearByOrgIdAndYear(Long orgId, Integer year);

	public void deleteDailyYear(Long yearId);

	/**
	 * 更新年度台帐目录
	 * 
	 * @param templateId
	 * @param dailyYear
	 */
	public DailyYear updateDailyYear(DailyYear dailyYear);

	/**
	 * 年度台帐目录 启用、停用状态更改
	 * 
	 * @param templateId
	 * @param dailyYear
	 */
	public DailyYear startAndStopDailyYearById(DailyYear dailyYear);

	/**
	 * 根据网格Id、年份 得到年度台帐目录
	 * 
	 * @param currentOrgId
	 * @param year
	 * @return
	 */
	public DailyYear getDailyYearByOrgIdAndYear(Long currentOrgId, Integer year);

	public DailyYear setWhetherAutoStartDailyYear(DailyYear dailyYear);

	public List<DailyYear> findAutoStartDailyYear();

	public List<DailyYear> findManuallyStartDailyYear();
}
