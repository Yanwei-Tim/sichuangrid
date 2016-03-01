package com.tianque.baseInfo.overseaPersonnel.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("overseaValidator")
public class OverseaValidatorImpl extends
		AbstractCountrymenValidator<OverseaPersonnel> {

	@Override
	public ValidateResult validateSpecializedInfo(OverseaPersonnel domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.emptyString(domain.getEnglishName())) {
			result.addErrorMessage(getColumNo("englishName") + "英文名必须输入");
		} else if (validateHelper.illegalStringLength(2, 50,
				domain.getEnglishName())) {
			result.addErrorMessage(getColumNo("englishName") + "英文名只能输入2-50个字符");
		} else if (validateHelper.illegalEnglishName(domain.getEnglishName())) {
			result.addErrorMessage(getColumNo("englishName") + "英文名不能输入中文");
		}

		// fateson update 修复 BUG单 #23606 境外人员导入时，如果英文名含有特殊字符，姓名为空，导入提示信息错误
		// 这里注释掉了金外人实体中的getname方法
		if (!validateHelper.emptyString(domain.getName())) {
			if (!validateHelper.isConSpeCharacters(domain.getName())) {
				result.addErrorMessage(getColumNo("name") + "姓名不能包含特殊字符");
			} else if (validateHelper.illegalStringLength(2, 20,
					domain.getName())) {
				result.addErrorMessage(getColumNo("name") + "姓名只能输入2-20个字符");
			} else if (validateHelper.illegalExculdeParticalChar(domain
					.getName())) {
				result.addErrorMessage(getColumNo("name") + "姓名只能输入数字,字母,中文字符");
			}
		}

		if (!typeIsNotNull(domain.getGender())) {
			result.addErrorMessage(getColumNo("gender") + "性别必须输入");
		}

		// fateson 修改 证件种类为非必填项 old 版本
		// if (!typeIsNotNull(domain.getCertificateType())) {
		// result.addErrorMessage(getColumNo("certificateType"), "证件种类必须输入");
		// } else if (!validateHelper.emptyString(domain.getCertificateType()
		// .getDisplayName())
		// && validateHelper.illegalPropertyDictDisplayName(
		// PropertyTypes.CERTIFICATE_TYPE, domain
		// .getCertificateType().getDisplayName())) {
		// result.addErrorMessage(getColumNo("certificateType"), "证件种类输入不正确。");
		// }
		if (!typeIsNotNull(domain.getCertificateType())) {
			result.addErrorMessage(getColumNo("certificateType") + "证件种类必须输入");
		} else if (!validateHelper.emptyString(domain.getCertificateType()
				.getDisplayName())
				&& validateHelper.illegalPropertyDictDisplayName(
						PropertyTypes.CERTIFICATE_TYPE, domain
								.getCertificateType().getDisplayName())) {
			result.addErrorMessage(getColumNo("certificateType") + "证件种类输入不正确。");
		}

		Boolean certificateNo = validateHelper.emptyString(domain
				.getCertificateNo());
		if (certificateNo) {
			result.addErrorMessage(getColumNo("certificateNo") + "证件号码必须输入。");
		}
		if (!certificateNo
				&& validateHelper.illegalStringLength(2, 20,
						domain.getCertificateNo())) {
			result.addErrorMessage(getColumNo("certificateNo")
					+ "证件号码只能输入2-20个字符");
		}
		if (!certificateNo
				&& !validateHelper
						.isConSpeCharacters(domain.getCertificateNo())) {
			result.addErrorMessage(getColumNo("certificateNo") + "证件号码不能输入特殊字符");
		}

		if (!validateOrgIsNotNull(domain.getOrganization())) {
			result.addErrorMessage(getColumNo("organization") + "所属网格不能为空");
		} else if (!validateOrgIsGrid(domain.getOrganization())) {
			result.addErrorMessage(getColumNo("organization") + "所属网格必须为片组片格");
		}

		if (!validateHelper.emptyString(domain.getInflowReason())) {
			if (validateHelper.illegalStringLength(1, 40,
					domain.getInflowReason())) {
				result.addErrorMessage(getColumNo("inflowReason")
						+ "流入原因不能输入大于40字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getInflowReason())) {
				result.addErrorMessage(getColumNo("inflowReason")
						+ "流入原因不能输入特殊字符");
			}
		}

		if (!validateHelper.emptyString(domain.getMail())) {
			if(validateHelper.illegalEmail(domain.getMail())){
				result.addErrorMessage(getColumNo("mail") + "邮箱地址格式不正确");
			}
			if(validateHelper.illegalStringLength(0, 20,
					domain.getMail())){
				result.addErrorMessage(getColumNo("mail") + "邮箱不能输入大于20个字符");
			}
		}

		if (!validateHelper.emptyString(domain.getWorkUnit())) {
			if (validateHelper.illegalStringLength(0, 50, domain.getWorkUnit())) {
				result.addErrorMessage(getColumNo("workUnit")
						+ "工作单位或就读学校不能输入大于50字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getWorkUnit())) {
				result.addErrorMessage(getColumNo("workUnit")
						+ "工作单位或就读学校不能输入特殊字符");
			}
		}

		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			result.addErrorMessage(getColumNo("mobileNumber")
					+ "联系手机只能输入1开头的11位数字");
		}

		if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getTelephone())) {
			result.addErrorMessage(getColumNo("telephone") + "固定电话不能输入大于20字符");
		} else if (!validateHelper.emptyString(domain.getTelephone())
				&& validateHelper.illegalTelephone(domain.getTelephone())) {
			result.addErrorMessage(getColumNo("telephone") + "固定电话只能输入数字和-");
		}

		if (!validateHelper.emptyString(domain.getNationality())) {
			if(!isConSpeCharacters(domain.getNationality())){
				result.addErrorMessage(getColumNo("nationality") + "国籍只能输入字母,中文字符");
			}
			if(validateHelper.illegalStringLength(0, 20,
					domain.getNationality())){
				result.addErrorMessage(getColumNo("nationality") + "国籍不能输入大于20个字符");
			}
		}
		
		if (null != domain.getCertificateStrartDay()
				&& !isDateStr(domain.getCertificateStrartDay())) {
			result.addErrorMessage(getColumNo("certificateStrartDay")
					+ "证件有效期起始时间输入格式有问题");
		}

		if (null != domain.getCertificateEndDay()
				&& !isDateStr(domain.getCertificateEndDay())) {
			result.addErrorMessage(getColumNo("certificateEndDay")
					+ "证件有效期结束时间输入格式有问题");
		}

		if (null != domain.getArrivalTime()
				&& !isDateStr(domain.getArrivalTime())) {
			result.addErrorMessage(getColumNo("arrivalTime") + "抵达时间输入格式有问题");
		}

		if (null != domain.getLeaveTime() && !isDateStr(domain.getLeaveTime())) {
			result.addErrorMessage(getColumNo("leaveTime") + "离开时间输入格式有问题");
		}
		
		/**抵达时间不能大于当前时间*/
		if(null !=domain.getArrivalTime() && 
				!validateHelper.endDateIsSameorBigForStartDate(
						CalendarUtil.getTomorrow(), domain.getArrivalTime())){
			result.addErrorMessage(getColumNo("arrivalTime")
					+ "抵达时间不能大于当前时间");
		}
//		/**离开时间不能大于当前时间*/
//		if(null !=domain.getLeaveTime() && 
//				!validateHelper.endDateIsSameorBigForStartDate(
//						CalendarUtil.getTomorrow(), domain.getLeaveTime())){
//			result.addErrorMessage(getColumNo("leaveTime")
//					+ "抵达时间不能大于当前时间");
//		}
		
		// fateson add
		if (null != domain.getArrivalTime() && null != domain.getLeaveTime()) {
			if (validateHelper.endDateIsSameorBigForStartDateNotEqual(
					domain.getArrivalTime(), domain.getLeaveTime())) {
				result.addErrorMessage(getColumNo("arrivalTime")
						+ "抵达时间不能大于离开时间");
			}
		}

		if (null != domain.getCertificateStrartDay()
				&& null != domain.getCertificateEndDay()) {
			if (validateHelper.endDateIsSameorBigForStartDateNotEqual(
					domain.getCertificateStrartDay(),
					domain.getCertificateEndDay())) {
				result.addErrorMessage(getColumNo("certificateStrartDay")
						+ "证件有效期开始时间不能大于证件有效期结束时间");
			}
		}

		if (!validateHelper.emptyString(domain.getRemark())) {
			if (validateHelper.illegalStringLength(0, 300, domain.getRemark())) {
				result.addErrorMessage(getColumNo("remark") + "备注不能输入大于300字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getRemark())) {
				result.addErrorMessage(getColumNo("remark") + "备注不能输入特殊字符");
			}
		}
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

	public boolean isConSpeCharacters(String string) {
		if (string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*", "").length() == 0) {
			return true;
		}
		return false;
	}

}
