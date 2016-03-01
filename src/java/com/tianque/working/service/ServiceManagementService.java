package com.tianque.working.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.ServiceManagement;

public interface ServiceManagementService {
	public ServiceManagement getServiceManagementById(Long id);

	public ServiceManagement addServiceManagement(ServiceManagement serviceManagement);

	public void deleteServiceManagementById(Long id);

	public ServiceManagement updateServiceManagement(ServiceManagement serviceManagement);

	public PageInfo<ServiceManagement> findServiceManagementsForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows, String sidx, String sord);

	public Long getDailyDirectoryType(Long orgId);

	public int checkCurrentOrgLevel(Long orgId);
}