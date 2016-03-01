package com.tianque.plugin.dataManage.population.unemployedPeopleTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.unemployedPeopleTemp.domain.UnemployedPeopleTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("unemployedPeopleTemp")
public class UnemployedPeopleTempDataConverter extends
		AbstractTempDataConverter<UnemployedPeopleTemp> {
	@Autowired
	@Resource(name = "unemployedPeopleTempService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "unemployedPeopleTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
