package com.tianque.baseInfo.householdStaff.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.PropertyDict;
import com.tianque.service.CensusRegisterFamilyService;
import com.tianque.service.impl.CensusRegisterFamilyServiceImpl;
import com.tianque.validate.AbstractCountrymenValidator;

@Service("householdStaffValidator")
@Scope("prototype")
public class HouseholdStaffValidatorImpl extends
		AbstractCountrymenValidator<HouseholdStaff> {

	@Autowired
	private CensusRegisterFamilyService censusRegisterFamilyService;
	@Autowired
	HouseholdStaffService householdStaffService;
	public static ThreadLocal<List<String>> realRow = new ThreadLocal<List<String>>();

	public HouseholdStaffValidatorImpl() {
		if (realRow.get() != null) {
			realRow.remove();
		}
	}

	/**
	 * 判断导入的数据的户口号是否在数据库中存在户主.YES,不能添加，给出提示
	 * 
	 * @param domain
	 * @return ValidateResult
	 */
	public boolean validateSQLAccountNumber(HouseholdStaff domain) {
		boolean result = false;
		PropertyDict relationShipWithHead = domain.getRelationShipWithHead();
		String displayName = relationShipWithHead.getDisplayName();
		if (validateHelper.containValue(displayName, new String[] { "户主",
				"小集体户主" })) {
			// true:这个户口号存在
			CensusRegisterFamily crf = censusRegisterFamilyService
					.getCensusRegisterFamilyByOrgIdAndAccountNumber(domain
							.getAccountNumber(), domain.getOrganization()
							.getId());
			if (crf != null) {
				// true:这个户口号已存在户主 ，还需要判断户口号 中的 人 和 关系 和excel中是否一致
				if (crf.getIdCardNo() != null) {

					// fateson add 如果身份证一致 并且 户主关系一致的话 就验证通过 --->允许更新
					if (!StringUtils.isEmpty(domain.getIdCardNo())) {
						// if idcardno of excel equals idcardno of db
						if (!domain.getIdCardNo().equals(crf.getIdCardNo())) {
							return true;
						}

						// 获取系统中的 户主关系
						HouseholdStaff householdStaff = householdStaffService
								.findHouseholdStaffByCardNoAndOrgId(domain
										.getIdCardNo(), domain
										.getOrganization().getId());

						if (householdStaff != null) {
							// 系统中 户主的关系
							PropertyDict dbRelationShipWithHead = householdStaff
									.getRelationShipWithHead();

							if (dbRelationShipWithHead != null) {
								Long dbRelationShipWithHeadId = dbRelationShipWithHead
										.getId();
								// excel 中户主关系
								Long excelRelationShipWithHeadId = relationShipWithHead
										.getId();
								if (dbRelationShipWithHeadId.longValue() == excelRelationShipWithHeadId
										.longValue()) {
									return false;
								} else {
									return true;
								}
							}

						}

					}

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
	public boolean validateExcelAccountNumber(HouseholdStaff domain) {
		boolean result = false;
		if (validateHelper.containValue(domain.getRelationShipWithHead()
				.getDisplayName(), new String[] { "户主", "小集体户主" })) {
			if (realRow.get() == null) {
				// 第一次与户主关系为户主的户口号
				List<String> list = new ArrayList<String>();
				list.add(domain.getAccountNumber());
				realRow.set(list);
			} else {
				if (realRow.get().contains(domain.getAccountNumber())) {
					result = true;
				} else {
					realRow.get().add(domain.getAccountNumber());
				}
			}
		}
		return result;
	}

	@Override
	public ValidateResult validateSpecializedInfo(HouseholdStaff domain) {
		ValidateResult result = new ValidateResult();
		boolean validataAccountNumber = true;
		if (validateHelper.emptyString(domain.getAccountNumber())) {
			result.addErrorMessage(getColumNo("accountNumber") + "户口号必须输入");
			validataAccountNumber = false;
		} else if (validateHelper.illegalStringLength(1, 50,
				domain.getAccountNumber())) {
			result.addErrorMessage(getColumNo("accountNumber")
					+ "户口号只能输入1-50个字符");
			validataAccountNumber = false;
		}
		if (null == domain.getRelationShipWithHead()) {
			result.addErrorMessage(getColumNo("relationShipWithHead")
					+ "与户主关系必须选择");

		}
		if (null == domain.getResidentStatus()) {
			result.addErrorMessage(getColumNo("residentStatus") + "人户状态必须选择");
		}

		if (null != domain.getRelationShipWithHead() && validataAccountNumber) {
			// FIXME 完成不能重复选择户主的验证
			// 先判断在excel中是不是 已经存在
			if (this.validateExcelAccountNumber(domain)) {
				result.addErrorMessage(getColumNo("accountNumber") + "户口号"
						+ domain.getAccountNumber() + "在Excel中已经选为户主，不能添加");
			} else {
				// 在判断系统中的
				if (this.validateSQLAccountNumber(domain)) {
					result.addErrorMessage(getColumNo("accountNumber") + "户口号："
							+ domain.getAccountNumber() + "在系统中已存在户主，不能添加!");
				}

			}
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

		// 判断该网格是否存在相同身份的户主

		if (this.validateSQLCensusRegisterFamily(domain)) {
			result.addErrorMessage(getColumNo("relationShipWithHead")
					+ "该网格已存在相同身份证的户主");
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
		// if (!validateHelper.nullObj(domain.getResidenceType())
		// && !validateHelper.emptyString(domain.getResidenceType()
		// .getDisplayName())) {
		// if (validateHelper.illegalPropertyDictDisplayName(
		// PropertyTypes.RESIDENCE_TYPE, domain.getResidenceType()
		// .getDisplayName())) {
		// result
		// .addErrorMessage(getColumNo("residenceType"),
		// "户口类别选择不正确");
		// }
		// }
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

	private boolean validateSQLCensusRegisterFamily(HouseholdStaff domain) {
		boolean result = false;
		if (domain.getRelationShipWithHead() != null) {
			PropertyDict relationShipWithHead = domain
					.getRelationShipWithHead();
			String displayName = relationShipWithHead.getDisplayName();
			if (validateHelper.containValue(displayName, new String[] { "户主",
					"小集体户主" })) {
				String idCardNo = domain.getIdCardNo();
				Long orgId = domain.getOrganization().getId();
				Long id = null;
				result = ((CensusRegisterFamilyServiceImpl) censusRegisterFamilyService)
						.hasDuplicateCensusRegisterFamily(idCardNo, orgId, id);
			}
		}
		return result;

	}
}
