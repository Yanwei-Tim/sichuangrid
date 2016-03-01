package com.tianque.plugin.dataManage.population.handicappedTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.handicappedTemp.domain.HandicappedTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("handicappedTemp")
public class HandicappedTempDataConverter extends AbstractTempDataConverter<HandicappedTemp> {
	@Autowired
	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "handicappedTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
