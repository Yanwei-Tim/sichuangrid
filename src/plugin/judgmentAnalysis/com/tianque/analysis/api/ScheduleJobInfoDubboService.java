package com.tianque.analysis.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJobInfo;

public interface ScheduleJobInfoDubboService {
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
	PageInfo findScheduleJobInfoForPage(Integer pageNum, Integer pageSize,
			String sortField, String order, ScheduleJobInfo scheduleJobInfo);

	/**
	 * 查询单个
	 * 
	 * @param id
	 * @return
	 */
	ScheduleJobInfo getScheduleJobInfoById(Long id);

	/**
	 * 修改
	 * 
	 * @param ScheduleJobInfo
	 * @return
	 */
	ScheduleJobInfo updateScheduleJobInfo(ScheduleJobInfo scheduleJobInfo);

	/**
	 * 添加
	 * 
	 * @param ScheduleJobInfo
	 * @return
	 */
	ScheduleJobInfo addScheduleJobInfo(ScheduleJobInfo scheduleJobInfo);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void deleteScheduleJobInfoById(Long id);

	/**
	 * 根据条件查询
	 * 
	 * @time: 2015-3-19 下午02:18:39
	 */
	ScheduleJobInfo getScheduleJobInfoByConditions(
			ScheduleJobInfo scheduleJobInfo);

	List<ScheduleJobInfo> queryScheduleJobInfoForListByScheduleJobId(Long id);
}
