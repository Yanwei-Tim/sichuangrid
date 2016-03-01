package com.tianque.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SystemhighLogDao;
import com.tianque.domain.SystemhighLog;
import com.tianque.service.SystemhighLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Transactional
@Service("systemhighLogService")
public class SystemhighLogServiceImpl extends AbstractBaseService implements
		SystemhighLogService {
	@Autowired
	private SystemhighLogDao systemhighLogDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	public PropertyDictService getPropertyDictService() {
		return propertyDictService;
	}

	public void setPropertyDictService(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	@Autowired
	private PermissionService permissionService;

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionDubboService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public SystemhighLogDao getSystemhighLogDao() {
		return systemhighLogDao;
	}

	public void setSystemhighLogDao(SystemhighLogDao systemhighLogDao) {
		this.systemhighLogDao = systemhighLogDao;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	@Override
	public SystemhighLog addSystemhighLog(SystemhighLog systemhighLog) {

		return systemhighLogDao.addSystemhighLog(systemhighLog);
	}

	@Override
	public PageInfo<SystemhighLog> findSystemhighLogByOrgCode(String OrgCode,
			Date startDate, Date endDate, String modelName,
			Integer operationType, String operationUserName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		return systemhighLogDao.findAllSystemhighLogsForPage(OrgCode,
				startDate, endDate, modelName, operationType,
				operationUserName, pageNum, pageSize, sidx, sord);
	}

	@Override
	public PageInfo<SystemhighLog> findSystemhighLogByOrgCode(String OrgCode,
			Date startDate, Date endDate, Integer pageNum, Integer pageSize,
			String sidx, String sord) {

		return systemhighLogDao.findAllSystemhighLogsForPage(OrgCode,
				startDate, endDate, pageNum, pageSize, sidx, sord);
	}

}
