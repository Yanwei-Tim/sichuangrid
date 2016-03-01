package com.tianque.plugin.dataManage.location.schoolTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.School;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.schoolTemp.domain.SchoolTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("schoolTempService")
public class SchoolTempServiceImpl extends LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		SchoolTemp temp = (SchoolTemp) population;

		School domain = new School();

		String[] exceptArgs = { "name", "manager", "vicePresident",
				"telephone", "mobileNumber", "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getName())) {
			domain.setChineseName(temp.getName());
		}

		if (!validateHelper.emptyString(temp.getManager())) {
			domain.setPresident(temp.getManager());
		}

		if (!validateHelper.emptyString(temp.getVicePresident())) {
			domain.setPersonLiable(temp.getVicePresident());
		}

		if (!validateHelper.emptyString(temp.getTelephone())) {
			domain.setPersonLiableTelephone(temp.getTelephone());
		}
		if (!validateHelper.emptyString(temp.getMobileNumber())) {
			domain.setPersonLiableMobileNumber(temp.getMobileNumber());
		}

		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}
}
