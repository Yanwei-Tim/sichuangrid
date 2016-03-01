package com.tianque.plugin.tbSchedule.service;

import java.util.List;

import com.tianque.core.vo.GridPage;
import com.tianque.plugin.tbSchedule.domain.IdCardNoExpLog;
import com.tianque.plugin.tbSchedule.domain.TBScheduleLog;

/**
 * @ClassName: ScheduleTaskDealService
 * @Description: 任务调度处理接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年11月14日 下午3:37:48
 */
public interface ScheduleTaskDealService {

	/**
	 * @Description: 任务执行过程中发送异常
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param id
	 *            日志id
	 * @param exception
	 *            异常对象
	 * @throws
	 */
	void addTBScheduleLogForException(Long id, Throwable exception);

	/**
	 * @param getListSize
	 * @Description: 任务结束，更新日志信息
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param id
	 *            日志id
	 * @param listSize
	 *            本次执行数据集合大小
	 * @throws
	 */
	void updateTBScheduleLogIsEnd(Long id, Integer listSize);

	/**
	 * @Description: 新增任务调度日志
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param jobName
	 *            任务名称
	 * @param taskParameter
	 *            任务参数
	 * @param ownSign
	 *            所有者
	 * @param taskItemNum
	 *            任务项个数
	 * @param taskItems
	 *            任务项集合
	 * @param eachFetchDataNum
	 *            每次获取量
	 * @param reMark
	 *            任务中文描述
	 * @return 日志id
	 * @return
	 * @throws
	 */
	Long saveTBScheduleLog(String jobName, String taskParameter,
			String ownSign, int taskItemNum, String taskItems,
			int eachFetchDataNum, String reMark);

	/**
	 * @Description: 前台列表
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param tbSchedule
	 *            参数对象
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页记录数
	 * @param sidx
	 *            排序字段
	 * @param sord
	 *            排序方式
	 * @return
	 * @throws
	 */
	GridPage queryTBScheduleLogForPage(TBScheduleLog tbSchedule, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * @Description: 查询日志详情
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param tbSchedule
	 *            查询参数
	 * @return
	 * @throws
	 */
	TBScheduleLog getFullTBScheduleLogForView(TBScheduleLog tbSchedule);

	void addIdCardNoExpLog(String msg, String idCardNo);

	GridPage queryIdCardNoExpLogForPage(IdCardNoExpLog idCardNoExpLog,
			Integer page, Integer rows, String sidx, String sord);

	List<IdCardNoExpLog> queryIdCardNoExpLog(IdCardNoExpLog idCardNoExpLog,
			String sidx, String sord);

	/**
	 * @param tbSchedule
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	GridPage queryExceptionForPage(Long logId, Integer page, Integer rows,
			String sidx, String sord);

}
