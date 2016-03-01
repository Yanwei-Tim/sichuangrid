package com.tianque.baseInfo.goodSamaritan.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("goodSamaritanValidator")
public class GoodSamaritanValidatorImpl extends
		AbstractCountrymenValidator<GoodSamaritan> {
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;

	@Override
	public ValidateResult validateSpecializedInfo(GoodSamaritan domain) {
		ValidateResult validateResult = new ValidateResult();
		if (!validateHelper.emptyString(domain.getActOccurred())
				&& validateHelper.illegalStringLength(0, 100,
						domain.getActOccurred())) {
			validateResult.addErrorMessage(getColumNo("actOccurred")
					+ "行为发生地不能大于100个字符");
		}
		if (domain.getInsureSituationType() == null
				|| domain.getInsureSituationType().getId() == null) {
			validateResult.addErrorMessage(getColumNo("insureSituationType")
					+ "请选择参保情况大类");
		}
		if (domain.getInsureSituationType() != null
				&& domain.getInsureSituationType().getId() != null) {
			PropertyDict insureSituationType = propertyDictDubboService
					.getPropertyDictById(domain.getInsureSituationType()
							.getId());
			if (!"其他".equals(insureSituationType.getDisplayName())) {
				if (domain.getInsureSituationSmallType() == null
						|| domain.getInsureSituationSmallType().getId() == null) {
					validateResult
							.addErrorMessage(getColumNo("insureSituationSmallType")
									+ "请选择参保情况小类");
				}
			}
		}
		if (!StringUtil.isStringAvaliable(domain.getAwardYear())
				|| Integer.parseInt(domain.getAwardYear()) > CalendarUtil
						.getNowYear()) {
			validateResult.addErrorMessage(getColumNo("awardYear")
					+ "不能为空或者您填写的年份大于当前年");
		}
		if (domain.getSacrificeType() == null
				|| domain.getSacrificeType().getId() == null) {
			validateResult.addErrorMessage(getColumNo("sacrificeType")
					+ "请选择是否牺牲");
		}
		if (domain.getDisableGradeType() == null
				|| domain.getDisableGradeType().getId() == null) {
			validateResult.addErrorMessage(getColumNo("disableGradeType")
					+ "请选择伤残等级");
		}
		if (domain.getAwardType() == null
				|| domain.getAwardType().getId() == null) {
			validateResult.addErrorMessage(getColumNo("awardType") + "请选择获奖类型");
		}
		if (domain.getDifficultType() == null
				|| domain.getDifficultType().getId() == null) {
			validateResult.addErrorMessage(getColumNo("difficultType")
					+ "请选择困难类型");
		}

		return validateResult;
	}
}
