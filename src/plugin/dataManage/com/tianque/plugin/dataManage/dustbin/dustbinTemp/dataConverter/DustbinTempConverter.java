package com.tianque.plugin.dataManage.dustbin.dustbinTemp.dataConverter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.dustbin.dustbinTemp.domain.DustbinTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.plugin.datatransfer.dataconvert.AbstractTempDataConverter;

/**
 * 数据管理部件信息导入数据
 */
@Component("dustbinTempConverter")
public class DustbinTempConverter extends
		AbstractTempDataConverter<DustbinTemp> {
	@Autowired
	@Resource(name = "dustbinTempService")
	public void setDataManageService(AbstractDataManageService dataManageService) {
		super.setDataManageService(dataManageService);
	}

	@Autowired
	@Resource(name = "dustbinTempValidator")
	public void setValidator(DomainValidatorTemp validators) {
		super.setValidator(validators);
	}
}
