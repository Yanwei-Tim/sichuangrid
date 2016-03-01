package com.tianque.usedInfoOptmize.service;

import java.util.List;

import com.tianque.usedInfoOptmize.domain.UsedInfoData;

/**
 * @Description:信息系统使用情况报表service
 * @author zhangyouwei@hztianque.com
 * @date: 2015-7-1 下午02:19:43
 */
public interface UsedInfoDataService {

	/**
	 * 生成月数据
	 */
	public void createUsedInfoMonthData();

	/**
	 * 生成周数据
	 */
	public void createUsedInfoWeekData();

	/**
	 * 获取周数据
	 * 
	 * @param orgId
	 * @return
	 */
	public List<UsedInfoData> findUsedInfoWeekDataByParentOrgId(Long parentOrgId);

	/**
	 * 获取月数据
	 * 
	 * @param orgId
	 * @return
	 */
	public List<UsedInfoData> findUsedInfoMonthDataByParentOrgId(
			Long parentOrgId);

}
