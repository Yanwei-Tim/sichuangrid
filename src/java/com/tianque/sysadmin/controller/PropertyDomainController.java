package com.tianque.sysadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDomain;
import com.tianque.sysadmin.service.PropertyDomainService;

@Transactional
@Scope("prototype")
@Controller("propertyDomainController")
public class PropertyDomainController extends BaseAction {
	@Autowired
	private PropertyDomainService propertyDomainService;

	private List<PropertyDomain> propertiesDomain;

	private PropertyDomain propertyDomain;

	private String domainName;

	public List<PropertyDomain> getPropertiesDomain() {
		return propertiesDomain;
	}

	public void setPropertiesDomain(List<PropertyDomain> propertiesDomain) {
		this.propertiesDomain = propertiesDomain;
	}

	private String userName;

	/**
	 * 查询域属性列表
	 * 
	 * @return SUCCESS
	 */
	public String findPropertyDomain() {
		propertiesDomain = propertyDomainService.findPropertyDomain();
		userName = ThreadVariable.getUser().getUserName();
		return SUCCESS;
	}

	public String findPropertyDomainBydomainName() {
		propertiesDomain = propertyDomainService
				.findPropertyDomainBydomainName(domainName, 1,
						GridProperties.ORG_TREE_AUTOCOMPLETE_SEARCH_NUM);
		return SUCCESS;
	}

	public String getPropertyDomainByDomainName() {
		propertyDomain = propertyDomainService
				.getPropertyDomainByDomainName(domainName);
		return SUCCESS;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PropertyDomain getPropertyDomain() {
		return propertyDomain;
	}

	public void setPropertyDomain(PropertyDomain propertyDomain) {
		this.propertyDomain = propertyDomain;
	}
}
