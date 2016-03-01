package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.BasicYearInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("basicYearInfoValidator")
public class BasicYearInfoValidator implements DomainValidator<BasicYearInfo> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(BasicYearInfo domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();
		// 验证BasicYearInfo中orgid是否是乡镇层级
		if (domain.getOrganization() == null
				|| domain.getOrganization().getId() == null) {
			throw new BusinessValidationException("请选择层级");
		}
		if (organizationDubboService
				.getFullOrgById(domain.getOrganization().getId()).getOrgLevel()
				.getInternalId() != OrganizationLevel.VILLAGE) {
			throw new BusinessValidationException("层级错误,操作失败");
		}
		//验证BasicYearInfo中totalArea总面积/幅员面积
		if (domain.getTotalArea() != null) {
			if (domain.getTotalArea() < 0
					|| domain.getTotalArea() > 999999999.99D) {
				result.addErrorMessage("总面积/幅员面积应在0-999999999.99之间");
			}
		}
		//验证耕地面积
		if (domain.getCultivatedLandArea() != null) {
			if (domain.getCultivatedLandArea() < 0
					|| domain.getCultivatedLandArea() > 999999999.99D) {
				result.addErrorMessage("耕地面积应在0-999999999.99之间");
			}
		}
		//验证林地面积
		if (domain.getWoodlandArea() != null) {
			if (domain.getWoodlandArea() < 0
					|| domain.getWoodlandArea() > 999999999.99D) {
				result.addErrorMessage("林地面积应在0-999999999.99之间");
			}
		}
		//验证荒地面积
		if (domain.getWastelandArea() != null) {
			if (domain.getWastelandArea() < 0
					|| domain.getWastelandArea() > 999999999.99D) {
				result.addErrorMessage("荒地面积应在0-999999999.99之间");
			}
		}
		//验证土地流转面积
		if (domain.getLandTransfer() != null) {
			if (domain.getLandTransfer() < 0
					|| domain.getLandTransfer() > 999999999.99D) {
				result.addErrorMessage("土地流转面积应在0-999999999.99之间");
			}
		}
		//验证总户数
		if (domain.getTotalHouseholdsNum() != null) {
			if (domain.getTotalHouseholdsNum() > 99999999
					|| domain.getTotalHouseholdsNum() < 0) {
				result.addErrorMessage("总户数应该为0-99999999");
			}
		}
		//验证总人数
		if (domain.getTotalPeopleNum() != null) {
			if (domain.getTotalPeopleNum() > 99999999
					|| domain.getTotalPeopleNum() < 0) {
				result.addErrorMessage("总人数应该为0-99999999");
			}
		}
		//验证外出务工人数
		if (domain.getOutWorkNum() != null) {
			if (domain.getOutWorkNum() > 99999999
					|| domain.getOutWorkNum() < 0) {
				result.addErrorMessage("外出务工人数应该为0-99999999");
			}
			if (domain.getTotalPeopleNum() != null
					&& domain.getTotalPeopleNum() < domain.getOutWorkNum()) {
				result.addErrorMessage("总人数不应小于外出务工人数");
			}
		}
		//验证村集体资产
		if (domain.getCollectiveFunds() != null) {
			if (domain.getCollectiveFunds() < 0
					|| domain.getCollectiveFunds() > 999999999.99D) {
				result.addErrorMessage("集体资金应在0-999999999.99万元之间");
			}
		}
		//验证村集体资产折合
		if (domain.getCollectiveAssets() != null) {
			if (domain.getCollectiveAssets() < 0
					|| domain.getCollectiveAssets() > 999999999.99D) {
				result.addErrorMessage("集体资产折资应在0-999999999.99万元之间");
			}
		}
		//验证资源说明
		if(!StringUtil.isStringAvaliable(domain.getAssetsDescribe())){
			if(validateHelper.illegalStringLength(0, 300,
					domain.getAssetsDescribe())){
				result.addErrorMessage("村集体资源描述应该在0-300个字符之间");
			}
		}
//		//农村经济总收入
//		if (domain.getTotalEconomicIncome() != null) {
//			if (domain.getTotalEconomicIncome() < 0
//					|| domain.getTotalEconomicIncome() > 999999999.99D) {
//				result.addErrorMessage("农村经济总收入应在0-999999999.99万元之间");
//			}
//		}
//		//村集体经济总收入
//		if (domain.getTotalCollectiveEconomicIncome() != null) {
//			if (domain.getTotalCollectiveEconomicIncome() < 0
//					|| domain.getTotalCollectiveEconomicIncome() > 999999999.99D) {
//				result.addErrorMessage("村集体经济总收入应在0-999999999.99万元之间");
//			}
//			//			if (domain.getTotalEconomicIncome() != null
//			//					&& domain.getTotalEconomicIncome() < domain
//			//							.getTotalCollectiveEconomicIncome()) {
//			//				result.addErrorMessage("农村经济总收入不应小于村集体经济总收入");
//			//			}
//		}
		return result;
	}
}
