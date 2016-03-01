package com.tianque.dao;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SystemhighLog;

public interface SystemhighLogDao {
	public SystemhighLog getSystemhighLogById(Long id);

	public SystemhighLog addSystemhighLog(SystemhighLog systemhighLog);

	public PageInfo<SystemhighLog> findAllSystemhighLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, String modelName, Integer operationType,
			String operationUserName, int pageNum, int pageSize, String sortField, String order);

	public PageInfo<SystemhighLog> findAllSystemhighLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, int pageNum, int pageSize, String sortField, String order);

}
