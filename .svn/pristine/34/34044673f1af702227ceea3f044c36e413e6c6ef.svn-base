package com.tianque.baseInfo.elderlyPeople.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.validate.ValidateResult;
import com.tianque.util.IdCardUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("elderlyPeopleValidator")
public class ElderlyPeopleValidatorImpl extends
		AbstractCountrymenValidator<ElderlyPeople> {

	@Override
	public ValidateResult validateSpecializedInfo(ElderlyPeople domain) {
		ValidateResult result = new ValidateResult();
		validatorAgeByIdCardNo(domain.getIdCardNo(), result);
		if (!validateHelper.emptyString(domain.getElderPeopleId())
				&& validateHelper.illegalStringLength(0, 18,
						domain.getElderPeopleId())) {
			result.addErrorMessage(getColumNo("elderPeopleId")
					+ "老年人证号不能大于18个字符");
		}
		if (!validateHelper.nullObj(domain.getBirthday())
				&& !validateHelper.nullObj(domain.getEnterWorkDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getEnterWorkDate(), domain.getBirthday())) {
			result.addErrorMessage(getColumNo("enterWorkDate")
					+ "参加工作日期不能小于出生日期");
		}
		if (domain.getEnterWorkDate() != null) {
			if (domain.getEnterWorkDate().after(new Date())) {
				result.addErrorMessage(getColumNo("enterWorkDate")
						+ "参加工作日期不能大于当前时间");
			}
		}
		if (!validateHelper.nullObj(domain.getEnterWorkDate())
				&& !validateHelper.nullObj(domain.getRetireDate())
				&& !validateHelper.endDateIsSameorBigForStartDate(
						domain.getRetireDate(), domain.getEnterWorkDate())) {
			result.addErrorMessage(getColumNo("retireDate") + "离职日期不能小于入职日期");
		}
		if (domain.getRetireDate() != null) {
			if (domain.getRetireDate().after(new Date())) {
				result.addErrorMessage(getColumNo("retireDate")
						+ "离职日期不能大于当前时间");
			}
		}
		if (!validateHelper.emptyString(domain.getRetireUnitAndDuty())) {
			if (validateHelper.illegalStringLength(0, 30,
					domain.getRetireUnitAndDuty())) {
				result.addErrorMessage(getColumNo("retireUnitAndDuty")
						+ "离退休单位不能输入大于30个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain
					.getRetireUnitAndDuty())) {
				result.addErrorMessage(getColumNo("retireUnitAndDuty")
						+ "离退休单位不能输入非法字符");
			}
		}
		if (!validateHelper.emptyString(domain.getZhiwu())) {
			if (validateHelper.illegalStringLength(0, 30, domain.getZhiwu())) {
				result.addErrorMessage(getColumNo("zhiwu")
						+ "离退休单位的职务不能输入大于30个字符");
			}
			if (validateHelper.illegalExculdeParticalChar(domain.getZhiwu())) {
				result.addErrorMessage(getColumNo("zhiwu") + "离退休单位的职务不能输入非法字符");
			}
		}
		if (domain.getPension() != null
				&& (domain.getPension() > 999999999 || domain.getPension() < 0 || domain
						.getPension().toString().length() > 11)) {
			result.addErrorMessage(getColumNo("pension")
					+ "退休金不能大于999999999或者小于0元");
		}
		if (domain.getIdCardNo() != null) {
			Date idCardDate = IdCardUtil.parseBirthday(domain.getIdCardNo());
			if (idCardDate == null) {
				result.addErrorMessage(getColumNo("idCardNo") + "身份证号码输入不正确");
			} else {
				Date afterDate = null;
				Date date = new Date();
				String dates = new SimpleDateFormat("yyyy-MM-dd").format(date);
				String date2 = (Integer.parseInt(dates.substring(0, 4)) - 60)
						+ dates.substring(4, 10);
				try {
					afterDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (idCardDate.after(afterDate)) {
					result.addErrorMessage(getColumNo("idCardNo")
							+ "老年人年龄应在60岁以上");
				}
			}

		}
		return result;
	}

	private void validatorAgeByIdCardNo(String idCardNo, ValidateResult result) {
		String year = "";
		Calendar cal = Calendar.getInstance();
		if (idCardNo != null && idCardNo.length() == 18) {
			idCardNo = idCardNo.substring(6, 14);
			year = idCardNo.substring(0, 4);
		} else if (idCardNo != null && idCardNo.length() == 15) {
			idCardNo = idCardNo.substring(6, 12);
			String temp = idCardNo.substring(0, 2);
			year = String.valueOf(Integer.parseInt(temp) + 1900);
		}
		if (!"".equals(year)) {
			int elementDate = Integer.parseInt(year);
			int nowYear = cal.get(Calendar.YEAR);
			int ageNow = nowYear - elementDate;
			if (ageNow < 60) {
				result.addErrorMessage("年龄小于60，不是老年人");
			}
		}
	}
}
