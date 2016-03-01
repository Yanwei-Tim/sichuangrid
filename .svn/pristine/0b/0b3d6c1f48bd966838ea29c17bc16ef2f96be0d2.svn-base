package com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.dangerousChemicalsUnitTemp.domain.DangerousChemicalsUnitTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

/*
 * 危险化学品单位
 */
@Component("dangerousChemicalsUnitTempConverter")
public class DangerousChemicalsUnitTempConvert extends
		AbstractTempDataConverter<DangerousChemicalsUnitTemp> {
	@Autowired
	@Resource(name = "locationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "dangerousChemicalsUnitTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
