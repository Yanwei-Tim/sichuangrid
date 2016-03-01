package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.primaryOrg.domain.RedCuffTeam;
import com.tianque.baseInfo.primaryOrg.service.RedCuffTeamService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("redCuffTeamDataConverter")
@Scope("prototype")
public class RedCuffTeamDataConverter extends AbstractDataConverter<RedCuffTeam> {

	@Qualifier("redCuffTeamValidator")
	@Autowired
	DomainValidator<RedCuffTeam> redCuffTeamValidator;
	@Autowired
	RedCuffTeamService redCuffTeamService;
	@Autowired
	private OrganizationDubboService organziationDubboService;
	

	@Override
	public ValidateResult validate(RedCuffTeam domain, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		if (ThreadVariable.getModule().trim() != null
				&& !"".equals(ThreadVariable.getModule().trim())) {
			Organization organization = organziationDubboService.getSimpleOrgById(Long.parseLong(ThreadVariable.getModule().trim()));
			domain.setOrganization(organization);
		}
		ValidateResult result = redCuffTeamValidator.validate(domain);
		return result;
	}

	@Override
	public RedCuffTeam persistenceDomain(RedCuffTeam domain) {

		return redCuffTeamService.addRedCuffTeam(domain);
	}
	
	@Override
	public RedCuffTeam convertToDomain(String[] cellValues,
			ValidateResult result) {
		return null;
	}


	@Override
	public RedCuffTeam updateDomain(RedCuffTeam domain) {
		return null;
	}

}
