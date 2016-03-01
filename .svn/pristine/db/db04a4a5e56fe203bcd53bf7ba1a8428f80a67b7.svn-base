package com.tianque.working.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.ServiceManagement;

public interface ServiceManagementDao {
	ServiceManagement getServiceManagementById(Long id);

	ServiceManagement addServiceManagement(ServiceManagement serviceManagement);

	void deleteServiceManagementById(Long id);

	ServiceManagement updateServiceManagement(ServiceManagement serviceManagement);

	PageInfo<ServiceManagement> findServiceManagementsForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord);
}
