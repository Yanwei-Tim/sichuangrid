package com.tianque.facade.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName:Facade
 */
@Component("organizationServiceFacade")
public class OrganizationServiceFacade {
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public Long getDistrictorgId(Long orgId) {

		Organization org = organizationDubboService
				.getDistrictOrganizationId(orgId);
		return org.getId();

	}

}
