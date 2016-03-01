package com.tianque.validate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.service.PermanentAddressService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.dataManage.util.Constants;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.sysadmin.service.OrganizationDubboService;

public abstract class AbstractCountrymenValidator<T> implements
		DomainValidator<T> {
	@Autowired
	protected ValidateHelper validateHelper;
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	protected PermanentAddressService permanentAddressService;

	public abstract ValidateResult validateSpecializedInfo(T domain);

	public ValidateResult validateBaseInfo(Countrymen domain) {
		ValidateResult result = new ValidateResult();
		// 姓名验证
		validatorName(domain.getName(), result, null);
		// 身份证验证
		validatorIdCardNo(domain.getIdCardNo(), result, null);
		// 性别验证
		validatorifGender(domain.getIdCardNo(), domain.getGender(), result,
				null);
		// 省市县验证
		validatorProvinceAndCityAndDistrict(domain.getProvince(),
				domain.getCity(), domain.getDistrict(), result, null);

		boolean orgIsNotNull = validateOrgIsNotNull(domain.getOrganization());
		if (!orgIsNotNull) {
			result.addErrorMessage(getColumNo("organization")
					+ "所属网格为空或网格名称填写错误！");
		}

		if (orgIsNotNull && !(domain instanceof PartyMemberInfo)
				&& !validateOrgIsGrid(domain.getOrganization())) {
			result.addErrorMessage(getColumNo("organization") + "所属网格必须为片组片格");
		}

		if (!validateHelper.emptyString(domain.getUsedName())) {
			if (validateHelper.illegalStringLength(2, 20, domain.getUsedName())) {
				result.addErrorMessage(getColumNo("usedName")
						+ "曾用名/别名只能输入2-20个字符");
			}
			if (!validateHelper.isConSpeCharacters(domain.getUsedName())) {
				result.addErrorMessage(getColumNo("usedName")
						+ "曾用名/别名不能含有特殊字符");
			}
		}

		// 联系人电话验证
		validatorifMobileNumber(domain.getMobileNumber(), result, null);
		// 联系人固定电话验证
		validatorifTelephone(domain.getTelephone(), result, null);
		// 工作单位验证
		validatorWorkUnit(domain.getWorkUnit(), result, null);

		if (domain.getStature() != null) {
			if (validateHelper.illegalInteger(String.valueOf(domain
					.getStature()))) {
				result.addErrorMessage(getColumNo("stature")
						+ "身高只能输入不大于300的正整数");
			} else if (Double.valueOf(domain.getStature()).longValue() > 300) {
				result.addErrorMessage(getColumNo("stature")
						+ "身高只能输入不大于300的正整数");
			}
		}

		/** 户籍地派出所字段长度验证 cc */
		if (!validateHelper.emptyString(domain.getNativePoliceStation())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getNativePoliceStation())) {
				result.addErrorMessage(getColumNo("nativePoliceStation")
						+ "户籍地派出所最多输入50个字符");
			}
		}

		/** 出生日期验证 */
		if (!validateHelper.nullObj(domain.getBirthday())) {
			if (!validateHelper.endDateIsSameorBigForStartDate(
					CalendarUtil.getTomorrow(), domain.getBirthday())) {
				result.addErrorMessage(getColumNo("birthday") + "出生日期不能大于当前日期");
			}
		}

		// fateson add 对导入业务人员实口类型添加验证
		if (ExcelImportHelper.isImport.get()
				&& !StringUtils.isEmpty(getColumNo("actualPopulationType"))) {
			if (domain instanceof AttentionPopulation) {
				validateActualPopulationType(domain.getActualPopulationType(),
						result, null);
			}
		}

		// 如果excel中的 实口类型为 流动人口的话 需要额外
		if (!"convert".equals(domain.getAttentionPopulationType())) {
			// 职业验证
			validatorCareer(domain.getCareer(), result, null);

			// 婚姻状况验证
			validatorifMaritalState(domain.getMaritalState(), result, null);
			// 民族验证
			validatorNation(domain.getNation(), result, null);

			if (null != domain.getFaith()
					&& null != domain.getFaith().getDisplayName()) {
				if (validateHelper
						.illegalPropertyDictDisplayName(PropertyTypes.FAITH,
								domain.getFaith().getDisplayName()))
					result.addErrorMessage(getColumNo("faith") + "宗教信仰输入不正确");
			}
			// 文化程度验证
			validatorSchooling(domain.getSchooling(), result, null);

			if (null != domain.getBloodType()
					&& null != domain.getBloodType().getDisplayName()) {
				if (validateHelper.illegalPropertyDictDisplayName(
						PropertyTypes.BLOOD_TYPE, domain.getBloodType()
								.getDisplayName()))
					result.addErrorMessage(getColumNo("bloodType") + "血型输入不正确");
			}

			// 政治面貌 验证
			validatorPoliticalBackground(domain.getPoliticalBackground(),
					result, null);
		}
		// 户籍地详址验证
		validatorNativePlaceAddress(domain.getNativePlaceAddress(), result,
				null);

		// 临时居所验证
		validateOtherAddress(domain.getOtherAddress(), result, null);

		if (!validateHelper.emptyString(domain.getEmail())) {
			if (validateHelper.illegalStringLength(0, 50, domain.getEmail())) {
				result.addErrorMessage(getColumNo("email") + "电子邮箱不能输入大于50字符");
			} else if (validateHelper.illegalEmail(domain.getEmail())) {
				result.addErrorMessage(getColumNo("email") + "电子邮箱格式输入不正确");
			}
		}

		if (!validateHelper.emptyString(domain.getOtherAddress())) {
			if (validateHelper.illegalStringLength(0, 50,
					domain.getOtherAddress())) {
				result.addErrorMessage(getColumNo("otherAddress")
						+ "临时居所不能输入大于50字符");
			}
		}
		// 备注验证
		validatorRemark(domain.getRemark(), result, null);
		return result;
	}

	private void validateActualPopulationType(String actualPopulationType,
			ValidateResult result, Object object) {
		if (actualPopulationType == null) {
			result.addErrorMessage(getColumNo("actualPopulationType")
					+ "实口类型必须填写");
		} else if (!actualPopulationType
				.equals(BaseInfoTables.HOUSEHOLDSTAFF_KEY)
				&& !actualPopulationType
						.equals(BaseInfoTables.FLOATINGPOPULATION_KEY)) {
			// 如果业务人员中 actualPopulationType 为空 那么 一定是excel中的 实口类型填写错误
			result.addErrorMessage(getColumNo("actualPopulationType")
					+ "实口类型填写错误");
		}

	}

	public void validatorRemark(String remark, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(remark)) {
			if (validateHelper.illegalStringLength(0, 300, remark)) {
				result.addErrorMessage(getColumNo("remark") + apstr
						+ "备注不能输入大于300字符");
			}
		}
	}

	public void validateCurrentAddress(Countrymen domain,
			ValidateResult result, DataConvertionHelper convertionHelper) {
		if (result.getMessages().size() > 0)
			return;
		if (validateHelper.emptyString(domain.getCurrentAddress())) {
			result.addErrorMessage(getColumNo("currentAddress") + "常住地址不能为空");
		}

	}

	public void validateIsHaveHouse(Countrymen domain, ValidateResult result,
			DataConvertionHelper convertionHelper,
			ActualHouseService actualHouseService) {
		// if (result.getMessages().size() > 0)
		// return;
		// fateson add BUG单 #21877 未落户人口中导入一千条数据后提示是否有固定地址不能为空
		if (!validateHelper.nullBoolean(domain.getIsHaveHouse())) {
			if (domain.getIsHaveHouse()) {
				if (validateHelper.emptyString(domain.getHouseCode())) {
					result.addErrorMessage(getColumNo("houseCode") + "房屋编号不能为空");
				} else {
					HouseInfo houseInfo = actualHouseService
							.getHouseInfoByHouseCodeAndOrgId(domain
									.getHouseCode(), domain.getOrganization()
									.getId());
					if (null == houseInfo) {
						result.addErrorMessage(getColumNo("houseCode")
								+ "房屋编号在该网格下不存在");
					} else {
						domain.setCurrentAddressType(convertionHelper
								.convertToPropertyDict(
										PropertyTypes.CURRENT_ADDRESS_TYPE,
										"商品房"));
						domain.setCommunity(houseInfo.getCommunity());
						domain.setBlock(houseInfo.getBlock());
						domain.setUnit(houseInfo.getUnit());
						domain.setRoom(houseInfo.getRoom());
						StringBuffer sb = new StringBuffer();
						if (!validateHelper.emptyString(domain.getCommunity())) {
							sb.append(domain.getCommunity()).append("小区");
						}
						if (!validateHelper.emptyString(domain.getBlock())) {
							sb.append(domain.getBlock()).append("幢");
						}
						if (!validateHelper.emptyString(domain.getUnit())) {
							sb.append(domain.getUnit()).append("单元");
						}
						if (!validateHelper.emptyString(domain.getRoom())) {
							sb.append(domain.getRoom()).append("室");
						}
						domain.setCurrentAddress(sb.toString());
					}
				}
			} else {
				if (validateHelper.emptyString(domain.getNoHouseReason())) {
					result.addErrorMessage(getColumNo("noHouseReason")
							+ "无房原因不能为空");
				}
			}
		}

	}

	public void validatorProvinceAndCityAndDistrict(String province,
			String city, String district, ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(province)) {
			result.addErrorMessage(getColumNo("province") + apstr + "户籍地省必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, 20, province)) {
				result.addErrorMessage(getColumNo("province") + apstr
						+ "户籍地省不能输入大于20字符");
			}
		}
		if (validateHelper.emptyString(city)) {
			result.addErrorMessage(getColumNo("city") + apstr + "户籍地市必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, 20, city)) {
				result.addErrorMessage(getColumNo("city") + apstr
						+ "户籍地市不能输入大于20字符");
			}
		}

		if (validateHelper.emptyString(district)) {
			result.addErrorMessage(getColumNo("district") + apstr + "户籍地县必须输入");
		} else {
			if (validateHelper.illegalStringLength(0, 20, district)) {
				result.addErrorMessage(getColumNo("district") + apstr
						+ "户籍地县不能输入大于20字符");
			}
		}
		/** 验证省是否输入正确 */
		List<PermanentAddress> provinceTempList = permanentAddressService
				.getPermanentAddressByAddressNameAndAddressLevel(
						province,
						Constants.permanentAddressLevel.PERMANENTADRESSLEVEL_PROVINCE);
		if (provinceTempList == null || provinceTempList.size() == 0) {
			result.addErrorMessage(getColumNo("province") + apstr + "请选择正确的省级");
		}
		/** 验证市是否输入正确 */
		List<PermanentAddress> cityTempList = permanentAddressService
				.getPermanentAddressByAddressNameAndAddressLevel(
						city,
						Constants.permanentAddressLevel.PERMANENTADRESSLEVEL_CITY);
		if (cityTempList == null || cityTempList.size() == 0) {
			result.addErrorMessage(getColumNo("city") + apstr + "请选择正确的市级");
		}
		/** 验证县是否输入正确 */
		List<PermanentAddress> districtTempList = permanentAddressService
				.getPermanentAddressByAddressNameAndAddressLevel(
						district,
						Constants.permanentAddressLevel.PERMANENTADRESSLEVEL_DISTRICT);
		if (districtTempList == null || districtTempList.size() == 0) {
			result.addErrorMessage(getColumNo("district") + apstr + "请选择正确的县级");
		}

		// 验证市是不是属于省
		List<String> cityList = permanentAddressService
				.getPermanentAddressByParentNameAndAddressLevel(
						province,
						Constants.permanentAddressLevel.PERMANENTADRESSLEVEL_PROVINCE);
		if (cityList != null && cityList.size() != 0 && city != null) {
			if (!cityList.contains(city)) {
				result.addErrorMessage(getColumNo("city") + apstr
						+ "请选择对应省下的市级");
			}
		}
		// 验证县是不是属于市
		List<String> districtList = permanentAddressService
				.getPermanentAddressByParentNameAndAddressNameAndAddressLevel(
						city,
						Constants.permanentAddressLevel.PERMANENTADRESSLEVEL_CITY,
						province);
		if (districtList != null && districtList.size() != 0
				&& district != null) {
			if (!districtList.contains(district)) {
				result.addErrorMessage(getColumNo("district") + apstr
						+ "请选择对应市下的县级");
			}
		}

	}

	public void validatorSchooling(PropertyDict schooling,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != schooling
				&& !validateHelper.emptyString(schooling.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.SCHOOLING, schooling.getDisplayName()))
				result.addErrorMessage(getColumNo("schooling") + apstr
						+ "文化程度输入不正确");
		} else if (null == schooling
				|| validateHelper.emptyString(schooling.getId() == null ? ""
						: schooling.getId() + "")) {
			result.addErrorMessage(getColumNo("schooling") + apstr + "文化程度必须选择");
		}
	}

	public void validatorCareer(PropertyDict career, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != career
				&& !validateHelper.emptyString(career.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.CAREER, career.getDisplayName()))
				result.addErrorMessage(getColumNo("career") + apstr + "职业输入不正确");
		}
	}

	public void validatorifMobileNumber(String mobileNumber,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(mobileNumber)
				&& validateHelper.illegalMobilePhone(mobileNumber)) {
			result.addErrorMessage(getColumNo("mobileNumber") + apstr
					+ "联系手机只能输入1开头的11位数字");
		}
	}

	public void validatorifTelephone(String telephone, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(telephone)) {
			if (validateHelper.illegalStringLength(2, 20, telephone)) {
				result.addErrorMessage(getColumNo("telephone") + apstr
						+ "固定电话不能输入大于20字符并且不能小于2个字符");
			} else if (validateHelper.illegalTelephone(telephone)) {
				result.addErrorMessage(getColumNo("telephone") + apstr
						+ "固定电话只能输入数字和-");
			}
		}
	}

	public void validatorWorkUnit(String workUnit, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(workUnit)
				&& validateHelper.illegalStringLength(0, 50, workUnit)) {
			result.addErrorMessage(getColumNo("workUnit") + apstr
					+ "工作单位或就读学校不能输入大于50字符");
		}
		if (!validateHelper.emptyString(workUnit)
				&& !validateHelper.isConSpeCharacters(workUnit)) {
			result.addErrorMessage(getColumNo("workUnit") + apstr
					+ "工作单位或就读学校不能输入特殊字符");
		}
	}

	public void validatorPoliticalBackground(PropertyDict politicalBackground,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != politicalBackground
				&& !validateHelper.emptyString(politicalBackground
						.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.POLITICAL_BACKGROUND,
					politicalBackground.getDisplayName()))
				result.addErrorMessage(getColumNo("politicalBackground")
						+ apstr + "政治面貌输入不正确");
		} else if (null == politicalBackground
				|| validateHelper
						.emptyString(politicalBackground.getId() == null ? ""
								: politicalBackground.getId() + "")) {
			result.addErrorMessage(getColumNo("politicalBackground") + apstr
					+ "政治面貌必须选择");
		}
	}

	public void validatorNativePlaceAddress(String nativePlaceAddress,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(nativePlaceAddress)) {
			if (validateHelper.illegalStringLength(0, 50, nativePlaceAddress)) {
				result.addErrorMessage(getColumNo("nativePlaceAddress") + apstr
						+ "户籍地详址不能输入大于50字符");
			}
			// else if (!validateHelper.isConSpeCharacters(nativePlaceAddress))
			// {
			// result.addErrorMessage(getColumNo("nativePlaceAddress") + apstr
			// + "户籍地详址不能包含特殊字符");
			// }
		}
	}

	public void validateOtherAddress(String otherAddress,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!validateHelper.emptyString(otherAddress)) {
			if (validateHelper.illegalStringLength(0, 50, otherAddress)) {
				result.addErrorMessage(getColumNo("otherAddress") + apstr
						+ "其他地详址不能输入大于50字符");
			}
			// else if (!validateHelper.isConSpeCharacters(otherAddress)) {
			// result.addErrorMessage(getColumNo("otherAddress") + apstr
			// + "其他地详址不能包含特殊字符");
			// }
		}

	}

	public void validatorifMaritalState(PropertyDict maritalState,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != maritalState
				&& !validateHelper.emptyString(maritalState.getDisplayName())) {
			if (validateHelper
					.illegalPropertyDictDisplayName(
							PropertyTypes.MARITAL_STATUS,
							maritalState.getDisplayName()))
				result.addErrorMessage(getColumNo("maritalState") + apstr
						+ "婚姻状况输入不正确");
		} else if (null == maritalState
				|| validateHelper.emptyString(maritalState.getId() == null ? ""
						: maritalState.getId() + "")) {
			result.addErrorMessage(getColumNo("maritalState") + apstr
					+ "婚姻状况必须选择");
		}
	}

	public void validatorNation(PropertyDict nation, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (null != nation
				&& !validateHelper.emptyString(nation.getDisplayName())) {
			if (validateHelper.illegalPropertyDictDisplayName(
					PropertyTypes.NATION, nation.getDisplayName()))
				result.addErrorMessage(getColumNo("nation") + apstr + "民族输入不正确");
		} else if (null == nation
				|| validateHelper.emptyString(nation.getId() == null ? ""
						: nation.getId() + "")) {
			result.addErrorMessage(getColumNo("nation") + apstr + "民族必须选择");
		}
	}

	public void validatorifGender(String idCardNo, PropertyDict gender,
			ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (!typeIsNotNull(gender)) {
			result.addErrorMessage(getColumNo("gender") + apstr + "性别必须输入");
		}
		/*
		 * if (validateHelper.illegalGenderByIdcard(idCardNo, gender)) {
		 * result.addErrorMessage(getColumNo("gender") + apstr + "性别与身份证不符"); }
		 */
	}

	public void validatorIdCardNo(String idCardNo, ValidateResult result,
			String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(idCardNo)) {
			result.addErrorMessage(getColumNo("idCardNo") + apstr + "身份证号码必须输入");
		}
		if (!validateHelper.emptyString(idCardNo)
				&& validateHelper.illegalExculdeParticalChar(idCardNo)) {
			result.addErrorMessage(getColumNo("idCardNo") + apstr
					+ "身份证号码不能输入非法字符");
		}
		if (!validateHelper.emptyString(idCardNo)
				&& validateHelper.illegalIdcard(idCardNo)) {
			result.addErrorMessage(getColumNo("idCardNo") + apstr
					+ "身份证号码输入不正确");
		}
	}

	/**
	 * 验证人员的身份证号是否已存在网格内
	 * 
	 * @param orgId
	 *            网格id
	 * @param idCardNo
	 *            身份证号
	 * @param populationType
	 *            实口类型
	 * @param populationId
	 *            实口基础信息id
	 * @param result
	 */
	public ValidateResult validatorIdCardNoExistedMessage(Long orgId,
			String idCardNo, String populationType, Long populationId,
			ValidateResult result) {
		String exsistedIdCardMessage = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNoExistedMessage(orgId,
						idCardNo, populationType, null);
		if (exsistedIdCardMessage != null && !"".equals(exsistedIdCardMessage)) {
			result.addErrorMessage(getColumNo("idCardNo")
					+ exsistedIdCardMessage);
		}
		return result;
	}

	public void validatorName(String name, ValidateResult result, String apstr) {
		apstr = apstr == null ? "" : apstr;
		if (validateHelper.emptyString(name)) {
			result.addErrorMessage(getColumNo("name") + apstr + "姓名必须输入");
		} else if (!validateHelper.isConSpeCharacters(name)) {
			result.addErrorMessage(getColumNo("name") + apstr + "姓名不能包含特殊字符");
		} else if (validateHelper.illegalExculdeParticalChar(name)) {
			result.addErrorMessage(getColumNo("name") + apstr
					+ "姓名只能输入数字,字母,中文字符");
		} else if (validateHelper.illegalStringLength(2, 20, name)) {
			result.addErrorMessage(getColumNo("name") + apstr + "姓名只能输入2-20个字符");
		}
	}

	public ValidateResult validate(T domain) {
		ValidateResult validateResult = new ValidateResult();
		validateResult
				.addAllErrorMessage(validateBaseInfo((Countrymen) domain));
		if (!"convert".equals(((Countrymen) domain)
				.getAttentionPopulationType())) {
			validateResult.addAllErrorMessage(validateSpecializedInfo(domain));
		}
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
