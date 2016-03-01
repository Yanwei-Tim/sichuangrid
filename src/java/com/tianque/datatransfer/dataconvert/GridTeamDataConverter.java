package com.tianque.datatransfer.dataconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.primaryOrg.domain.GridTeam;
import com.tianque.baseInfo.primaryOrg.service.GridTeamService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("gridTeamDataConverter")
@Scope("prototype")
public class GridTeamDataConverter extends AbstractDataConverter<GridTeam> {

	@Qualifier("gridTeamValidator")
	@Autowired
	DomainValidator<GridTeam> gridTeamValidator;
	@Autowired
	GridTeamService gridTeamService;
	@Autowired
	private OrganizationDubboService organziationDubboService;
	

	@Override
	public ValidateResult validate(GridTeam domain, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		ValidateResult result = gridTeamValidator.validate(domain);
		return result;
	}

	@Override
	public GridTeam persistenceDomain(GridTeam domain) {
		return gridTeamService.addGridTeam(domain);
	}
	
	@Override
	public GridTeam convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		GridTeam result = new GridTeam();
		ExcelImportHelper.procImportDate(
				SpecialGroupsContext.getGridTeamImportArray(),
				cellValues, getUploadOrganization(), result, vr);
		return result;
	}


	@Override
	public GridTeam updateDomain(GridTeam domain) {
		return null;
	}

}
