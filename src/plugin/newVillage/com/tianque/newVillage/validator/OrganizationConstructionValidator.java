package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.constant.ComprehensiveInfoConstant;
import com.tianque.newVillage.domain.OrganizationConstruction;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("organizationConstructionValidator")
public class OrganizationConstructionValidator implements
		DomainValidator<OrganizationConstruction> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(OrganizationConstruction domain) {
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
		if (domain.getIsPositionBuilding() != null) {
			if (domain.getIsPositionBuilding() != ComprehensiveInfoConstant.NO
					&& domain.getIsPositionBuilding() != ComprehensiveInfoConstant.YES) {
				result.addErrorMessage("是否阵地建设错误");
			}
		}
		// 验证threeSatisfaction三务公开群众满意度
		if (domain.getThreeSatisfaction() != null) {
			if (domain.getThreeSatisfaction() < 0
					|| domain.getThreeSatisfaction() > 100D) {
				result.addErrorMessage("三务公开群众满意度范围必须在0-100之间");
			}
		}
		// 验证surveySatisfaction调查满意度
		if (domain.getSurveySatisfaction() != null) {
			if (domain.getSurveySatisfaction() < 0
					|| domain.getSurveySatisfaction() > 100D) {
				result.addErrorMessage("满意度范围必须在0-100之间");
			}
		}
		
		//党员人数
		if (domain.getPartyMembers() != null) {
			if (domain.getPartyMembers() > 99999999
					|| domain.getPartyMembers() < 0) {
				result.addErrorMessage("党员人数应在范围0-99999999");
			}
		}
		
		return result;
	}
}
