package com.tianque.fourTeams.fourTeamsIssue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.GridProperties;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsOrganizationInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsIssueOperationException;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsUnsupportOperationException;

public class FourTeamsNewIssueState extends FourTeamsCanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "新建";
	}

	@Override
	public FourTeamsIssueStep reportTo(FourTeamsIssueNew issue,
			FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context)
			throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (isCommandCenter(currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("该事件已经处于指挥中心，无须上报");
		} else if (!isCommandCenter(context.getTargetOrg())) {
			throw new FourTeamsIssueOperationException("不能将事件上报给除指挥中心以外的部门");
		}
		FourTeamsIssueStep result = constructIssueStepAndFillDefaultProperty(
				issue, context.getCurrentLoginedOrg(), context.getTargetOrg());
		stepCompleteCurrentStepAndFillDealTime(currentStep, result);
		result.setState(FourTeamsUnGiveToState.class.getName());
		result.setStateCode(UNGIVETO_CODE);
		issue.setStatus(DEALING);
		issue.setCurrentStep(result);
		issue.setCurrentOrg(result.getTarget());
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		return result;
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
		FourTeamsIssueStepGroup result = new FourTeamsIssueStepGroup();
		FourTeamsIssueStep step = constructIssueStepAndFillDefaultProperty(
				issue, context.getCurrentLoginedOrg(), context.getTargetOrg());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			step.setState(FourTeamsUnConceptedState.class.getName());
			step.setStateCode(UNCONCEPTED_CODE);
		} else {
			step.setState(FourTeamsDealingState.class.getName());
			step.setStateCode(DEALING_CODE);
		}
		stepCompleteCurrentStepAndFillDealTime(currentStep, step);
		result.setKeyStep(step);
		issue.setCurrentStep(step);
		issue.setCurrentOrg(step.getTarget());
		issue.setStatus(DEALING);
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		return result;
	}

	@Override
	public List<FourTeamsIssueOperate> getCando(FourTeamsIssueStepInfo step,
			FourTeamsOrganizationInfo orgInfo) {
		List<FourTeamsIssueOperate> result = new ArrayList<FourTeamsIssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			if (isCommandCenter(orgInfo.getOrganization())) {
				result.add(FourTeamsIssueOperate.GIVETO);
			} else {
				result.add(FourTeamsIssueOperate.REPORT_TO);
			}
		}
		return result;
	}
}
