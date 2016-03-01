package com.tianque.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.state.IssueContext;
import com.tianque.state.IssueDealType;
import com.tianque.state.IssueStateNew;

public class CityFunctionalOrgProcessingStateNew extends IssueStateNew {
	public void comment(IssueContext issueContext) {
		setStateClass(issueContext,
				CityFunctionalOrgProcessingStateNew.class.getName());
	}

	public void complete(IssueContext issueContext) {
		;
		setStateClass(issueContext,
				CityFunctionalOrgCompleteStateNew.class.getName());
	}

	public void sendback(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.CITY) {
			setStateClass(issueContext,
					CityAdministrativeRegionProcessingStateNew.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.DISTRICT) {
			setStateClass(issueContext,
					DistrictAdministrativeRegionProcessingStateNew.class
							.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.DISTRICT) {
			setStateClass(issueContext,
					DistrictFunctionalOrgProcessingStateNew.class.getName());
		} else {
			throw new BusinessValidationException("目标部门不正确");
		}
	}

	public void submitForward(IssueContext issueContext) {
		setStateClass(issueContext,
				CityAdministrativeRegionUnConceptStateNew.class.getName());
	}

	public void assign(IssueContext issueContext) {
		setStateClass(issueContext,
				DistrictFunctionalOrgUnConceptStateNew.class.getName());
	}

	public void tell(IssueContext issueContext) {
		setStateClass(issueContext,
				DistrictFunctionalOrgUnReadState.class.getName());
	}

	@Override
	public List<Long> getConDo() {
		List list = new ArrayList();
		list.add(IssueDealType.COMMENT);
		list.add(IssueDealType.COMPLETE);
		list.add(IssueDealType.SEND_BACK);
		list.add(IssueDealType.SUBMIT_FORWARD);
		list.add(IssueDealType.SUBMIT_FORWARD_FUNCTION_TO_ADMIN);
		list.add(IssueDealType.ASSIGN);
		list.add(IssueDealType.ASSIGN_TO_FUNCTION);
		return list;
	}
}
