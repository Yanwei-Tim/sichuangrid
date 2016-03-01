package com.tianque.plugin.dataManage.location.rentalHouseTemp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.plugin.dataManage.base.service.LocationDataManageServiceImpl;
import com.tianque.plugin.dataManage.location.rentalHouseTemp.domain.RentalHouseTemp;
import com.tianque.plugin.dataManage.util.DataManageConvertUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("rentalHouseTempService")
public class RentalHouseTempServiceImpl extends LocationDataManageServiceImpl {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {

		RentalHouseTemp temp = (RentalHouseTemp) population;

		RentalHouse domain = new RentalHouse();

		String[] exceptArgs = { "manager", "claimDetail" };

		try {
			DataManageConvertUtil.convert(temp.getClass(), domain, temp,
					exceptArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!validateHelper.emptyString(temp.getManager())) {
			domain.setHouseOwner(temp.getManager());
		}

		domain.setOrganization(organizationDubboService.getSimpleOrgById(domain
				.getOrganization().getId()));

		if (domain.getAddressType() == null
				|| propertyDictService.getPropertyDictById(
						domain.getAddressType().getId()).getInternalId() == CurrentAddressType.OTHER) {
			domain.setAddressType(propertyDictService
					.getPropertyDictById((long) CurrentAddressType.OTHER));
		}

		return domain;
	}
}
