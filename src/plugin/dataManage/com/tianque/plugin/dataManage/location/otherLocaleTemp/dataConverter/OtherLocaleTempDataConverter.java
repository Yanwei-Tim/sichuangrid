package com.tianque.plugin.dataManage.location.otherLocaleTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.location.otherLocaleTemp.domain.OtherLocaleTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("otherLocaleTempConverter")
public class OtherLocaleTempDataConverter extends AbstractTempDataConverter<OtherLocaleTemp> {
	@Autowired
	@Resource(name = "locationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "otherLocaleTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
