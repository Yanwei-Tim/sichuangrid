package com.tianque.plugin.dataManage.location.safetyProductionTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.safetyProductionTemp.domain.SafetyProductionTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("safetyProductionTempConverter")
public class SafetyProductionTempDataConverter extends
		AbstractTempDataConverter<SafetyProductionTemp> {
	@Autowired
	@Resource(name = "locationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "safetyProductionTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
