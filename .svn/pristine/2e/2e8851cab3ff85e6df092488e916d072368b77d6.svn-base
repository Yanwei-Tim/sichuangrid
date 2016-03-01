package com.tianque.plugin.taskList.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.taskList.dao.TaskListReplyDao;
import com.tianque.plugin.taskList.domain.TaskListReply;

@Repository("taskListReplyDao")
public class TaskListReplyDaoImpl extends AbstractBaseDao implements TaskListReplyDao {

	@Override
	public TaskListReply addTaskListReply(TaskListReply taskListReply) {
		Long id = (Long) getSqlMapClientTemplate().insert("taskListReply.addTaskListReply",
				taskListReply);
		return getTaskListReplyById(id);
	}

	@Override
	public TaskListReply getTaskListReplyById(Long id) {
		return (TaskListReply) getSqlMapClientTemplate().queryForObject(
				"taskListReply.getTaskListReplyById", id);
	}

	@Override
	public List<TaskListReply> queryTaskListReplyByTaskId(String moduleKey, Long taskId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moduleKey", moduleKey);
		map.put("taskId", taskId);
		return getSqlMapClientTemplate().queryForList("taskListReply.queryTaskListReplyByTaskId",
				map);
	}

}
