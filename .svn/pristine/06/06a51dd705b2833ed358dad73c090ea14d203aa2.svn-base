package com.tianque.plugin.dataManage.location.rentalHouseTemp.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.rentalHouse.dao.RentalHouseDao;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.location.rentalHouseTemp.domain.RentalHouseTemp;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("rentalHouseTempValidator")
public class RentalHouseTempValidatorImpl implements
		DomainValidatorTemp<RentalHouseTemp> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private RentalHouseDao houseInfoDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	protected PropertyDictService propertyDictService;

	public void setValidateHelper(ValidateHelper validateHelper) {
		this.validateHelper = validateHelper;
	}

	@Override
	public ValidateResult validate(RentalHouseTemp domain) {
		ValidateResult result = new ValidateResult();
		if (ExcelImportHelper.isImport.get()) {
			domain.setAddress(domain.getAddress());
		}
		if (domain.getHouseCode() != null) {
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

		/** 验证房产证地址 */
		if (!validateHelper.emptyString(domain.getHouseAddress())
				&& validateHelper.illegalStringLength(0, 60,
						domain.getHouseAddress())) {
			result.addErrorMessage(getColumNo("houseAddress")
					+ "房产证地址不能输入大于60个字符");
		}

		/** 租赁证备案号验证 */
		if (!validateHelper.emptyString(domain.getHouseFileNum())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getHouseFileNum())) {
			result.addErrorMessage(getColumNo("houseFileNum")
					+ "租赁证备案号不能输入大于50个字符");
		}

		/** 出租人联系地址验证 */
		if (!validateHelper.emptyString(domain.getLessorAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getLessorAddress())) {
			result.addErrorMessage(getColumNo("lessorAddress")
					+ "出租人联系地址不能输入大于50个字符");
		}
		if (validateHelper.emptyString(domain.getRentalPerson())) {
			result.addErrorMessage(getColumNo("rentalPerson") + "出租人不能为空");
		}
		if (!validateHelper.emptyString(domain.getRentalPerson())) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getRentalPerson())) {
				result.addErrorMessage(getColumNo("rentalPerson")
						+ "出租人不能输入大于20字符");
			}
			if (validateHelper.nullObj(domain.getHiddenDangerLevel())) {
				result.addErrorMessage(getColumNo("hiddenDangerLevel")
						+ "隐患程度不能为空");
			}
			if (validateHelper.nullObj(domain.getRentalType())) {
				result.addErrorMessage(getColumNo("rentalType") + "出租类型不能为空");
			}
			if (null == domain.getAddressType()) {
				result.addErrorMessage(getColumNo("addressType") + "房屋地址类型必须输入");
			}
			// if (null != domain.getAddressType()
			// && propertyDictService.getPropertyDictById(
			// domain.getAddressType().getId()).getInternalId() !=
			// CurrentAddressType.OTHER) {
			// if (validateHelper.emptyString(domain.getCommunity())) {
			// result.addErrorMessage(getColumNo("community")
			// + "房屋准确地址必须输入");
			// } else if (validateHelper.illegalStringLength(0, 20,
			// domain.getCommunity())) {
			// result.addErrorMessage(getColumNo("community")
			// + "房屋小区不能输入大于20字符");
			// }
			// if (!validateHelper.emptyString(domain.getBlock())) {
			// if (validateHelper.illegalStringLength(0, 8,
			// domain.getBlock())) {
			// result.addErrorMessage(getColumNo("block")
			// + "房屋幢不能输入大于8字符");
			// }
			// }
			// if (!validateHelper.emptyString(domain.getUnit())) {
			// if (validateHelper.illegalStringLength(0, 6,
			// domain.getUnit())) {
			// result.addErrorMessage(getColumNo("unit")
			// + "房屋单元不能输入大于6字符");
			// }
			// }
			// if (!validateHelper.emptyString(domain.getRoom())) {
			// if (validateHelper.illegalStringLength(0, 10,
			// domain.getRoom())) {
			// result.addErrorMessage(getColumNo("room")
			// + "房屋室不能输入大于10字符");
			// }
			// }
			// } else {
			// if (validateHelper.emptyString(domain.getCommunity())) {
			// result.addErrorMessage(getColumNo("community")
			// + ("房屋准确地址必须输入"));
			// } else {
			// if (validateHelper.illegalStringLength(0, 50,
			// domain.getCommunity())) {
			// result.addErrorMessage(getColumNo("community")
			// + ("房屋地址不能输入大于50字符"));
			// }
			// }
			// }
			if (!validateHelper.emptyString(domain.getBuildingName())) {
				if (validateHelper.illegalStringLength(2, 100,
						domain.getBuildingName())) {
					result.addErrorMessage(getColumNo("buildingName")
							+ ("建筑物名称不能输入大于100字符并且不能小于两个字符"));
				}
			}
			if (!validateHelper.emptyString(domain.getPropertyName())) {
				if (validateHelper.illegalStringLength(2, 100,
						domain.getPropertyName())) {
					result.addErrorMessage(getColumNo("propertyName")
							+ ("物业管理单位名称不能输入大于100字符并且不能小于两个字符"));
				}
			}
			if (!validateHelper.emptyString(domain.getManager())) {
				if (validateHelper.illegalStringLength(2, 30,
						domain.getManager())) {
					result.addErrorMessage(getColumNo("manager")
							+ ("代表人/业主不能输入大于30字符并且不能小于两个字符").toString());
				}
			}
			if (!validateHelper.emptyString(domain.getHouseOwnerIdCardNo())) {
				if (validateHelper
						.illegalIdcard(domain.getHouseOwnerIdCardNo())) {
					result.addErrorMessage(getColumNo("houseOwnerIdCardNo")
							+ ("业主身份证号码输入不正确").toString());
				}
			}
			if (!validateHelper.emptyString(domain.getTelephone())) {
				if (validateHelper.illegalStringLength(0, 20,
						domain.getTelephone())) {
					result.addErrorMessage(getColumNo("telephone")
							+ ("业主联系电话不能输入大于20字符").toString());
				} else if (validateHelper.illegalTelephone(domain
						.getTelephone())) {
					result.addErrorMessage(getColumNo("telephone")
							+ ("业主联系电话只能输入数字和-").toString());
				}
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
				} else if (validateHelper.illegalExculdeParticalChar(domain
						.getName())) {
					result.addErrorMessage(getColumNo("name")
							+ ("产权人姓名只能输入数字,字母,中文字符").toString());
				}
			}
			if (null != domain.getCertificateNumbe()
					&& null == domain.getCertificateType()) {
				result.addErrorMessage(getColumNo("certificateType")
						+ ("请选择证件类型").toString());
			}
			if (!validateHelper.emptyString(domain.getCertificateNumbe())) {
				if (null != domain.getCertificateType()) {
					if ("身份证".equals(domain.getCertificateType()
							.getDisplayName())
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
			if (null != domain.getRentalCertificateNumbe()
					&& null == domain.getRentalCertificateType()) {
				result.addErrorMessage(getColumNo("rentalCertificateType")
						+ ("请选择证件类型").toString());
			}
			if (!validateHelper.emptyString(domain.getRentalCertificateNumbe())) {
				if (null != domain.getRentalCertificateType()) {
					if ("身份证".equals(domain.getRentalCertificateType()
							.getDisplayName())
							&& validateHelper.illegalIdcard(domain
									.getRentalCertificateNumbe())) {
						result.addErrorMessage(getColumNo("rentalCertificateNumbe")
								+ ("出租人证件号码输入不正确").toString());
					} else {
						if (validateHelper.illegalStringLength(2, 20,
								domain.getRentalCertificateNumbe())) {
							result.addErrorMessage(getColumNo("rentalCertificateNumbe")
									+ ("出租人证件号码只能输入2-20个字符").toString());
						}
					}
				} else {
					if (validateHelper.illegalStringLength(2, 20,
							domain.getRentalCertificateNumbe())) {
						result.addErrorMessage(getColumNo("rentalCertificateNumbe")
								+ ("出租人证件号码只能输入2-20个字符").toString());
					}
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
			if (!validateHelper.emptyString(domain.getPropertyPersonMobile())
					&& validateHelper.illegalMobilePhone(domain
							.getPropertyPersonMobile())) {
				result.addErrorMessage(getColumNo("propertyPersonMobile")
						+ ("产权人联系手机只能输入1开头的11位数字").toString());
			}
			if (!validateHelper.emptyString(domain.getRentalMobileNumber())
					&& validateHelper.illegalMobilePhone(domain
							.getRentalMobileNumber())) {
				result.addErrorMessage(getColumNo("rentalMobileNumber")
						+ ("产权人联系手机只能输入1开头的11位数字").toString());
			}
			if (!validateHelper.emptyString(domain.getRemark())) {
				if (validateHelper.illegalStringLength(2, 300,
						domain.getRemark())) {
					result.addErrorMessage(getColumNo("remark")
							+ ("备注不能输入大于300字符且不能小于两个字符").toString());
				}
			}
		}
		// 对建成年份的验证
		if (!validateHelper.emptyString(domain.getBuiltYear())) {
			if (validateHelper.illegaYear(domain.getBuiltYear())) {
				result.addErrorMessage(getColumNo("builtYear")
						+ ("建成年份不能输入大于4字符且1900年之后").toString());
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			if (Integer.parseInt(domain.getBuiltYear()) > Integer
					.parseInt(formatter.format(new Date()))) {
				result.addErrorMessage(getColumNo("builtYear") + "建成年份不能大于当前年份");
			}
		}
		// 房屋权证发证时间验证
		if (domain.getHouseRightNumberDate() != null) {
			if (domain.getHouseRightNumberDate().after(new Date())) {
				result.addErrorMessage(getColumNo("houseRightNumberDate")
						+ "房屋权证发证时间不能大于当前时间");
			}
		}
		// 土地权证发证时间验证
		if (domain.getLandRightsNumbeDate() != null) {
			if (domain.getLandRightsNumbeDate().after(new Date())) {
				result.addErrorMessage(getColumNo("landRightsNumbeDate")
						+ "土地权证发证时间不能大于当前时间");
			}
		}
		// 登记日期验证
		if (domain.getRegistDate() != null) {
			if (domain.getRegistDate().after(new Date())) {
				result.addErrorMessage(getColumNo("registDate")
						+ "登记日期不能大于当前时间");
			}
		}
		/** 出租间数验证 */
		if (null != domain.getRoomNumber()) {
			if (validateHelper
					.illegalInteger(domain.getRoomNumber().toString())) {
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
			if (domain.getRentMonth() > 99999.9 || domain.getRentMonth() < 0) {
				result.addErrorMessage(getColumNo("rentMonth")
						+ ("月租金应在0-99999.9之间").toString());
			}
		}

		/** 隐患情况验证 */
		if (!validateHelper.emptyString(domain.getHiddenRectification())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getHiddenRectification())) {
			result.addErrorMessage(getColumNo("hiddenRectification")
					+ ("隐患情况不能输入大于50个字符").toString());
		}
		// 出租时间验证
		if (domain.getLessorDate() != null) {
			if (domain.getLessorDate().after(new Date())) {
				result.addErrorMessage(getColumNo("lessorDate")
						+ "出租时间不能大于当前时间");
			}
		}
		if (domain.getRegistDate() != null && domain.getLessorDate() != null) {
			if (domain.getRegistDate().after(domain.getLessorDate())) {
				result.addErrorMessage(getColumNo("lessorDate")
						+ "出租时间不能小于登记时间");
			}
		}

		return result;
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
}
