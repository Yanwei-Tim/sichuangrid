package com.tianque.partyBuilding.members.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.util.IdCardUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("memberValidatorImpl")
public class MemberValidatorImpl extends AbstractCountrymenValidator<Member> {
	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(Member domain) {
		ValidateResult result = new ValidateResult();
		result.addAllErrorMessage(validateBaseInfo(domain));
		if (domain.getPartyOrgType() != MemberType.DOUBLE_REG_ACTIVITIES) {
			result.addAllErrorMessage(validateSpecializedInfo(domain));
		}
		return result;
	}

	private ValidateResult validateBaseInfo(Member domain) {
		ValidateResult result = new ValidateResult();
		/** 姓名 */
		validatorName(domain.getName(), result, null);
		/** 身份证号码 */
		validatorIdCardNo(domain.getIdCardNo(), result, null);
		/** 性别 */
		/** 联系电话 */
		validatorifMobileNumber(domain.getMobileNumber(), result, null);
		/** 联系手机 */
		validatorifTelephone(domain.getTelephone(), result, null);
		/** 省 市 县 */
		validatorMemberProvinceAndCityAndDistrict(domain.getProvince(),
				domain.getCity(), domain.getDistrict(), result, null);
		/** 民族 */
		validatorMemberNation(domain.getNation(), result, null);
		/** 学历 */
		validatorMemberSchooling(domain.getSchooling(), result, null);
		/** 现居地址 */
		validatorMemberNativePlaceAddress(domain.getNativePlaceAddress(),
				result, null);
		/** 学位 */
		validatorDegree(domain.getDegree(), result, null);
		/** 专业技术职务 */
		validatorSpecialPosition(domain.getSpecialPosition(), result, null);
		fillDefaultBirthday(domain);
		return result;
	}

	private void fillDefaultBirthday(Member domain) {
		if (StringUtil.isStringAvaliable(domain.getIdCardNo())) {
			domain.setBirthday(IdCardUtil.parseBirthday(domain.getIdCardNo()));
		}
	}

	@Override
	public ValidateResult validateSpecializedInfo(Member domain) {
		ValidateResult result = new ValidateResult();
		/** 所属部门 */
		validatorBelongOrg(domain.getBelongOrg(), result, null);
		/** 党内职务 */
		validatorPartyPosition(domain.getPartyPosition(), result, null);
		/** 入党日期 */
		/** 单位、职务或职业 */
		validatorCareer(domain.getCareer(), result, null);
		/** 党费交纳至 */
		/** 奖惩情况 */
		validatorRewardAndPunishment(domain.getRewardAndPunishment(), result,
				null);
		/** 参加组织生活情况 */
		validatorAccedingSituation(domain.getAccedingSituation(), result, null);
		/** 进入当前党支部日期 */
		/** 民主评议情况 */
		validatorDemocracy(domain.getDemocracy(), result, null);
		/** 是否困难党员 */
		/** 是否建国前老党员 */
		/** 是否出国出境 */
		return result;
	}

	private void validatorMemberSchooling(PropertyDict schooling,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != schooling
				&& !validateHelper.emptyString(schooling.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.SCHOOLING, schooling.getDisplayName()))
				result.addErrorMessage(getColumNo("schooling") + apstr
						+ "文化程度输入不正确");
		}
	}

	private void validatorMemberNation(PropertyDict nation,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != nation
				&& !validateHelper.emptyString(nation.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.NATION, nation.getDisplayName()))
				result.addErrorMessage(getColumNo("nation") + apstr + "民族输入不正确");
		}
	}

	private void validatorMemberProvinceAndCityAndDistrict(String province,
			String city, String district, ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(province)
				&& validateHelper.illegalStringLength(0, 20, province)) {
			result.addErrorMessage(getColumNo("province") + apstr
					+ "户籍地省不能输入大于20字符");
		}
		if (!validateHelper.emptyString(province)
				&& validateHelper.illegalStringLength(0, 20, city)) {
			result.addErrorMessage(getColumNo("city") + apstr
					+ "户籍地市不能输入大于20字符");
		}

		if (!validateHelper.emptyString(province)
				&& validateHelper.illegalStringLength(0, 20, district)) {
			result.addErrorMessage(getColumNo("district") + apstr
					+ "户籍地县不能输入大于20字符");
		}
	}

	private void validatorDemocracy(PropertyDict democracy,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != democracy
				&& !validateHelper.emptyString(democracy.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.DEMOCRACY, democracy.getDisplayName()))
				result.addErrorMessage(getColumNo("democracy") + apstr
						+ "民主评议情况输入不正确");
		}
	}

	private void validatorPartyPosition(PropertyDict partyPosition,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != partyPosition
				&& !validateHelper.emptyString(partyPosition.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.PARTY_POSITION,
					partyPosition.getDisplayName()))
				result.addErrorMessage(getColumNo("partyPosition") + apstr
						+ "党内职务输入不正确");
		}
	}

	private void validatorBelongOrg(PropertyDict belongOrg,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != belongOrg
				&& !validateHelper.emptyString(belongOrg.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.BELONG_ORG, belongOrg.getDisplayName()))
				result.addErrorMessage(getColumNo("belongOrg") + apstr
						+ "所属部门输入不正确");
		}
	}

	private void validatorMemberNativePlaceAddress(String nativePlaceAddress,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(nativePlaceAddress)) {
			if (validateHelper.illegalStringLength(0, 50, nativePlaceAddress)) {
				result.addErrorMessage(getColumNo("nativePlaceAddress") + apstr
						+ "现居地址不能输入大于50字符");
			}
		}
	}

	private void validatorDegree(String degree, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(degree)) {
			if (validateHelper.illegalStringLength(0, 20, degree)) {
				result.addErrorMessage(getColumNo("degree") + apstr
						+ "学位不能输入大于20字符");
			}
		}
	}

	private void validatorSpecialPosition(String specialPosition,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(specialPosition)) {
			if (validateHelper.illegalStringLength(0, 20, specialPosition)) {
				result.addErrorMessage(getColumNo("specialPosition") + apstr
						+ "专业技术职务不能输入大于20字符");
			}
		}
	}

	private void validatorCareer(String career, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(career)) {
			if (validateHelper.illegalStringLength(0, 20, career)) {
				result.addErrorMessage(getColumNo("career") + apstr
						+ "单位、职务或职业不能输入大于20字符");
			}
		}
	}

	private void validatorRewardAndPunishment(String rewardAndPunishment,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(rewardAndPunishment)) {
			if (validateHelper.illegalStringLength(0, 300, rewardAndPunishment)) {
				result.addErrorMessage(getColumNo("rewardAndPunishment")
						+ apstr + "奖惩情况不能输入大于300字符");
			}
		}
	}

	private void validatorAccedingSituation(String accedingSituation,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(accedingSituation)) {
			if (validateHelper.illegalStringLength(0, 300, accedingSituation)) {
				result.addErrorMessage(getColumNo("accedingSituation") + apstr
						+ "参加组织生活情况不能输入大于300字符");
			}
		}
	}

}
