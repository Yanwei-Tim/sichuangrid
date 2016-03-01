package com.tianque.plugin.dataManage.location.fireSafetyEnterpriseTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.FireSafetyEnterprise;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.fireSafetyEnterpriseTemp.domain.FireSafetyEnterpriseTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("fireSafetyEnterpriseTempService")
public class FireSafetyEnterpriseTempServiceImpl extends
		LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		FireSafetyEnterpriseTemp temp = (FireSafetyEnterpriseTemp) population;

		FireSafetyEnterprise domain = new FireSafetyEnterprise();

		String[] exceptArgs = { "manager", "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getManager())) {
			domain.setLegalPerson(temp.getManager());
		}

		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}
}
