package com.tianque.working.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.DailyDirectoryTypes;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.dao.ServiceManagementDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.ServiceManagement;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.ServiceManagementService;

@Service("serviceManagementService")
public class ServiceManagementServiceImpl implements ServiceManagementService {

	@Autowired
	private ServiceManagementDao serviceManagementDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	@Override
	public ServiceManagement getServiceManagementById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		return serviceManagementDao.getServiceManagementById(id);
	}

	@Override
	public ServiceManagement addServiceManagement(
			ServiceManagement serviceManagement) {
		if (serviceManagement == null) {
			throw new BusinessValidationException("参数错误");
		}
		serviceManagement.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(serviceManagement.getOrganization().getId())
				.getOrgInternalCode());
		return serviceManagementDao.addServiceManagement(serviceManagement);
	}

	@Override
	public void deleteServiceManagementById(Long id) {
		if (validateId(id)) {
			throw new BusinessValidationException("参数错误");
		}
		serviceManagementDao.deleteServiceManagementById(id);
	}

	@Override
	public ServiceManagement updateServiceManagement(
			ServiceManagement serviceManagement) {
		if (serviceManagement == null || serviceManagement.getId() == null
				|| serviceManagement.getId().intValue() == 0) {
			throw new BusinessValidationException("参数错误");
		}
		return serviceManagementDao.updateServiceManagement(serviceManagement);
	}

	@Override
	public PageInfo<ServiceManagement> findServiceManagementsForPageByOrgIdAndDailyDirectoryId(
			Long dailyDirectoryId, Long orgId, Integer page, Integer rows,
			String sidx, String sord) {
		if (orgId == null || dailyDirectoryId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return appendAttachFile(
				serviceManagementDao.findServiceManagementsForPageByOrgIdAndDailyDirectoryId(
						buildDailyDirectoryIds(dailyDirectoryId), orgId, page,
						rows, sidx, sord), orgId);
	}

	private String buildDailyDirectoryIds(Long dailyDirectoryId) {
		String allDailyDirectoryId = "";
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(dailyDirectoryId);
		if (dailyDirectories.size() == 0) {
			allDailyDirectoryId = "'" + dailyDirectoryId + "'";
		} else {
			for (int i = 0; i < dailyDirectories.size(); i++) {
				if (i == 0) {
					allDailyDirectoryId = "'" + dailyDirectories.get(i).getId()
							+ "'";
				} else {
					allDailyDirectoryId = allDailyDirectoryId + ",'"
							+ dailyDirectories.get(i).getId() + "'";
				}
			}
		}
		return allDailyDirectoryId;
	}

	@Override
	public Long getDailyDirectoryType(Long orgId) {
		return propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						DailyDirectoryTypes.SERVICE_MANAGEMENT_NAME,
						createDailyDirectoryTypeName(orgId)).getId();
	}

	@Override
	public int checkCurrentOrgLevel(Long orgId) {
		Organization org = organizationDubboService.getFullOrgById(orgId);
		PropertyDict orgLevel = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.ORGANIZATION_LEVEL, org.getOrgLevel()
								.getId());
		return orgLevel.getInternalId();
	}

	private String createDailyDirectoryTypeName(Long orgId) {
		String dailyDirectoryTypeName = DailyDirectoryTypes.SERVICE_MANAGEMENT_CITY_NAME;
		int orgLevel = checkCurrentOrgLevel(orgId);
		switch (orgLevel) {
		case OrganizationLevel.TOWN:
			dailyDirectoryTypeName = DailyDirectoryTypes.SERVICE_MANAGEMENT_TOWN_NAME;
			break;
		case OrganizationLevel.VILLAGE:
			dailyDirectoryTypeName = DailyDirectoryTypes.SERVICE_MANAGEMENT_VILLAGE_NAME;
			break;
		}
		return dailyDirectoryTypeName;
	}

	private PageInfo<ServiceManagement> appendAttachFile(
			PageInfo<ServiceManagement> pageInfo, Long orgId) {
		for (ServiceManagement serviceManagement : pageInfo.getResult()) {
			serviceManagement.setDailyAttachFiles(dailyAttachFileService
					.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
							serviceManagement.getId(),
							getDailyDirectoryType(orgId)));
		}
		return pageInfo;

	}

	private boolean validateId(Long id) {
		return id == null || id.intValue() == 0;
	}

}
