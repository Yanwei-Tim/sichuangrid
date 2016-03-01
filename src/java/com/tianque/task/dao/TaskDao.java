package com.tianque.task.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.task.domain.Task;

/**
 * 任务数据库处理接口
 * 
 * @ClassName: TaskDao
 * @Description: TODO(描述)
 * @author 王乐
 * @date 2013-06-07 16:04:00
 * */
public interface TaskDao {
	public Task getTaskById(Task task);

	public Task addTask(Task task);

	public Task updateTaskById(Task task);

	public Task changeTaskById(Task task);

	public boolean getTaskByPloyId(Task task);

	public void deleteTaskById(Task task);

	public void deleteTaskByIds(List<Task> tasks);

	public PageInfo<Task> findTaskPage(Task task, Integer page, Integer rows,
			String sidx, String sord);

	public List getAllTask();
}
