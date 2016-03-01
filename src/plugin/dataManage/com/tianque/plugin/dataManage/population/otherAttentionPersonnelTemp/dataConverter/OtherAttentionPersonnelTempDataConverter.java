package com.tianque.plugin.dataManage.population.otherAttentionPersonnelTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.otherAttentionPersonnelTemp.domain.OtherAttentionPersonnelTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("otherAttentionPersonnelTemp")
public class OtherAttentionPersonnelTempDataConverter extends
		AbstractTempDataConverter<OtherAttentionPersonnelTemp> {

	@Autowired
	@Resource(name = "businessPopulationDataManageService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "otherAttentionPersonnelTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
