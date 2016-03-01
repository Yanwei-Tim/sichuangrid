package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.IndustryDevelopment;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("industryDevelopmentValidator")
public class IndustryDevelopmentValidator implements
		DomainValidator<IndustryDevelopment> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(IndustryDevelopment domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		if (domain.getOrganization() == null
				|| domain.getOrganization().getId() == null) {
			throw new BusinessValidationException("请选择正确的层级");
		}
		if (organizationDubboService
				.getFullOrgById(domain.getOrganization().getId()).getOrgLevel()
				.getInternalId() != OrganizationLevel.VILLAGE) {
			throw new BusinessValidationException("层级错误,操作失败");
		}
		ValidateResult result = new ValidateResult();
		// 粮食作物亩数
		if (domain.getGrainCrops() != null) {
			if (domain.getGrainCrops() < 0
					|| domain.getGrainCrops() > 999999999.99D)
				result.addErrorMessage("粮食作物亩数 值应在0-999999999.99");
		}
		// 经济作物亩数
		if (domain.getEconomicCrops() != null) {
			if (domain.getEconomicCrops() < 0
					|| domain.getEconomicCrops() > 999999999.99D)
				result.addErrorMessage("经济作物亩数 值应在0-999999999.99");
		}
		// 适度规模种植户数
		if (domain.getScalePlanting() != null) {
			if (domain.getScalePlanting() < 0
					|| domain.getScalePlanting() > 99999999)
				result.addErrorMessage("适度规模种植户数值应在0-99999999");
		}
		// 生猪数
		if (domain.getPigNum() != null) {
			if (domain.getPigNum() < 0 || domain.getPigNum() > 99999999)
				result.addErrorMessage("生猪数值应在0-99999999");
		}
		// 牛羊数
		if (domain.getCattleSheepNum() != null) {
			if (domain.getCattleSheepNum() < 0
					|| domain.getCattleSheepNum() > 99999999)
				result.addErrorMessage("牛羊数值应在0-99999999");
		}
		// 小家禽数
		if (domain.getPoultryNum() != null) {
			if (domain.getPoultryNum() < 0
					|| domain.getPoultryNum() > 99999999)
				result.addErrorMessage("小家禽数值应在0-99999999");
		}
		// 水产数
		if (domain.getAquaticProductNum() != null) {
			if (domain.getAquaticProductNum() < 0
					|| domain.getAquaticProductNum() > 999999999.99D)
				result.addErrorMessage("水产数值应在0-999999999.99");
		}
		// 适度规模养殖户
		if (domain.getScaleBreed() != null) {
			if (domain.getScaleBreed() < 0
					|| domain.getScaleBreed() > 99999999)
				result.addErrorMessage("适度规模养殖户值应在0-99999999");
		}
		// 专业合作组织数
		if (domain.getSpecialistNum() != null) {
			if (domain.getSpecialistNum() < 0
					|| domain.getSpecialistNum() > 99999999)
				result.addErrorMessage("专业合作组织数值应在0-99999999");
		}
		// 家庭农场(个数)
		if (domain.getFamilyFarmNum() != null) {
			if (domain.getFamilyFarmNum() < 0
					|| domain.getFamilyFarmNum() > 99999999)
				result.addErrorMessage("家庭农场数值应在0-99999999");
		}
		//种养大户户数
		if (domain.getPlantingNum() != null) {
			if (domain.getPlantingNum() < 0
					|| domain.getPlantingNum() > 99999999)
				result.addErrorMessage("种养大户户数值应在0-99999999");
		}
		
		//特色产业种植数
		if(domain.getCharacteristicPlanting() !=null){
			if (domain.getCharacteristicPlanting() < 0
					|| domain.getCharacteristicPlanting() > 999999999.99D)
				result.addErrorMessage("特色产业种植数应在0-999999999.99");
		}
		//种植户总数
		if (domain.getPlantingHouseholdsCount() != null) {
			if (domain.getPlantingHouseholdsCount() < 0
					|| domain.getPlantingHouseholdsCount() > 99999999)
				result.addErrorMessage("种植户总数值应在0-99999999");
		}
		//养殖户总数
		if (domain.getFarmHouseholds() != null) {
			if (domain.getFarmHouseholds() < 0
					|| domain.getFarmHouseholds() > 99999999)
				result.addErrorMessage("养殖户总数值应在0-99999999");
		}
		//农产品加工企业
		if (domain.getProductProcessing()!= null) {
			if (domain.getProductProcessing() < 0
					|| domain.getProductProcessing() > 99999999)
				result.addErrorMessage("农产品加工企业值应在0-99999999");
		}
		//其中县级以上龙头企业
		if (domain.getCountyCorporateChampion()!= null) {
			if (domain.getCountyCorporateChampion() < 0
					|| domain.getCountyCorporateChampion() > 99999999)
				result.addErrorMessage("县级以上龙头企业值应在0-99999999");
		}
		//产业化经营组织带动农户数
		if (domain.getIndustrialOrganization()!= null) {
			if (domain.getIndustrialOrganization() < 0
					|| domain.getIndustrialOrganization() > 99999999)
				result.addErrorMessage("产业化经营组织带动农户数值应在0-99999999");
		}
		//乡村酒店
		if (domain.getVillaggioBoutiqueHotel()!= null) {
			if (domain.getVillaggioBoutiqueHotel() < 0
					|| domain.getVillaggioBoutiqueHotel() > 99999999)
				result.addErrorMessage("乡村酒店数值应在0-99999999");
		}
		//农家乐
		if (domain.getAgritainment()!= null) {
			if (domain.getAgritainment() < 0
					|| domain.getAgritainment() > 99999999)
				result.addErrorMessage("农家乐数值应在0-99999999");
		}
		//带动农户就业人数
		if (domain.getHouseholdEmployment()!= null) {
			if (domain.getHouseholdEmployment() < 0
					|| domain.getHouseholdEmployment() > 99999999)
				result.addErrorMessage("带动农户就业人数应在0-99999999");
		}
		return result;
	}
}
