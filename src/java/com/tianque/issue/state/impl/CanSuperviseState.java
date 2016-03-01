package com.tianque.issue.state.impl;

import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperationContext;
import com.tianque.issue.state.exception.IssueOperationException;
import com.tianque.issue.state.exception.UnsupportOperationException;

public abstract class CanSuperviseState extends AbstractIssueState {

	@Override
	public void instruct(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!notParentOrg(getLoginedOrg(context), currentStep.getTarget())) {
			throw new IssueOperationException("不能批示正在上级部门处理的事件");
		}
	}

	@Override
	public void supervise(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (!notParentOrg(getLoginedOrg(context), currentStep.getTarget())) {
			throw new IssueOperationException("不能督办正在上级部门处理的事件");
		}
		int superviseLevel = (Integer) context
				.getParameter(IssueOperationContext.SUPERVISE_LEVEL_KEY);
		if (superviseLevel > currentStep.getSuperviseLevel()) {
			currentStep.setSuperviseLevel(superviseLevel);
			currentStep.setIsExtended(ISSUE_EXTENDED);
		} else {
			throw new IssueOperationException("正处于更高级别的督办中");
		}

	}

	@Override
	public void cancelSupervise(IssueNew issue, IssueStep currentStep,
			IssueOperationContext context) throws UnsupportOperationException,
			IssueOperationException {
		if (currentStep.getSuperviseLevel() < NORMAL_SUPERVISE) {
			throw new IssueOperationException("本事件并未受到督办");
		} else if (!notParentOrg(getLoginedOrg(context),
				currentStep.getTarget())) {
			throw new IssueOperationException("不能取消上级部门的督办事件");
		}
		currentStep.setSuperviseLevel(NO_SUPERVISE);
		currentStep.setIsExtended(ISSUE_NOT_EXTENDED);
	}

}
