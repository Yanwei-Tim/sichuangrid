package com.tianque.task.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.task.domain.Task;

/**
 * 任务服务层接口
 * */
public interface TaskService {
	public static final String ERROR_ID = "参数id不合法";
	public static final String NO_RESULT = "编辑任务不存在";
	public static final String ERROT_ARGUMENT = "参数不合法";
	public static final String NO_STARTDATE = "请输入开始时间";
	public static final String NO_ENDDATE = "请输入结束时间";
	public static final String STARTDATE_GRANT_ENDDATE = "开始时间早于结束时间";
	public static final String ERROR_NAME = "任务名称不正确";
	public static final String ERROT_STATUS = "开启后不能修改";
	public static final String HAS_ENDED = "已经结束了";

	public static final Integer GET = 0;
	public static final Integer ADD = 1;
	public static final Integer DELETE = 2;
	public static final Integer UPDATE = 3;
	public static final Integer CHANGE = 4;

	public Task getTaskById(Task task);

	public Task addTask(Task task);

	public Task updateTaskById(Task task);

	public Task changeTaskById(Task task);

	public void deleteTaskById(Task task);

	public void deleteTaskByIds(List<Task> tasks);

	public PageInfo<Task> findTaskPage(Task task, Integer page, Integer rows,
			String sidx, String sord);

	public void afterStartRunTask();
}
