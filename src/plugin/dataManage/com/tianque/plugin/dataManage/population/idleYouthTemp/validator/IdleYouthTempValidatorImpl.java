package com.tianque.plugin.dataManage.population.idleYouthTemp.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.idleYouthTemp.domain.IdleYouthTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;
import com.tianque.util.IdCardUtil;

@Component("idleYouthTempValidator")
public class IdleYouthTempValidatorImpl extends
		AbstactDataManageValidator<IdleYouthTemp> {

	@Override
	public ValidateResult validateSpecializedInfo(IdleYouthTemp domain) {

		ValidateResult validateResult = new ValidateResult();
		validatorAgeByIdCardNo(domain.getIdCardNo(), validateResult);

		if (null == domain.getStaffType()) {
			validateResult.addErrorMessage(getColumNo("staffType")
					+ "人员类型 下面必须选一项");
		}
		if (domain.getIdCardNo() != null) {
			Date idCardDate = IdCardUtil.parseBirthday(domain.getIdCardNo());
			if (idCardDate == null) {
				validateResult.addErrorMessage(getColumNo("idCardNo")
						+ "身份证号码输入不正确");
			} else {
				Date beforeDate = null;
				Date afterDate = null;
				Date date = new Date();
				String dates = new SimpleDateFormat("yyyy-MM-dd").format(date);
				String date2 = (Integer.parseInt(dates.substring(0, 4)) - 35)
						+ dates.substring(4, 10);
				String date1 = (Integer.parseInt(dates.substring(0, 4)))
						+ dates.substring(4, 10);
				try {
					beforeDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(date1);
					afterDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (!(idCardDate.after(afterDate) && idCardDate
						.before(beforeDate))) {
					validateResult.addErrorMessage(getColumNo("idCardNo")
							+ "青少年年龄应在0~35之间");
				}
			}

		}
		if (false == domain.isStayInSchool() && null != domain.getSchoolName()) {
			validateResult.addErrorMessage(getColumNo("schoolName")
					+ "不在校住宿，不可填写学校名称！");
		}
		if (true == domain.isStayInSchool()) {
			if (validateHelper.emptyString(domain.getSchoolName())) {
				validateResult.addErrorMessage(getColumNo("schoolName")
						+ "是否在校住宿选择“是”时，学校名称不能为空");
			} else if (validateHelper.illegalExculdeParticalChar(domain
					.getSchoolName())) {
				validateResult.addErrorMessage(getColumNo("schoolName")
						+ "学校名称不能包含特殊字符");
			} else if (validateHelper.illegalStringLength(0, 30,
					domain.getSchoolName())) {
				validateResult.addErrorMessage(getColumNo("schoolName")
						+ "学校名称不能大于30个字符");
			}
		}
		if (null != domain.getWorkCondition()
				&& validateHelper.illegalStringLength(0, 50,
						domain.getWorkCondition())) {
			validateResult.addErrorMessage(getColumNo("workCondition")
					+ "工作情况不能大于50个字符");
		}
		if (null != domain.getGuardianName()) {
			if (validateHelper.illegalStringLength(2, 20,
					domain.getGuardianName())) {
				validateResult.addErrorMessage(getColumNo("guardianName")
						+ "监护人不能小于2个字符，不能大于20个字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getGuardianName())) {
				validateResult.addErrorMessage(getColumNo("guardianName")
						+ "监护人不能输入特殊符号");
			}
		}
		if (null != domain.getGuardianTelephone()
				&& validateHelper.illegalMobilePhone(domain
						.getGuardianTelephone())) {
			validateResult.addErrorMessage(getColumNo("guardianTelephone")
					+ "联系方式输入只能是以1开头的11位数字");
		}
		return validateResult;

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
			if (!(ageNow > 6 && ageNow < 25)) {
				result.addErrorMessage("年龄大于25或者小于6，不是青少年");
			}
		}
	}
}
