package com.tianque.plugin.dataManage.population.aidNeedPopulationTemp.validator;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.aidNeedPopulationTemp.domain.AidNeedPopulationTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("aidNeedPopulationTempValidator")
public class AidNeedPopulationTempValidatorImpl extends
		AbstactDataManageValidator<AidNeedPopulationTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(AidNeedPopulationTemp domain) {
		ValidateResult result = new ValidateResult();
		if (null == domain.getAidReason()
				|| null == domain.getAidReason().getId()) {
			result.addErrorMessage(getColumNo("aidReason") + "救助原因必须输入");
		}
		if (domain.getIsLowHouseholds() != null && domain.getIsLowHouseholds()) {
			if (validateHelper.emptyString(domain.getDifficultCardNo())) {
				result.addErrorMessage(getColumNo("difficultCardNo")
						+ "困难证号必须输入");
			} else if (validateHelper.illegalStringLength(2, 30,
					domain.getDifficultCardNo())) {
				result.addErrorMessage(getColumNo("difficultCardNo")
						+ "困难证号只能输入2至30个字符");
			}
			// fateson update
			if (null == domain.getDifficultType()
					|| null == domain.getDifficultType().getId()) {
				result.addErrorMessage(getColumNo("difficultType") + "困难类型必须输入");
			}
			if (validateHelper.emptyString(domain.getSubsidiesArea())) {
				result.addErrorMessage(getColumNo("subsidiesArea") + "享受地区必须输入");
			} else if (validateHelper.illegalStringLength(2, 50,
					domain.getSubsidiesArea())) {
				result.addErrorMessage(getColumNo("subsidiesArea")
						+ "享受地区只能输入2至50个字符");
			} else if (validateHelper.illegalExculdeParticalChar(domain
					.getSubsidiesArea())) {
				result.addErrorMessage(getColumNo("subsidiesArea")
						+ "享受地区不能输入非法字符");
			}
		}
		if ((null != domain.getDifficultCardNo()
				|| null != domain.getDifficultType() || null != domain
				.getSubsidiesArea())
				&& (!domain.getIsLowHouseholds() || null == domain
						.getIsLowHouseholds())) {
			result.addErrorMessage(getColumNo("isLowHouseholds") + "请选择是否低保项为是");
		}
		if (domain.getSubsidiesAmount() != null) {
			if (validateHelper.illegalNumberZS(domain.getSubsidiesAmount())) {
				result.addErrorMessage(getColumNo("subsidiesAmount")
						+ "领取金额输入不正确");
			}
		}
		if (domain.getSubsidiesPopulation() != null) {
			if (validateHelper.illegalNumberZZ(domain.getSubsidiesPopulation())) {
				result.addErrorMessage(getColumNo("subsidiesPopulation")
						+ "享受人数输入不正确");
			} else {
				if (validateHelper.illegalStringLength(0, 3,
						domain.getSubsidiesPopulation())) {
					result.addErrorMessage(getColumNo("subsidiesPopulation")
							+ "享受人数不能大于3位数");
				}
			}
			if (domain.getSubsidiesGetTime() != null) {
				if (domain.getSubsidiesGetTime().after(new Date())) {
					result.addErrorMessage(getColumNo("subsidiesGetTime")
							+ "领取时间不能大于当前时间");
				}
			}
			if (domain.getSubsidiesStartTime() != null) {
				if (domain.getSubsidiesStartTime().after(new Date())) {
					result.addErrorMessage(getColumNo("subsidiesStartTime")
							+ "享受起始时间不能大于当前时间");
				}
			}
		}
		return result;
	}
}
