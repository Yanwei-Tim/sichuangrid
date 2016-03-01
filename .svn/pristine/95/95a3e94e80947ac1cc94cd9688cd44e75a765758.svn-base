package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Enterprise;
import com.tianque.domain.FireSafetyEnterprise;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.FireSafetyEnterpriseService;

@Component("fireDataConverter")
public class FireDataConverter extends AbstractDataConverter<Enterprise> {
	@Autowired
	private FireSafetyEnterpriseService fireSafetyEnterpriseService;
	@Autowired
	private ValidateHelper validateHelper;
	@Qualifier("enterPriseValidator")
	@Autowired
	private DomainValidator<Enterprise> enterPriseValidator;

	@Override
	public FireSafetyEnterprise convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		FireSafetyEnterprise result = new FireSafetyEnterprise();
		ExcelImportHelper.procImportDate(SpecialGroupsContext.getFireSafetyImportArray(),
				cellValues, getUploadOrganization(), result, vr);

		result.setKeyType("fireSafetyKey");
		return result;
	}

	@Override
	public FireSafetyEnterprise convertToDomain(String[] cellValues, ValidateResult vr,
			String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		FireSafetyEnterprise result = new FireSafetyEnterprise();
		ExcelImportHelper.newProcImportDate(beanDatas, cellValues, getUploadOrganization(), result,
				vr);
		result.setKeyType("fireSafetyKey");
		return result;
	}

	@Override
	public FireSafetyEnterprise persistenceDomain(Enterprise domain) {
		domain.setKeyType("fireSafetyKey");
		FireSafetyEnterprise fireSafetyEnterprise = getFireSafetyEnterprise(domain);
		fireSafetyEnterprise.setIsEmphasis(false);// 现在关注
		return fireSafetyEnterpriseService.addFireSafetyEnterprise(fireSafetyEnterprise);
	}

	@Override
	public ValidateResult validate(Enterprise enterprise, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return enterPriseValidator.validate(enterprise);
	}

	@Override
	public boolean isRepeatData(Enterprise domain) {
		domain.setKeyType("fireSafetyKey");
		FireSafetyEnterprise fireSafetyEnterprise = getFireSafetyEnterprise(domain);
		return fireSafetyEnterpriseService.getFireSafetyEnterpriseByNameAndOrgIdAndKeyType(
				fireSafetyEnterprise.getName(), fireSafetyEnterprise.getOrganization().getId(),
				fireSafetyEnterprise.getKeyType()) != null;
	}

	@Override
	public Enterprise updateDomain(Enterprise domain) {
		domain.setKeyType("fireSafetyKey");
		FireSafetyEnterprise fireSafetyEnterprise = getFireSafetyEnterprise(domain);
		fireSafetyEnterprise.setIsEmphasis(false);// 现在关注
		return fireSafetyEnterpriseService.updateFireSafetyEnterpriseByName(
				fireSafetyEnterprise.getName(), fireSafetyEnterprise.getOrganization().getId(),
				fireSafetyEnterprise);
	}

	private FireSafetyEnterprise getFireSafetyEnterprise(Enterprise domain) {
		FireSafetyEnterprise fireSafetyEnterprise = new FireSafetyEnterprise();
		fireSafetyEnterprise.setAddress(domain.getAddress());
		fireSafetyEnterprise.setArea(domain.getArea());
		fireSafetyEnterprise.setAreaString(domain.getAreaString());
		fireSafetyEnterprise.setBusinessLicense(domain.getBusinessLicense());
		fireSafetyEnterprise.setComprehensiveManageMembers(domain.getComprehensiveManageMembers());
		fireSafetyEnterprise.setCreateDate(domain.getCreateDate());
		fireSafetyEnterprise.setCreateUser(domain.getCreateUser());
		fireSafetyEnterprise.setEmployeeAmount(domain.getEmployeeAmount());
		fireSafetyEnterprise.setEnterpriseTelephone(domain.getEnterpriseTelephone());
		fireSafetyEnterprise.setFax(domain.getFax());
		fireSafetyEnterprise.setFullPinyin(domain.getFullPinyin());
		fireSafetyEnterprise.setHiddenCases(domain.getHiddenCases());
		fireSafetyEnterprise.setHiddenRectification(domain.getHiddenRectification());
		fireSafetyEnterprise.setId(domain.getId());
		fireSafetyEnterprise.setIndustry(domain.getIndustry());
		fireSafetyEnterprise.setIsEmphasis(domain.getIsEmphasis());
		fireSafetyEnterprise.setKeyType(domain.getKeyType());
		fireSafetyEnterprise.setLegalPerson(domain.getLegalPerson());
		fireSafetyEnterprise.setMobileNumber(domain.getMobileNumber());
		fireSafetyEnterprise.setName(domain.getName());
		fireSafetyEnterprise.setOrganization(domain.getOrganization());
		fireSafetyEnterprise.setOrgInternalCode(domain.getOrgInternalCode());
		fireSafetyEnterprise.setPartyMemberAmount(domain.getPartyMemberAmount());
		fireSafetyEnterprise.setRegisteredCapital(domain.getRegisteredCapital());
		fireSafetyEnterprise.setRemark(domain.getRemark());
		fireSafetyEnterprise.setSimplePinyin(domain.getSimplePinyin());
		fireSafetyEnterprise.setTelephone(domain.getTelephone());
		fireSafetyEnterprise.setType(domain.getType());
		fireSafetyEnterprise.setUpdateDate(domain.getUpdateDate());
		fireSafetyEnterprise.setUpdateUser(domain.getUpdateUser());
		fireSafetyEnterprise.setRiskEnterprise(domain.isRiskEnterprise());
		fireSafetyEnterprise.setAttentionExtent(domain.getAttentionExtent());
		return fireSafetyEnterprise;
	}
}
