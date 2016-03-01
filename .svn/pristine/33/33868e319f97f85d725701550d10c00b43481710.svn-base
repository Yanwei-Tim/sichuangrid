package com.tianque.baseInfo.rentalHouse.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.rentalHouse.dao.RentalHouseDao;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.validate.ValidateResult;
import com.tianque.validate.AbstractHouseValidator;

@Component("rentalHouseValidator")
public class RentalHouseValidatorImpl extends
		AbstractHouseValidator<RentalHouse> {

	public final static Logger logger = LoggerFactory
			.getLogger(RentalHouseValidatorImpl.class);

	@Autowired
	private RentalHouseDao houseInfoDao;

	@Override
	public ValidateResult validateSpecializedInfo(RentalHouse domain) {
		ValidateResult result = new ValidateResult();
		if (!validateHelper.emptyString(domain.getHouseFileNum())
				&& validateHelper.illegalStringLength(0, 70,
						domain.getHouseFileNum())) {
			result.addErrorMessage(getColumNo("houseFileNum")
					+ ("租赁备案证号不能大于70个字符").toString());
		}
		if (validateHelper.emptyString(domain.getRentalPerson())) {
			result.addErrorMessage(getColumNo("rentalPerson")
					+ ("出租人姓名必须输入").toString());
		} else {
			if (validateHelper.illegalStringLength(0, 25,
					domain.getRentalPerson())) {
				result.addErrorMessage(getColumNo("rentalPerson")
						+ ("出租人姓名不能大于25个字符").toString());
			}
		}
		if (!validateHelper.emptyString(domain.getRentalTelephone())) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getRentalTelephone())) {
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
		if (!validateHelper.emptyString(domain.getLessorAddress())
				&& validateHelper.illegalStringLength(0, 60,
						domain.getLessorAddress())) {
			result.addErrorMessage(getColumNo("lessorAddress")
					+ ("出租人联系地址不能大于60个字符").toString());
		}
		if (null == domain.getHiddenDangerLevel()) {
			result.addErrorMessage(getColumNo("hiddenDangerLevel")
					+ ("隐患等级必须输入").toString());
		}

		if (null == domain.getRentalType()) {
			result.addErrorMessage(getColumNo("rentalType")
					+ ("出租房类别必须输入").toString());
		}

		if (null != domain.getRoomNumber() && domain.getRoomNumber() <= 0) {
			result.addErrorMessage(getColumNo("roomNumber")
					+ ("出租间数不能小于零").toString());
		}

		if (null != domain.getLimitPersons() && domain.getLimitPersons() < 0) {
			result.addErrorMessage(getColumNo("limitPersons")
					+ ("限住人数不能小于零").toString());
		}

		if (null != domain.getRentMonth() && domain.getRentMonth() <= 0) {
			result.addErrorMessage(getColumNo("rentMonth")
					+ ("月租金不能小于零").toString());
		}
		if (null != domain.getCertificateNumbe()
				&& null == domain.getCertificateType()) {
			result.addErrorMessage(getColumNo("certificateType")
					+ ("请选择证件类型").toString());
		}
		if (!validateHelper.emptyString(domain.getCertificateNumbe())) {
			if (null != domain.getCertificateType()) {
				if ("身份证".equals(domain.getCertificateType().getDisplayName())
						&& validateHelper.illegalIdcard(domain
								.getCertificateNumbe())) {
					result.addErrorMessage(getColumNo("certificateNumbe")
							+ ("产权人证件号码输入不正确").toString());
				} else {
					if (validateHelper.illegalStringLength(2, 20,
							domain.getCertificateNumbe())) {
						result.addErrorMessage(getColumNo("certificateNumbe")
								+ ("产权人证件号码只能输入2-20个字符").toString());
					}
				}
			}
		}
		if (!validateHelper.emptyString(domain.getRentalCertificateNumbe())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getRentalCertificateNumbe())) {
			result.addErrorMessage(getColumNo("rentalCertificateNumbe")
					+ ("出租人证件号码不能大于20个字符").toString());
		}
		if (null != domain.getRentalCertificateNumbe()
				&& null == domain.getRentalCertificateType()) {
			result.addErrorMessage(getColumNo("rentalCertificateType")
					+ ("请选择证件类型").toString());
		}
		if (!validateHelper.emptyString(domain.getRentalCertificateNumbe())) {
			if (null != domain.getRentalCertificateType()
					&& "身份证".equals(domain.getRentalCertificateType()
							.getDisplayName())) {
				if (validateHelper.illegalIdcard(domain
						.getRentalCertificateNumbe())) {
					result.addErrorMessage(getColumNo("rentalCertificateNumbe")
							+ ("出租人身份证号码输入不正确").toString());
				}
			} else {
				if (validateHelper.illegalStringLength(0, 20,
						domain.getRentalCertificateNumbe())) {
					result.addErrorMessage(getColumNo("rentalCertificateNumbe")
							+ ("证件号码不能输入大于20字符").toString());
				}
			}
		}
		if (!validateHelper.emptyString(domain.getRemark())
				&& validateHelper.illegalStringLength(0, 450,
						domain.getRemark())) {
			result.addErrorMessage(getColumNo("remark")
					+ ("备注不能输入大于450字符").toString());
		}
		// if (!StringUtils.isEmpty(domain.getHouseCode())) {
		// RentalHouse rentalHouse = this.houseInfoDao
		// .getHouseInfoByHouseCodeAndOrganizationId(domain
		// .getHouseCode(), domain.getOrganization().getId());
		// if (null != rentalHouse) {
		// if (null != domain.getId()
		// && domain.getId().longValue() != rentalHouse.getId()
		// .longValue()) {
		// result.addErrorMessage(getColumNo("houseCode") + ("房屋编号为[")
		// + domain.getHouseCode() + ("]的出租房已存在"));
		// }
		// }
		// }
		// 导入 去掉该 功能
		// if (null == domain.getIsRentalHouse()) {
		// result.addErrorMessage(getColumNo("isRentalHouse") + "是否出租房必须输入");
		// }
		return result;
	}
}
