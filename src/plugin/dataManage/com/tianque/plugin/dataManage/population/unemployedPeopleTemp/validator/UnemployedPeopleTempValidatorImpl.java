package com.tianque.plugin.dataManage.population.unemployedPeopleTemp.validator;

import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.unemployedPeopleTemp.domain.UnemployedPeopleTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;

@Component("unemployedPeopleTempValidator")
public class UnemployedPeopleTempValidatorImpl extends
		AbstactDataManageValidator<UnemployedPeopleTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(UnemployedPeopleTemp domain) {
		ValidateResult validateResult = new ValidateResult();
		/** 原单位名称验证 */
		if (null != domain.getOriginalWorkUnit()
				&& domain.getOriginalWorkUnit().trim().length() != 0) {
			/** 字段长度验证 */
			if (!validateHelper.emptyString(domain.getOriginalWorkUnit())
					&& validateHelper.illegalStringLength(0, 30,
							domain.getOriginalWorkUnit())) {
				validateResult.addErrorMessage(getColumNo("originalWorkUnit")
						+ "原单位名称不能大于30个字符");
			}
			/** 非法字符验证 */
			if (validateHelper.illegalExculdeParticalChar(domain
					.getOriginalWorkUnit())) {
				validateResult.addErrorMessage(getColumNo("originalWorkUnit")
						+ "原单位名称不能输入非法字符");
			}
		}
		/** 原单位类型验证 */
		if (null != domain.getOriginalWorkUnitType()
				&& domain.getOriginalWorkUnitType().trim().length() != 0) {
			if (!validateHelper.emptyString(domain.getOriginalWorkUnitType())
					&& validateHelper.illegalStringLength(0, 20,
							domain.getOriginalWorkUnitType())) {
				validateResult
						.addErrorMessage(getColumNo("originalWorkUnitType")
								+ "原单位性质不能大于20个字符");
			}
			/** 非法字符验证 */
			if (validateHelper.illegalExculdeParticalChar(domain
					.getOriginalWorkUnitType())) {
				validateResult
						.addErrorMessage(getColumNo("originalWorkUnitType")
								+ "原单位名称不能输入非法字符");
			}
		}

		/** 职称验证 */
		if (null != domain.getTitle() && domain.getTitle().trim().length() != 0) {
			if (!validateHelper.emptyString(domain.getTitle())
					&& validateHelper.illegalStringLength(0, 10,
							domain.getTitle())) {
				validateResult.addErrorMessage(getColumNo("title")
						+ "职称不能大于10个字符");
			}
			/** 非法字符验证 */
			if (validateHelper.illegalExculdeParticalChar(domain.getTitle())) {
				validateResult.addErrorMessage(getColumNo("title")
						+ "职称不能输入非法字符");
			}
		}
		/** 登记证号验证 */
		if (!validateHelper.emptyString(domain.getRegisterNumber())
				&& validateHelper.illegalStringLength(0, 30,
						domain.getRegisterNumber())) {
			validateResult.addErrorMessage(getColumNo("registerNumber")
					+ "登记证号不能大于30个字符");
		}

		/** 技能特长验证 */
		if (!validateHelper.emptyString(domain.getSpecialtySkills())) {
			if(validateHelper.illegalStringLength(2, 50,
					domain.getSpecialtySkills())){
				validateResult.addErrorMessage(getColumNo("specialtySkills")
						+ "技能特长只能输入2-50个字符");
			}
			if(!validateHelper.isConSpeCharacters(domain.getSpecialtySkills())){
				validateResult.addErrorMessage(getColumNo("specialtySkills")
						+ "技能特长不能输入特殊字符");
			}
		}

		/** 验证参与过的培训和时间 */
		if (!validateHelper.emptyString(domain.getParticipatedPrograms()) ) {
			if(validateHelper.illegalStringLength(2, 200,
					domain.getParticipatedPrograms())){
				validateResult
				.addErrorMessage(getColumNo("participatedPrograms")
						+ "参与过的培训项目与时间介绍只能输入2到200个字符");
			}
			if(!validateHelper.isConSpeCharacters(domain.getParticipatedPrograms())){
				validateResult.addErrorMessage(getColumNo("participatedPrograms")
						+ "参与过的培训项目与时间介绍不能输入特殊字符");
			}
	}

		if (null == domain.getUnemploymentDate()) {
			validateResult.addErrorMessage(getColumNo("unemploymentDate")
					+ "失业日期必须输入或失业日期输入不正确，正确格式 例如: '1930-01-01'.");
		}
		if (!validateHelper.nullObj(domain.getBirthday())
				&& !validateHelper.nullObj(domain.getUnemploymentDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getUnemploymentDate(), domain.getBirthday())) {
			validateResult.addErrorMessage(getColumNo("unemploymentDate")
					+ "失业日期不能小于出生日期");
		} else if (!validateHelper.nullObj(domain.getUnemploymentDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						CalendarUtil.getTomorrow(), domain.getUnemploymentDate())) {
			validateResult.addErrorMessage(getColumNo("unemploymentDate")
					+ "失业日期不能大于当前日期");
		}
		if (!validateHelper.nullObj(domain.getUpEnterWorkDate())) {
			if (!validateHelper.nullObj(domain.getUnemploymentDate())
					&& !validateHelper.endDateIsSameorBigForStartDate(
							domain.getUnemploymentDate(),
							domain.getUpEnterWorkDate())) {
				validateResult.addErrorMessage(getColumNo("upEnterWorkDate")
						+ "参加工作时间不能大于等于失业时间");
			} else if (!validateHelper.nullObj(domain.getBirthday())
					&& !validateHelper.endDateIsSameorBigForStartDate(
							domain.getUpEnterWorkDate(), domain.getBirthday())) {
				validateResult.addErrorMessage(getColumNo("upEnterWorkDate")
						+ "参加工作日期不能小于出生日期");
			}
		}
		return validateResult;
	}

}
