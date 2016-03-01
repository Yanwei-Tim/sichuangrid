package com.tianque.task.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.task.domain.TaskPloy;

public interface TaskPloyService {
	public static final String ERROR_ID = "参数id不合法";
	public static final String NO_RESULT = "编辑任务不存在";
	public static final String ERROT_ARGUMENT = "参数不合法";
	public static final String NO_STARTDATE = "请输入开始时间";
	public static final String NO_ENDDATE = "请输入结束时间";
	public static final String STARTDATE_GRANT_ENDDATE = "开始时间早于结束时间";
	public static final String ERROR_NAME = "任务名称不正确";
	public static final String EXISTS_TASK = "策略被使用中";

	public static final Integer GET = 0;
	public static final Integer ADD = 1;
	public static final Integer DELETE = 2;
	public static final Integer UPDATE = 3;
	public static final Integer CHANGE = 4;

	public TaskPloy getTaskPloyById(TaskPloy taskPloy);

	public TaskPloy addTaskPloy(TaskPloy taskPloy);

	public TaskPloy updateTaskPloyById(TaskPloy taskPloy);

	public void deleteTaskPloyById(TaskPloy taskPloy);

	public void deleteTaskPloyByIds(List<TaskPloy> taskPloys);

	public PageInfo<TaskPloy> findTaskPloyPage(TaskPloy taskPloy, Integer page,
			Integer rows, String sidx, String sord);
}
