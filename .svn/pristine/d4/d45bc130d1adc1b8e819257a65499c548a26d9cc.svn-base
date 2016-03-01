package com.tianque.plugin.dataManage.location.fireSafetyEnterpriseTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.fireSafetyEnterpriseTemp.domain.FireSafetyEnterpriseTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("fireSafetyEnterpriseTempConverter")
public class FireSafetyEnterpriseTempDataConverter extends
		AbstractTempDataConverter<FireSafetyEnterpriseTemp> {
	@Autowired
	@Resource(name = "locationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "fireSafetyEnterpriseTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
