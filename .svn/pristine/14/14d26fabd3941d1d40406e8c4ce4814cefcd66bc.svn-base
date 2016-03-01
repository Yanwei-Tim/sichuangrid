package com.tianque.jms.util;

import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.BaseMsg;
import com.tianque.jms.msg.PopulationMsg;

public class TransferData {

	public static BaseMsg convertToBaseMsg(Object object, String type,
			OperateMode mode) {
		if (object instanceof HouseholdStaff) {
			return createPopulationMsg((HouseholdStaff) object, type, mode);
		} else if (object instanceof FloatingPopulation) {
			return createPopulationMsg((FloatingPopulation) object, type, mode);
		}
		return new BaseMsg();
	}

	private static PopulationMsg createPopulationMsg(
			HouseholdStaff householdStaff, String type, OperateMode mode) {
		PopulationMsg populationMsg = new PopulationMsg();
		populationMsg.setOrgId(householdStaff.getOrganization().getId());
		populationMsg.setIdCardNo(householdStaff.getIdCardNo());
		populationMsg.setType(type);
		populationMsg.setMode(mode);
		populationMsg.setBaseinfoId(householdStaff.getBaseInfoId());
		populationMsg.setAddressId(householdStaff.getAddressInfoId());
		populationMsg.setObjectId(householdStaff.getId());
		populationMsg.setObjectType(BaseInfoTables.HOUSEHOLDSTAFF_KEY);
		populationMsg.setCreateUser(householdStaff.getCreateUser());
		return populationMsg;
	}

	private static PopulationMsg createPopulationMsg(
			FloatingPopulation floatingPopulation, String type, OperateMode mode) {
		PopulationMsg populationMsg = new PopulationMsg();
		populationMsg.setOrgId(floatingPopulation.getOrganization().getId());
		populationMsg.setIdCardNo(floatingPopulation.getIdCardNo());
		populationMsg.setType(type);
		populationMsg.setMode(mode);
		populationMsg.setBaseinfoId(floatingPopulation.getBaseInfoId());
		populationMsg.setAddressId(floatingPopulation.getAddressInfoId());
		populationMsg.setObjectId(floatingPopulation.getId());
		populationMsg.setObjectType(BaseInfoTables.FLOATINGPOPULATION_KEY);
		populationMsg.setCreateUser(floatingPopulation.getCreateUser());
		return populationMsg;
	}
}
