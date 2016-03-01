package com.tianque.validate.impl;

import org.springframework.stereotype.Service;

import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.validate.AbstractWorkingValidator;
import com.tianque.xichang.working.peopleAspiration.domain.PeopleAspirations;

/**
 * @ClassName: PeopleAspirationsValidatorImpl
 * @Description: 三本台账-民生诉求台账-验证类
 */
@Service("peopleAspirationsValidator")
public class PeopleAspirationsValidatorImpl extends
		AbstractWorkingValidator<PeopleAspirations> {

	@Override
	public ValidateResult validateSpecializedInfo(PeopleAspirations domain) {
		ValidateResult result = new ValidateResult();
		validatorName(domain.getName(), result, null);
		validatorIdCardNo(domain.getIdCardNo(), result, null);
		validatorPermanentAddress(domain.getPermanentAddress(), result, null);
		validateAppealContent(domain.getAppealContent(), result, null);
		validateItemCategory(domain.getItemCategory(), result, null);
		validateItemCategorySub(domain.getItemCategorySub(), result, null);
		return result;
	}

	// 诉求人类别验证
	public void validateAppealHumanType(PropertyDict appealHumanType,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (appealHumanType == null
				|| (appealHumanType.getId() == null && validateHelper
						.emptyString(appealHumanType.getDisplayName()))) {
			result.addErrorMessage(getColumNo("appealHumanType") + apstr
					+ "诉求人类别必须输入");
		} else if (appealHumanType != null
				&& !validateHelper
						.emptyString(appealHumanType.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.APPEAL_HUMAN_TYPE,
					appealHumanType.getDisplayName())) {
				result.addErrorMessage(getColumNo("appealHumanType") + apstr
						+ "诉求人类别输入不正确");
			}
		}
	}

	// 主要愿望或诉求验证
	public void validateAppealContent(String appealContent,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(appealContent)) {
			result.addErrorMessage(getColumNo("appealContent") + apstr
					+ "主要愿望或诉求必须输入");
		} else if (!validateHelper.emptyString(appealContent)
				&& validateHelper.illegalStringLength(2, 50, appealContent)) {
			result.addErrorMessage(getColumNo("appealContent") + apstr
					+ "主要愿望或诉求只能输入2-50个字符");
		}
	}

	// 项目类别大类验证
	public void validateItemCategory(PropertyDict itemCategory,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (itemCategory == null
				|| (itemCategory.getId() == null && validateHelper
						.emptyString(itemCategory.getDisplayName()))) {
			result.addErrorMessage(getColumNo("itemCategory") + apstr
					+ "项目类别(大类)必须输入");
		} else if (itemCategory != null
				&& !validateHelper.emptyString(itemCategory.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.ITEM_CATEGORY, itemCategory.getDisplayName())) {
				result.addErrorMessage(getColumNo("itemCategory") + apstr
						+ "项目类别(大类)不正确");
			}
		}
	}

	// 项目类别子类验证
	public void validateItemCategorySub(PropertyDict itemCategorySub,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (itemCategorySub == null
				|| (itemCategorySub.getId() == null && validateHelper
						.emptyString(itemCategorySub.getDisplayName()))) {
			result.addErrorMessage(getColumNo("itemCategorySub") + apstr
					+ "项目类别(子类)必须输入");
		} else if (itemCategorySub != null
				&& !validateHelper
						.emptyString(itemCategorySub.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.ITEM_CATEGORY_SUB,
					itemCategorySub.getDisplayName())) {
				result.addErrorMessage(getColumNo("itemCategorySub") + apstr
						+ "项目类别(子类)不正确");
			}
		}
	}

}
