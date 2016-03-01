package com.tianque.plugin.analyzing.service;

import java.util.List;

import com.tianque.plugin.analyzing.domain.UserActivateReport;

/**
 * @Description:用户覆盖率业务接口
 * @author zhangyouwei@hztianque.com
 * @date: 2015-1-16 上午12:23:52
 */
public interface UserActivateReportService {

	/**
	 * 用户覆盖率job调用生成数据
	 */
	public void createUserActivateReportData();

	/**
	 * 根据月份查询数据
	 * 
	 * @param year
	 * @param month
	 * @param orgLevelDistance
	 * @return
	 */
	public List<UserActivateReport> getUserActivateReportList(int year,
			int month, Integer orgLevelDistance);

	/**
	 * 根据年月生成报表
	 * 
	 * @param year
	 * @param month
	 */
	public void createUserActivateReportList(int year, int month);

	public List<UserActivateReport> getUserActivateReportSort(int year,
			int month, Integer orgLevelDistance, final String sortName,
			final String sort);
}
