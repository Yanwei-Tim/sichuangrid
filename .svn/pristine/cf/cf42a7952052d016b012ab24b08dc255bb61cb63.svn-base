package com.tianque.plugin.analyzing.service;

import java.util.Date;
import java.util.List;

import com.tianque.domain.UsedInfo;

/**
 * @Description:网格化服务管理信息系统使用情况service
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-27 下午05:17:52
 */
public interface UsedInfoService {
	/**
	 * 根据orgId查询下辖系统使用情况
	 * 
	 * @param orgId
	 * @return
	 */
	public List<UsedInfo> findUsedInfoData(Long orgId);

	/**
	 * job调用生成数据（每天调用一次）
	 */
	public void createUsedInfoData();

	/**
	 * 生成一条记录
	 * 
	 * @param beforDayStartDate
	 * @param beforDayEndDate
	 * @param beforWeekMonday
	 * @param beforWeekSunday
	 * @param monthStartDate
	 * @param monthEndDate
	 * @param orgId
	 * @param orgTypeId
	 * @return
	 */
	public List<UsedInfo> createUsedInfoDataCacheByOrgId(
			Date beforDayStartDate, Date beforDayEndDate, Date beforWeekMonday,
			Date beforWeekSunday, Date monthStartDate, Date monthEndDate,
			Long orgId, Long orgTypeId);

	/**
	 * 为优化后的job调用
	 * 
	 * @param beforDayStartDate
	 * @param beforDayEndDate
	 * @param orgId
	 * @param id
	 */
	public List<UsedInfo> createUsedInfoDataCacheByOrgIdToOptmize(
			Date beforDayStartDate, Date beforDayEndDate, Long orgId, Long id);
	
	public void createUserSignEveryDay();
}
