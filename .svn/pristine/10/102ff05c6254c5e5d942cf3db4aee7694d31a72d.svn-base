package com.tianque.plugin.taskList.daoImpl;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.taskList.dao.TaskListTimeStandardDao;
import com.tianque.plugin.taskList.domain.TaskListTimeStandard;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hesimin on 2015/12/17
 */
@Repository("taskListTimeStandardDao")
public class TaskListTimeStandardDaoImpl extends AbstractBaseDao implements TaskListTimeStandardDao {
	@Override
	public TaskListTimeStandard addTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard) {
		Long id = (Long) getSqlMapClientTemplate().insert("taskListTimeStandard.addTaskListTimeStandard", taskListTimeStandard);
		return getTaskListTimeStandardById(id);
	}

	@Override
	public TaskListTimeStandard getTaskListTimeStandardById(Long id) {
		return (TaskListTimeStandard) getSqlMapClientTemplate().queryForObject("taskListTimeStandard.getTaskListTimeStandardById",id);
	}

	@Override
	public List<TaskListTimeStandard> getTaskListTimeStandardByOrgCodeAndItemNameDictId(String orgCode, Long itemNameDictId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgCode",orgCode);
		map.put("itemNameDictId",itemNameDictId);
		return getSqlMapClientTemplate().queryForList("taskListTimeStandard.getTaskListTimeStandardByOrgCodeAndItemNameDictId",map);
	}

	@Override
	public TaskListTimeStandard getTaskListTimeStandardByOrgIdAndItemNameDictId(Long orgId, Long itemNameDictId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId",orgId);
		map.put("itemNameDictId",itemNameDictId);
		return (TaskListTimeStandard) getSqlMapClientTemplate().queryForObject("taskListTimeStandard.getTaskListTimeStandardByOrgIdAndItemNameDictId",map);
	}

	@Override
	public void updateTaskListTimeStandard(TaskListTimeStandard taskListTimeStandard) {
		getSqlMapClientTemplate().update("taskListTimeStandard.updateTaskListTimeStandard",taskListTimeStandard);
	}

	@Override
	public void deleteTaskListTimeStandard(Long id) {
		getSqlMapClientTemplate().delete("taskListTimeStandard.deleteTaskListTimeStandard",id);
	}

	@Override
	public void deleteTaskListTimeStandardByIds(List<Long> ids) {
		getSqlMapClientTemplate().delete("taskListTimeStandard.deleteTaskListTimeStandardByIds",ids);
	}

	@Override
	public PageInfo<TaskListTimeStandard> findTaskListTimeStandard(String orgCode, Integer pageNum, Integer pageSize, String sortField, String order) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgCode",orgCode);
		return getPageInfoByParamMap(new PageInfo(), "taskListTimeStandard.findTaskListTimeStandardCount", "taskListTimeStandard.findTaskListTimeStandard", pageNum, pageSize, map);
	}

	@Override
	public Integer countByOrgIdAndItemName(Long orgId, Long itemNameDictId) {
		Map map = new HashMap();
		map.put("orgId",orgId);
		map.put("itemNameDictId",itemNameDictId);
		return (Integer) getSqlMapClientTemplate().queryForObject("taskListTimeStandard.countByOrgIdAndItemName",map);
	}
}
