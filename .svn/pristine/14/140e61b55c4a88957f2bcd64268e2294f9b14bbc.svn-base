package com.tianque.newVillage.validator;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.NewVillageBasic;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("newVillageBasicValidator")
public class NewVillageBasicValidator implements
		DomainValidator<NewVillageBasic> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(NewVillageBasic domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();
		// 验证NewVillageBasic中orgid是否是乡镇层级
		if (domain.getOrganization() == null
				|| domain.getOrganization().getId() == null) {
			throw new BusinessValidationException("请选择正确的层级");
		}
		if (organizationDubboService
				.getFullOrgById(domain.getOrganization().getId()).getOrgLevel()
				.getInternalId() != OrganizationLevel.VILLAGE) {
			throw new BusinessValidationException("层级错误,操作失败");
		}
		//验证年份和季度
		if (domain.getYear() == null) {
			throw new BusinessValidationException("年份信息缺失");
		}
		if (domain.getYear() > Calendar.getInstance().get(Calendar.YEAR) + 5
				|| domain.getYear() < Calendar.getInstance().get(Calendar.YEAR) - 5) {
			throw new BusinessValidationException("年份错误");
		}
		if (domain.getQuarter() == null && domain.getIsPlaning() == 0) {
			throw new BusinessValidationException("季度信息缺失");
		}
		if (domain.getQuarter() != null
				&& (domain.getQuarter() < 1 || domain.getQuarter() > 4)) {
			throw new BusinessValidationException("季度信息错误");
		}
		return result;
	}
}
