package com.tianque.plugin.dataManage.population.householdStaffTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.householdStaffTemp.domain.HouseholdStaffTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("householdStaffTemp")
public class HouseholdStaffTempDataConverter extends AbstractTempDataConverter<HouseholdStaffTemp> {

	@Autowired
	@Resource(name = "actualPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "householdStaffTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}

}
