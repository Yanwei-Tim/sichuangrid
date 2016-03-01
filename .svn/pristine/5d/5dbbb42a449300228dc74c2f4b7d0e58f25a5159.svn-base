package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.EnvironmentalReform;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("environmentalReformValidator")
public class EnvironmentalReformValidator implements
		DomainValidator<EnvironmentalReform> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(EnvironmentalReform domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();
		// 验证BasicYearInfo中orgid是否是乡镇层级
		if (domain.getOrganization() == null
				|| domain.getOrganization().getId() == null) {
			throw new BusinessValidationException("请选择层级");
		}
		if (organizationDubboService
				.getFullOrgById(domain.getOrganization().getId()).getOrgLevel()
				.getInternalId() != OrganizationLevel.VILLAGE) {
			throw new BusinessValidationException("层级错误,操作失败");
		}
		// 验证garbageTank垃圾池个数
		if (domain.getGarbageTank() != null) {
			if (domain.getGarbageTank() < 0
					|| domain.getGarbageTank() > 99999999)
				result.addErrorMessage("垃圾池个数数值应在0-99999999");
		}
		// 验证公厕个数
		if (domain.getToilets() != null) {
			if (domain.getToilets() < 0 || domain.getToilets() > 99999999)
				result.addErrorMessage("公厕个数数值应在0-99999999");
		}
		// 验证污水处理设施
		if (domain.getTreatmentFacilities() != null) {
			if (domain.getTreatmentFacilities() < 0
					|| domain.getTreatmentFacilities() > 99999999)
				result.addErrorMessage("污水处理设施数值应在0-99999999");
		}
		//生活污水处理户数
		if (domain.getSanitarySewage() != null) {
			if (domain.getSanitarySewage() < 0
					|| domain.getSanitarySewage() > 99999999)
				result.addErrorMessage("生活污水处理户数值应在0-99999999");
		}
		return result;
	}
}
