package com.tianque.baseInfo.nurturesWomen.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.LicenseSituation;
import com.tianque.domain.property.MaritalState;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.util.IdCardUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("nurturesWomenValidator")
public class NurturesWomenValidatorImpl extends
		AbstractCountrymenValidator<NurturesWomen> {

	private static final String MAN = "男方";

	private static final String WOMAN = "女";
	private static final int singleChildCardnoLength = 50;

	@Override
	public ValidateResult validateSpecializedInfo(NurturesWomen domain) {
		ValidateResult result = new ValidateResult();
		if (null != domain.getMaternityCardCheckTime()
				&& !isDateStr(domain.getMaternityCardCheckTime())) {
			result.addErrorMessage(getColumNo("maternityCardCheckTime")
					+ "查验时间输入格式有问题");
		}
		Boolean displayName = domain.getGender() == null;
		if (displayName) {
			result.addErrorMessage(getColumNo("gender") + "性别必须输入");
		}
		if (!displayName) {
			if (validateHelper.illegalPropertyDictDisplayNameById(domain
					.getGender().getId(), WOMAN)) {
				result.addErrorMessage(getColumNo("gender") + "性别只能为女性");
			}
		}
		if (!displayName
				&& !validateHelper.illegalPropertyDictDisplayNameById(domain
						.getGender().getId(), WOMAN)
				&& !validateHelper.emptyString(domain.getIdCardNo())
				&& !validateHelper.illegalIdcard(domain.getIdCardNo())) {
			Date idCardDate = IdCardUtil.parseBirthday(domain.getIdCardNo());
			Date afterDate = null, beforeDate = null;
			Date date = new Date();
			String dates = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String afterDateStr = (Integer.parseInt(dates.substring(0, 4)) - 15)
					+ dates.substring(4, 10);
			String beforeDateStr = (Integer.parseInt(dates.substring(0, 4)) - 49)
					+ dates.substring(4, 10);
			try {
				afterDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(afterDateStr);

				beforeDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(beforeDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (idCardDate.before(beforeDate)) {
				result.addErrorMessage(getColumNo("idCardNo")
						+ "育龄妇女年龄应在15-49岁之间");
			}
			if (idCardDate.after(afterDate)) {
				result.addErrorMessage(getColumNo("idCardNo")
						+ "育龄妇女年龄应在15-49岁之间");
			}
			String idCardNo = domain.getIdCardNo();
			if (15 == idCardNo.length()) { // 15位身份证号码
				if (idCardNo.charAt(14) / 2 * 2 != idCardNo.charAt(14)) {
					result.addErrorMessage(getColumNo("idCardNo")
							+ "育龄妇女性别应为女性!");
				}
			}
			if (18 == idCardNo.length()) { // 18位身份证号码
				if (idCardNo.charAt(16) / 2 * 2 != idCardNo.charAt(16)) {
					result.addErrorMessage(getColumNo("idCardNo")
							+ "育龄妇女性别应为女性!");
				}
			}
		}
		/** 年龄判断 */
		if (!validateHelper.emptyString(domain.getIdCardNo())) {
			validatorIdCardNoWomen(domain.getIdCardNo(), result, WOMAN);
		}

		Date firstMarriageTimeDate = domain.getFirstMarriageTime();
		Date birthdayDate = domain.getBirthday();

		if (null != firstMarriageTimeDate && null != birthdayDate) {
			if (!validateHelper.endDateIsSameorBigForStartDate(
					firstMarriageTimeDate, domain.getBirthday())) {
				result.addErrorMessage(getColumNo("firstMarriageTime")
						+ "初婚时间不能小于出生日期");
			}
		}
		/** 初婚时间不大于当前日期验证 */
		if (null != firstMarriageTimeDate) {
			if (validateHelper.endDateIsSameorBigForStartDate(
					firstMarriageTimeDate, CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("firstMarriageTime")
						+ "初婚时间不能大于当前日期");
			}
		}

		/** 男方工作单位或学校验证 */
		if (!validateHelper.emptyString(domain.getManWorkUnit())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getManWorkUnit())) {
				result.addErrorMessage(getColumNo("manWorkUnit")
						+ "男方工作单位或学校不能大于50个字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getManWorkUnit())) {
				result.addErrorMessage(getColumNo("manWorkUnit")
						+ "男方工作单位或学校不能输入特殊字符");
			}
		}

		/** 男方居住 小区验证 */
		if (!validateHelper.emptyString(domain.getManCommunity())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getManCommunity())) {
			result.addErrorMessage(getColumNo("manCommunity")
					+ "小区/地址不能大于20个字符");
		}
		/** 男方居住 撞验证 */
		if (!validateHelper.emptyString(domain.getManBlock())
				&& validateHelper.illegalStringLength(0, 8,
						domain.getManBlock())) {
			result.addErrorMessage(getColumNo("manBlock") + "不能输入大于8个字符");
		}
		/** 男方居住 单元验证 */
		if (!validateHelper.emptyString(domain.getManUnit())
				&& validateHelper
						.illegalStringLength(0, 6, domain.getManUnit())) {
			result.addErrorMessage(getColumNo("manUnit") + "单元不能输入大于6个字符");
		}
		/** 男方居住 室验证 */
		if (!validateHelper.emptyString(domain.getManRoom())
				&& validateHelper.illegalStringLength(0, 10,
						domain.getManRoom())) {
			result.addErrorMessage(getColumNo("manRoom") + "单元不能输入大于10个字符");
		}

		// fateson 发证时间”和“有效期截至时间”大小限制
		Date maternityCardIssueTime = domain.getMaternityCardIssueTime();
		Date maternityCardValidityTime = domain.getMaternityCardValidityTime();
		if (null != maternityCardIssueTime && null != maternityCardValidityTime) {
			if (!validateHelper.endDateIsSameorBigForStartDate(
					maternityCardValidityTime, maternityCardIssueTime)) {
				result.addErrorMessage(getColumNo("maternityCardValidityTime")
						+ "发证有效期不能小于发证时间");
			}
		}
		if (null != domain.getMaternityCardIssueTime()) {
			if (validateHelper.endDateIsSameorBigForStartDate(
					domain.getMaternityCardIssueTime(),
					CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("maternityCardIssueTime")
						+ "发证时间不能大于当前日期");
			}
		}
		if (null != domain.getMaternityCardValidityTime()
				&& !isDateStr(domain.getMaternityCardValidityTime())) {
			result.addErrorMessage(getColumNo("maternityCardValidityTime")
					+ "有效期截至时间输入格式有问题");
		}
		if (null != domain.getMaternityCardIssueTime()
				&& null != domain.getMaternityCardValidityTime()) {
			if (!validateHelper.endDateIsSameorBigForStartDate(
					domain.getMaternityCardValidityTime(),
					domain.getMaternityCardIssueTime())) {
				result.addErrorMessage(getColumNo("maternityCardIssueTime")
						+ "发证时间不能大于有效期截至时间");
				result.addErrorMessage(getColumNo("maternityCardValidityTime")
						+ "有效期截至时间不能小于发证时间");
			}
		}
		Date marriageRegistrationTimeDate = domain
				.getMarriageRegistrationTime();
		if (marriageRegistrationTimeDate != null
				&& firstMarriageTimeDate != null) {
			if (!isDateStr(marriageRegistrationTimeDate)) {
				result.addErrorMessage(getColumNo("marriageRegistrationTimeDate")
						+ "离婚时间输入格式有问题");
			}
			if (!validateHelper.endDateIsSameorBigForStartDate(
					marriageRegistrationTimeDate, firstMarriageTimeDate)) {
				result.addErrorMessage(getColumNo("marriageRegistrationTime")
						+ "离婚时间不能小于初婚日期");
			}
		}
		/** 离婚时间不能大于当前时间 */
		if (marriageRegistrationTimeDate != null) {
			if (validateHelper.endDateIsSameorBigForStartDate(
					marriageRegistrationTimeDate, CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("marriageRegistrationTime")
						+ "离婚时间不能大于当前日期");
			}
		}
		PropertyDict licenseSituation = domain.getLicenseSituation();
		String fertilityServicesNo = domain.getFertilityServicesNo();
		Date licenseTime = domain.getLicenseTime();
		if (licenseSituation != null
				&& !validateHelper.emptyString(licenseSituation
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.LICENSE_SITUATION,
					licenseSituation.getDisplayName())) {
				result.addErrorMessage(getColumNo("licenseSituation")
						+ "领证情况输入不正确");
			} else {
				if (!validateHelper.emptyString(fertilityServicesNo)) {
					boolean flag = licenseSituation.getDisplayName().equals(
							LicenseSituation.getInternalProperties()
									.get(LicenseSituation.NEVERLICENSE)
									.getDisplayName().trim());
					boolean flag1 = licenseSituation.getDisplayName().equals(
							LicenseSituation.getInternalProperties()
									.get(LicenseSituation.CHILDRENIN)
									.getDisplayName().trim());
					boolean flag2 = licenseSituation.getDisplayName().equals(
							LicenseSituation.getInternalProperties()
									.get(LicenseSituation.CHILDRENOUT)
									.getDisplayName().trim());
					if (flag || flag1 || flag2) {
						result.addErrorMessage(getColumNo("fertilityServicesNo")
								+ "领证情况为未领证或计内、外二孩，证废时，生育服务证无效");
					}
					if (validateHelper.illegalStringLength(0, 50,
							fertilityServicesNo)) {
						result.addErrorMessage(getColumNo("fertilityServicesNo")
								+ "生育服务证号不能输入大于50字符");
					}
				}
				/** 领证时间不能大于当前时间 */
				if (validateHelper.endDateIsSameorBigForStartDate(licenseTime,
						CalendarUtil.getTomorrow())) {
					result.addErrorMessage(getColumNo("licenseTime")
							+ "领证时间不能大于当前日期");
				}
				if (licenseTime != null) {
					if (!isDateStr(licenseTime)) {
						result.addErrorMessage(getColumNo("licenseTime")
								+ "领证时间输入格式有问题");
					}
					if (licenseSituation.getDisplayName().equals(
							LicenseSituation.getInternalProperties()
									.get(LicenseSituation.NEVERLICENSE)
									.getDisplayName())) {
						result.addErrorMessage(getColumNo("licenseTime")
								+ "领证情况为非有效证时，领证时间无效");
					}
					if (null != birthdayDate
							&& !validateHelper.endDateIsSameorBigForStartDate(
									licenseTime, birthdayDate)) {
						result.addErrorMessage(getColumNo("licenseTime")
								+ "领证时间不能小于出生日期");
					}
				}
			}
		}
		/** 独生子女证领取时间验证：1.不能大于当前时间；2.时间格式验证 */
		Date singleChildCDIssueTime = domain.getSingleChildCDIssueTime();
		if (null != singleChildCDIssueTime) {
			/** 时间格式 */
			if (!isDateStr(singleChildCDIssueTime)) {
				result.addErrorMessage(getColumNo("singleChildCDIssueTime")
						+ "独生子女证领取时间输入格式有问题");
			}
			/** 是否大于当前时间 */
			if (validateHelper.endDateIsSameorBigForStartDate(
					singleChildCDIssueTime, CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("singleChildCDIssueTime")
						+ "独生子女证领取时间不能大于当前日期");
			}

		}
		Date marriageOrDivorceTime = domain.getMarriageOrDivorceTime();
		if (marriageOrDivorceTime != null) {
			if (null != domain.getMaritalState()
					&& !validateHelper.emptyString(domain.getMaritalState()
							.getDisplayName())) {
				if (!isDateStr(marriageOrDivorceTime)) {
					result.addErrorMessage(getColumNo("marriageOrDivorceTime")
							+ "再婚时间 输入格式有问题");
				}
				/** 初婚时间不大于当前日期验证 */
				if (validateHelper.endDateIsSameorBigForStartDate(
						marriageOrDivorceTime, CalendarUtil.getTomorrow())) {
					result.addErrorMessage(getColumNo("marriageOrDivorceTime")
							+ "再婚时间不能大于当前日期");
				}

				if (!validateHelper.illegalPropertyDictDisplayName(
						PropertyTypes.MARITAL_STATUS, domain.getMaritalState()
								.getDisplayName())) {
					if (domain
							.getMaritalState()
							.getDisplayName()
							.equals(MaritalState.getInternalProperties()
									.get(MaritalState.NEVERMARRIED)
									.getDisplayName())) {
						result.addErrorMessage(getColumNo("marriageOrDivorceTime")
								+ "婚姻状况为未结过婚时，最近再婚时间无效");
					}
				}
			}
			if (!validateHelper.endDateIsSameorBigForStartDate(
					marriageOrDivorceTime, marriageRegistrationTimeDate)) {
				result.addErrorMessage(getColumNo("marriageOrDivorceTime")
						+ "最近再婚时间不能小于离婚时间");
			}
		}

		String contraceptiveMethod = domain.getContraceptiveMethod();
		if (!validateHelper.emptyString(contraceptiveMethod)) {
			if (validateHelper.illegalStringLength(0, 50, contraceptiveMethod)) {
				result.addErrorMessage(getColumNo("contraceptiveMethod")
						+ "避孕方法不能输入大于50字符");
			}
			if (!validateHelper.isConSpeCharacters(contraceptiveMethod)) {
				result.addErrorMessage(getColumNo("contraceptiveMethod")
						+ "避孕方法不能输入特殊字符");
			}
		}
		Date contraceptiveTime = domain.getContraceptiveTime();
		if (contraceptiveTime != null) {
			if (!isDateStr(contraceptiveTime)) {
				result.addErrorMessage(getColumNo("contraceptiveTime")
						+ "避孕起始时间输入格式有问题");
			}
			if (null != birthdayDate
					&& !validateHelper.endDateIsSameorBigForStartDate(
							contraceptiveTime, birthdayDate)) {
				result.addErrorMessage(getColumNo("contraceptiveTime")
						+ "避孕起始时间不能小于出生日期");
			}

			if (validateHelper.endDateIsSameorBigForStartDate(
					contraceptiveTime, CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("contraceptiveTime")
						+ "避孕起始时间不能大于当前时间");
			}
		}

		PropertyDict onechildOfCouple = domain.getOnechildOfCouple();
		if (onechildOfCouple != null
				&& !validateHelper.emptyString(onechildOfCouple
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.ONE_CHILD_SITUATION,
					onechildOfCouple.getDisplayName())) {
				result.addErrorMessage(getColumNo("onechildOfCouple")
						+ "夫妻双方独生子女情况输入不正确");
			}
		}
		String singleChildCardno = domain.getSingleChildCardno();
		if (!validateHelper.emptyString(singleChildCardno)
				&& validateHelper.illegalStringLength(0,
						singleChildCardnoLength, singleChildCardno)) {
			result.addErrorMessage(getColumNo("singleChildCardno")
					+ "独生子女证号输入不能大于" + singleChildCardnoLength + "字符");
		}
		String certifiedUnit = domain.getCertifiedUnit();
		if (!validateHelper.emptyString(certifiedUnit)) {
			if (validateHelper.illegalStringLength(0, 50, certifiedUnit)) {
				result.addErrorMessage(getColumNo("certifiedUnit")
						+ "发证单位输入大于50字符");
			}
			if (!validateHelper.isConSpeCharacters(certifiedUnit)) {
				result.addErrorMessage(getColumNo("certifiedUnit")
						+ "发证单位不能输入非法字符");
			}
		}
		String maternityCardUnit = domain.getMaternityCardUnit();
		if (!validateHelper.emptyString(maternityCardUnit)) {
			if (validateHelper.illegalStringLength(0, 50, maternityCardUnit)) {
				result.addErrorMessage(getColumNo("maternityCardUnit")
						+ "孕育证发证单位输入大于50字符");
			}
			if (!validateHelper.isConSpeCharacters(maternityCardUnit)) {
				result.addErrorMessage(getColumNo("maternityCardUnit")
						+ "孕育证发证单位不能输入特殊字符");
			}
		}
		String maternityCardNo = domain.getMaternityCardNo();
		if (!validateHelper.emptyString(maternityCardNo)
				&& validateHelper.illegalStringLength(0, 30, maternityCardNo)) {
			result.addErrorMessage(getColumNo("maternityCardNo") + "婚育证号输入大于30"
					+ "字符");
		}

		/** 检查情况字段验证 */
		if (!validateHelper.emptyString(domain.getMaternityCardRemark())) {
			if (validateHelper.illegalStringLength(0, 300,
					domain.getMaternityCardRemark())) {
				result.addErrorMessage(getColumNo("maternityCardRemark")
						+ "检查情况输入不能大于300" + "字符");
			}
			if (!validateHelper.isConSpeCharacters(domain
					.getMaternityCardRemark())) {
				result.addErrorMessage(getColumNo("maternityCardRemark")
						+ "检查情况不能输入特殊字符");
			}

		}

		// fateson add 身高 验证 这里不行得不到 生成domain是的验证的result中的map，不能验证
		/*
		 * String stature = getColumNo("stature"); // 获取身高所在的列 String statureKey
		 * = stature.substring(stature.indexOf("[", 2) + 1,
		 * stature.lastIndexOf("]")); if (result.getMapMessages() != null &&
		 * result.getMapMessages().containsKey(statureKey)) {
		 * result.addErrorMessage(getColumNo("stature") + "请输入小于300的正整数"); }
		 * else { // 说明前面验证通过 Long statureLong = domain.getStature(); if
		 * (statureLong!=null&&300 > statureLong) {
		 * result.addErrorMessage(getColumNo("stature") + "请输入小于300的正整数"); } }
		 */

		/** 男方信息验证 */
		if (!validateHelper.emptyString(domain.getManName())) {
			validatorName(domain.getManName(), result, MAN);
		}
		if (!validateHelper.emptyString(domain.getManIdcardNo())) {
			validatorIdCardNo(domain.getManIdcardNo(), result, MAN);
		}
		Date manFirstMarriageTime = domain.getManFirstMarriageTime();
		if (manFirstMarriageTime != null && domain.getManBirthday() != null) {
			if (!isDateStr(manFirstMarriageTime)) {
				result.addErrorMessage(getColumNo("manFirstMarriageTime")
						+ "男方初婚时间输入格式有问题");
			}
			if (!validateHelper.endDateIsSameorBigForStartDate(
					manFirstMarriageTime, domain.getManBirthday())) {
				result.addErrorMessage(getColumNo("manFirstMarriageTime")
						+ "男方初婚时间不能小于男方出生日期");
			}
		}
		if (null != manFirstMarriageTime) {
			if (validateHelper.endDateIsSameorBigForStartDate(
					manFirstMarriageTime, CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("manFirstMarriageTime")
						+ "男方初婚时间不能大于当前日期");
			}
		}
		if (null != domain.getManBirthday()) {
			if (!isDateStr(domain.getManBirthday())) {
				result.addErrorMessage(getColumNo("manBirthday")
						+ "男方出生日期输入格式有问题");
			}
			if (validateHelper.endDateIsSameorBigForStartDate(
					domain.getManBirthday(), CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("manBirthday")
						+ "男方出生日期不能大于当前日期");
			}
		}

		validatorifMobileNumber(domain.getManMobileNumber(), result, MAN);
		validatorifTelephone(domain.getManTelephone(), result, MAN);
		// validatorNation(domain.getManNation(), result, MAN);
		// validatorPoliticalBackground(domain.getManPoliticalBackground(),
		// result, MAN);
		// validatorSchooling(domain.getManSchooling(), result, MAN);
		validatorCareer(domain.getManCareer(), result, MAN);
		validatorWorkUnit(domain.getWorkUnit(), result, MAN);
		if (!validateHelper.emptyString(domain.getManProvince())
				&& !validateHelper.emptyString(domain.getManCity())
				&& !validateHelper.emptyString(domain.getManDistrict())) {
			validatorProvinceAndCityAndDistrict(domain.getManProvince(),
					domain.getManCity(), domain.getManDistrict(), result, MAN);
		}
		validatorNativePlaceAddress(domain.getManNativeplaceAddress(), result,
				MAN);
		validatorRemark(domain.getManRemark(), result, MAN);

		validatorCertifiedUnit(domain.getCertifiedUnit(), result);

		validatorMaternityCardUnit(domain.getMaternityCardUnit(), result);
		validatorBoyAndGirlNumber(domain.getBoyNumber(),
				domain.getGirlNumber(), result);
		checkIsMaternityCard(domain, result);

		return result;
	}

	@Override
	public void validatorName(String name, ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		String columnName = MAN.equals(apstr) ? "manName" : "name";
		if (validateHelper.emptyString(name)) {
			result.addErrorMessage(getColumNo(columnName) + apstr + "姓名必须输入");
		} else if (!validateHelper.isConSpeCharacters(name)) {
			result.addErrorMessage(getColumNo(columnName) + apstr
					+ "姓名不能包含特殊字符");
		} else if (validateHelper.illegalStringLength(2, 20, name)) {
			result.addErrorMessage(getColumNo(columnName) + apstr
					+ "姓名只能输入2-20个字符");
		} else if (validateHelper.illegalExculdeParticalChar(name)) {
			result.addErrorMessage(getColumNo(columnName) + apstr
					+ "姓名只能输入数字,字母,中文字符");
		}
	}

	@Override
	public void validatorIdCardNo(String idCardNo, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		String columnName = MAN.equals(apstr) ? "manIdcardNo" : "idCardNo";
		if (validateHelper.emptyString(idCardNo)) {
			result.addErrorMessage(getColumNo(columnName) + apstr + "身份证号码必须输入");
		}
		if (!validateHelper.emptyString(idCardNo)
				&& validateHelper.illegalIdcard(idCardNo)) {
			result.addErrorMessage(getColumNo(columnName) + apstr
					+ "身份证号码输入不正确");
		}
	}

	private void validatorIdCardNoWomen(String idCardNo, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		Date idCardDate = IdCardUtil.parseBirthday(idCardNo);
		if (idCardDate == null) {
			result.addErrorMessage(getColumNo(idCardNo) + apstr + "身份证号码输入不正确");
		} else {
			Date afterDate = null, beforeDate = null;
			Date date = new Date();
			String dates = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String afterDateStr = (Integer.parseInt(dates.substring(0, 4)) - 15)
					+ dates.substring(4, 10);
			String beforeDateStr = (Integer.parseInt(dates.substring(0, 4)) - 49)
					+ dates.substring(4, 10);
			try {
				afterDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(afterDateStr);

				beforeDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(beforeDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (idCardDate.before(beforeDate)) {
				result.addErrorMessage(getColumNo(idCardNo) + apstr
						+ "育龄妇女年龄应在15-49岁之间");
			}
			if (idCardDate.after(afterDate)) {
				result.addErrorMessage(getColumNo(idCardNo) + apstr
						+ "育龄妇女年龄应在15-49岁之间");
			}
		}
	}

	@Override
	public void validatorNativePlaceAddress(String nativePlaceAddress,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		String columnName = MAN.equals(apstr) ? "manNativePlaceAddress"
				: "nativePlaceAddress";
		if (!validateHelper.emptyString(nativePlaceAddress)) {
			if (validateHelper.illegalStringLength(0, 50, nativePlaceAddress)) {
				result.addErrorMessage(getColumNo(columnName) + apstr
						+ "户籍地详址不能输入大于50字符");
			}

		}
	}

	@Override
	public void validatorifMobileNumber(String mobileNumber,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		String columnName = MAN.equals(apstr) ? "manMobileNumber"
				: "mobileNumber";
		if (!validateHelper.emptyString(mobileNumber)
				&& validateHelper.illegalMobilePhone(mobileNumber)) {
			result.addErrorMessage(getColumNo(columnName) + apstr
					+ "联系手机只能输入1开头的11位数字");
		}
	}

	@Override
	public void validatorifTelephone(String telephone, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		String columnName = MAN.equals(apstr) ? "manTelephone" : "telephone";
		if (!validateHelper.emptyString(telephone)) {
			if (validateHelper.illegalStringLength(0, 20, telephone)) {
				result.addErrorMessage(getColumNo(columnName) + apstr
						+ "固定电话不能输入大于20字符");
			} else if (validateHelper.illegalTelephone(telephone)) {
				result.addErrorMessage(getColumNo(columnName) + apstr
						+ "固定电话只能输入数字和-");
			}
		}
	}

	/**
	 * 婚育证发证单位
	 * 
	 * @param certifiedUnit
	 * @param result
	 */
	public static int certifiedUnitLength = 50;

	public void validatorCertifiedUnit(String certifiedUnit,
			ValidateResult result) {
		if (!validateHelper.emptyString(certifiedUnit)) {
			if (validateHelper.illegalStringLength(0, certifiedUnitLength,
					certifiedUnit)) {
				result.addErrorMessage(getColumNo("certifiedUnit")
						+ "婚育证发证单位不能超过" + certifiedUnitLength);
			}
		}
	}

	/**
	 * 发证单位
	 * 
	 * @param certifiedUnit
	 * @param result
	 */
	public void validatorMaternityCardUnit(String certifiedUnit,
			ValidateResult result) {
		if (!validateHelper.emptyString(certifiedUnit)) {
			if (validateHelper.illegalStringLength(0, certifiedUnitLength,
					certifiedUnit)) {
				result.addErrorMessage(getColumNo("maternityCardUnit")
						+ "发证单位不能超过" + certifiedUnitLength);
			}
			if (!validateHelper.isConSpeCharacters(certifiedUnit)) {
				result.addErrorMessage(getColumNo("maternityCardUnit")
						+ "发证单位不能输入特殊字符");
			}
		}

	}

	// 育龄妇女的备注 比较特别 为 600个字符（数据库中存储2000字节）
	@Override
	public void validatorRemark(String remark, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(remark)) {
			if (validateHelper.illegalStringLength(0, 600, remark)) {
				result.addErrorMessage(getColumNo("remark") + apstr
						+ "备注不能输入大于600字符");
			}
		}
	}

	private boolean isDateStr(Date date) {
		if (date == null) {
			return false;
		}
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.format(date);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	private void validatorBoyAndGirlNumber(int boyNumber, int girlNumber,
			ValidateResult result) {
		if (boyNumber != 0) {
			if (boyNumber != 0
					&& validateHelper.illegalNumberZZ(boyNumber + "")) {
				result.addErrorMessage(getColumNo("boyNumber") + "男孩数输入格式不正确");
			}
			if (boyNumber <= 0 || boyNumber >= 10) {
				result.addErrorMessage(getColumNo("boyNumber") + "男孩数为0-9之间的整数");
			}
		}
		if (girlNumber != 0) {
			if (girlNumber != 0
					&& validateHelper.illegalNumberZZ(girlNumber + "")) {
				result.addErrorMessage(getColumNo("girlNumber") + "女孩数输入格式不正确");
			}
			if (girlNumber <= 0 || girlNumber >= 10) {
				result.addErrorMessage(getColumNo("girlNumber")
						+ "女孩数为0-9之间的整数");
			}
		}
	}

	public void checkIsMaternityCard(NurturesWomen domain, ValidateResult result) {
		String isMaternityCard = domain.getIsMaternityCard();
		if (isMaternityCard == null || isMaternityCard.equals("0")) {
			if (StringUtil.isStringAvaliable(domain.getMaternityCardUnit())
					|| StringUtil
							.isStringAvaliable(domain.getMaternityCardNo())
					|| domain.getMaternityCardIssueTime() != null
					|| domain.getMaternityCardValidityTime() != null
					|| domain.getMaternityCardCheckTime() != null
					|| StringUtil.isStringAvaliable(domain
							.getMaternityCardRemark())) {
				result.addErrorMessage(getColumNo("isMaternityCard")
						+ "婚育证信息已填写，请选择‘是’");
			}

		}

	}
}
