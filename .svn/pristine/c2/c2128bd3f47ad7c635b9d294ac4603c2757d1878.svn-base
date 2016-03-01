package com.tianque.issue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.GridProperties;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueOperationContext;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.state.IssueStepInfo;
import com.tianque.issue.state.OrganizationInfo;
import com.tianque.issue.state.exception.IssueOperationException;
import com.tianque.issue.state.exception.UnsupportOperationException;

public class NewIssueState extends CanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "新建";
	}

	@Override
	public IssueStep reportTo(IssueNew issue, IssueStep currentStep, IssueOperationContext context)
			throws UnsupportOperationException, IssueOperationException {
		if (isCommandCenter(currentStep.getTarget())) {
			throw new IssueOperationException("该事件已经处于指挥中心，无须上报");
		} else if (!isCommandCenter(context.getTargetOrg())) {
			throw new IssueOperationException("不能将事件上报给除指挥中心以外的部门");
		}
		IssueStep result = constructIssueStepAndFillDefaultProperty(issue,
				context.getCurrentLoginedOrg(), context.getTargetOrg());
		stepCompleteCurrentStepAndFillDealTime(currentStep, result);
		result.setState(UnGiveToState.class.getName());
		result.setStateCode(UNGIVETO_CODE);
		issue.setStatus(DEALING);
		issue.setCurrentStep(result);
		issue.setCurrentOrg(result.getTarget());
		// currentStep.setSuperviseLevel(NO_SUPERVISE);
		return result;
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
		IssueStepGroup result = new IssueStepGroup();
		IssueStep step = constructIssueStepAndFillDefaultProperty(issue,
				context.getCurrentLoginedOrg(), context.getTargetOrg());
		if ("true".equals(GridProperties.IS_NEED_UNCONCEPTEDSTATE)) {
			step.setState(UnConceptedState.class.getName());
			step.setStateCode(UNCONCEPTED_CODE);
		} else {
			step.setState(DealingState.class.getName());
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
	public List<IssueOperate> getCando(IssueStepInfo step, OrganizationInfo orgInfo) {
		List<IssueOperate> result = new ArrayList<IssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			if (isCommandCenter(orgInfo.getOrganization())) {
				result.add(IssueOperate.GIVETO);
			} else {
				result.add(IssueOperate.REPORT_TO);
			}
		}
		return result;
	}
}
