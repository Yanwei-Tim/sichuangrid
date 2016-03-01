package com.tianque.validate;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.xichang.working.domain.CommonWorking;

/**
 * @ClassName: AbstractWorkingValidator
 * @Description: 三本台账验证类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 24, 2013 4:19:47 PM
 * @param <T>
 */
public abstract class AbstractWorkingValidator<T> implements DomainValidator<T> {
	@Autowired
	protected ValidateHelper validateHelper;

	public abstract ValidateResult validateSpecializedInfo(T domain);

	public ValidateResult validateBaseInfo(CommonWorking domain) {
		ValidateResult result = new ValidateResult();
		validateSerialNumber(domain.getSerialNumber(), result, null);
		validatorServerContractor(domain.getServerContractor(), result, null);
		validatorServerTelephone(domain.getServerTelephone(), result, null);
		validatorServerJob(domain.getServerJob(), result, null);
		validatorServerUnit(domain.getServerUnit(), result, null);
		return result;
	}

	public void validateSerialNumber(String serialNumber,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(serialNumber)) {
			result.addErrorMessage(getColumNo("serialNumber") + apstr
					+ "编号必须输入");
		} else if (validateHelper.illegalStringLength(2, 30, serialNumber)) {
			result.addErrorMessage(getColumNo("serialNumber") + apstr
					+ "编号只能输入2-30个字符");
		}
	}

	public void validatorServerUnit(String serverUnit, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(serverUnit)
				&& validateHelper.illegalStringLength(2, 50, serverUnit)) {
			result.addErrorMessage(getColumNo("serverUnit") + apstr
					+ "服务联系人单位只能输入2-50个字符");
		}
	}

	public void validatorServerJob(String serverJob, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(serverJob)
				&& validateHelper.illegalStringLength(2, 20, serverJob)) {
			result.addErrorMessage(getColumNo("serverJob") + apstr
					+ "服务职务只能输入2-20个字符");
		}
	}

	public void validatorServerTelephone(String serverTelephone,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(serverTelephone)
				&& validateHelper.illegalStringLength(2, 15, serverTelephone)) {
			result.addErrorMessage(getColumNo("serverTelephone") + apstr
					+ "服务联系电话只能输入2-15个字符");
		}
	}

	public void validatorPermanentAddress(String permanentAddress,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(permanentAddress)
				&& validateHelper.illegalStringLength(2, 50, permanentAddress)) {
			result.addErrorMessage(getColumNo("permanentAddress") + apstr
					+ "常住地址只能输入2-50个字符");
		}
	}

	public void validatorServerContractor(String serverContractor,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(serverContractor)
				&& validateHelper.illegalStringLength(2, 20, serverContractor)) {
			result.addErrorMessage(getColumNo("serverContractor") + apstr
					+ "服务联系人只能输入2-20个字符");
		}
	}

	public void validatorIdCardNo(String idCardNo, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(idCardNo)) {
			result.addErrorMessage(getColumNo("idCardNo") + apstr + "身份证号码必须输入");
		} else if (!validateHelper.emptyString(idCardNo)
				&& validateHelper.illegalIdcard(idCardNo)) {
			result.addErrorMessage(getColumNo("idCardNo") + apstr
					+ "身份证号码输入不正确");
		}
	}

	public void validatorName(String name, ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(name)) {
			result.addErrorMessage(getColumNo("name") + apstr + "姓名必须输入");
		} else if (!validateHelper.isConSpeCharacters(name)) {
			result.addErrorMessage(getColumNo("name") + apstr + "姓名不能包含特殊字符");
		} else if (validateHelper.illegalStringLength(2, 20, name)) {
			result.addErrorMessage(getColumNo("name") + apstr + "姓名只能输入2-20个字符");
		} else if (validateHelper.illegalExculdeParticalChar(name)) {
			result.addErrorMessage(getColumNo("name") + apstr
					+ "姓名只能输入数字,字母,中文字符");
		}
	}

	public ValidateResult validate(T domain) {
		ValidateResult validateResult = new ValidateResult();
		validateResult
				.addAllErrorMessage(validateBaseInfo((CommonWorking) domain));
		validateResult.addAllErrorMessage(validateSpecializedInfo(domain));
		return validateResult;
	}

	public String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))) {
			bf.append("第[").append(ExcelImportHelper.realRow.get())
					.append("]行");
			bf.append("第[")
					.append(Integer.valueOf(ExcelImportHelper
							.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}

	public boolean typeIsNotNull(PropertyDict p) {
		if (p == null || p.getId() == null) {
			return false;
		}
		return true;
	}

	public boolean validateOrgIsNotNull(Organization org) {
		if (org == null) {
			return false;
		}
		if (org != null && org.getId() == null) {
			return false;
		}
		return true;
	}

}
