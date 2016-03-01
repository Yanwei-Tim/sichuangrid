package com.tianque.xichang.working.statisticsAccountTimeout.service;

import java.util.List;

/***
 * 三本台账时限成绩job帮助类
 * 
 * @author zhangyouwei
 * 
 */
public interface StatisticsAccountTimeoutsJobHelper {

	/**
	 * 省和市、县级所以的组织机构（包括省和市下面的职能部门）
	 */
	// public void addProvinceAndCityStatisticsAccountTimeoutData();

	/**
	 * 乡镇级所以的组织机构（包括职能部门）
	 */
	// public void addTownStatisticsAccountTimeoutData();

	/**
	 * 社区级所以的组织机构
	 */
	// public void addVillageStatisticsAccountTimeoutData();

	/**
	 * 网格级所以的组织机构
	 */
	// public void addGridStatisticsAccountTimeoutData();

	/**
	 * 淘宝任务调度管理优化后的调用的方法
	 * 
	 * @param orgId
	 * @param map
	 */
	// public void addStatisticsAccountTimeoutDataByOrgIdAndTimes(Long orgId,
	// Map<String, Date> date);

	/**
	 * 根据取模去删除原有的数据脏数据
	 * 
	 * @param idModList
	 * @param fetchNum
	 * @param taskParameter
	 * @param tableName
	 */
	// public void deleteDirtyDataByModel(List<Long> idModList, int fetchNum,
	// String taskParameter, int year, int month);

	/**
	 * 根据取模去生成数据
	 * 
	 * @param idModList
	 * @param fetchNum
	 * @param taskParameter
	 */
	public int executeDataByModel(List<Long> idModList, String taskParameter);

}
