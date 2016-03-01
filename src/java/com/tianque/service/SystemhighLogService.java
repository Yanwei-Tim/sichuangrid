package com.tianque.service;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SystemhighLog;

public interface SystemhighLogService {
	public SystemhighLog addSystemhighLog(SystemhighLog systemhighLog);

	public PageInfo<SystemhighLog> findSystemhighLogByOrgCode(String OrgCode, Date startDate,
			Date endDate, String modelName, Integer operationType, String operationUserName,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<SystemhighLog> findSystemhighLogByOrgCode(String OrgCode, Date startDate,
			Date endDate, Integer pageNum, Integer pageSize, String sidx, String sord);

}
