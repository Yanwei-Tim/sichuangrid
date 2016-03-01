package com.tianque.datatransfer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.tianque.controller.ControllerHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public class DataExportHelper {

	private PropertyDictService propertyDictService;
	private OrganizationDubboService organizationDubboService;
	private Map<Long, String> propertyNames = new HashMap<Long, String>();
	private Map<Long, String> orgRelativeNames = new HashMap<Long, String>();
	private ApplicationContext context;

	public void setPropertyDictService(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	public void setOrganizationService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public String getPropertyDictDisplayName(String propertyDomainName,
			Long dictId) {
		if (propertyDictService == null)
			throw new IllegalStateException("尚未设置相应的数据字典工具");
		if (dictId == null)
			return "";
		if (propertyNames.containsKey(dictId)) {
			return propertyNames.get(dictId);
		} else {
			String result = propertyDictService.getPropertyDictById(dictId)
					.getDisplayName();
			propertyNames.put(dictId, result);
			return result;
		}
	}

	public String getOrganizationRelativeName(Long orgId) {
		if (organizationDubboService == null)
			throw new IllegalStateException("尚未设置相应的网格工具");
		if (orgId == null)
			return "";
		if (orgRelativeNames.containsKey(orgId)) {
			return orgRelativeNames.get(orgId);
		} else {
			if (context != null) {
				String result = ControllerHelper.getRelativeOrgNameByOrgIdTemp(
						orgId, organizationDubboService, context);
				orgRelativeNames.put(orgId, result);
				return result;
			} else {
				String result = ControllerHelper.getRelativeOrgNameByOrgId(
						orgId, organizationDubboService);
				orgRelativeNames.put(orgId, result);
				return result;
			}
		}
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
