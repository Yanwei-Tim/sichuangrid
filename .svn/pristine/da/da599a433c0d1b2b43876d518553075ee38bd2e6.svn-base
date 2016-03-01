package com.tianque.plugin.taskList.service;


import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.TaskListTimeStandard;

import java.util.List;

/**
 * Created by hesimin on 2015/12/17.
 */
public interface TaskListTimeStandardService {
	public TaskListTimeStandard addTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard);
	public TaskListTimeStandard getTaskListTimeStandardById(Long id);

	/**
	 * 根据组织和项目名获取任务清单考核标准,当前部门没配置查找上级职能部门的配置
	 * @param orgId 行政部门id，区域id，比如成都市id，不是职能部门组织id
	 * @param itemNameDictId
	 * @return
	 */
	public List<TaskListTimeStandard> getTaskListTimeStandardByFunOrgIdAndItemName(Long orgId, Integer itemNameDictInternalId);
	public void deleteTaskListTimeStandard(Long id);
	public void deleteTaskListTimeStandardByIds(List<Long> ids);
	public void updateTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard);
	public PageInfo<TaskListTimeStandard> findTaskListTimeStandard(
			String orgCode, Integer pageNum, Integer pageSize,
			String sortField, String order);
}
