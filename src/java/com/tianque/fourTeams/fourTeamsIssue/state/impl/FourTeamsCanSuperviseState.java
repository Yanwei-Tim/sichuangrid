package com.tianque.fourTeams.fourTeamsIssue.state.impl;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsIssueOperationException;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsUnsupportOperationException;

public abstract class FourTeamsCanSuperviseState extends AbstractFourTeamsIssueState {

	@Override
	public void instruct(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!notParentOrg(getLoginedOrg(context), currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能批示正在上级部门处理的事件");
		}
	}

	@Override
	public void supervise(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!notParentOrg(getLoginedOrg(context), currentStep.getTarget())) {
			throw new FourTeamsIssueOperationException("不能督办正在上级部门处理的事件");
		}
		int superviseLevel = (Integer) context
				.getParameter(FourTeamsIssueOperationContext.SUPERVISE_LEVEL_KEY);
		if (superviseLevel > currentStep.getSuperviseLevel()) {
			currentStep.setSuperviseLevel(superviseLevel);
			currentStep.setIsExtended(ISSUE_EXTENDED);
		} else {
			throw new FourTeamsIssueOperationException("正处于更高级别的督办中");
		}

	}

	@Override
	public void cancelSupervise(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (currentStep.getSuperviseLevel() < NORMAL_SUPERVISE) {
			throw new FourTeamsIssueOperationException("本事件并未受到督办");
		} else if (!notParentOrg(getLoginedOrg(context), currentStep
				.getTarget())) {
			throw new FourTeamsIssueOperationException("不能取消上级部门的督办事件");
		}
		currentStep.setSuperviseLevel(NO_SUPERVISE);
		currentStep.setIsExtended(ISSUE_NOT_EXTENDED);
	}

}
