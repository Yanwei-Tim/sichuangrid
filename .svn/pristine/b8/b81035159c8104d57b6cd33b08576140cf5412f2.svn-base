package com.tianque.plugin.tbSchedule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tianque.job.JobHelper;
import com.tianque.plugin.tbSchedule.service.ScheduleTaskDealService;

/**
 * @ClassName: IScheduleTaskDealSingleBase
 * @Description: 单任务调度基类
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年11月14日 下午3:31:54
 * @param <T>
 */
public abstract class IScheduleTaskDealSingleBase<T> implements
		IScheduleTaskDealSingle<T> {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected ScheduleTaskDealService scheduleTaskDealService;

	protected Long logId;

	/**
	 * 处理数据数，为了记录queryTasks里直接执行的job
	 */
	protected ThreadLocal<Integer> size = new ThreadLocal<Integer>();

	/**
	 * @Description: 执行任务前置处理器
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param jobName
	 *            任务名称
	 * @param taskParameter
	 *            任务参数
	 * @param ownSign
	 *            所有者
	 * @param taskItemNum
	 *            任务项个数
	 * @param taskItemList
	 *            任务项集合
	 * @param eachFetchDataNum
	 *            每次获取量
	 * @return 日志id
	 * @throws
	 */
	public Long beforeTask(String jobName, String taskParameter,
			String ownSign, int taskItemNum, List<TaskItemDefine> taskItemList,
			int eachFetchDataNum) {
		synchronized (this) {
			StringBuffer sb = new StringBuffer("");
			for (TaskItemDefine taskItemDefine : taskItemList) {
				sb.append(taskItemDefine.getTaskItemId()).append(",");
			}
			String taskItems = "".equals(sb.toString()) ? "" : sb.deleteCharAt(
					sb.toString().length() - 1).toString();
			logger.info("[" + getJobRemark() + "]任务调度开始[" + jobName + "]，参数："
					+ taskParameter + "；获取到任务项：" + taskItems);
			JobHelper.createMockAdminSession();
			logId = scheduleTaskDealService.saveTBScheduleLog(jobName,
					taskParameter, ownSign, taskItemNum, taskItems,
					eachFetchDataNum, getJobRemark());
			return logId;
		}
	}

	/**
	 * @Description: 任务后置处理器
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param id
	 *            任务id
	 * @throws
	 */
	public void afterTask(Long id, Integer listSize) {
		logger.info("[" + getJobRemark() + "]任务调度结束！");
		JobHelper.createMockAdminSession();
		scheduleTaskDealService.updateTBScheduleLogIsEnd(id,
				size.get() == null ? listSize : size.get());
		callBack();
	}

	@Override
	public List selectTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> queryCondition, int fetchNum)
			throws Exception {
		try {
			return queryTasks(taskParameter, ownSign, taskItemNum,
					queryCondition, fetchNum);
		} catch (Exception e) {
			onException(logId, e);
		}
		return new ArrayList();
	}

	/**
	 * @Description: 任务结束后回调
	 * @author wangxiaohu wsmalltiger@163.com
	 * @throws
	 */
	public abstract void callBack();

	/**
	 * 根据条件，查询当前调度服务器可处理的任务
	 * 
	 * @param taskParameter
	 *            任务的自定义参数
	 * @param ownSign
	 *            当前环境名称
	 * @param taskItemNum
	 *            当前任务类型的任务队列数量
	 * @param taskItemList
	 *            当前调度服务器，分配到的可处理队列
	 * @param eachFetchDataNum
	 *            每次获取数据的数量
	 * @return
	 * @throws Exception
	 */
	public abstract List queryTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> queryCondition, int fetchNum)
			throws Exception;

	/**
	 * @Description: 获取任务描述
	 * @author wangxiaohu wsmalltiger@163.com
	 * @throws
	 */
	public abstract String getJobRemark();

	/**
	 * @Description: 发生异常回调
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param id
	 *            任务id
	 * @param exception
	 *            异常对象
	 * @throws
	 */
	public void onException(Long id, Throwable exception) {
		logger.error("[" + getJobRemark() + "]任务调度发生异常！", exception);
		JobHelper.createMockAdminSession();
		scheduleTaskDealService.addTBScheduleLogForException(id, exception);
	}

}
