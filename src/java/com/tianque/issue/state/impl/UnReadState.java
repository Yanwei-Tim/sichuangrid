package com.tianque.issue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueOperationContext;
import com.tianque.issue.state.IssueStepInfo;
import com.tianque.issue.state.OrganizationInfo;
import com.tianque.issue.state.exception.IssueOperationException;
import com.tianque.issue.state.exception.UnsupportOperationException;

public class UnReadState extends CanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "未阅读";
	}

	@Override
	public void read(IssueNew issue, IssueStep currentStep, IssueOperationContext context)
			throws UnsupportOperationException, IssueOperationException {
		if (!isSameOrg(getLoginedOrg(context), context.getTargetOrg())) {
			throw new IssueOperationException("不能阅读非本部门的事件");
		}
		stepCompleteCurrentStep(currentStep);
	}

	@Override
	public List<IssueOperate> getCando(IssueStepInfo step, OrganizationInfo orgInfo) {
		List<IssueOperate> result = new ArrayList<IssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			result.add(IssueOperate.READ);
		}
		if (isHighLevelOrg(orgInfo.getOrganization().getOrgLevel().getInternalId(), step
				.getOperationOrg().getOrgLevel().getInternalId())
				|| isSameLevelOrg(orgInfo.getOrganization().getOrgLevel().getInternalId(), step
						.getOperationOrg().getOrgLevel().getInternalId())) {
			if (step.getSuperviseLevel() < RED_CARD_SUPERVISE) {
				result.add(IssueOperate.RED_SUPERVISE);
			}
			if (step.getSuperviseLevel() < YELLOW_CARD_SUPERVISE) {
				result.add(IssueOperate.YELLOW_SUPERVISE);
			}
			if (step.getSuperviseLevel() < NORMAL_SUPERVISE) {
				result.add(IssueOperate.NORMAL_SUPERVISE);
			}
			if (step.getSuperviseLevel() > NO_SUPERVISE) {
				result.add(IssueOperate.CANCEL_SUPERVISE);
			}
		}
		return result;
	}
}
