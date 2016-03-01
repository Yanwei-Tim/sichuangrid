package com.tianque.plugin.dataManage.population.dangerousGoodsPractitionerTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.dangerousGoodsPractitionerTemp.domain.DangerousGoodsPractitionerTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("dangerousGoodsPractitionerTemp")
public class DangerousGoodsPractitionerTempDataConverter extends
		AbstractTempDataConverter<DangerousGoodsPractitionerTemp> {
	@Autowired
	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "dangerousGoodsPractitionerTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
