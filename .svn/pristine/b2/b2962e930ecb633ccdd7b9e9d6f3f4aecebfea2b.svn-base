package com.tianque.plugin.dataManage.location.publicPlaceTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.publicPlaceTemp.domain.PublicPlaceTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("publicPlaceTempService")
public class PublicPlaceTempServiceImpl extends LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		PublicPlaceTemp temp = (PublicPlaceTemp) population;

		PublicPlace domain = new PublicPlace();

		String[] exceptArgs = { "name", "address", "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getName())) {
			domain.setPlaceName(temp.getName());
		}

		if (!validateHelper.emptyString(temp.getAddress())) {
			domain.setPlaceAddress(temp.getAddress());
		}
		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));
		return domain;
	}
}
