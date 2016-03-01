package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamMembers;
import com.tianque.fourTeams.fourTeamsManage.service.FourTeamsService;

@Component("fourTeamMembersDataConverter")
@Scope("prototype")
public class FourTeamMembersDataConverter extends
		AbstractDataConverter<FourTeamMembers> {

	@Qualifier("fourTeamMemberValidatorImpl")
	@Autowired
	DomainValidator<FourTeamMembers> fourTeamMemberValidator;
	@Autowired
	FourTeamsService fourTeamsService;

	@Override
	public ValidateResult validate(FourTeamMembers domain, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		if (ThreadVariable.getModule().trim() != null
				&& !"".equals(ThreadVariable.getModule().trim())) {
			domain.setTeamId(Long.parseLong(ThreadVariable.getModule().trim()));
		}
		ValidateResult result = fourTeamMemberValidator.validate(domain);
		return result;
	}

	@Override
	public FourTeamMembers persistenceDomain(FourTeamMembers domain) {

		return fourTeamsService.addTeamMember(domain);
	}

	@Override
	public FourTeamMembers updateDomain(FourTeamMembers domain) {
		return null;
	}

	@Override
	public FourTeamMembers convertToDomain(String[] cellValues,
			ValidateResult result) {
		return null;
	}

}
