package com.tianque.validate.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.xichang.working.poorPeople.domain.PoorPeople;
import com.tianque.xichang.working.poorPeople.domain.PoorPeopleMembers;

/**
 * @ClassName: PoorPeopleValidateImpl
 * @Description: 三本台账-困难群众台账-维护家庭成员-验证类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 24, 2013 4:44:53 PM
 */
@Service("poorPeopleMembersValidator")
public class PoorPeopleMembersValidatorImpl
		implements
			DomainValidator<PoorPeopleMembers> {
	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(PoorPeopleMembers domain) {
		ValidateResult result = new ValidateResult();
		validatorName(domain.getName(), result, null);
		validatorRelationShipWithHead(domain.getRelationShipWithHead(), result,
				null);
		validatorInsuranceType(domain.getInsuranceType(), result, null);
		validatorPoorPeople(domain.getPoorPeople(), result, null);
		return result;
	}

	private void validatorInsuranceType(PropertyDict insuranceType,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null == insuranceType
				|| (null == insuranceType.getId() && validateHelper
						.emptyString(insuranceType.getDisplayName()))) {
			result.addErrorMessage(getColumNo("insuranceType") + apstr
					+ "纳入低保（五保）情况必须输入");
		} else if (null != insuranceType
				&& !validateHelper.emptyString(insuranceType.getDisplayName())) {
			if (validateHelper
					.illegalPropertyDictDisplayName(
							PropertyTypes.INSURANCETYPE, insuranceType
									.getDisplayName()))
				result.addErrorMessage(getColumNo("insuranceType") + apstr
						+ "纳入低保（五保）情况输入不正确");
		}
	}

	private void validatorRelationShipWithHead(
			PropertyDict relationShipWithHead, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null == relationShipWithHead
				|| (null == relationShipWithHead.getId() && validateHelper
						.emptyString(relationShipWithHead.getDisplayName()))) {
			result.addErrorMessage(getColumNo("relationShipWithHead") + apstr
					+ "与户主关系必须输入");
		} else if (null != relationShipWithHead
				&& !validateHelper.emptyString(relationShipWithHead
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.RELATION_SHIP_WITH_HEAD, relationShipWithHead
							.getDisplayName()))
				result.addErrorMessage(getColumNo("relationShipWithHead")
						+ apstr + "与户主关系输入不正确");
		}
	}

	public void validatorPoorPeople(PoorPeople poorPeople,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (poorPeople == null || poorPeople.getId() == null
				|| poorPeople.getId().longValue() == 0L) {
			result.addErrorMessage(apstr + "家庭成员于困难群众台账绑定失败");
		}
	}

	public void validatorName(String name, ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(name)) {
			result.addErrorMessage(getColumNo("name") + apstr + "姓名必须输入");
		} else if (!validateHelper.isConSpeCharacters(name)) {
			result.addErrorMessage(getColumNo("name") + apstr + "姓名不能包含特殊字符");
		} else if (validateHelper.illegalStringLength(2, 20, name)) {
			result
					.addErrorMessage(getColumNo("name") + apstr
							+ "姓名只能输入2-20个字符");
		} else if (validateHelper.illegalExculdeParticalChar(name)) {
			result.addErrorMessage(getColumNo("name") + apstr
					+ "姓名只能输入数字,字母,中文字符");
		}
	}

	public String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))) {
			bf.append("第[").append(ExcelImportHelper.realRow.get())
					.append("]行");
			bf.append("第[")
					.append(
							Integer.valueOf(ExcelImportHelper
									.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}
}
