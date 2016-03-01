package com.tianque.validate;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public abstract class AbstractHouseValidator<T> implements DomainValidator<T> {
	@Autowired
	protected ValidateHelper validateHelper;

	@Autowired
	protected OrganizationDubboService organizationDubboService;

	@Autowired
	protected PropertyDictService propertyDictService;

	public abstract ValidateResult validateSpecializedInfo(T domain);

	public ValidateResult validateHouseBaseInfo(RentalHouse domain) {
		ValidateResult result = new ValidateResult();
		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			result.addErrorMessage(getColumNo("organization") + "所属网格不能为空");
		}
		// fateson add 添加非空判断
		if (!validateHelper.emptyString(domain.getHouseCode())) {
			/** 房屋编号字符长度验证 */
			if (validateHelper
					.illegalStringLength(0, 50, domain.getHouseCode())) {
				result.addErrorMessage(getColumNo("houseCode")
						+ "房屋编号不能输入大于50个字符");
			}

			if (validateHelper.notLettersOrNum(domain.getHouseCode())) {
				result.addErrorMessage(getColumNo("houseCode")
						+ "房屋编号只能输入英文字母和数字");
			}
		}

		/** 房产证地址字段长度验证 */
		if (!validateHelper.emptyString(domain.getHouseAddress())) {
			if (validateHelper.illegalStringLength(2, 60,
					domain.getHouseAddress())) {
				result.addErrorMessage(getColumNo("houseAddress")
						+ "房产证地址不能输入大于60个字符并且不能少于2个字符");
			}
		}

		/** 房屋来源数据验证 */
		if (domain.getHouseSource() != null
				&& "自有产权".equals(domain.getHouseSource().getDisplayName())) {
			if (domain.getRentalBuildings() != null) {
				result.addErrorMessage(getColumNo("rentalBuildings")
						+ "房屋来源为自有产权则不能输入租赁公房数据");
			}
		}
		if (domain.getHouseSource() != null
				&& "租赁公房".equals(domain.getHouseSource().getDisplayName())) {
			if (domain.getOwnProperty() != null) {
				result.addErrorMessage(getColumNo("ownProperty")
						+ "房屋来源为租赁公房则不能输入自有产权数据");
			}
		}

		if (orgIsNotNull && !validateOrgIsGrid(domain.getOrganization())) {
			result.addErrorMessage(getColumNo("organization") + ("所属网格必须为片组片格"));
		}
		// if (null != domain.getAddressType()
		// && propertyDictService.getPropertyDictById(
		// domain.getAddressType().getId()).getInternalId() !=
		// CurrentAddressType.OTHER) {
		// if (validateHelper.emptyString(domain.getCommunity())) {
		// result.addErrorMessage(getColumNo("community") + "房屋准确地址必须输入");
		// } else if (validateHelper.illegalStringLength(0, 50,
		// domain.getCommunity())) {
		// result.addErrorMessage(getColumNo("community")
		// + "房屋小区不能输入大于50字符");
		// }
		// if (!validateHelper.emptyString(domain.getBlock())) {
		// if (validateHelper.illegalStringLength(0, 8, domain.getBlock())) {
		// result.addErrorMessage(getColumNo("block") + "房屋幢不能输入大于8字符");
		// }
		// }
		// if (!validateHelper.emptyString(domain.getUnit())) {
		// if (validateHelper.illegalStringLength(0, 6, domain.getUnit())) {
		// result.addErrorMessage(getColumNo("unit") + "房屋单元不能输入大于6字符");
		// }
		// }
		// if (!validateHelper.emptyString(domain.getRoom())) {
		// if (validateHelper.illegalStringLength(0, 10, domain.getRoom())) {
		// result.addErrorMessage(getColumNo("room") + "房屋室不能输入大于10字符");
		// }
		// }
		// } else {
		// // fateson add start BUG单 #21269出租房中小区地址填写后导入时提示房屋准确地址必须输入
		// // 判断是否是导入的时候
		// // if (domain.getAddressType() == null) {
		// // result.addErrorMessage(getColumNo("addressType")
		// // + ("房屋地址类型必须输入"));
		// // }
		// if (ExcelImportHelper.isImport.get()) {
		// domain.setAddress(domain.getCommunity());
		// }
		// // end
		// if (validateHelper.emptyString(domain.getAddress())) {
		// result.addErrorMessage(getColumNo("community") + ("房屋准确地址必须输入"));
		// } else {
		// if (validateHelper.illegalStringLength(0, 50,
		// domain.getAddress())) {
		// result.addErrorMessage(getColumNo("community")
		// + ("房屋准确地址不能输入大于50字符"));
		// }
		// }
		// }
		if (!validateHelper.emptyString(domain.getBuildingName())) {
			if (validateHelper.illegalStringLength(2, 100,
					domain.getBuildingName())) {
				result.addErrorMessage(getColumNo("buildingName")
						+ ("建筑物名称不能输入大于100字符并且不能小于2个字符"));
			}
		}
		if (!validateHelper.emptyString(domain.getPropertyName())) {
			if (validateHelper.illegalStringLength(2, 100,
					domain.getPropertyName())) {
				result.addErrorMessage(getColumNo("propertyName")
						+ ("物业管理单位名称不能输入大于100字符并且不能小于2个字符"));
			}
		}
		if (!validateHelper.emptyString(domain.getHouseOwner())) {
			if (validateHelper.illegalStringLength(2, 30,
					domain.getHouseOwner())) {
				result.addErrorMessage(getColumNo("houseOwner")
						+ ("代表人/业主不能输入大于30字符并且不能小于2个字符").toString());
			}
			if (validateHelper.illegalExculdeParticalChar2(domain
					.getHouseOwner())) {
				result.addErrorMessage(getColumNo("houseOwner")
						+ "代表人/业主不能输入非法字符");
			}
		}
		if (!validateHelper.emptyString(domain.getHouseOwnerIdCardNo())) {
			if (validateHelper.illegalIdcard(domain.getHouseOwnerIdCardNo())) {
				result.addErrorMessage(getColumNo("houseOwnerIdCardNo")
						+ ("业主身份证号码输入不正确").toString());
			}
		}
		if (!validateHelper.emptyString(domain.getTelephone())) {
			if (validateHelper
					.illegalStringLength(0, 20, domain.getTelephone())) {
				result.addErrorMessage(getColumNo("telephone")
						+ ("业主联系电话不能输入大于20字符").toString());
			} else if (validateHelper.illegalTelephone(domain.getTelephone())) {
				result.addErrorMessage(getColumNo("telephone")
						+ ("业主联系电话只能输入数字和-").toString());
			}
		}
		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalMobilePhone(domain.getMobileNumber())) {
			result.addErrorMessage(getColumNo("mobileNumber")
					+ ("业主联系手机只能输入1开头的11位数字").toString());
		}
		if (!validateHelper.emptyString(domain.getHouseDoorModel())) {
			if (validateHelper.illegalStringLength(2, 30,
					domain.getHouseDoorModel())) {
				result.addErrorMessage(getColumNo("houseDoorModel")
						+ ("房屋户型不能输入大于30字符且不能小于2个字符").toString());
			}
		}
		// 对建成年份的验证
		if (!validateHelper.emptyString(domain.getBuiltYear())) {
			/**
			 * if (validateHelper.illegalStringLength(0, 4,
			 * domain.getBuiltYear())) {
			 */

			if (validateHelper.illegaYear(domain.getBuiltYear())) {
				result.addErrorMessage(getColumNo("builtYear")
						+ ("建成年份不能输入大于4字符且1900年之后").toString());
			}
		}

		if (null != domain.getHouseArea()) {
			if (validateHelper.illegalStringLength(0, 10,
					String.valueOf(domain.getHouseArea()))) {
				result.addErrorMessage(getColumNo("houseArea")
						+ ("本户建筑面积不能输入大于10字符").toString());
			}
		}

		// 房屋证号设置在最长100个字符
		if (!validateHelper.emptyString(domain.getHouseRightNumber())) {
			if (validateHelper.illegalStringLength(2, 30,
					domain.getHouseRightNumber())) {
				result.addErrorMessage(getColumNo("houseRightNumber")
						+ ("房屋权证号不能输入大于30符并且不能小于两个字符").toString());
			}
		}

		if (!validateHelper.emptyString(domain.getLandRightsNumbe())) {
			if (validateHelper.illegalStringLength(2, 30,
					String.valueOf(domain.getLandRightsNumbe()))) {
				result.addErrorMessage(getColumNo("landRightsNumbe")
						+ ("土地权证号不能输入大于30字符并且不能小于两个字符").toString());
			}
		}
		if (!validateHelper.emptyString(domain.getName())) {
			if (!validateHelper.isConSpeCharacters(domain.getName())) {
				result.addErrorMessage(getColumNo("name")
						+ ("产权人姓名不能包含特殊字符").toString());
			} else if (validateHelper.illegalStringLength(2, 20,
					domain.getName())) {
				result.addErrorMessage(getColumNo("name")
						+ ("产权人姓名只能输入2-20个字符").toString());
			} else if (validateHelper.illegalExculdeParticalChar2(domain
					.getName())) {
				result.addErrorMessage(getColumNo("name")
						+ ("产权人姓名只能输入数字,字母,中文字符").toString());
			}
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
			} else if (!validateHelper
					.emptyString(domain.getCertificateNumbe())
					&& validateHelper.illegalStringLength(2, 20,
							domain.getCertificateNumbe())) {
				result.addErrorMessage(getColumNo("certificateNumbe")
						+ ("产权人证件号码只能输入2-20个字符").toString());
			}
		}
		if (!validateHelper.emptyString(domain.getPropertyPersonTel())) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getPropertyPersonTel())) {
				result.addErrorMessage(getColumNo("propertyPersonTel")
						+ ("产权人联系电话不能输入大于20字符").toString());
			} else if (validateHelper.illegalTelephone(domain
					.getPropertyPersonTel())) {
				result.addErrorMessage(getColumNo("propertyPersonTel")
						+ ("产权人联系电话只能输入数字和-").toString());
			}
		}
		if (!validateHelper.emptyString(domain.getPropertyPersonMobile())
				&& validateHelper.illegalMobilePhone(domain
						.getPropertyPersonMobile())) {
			result.addErrorMessage(getColumNo("propertyPersonMobile")
					+ ("产权人联系手机只能输入1开头的11位数字").toString());
		}

		if (!validateHelper.emptyString(domain.getRemark())) {
			if (validateHelper.illegalStringLength(2, 300, domain.getRemark())) {
				result.addErrorMessage(getColumNo("remark")
						+ ("备注不能输入大于300字符且不能小于两个字符").toString());
			}
		}

		/** 房产权证发证时间验证 */

		if (null != domain.getHouseRightNumberDate()
				&& validateHelper.endDateIsSameorBigForStartDate(
						domain.getHouseRightNumberDate(),
						CalendarUtil.getTomorrow())) {
			result.addErrorMessage(getColumNo("houseRightNumberDate")
					+ ("房产权证发证时间不能大于当前时间").toString());
		}

		/** 土地权证发证时间验证 */
		if (null != domain.getLandRightsNumbeDate()
				&& validateHelper.endDateIsSameorBigForStartDate(
						domain.getLandRightsNumbeDate(),
						CalendarUtil.getTomorrow())) {
			result.addErrorMessage(getColumNo("landRightsNumbeDate")
					+ ("土地权证发证时间不能大于当前时间").toString());
		}

		return result;
	}

	public ValidateResult validate(T domain) {
		ValidateResult validateResult = new ValidateResult();
		validateResult
				.addAllErrorMessage(validateHouseBaseInfo((RentalHouse) domain));
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

	public boolean validateOrgIsGrid(Organization org) {
		return organizationDubboService.isGridOrganization(org.getId());
	}
}
