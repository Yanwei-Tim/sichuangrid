package com.tianque.plugin.dataManage.location.newSocietyOrganizationsTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.newSocietyOrganizationsTemp.domain.NewSocietyOrganizationsTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("newSocietyOrganizationsTempService")
public class NewSocietyOrganizationsTempServiceImpl extends
		LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		NewSocietyOrganizationsTemp temp = (NewSocietyOrganizationsTemp) population;

		NewSocietyOrganizations domain = new NewSocietyOrganizations();

		String[] exceptArgs = { "manager", "telephone", "mobileNumber",
				"partyNum", "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getManager())) {
			domain.setLegalPerson(temp.getManager());
		}

		if (!validateHelper.emptyString(temp.getTelephone())) {
			domain.setLegalPersonTelephone(temp.getTelephone());
		}

		if (!validateHelper.emptyString(temp.getMobileNumber())) {
			domain.setLegalPersonMobileNumber(temp.getMobileNumber());
		}
		if (!validateHelper.emptyString(temp.getPartyNum() == null ? ""
				: String.valueOf(temp.getPartyNum()))) {
			domain.setPartyNum(new Long((long) temp.getPartyNum()));
		}
		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}
}
