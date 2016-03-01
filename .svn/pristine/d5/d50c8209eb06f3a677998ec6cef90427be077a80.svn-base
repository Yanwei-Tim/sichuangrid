package com.tianque.plugin.dataManage.population.unsettledPopulationTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.unsettledPopulationTemp.domain.UnsettledPopulationTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("unsettledPopulationTempConverter")
public class UnsettledPopulationTempDataConverter extends
		AbstractTempDataConverter<UnsettledPopulationTemp> {
	@Autowired
	@Resource(name = "actualPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "unsettledPopulationTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
