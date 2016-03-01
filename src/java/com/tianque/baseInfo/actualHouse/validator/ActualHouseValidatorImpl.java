package com.tianque.baseInfo.actualHouse.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.validate.AbstractHouseValidator;

@Component("actualHouseValidator")
public class ActualHouseValidatorImpl extends
		AbstractHouseValidator<RentalHouse> {

	@Autowired
	public ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(RentalHouse domain) {
		ValidateResult result = super.validate(domain);
		/** 房屋准确地址字段验证 */
		if (validateHelper.emptyString(domain.getAddress())) {
			result.addErrorMessage(getColumNo("address") + "房屋准确地址必须输入");

		} else if (validateHelper.illegalStringLength(2, 50, domain
				.getAddress())) {
			result.addErrorMessage(getColumNo("address")
					+ "房屋准确地址不能输入大于50个字符并且不能少于2个字符");
		}
		if (null != domain.getIsRentalHouse() && domain.getIsRentalHouse()) {
			if (validateHelper.emptyString(domain.getRentalPerson())) {
				result.addErrorMessage(getColumNo("rentalPerson") + "房主不能为空");
			}
			/** 租赁备案证号字段验证 */
			if (!validateHelper.emptyString(domain.getHouseFileNum())
					&& validateHelper.illegalStringLength(2, 50, domain
							.getHouseFileNum())) {
				result.addErrorMessage(getColumNo("houseFileNum")
						+ "租赁证备案号不能输入大于50个字符并且不能少于2个字符");
			}

			/** 出租人字段长度验证 */
			if (!validateHelper.emptyString(domain.getRentalPerson())
					&& validateHelper.illegalStringLength(2, 50, domain
							.getRentalPerson())) {
				result.addErrorMessage(getColumNo("rentalPerson")
						+ "房主不能输入大于50个字符并且不能少于2个字符");
			}

			/** 出租人联系地址字段验证 */
			if (!validateHelper.emptyString(domain.getLessorAddress())
					&& validateHelper.illegalStringLength(2, 50, domain
							.getLessorAddress())) {
				result.addErrorMessage(getColumNo("lessorAddress")
						+ "出租人联系地址不能输入大于50个字符并且不能少于2个字符");
			}
			if (validateHelper.nullObj(domain.getHiddenDangerLevel())) {
				result.addErrorMessage(getColumNo("hiddenDangerLevel")
						+ "隐患程度不能为空");
			}
			// 出租类别必填写
			if (null == domain.getRentalType()) {
				result.addErrorMessage(getColumNo("rentalType")
						+ ("房屋为出租房时，出租房类别必须输入").toString());
			}

			if (domain.getRentalCertificateType() != null) {
				if ("身份证".equals(domain.getRentalCertificateType()
						.getDisplayName())
						&& validateHelper.illegalIdcard(domain
								.getRentalCertificateNumbe())) {
					result.addErrorMessage(getColumNo("rentalCertificateNumbe")
							+ ("出租人证件号码输入不正确").toString());
				} else {
					if ((!validateHelper.emptyString(domain
							.getRentalCertificateNumbe()))
							&& validateHelper.illegalStringLength(2, 20, domain
									.getRentalCertificateNumbe())) {
						result
								.addErrorMessage(getColumNo("rentalCertificateNumbe")
										+ ("出租人证件号码只能输入2-20个字符").toString());
					}
				}
			} else if (!validateHelper.emptyString(domain
					.getRentalCertificateNumbe())
					&& validateHelper.illegalStringLength(2, 20, domain
							.getRentalCertificateNumbe())) {
				result.addErrorMessage(getColumNo("rentalCertificateNumbe")
						+ ("出租人证件号码只能输入2-20个字符").toString());
			}

			if (!validateHelper.emptyString(domain.getRentalTelephone())) {
				if (validateHelper.illegalStringLength(0, 20, domain
						.getRentalTelephone())) {
					result.addErrorMessage(getColumNo("rentalTelephone")
							+ ("出租人联系电话不能输入大于20字符").toString());
				} else if (validateHelper.illegalTelephone(domain
						.getRentalTelephone())) {
					result.addErrorMessage(getColumNo("rentalTelephone")
							+ ("出租人联系电话只能输入数字和-").toString());
				}
			}
			if (!validateHelper.emptyString(domain.getRentalMobileNumber())
					&& validateHelper.illegalMobilePhone(domain
							.getRentalMobileNumber())) {
				result.addErrorMessage(getColumNo("rentalMobileNumber")
						+ ("出租人联系手机只能输入1开头的11位数字").toString());
			}

			/** 出租房登记时间验证 */
			if (null != domain.getRegistDate()
					&& validateHelper.endDateIsSameorBigForStartDate(domain
							.getRegistDate(), CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("registDate")
						+ ("登记时间不能大于当前时间").toString());
			}
			/** 出租房出租时间 */
			/** 出租时间要大于登记时间 */
			if (null != domain.getLessorDate()) {
				if (validateHelper.endDateIsSameorBigForStartDate(domain
						.getLessorDate(), CalendarUtil.getTomorrow())) {
					result.addErrorMessage(getColumNo("lessorDate")
							+ ("出租时间不能大于当前时间").toString());
				}
				if (null != domain.getRegistDate()
						&& validateHelper.endDateIsSameorBigForStartDate(domain
								.getRegistDate(), domain.getLessorDate())) {
					result.addErrorMessage(getColumNo("lessorDate")
							+ ("出租时间不能小于登记时间").toString());
				}
			}

			/** 出租间数验证 */
			if (null != domain.getRoomNumber()) {
				if (validateHelper.illegalInteger(domain.getRoomNumber()
						.toString())) {
					result.addErrorMessage(getColumNo("roomNumber")
							+ ("出租间数只能输入正整数").toString());
				} else if (domain.getRoomNumber() > 999999999) {
					result.addErrorMessage(getColumNo("roomNumber")
							+ ("出租间数不能大于999999999").toString());
				}
			}
			/** 限住人数验证 */
			if (null != domain.getLimitPersons()) {
				if (validateHelper.illegalInteger(domain.getLimitPersons()
						.toString())) {
					result.addErrorMessage(getColumNo("limitPersons")
							+ ("限住人数只能输入正整数").toString());
				} else if (domain.getLimitPersons() > 999999) {
					result.addErrorMessage(getColumNo("limitPersons")
							+ ("限住人数不能大于999999").toString());
				}
			}
			/** 租金验证 */
			if (null != domain.getRentMonth()) {
				if (domain.getRentMonth() > 99999.9
						|| domain.getRentMonth() < 0) {
					result.addErrorMessage(getColumNo("rentMonth")
							+ ("月租金应在0-99999.9之间").toString());
				}
			}
			/** 隐患情况验证 */
			if (!validateHelper.emptyString(domain.getHiddenRectification())
					&& validateHelper.illegalStringLength(0, 200, domain
							.getHiddenRectification())) {
				result.addErrorMessage(getColumNo("hiddenRectification")
						+ ("隐患情况不能输入大于200个字符").toString());
			}
			if (!validateHelper.emptyString(domain.getPropertyName())) {
				if (validateHelper.illegalStringLength(2, 100, domain
						.getPropertyName())) {
					result.addErrorMessage(getColumNo("propertyName")
							+ ("物业管理单位名称不能输入大于100字符并且不能小于两个字符"));
				}
				if (validateHelper.illegalExculdeParticalChar(domain
						.getPropertyName())) {
					result.addErrorMessage(getColumNo("propertyName")
							+ "物业管理单位名称不能输入非法字符");
				}
			}
			/** 验证房产证地址 */
			if (!validateHelper.emptyString(domain.getHouseAddress())) {
				if (validateHelper.illegalStringLength(0, 60, domain
						.getHouseAddress())) {
					result.addErrorMessage(getColumNo("houseAddress")
							+ "房产证地址不能输入大于60个字符");
				}

			}
			if (!validateHelper.emptyString(domain.getBuildingName())) {
				if (validateHelper.illegalStringLength(2, 100, domain
						.getBuildingName())) {
					result.addErrorMessage(getColumNo("buildingName")
							+ ("建筑物名称不能输入大于100字符并且不能小于两个字符"));
				}
				if (validateHelper.illegalExculdeParticalChar(domain
						.getBuildingName())) {
					result.addErrorMessage(getColumNo("buildingName")
							+ "建筑物名称不能输入非法字符");
				}
			}
			// }

			// fateson add 验证
			// if (!validateHelper.emptyString(domain.getCertificateNumbe())) {
		}

		return result;
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

	public boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}

	public boolean illegalBirthWorkRetireDate(Date birthday,
			Date enterWorkDate, Date retireDate) {
		if (enterWorkDate == null && retireDate == null) {
			return false;
		} else if (enterWorkDate != null && retireDate == null) {
			return !birthday.before(enterWorkDate);
		} else if (enterWorkDate == null && retireDate != null) {
			return !birthday.before(retireDate);
		} else if (enterWorkDate != null && retireDate != null) {
			return !(birthday.before(enterWorkDate) && enterWorkDate
					.before(retireDate));
		}
		return true;
	}

	@Override
	public ValidateResult validateSpecializedInfo(RentalHouse domain) {
		ValidateResult result = new ValidateResult();
		if (null == domain.getIsRentalHouse()) {
			result.addErrorMessage(getColumNo("isRentalHouse") + "是否出租房必须输入");
		}
		return result;
	}

}
