package com.tianque.bigData.api;

import com.tianque.core.vo.PageInfo;
import com.tianque.jms.domain.SystemOperateLogDTO;
import com.tianque.systemOperateLog.domain.SystemOperateLog;

public interface PhoenixHbaseDubbo {
	public void addSystemOperateLog(SystemOperateLogDTO systemOperateLogDTO);

	public PageInfo<SystemOperateLog> findAllSystemLogsForPage(
			String findLogsSql, int pageNum, int pageSize, String sortField,
			String order);

	public SystemOperateLog getSystemOperateLogById(Long id);
}
