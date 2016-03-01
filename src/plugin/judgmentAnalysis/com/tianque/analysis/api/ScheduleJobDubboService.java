package com.tianque.analysis.api;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJob;

public interface ScheduleJobDubboService {
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
	PageInfo<ScheduleJob> findScheduleJobForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			ScheduleJob scheduleJob);

	/**
	 * 查询单个
	 * 
	 * @param id
	 * @return
	 */
	ScheduleJob getScheduleJobById(Long id);

	/**
	 * @Description: 根据名称查询
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param Name
	 * @return
	 * @throws
	 */
	ScheduleJob getScheduleJobByName(String name, Integer runType);

	/**
	 * 修改
	 * 
	 * @param ScheduleJob
	 * @return
	 */
	ScheduleJob updateScheduleJob(ScheduleJob scheduleJob) throws Exception;

	/**
	 * 添加
	 * 
	 * @param ScheduleJob
	 * @return
	 */
	ScheduleJob addScheduleJob(ScheduleJob scheduleJob);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void deleteScheduleJobById(Long id) throws Exception;

	/**
	 * 根据条件查询
	 * 
	 * @time: 2015-3-19 下午02:18:39
	 */
	ScheduleJob getScheduleJobByConditions(ScheduleJob scheduleJob);

	void updateScheduleJobCurrentCycle(ScheduleJob scheduleJob);

	boolean existsScheduleJob(String trim);

	void scheduleJobStart(Long[] ids) throws Exception;

	void scheduleJobStop(Long[] ids) throws Exception;

	void scheduleJobSync() throws Exception;

	String scheduleJobIsStart(Long[] ids) throws Exception;
}
