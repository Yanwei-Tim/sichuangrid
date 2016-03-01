package com.tianque.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.state.IssueContext;
import com.tianque.state.IssueDealType;
import com.tianque.state.IssueStateNew;

public class TownProcessingStateNew extends IssueStateNew {
	public void submitForward(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {
			setStateClass(issueContext,
					DistrictAdministrativeRegionUnConceptStateNew.class
							.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			setStateClass(issueContext,
					DistrictFunctionalOrgUnConceptStateNew.class.getName());
		} else {
			throw new BusinessValidationException("目标部门不正确");
		}
	}

	public void comment(IssueContext issueContext) {
		setStateClass(issueContext, TownProcessingStateNew.class.getName());
	}

	public void complete(IssueContext issueContext) {
		setStateClass(issueContext, TownCompleteStateNew.class.getName());
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
						.getInternalId() == OrganizationLevel.DISTRICT) {
			setStateClass(issueContext,
					DistrictFunctionalOrgProcessingStateNew.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.VILLAGE) {
			setStateClass(issueContext,
					ViliageProcessingStateNew.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
				&& issueContext.getIssueLogNew().getTargeOrg().getOrgLevel()
						.getInternalId() == OrganizationLevel.GRID) {
			setStateClass(issueContext, GridProcessingStateNew.class.getName());
		} else {
			throw new BusinessValidationException("目标部门不正确");
		}
	}

	public void assign(IssueContext issueContext) {
		setStateClass(issueContext, ViliageUnConceptStateNew.class.getName());
	}

	public void tell(IssueContext issueContext) {
		if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {
			setStateClass(issueContext,
					DistrictAdministrativeRegionUnReadState.class.getName());
		} else if (issueContext.getIssueLogNew().getTargeOrg().getOrgType()
				.getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			setStateClass(issueContext,
					DistrictFunctionalOrgUnReadState.class.getName());
		} else {
			throw new BusinessValidationException("目标部门不正确");
		}
	}

	@Override
	public List<Long> getConDo() {
		List list = new ArrayList();
		list.add(IssueDealType.SUBMIT_FORWARD);
		list.add(IssueDealType.SUBMIT_FORWARD_ADMIN_TO_ADMIN);
		list.add(IssueDealType.SUBMIT_FORWARD_ADMIN_TO_FUNCTION);
		list.add(IssueDealType.COMMENT);
		list.add(IssueDealType.COMPLETE);
		list.add(IssueDealType.SEND_BACK);
		list.add(IssueDealType.ASSIGN);
		list.add(IssueDealType.ASSIGN_TO_ADMIN);
		list.add(IssueDealType.TELL);
		list.add(IssueDealType.TELL_SUBMIT_FORWARD_ADMIN_TO_FUNCTION);
		list.add(IssueDealType.TELL_SUBMIT_FORWARD_ADMIN_TO_ADMIN);
		return list;
	}
}
