package com.tianque.baseInfo.floatingPopulation.dataConverter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.floatingPopulation.dao.FloatingPopulationDao;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationMutexService;
import com.tianque.service.util.PopulationType;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("floatingPopulationDataConverter")
@Scope("prototype")
public class FloatingPopulationDataConverter extends
		AbstractDataConverter<FloatingPopulation> {

	@Autowired
	private FloatingPopulationService floatingPopulationService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private DataConvertionHelper convertionHelper;
	@Autowired
	@Qualifier("floatingPopulationValidator")
	private AbstractCountrymenValidator<FloatingPopulation> floatingPopulationValidator;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualHouseService actualHouseService;

	@Autowired
	private ActualPopulationMutexService actualPopulationMutexService;

	@Autowired
	private FloatingPopulationDao floatingPopulationDao;

	@Override
	public ValidateResult validate(FloatingPopulation floatingPopulation,
			int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		Countrymen domain = floatingPopulation;
		domain.setActualPopulationType(PopulationType.FLOATING_POPULATION);
		ValidateResult result = new ValidateResult();
		if (domain.getIdCardNo() == null) {
			result.addErrorMessage(floatingPopulationValidator
					.getColumNo("idCardNo") + " 身份证 号码不能为空");
			// return result;
		} else {
			if (Boolean.TRUE
					.toString()
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.ACTUAL_POPULATION_MUTEX))) {
				if (domain.getOrganization() != null
						&& null != domain.getIdCardNo()
						&& actualPopulationMutexService
								.isExistByOrgIdAndIdCardNoAndExcludeActualPopulationType(
										domain.getOrganization().getId(),
										domain.getIdCardNo(),
										PopulationType.FLOATING_POPULATION)) {
					result.addErrorMessage(floatingPopulationValidator
							.getColumNo("idCardNo") + " 身份证 号码已经在其他人口中存在，不能再添加");
				}
			}
		}
		result.addAllErrorMessage(validateIsDeathAndEmphis(floatingPopulation,
				realRow));
		result.addAllErrorMessage(floatingPopulationValidator
				.validateBaseInfo(domain));
		floatingPopulationValidator.validateIsHaveHouse(domain, result,
				convertionHelper, actualHouseService);
		fillDefaultBirthday(domain);
		result.addAllErrorMessage(floatingPopulationValidator
				.validateSpecializedInfo((FloatingPopulation) domain));
		return result;
	}

	@Override
	public FloatingPopulation convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		FloatingPopulation result = new FloatingPopulation();
		ExcelImportHelper.procImportDate(
				SpecialGroupsContext.getFloatingPopulationImportArray(),
				cellValues, getUploadOrganization(), result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization()
					.getOrgInternalCode());
		}
		return result;
	}

	@Override
	public boolean isRepeatData(FloatingPopulation domain) {
		boolean flag = floatingPopulationService
				.hasDuplicateFloatingPopulation(domain.getOrganization()
						.getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(floatingPopulationService
					.findFloatingPopulationByCardNoAndOrgId(
							domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public FloatingPopulation persistenceDomain(FloatingPopulation domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		if (checkDataExitInCache(NewBaseInfoTables.FLOATINGPOPULATION_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		fillDefaultBirthday(domain);
		autoFillAddress(domain);
		Countrymen exist = baseInfoService.existBaseInfo(domain.getIdCardNo());
		if (exist != null) {
			domain.setBaseInfoId(exist.getId());
		}
		floatingPopulationService.addFloatingPopulation(domain);

		cleanDataInCache(NewBaseInfoTables.FLOATINGPOPULATION_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	@Override
	public FloatingPopulation updateDomain(FloatingPopulation domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		fillDefaultBirthday(domain);
		autoFillAddress(domain);
		FloatingPopulation temp = floatingPopulationService
				.getFloatingPopulationById(domain.getId());
		domain.setBaseInfoId(temp.getBaseInfoId());
		domain.setAddressInfoId(temp.getAddressInfoId());
		floatingPopulationService.updateFloatingPopulation(domain);
		return domain;
	}

	private void autoFillAddress(FloatingPopulation domain) {
		if (domain.getIsHaveHouse() == null) {
			return;
		}
		if (domain.getIsHaveHouse() && null != domain.getHouseCode()
				&& domain.getHouseCode().length() > 0) {
			HouseInfo houseInfo = actualHouseService
					.getHouseInfoByHouseCodeAndOrgId(domain.getHouseCode(),
							domain.getOrganization().getId());
			domain.setHouseId(houseInfo.getId());
			domain.setCurrentAddressType(convertionHelper
					.convertToPropertyDict(PropertyTypes.CURRENT_ADDRESS_TYPE,
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
			domain.setNoHouseReason(null);
		}
	}

	public void persistenceDomain(List<FloatingPopulation> data) {
		for (int i = 0; i < data.size(); i++) {
			fillDefaultBirthday(data.get(i));
			autoFillAddress(data.get(i));
		}
		floatingPopulationDao.batchInsertDate(data);
	}

	public void updateDomain(List<FloatingPopulation> data) {
		for (int i = 0; i < data.size(); i++) {
			fillDefaultBirthday(data.get(i));
			autoFillAddress(data.get(i));
		}
		floatingPopulationDao.batchUpdateDate(data);
	}
}
