package com.tianque.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.domain.OperateLog;

public interface OperateLogDao {

	/**
	 * 添加操作日志
	 * 
	 * @param operateLog
	 * @return
	 */
	public OperateLog addOperateLog(OperateLog operateLog);

	/**
	 * 根据id获取操作日志信息
	 * 
	 * @param id
	 * @return
	 */
	public OperateLog getOperateLogById(Long id);

	/**
	 * 根据日期区间删除操作日志信息
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int deleteOperateLogsByDateRange(Date startDate, Date endDate);

	/**
	 * 统计总数
	 * 
	 * @param orgCode
	 * @param operationType
	 * @param moduleType
	 * @param startDate
	 * @param endDate
	 * @return
	 */

	public int statisticsOperateLogs(String orgCode, int operationType, String moduleType,
			Date startDate, Date endDate);

	/**
	 * 统计所有操作总数
	 * 
	 * @param operateTypes
	 * @param orgCode
	 * @param moduleType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String, Object>> statisticsAllOperate(List<Integer> operateTypes,
			List<String> moduleTypes, String orgCode, Date startDate, Date endDate);
}
