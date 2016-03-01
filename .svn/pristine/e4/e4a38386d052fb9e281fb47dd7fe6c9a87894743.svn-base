package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.constant.ComprehensiveInfoConstant;
import com.tianque.newVillage.domain.Infrastructure;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("infrastructureValidator")
public class InfrastructureValidator implements DomainValidator<Infrastructure> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(Infrastructure domain) {
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
		// 验证村社道路
		if (domain.getVillageRoad() != null) {
			if (domain.getVillageRoad() < 0
					|| domain.getVillageRoad() > 999999999.99D)
				result.addErrorMessage("村社道路 公里值应在0-999999999.99");
		}
		// 验证验证村路-硬化路
		if (domain.getVillageHardenedRoad() != null) {
			if (domain.getVillageHardenedRoad() < 0
					|| domain.getVillageHardenedRoad() > 999999999.99D)
				result.addErrorMessage("村路-硬化路 公里值应在0-999999999.99");
			/*if (domain.getVillageRoad() != null
					&& domain.getVillageRoad() < domain
							.getVillageHardenedRoad()) {
				result.addErrorMessage("村路-硬化路不应该高于村社道路公里数");
			}*/
		}
		// 验证村路-泥结路
		if (domain.getVillageMudKnotRoad() != null) {
			if (domain.getVillageMudKnotRoad() < 0
					|| domain.getVillageMudKnotRoad() > 999999999.99D)
				result.addErrorMessage("村路-泥结路 公里值应在0-999999999.99");
			/*if (domain.getVillageRoad() != null
					&& domain.getVillageRoad() < domain.getVillageMudKnotRoad()) {
				result.addErrorMessage("村路-泥结路不应该高于村社道路公里数");
			}*/
		}
		//		if (domain.getVillageMudKnotRoad() != null
		//				&& domain.getVillageHardenedRoad() != null
		//				&& domain.getVillageRoad() != null) {
		//			if (domain.getVillageMudKnotRoad()
		//					+ domain.getVillageHardenedRoad() > domain.getVillageRoad()) {
		//				result.addErrorMessage("村路-泥结路和村路-硬化路之和不应该高于村社道路公里数");
		//			}
		//		}
		// 验证塘湖堰池(数量)
		if (domain.getWeirPoolNum() != null) {
			if (domain.getWeirPoolNum() < 0
					|| domain.getWeirPoolNum() > 99999999)
				result.addErrorMessage("塘湖堰池个数值应在0-99999999");
		}
		// 验证水渠总长度(公里)
		if (domain.getCanalLength() != null) {
			if (domain.getCanalLength() < 0
					|| domain.getCanalLength() > 999999999.99D)
				result.addErrorMessage("水渠总长度 公里值应在0-999999999.99");
		}
		// 生活饮用水已解决人户数
		if (domain.getDrinkingWaterNum() != null) {
			if (domain.getDrinkingWaterNum() < 0
					|| domain.getDrinkingWaterNum() > 99999999)
				result.addErrorMessage("生活饮用水已解决人户数值应在0-99999999");
		}
		// 验证沼气(口)
		if (domain.getBiogasNum() != null) {
			if (domain.getBiogasNum() < 0 || domain.getBiogasNum() > 99999999)
				result.addErrorMessage("沼气口数值 应在0-99999999");
		}
		// 验证三建四改设计户数
		if (domain.getInvolveHouseCount() != null) {
			if (domain.getInvolveHouseCount() < 0
					|| domain.getInvolveHouseCount() > 99999999)
				result.addErrorMessage("三建四改涉及户数 应在0-99999999");
		}
		// 验证有线电视
		if (domain.getCableTvCount() != null) {
			if (domain.getCableTvCount() < 0
					|| domain.getCableTvCount() > 99999999)
				result.addErrorMessage("有线电视户数 应在0-99999999");
		}
		// 验证宽带
		if (domain.getBroadbandCount() != null) {
			if (domain.getBroadbandCount() < 0
					|| domain.getBroadbandCount() > 99999999)
				result.addErrorMessage("宽带户数 应在0-99999999");
		}
		//验证电网是否改造
		if (domain.getIsPowerGrid() != null) {
			if (domain.getIsPowerGrid() != ComprehensiveInfoConstant.YES
					&& domain.getIsPowerGrid() != ComprehensiveInfoConstant.NO) {
				result.addErrorMessage("电网是否改造参数错误");
			}
		}
		//验证是否宽带乡村
		if (domain.getIsBroadbandVillage() != null) {
			if (domain.getIsBroadbandVillage() != ComprehensiveInfoConstant.YES
					&& domain.getIsBroadbandVillage() != ComprehensiveInfoConstant.NO) {
				result.addErrorMessage("是否宽带乡村 参数错误");
			}
		}
		return result;
	}
}
