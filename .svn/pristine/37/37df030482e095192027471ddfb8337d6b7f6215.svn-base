package com.tianque.fourTeams.fourTeamsIssue.state.impl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperationContext;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsOrganizationInfo;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsIssueOperationException;
import com.tianque.fourTeams.fourTeamsIssue.state.exception.FourTeamsUnsupportOperationException;

public class FourTeamsUnReadState extends FourTeamsCanSuperviseState {

	@Override
	protected String getStateLabel() {
		return "未阅读";
	}

	@Override
	public void read(FourTeamsIssueNew issue, FourTeamsIssueStep currentStep,
			FourTeamsIssueOperationContext context) throws FourTeamsUnsupportOperationException,
			FourTeamsIssueOperationException {
		if (!isSameOrg(getLoginedOrg(context), context.getTargetOrg())) {
			throw new FourTeamsIssueOperationException("不能阅读非本部门的事件");
		}
		stepCompleteCurrentStep(currentStep);
	}

	@Override
	public List<FourTeamsIssueOperate> getCando(FourTeamsIssueStepInfo step,
			FourTeamsOrganizationInfo orgInfo) {
		List<FourTeamsIssueOperate> result = new ArrayList<FourTeamsIssueOperate>();
		if (step.getOperationOrg().equals(orgInfo.getOrganization())) {
			result.add(FourTeamsIssueOperate.READ);
		}
		if (isHighLevelOrg(orgInfo.getOrganization().getOrgLevel()
				.getInternalId(), step.getOperationOrg().getOrgLevel()
				.getInternalId())
				|| isSameLevelOrg(orgInfo.getOrganization().getOrgLevel()
						.getInternalId(), step.getOperationOrg().getOrgLevel()
						.getInternalId())) {
			if (step.getSuperviseLevel() < RED_CARD_SUPERVISE) {
				result.add(FourTeamsIssueOperate.RED_SUPERVISE);
			}
			if (step.getSuperviseLevel() < YELLOW_CARD_SUPERVISE) {
				result.add(FourTeamsIssueOperate.YELLOW_SUPERVISE);
			}
			if (step.getSuperviseLevel() < NORMAL_SUPERVISE) {
				result.add(FourTeamsIssueOperate.NORMAL_SUPERVISE);
			}
			if (step.getSuperviseLevel() > NO_SUPERVISE) {
				result.add(FourTeamsIssueOperate.CANCEL_SUPERVISE);
			}
		}
		return result;
	}
}
