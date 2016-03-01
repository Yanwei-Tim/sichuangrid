package com.tianque.systemOperateLog.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.vo.SystemOperateLogVo;

public interface SystemOperateLogDao {

	/**
	 * 添加系统日志
	 * 
	 * @param SystemOperateLog
	 *            系统日志对象
	 * @return SystemOperateLog 系统日志对象
	 */
	public SystemOperateLog addSystemOperateLog(
			SystemOperateLog systemOperateLog);

	public SystemOperateLog getSystemOperateLogById(Long id);

	public PageInfo<SystemOperateLog> findAllSystemLogsForPage(
			SystemOperateLogVo systemOperateLogVo, int pageNum, int pageSize,
			String sortField, String order);

	public SystemOperateLog updateSystemOperateLogById(
			SystemOperateLog systemOperateLog);

	public Long getSystemOperateLogKey();

	public PageInfo<SystemOperateLog> findSystemLogsForPageImportToHbase(
			int pageNum, int pageSize, String sortField, String order);

	/**
	 * 批量导入到hbase
	 * 
	 * @param list
	 */
	// public void addSystemOperateLogsForBatch(List<SystemOperateLog> list);

	/**
	 * 修改dataKeyWord 可以根据不同的条件修改，有些值可以为null
	 * 
	 * @param orgId
	 *            组织机构Id
	 * @param dataId
	 *            数据对应的dataId
	 * @param moduleType
	 *            模块类型
	 * @param businessType
	 *            业务类型
	 * @param beforeDataKeyWord
	 *            修改之前的值
	 * @param afterDataKeyWord
	 *            修改之后的值
	 */
	public void updateSystemOperateDataKeyWord(Long orgId, Long dataId,
			String moduleType, String businessType, String beforeDataKeyWord,
			String afterDataKeyWord);

}
