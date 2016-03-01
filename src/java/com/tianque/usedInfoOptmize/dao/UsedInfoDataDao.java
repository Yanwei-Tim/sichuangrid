package com.tianque.usedInfoOptmize.dao;

import java.util.Date;
import java.util.List;

import com.tianque.usedInfoOptmize.domain.UsedInfoData;

/**
 * @Description:信息系统使用情况报表dao
 * @author zhangyouwei@hztianque.com
 * @date: 2015-7-1 下午03:39:20
 */
public interface UsedInfoDataDao {

	/**
	 * 生成月数据
	 * 
	 * @param startDate
	 * @param endDate
	 * @param orgTypeId
	 */
	public void createUsedInfoMonthData(Date startDate, Date endDate,
			Long orgTypeId);

	/**
	 * 生成周数据
	 * 
	 * @param startDate
	 * @param endDate
	 * @param orgTypeId
	 */
	public void createUsedInfoWeekData(Date startDate, Date endDate,
			Long orgTypeId);

	/**
	 * 删除月数据
	 */
	public void deleteUsedInfoMonthData();

	/**
	 * 删除周数据
	 */
	public void deleteUsedInfoWeekData();

	public void dropTempWeekTable();
	
	public void createTempWeekData(Date startDate, Date endDate,
			Long orgTypeId);
	
	public void mergeWeekData();
	
	/**
	 * 根据父组织机构id获取周数据
	 * 
	 * @param parentOrgId
	 * @return
	 */
	public List<UsedInfoData> findUsedInfoWeekDataByParentOrgId(Long parentOrgId);

	/**
	 * 根据父组织机构id获取月数据
	 * 
	 * @param parentOrgId
	 * @return
	 */
	public List<UsedInfoData> findUsedInfoMonthDataByParentOrgId(
			Long parentOrgId);

}
