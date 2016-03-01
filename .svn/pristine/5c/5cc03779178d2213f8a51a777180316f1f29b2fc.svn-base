package com.tianque.init.product.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.init.Initialization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public class WenzhouOrganizationInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private OrganizationDubboService organizationDubboService;
	private PropertyDictService propertyDictService;

	public WenzhouOrganizationInitialization(OrganizationDubboService organizationDubboService,
			PropertyDictService propertyDictService) {
		this.organizationDubboService = organizationDubboService;
		this.propertyDictService = propertyDictService;
	}

	@Override
	public void init() {
		// Organization organization=
		addOrganization("全国;1;000000", null, 1L, OrganizationLevel.COUNTRY);
		logger.info("默认责任区创建完成!");
	}

	/**
	 * str: orgName;orgType;contactWay;
	 * 
	 * @param str
	 * @param parentId
	 * @return
	 */
	private Organization addOrganization(String str, Long parentId, Long seq, int orgLevelInternalId) {
		String[] orgValues = str.split(";");
		Organization organization = new Organization();
		organization.setOrgName(orgValues[0]);
		organization.setContactWay(orgValues[2]);
		organization.setCreateUser("超级管理员");
		List<PropertyDict> orgTypes = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION);
		organization.setOrgType(orgTypes.get(0));
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(OrganizationLevel.ORG_LEVEL_KEY,
						orgLevelInternalId);
		organization.setOrgLevel(orgLevels.get(0));
		if (parentId != null) {
			Organization parentOrg = new Organization();
			parentOrg.setId(parentId);
			organization.setParentOrg(parentOrg);
		}

		organization = organizationDubboService.addOrganization(organization);
		return organization;
	}

}
