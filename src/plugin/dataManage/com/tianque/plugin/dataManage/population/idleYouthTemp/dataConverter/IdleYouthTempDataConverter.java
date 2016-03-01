package com.tianque.plugin.dataManage.population.idleYouthTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.population.idleYouthTemp.domain.IdleYouthTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

@Component("idleYouthTemp")
public class IdleYouthTempDataConverter extends
		AbstractTempDataConverter<IdleYouthTemp> {
	@Autowired
	@Resource(name = "idleYouthTempService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "idleYouthTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}

	@Override
	public IdleYouthTemp persistenceDomain(IdleYouthTemp domain) {
		if (domain.isStayInSchool() == false)
			domain.setSchoolName(null);
		return super.persistenceDomain(domain);
	}
}
