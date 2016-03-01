package com.tianque.service;

import java.util.List;
import java.util.Map;

import com.tianque.domain.OperateLog;

public interface OperateLogService {

	public OperateLog log(Long orgId, String operation, String moduleName, Integer operationType,
			String moduleType, String operateContent);

	public OperateLog log(Long orgId, int logLevel, String moduleName, Integer operationType,
			String operation, String moduleType, String operateContent);

	/**
	 * 总况统计
	 * 
	 * @param orgId
	 * @param operateTypes
	 * @param moduleType
	 * @param year
	 * @param month
	 * @return
	 */
	public Map<String, Integer> statisticsAllOperate(Long orgId, List<Integer> operateTypes,
			String moduleType, int year, int month);

	/**
	 * 分表统计
	 * 
	 * @param orgId
	 * @param operateTypes
	 * @param moduleType
	 * @param tableName
	 * @param year
	 * @param month
	 * @return
	 */
	public Map<String, Integer> statisticsAllOperateByTableName(Long orgId,
			List<Integer> operateTypes, String tableName, int year, int month);
}
