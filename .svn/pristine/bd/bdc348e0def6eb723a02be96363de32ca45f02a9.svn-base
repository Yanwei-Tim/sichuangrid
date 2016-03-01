package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;

@Component("tenHouseHoldsGroupDataConverter")
@Scope("prototype")
public class TenHouseHoldsGroupDataConverter extends
AbstractDataConverter<FamilyTeam>{

	@Qualifier("tenHouseHoldsGroupValidatorImpl")
	@Autowired
	DomainValidator<FamilyTeam> tenHouseHoldsGroupValidator;
	
	@Autowired
	FamilyTeamService familyTeamService;
	/***
	 * 验证
	 */
	@Override
	public ValidateResult validate(FamilyTeam domain, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		ValidateResult result = tenHouseHoldsGroupValidator.validate(domain);
		return result;
	}

	/***
	 * 新增
	 */
	@Override
	public FamilyTeam persistenceDomain(FamilyTeam domain) {
		return familyTeamService.addFamilyTeam(domain);
	}

	@Override
	public FamilyTeam convertToDomain(String[] cellValues, ValidateResult result) {
		return null;
	}
	
	@Override
	public FamilyTeam updateDomain(FamilyTeam domain) {
		return null;
	}


}
