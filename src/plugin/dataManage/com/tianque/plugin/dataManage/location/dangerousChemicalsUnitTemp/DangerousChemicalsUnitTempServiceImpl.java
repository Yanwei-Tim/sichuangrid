package com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp.domain.DangerousChemicalsUnitTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("dangerousChemicalsUnitTempService")
public class DangerousChemicalsUnitTempServiceImpl extends
		LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		DangerousChemicalsUnitTemp temp = (DangerousChemicalsUnitTemp) population;

		DangerousChemicalsUnit domain = new DangerousChemicalsUnit();

		String[] exceptArgs = { "name", "address", "manager", "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getName())) {
			domain.setUnitName(temp.getName());
		}

		if (!validateHelper.emptyString(temp.getAddress())) {
			domain.setUnitAddress(temp.getAddress());
		}
		if (!validateHelper.emptyString(temp.getManager())) {
			domain.setSuperintendent(temp.getManager());
		}

		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}

}
