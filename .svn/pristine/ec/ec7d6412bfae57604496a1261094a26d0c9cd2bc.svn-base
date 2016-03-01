package com.tianque.baseInfo.overseaPersonnel.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.overseaPersonnel.service.OverseaPersonnelService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("overseaPersonnelDataConverter")
@Scope("prototype")
public class OverseaPersonnelDataConverter extends AbstractDataConverter<OverseaPersonnel> {

	@Autowired
	private OverseaPersonnelService overseaPersonnelService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private DataConvertionHelper convertionHelper;
	@Autowired
	@Qualifier("overseaValidator")
	private AbstractCountrymenValidator<OverseaPersonnel> overseaValidator;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;

	@Override
	public OverseaPersonnel convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		OverseaPersonnel result = new OverseaPersonnel();
		ExcelImportHelper.procImportDate(SpecialGroupsContext.getOverseaPersonnelImportArray(),
				cellValues, getUploadOrganization(), result, vr);
		return result;
	}

	@Override
	public ValidateResult validate(OverseaPersonnel overseaPersonnel, int realRow) {
		ValidateResult result = new ValidateResult();
		ExcelImportHelper.realRow.set(realRow);
		result = overseaValidator.validateSpecializedInfo(overseaPersonnel);
		overseaValidator.validateIsHaveHouse(overseaPersonnel, result, convertionHelper,
				actualHouseService);
		return result;
	}

	@Override
	public OverseaPersonnel persistenceDomain(OverseaPersonnel domain) {
		autoFillAddress(domain);
		return overseaPersonnelService.addOverseaPersonnel(domain);
	}

	@Override
	public boolean isRepeatData(OverseaPersonnel domain) {

		if (domain.getCertificateType() == null) {
			return overseaPersonnelService.hasDuplicateOverseaPersonnel(domain.getOrganization()
					.getId(), null, domain.getCertificateNo(), null);
		} else {
			return overseaPersonnelService.hasDuplicateOverseaPersonnel(domain.getOrganization()
					.getId(), domain.getCertificateType().getId(), domain.getCertificateNo(), null);
		}

	}

	@Override
	public OverseaPersonnel updateDomain(OverseaPersonnel domain) {
		autoFillAddress(domain);
		// 导入的时候去掉了 房屋有关的信息 所以excle更新 房屋的是时候这里没意义
		if (domain.getIsHaveHouse() != null) {
			synHouseInfo(domain);
		}

		return overseaPersonnelService.updateOverseaPersonnelByCertificate(
				domain.getCertificateType() == null ? null : domain.getCertificateType().getId(),
				domain.getCertificateNo(), domain.getOrganization().getId(), domain);
	}

	private void synHouseInfo(OverseaPersonnel domain) {
		OverseaPersonnel actualPopulation = overseaPersonnelService
				.getOverseaPersonnelByCertificateAndOrganizationId(domain.getCertificateType()
						.getId(), domain.getCertificateNo(), domain.getOrganization().getId());
		actualPopulation.setHouseId(housePopulationMaintanceService.getPopulationLivingHouseId(
				PopulationCatalog.OVERSEA_POPULATION, actualPopulation.getId()));

		if (!domain.getIsHaveHouse() && null != actualPopulation.getHouseId()) {
			houseHasActualPopulationService
					.deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
							actualPopulation.getActualPopulationType(),
							actualPopulation.getHouseId(), actualPopulation.getId());
		}
		if (domain.getIsHaveHouse() && null == actualPopulation.getHouseId()) {
			houseHasActualPopulationService
					.addHouseHasActualPopulation(new HouseHasActualPopulation(
							PopulationCatalog.ALL_ACTUAL_POPULATION, PopulationType.OVERSEA_STAFF,
							actualHouseService.getHouseInfoByHouseCodeAndOrgId(
									domain.getHouseCode(), domain.getOrganization().getId())
									.getId(), actualPopulation.getId(), 1L));
			domain.setNoHouseReason(null);
		}

		if (StringUtil.notSame(domain.getHouseCode(), actualPopulation.getHouseCode())) {
			houseHasActualPopulationService
					.updateHouseHasActualPopulationByPopulationTypeAndHouseCodes(
							PopulationType.OVERSEA_STAFF, actualPopulation.getId(),
							actualPopulation.getHouseCode(), domain.getHouseCode(), domain
									.getOrganization().getId());
		}
	}

	private void autoFillAddress(OverseaPersonnel domain) {
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
