package com.tianque.issue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.GridProperties;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueOperationContext;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.state.IssueStepInfo;
import com.tianque.issue.state.OrganizationInfo;
import com.tianque.issue.state.exception.IssueOperationException;
import com.tianque.issue.state.exception.UnsupportOperationException;

public class UnGiveToState extends CanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "未指派";
	}

	@Override
	public IssueStepGroup giveTo(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!isCommandCenter(currentStep.getTarget())) {
			throw new UnsupportOperationException("事件不在指挥中心，不能交办");
		} else if (isCommandCenter(context.getTargetOrg())) {
			throw new IssueOperationException("不能将事件交办给指挥中心");
		} else if (notParentOrg(context.getTargetOrg(), currentStep.getTarget())) {
			throw new IssueOperationException("不能将事件交办给上级部门");
		}
		return transferIssueToOtherOrg(issue, currentStep, getLoginedOrg(context),
				context.getTargetOrg(), context.getTellOrgs());
	}

	@Override
	public List<IssueOperate> getCando(IssueStepInfo step, OrganizationInfo orgInfo) {
		List<IssueOperate> result = new ArrayList<IssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())
				&& isCommandCenter(orgInfo.getOrganization())) {
			result.add(IssueOperate.GIVETO);
		}
		return result;
	}

	private IssueStepGroup transferIssueToOtherOrg(IssueNew issue, IssueStep currentStep,
			Organization loginedOrg, Organization target, List<Organization> tellOrgs) {
		IssueStepGroup result = new IssueStepGroup();
		IssueStep keyStep = constructIssueStepAndFillDefaultProperty(issue, loginedOrg, target);
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			keyStep.setState(UnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			keyStep.setState(DealingState.class.getName());
			keyStep.setStateCode(DEALING_CODE);
		}
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		if (tellOrgs != null) {
			for (Organization org : tellOrgs) {
				if (!org.equals(target)) {
					IssueStep tellStep = constructIssueStepAndFillDefaultProperty(issue,
							loginedOrg, org);
					tellStep.setState(UnReadState.class.getName());
					result.addIncidentalStep(constructTellStep(issue, loginedOrg, org));
				}
			}
		}
		result.setKeyStep(keyStep);
		issue.setCurrentStep(keyStep);
		issue.setCurrentOrg(keyStep.getTarget());
		issue.setStatus(DEALING);
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		return result;
	}

	private IssueStep constructTellStep(IssueNew issue, Organization loginedOrg,
			Organization tellorg) {
		IssueStep tellStep = constructIssueStepAndFillDefaultProperty(issue, loginedOrg, tellorg);
		tellStep.setState(UnReadState.class.getName());
		tellStep.setStateCode(UNREAD_CODE);
		return tellStep;
	}

}
