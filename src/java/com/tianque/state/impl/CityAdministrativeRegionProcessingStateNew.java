package com.tianque.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.state.IssueContext;
import com.tianque.state.IssueDealType;
import com.tianque.state.IssueStateNew;

public class CityAdministrativeRegionProcessingStateNew extends IssueStateNew {

	public void comment(IssueContext issueContext) {
		setStateClass(issueContext,
				CityAdministrativeRegionProcessingStateNew.class.getName());
	}

	public void sendback(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.DISTRICT) {
			setStateClass(issueContext,
					DistrictAdministrativeRegionProcessingStateNew.class
							.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.CITY) {
			setStateClass(issueContext,
					CityFunctionalOrgProcessingStateNew.class.getName());
		} else {
			throw new BusinessValidationException("目标部门不正确");
		}
	}

	public void assign(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.DISTRICT) {
			setStateClass(issueContext,
					DistrictAdministrativeRegionUnConceptStateNew.class
							.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.CITY) {
			setStateClass(issueContext,
					CityFunctionalOrgUnConceptStateNew.class.getName());
		} else {
			throw new BusinessValidationException("目标部门不正确");
		}
	}

	public void complete(IssueContext issueContext) {
		setStateClass(issueContext,
				CityAdministrativeRegionCompleteState.class.getName());
	}

	public void tell(IssueContext issueContext) {
		setStateClass(issueContext,
				CityFunctionalOrgUnReadState.class.getName());
	}

	@Override
	public List<Long> getConDo() {
		List list = new ArrayList();
		list.add(IssueDealType.COMMENT);
		list.add(IssueDealType.SEND_BACK);
		list.add(IssueDealType.COMPLETE);
		list.add(IssueDealType.ASSIGN);
		list.add(IssueDealType.ASSIGN_TO_ADMIN);
		list.add(IssueDealType.ASSIGN_TO_FUNCTION);
		list.add(IssueDealType.TELL);
		list.add(IssueDealType.TELL_ASSIGN_TO_FUNCTION);
		return list;
	}
}
