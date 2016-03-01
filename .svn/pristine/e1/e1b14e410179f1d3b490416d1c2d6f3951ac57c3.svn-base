package com.tianque.partyBuilding.baseInfos.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.partyBuilding.baseInfos.domain.PartyworkMembers;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;


/**
 * 
 * @author 
 * @date 2014-01-14 15:32:52
 */
@Component("partyworkMembersValidator")
public class PartyworkMembersValidator implements DomainValidator<PartyworkMembers> {
	
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
	public ValidateResult validate(PartyworkMembers domain) {
		ValidateResult validateResult = new ValidateResult();

		// TODO Auto-generated method stub
		
		return validateResult;
	}
}