package com.tianque.plugin.dataManage.population.nurturesWomenTemp.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.domain.property.LicenseSituation;
import com.tianque.domain.property.MaritalState;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.dataManage.population.nurturesWomenTemp.domain.NurturesWomenTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.IdCardUtil;

@Component("nurturesWomenTempValidator")
public class NurturesWomenTempValidatorImpl extends
		AbstactDataManageValidator<NurturesWomenTemp> {

	@Autowired
	protected PropertyDictService propertyDictService;
	private static final String MAN = "男方";

	private static final String WOMAN = "女";

	@Override
	public ValidateResult validateSpecializedInfo(NurturesWomenTemp domain) {
		ValidateResult result = new ValidateResult();

		if (null != domain.getMaternityCardCheckTime()
				&& !isDateStr(domain.getMaternityCardCheckTime())) {
			result.addErrorMessage(getColumNo("maternityCardCheckTime")
					+ "查验时间输入格式有问题");
		}
		/** 时间不能大于当前时间 */
		if (null != domain.getMaternityCardCheckTime()) {
			if (validateHelper.endDateIsSameorBigForStartDate(
					domain.getMaternityCardCheckTime(), CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("maternityCardCheckTime")
						+ "查验时间不能大于当前日期");
			}
		}
		
		/**检查情况字段验证 */
		if(!validateHelper.emptyString(domain.getMaternityCardRemark())){
			if(validateHelper.illegalStringLength(0, 300, domain.getMaternityCardRemark())){
				result.addErrorMessage(getColumNo("maternityCardRemark") + "检查情况输入不能大于300"
						+ "字符");
			}
			if(!validateHelper.isConSpeCharacters(domain.getMaternityCardRemark())){
				result.addErrorMessage(getColumNo("maternityCardRemark") + "检查情况不能输入特殊字符");
			}
			
		}

		Boolean displayName = domain.getGender() == null;
		if (displayName) {
			result.addErrorMessage(getColumNo("gender") + "性别必须输入");
		}
		if (!displayName) {
			if (!domain.getGender().getDisplayName().equals(WOMAN)) {
				result.addErrorMessage(getColumNo("gender") + "性别只能为女性");
			}
		}
		if (!displayName && domain.getGender().getDisplayName().equals(WOMAN)) {
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

		if (null != domain.getMaternityCardIssueTime()
				&& !isDateStr(domain.getMaternityCardIssueTime())) {
			result.addErrorMessage(getColumNo("maternityCardIssueTime")
					+ "发证时间输入格式有问题");
		}

		if (null != domain.getMaternityCardIssueTime()) {
			if (validateHelper.endDateIsSameorBigForStartDate(
					domain.getMaternityCardIssueTime(), CalendarUtil.getTomorrow())) {
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
				result.addErrorMessage(getColumNo("marriageRegistrationTime")
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
					if (licenseSituation.getDisplayName().equals(
							LicenseSituation.getInternalProperties()
									.get(LicenseSituation.NEVERLICENSE)
									.getDisplayName())) {
						result.addErrorMessage(getColumNo("fertilityServicesNo")
								+ "领证情况为为领证时，生育服务证无效");
					}
					if (validateHelper.illegalStringLength(0, 50,
							fertilityServicesNo)) {
						result.addErrorMessage(getColumNo("fertilityServicesNo")
								+ "生育服务证号不能输入大于50字符");
					}
				}

				if (licenseTime != null) {
					if (!isDateStr(licenseTime)) {
						result.addErrorMessage(getColumNo("licenseTime")
								+ "领证时间输入格式有问题");
					}

					/** 领证时间不能大于当前时间 */
					if (validateHelper.endDateIsSameorBigForStartDate(
							licenseTime, CalendarUtil.getTomorrow())) {
						result.addErrorMessage(getColumNo("licenseTime")
								+ "领证时间不能大于当前日期");
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
							+ "再婚时间输入格式有问题");
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
			if (marriageRegistrationTimeDate != null
					&& !validateHelper
							.endDateIsSameorBigForStartDate(
									marriageOrDivorceTime,
									marriageRegistrationTimeDate)) {
				result.addErrorMessage(getColumNo("marriageOrDivorceTime")
						+ "最近再婚时间不能小于离婚时间");
			}
		}

		String contraceptiveMethod = domain.getContraceptiveMethod();
		if (!validateHelper.emptyString(contraceptiveMethod) ) {
			if(validateHelper.illegalStringLength(0, 50,
					contraceptiveMethod)){
				result.addErrorMessage(getColumNo("contraceptiveMethod")
						+ "避孕方法不能输入大于50字符");
			}
			if(!validateHelper.isConSpeCharacters(contraceptiveMethod)){
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
				&& validateHelper.illegalStringLength(0, 30, singleChildCardno)) {
			result.addErrorMessage(getColumNo("singleChildCardno")
					+ "独生子女证号输入大于30字符");
		}
		String certifiedUnit = domain.getCertifiedUnit();
		if (!validateHelper.emptyString(certifiedUnit)){
			
			if(validateHelper.illegalStringLength(0, 50, certifiedUnit)){
					result.addErrorMessage(getColumNo("certifiedUnit") + "发证单位输入大于50字符");
			}
			if(!validateHelper.isConSpeCharacters(certifiedUnit)){
				result.addErrorMessage(getColumNo("certifiedUnit")
						+ "发证单位不能输入特殊字符");
			}
		}
		String maternityCardUnit = domain.getMaternityCardUnit();
		if (!validateHelper.emptyString(maternityCardUnit)) {
			if(validateHelper.illegalStringLength(0, 50, maternityCardUnit)){
				result.addErrorMessage(getColumNo("maternityCardUnit")
						+ "孕育证发证单位输入大于50字符");
			}
			if(!validateHelper.isConSpeCharacters(maternityCardUnit)){
				result.addErrorMessage(getColumNo("maternityCardUnit")
						+ "孕育证发证单位不能输入特殊字符");
			}
			
		}
		String maternityCardNo = domain.getMaternityCardNo();
		if (!validateHelper.emptyString(maternityCardNo)
				&& validateHelper.illegalStringLength(0, 30, maternityCardNo)) {
			result.addErrorMessage(getColumNo("maternityCardNo")
					+ "婚育证号输入大于30字符");
		}
		int boyNumber = domain.getBoyNumber();
		if (boyNumber != 0) {
			if (boyNumber != 0
					&& validateHelper.illegalNumberZZ(boyNumber + "")) {
				result.addErrorMessage(getColumNo("boyNumber") + "男孩数输入格式不正确");
			}
			if (boyNumber <= 0 || boyNumber >= 10) {
				result.addErrorMessage(getColumNo("boyNumber") + "男孩数为0-9之间的整数");
			}
		}
		int girlNumber = domain.getGirlNumber();
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

		/** 男方信息验证 */
		if (!validateHelper.emptyString(domain.getManName())) {
			validatorManName(domain.getManName(), result, MAN);
		}

		if (!validateHelper.emptyString(domain.getManIdcardNo())) {
			validatorManIdCardNo(domain.getManIdcardNo(), result, MAN);
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

		validatorifManMobileNumber(domain.getManMobileNumber(), result, MAN);
		validatorifManTelephone(domain.getManTelephone(), result, MAN);
		validatorManNation(domain.getManNation(), result, MAN);
		validatorManPoliticalBackground(domain.getManPoliticalBackground(),
				result, MAN);
		validatorManSchooling(domain.getManSchooling(), result, MAN);
		validatorManCareer(domain.getManCareer(), result, MAN);
		validatorManWorkUnit(domain.getManWorkUnit(), result, MAN);
		if (!validateHelper.emptyString(domain.getManProvince())
				&& !validateHelper.emptyString(domain.getManCity())
				&& !validateHelper.emptyString(domain.getManDistrict())) {
			validatorManProvinceAndCityAndDistrict(domain.getManProvince(),
					domain.getManCity(), domain.getManDistrict(), result, MAN);
		}
		validatorManNativePlaceAddress(domain.getManNativeplaceAddress(),
				result, MAN);
		validatorManRemark(domain.getManRemark(), result, MAN);
		validatorCurrentAddress(domain, result);
		isMaternityCardCheck(domain, result);

		return result;
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

	public void validatorManName(String name, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(name)) {
			if (!validateHelper.isConSpeCharacters(name)) {
				result.addErrorMessage(getColumNo("manName") + apstr
						+ "姓名不能包含特殊字符");
			} else if (validateHelper.illegalStringLength(2, 20, name)) {
				result.addErrorMessage(getColumNo("manName") + apstr
						+ "姓名只能输入2-20个字符");
			} else if (validateHelper.illegalExculdeParticalChar(name)) {
				result.addErrorMessage(getColumNo("manName") + apstr
						+ "姓名只能输入数字,字母,中文字符");
			}
		}
	}

	public void validatorManIdCardNo(String idCardNo, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(idCardNo)
				&& validateHelper.illegalIdcard(idCardNo)) {
			result.addErrorMessage(getColumNo("manIdcardNo") + apstr
					+ "身份证号码输入不正确");
		}
	}

	public void validatorifManMobileNumber(String mobileNumber,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(mobileNumber)
				&& validateHelper.illegalMobilePhone(mobileNumber)) {
			result.addErrorMessage(getColumNo("manMobileNumber") + apstr
					+ "联系手机只能输入1开头的11位数字");
		}
	}

	public void validatorifManTelephone(String telephone,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(telephone)) {
			if (validateHelper.illegalStringLength(0, 20, telephone)) {
				result.addErrorMessage(getColumNo("manTelephone") + apstr
						+ "固定电话不能输入大于20字符");
			} else if (validateHelper.illegalTelephone(telephone)) {
				result.addErrorMessage(getColumNo("manTelephone") + apstr
						+ "固定电话只能输入数字和-");
			}
		}
	}

	public void validatorManNation(PropertyDict nation, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != nation
				&& !validateHelper.emptyString(nation.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.NATION, nation.getDisplayName()))
				result.addErrorMessage(getColumNo("manNation") + apstr
						+ "民族输入不正确");
		}
	}

	public void validatorManPoliticalBackground(
			PropertyDict politicalBackground, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != politicalBackground
				&& !validateHelper.emptyString(politicalBackground
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.POLITICAL_BACKGROUND,
					politicalBackground.getDisplayName()))
				result.addErrorMessage(getColumNo("manPoliticalBackground")
						+ apstr + "政治面貌输入不正确");
		}
	}

	public void validatorManSchooling(PropertyDict schooling,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != schooling
				&& !validateHelper.emptyString(schooling.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.SCHOOLING, schooling.getDisplayName()))
				result.addErrorMessage(getColumNo("manSchooling") + apstr
						+ "文化程度输入不正确");
		}
	}

	public void validatorManCareer(PropertyDict career, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != career
				&& !validateHelper.emptyString(career.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.CAREER, career.getDisplayName()))
				result.addErrorMessage(getColumNo("manCareer") + apstr
						+ "职业输入不正确");
		}
	}

	public void validatorManWorkUnit(String workUnit, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(workUnit)
				&& validateHelper.illegalStringLength(0, 50, workUnit)) {
			result.addErrorMessage(getColumNo("manWorkUnit") + apstr
					+ "工作单位或就读学校不能输入大于50字符");
		}
		if (!validateHelper.emptyString(workUnit)
				&& !validateHelper.isConSpeCharacters(workUnit)) {
			result.addErrorMessage(getColumNo("manWorkUnit") + apstr
					+ "工作单位或就读学校不能输入特殊字符");
		}
	}

	public void validatorManProvinceAndCityAndDistrict(String province,
			String city, String district, ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(province)
				&& validateHelper.illegalStringLength(0, 20, province)) {
			result.addErrorMessage(getColumNo("manProvince") + apstr
					+ "户籍地省不能输入大于20字符");
		}
		if (!validateHelper.emptyString(city)
				&& validateHelper.illegalStringLength(0, 20, city)) {
			result.addErrorMessage(getColumNo("manCity") + apstr
					+ "户籍地市不能输入大于20字符");
		}
		if (!validateHelper.emptyString(district)
				&& validateHelper.illegalStringLength(0, 20, district)) {
			result.addErrorMessage(getColumNo("manDistrict") + apstr
					+ "户籍地县不能输入大于20字符");
		}
	}

	public void validatorManNativePlaceAddress(String nativePlaceAddress,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(nativePlaceAddress)) {
			if (validateHelper.illegalStringLength(0, 50, nativePlaceAddress)) {
				result.addErrorMessage(getColumNo("manNativeplaceAddress")
						+ apstr + "户籍地详址不能输入大于50字符");
			} else if (!validateHelper.isConSpeCharacters(nativePlaceAddress)) {
				result.addErrorMessage(getColumNo("manNativeplaceAddress")
						+ apstr + "户籍地详址不能包含特殊字符");
			}
		}
	}

	public void validatorManRemark(String remark, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(remark)) {
			if (validateHelper.illegalStringLength(0, 300, remark)) {
				result.addErrorMessage(getColumNo("manRemark") + apstr
						+ "备注不能输入大于300字符");
			}
		}
	}

	public void validatorCurrentAddress(NurturesWomenTemp domain,
			ValidateResult result) {
		if (null != domain.getManCurrentAddressType()
				&& propertyDictService.getPropertyDictById(
						domain.getManCurrentAddressType().getId())
						.getInternalId() != CurrentAddressType.OTHER) {
			if (validateHelper.emptyString(domain.getManCommunity())) {
				result.addErrorMessage(getColumNo("manCommunity")
						+ "房屋准确地址必须输入");
			} else if (validateHelper.illegalStringLength(0, 20,
					domain.getManCommunity())) {
				result.addErrorMessage(getColumNo("manCommunity")
						+ "房屋小区不能输入大于20字符");
			}
			if (!validateHelper.emptyString(domain.getManBlock())) {
				if (validateHelper.illegalStringLength(0, 8,
						domain.getManBlock())) {
					result.addErrorMessage(getColumNo("manBlock")
							+ "房屋幢不能输入大于8字符");
				}
			}
			if (!validateHelper.emptyString(domain.getManUnit())) {
				if (validateHelper.illegalStringLength(0, 6,
						domain.getManUnit())) {
					result.addErrorMessage(getColumNo("manUnit")
							+ "房屋单元不能输入大于6字符");
				}
			}
			if (!validateHelper.emptyString(domain.getManRoom())) {
				if (validateHelper.illegalStringLength(0, 10,
						domain.getManRoom())) {
					result.addErrorMessage(getColumNo("manRoom")
							+ "房屋室不能输入大于10字符");
				}
			}
		} else {
			if (!validateHelper.emptyString(domain.getManCommunity())) {
				if (validateHelper.illegalStringLength(0, 50,
						domain.getManCommunity())) {
					result.addErrorMessage(getColumNo("manCommunity")
							+ ("房屋地址不能输入大于50字符"));
				}
			}
		}
	}

	// 是否有婚育证验证
	public void isMaternityCardCheck(NurturesWomenTemp domain,
			ValidateResult result) {
		if ((null != domain.getMaternityCardUnit()
				|| null != domain.getMaternityCardNo()
				|| null != domain.getMaternityCardIssueTime()
				|| null != domain.getMaternityCardValidityTime()
				|| null != domain.getMaternityCardCheckTime() || null != domain
				.getMaternityCardRemark())
				&& (null == domain.getIsMaternityCard() || "0".equals(domain
						.getIsMaternityCard()))) {
			result.addErrorMessage(getColumNo("isMaternityCard")
					+ ("有婚育证，才可填写婚育证信息！请重新选择‘是否持有婚育证’项"));
		}
	}
}
