package com.tianque.plugin.taskList.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.plugin.taskList.dao.TaskListReplyDao;
import com.tianque.plugin.taskList.domain.TaskListReply;
import com.tianque.plugin.taskList.service.TaskListReplyService;

@Service("taskListReplyService")
public class TaskListReplyServiceImpl implements TaskListReplyService {
	@Autowired
	private TaskListReplyDao taskListReplyDao;

	@Override
	public TaskListReply addTaskListReply(TaskListReply taskListReply) {
		return taskListReplyDao.addTaskListReply(taskListReply);
	}

	@Override
	public TaskListReply getTaskListReplyById(Long id) {
		return taskListReplyDao.getTaskListReplyById(id);
	}

	@Override
	public List<TaskListReply> queryTaskListReplyByTaskId(String moduleKey, Long taskId) {
		return taskListReplyDao.queryTaskListReplyByTaskId(moduleKey, taskId);
	}

}
