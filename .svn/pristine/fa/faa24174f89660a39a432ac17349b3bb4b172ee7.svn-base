package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.FarmerPerIncomeInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("farmerPerIncomeInfoValidator")
public class FarmerPerIncomeInfoValidator implements
		DomainValidator<FarmerPerIncomeInfo> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(FarmerPerIncomeInfo domain) {
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
		// 验证domain中orgid是否是乡镇层级
		if (domain.getOrganization() == null
				|| domain.getOrganization().getId() == null) {
			throw new BusinessValidationException("缺少层级参数");
		}
		if (organizationDubboService
				.getFullOrgById(domain.getOrganization().getId()).getOrgLevel()
				.getInternalId() != OrganizationLevel.VILLAGE) {
			throw new BusinessValidationException("层级错误,操作失败");
		}
		// 验证farmerPerIncome农民可支配人均收入
		if (domain.getFarmerPerIncome() != null) {
			if (domain.getFarmerPerIncome() > 999999999.99D
					|| domain.getFarmerPerIncome() < 0) {
				result.addErrorMessage("农民可支配人均收入范围0到999999999.99");
			}
		}
		// 验证 农业主导产业收入
		if (domain.getAgriculturalIncome() != null) {
			if (domain.getAgriculturalIncome() > 999999999.99D
					|| domain.getAgriculturalIncome() < 0) {
				result.addErrorMessage("农业主导产业收入范围0到999999999.99");
			}
		}
		// 验证 农村家庭经营性收入
		if (domain.getHouseholdIncome() != null) {
			if (domain.getHouseholdIncome() > 999999999.99D
					|| domain.getHouseholdIncome() < 0) {
				result.addErrorMessage("农村家庭经营性收入范围0到999999999.99");
			}
		}
		//验证村集体经济收入
		if (domain.getVillageCollectiveIncome() != null) {
			if (domain.getVillageCollectiveIncome() > 999999999.99D
					|| domain.getVillageCollectiveIncome() < 0) {
				result.addErrorMessage("村集体经济收入范围0到999999999.99");
			}
		}
		 
		return result;
	}
}
