package com.tianque.init.impl.project;

import com.tianque.core.util.FileUtil;
import com.tianque.init.OrganizationExcelInit;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public class YFDOrganizationExcelInit extends OrganizationExcelInit {
	public YFDOrganizationExcelInit(PropertyDictService propertyDictService,
			OrganizationDubboService organizationDubboService, boolean isProvinceInit) {
		super(propertyDictService, organizationDubboService, isProvinceInit);
	}

	public String getDevelopOrgPath() {
		return FileUtil.getWebRoot() + "/WEB-INF/classes/organizationConfig-development-yfd.xls";
	}
}
