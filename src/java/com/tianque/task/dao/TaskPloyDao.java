package com.tianque.task.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.task.domain.TaskPloy;

/**
 * 策略数据库处理接口
 * 
 * @ClassName: TaskPloyDao
 * @Description: TODO(描述)
 * @author 王乐
 * @date 2013-06-07 16:04:00
 * */
public interface TaskPloyDao {
	public TaskPloy getTaskPloyById(TaskPloy taskPloy);

	public TaskPloy addTaskPloy(TaskPloy taskPloy);

	public TaskPloy updateTaskPloyById(TaskPloy taskPloy);

	public void deleteTaskPloyById(TaskPloy taskPloy);

	public void deleteTaskPloyByIds(List<TaskPloy> taskPloys);

	public PageInfo<TaskPloy> findTaskPloyPage(TaskPloy taskPloy, Integer page,
			Integer rows, String sidx, String sord);
}
