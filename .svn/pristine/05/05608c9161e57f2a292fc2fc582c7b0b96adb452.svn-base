package com.tianque.plugin.dataManage.population.floatingPopulationTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.floatingPopulationTemp.domain.FloatingPopulationTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("floatingPopulationTemp")
public class FloatingPopulationTempDataConverter extends
		AbstractTempDataConverter<FloatingPopulationTemp> {

	@Autowired
	@Resource(name = "actualPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "floatingPopulationTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}

}
