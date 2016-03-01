package com.tianque.plugin.transfer.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.RelationShipWithHead;
import com.tianque.plugin.transfer.util.Constants;
import com.tianque.plugin.transfer.util.Context;
import com.tianque.service.CensusRegisterFamilyService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("censusRegisterFamilyHandler")
public class CensusRegisterFamilyHandler extends Handler {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CensusRegisterFamilyService censusRegisterFamilyService;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public void invoke(String type, Long id, Long toOrgId, Context context) {
		if (!checkInvoke(type, id, toOrgId, context)) {
			return;
		}
		if (context.isExistedToOrgId()) {
			return;
		}
		Map<String, Object> oldMap = context.getOldMap();
		Map<String, Object> newMap = context.getNewMap();
		// 当原有户籍家庭存在时，业务继续
		HouseholdStaff oldHouseholdStaff = (HouseholdStaff) oldMap
				.get(Constants.HOUSEHOLDSTAFF_KEY);
		CensusRegisterFamily oldCensusRegisterFamily = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(
						oldHouseholdStaff.getAccountNumber(), oldHouseholdStaff
								.getOrganization().getId());
		HouseholdStaff newHouseholdStaff = (HouseholdStaff) newMap
				.get(Constants.HOUSEHOLDSTAFF_KEY);
		newHouseholdStaff
				.setAccountNumber(oldHouseholdStaff.getAccountNumber());
		if (oldCensusRegisterFamily == null
				|| oldCensusRegisterFamily.getAccountNumber() == null) {
			return;
		}
		// 如果老的编号在新的网格不存在，那么添加一条家庭；反之不做操作
		householdStaffService
				.transferHousePopulationBusinessInfo(newHouseholdStaff);
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(oldHouseholdStaff
						.getRelationShipWithHead().getId());
		if (propertyDict.getInternalId() == RelationShipWithHead.HEADER) {
			householdStaffService
					.emptyCensusRegisterFamily(oldCensusRegisterFamily);
		}
	}

	private boolean checkInvoke(String type, Long id, Long toOrgId,
			Context context) {
		Map<String, Object> oldMap = context.getOldMap();
		Map<String, Object> newMap = context.getNewMap();
		if (oldMap.get(Constants.HOUSEHOLDSTAFF_KEY) == null) {
			return false;
		}
		if (newMap.get(Constants.HOUSEHOLDSTAFF_KEY) == null) {
			return false;
		}
		Organization newOrganization = organizationDubboService
				.getFullOrgById(toOrgId);
		if (newOrganization == null) {
			return false;
		}
		return true;
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Context context) {
	}

	@Override
	public void validate(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {

	}

	@Override
	public void invoke(String type, Long id, Long toOrgId, Long fromOrgId,
			Context context) {
		invoke(type, id, toOrgId, context);
	}
}
