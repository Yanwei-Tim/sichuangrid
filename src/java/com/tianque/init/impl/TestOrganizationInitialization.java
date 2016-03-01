package com.tianque.init.impl;

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

/**
 * 为测试人员添加每个层级组织机构，方便测试
 */
public class TestOrganizationInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private OrganizationDubboService organizationDubboService;
	private PropertyDictService propertyDictService;

	public TestOrganizationInitialization(OrganizationDubboService organizationDubboService,
			PropertyDictService propertyDictService) {
		this.organizationDubboService = organizationDubboService;
		this.propertyDictService = propertyDictService;
		logger.info("为测试创建 OrganizationInitialization 对象");
	}

	@Override
	public void init() {
		logger.info("开始创建责任区!");
		Organization organizationDistrict1 = getOraganizationById(2L);
		Organization organizationDistrict2 = getOraganizationById(3L);
		Organization organizationDistrict3 = getOraganizationById(4L);
		Organization organizationDistrict4 = getOraganizationById(5L);
		Organization organizationTown1 = addOrganization("一镇;1;234567",
				organizationDistrict1.getId(), 6L, OrganizationLevel.TOWN);
		Organization organizationVillage1 = addOrganization("一村;1;345678",
				organizationTown1.getId(), 7L, OrganizationLevel.VILLAGE);
		addOrganization("网格一;1;456789", organizationVillage1.getId(), 8L, OrganizationLevel.GRID);

		Organization organizationTown2 = addOrganization("二街道;1;234567",
				organizationDistrict2.getId(), 9L, OrganizationLevel.TOWN);
		Organization organizationVillage2 = addOrganization("二社区;1;345678",
				organizationTown2.getId(), 10L, OrganizationLevel.VILLAGE);
		addOrganization("网格二;1;456789", organizationVillage2.getId(), 11L, OrganizationLevel.GRID);

		Organization organizationTown3 = addOrganization("三镇;1;234567",
				organizationDistrict3.getId(), 12L, OrganizationLevel.TOWN);
		Organization organizationVillage3 = addOrganization("三村;1;345678",
				organizationTown3.getId(), 13L, OrganizationLevel.VILLAGE);
		addOrganization("网格三;1;456789", organizationVillage3.getId(), 114L, OrganizationLevel.GRID);

		Organization organizationTown4 = addOrganization("四镇;1;234567",
				organizationDistrict4.getId(), 15L, OrganizationLevel.TOWN);
		Organization organizationVillage4 = addOrganization("四村;1;345678",
				organizationTown4.getId(), 16L, OrganizationLevel.VILLAGE);
		addOrganization("网格四;1;456789", organizationVillage4.getId(), 17L, OrganizationLevel.GRID);
		logger.info("默认测试责任区创建完成!");
	}

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

	private Organization getOraganizationById(Long id) {
		return organizationDubboService.getSimpleOrgById(id);
	}
}