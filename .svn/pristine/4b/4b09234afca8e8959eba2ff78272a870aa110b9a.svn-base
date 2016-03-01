package com.tianque.plugin.dataManage.population.mentalPatientTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.mentalPatientTemp.domain.MentalPatientTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("mentalPatientTemp")
public class MentalPatientTempDataConverter extends AbstractTempDataConverter<MentalPatientTemp> {
	@Autowired
	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "mentalPatientTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
