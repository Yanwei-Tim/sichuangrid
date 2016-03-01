package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.CapitalInvested;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("capitalInvestedValidator")
public class CapitalInvestedValidator implements
		DomainValidator<CapitalInvested> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(CapitalInvested domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();
		// 验证domain中orgid是否是乡镇层级
		if (domain.getOrganization() == null
				|| domain.getOrganization().getId() == null) {
			throw new BusinessValidationException("请选择正确的层级");
		}
		if (organizationDubboService
				.getFullOrgById(domain.getOrganization().getId()).getOrgLevel()
				.getInternalId() != OrganizationLevel.VILLAGE) {
			throw new BusinessValidationException("层级错误,操作失败");
		}
		// 验证中央/省份投入资金
		if (domain.getCentralProvinceInvested() != null) {
			if (domain.getCentralProvinceInvested() < 0
					|| domain.getCentralProvinceInvested() > 999999999.99D) {
				result.addErrorMessage("中央/省份投入资金应该在0-999999999.99万元范围");
			}
		}
		// 验证市投入资金
		if (domain.getMunicipalityInvested() != null) {
			if (domain.getMunicipalityInvested() < 0
					|| domain.getMunicipalityInvested() > 999999999.99D) {
				result.addErrorMessage("市投入资金应该在0-999999999.99万元范围");
			}
		}
		// 验证县资金投入
		if (domain.getCountyInvested() != null) {
			if (domain.getCountyInvested() < 0
					|| domain.getCountyInvested() > 999999999.99D) {
				result.addErrorMessage("县资金投入应该在0-999999999.99万元范围");
			}
		}
		// 验证金融投入资金
		if (domain.getFinancialInvested() != null) {
			if (domain.getFinancialInvested() < 0
					|| domain.getFinancialInvested() > 999999999.99D) {
				result.addErrorMessage("金融投入资金应该在0-999999999.99万元范围");
			}
		}
		// 验证工商投入资金
		if (domain.getIndustryAndCommerceInvested() != null) {
			if (domain.getIndustryAndCommerceInvested() < 0
					|| domain.getIndustryAndCommerceInvested() > 999999999.99D) {
				result.addErrorMessage("工商投入资金应该在0-999999999.99万元范围");
			}
		}
		// 验证农民自筹资金
		if (domain.getFarmerInvested() != null) {
			if (domain.getFarmerInvested() < 0
					|| domain.getFarmerInvested() > 999999999.99D) {
				result.addErrorMessage("农民自筹资金应该在0-999999999.99万元范围");
			}
		}
		
		//概算总投资
		if (domain.getAllInvestmentCount() != null) {
			if (domain.getAllInvestmentCount() < 0
					|| domain.getAllInvestmentCount() > 999999999.99D) {
				result.addErrorMessage("概算总投资应该在0-999999999.99万元范围");
			}
		}
		//财政资金投入总额
		if (domain.getCapitalInvestmentCount() != null) {
			if (domain.getCapitalInvestmentCount() < 0
					|| domain.getCapitalInvestmentCount() > 999999999.99D) {
				result.addErrorMessage("财政资金投入资金应该在0-999999999.99万元范围");
			}
		}
		//社会资金投入总额
		if (domain.getSociologyInvested() != null) {
			if (domain.getSociologyInvested() < 0
					|| domain.getSociologyInvested() > 999999999.99D) {
				result.addErrorMessage("社会资金投入资金应该在0-999999999.99万元范围");
			}
		}
		//其他
		if (domain.getOtherInvested() != null) {
			if (domain.getOtherInvested() < 0
					|| domain.getOtherInvested() > 999999999.99D) {
				result.addErrorMessage("其他资金应该在0-999999999.99万元范围");
			}
		}
		return result;
	}
}
