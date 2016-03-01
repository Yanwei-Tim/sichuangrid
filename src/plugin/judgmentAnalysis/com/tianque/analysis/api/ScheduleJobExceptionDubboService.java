package com.tianque.analysis.api;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJobException;

public interface ScheduleJobExceptionDubboService {
	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param name
	 * @param keyName
	 * @param type
	 * @return
	 */
	PageInfo findScheduleJobExceptionForPage(Integer pageNum, Integer pageSize,
			String sortField, String order,
			ScheduleJobException scheduleJobException);

	/**
	 * 查询单个
	 * 
	 * @param id
	 * @return
	 */
	ScheduleJobException getScheduleJobExceptionById(Long id);

	/**
	 * 修改
	 * 
	 * @param ScheduleJobException
	 * @return
	 */
	ScheduleJobException updateScheduleJobException(
			ScheduleJobException scheduleJobException);

	/**
	 * 添加
	 * 
	 * @param ScheduleJobException
	 * @return
	 */
	ScheduleJobException addScheduleJobException(
			ScheduleJobException scheduleJobException);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void deleteScheduleJobExceptionById(Long id);

	/**
	 * 根据条件查询
	 * 
	 * @time: 2015-3-19 下午02:18:39
	 */
	ScheduleJobException getScheduleJobExceptionByConditions(
			ScheduleJobException scheduleJobException);

	/**
	 * @Description: 处理发生异常
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param scheduleJobException
	 * @throws
	 */
	void dealScheduleJobOnException(ScheduleJobException scheduleJobException);
}
