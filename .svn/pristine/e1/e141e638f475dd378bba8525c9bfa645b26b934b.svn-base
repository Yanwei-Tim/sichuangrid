package com.tianque.plugin.taskList.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.taskList.domain.HiddenDanger;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("hiddenDangerValidator")
public class HiddenDangerValidatorImpl implements
DomainValidator<HiddenDanger>{
	
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	public ValidateHelper validateHelper;

	private boolean validateOrgIfExsist(Organization org) {
		Organization organization = organizationDubboService.getSimpleOrgById(org.getId());
		if (organization == null) {
			return false;
		}
		return true;
	}

	private boolean validateOrgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}



	@Override
	public ValidateResult validate(HiddenDanger domain) {
		ValidateResult validateResult = new ValidateResult();
	
		if (!validateOrgIsNotNull(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization") + "所属网格必须输入");
		}
		if (validateOrgIsNotNull(domain.getOrganization())
				&& !validateOrgIfExsist(domain.getOrganization())) {
			validateResult.addErrorMessage(getColumNo("organization") + "找不到指定网格");
		}
	
		if(domain.getDiscoverDate()==null){
			validateResult.addErrorMessage("发现时间不能为空！");
		}
		if(validateHelper.emptyString(domain.getAddress())){
			validateResult.addErrorMessage("地点不能为空！");
		}else if (validateHelper.illegalStringLength(0, 50, domain.getAddress())) {
			validateResult.addErrorMessage("地点不能输入超过50个字！");
		}
		if (!exceptionTypeIsNotNull(domain.getExceptionType())) {
			validateResult.addErrorMessage("异常类型必须输入");
		}
		if(validateHelper.emptyString(domain.getExceptionSituation())){
			
		}else if (validateHelper.illegalStringLength(0, 250, domain.getExceptionSituation())) {
			validateResult.addErrorMessage("异常情况不能输入超过250个字！");
		}
		if (validateHelper.emptyString(domain.getAdvice())) {

		} else if (validateHelper.illegalStringLength(0, 200, domain.getAdvice())) {
			validateResult.addErrorMessage("签收意见不能输入超过200个字！");
		}
		
		if (validateHelper.emptyString(domain.getRemark())) {

		} else if (validateHelper.illegalStringLength(0, 200, domain.getRemark())) {
			validateResult.addErrorMessage("备注不能输入超过200个字！");
		}
	
		
		
		return validateResult;
	}
	
	public String getColumNo(String key) {
		return ExcelImportHelper.getColumNo(key);
	}
	
	private boolean exceptionTypeIsNotNull(PropertyDict exceptionType) {
		if (null == exceptionType || null == exceptionType.getId()) {
			return false;
		}
		return true;
	}
	


}
