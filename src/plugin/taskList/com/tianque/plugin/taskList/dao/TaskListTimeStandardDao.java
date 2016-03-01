package com.tianque.plugin.taskList.dao;


import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.domain.TaskListTimeStandard;

import java.util.List;

/**
 * Created by hesimin on 2015/12/17.
 */
public interface TaskListTimeStandardDao {
	public TaskListTimeStandard addTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard);
	public TaskListTimeStandard getTaskListTimeStandardById(Long id);

	/**
	 * 根据组织code和项目名查找考核时限标准
	 * @param orgCode 模糊查询：orgCode%
	 * @param itemNameDictId
	 * @return
	 */
	public List<TaskListTimeStandard> getTaskListTimeStandardByOrgCodeAndItemNameDictId(String orgCode,Long itemNameDictId);

	/**
	 * 根据组织id和项目名查找考核时限标准
	 * @param orgId
	 * @param itemNameDictId
	 * @return
	 */
	public TaskListTimeStandard getTaskListTimeStandardByOrgIdAndItemNameDictId(Long orgId,Long itemNameDictId);

	/**
	 * 更新：职能部门和项目名不可修改
	 * @param taskListTimeStandard
	 */
	public void updateTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard);
	public void deleteTaskListTimeStandard(Long id);
	public void deleteTaskListTimeStandardByIds(List<Long> ids);
	public PageInfo<TaskListTimeStandard> findTaskListTimeStandard(
			String orgCode,Integer pageNum, Integer pageSize,
			String sortField, String order);

	/**
	 * 根据职能部门组织id和项目名称统计，防止重复添加
	 * @param orgId
	 * @param itemNameDictId
	 * @return
	 */
	public Integer countByOrgIdAndItemName(Long orgId, Long itemNameDictId);
}
