package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.BasicInfoMation;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("basicInfoMationValidator")
public class BasicInfoMationValidator implements
		DomainValidator<BasicInfoMation> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(BasicInfoMation domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		// 验证BasicYearInfo中orgid是否是乡镇层级
		ValidateResult result = new ValidateResult();
		// 验证地理位置
		if (StringUtil.isStringAvaliable(domain.getGeographicalPosition())
				&& validateHelper.illegalStringLength(0, 30,
						domain.getGeographicalPosition())) {
			result.addErrorMessage("地理位置最多输入30个字符");
		}
		// 验证气候
		if (StringUtil.isStringAvaliable(domain.getClimate())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getClimate())) {
			result.addErrorMessage("气候最多输入20个字符");
		}
		//验证总面积
		if (domain.getTotalArea() != null) {
			if (domain.getTotalArea() < 0
					|| domain.getTotalArea() > 999999999.99D) {
				result.addErrorMessage("总面积/幅员面积应在0-999999999.99之间");
			}
		}
		//验证耕地面积
		if (domain.getCultivatedLandArea() != null) {
			if (domain.getCultivatedLandArea() < 0
					|| domain.getCultivatedLandArea() > 999999999.99D) {
				result.addErrorMessage("耕地面积应在0-999999999.99之间");
			}
		}
		//验证林地面积
		if (domain.getWoodlandArea() != null) {
			if (domain.getWoodlandArea() < 0
					|| domain.getWoodlandArea() > 999999999.99D) {
				result.addErrorMessage("林地面积应在0-999999999.99之间");
			}
		}
		//验证荒地面积
		if (domain.getWastelandArea() != null) {
			if (domain.getWastelandArea() < 0
					|| domain.getWastelandArea() > 999999999.99D) {
				result.addErrorMessage("荒地面积应在0-999999999.99之间");
			}
		}
		//验证海拔高度
		if (domain.getAltitude() != null) {
			if (domain.getAltitude() < 0 || domain.getAltitude() > 9999) {
				result.addErrorMessage("海拔高度应在0-9999之间");
			}
		}
		//验证土壤特性
		if (StringUtil.isStringAvaliable(domain.getSoilProperties())
				&& validateHelper.illegalStringLength(0, 10,
						domain.getSoilProperties())) {
			result.addErrorMessage("土壤特性最多输入10个字符");
		}
		//验证基本介绍
		if (StringUtil.isStringAvaliable(domain.getBasicIntroduction())
				&& validateHelper.illegalStringLength(0, 2000,
						domain.getBasicIntroduction())) {
			result.addErrorMessage("基本介绍最多输入2000个字符");
		}
		return result;
	}
}
