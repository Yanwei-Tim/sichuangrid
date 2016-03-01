package com.tianque.plugin.dataManage.location.otherLocaleTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.OtherLocale;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.otherLocaleTemp.domain.OtherLocaleTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("otherLocaleTempService")
public class OtherLocaleTempServiceImpl extends LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		OtherLocaleTemp temp = (OtherLocaleTemp) population;

		OtherLocale domain = new OtherLocale();

		String[] exceptArgs = { "manager", "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getManager())) {
			domain.setContactPerson(temp.getManager());
		}

		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}

}
