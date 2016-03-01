package com.tianque.baseInfo.householdStaff.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationMutexService;
import com.tianque.service.util.PopulationType;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("householdStaffDataConverter")
@Scope("prototype")
public class HouseholdStaffDataConverter extends
		AbstractDataConverter<HouseholdStaff> {

	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private DataConvertionHelper convertionHelper;
	@Autowired
	@Qualifier("householdStaffValidator")
	private AbstractCountrymenValidator<HouseholdStaff> householdStaffValidator;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private ActualPopulationMutexService actualPopulationMutexService;

	@Override
	public ValidateResult validate(HouseholdStaff householdStaff, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		Countrymen domain = householdStaff;
		ValidateResult result = new ValidateResult();
		boolean flag = true;
		if (domain.getIdCardNo() == null) {
			result.addErrorMessage(householdStaffValidator
					.getColumNo("idCardNo") + " 身份证 号码不能为空");
			flag = false;
		}

		if (flag
				&& domain.getOrganization() != null
				&& domain.getOrganization().getId() != null
				&& checkDataExitInCache(NewBaseInfoTables.HOUSEHOLDSTAFF_KEY,
						domain.getIdCardNo(), domain.getOrganization().getId()
								.toString())) {
			result.addErrorMessage(householdStaffValidator
					.getColumNo("idCardNo") + " 身份证号码已经在网格中存在，不能再添加");
			flag = false;
		}
		if (flag
				&& Boolean.TRUE
						.toString()
						.equals(globalSettingService
								.getGlobalValue(GlobalSetting.ACTUAL_POPULATION_MUTEX))) {
			if (domain.getOrganization() != null
					&& null != domain.getIdCardNo()
					&& actualPopulationMutexService
							.isExistByOrgIdAndIdCardNoAndExcludeActualPopulationType(
									domain.getOrganization().getId(),
									domain.getIdCardNo(),
									PopulationType.HOUSEHOLD_STAFF)) {
				result.addErrorMessage(householdStaffValidator
						.getColumNo("idCardNo") + " 身份证 号码已经在其他人口中存在，不能再添加");
				flag = false;
			}
		}
		if (flag) {
			result.addAllErrorMessage(validateIsDeathAndEmphis(householdStaff,
					realRow));
			result.addAllErrorMessage(householdStaffValidator
					.validateBaseInfo(domain));
			householdStaffValidator.validateIsHaveHouse(domain, result,
					convertionHelper, actualHouseService);
			fillDefaultBirthday(domain);
			result.addAllErrorMessage(householdStaffValidator
					.validateSpecializedInfo((HouseholdStaff) domain));
		}

		return result;
	}

	@Override
	public HouseholdStaff convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		HouseholdStaff result = new HouseholdStaff();
		ExcelImportHelper.procImportDate(
				SpecialGroupsContext.getHouseholdStaffImportArray(),
				cellValues, getUploadOrganization(), result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization()
					.getOrgInternalCode());
		}
		return result;
	}

	@Override
	public boolean isRepeatData(HouseholdStaff domain) {
		boolean flag = householdStaffService.hasDuplicateHouseholdStaff(
				domain.getIdCardNo(), domain.getOrganization().getId(), null);
		if (flag) {
			domain.setId(householdStaffService
					.findHouseholdStaffByCardNoAndOrgId(domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public HouseholdStaff persistenceDomain(HouseholdStaff domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}

		fillDefaultBirthday(domain);
		autoFillAddress(domain);
		initDefaultFields(domain);
		Countrymen exist = baseInfoService.existBaseInfo(domain.getIdCardNo());
		if (exist != null) {
			domain.setBaseInfoId(exist.getId());
		}

		householdStaffService.setHouseholdStaff(domain);
		// HouseholdStaff temp =
		// 判断家庭称呼是否有值有就存入
		// if (domain.getHouseMarchType() != null
		// && domain.getHouseMarchType().size() > 0) {
		// List<Long> houseMarchTypeList = new ArrayList<Long>();
		// for (PropertyDict dict : domain.getHouseMarchType()) {
		// if (dict.getId() != null) {
		// houseMarchTypeList.add(dict.getId());
		// }
		// }
		// householdStaffService.regrantFamilyHouse(temp, houseMarchTypeList);
		// }

		cleanDataInCache(NewBaseInfoTables.HOUSEHOLDSTAFF_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public HouseholdStaff updateDomain(HouseholdStaff domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		fillDefaultBirthday(domain);
		autoFillAddress(domain);
		initDefaultFields(domain);
		HouseholdStaff temp = householdStaffService
				.getHouseholdStaffById(domain.getId());
		domain.setAddressInfoId(temp.getAddressInfoId());
		domain.setBaseInfoId(temp.getBaseInfoId());
		householdStaffService.updateHouseholdStaff(domain);
		return domain;
	}

	private void autoFillAddress(HouseholdStaff domain) {
		if (domain.getIsHaveHouse() != null) {
			if (domain.getIsHaveHouse() && null != domain.getHouseCode()
					&& domain.getHouseCode().length() > 0) {
				HouseInfo houseInfo = actualHouseService
						.getHouseInfoByHouseCodeAndOrgId(domain.getHouseCode(),
								domain.getOrganization().getId());
				domain.setHouseId(houseInfo.getId());
				domain.setCurrentAddressType(houseInfo.getAddressType());
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
				domain.setNoHouseReason(null);
			}
		}

	}

	private void initDefaultFields(HouseholdStaff domain) {
		if (null == domain.getOutGone()
				|| (null != domain.getOutGone() && !domain.getOutGone())) {
			domain.setOutReasons(null);
			domain.setReasonsDate(null);
			domain.setOutProvince(null);
			domain.setOutCity(null);
			domain.setOutDistrict(null);
			domain.setGoOutDetailedAddress(null);
		}
		if (!validateHelper.containValue(domain.getRelationShipWithHead()
				.getDisplayName(), new String[] { "本人", "户主", "小集体户主" })) {
			domain.setHomePhone(null);
		}
	}
}
