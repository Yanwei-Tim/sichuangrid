package com.tianque.baseInfo.unsettledPopulation.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.baseInfo.unsettledPopulation.service.UnsettledPopulationService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationMutexService;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("unsettledPopulationDataConverter")
@Scope("prototype")
public class UnsettledPopulationDataConverter extends AbstractDataConverter<UnsettledPopulation> {
	@Autowired
	private UnsettledPopulationService unsettledPopulationService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private DataConvertionHelper convertionHelper;
	@Autowired
	@Qualifier("unsettledPopulationValidator")
	private AbstractCountrymenValidator<UnsettledPopulation> unsettledPopulationValidator;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;
	@Autowired
	private ActualPopulationMutexService actualPopulationMutexService;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(UnsettledPopulation unsettledPopulation, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		Countrymen domain = unsettledPopulation;
		ValidateResult result = new ValidateResult();
		if (domain.getIdCardNo() == null) {
			// 身份证为非必填项 BUG单 #21412 未落户人口中错误分析表显示为身份证必须填写 注释掉
			// result.addErrorMessage(unsettledPopulationValidator.getColumNo("idCardNo")
			// + " 身份证 号码不能为空");
			// fateson add 身份证为空的时候 其他字段就不会验证了,因此注释掉 return
			// return result;
		} else {
			if (Boolean.TRUE.toString().equals(
					globalSettingService.getGlobalValue(GlobalSetting.ACTUAL_POPULATION_MUTEX))) {
				if (domain.getOrganization() != null
						&& null != domain.getIdCardNo()
						&& actualPopulationMutexService
								.isExistByOrgIdAndIdCardNoAndExcludeActualPopulationType(domain
										.getOrganization().getId(), domain.getIdCardNo(),
										PopulationType.UNSETTLED_POPULATION)) {
					result.addErrorMessage(unsettledPopulationValidator.getColumNo("idCardNo")
							+ " 身份证 号码已经在其他人口中存在，不能再添加");
				}
			}
		}
		result.addAllErrorMessage(unsettledPopulationValidator.validateBaseInfo(domain));
		unsettledPopulationValidator.validateIsHaveHouse(domain, result, convertionHelper,
				actualHouseService);
		result.addAllErrorMessage(unsettledPopulationValidator
				.validateSpecializedInfo((UnsettledPopulation) domain));

		return result;
	}

	@Override
	public UnsettledPopulation convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		UnsettledPopulation result = new UnsettledPopulation();
		ExcelImportHelper.procImportDate(SpecialGroupsContext.getUnsettledPopulationImportArray(),
				cellValues, getUploadOrganization(), result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization().getOrgInternalCode());
		}
		return result;
	}

	@Override
	public boolean isRepeatData(UnsettledPopulation domain) {
		boolean flag = null != domain.getIdCardNo()
				&& unsettledPopulationService.hasDuplicateUnsettledPopulation(domain
						.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(unsettledPopulationService.getUnsettledPopulationByIdCardNo(
					domain.getIdCardNo(), domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public UnsettledPopulation persistenceDomain(UnsettledPopulation domain) {
		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization().getOrgInternalCode());
		}
		autoFillIdCardNo(domain);
		autoFillAddress(domain);
		unsettledPopulationService.addUnsettledPopulation(domain);
		return domain;
	}

	@Override
	public UnsettledPopulation updateDomain(UnsettledPopulation domain) {
		autoFillIdCardNo(domain);
		autoFillAddress(domain);
		synHouseInfo(domain);
		unsettledPopulationService.updateUnsettledPopulation(domain);
		return domain;
	}

	private void autoFillIdCardNo(UnsettledPopulation domain) {
		if (null == domain.getIdCardNo()) {
			domain.setIdCardNo("");
		}
	}

	private void synHouseInfo(UnsettledPopulation domain) {
		UnsettledPopulation actualPopulation = unsettledPopulationService
				.getUnsettledPopulationById(domain.getId());
		actualPopulation.setHouseId(housePopulationMaintanceService.getPopulationLivingHouseId(
				PopulationCatalog.UNHOUSEHOULD_POPULATION, actualPopulation.getId()));

		if (!Boolean.TRUE.equals(domain.getIsHaveHouse()) && null != actualPopulation.getHouseId()) {
			houseHasActualPopulationService
					.deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
							actualPopulation.getActualPopulationType(),
							actualPopulation.getHouseId(), actualPopulation.getId());
		}
		if (Boolean.TRUE.equals(domain.getIsHaveHouse()) && null == actualPopulation.getHouseId()) {
			houseHasActualPopulationService
					.addHouseHasActualPopulation(new HouseHasActualPopulation(
							PopulationCatalog.ALL_ACTUAL_POPULATION,
							PopulationType.UNSETTLED_POPULATION, actualHouseService
									.getHouseInfoByHouseCodeAndOrgId(domain.getHouseCode(),
											domain.getOrganization().getId()).getId(),
							actualPopulation.getId(), 1L));
			domain.setNoHouseReason(null);
		}
		if (StringUtil.notSame(domain.getHouseCode(), actualPopulation.getHouseCode())) {
			houseHasActualPopulationService
					.updateHouseHasActualPopulationByPopulationTypeAndHouseCodes(
							PopulationType.UNSETTLED_POPULATION, actualPopulation.getId(),
							actualPopulation.getHouseCode(), domain.getHouseCode(), domain
									.getOrganization().getId());
		}
	}

	private void autoFillAddress(UnsettledPopulation domain) {
		if (domain.getIsHaveHouse() == null) {
			return;
		}
		if (domain.getIsHaveHouse() && null != domain.getHouseCode()
				&& domain.getHouseCode().length() > 0) {
			HouseInfo houseInfo = actualHouseService.getHouseInfoByHouseCodeAndOrgId(
					domain.getHouseCode(), domain.getOrganization().getId());
			domain.setHouseId(houseInfo.getId());
			domain.setCurrentAddressType(convertionHelper.convertToPropertyDict(
					PropertyTypes.CURRENT_ADDRESS_TYPE, "商品房"));
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
