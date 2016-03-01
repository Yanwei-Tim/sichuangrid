package com.tianque.fourTeams.fourTeamsIssue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.GridProperties;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsOrganizationInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsIssueOperationException;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsUnsupportOperationException;

public class FourTeamsUnGiveToState extends FourTeamsCanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "未指派";
	}

	@Override
	public FourTeamsIssueStepGroup giveTo(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!isCommandCenter(currentStep.getTarget())) {
			throw new FourTeamsUnsupportOperationException("事件不在指挥中心，不能交办");
		} else if (isCommandCenter(context.getTargetOrg())) {
			throw new FourTeamsIssueOperationException("不能将事件交办给指挥中心");
		} else if (notParentOrg(context.getTargetOrg(), currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能将事件交办给上级部门");
		}
		return transferIssueToOtherOrg(issue, currentStep,
				getLoginedOrg(context), context.getTargetOrg(), context
						.getTellOrgs());
	}

	@Override
	public List<FourTeamsIssueOperate> getCando(FourTeamsIssueStepInfo step,
			FourTeamsOrganizationInfo orgInfo) {
		List<FourTeamsIssueOperate> result = new ArrayList<FourTeamsIssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())
				&& isCommandCenter(orgInfo.getOrganization())) {
			result.add(FourTeamsIssueOperate.GIVETO);
		}
		return result;
	}

	private FourTeamsIssueStepGroup transferIssueToOtherOrg(
			FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			Organization loginedOrg, Organization target,
			List<Organization> tellOrgs) {
		FourTeamsIssueStepGroup result = new FourTeamsIssueStepGroup();
		FourTeamsIssueStep keyStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, target);
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			keyStep.setState(FourTeamsUnConceptedState.class.getName());
			keyStep.setStateCode(UNCONCEPTED_CODE);
		} else {
			keyStep.setState(FourTeamsDealingState.class.getName());
			keyStep.setStateCode(DEALING_CODE);
		}
		stepCompleteCurrentStepAndFillDealTime(currentStep, keyStep);
		if (tellOrgs != null) {
			for (Organization org : tellOrgs) {
				if (!org.equals(target)) {
					FourTeamsIssueStep tellStep = constructIssueStepAndFillDefaultProperty(
							issue, loginedOrg, org);
					tellStep.setState(FourTeamsUnReadState.class.getName());
					result.addIncidentalStep(constructTellStep(issue,
							loginedOrg, org));
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

	private FourTeamsIssueStep constructTellStep(FourTeamsIssueNew issue,
			Organization loginedOrg, Organization tellorg) {
		FourTeamsIssueStep tellStep = constructIssueStepAndFillDefaultProperty(
				issue, loginedOrg, tellorg);
		tellStep.setState(FourTeamsUnReadState.class.getName());
		tellStep.setStateCode(UNREAD_CODE);
		return tellStep;
	}

}
