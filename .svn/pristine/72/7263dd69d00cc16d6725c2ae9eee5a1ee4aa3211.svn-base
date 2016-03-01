package com.tianque.xichang.working.report.service;

import java.util.List;

/**
 * @Description:三本台账报表统计优化的job帮助类
 * @author zhangyouwei@hztianque.com
 * @date: 2014-11-20 上午11:15:30
 */
public interface AccountReportJobOptmizeHelper {

	/**
	 * 乡镇级别根据调度分配的值去新增数据
	 * 
	 * @param orgIds
	 */
	public int executeDataByModelForAccountReportTown(Integer year,
			Integer month, List<Long> idModList, String taskParameter);

	/**
	 * 县区级别根据调度分配的值去新增数据
	 * 
	 * @param orgIds
	 */
	public int executeDataByModelForAccountReportDistrict(Integer year,
			Integer month, List<Long> idModList, String taskParameter);

	/**
	 * 社区级别 根据取模去生成数据
	 * 
	 * @param idModList
	 * @param taskParameter
	 */
	public int executeDataByModelForAccountReportVillage(Integer year,
			Integer month, List<Long> idModList, String taskParameter);
}
