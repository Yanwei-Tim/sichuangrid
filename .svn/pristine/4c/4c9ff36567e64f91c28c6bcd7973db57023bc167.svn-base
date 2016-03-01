package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.CommonServiceInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("commonServiceInfoValidator")
public class CommonServiceInfoValidator implements
		DomainValidator<CommonServiceInfo> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(CommonServiceInfo domain) {
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
		// 验证村小个数
		if (domain.getVillageSchool() != null) {
			if (domain.getVillageSchool() < 0
					|| domain.getVillageSchool() > 99999999)
				result.addErrorMessage("村小个数数值应在0-99999999");
		}
		// 验证幼儿园个数
		if (domain.getKindergarten() != null) {
			if (domain.getKindergarten() < 0
					|| domain.getKindergarten() > 99999999)
				result.addErrorMessage("幼儿园个数数值应在0-99999999");
		}
		// 验证中学
		if (domain.getHighSchool() != null) {
			if (domain.getHighSchool() < 0
					|| domain.getHighSchool() > 99999999)
				result.addErrorMessage("中学数值应在0-99999999所");
		}
		// 验证卫生所
		if (domain.getClinic() != null) {
			if (domain.getClinic() < 0 || domain.getClinic() > 99999999)
				result.addErrorMessage("卫生所数值应在0-99999999所");
		}
		// 新农合参保人数
		if (domain.getInsuredNumber() != null) {
			if (domain.getInsuredNumber() < 0 || domain.getInsuredNumber() > 99999999)
				result.addErrorMessage("新农合参保人数值应在0-99999999");
		}
		// 验证公共服务中心
		if (domain.getSocialWorkCenter() != null) {
			if (domain.getSocialWorkCenter() < 0
					|| domain.getSocialWorkCenter() > 99999999)
				result.addErrorMessage("公共服务中心个数应在0-99999999");
		}
		// 验证图书馆
		if (domain.getLibrary() != null) {
			if (domain.getLibrary() < 0 || domain.getLibrary() > 99999999)
				result.addErrorMessage("图书馆个数应在0-99999999");
		}
		//验证健身广场(平方米)
		if (domain.getFitnessSquare() != null) {
			if (domain.getFitnessSquare() < 0
					|| domain.getFitnessSquare() > 999999999.99D)
				result.addErrorMessage("健身广场平方米应在0-999999999.99内");
		}
		//验证健身器材(套)
		if (domain.getFitnessEquipment() != null) {
			if (domain.getFitnessEquipment() < 0
					|| domain.getFitnessEquipment() > 99999999)
				result.addErrorMessage("健身器材套数应在0-99999999");
		}
		//验证文化活动室
		if (domain.getEntertainmentRoom() != null) {
			if (domain.getEntertainmentRoom() < 0
					|| domain.getEntertainmentRoom() > 99999999)
				result.addErrorMessage("文化活动室数应在0-99999999");
		}
		//验证农家超市
		if (domain.getFarmerSupermarket() != null) {
			if (domain.getFarmerSupermarket() < 0
					|| domain.getFarmerSupermarket() > 99999999)
				result.addErrorMessage("农家超市数应在0-99999999");
		}
		
		//九年义务教育适龄人数
		if (domain.getCompulsoryEducationCount() != null) {
			if (domain.getCompulsoryEducationCount() < 0
					|| domain.getCompulsoryEducationCount() > 99999999)
				result.addErrorMessage("九年义务教育适龄人数应在0-99999999");
		}
		//九年义务教育适龄已入学人数
		if (domain.getInCompulsoryEducationCount() != null) {
			if (domain.getInCompulsoryEducationCount() < 0
					|| domain.getInCompulsoryEducationCount() > 99999999)
				result.addErrorMessage("九年义务教育适龄已入学人数应在0-99999999");
		}
		//服务中心面积
		if (domain.getSocialWorkCenterArea() != null) {
			if (domain.getSocialWorkCenterArea() < 0
					|| domain.getSocialWorkCenterArea() > 999999999.99D)
				result.addErrorMessage("服务中心面积应在0-999999999.99内");
		}
		//组织开展文体活动
		if (domain.getRecreationalActivities() != null) {
			if (domain.getRecreationalActivities() < 0
					|| domain.getRecreationalActivities() > 99999999)
				result.addErrorMessage("组织开展文体活动次数应在0-99999999");
		}
		//参加活动人数
		if (domain.getRecreationalActivitiesPeople() != null) {
			if (domain.getRecreationalActivitiesPeople() < 0
					|| domain.getRecreationalActivitiesPeople() > 99999999)
				result.addErrorMessage("参加活动人数应在0-99999999");
		}
		return result;
	}
}
