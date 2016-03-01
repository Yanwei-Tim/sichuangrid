package com.tianque.analysis.api;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJobLog;

public interface ScheduleJobLogDubboService {
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param name
	 * @param keyName
	 * @param type
	 * @return
	 */
	PageInfo findScheduleJobLogForPage(Integer pageNum, Integer pageSize, String sortField,
			String order, ScheduleJobLog scheduleJobLog);

	/**
	 * 查询单个
	 * @param id
	 * @return
	 */
	ScheduleJobLog getScheduleJobLogbById(Long id);

	/**
	 * 修改
	 * @param ScheduleJob
	 * @return
	 */
	ScheduleJobLog updateScheduleJobLog(ScheduleJobLog scheduleJobLog);

	/**
	 * 添加
	 * @param ScheduleJob
	 * @return
	 */
	ScheduleJobLog addScheduleJobLog(ScheduleJobLog scheduleJobLog);

	/**
	 * 删除
	 * @param id
	 */
	void deleteScheduleJobLogById(Long id);

	/**
	 * 根据条件查询
	 * @time: 2015-3-19 下午02:18:39
	 */
	ScheduleJobLog getScheduleJobLogByConditions(ScheduleJobLog scheduleJobLog);

	void updateScheduleJobLogExceptionNum(Long id);
}
