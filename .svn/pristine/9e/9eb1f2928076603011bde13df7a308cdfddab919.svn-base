package com.tianque.partyBuilding.baseInfos.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.partyBuilding.baseInfos.domain.DistrictBasiccase;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;


/**
 * 
 * @author 
 * @date 2014-01-14 15:24:54
 */
@Component("districtBasiccaseValidator")
public class DistrictBasiccaseValidator implements DomainValidator<DistrictBasiccase> {
	
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	private boolean orgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

	private boolean orgIfExsist(Organization org) {
		Organization organization = organizationDubboService.getSimpleOrgById(org.getId());
		if (organization == null) {
			return false;
		}
		return true;
	}

	private boolean orgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	@Override
	public ValidateResult validate(DistrictBasiccase domain) {
		ValidateResult validateResult = new ValidateResult();

		// TODO Auto-generated method stub
		
		return validateResult;
	}
}