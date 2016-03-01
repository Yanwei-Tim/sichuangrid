package com.tianque.sysadmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.PropertyDomain;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

@Service("propertyDomainService")
public class PropertyDomainServiceImpl implements PropertyDomainService {

	@Autowired
	private PropertyDomainDubboService propertyDomainDubboService;
	@Autowired
	private CacheService cacheService;

	@Override
	public PropertyDomain addPropertyDomain(PropertyDomain propertyDomain) {
		return propertyDomainDubboService.addPropertyDomain(propertyDomain);
	}

	@Override
	public List<PropertyDomain> findPropertyDomain() {
		return propertyDomainDubboService.findPropertyDomain();
	}

	@Override
	public PropertyDomain getPropertyDomainById(Long id) {
		return propertyDomainDubboService.getPropertyDomainById(id);
	}

	@Override
	public PropertyDomain getPropertyDomainByDomainName(String domainName) {
		String key = MemCacheConstant.getPropertyDomainKey(
				MemCacheConstant.PROPERTYDOMAIN_KEY,
				MemCacheConstant.PROPERTYDOMAIN_DOMAINNAME_KEY, null,
				domainName);
		PropertyDomain propertyDomain = null;
		if (StringUtil.isStringAvaliable(key)) {
			propertyDomain = (PropertyDomain) cacheService.get(
					MemCacheConstant.PROPERTYDOMAIN_NAMESPACE, key);
		}

		if (null == propertyDomain) {
			propertyDomain = propertyDomainDubboService
					.getPropertyDomainByDomainName(domainName);
		}
		return propertyDomain;
	}

	@Override
	public List<PropertyDomain> findPropertyDomainBydomainName(
			String domainName, int pageNum, int pageSize) {
		return propertyDomainDubboService.findPropertyDomainBydomainName(
				domainName, pageNum, pageSize);
	}

}
