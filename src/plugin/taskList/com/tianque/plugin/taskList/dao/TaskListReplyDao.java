package com.tianque.plugin.taskList.dao;

import java.util.List;

import com.tianque.plugin.taskList.domain.TaskListReply;

/**
 * 任务清单回复
 * @ClassName: TaskListReplyDao 
 * @author: he.simin
 * @date: 2015年11月12日 下午3:39:13
 */
public interface TaskListReplyDao {
	/**
	 * 新增回复
	 * @Title: addTaskListReply 
	 * @param taskListReply
	 * @return
	 * @return: TaskListReply
	 */
	public TaskListReply addTaskListReply(TaskListReply taskListReply);

	/**
	 * 获取by id
	 * @param id id
	 * @return
	 */
	public TaskListReply getTaskListReplyById(Long id);

	/**
	 * 查询回复集合
	 * @param data 该附件的功能模块对象id和功能模块对象名
	 * @return
	 */
	public List<TaskListReply> queryTaskListReplyByTaskId(String moduleKey, Long taskId);
}
