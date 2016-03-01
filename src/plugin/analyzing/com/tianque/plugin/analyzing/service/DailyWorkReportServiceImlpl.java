package com.tianque.plugin.analyzing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analyzing.domain.DailyWork;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Service("dailyWorkReportService")
public class DailyWorkReportServiceImlpl implements DailyWorkReportService {
	// 四川省的code原因：这个报表无论什么用户进来，只展示四川的市州组织机构数据
	public static final String SICHUAN_PROVIENCE_CODE = ".1.";

	@Autowired
	private OrganizationDubboRemoteService organizationService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public List<DailyWork> getDailyWorkReportForList() {
		// 获得市层级
		Long orgLevel = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.CITY_KEY).getId();
		// 获得行政部门type
		Long orgType = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_KEY).getId();
		List<Organization> list = organizationService
				.getAdministrativeOrgByLevelAndType(SICHUAN_PROVIENCE_CODE,
						orgLevel, orgType);
		if (list == null || list.size() == 0) {
			throw new BusinessValidationException("报表统计出错，未获得正确的市州层级组织机构数据");
		}
		List<DailyWork> listData = new ArrayList<DailyWork>();
		for (Organization organization : list) {
			DailyWork dailyWork = new DailyWork();
			dailyWork.setOrganization(organization);
			listData.add(dailyWork);
		}
		return listData;
	}
}
