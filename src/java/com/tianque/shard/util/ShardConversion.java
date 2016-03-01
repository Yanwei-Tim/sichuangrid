package com.tianque.shard.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("shardConversion")
public class ShardConversion {

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private PropertyDictService propertyDictService;

	public String getShardCode(Organization organization) {
		if (null == organization || null == organization.getId()) {
			throw new ServiceValidationException("分表参数错误！", null);
		}
		if (!StringUtil.isStringAvaliable(organization.getDepartmentNo())) {
			organization = organizationDubboService.getSimpleOrgById(organization
					.getId());
		}
		if (!StringUtil.isStringAvaliable(organization.getDepartmentNo())
				|| organization.getDepartmentNo().length() < 4) {
			throw new ServiceValidationException("分表参数错误！", null);
		}
		return organization.getDepartmentNo().substring(0, 4);
	}

	public String getShardCode(Long orgId) {
		if (null == orgId) {
			throw new ServiceValidationException("分表参数错误！", null);
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (!StringUtil.isStringAvaliable(organization.getDepartmentNo())
				|| organization.getDepartmentNo().length() < 4) {
			throw new ServiceValidationException("分表参数错误！", null);
		}
		return organization.getDepartmentNo().substring(0, 4);
	}

	public String getShardCodeByOrgCode(String orgCode) {
		if (!StringUtil.isStringAvaliable(orgCode)) {
			throw new ServiceValidationException("分表参数错误！", null);
		}
		Organization organization = organizationDubboService
				.getOrganizationByOrgInternalCode(orgCode);
		if (!StringUtil.isStringAvaliable(organization.getDepartmentNo())
				|| organization.getDepartmentNo().length() < 4) {
			throw new ServiceValidationException("分表参数错误！", null);
		}
		return organization.getDepartmentNo().substring(0, 4);
	}

	public String getShardCode(String departmentNo) {
		if (!StringUtil.isStringAvaliable(departmentNo)
				|| departmentNo.length() < 4) {
			throw new ServiceValidationException("分表参数错误！", null);
		}
		return departmentNo.substring(0, 4);
	}

	public List<String> getAllShardCode() {
		PropertyDict levelDict = propertyDictService
				.getPropertyDictByDomainName(OrganizationLevel.CITY_KEY);

		PropertyDict typeDict = propertyDictService
				.getPropertyDictByDomainName(OrganizationType.ADMINISTRATIVE_KEY);
		List<Organization> cityOrgs = organizationDubboService.getDepartmentNoByLevel(typeDict.getId(),
				levelDict.getId());
		List<String> shardList = new ArrayList<String>();
		if(cityOrgs == null || cityOrgs.size() < 1){
			return shardList;
		}
		for(Organization org : cityOrgs){
			shardList.add(org.getDepartmentNo());
		}
		return shardList;
	}
	
	public List<Organization> getAllShardCodeOrganization() {
		PropertyDict levelDict = propertyDictService
				.getPropertyDictByDomainName(OrganizationLevel.CITY_KEY);

		PropertyDict typeDict = propertyDictService
				.getPropertyDictByDomainName(OrganizationType.ADMINISTRATIVE_KEY);
		return organizationDubboService.getDepartmentNoByLevel(typeDict.getId(),
				levelDict.getId());
	}

	/**
	 * 判断组织机构是否高于市层级
	 * 
	 * @param organization
	 * @return
	 */
	public boolean isOverCity(Organization organization) {
		PropertyDict orgLevel = null;
		if (organization.getOrgLevel() != null
				&& organization.getOrgLevel().getId() != null) {
			orgLevel = propertyDictService.getPropertyDictById(organization
					.getOrgLevel().getId());
		} else {
			Organization tempOrganization = organizationDubboService
					.getSimpleOrgById(organization.getId());
			orgLevel = propertyDictService.getPropertyDictById(tempOrganization
					.getOrgLevel().getId());
		}
		return orgLevel.getInternalId() > OrganizationLevel.CITY;
	}
}
