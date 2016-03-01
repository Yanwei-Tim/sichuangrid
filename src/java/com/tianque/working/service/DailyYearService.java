package com.tianque.working.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.vo.DailyDirectoryVo;

public interface DailyYearService {

	public DailyYear addDailyYear(DailyYear dailyYear);

	public DailyYear getSimpleDailyYearById(Long id);

	public PageInfo<DailyYear> findDailyYearForPageByOrgId(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public List<DailyYear> findDailyYearList();

	public List<DailyYear> findDailyYearsByOrgId(Long orgId);

	/**
	 * 查询启用的年度台帐目录
	 * 
	 * @param orgId
	 * @param status
	 * @return
	 */
	public List<DailyYear> findDailyYearsByOrgIdAndStatus(Long orgId, Long status);

	public DailyYear getInitYear();

	public DailyYear findDailyYearByParentOrgIdAndYear(Long currentOrgId, String name);

	/**
	 * 成功删除返回 “true” 字符
	 * 
	 * @param yearId
	 * @return
	 */
	public String deleteDailyYearById(Long yearId);

	public String validateDeleteDailyYearById(Long yearId);

	/**
	 * 更新年度台帐目录
	 * 
	 * @param templateId
	 * @param dailyYear
	 */
	public DailyYear updateDailyYear(DailyYear dailyYear);

	/**
	 * 年度台帐目录细则初始化
	 * 
	 * @param templateId
	 * @param dailyYear
	 */
	public void initDailyDirectory(Long templateId, DailyYear dailyYear);

	/**
	 * 年度台帐目录细则展现
	 * 
	 * @param dailyYearId
	 * @return
	 */
	public List<DailyDirectoryVo> findDailyDirectory(Long dailyYearId);

	/**
	 * 验证年度台帐目录唯一，有年度台帐目录存在返回 ture，否则返回 false
	 * 
	 * @param dailyYear
	 * @return
	 */
	public boolean checkUniqueDailyYearByOrgIdAndYear(DailyYear dailyYear);

	/**
	 * 启用年度台帐目录
	 * 
	 * @param dailyYear
	 * @return
	 */
	public DailyYear startDailyYearById(Long dailyYearId);

	/**
	 * 停用年度台帐目录
	 * 
	 * @param dailyYear
	 * @return
	 */
	public DailyYear stopDailyYearById(Long dailyYearId);

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
