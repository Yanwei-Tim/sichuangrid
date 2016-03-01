package com.tianque.plugin.dataManage.location.newSocietyOrganizationsTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.newSocietyOrganizationsTemp.domain.NewSocietyOrganizationsTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("newSocietyOrganizationsTempConverter")
public class NewSocietyOrganizationsTempDataConverter extends
		AbstractTempDataConverter<NewSocietyOrganizationsTemp> {
	@Autowired
	@Resource(name = "locationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "newSocietyOrganizationsTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
