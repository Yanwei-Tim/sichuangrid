package com.tianque.plugin.dataManage.population.householdStaffTemp.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.dataManage.population.householdStaffTemp.domain.HouseholdStaffTemp;
import com.tianque.plugin.dataManage.validate.AbstactDataManageValidator;
import com.tianque.service.CensusRegisterFamilyService;

@Component("householdStaffTempValidator")
public class HouseholdStaffTempValidatorImpl extends
		AbstactDataManageValidator<HouseholdStaffTemp> {

	@Autowired
	private CensusRegisterFamilyService censusRegisterFamilyService;

	public static ThreadLocal<List<String>> realRow = new ThreadLocal<List<String>>();

	/**
	 * 判断导入的数据的户口号是否在数据库中存在户主.YES,不能添加，给出提示
	 * 
	 * @param domain
	 * @return ValidateResult
	 */
	public ValidateResult validateSQLAccountNumber(HouseholdStaff domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.containValue(domain.getRelationShipWithHead()
				.getDisplayName(), new String[] { "户主", "小集体户主" })) {
			// true:这个户口号存在
			if (censusRegisterFamilyService
					.getCensusRegisterFamilyByOrgIdAndAccountNumber(domain
							.getAccountNumber(), domain.getOrganization()
							.getId()) != null) {
				// true:这个户口号已存在户主
				if (censusRegisterFamilyService
						.getCensusRegisterFamilyByOrgIdAndAccountNumber(
								domain.getAccountNumber(),
								domain.getOrganization().getId()).getIdCardNo() != null) {
					result.addErrorMessage(getColumNo("accountNumber"), "户口号：["
							+ domain.getAccountNumber() + "]在系统中已存在户主，不能添加!");
				}
			}
		}
		return result;
	}

	/**
	 * 判断导入数据的户口号是否在Excel中再次设为户主.YES,不能添加，给出提示
	 * 
	 * @param domain
	 * @return ValidateResult
	 */
	public ValidateResult validateExcelAccountNumber(HouseholdStaff domain) {
		ValidateResult result = new ValidateResult();
		if (validateHelper.containValue(domain.getRelationShipWithHead()
				.getDisplayName(), new String[] { "户主", "小集体户主" })) {
			if (realRow.get() == null) {
				// 第一次与户主关系为户主的户口号
				List<String> list = new ArrayList<String>();
				list.add(domain.getAccountNumber());
				realRow.set(list);
			} else {
				if (realRow.get().contains(domain.getAccountNumber())) {
					result.addErrorMessage(getColumNo("accountNumber"), "户口号：["
							+ domain.getAccountNumber() + "]在Excel中已经选为户主，不能添加");
				} else {
					realRow.get().add(domain.getAccountNumber());
				}
			}
		}
		return result;
	}

	@Override
	public ValidateResult validateSpecializedInfo(HouseholdStaffTemp domain) {
		ValidateResult result = new ValidateResult();
		// FIXME 完成不能重复选择户主的验证
		if (validateHelper.emptyString(domain.getAccountNumber())) {
			result.addErrorMessage(getColumNo("accountNumber") + "户口号必须输入");
		} else if (validateHelper.illegalStringLength(1, 50,
				domain.getAccountNumber())) {
			result.addErrorMessage(getColumNo("accountNumber")
					+ "户口号只能输入1-50个字符");
		}
		if (null == domain.getRelationShipWithHead()) {
			result.addErrorMessage(getColumNo("relationShipWithHead")
					+ "与户主关系必须选择");
		}

		/** 验证与户主关系文本 */
		if (domain.getRelationShipWithHeadText() != null
				&& !validateHelper.emptyString(domain
						.getRelationShipWithHeadText())) {
			if (validateHelper.illegalStringLength(0, 20,
					domain.getRelationShipWithHeadText())) {
				result.addErrorMessage(getColumNo("relationShipWithHeadText")
						+ "与户主关系文本输入不能大于20个字符");
			}
		}

		if (null != domain.getOutGone() && true == domain.getOutGone()) {
			if (validateHelper.nullObj(domain.getOutReasons())) {
				result.addErrorMessage(getColumNo("outReasons") + "外出原因必须选择");
			}

			if (validateHelper.nullObj(domain.getReasonsDate())) {
				result.addErrorMessage(getColumNo("reasonsDate")
						+ "外出时间必须输入  或 外出时间输入不正确，正确格式例如：'1930-01-01'.");
			} else if (validateHelper.endDateIsSameorBigForStartDate(
					domain.getReasonsDate(), CalendarUtil.getTomorrow())) {
				result.addErrorMessage(getColumNo("reasonsDate")
						+ "外出时间不能大于当前日期");
			}

			if (!validateHelper.emptyString(domain.getOutProvince())
					&& validateHelper.illegalStringLength(0, 50,
							domain.getOutProvince())) {
				result.addErrorMessage(getColumNo("outProvince")
						+ "外出去向省不能输入大于50字符");
			}

			if (!validateHelper.emptyString(domain.getOutCity())
					&& validateHelper.illegalStringLength(0, 50,
							domain.getOutCity())) {
				result.addErrorMessage(getColumNo("outCity")
						+ "外出去向市不能输入大于50字符");
			}

			if (!validateHelper.emptyString(domain.getOutDistrict())
					&& validateHelper.illegalStringLength(0, 50,
							domain.getOutDistrict())) {
				result.addErrorMessage(getColumNo("outDistrict")
						+ "外出去向县不能输入大于50字符");
			}

			if (!validateHelper.emptyString(domain.getGoOutDetailedAddress())
					&& validateHelper.illegalStringLength(0, 50,
							domain.getGoOutDetailedAddress())) {
				result.addErrorMessage(getColumNo("goOutDetailedAddress")
						+ "外出详址不能输入大于50字符");
			}
		}
		if (!validateHelper.emptyString(domain.getHomePhone())
				&& validateHelper.illegalStringLength(0, 20,
						domain.getHomePhone())) {
			result.addErrorMessage(getColumNo("homePhone") + "住宅电话不能输入大于20字符");
		} else if (!validateHelper.emptyString(domain.getHomePhone())
				&& validateHelper.illegalTelephone(domain.getHomePhone())) {
			result.addErrorMessage(getColumNo("homePhone") + "住宅电话只能输入数字和-");
		}
		return result;
	}

}
