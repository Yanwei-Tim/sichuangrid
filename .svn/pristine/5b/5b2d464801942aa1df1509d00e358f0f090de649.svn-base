package com.tianque.plugin.dataManage.location.actualCompanyTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.actualCompanyTemp.domain.ActualCompanyTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("actualCompanyTempService")
public class ActualCompanyTempServiceImpl extends LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		ActualCompanyTemp temp = (ActualCompanyTemp) population;

		ActualCompany domain = new ActualCompany();

		String[] exceptArgs = { "name", "address", "manager", "fax",
				"claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getName())) {
			domain.setCompanyName(temp.getName());
		}

		if (!validateHelper.emptyString(temp.getAddress())) {
			domain.setCompanyAddress(temp.getAddress());
		}
		if (!validateHelper.emptyString(temp.getManager())) {
			domain.setCorporateRepresentative(temp.getManager());
		}

		if (!validateHelper.emptyString(temp.getFax())) {
			domain.setFacsimile(temp.getFax());
		}
		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}

}
