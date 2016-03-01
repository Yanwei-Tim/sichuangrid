package com.tianque.xichang.working.report.service;

/**
 * @Description:生成数据报表类
 * @author zhangyouwei@hztianque.com
 * @date: 2015-4-21 下午04:04:50
 */
public interface CreateAccountReportDataService {
	/**
	 * 生成报表功能
	 * 
	 * @param year
	 * @param month
	 * @param reportLevel
	 *            是县乡镇，还是社区
	 */
	public void createAccountReportData(Integer year, Integer month,
			Integer reportLevel);
}
