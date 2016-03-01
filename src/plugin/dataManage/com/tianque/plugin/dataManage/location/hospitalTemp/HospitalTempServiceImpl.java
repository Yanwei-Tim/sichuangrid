package com.tianque.plugin.dataManage.location.hospitalTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.Hospital;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.hospitalTemp.domain.HospitalTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("hospitalTempService")
public class HospitalTempServiceImpl extends LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		HospitalTemp temp = (HospitalTemp) population;

		Hospital domain = new Hospital();

		String[] exceptArgs = { "name", "address", "affiliatedUnit",
				"telephone", "mobileNumber", "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getName())) {
			domain.setHospitalName(temp.getName());
		}

		if (!validateHelper.emptyString(temp.getAddress())) {
			domain.setAddress(temp.getAddress());
		}

		if (!validateHelper.emptyString(temp.getAffiliatedUnit())) {
			domain.setAffiliatedUnit(temp.getAffiliatedUnit());
		}

		if (!validateHelper.emptyString(temp.getTelephone())) {
			domain.setTelephone(temp.getTelephone());
		}
		if (!validateHelper.emptyString(temp.getMobileNumber())) {
			domain.setMobileNumber(temp.getMobileNumber());
		}

		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}
}
