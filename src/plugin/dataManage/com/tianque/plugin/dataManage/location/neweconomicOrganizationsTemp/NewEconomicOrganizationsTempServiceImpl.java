package com.tianque.plugin.dataManage.location.neweconomicOrganizationsTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.neweconomicOrganizationsTemp.domain.NewEconomicOrganizationsTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("newEconomicOrganizationsTempService")
public class NewEconomicOrganizationsTempServiceImpl extends
		LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		NewEconomicOrganizationsTemp temp = (NewEconomicOrganizationsTemp) population;

		NewEconomicOrganizations domain = new NewEconomicOrganizations();

		String[] exceptArgs = { "address", "type", "manager", "partyNum",
				"claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getAddress())) {
			domain.setResidence(temp.getAddress());
		}
		if (!validateHelper.nullObj(temp.getType())) {
			domain.setStyle(temp.getType());
		}
		if (!validateHelper.emptyString(temp.getManager())) {
			domain.setChief(temp.getManager());
		}
		if (!validateHelper.emptyString(temp.getPartyNum() + "")) {
			domain.setPartyMemberNumber(temp.getPartyNum());
		}
		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}

}
