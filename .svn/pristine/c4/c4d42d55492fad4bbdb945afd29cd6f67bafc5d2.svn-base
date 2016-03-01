package com.tianque.systemOperateLog.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.vo.SystemOperateLogVo;

public interface SystemOperateLogService {
	/**
	 * 添加系统日志
	 * 
	 * @return SystemOperateLog 系统日志对象
	 */
	public SystemOperateLog addSystemOperateLog(
			SystemOperateLog systemOperateLog);

	/**
	 * 添加系统日志
	 * 
	 * @param businesType
	 *            数据所属类型
	 * @param dataKeyword
	 *            数据唯一标示（人：身份证）
	 * @param dataOrg
	 *            所属网格
	 * @param operateSource
	 *            操作源（在那个模块操作）
	 * @param operateType
	 *            操作类型
	 * @param dataId
	 *            数据id（针对没有唯一标示，存id，例如未落户）
	 * @param oldOrgId
	 *            数据操作之前的网格（针对转移）
	 * @return SystemOperateLog 系统日志对象
	 */
	public SystemOperateLog addSystemOperateLog(String businesType,
			String dataKeyword, Organization dataOrg, String orgCode,
			String operateSource, Integer operateType, Long dataId,
			Long oldOrgId);

	public SystemOperateLog getSystemOperateLogById(Long id);

	public PageInfo<SystemOperateLog> findAllSystemLogsForPage(
			SystemOperateLogVo systemOperateLogVo, int pageNum, int pageSize,
			String sortField, String order);

	public SystemOperateLog updateSystemOperateLogById(
			SystemOperateLog systemOperateLog);

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
