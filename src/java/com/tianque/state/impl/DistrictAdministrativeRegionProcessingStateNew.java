package com.tianque.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.state.IssueContext;
import com.tianque.state.IssueDealType;
import com.tianque.state.IssueStateNew;

public class DistrictAdministrativeRegionProcessingStateNew extends
		IssueStateNew {
	public void submitForward(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {
			setStateClass(issueContext,
					CityAdministrativeRegionUnConceptStateNew.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			setStateClass(issueContext,
					CityFunctionalOrgUnConceptStateNew.class.getName());
		} else {
			throw new BusinessValidationException("目标部门不正确");
		}
	}

	public void comment(IssueContext issueContext) {
		setStateClass(issueContext,
				DistrictAdministrativeRegionProcessingStateNew.class.getName());
	}

	public void complete(IssueContext issueContext) {
		setStateClass(issueContext,
				DistrictAdministrativeRegionCompleteState.class.getName());
	}

	public void sendback(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.CITY) {
			setStateClass(issueContext,
					CityAdministrativeRegionProcessingStateNew.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.CITY) {
			setStateClass(issueContext,
					CityFunctionalOrgProcessingStateNew.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.TOWN) {
			setStateClass(issueContext, TownProcessingStateNew.class.getName());
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

	public void assign(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {
			setStateClass(issueContext, TownUnConceptStateNew.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			setStateClass(issueContext,
					DistrictFunctionalOrgUnConceptStateNew.class.getName());
		}
	}

	public void tell(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.CITY) {
			setStateClass(issueContext,
					CityAdministrativeRegionUnReadState.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.CITY) {
			setStateClass(issueContext,
					CityFunctionalOrgUnReadState.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.DISTRICT) {
			setStateClass(issueContext,
					DistrictFunctionalOrgUnReadState.class.getName());
		}
	}

	@Override
	public List<Long> getConDo() {
		List list = new ArrayList();
		list.add(IssueDealType.COMMENT);
		list.add(IssueDealType.SUBMIT_FORWARD);
		list.add(IssueDealType.SUBMIT_FORWARD_ADMIN_TO_ADMIN);
		list.add(IssueDealType.SUBMIT_FORWARD_ADMIN_TO_FUNCTION);
		list.add(IssueDealType.COMPLETE);
		list.add(IssueDealType.SEND_BACK);
		list.add(IssueDealType.ASSIGN);
		list.add(IssueDealType.ASSIGN_TO_ADMIN);
		list.add(IssueDealType.ASSIGN_TO_FUNCTION);
		list.add(IssueDealType.TELL);
		list.add(IssueDealType.TELL_ASSIGN_TO_FUNCTION);
		list.add(IssueDealType.TELL_SUBMIT_FORWARD_ADMIN_TO_FUNCTION);
		list.add(IssueDealType.TELL_SUBMIT_FORWARD_ADMIN_TO_ADMIN);
		return list;
	}
}
